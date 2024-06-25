package nikita.uniquescythe.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.*;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import nikita.uniquescythe.blocks.ModBlocks;
import nikita.uniquescythe.sounds.ModSoundEvents;
import nikita.uniquescythe.utility.MinerDispenseBehavior;
import nikita.uniquescythe.utility.SoundsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinerBlock extends HorizontallyDirectionalBlock {
	private static final DispenserBehavior BEHAVIOR = new MinerDispenseBehavior();
	public static final BooleanProperty POWERED = Properties.POWERED;
	private static final BooleanProperty OPENED = BooleanProperty.of("opened");
	private static final BooleanProperty DROPPED_ITEM = BooleanProperty.of("dropped_item");
	/*public static final BooleanProperty READY = BooleanProperty.of("ready");*/
	private static final BooleanProperty FOUND_RESOURCE = BooleanProperty.of("found_resource");

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

	public static Position getOutputLocation(BlockPointer pointer) {
		Direction direction = (Direction)pointer.getBlockState().get(FACING);
		double d = pointer.getX() + 0.7 * (double)direction.getOffsetX();
		double e = pointer.getY() + 0.7 * (double)direction.getOffsetY();
		double f = pointer.getZ() + 0.7 * (double)direction.getOffsetZ();
		return new PositionImpl(d, e, f);
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

	private void scanBlocksBelow(BlockState oldState, ServerWorld world, BlockPos pos) {
		BlockState state = oldState;

		BlockPos posAbove = pos.up(1);
		BlockState checkForTurbine = world.getBlockState(posAbove);

		int delay;
		if (checkForTurbine.isOf(ModBlocks.COPPER_PIPE) && checkForTurbine.get(CopperPipeBlock.POWERED)) {
			delay = calculateFinalCooldown(countMinerBlocksInChunk(world, pos), 80);
		}
		else delay = calculateFinalCooldown(countMinerBlocksInChunk(world, pos), 800);


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

					if (!oldState.get(FOUND_RESOURCE)) world.setBlockState(pos, oldState.with(FOUND_RESOURCE, true));


					if (oldState.get(OPENED) && !oldState.get(DROPPED_ITEM)) {
						Random random = new Random();
						ItemStack selectedItem = possibleItems.get(random.nextInt(possibleItems.size()));

						if (world instanceof ServerWorld) {

							world.spawnParticles(DustParticleEffect.DEFAULT,
								pos.getX()  + 0.5,
								pos.getY()  + 0.5,
								pos.getZ()  + 0.5,
								5, 0.5, 0.5, 0.5, 0.2);

							world.spawnParticles(ParticleTypes.LARGE_SMOKE,
								pos.getX()  + 0.5,
								pos.getY()  + 1.15,
								pos.getZ()  + 0.5,
								0, 0, 2, 0, 0);

							world.spawnParticles(ParticleTypes.SMOKE,
								pos.getX()  + 0.5,
								pos.getY()  + 1.15,
								pos.getZ()  + 0.5,
								8, 0.15, 0.25, 0.15, 0.05);
						}
						dispenseResource(world, pos, possibleItems.size(), selectedItem);


						if (!oldState.get(DROPPED_ITEM)) world.setBlockState(pos, state.with(DROPPED_ITEM, true));
						/*if(oldState.get(FOUND_RESOURCE)) world.setBlockState(pos, oldState.with(FOUND_RESOURCE, false));*/

					}


				} else {
					if (oldState.get(FOUND_RESOURCE)) world.setBlockState(pos, oldState.with(FOUND_RESOURCE, false));
				}

				if (!oldState.get(DROPPED_ITEM)) {

					if (!oldState.get(OPENED) && oldState.get(FOUND_RESOURCE)) {
						SoundsManager.playBlocksSoundOnSpot(world, pos, ModSoundEvents.PREPARE_MINER, 1f);
						world.setBlockState(pos, state.with(OPENED, true), NOTIFY_ALL);
					}

					world.scheduleBlockTick(pos, this, 45);
				} else {

					if (oldState.get(DROPPED_ITEM)) world.setBlockState(pos, state.with(DROPPED_ITEM, false), NOTIFY_ALL);
					if (oldState.get(OPENED)) {
						world.setBlockState(pos, state.with(OPENED, false), NOTIFY_ALL);
						SoundsManager.playBlocksSoundOnSpot(world, pos, ModSoundEvents.CLOSE_MINER, 1f);
					}

					world.scheduleBlockTick(pos, this, delay);
				}

			}
		}


	}

	private int calculateFinalCooldown(int minerBlockCount, int initialCooldown) {
		return (int) (initialCooldown + (8 * ((minerBlockCount - 1)*(minerBlockCount - 1))) );

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

	protected void dispenseResource(ServerWorld world, BlockPos pos, int itemsAmount, ItemStack stack) {
		BlockPointerImpl blockPointerImpl = new BlockPointerImpl(world, pos);

		if (itemsAmount < 0) {
			world.syncWorldEvent(1001, pos, 0);
		} else {
			ItemStack itemStack = stack;
			if (!itemStack.isEmpty()) {


				BEHAVIOR.dispense(blockPointerImpl, itemStack);
				SoundsManager.playBlocksSoundOnSpot(world, pos, ModSoundEvents.DISPENSE_MINER, 4f);

			}
		}
	}



	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(POWERED, OPENED, DROPPED_ITEM, FOUND_RESOURCE);
	}
}
