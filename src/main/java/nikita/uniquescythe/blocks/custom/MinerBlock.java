package nikita.uniquescythe.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinerBlock extends HorizontallyDirectionalBlock {
	public static final BooleanProperty POWERED = Properties.POWERED;
	public static final BooleanProperty OPENED = BooleanProperty.of("opened");
	public static final BooleanProperty DROPPED_ITEM = BooleanProperty.of("dropped_item");
	/*public static final BooleanProperty READY = BooleanProperty.of("ready");*/
	public static final BooleanProperty FOUND_RESOURCE = BooleanProperty.of("found_resource");

	public MinerBlock(Settings settings) {
		super(settings);
		setDefaultState(this.stateManager.getDefaultState()
			.with(POWERED, false)
			.with(OPENED, false)
			.with(DROPPED_ITEM, false)
			/*.with(READY, false)*/
			.with(FOUND_RESOURCE, false)
			.with(FACING, Direction.NORTH));
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		boolean isPowered = world.isReceivingRedstonePower(pos);
		if (oldState.getBlock() != state.getBlock() && world instanceof ServerWorld) {
			if (isPowered != state.get(POWERED) && world instanceof ServerWorld) world.setBlockState(pos, state.with(POWERED, isPowered), Block.NOTIFY_ALL);
			world.scheduleBlockTick(pos,this, 45);
		}
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
		boolean isPowered = world.isReceivingRedstonePower(pos);
		if (isPowered != state.get(POWERED) && world instanceof ServerWorld) {
			world.setBlockState(pos, state.with(POWERED, isPowered), Block.NOTIFY_ALL);
			if (isPowered) {
				world.scheduleBlockTick(pos,this, 45);
			}
		}
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {

		scanBlocksBelow( state, world, pos);

	}

	public void scanBlocksBelow(BlockState oldState, ServerWorld world, BlockPos pos) {

		BlockState state = oldState;

		if (!world.isClient) {
			boolean isCharged = world.isReceivingRedstonePower(pos);
			world.setBlockState(pos, state.with(POWERED, isCharged), Block.NOTIFY_ALL);

			if (oldState.get(POWERED)) {
				List<ItemStack> possibleItems = new ArrayList<>();
				for (int i = 1; i <= 50; i++) {
					BlockState checkState = world.getBlockState(pos.down(i));

					if (!checkState.isAir() && checkState.isSolidBlock(world, pos.down(i))) {
						if (checkState.isOf(Blocks.IRON_ORE) || checkState.isOf(Blocks.DEEPSLATE_IRON_ORE)) {
							possibleItems.add(new ItemStack(Items.RAW_IRON));
						}
						if (checkState.isOf(Blocks.GOLD_ORE) || checkState.isOf(Blocks.DEEPSLATE_GOLD_ORE)) {
							possibleItems.add(new ItemStack(Items.RAW_GOLD));
						}
						if (checkState.isOf(Blocks.REDSTONE_ORE) || checkState.isOf(Blocks.DEEPSLATE_REDSTONE_ORE)) {
							possibleItems.add(new ItemStack(Items.REDSTONE));
						}
						if (checkState.isOf(Blocks.DIAMOND_ORE) || checkState.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)) {
							possibleItems.add(new ItemStack(Items.DIAMOND));
						}
						if (checkState.isOf(Blocks.AMETHYST_BLOCK) || checkState.isOf(Blocks.AMETHYST_CLUSTER)) {
							possibleItems.add(new ItemStack(Items.AMETHYST_SHARD));
						}
						if (checkState.isOf(Blocks.EMERALD_ORE) || checkState.isOf(Blocks.DEEPSLATE_EMERALD_ORE)) {
							possibleItems.add(new ItemStack(Items.EMERALD));
						}
						if (checkState.isOf(Blocks.COPPER_ORE) || checkState.isOf(Blocks.DEEPSLATE_COPPER_ORE)) {
							possibleItems.add(new ItemStack(Items.RAW_COPPER));
						}
						if (checkState.isOf(Blocks.LAPIS_ORE) || checkState.isOf(Blocks.DEEPSLATE_LAPIS_ORE)) {
							possibleItems.add(new ItemStack(Items.LAPIS_LAZULI));
						}
					} else {
						break;
					}
				}

				if (!possibleItems.isEmpty()) {
					// Set FOUND_RESOURCE to true
					/*world.setBlockState(pos, state.with(FOUND_RESOURCE, true));*/

					// Fetch the updated block state to ensure it has been applied


					int delay = calculateFinalCooldown(countMinerBlocksInChunk(world, pos), 45);


					if (!oldState.get(DROPPED_ITEM)) {
						world.scheduleBlockTick(pos, this, 45);
						if(!oldState.get(OPENED)) world.setBlockState(pos, state.with(OPENED, true), NOTIFY_ALL);
					}
					else {
						world.scheduleBlockTick(pos, this, delay);
						if(oldState.get(DROPPED_ITEM)) world.setBlockState(pos, state.with(DROPPED_ITEM, false), NOTIFY_ALL);
						if(oldState.get(OPENED)) world.setBlockState(pos, state.with(OPENED, false), NOTIFY_ALL);
					}



					if (oldState.get(OPENED) && !oldState.get(DROPPED_ITEM)) {
						Random random = new Random();
						ItemStack selectedItem = possibleItems.get(random.nextInt(possibleItems.size()));

						Direction facing = state.get(HorizontalFacingBlock.FACING);
						double spawnX = pos.getX() + 0.5 + facing.getOffsetX() * 0.6;
						double spawnY = pos.getY() + 0.25;
						double spawnZ = pos.getZ() + 0.5 + facing.getOffsetZ() * 0.6;

						ItemEntity itemEntity = new ItemEntity(world, spawnX, spawnY, spawnZ, selectedItem);

						double velocityX = facing.getOffsetX() * 0.2;
						double velocityY = 0;
						double velocityZ = 0;
						itemEntity.setVelocity(velocityX, velocityY, velocityZ);

						world.spawnEntity(itemEntity);

						if(!oldState.get(DROPPED_ITEM)) world.setBlockState(pos, state.with(DROPPED_ITEM, true));


					}

				}
			}
		}
	}

	private int calculateFinalCooldown(int minerBlockCount, int initialCooldown) {
		return (int) (initialCooldown + (3 * Math.log(minerBlockCount + 1) / Math.log(2)) + (double) minerBlockCount / 4);
	}

	private int countMinerBlocksInChunk(ServerWorld world, BlockPos pos) {
		int count = 0;
		Chunk chunk = world.getChunk(pos);

		int startY = Math.max(world.getBottomY(), pos.getY() - 8);
		int endY = Math.min(world.getTopY() - 1, pos.getY() + 8);

		for (BlockPos chunkPos : BlockPos.iterate(chunk.getPos().getStartX(), startY, chunk.getPos().getStartZ(),
			chunk.getPos().getEndX(), endY, chunk.getPos().getEndZ())) {
			if (world.getBlockState(chunkPos).getBlock() instanceof MinerBlock) {
				count++;
			}
		}

		return count;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(POWERED, OPENED, DROPPED_ITEM, /*READY,*/ FOUND_RESOURCE);
	}
}
