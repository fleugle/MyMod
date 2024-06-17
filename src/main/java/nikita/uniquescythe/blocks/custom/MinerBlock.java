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
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinerBlock extends HorizontallyDirectionalBlock{
	public static final BooleanProperty POWERED = Properties.POWERED;
	public static final BooleanProperty OPENED = BooleanProperty.of("opened");
	public static final BooleanProperty DROPPED_ITEM = BooleanProperty.of("dropped_item");


	public MinerBlock(Settings settings) {
		super(settings);
		setDefaultState(this.stateManager.getDefaultState()
			.with(POWERED, false)
			.with(OPENED, false)
			.with(DROPPED_ITEM, false));
	}

	// Method to scan blocks underneath
	public void scanBlocksBelow(BlockState state, ServerWorld world, BlockPos pos) {
		if (!world.isClient) {
			boolean isCharged = world.isReceivingRedstonePower(pos);

			boolean shouldDropIron;
			boolean shouldDropRedstone;
			boolean shouldDropDiamonds;
			boolean shouldDropGold;
			boolean shouldDropAmethyst;
			boolean shouldDropEmeralds;
			boolean shouldDropCopper;
			boolean shouldDropLapis;


			world.setBlockState(pos, state.with(POWERED, isCharged), Block.NOTIFY_ALL);


			if (isCharged) {

				// Drop the item
				ItemEntity itemEntity;
				List<ItemStack> possibleItems = new ArrayList<>();
				//STOP FUCKING BEING WORRIED ABOUT THIS PART YOU AUTISTIC ASS, IT IS FINE, EACH TIME IT CHECKS IT PICKS ONLY ONE AND ADDS ONLY ONE
				for (int i = 1; i <= 50; i++) {
					BlockState checkState = null;
					BlockPos checkPos = pos.down(i);
					checkState = world.getBlockState(checkPos);
					shouldDropIron = false;
					shouldDropRedstone = false;
					shouldDropDiamonds = false;
					shouldDropGold = false;
					shouldDropAmethyst = false;
					shouldDropEmeralds = false;
					shouldDropCopper = false;
					shouldDropLapis = false;
					if (!checkState.isAir() && checkState.isSolidBlock(world, checkPos)) {


						// Check for a specific block, for example, an iron ore block
						if (checkState.isOf(Blocks.IRON_ORE)
							|| checkState.isOf(Blocks.DEEPSLATE_IRON_ORE)) {
							shouldDropIron = true;

						}
						if (checkState.isOf(Blocks.GOLD_ORE)
							|| checkState.isOf(Blocks.DEEPSLATE_GOLD_ORE)) {
							shouldDropGold= true;

						}
						if (checkState.isOf(Blocks.REDSTONE_ORE)
							|| checkState.isOf(Blocks.DEEPSLATE_REDSTONE_ORE)) {
							shouldDropRedstone= true;

						}
						if (checkState.isOf(Blocks.DIAMOND_ORE)
							|| checkState.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)) {
							shouldDropDiamonds= true;

						}
						if (checkState.isOf(Blocks.AMETHYST_BLOCK)
							|| checkState.isOf(Blocks.AMETHYST_CLUSTER)) {
							shouldDropAmethyst= true;

						}
						if (checkState.isOf(Blocks.EMERALD_ORE)
							|| checkState.isOf(Blocks.DEEPSLATE_EMERALD_ORE)) {
							shouldDropEmeralds= true;

						}
						if (checkState.isOf(Blocks.COPPER_ORE)
							|| checkState.isOf(Blocks.DEEPSLATE_COPPER_ORE)) {
							shouldDropCopper= true;

						}
						if (checkState.isOf(Blocks.LAPIS_ORE)
							|| checkState.isOf(Blocks.DEEPSLATE_LAPIS_ORE)) {
							shouldDropLapis= true;

						}



						//Adding item to array
						if (shouldDropDiamonds) {
							possibleItems.add(new ItemStack(Items.DIAMOND));
						}

						if (shouldDropIron) {
							possibleItems.add(new ItemStack(Items.RAW_IRON));
						}

						if (shouldDropGold) {
							possibleItems.add(new ItemStack(Items.RAW_GOLD));
						}

						if (shouldDropRedstone) {
							possibleItems.add(new ItemStack(Items.REDSTONE));
						}

						if (shouldDropAmethyst) {
							possibleItems.add(new ItemStack(Items.AMETHYST_SHARD));
						}

						if (shouldDropEmeralds) {
							possibleItems.add(new ItemStack(Items.EMERALD));
						}

						if (shouldDropCopper) {
							possibleItems.add(new ItemStack(Items.RAW_COPPER));
						}

						if (shouldDropLapis) {
							possibleItems.add(new ItemStack(Items.LAPIS_LAZULI));
						}




					} else break;


				}
				if (!possibleItems.isEmpty()) {

					world.setBlockState(pos, state.with(OPENED, true), Block.NOTIFY_ALL);

					world.scheduleBlockTick(pos, this, 45);

							/*Random random = new Random();
							ItemStack selectedItem = possibleItems.get(random.nextInt(possibleItems.size()));
							itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), selectedItem);
							world.spawnEntity(itemEntity);*/
					Random random = new Random();
					ItemStack selectedItem = possibleItems.get(random.nextInt(possibleItems.size()));

					// Determine the facing direction of the block
					Direction facing = state.get(HorizontalFacingBlock.FACING);
					double spawnX = pos.getX() + 0.5 + facing.getOffsetX() * 0.6;
					double spawnY = pos.getY() + 0.25;// 'mouth' is located pretty low though
					double spawnZ = pos.getZ() + 0.5 + facing.getOffsetZ() * 0.6;

					itemEntity = new ItemEntity(world, spawnX, spawnY, spawnZ, selectedItem);

					// Set the velocity to make the item move forward
					double velocityX = facing.getOffsetX() * 0.2;
					double velocityY = 0;
					double velocityZ = facing.getOffsetZ() * 0.2;
					itemEntity.setVelocity(velocityX, velocityY, velocityZ);

					world.spawnEntity(itemEntity);


					//sounds here
					world.scheduleBlockTick(pos, this, 45);//not a delay. it's a shedular for sheduleTick method, so i need to override it for goods.

					world.setBlockState(pos, (BlockState)state.with(OPENED, true), Block.NOTIFY_ALL);

					world.scheduleBlockTick(pos, this, 45);


				}
			}

			// If the block is charged and the specified block was found

		}
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {

		if(state.get(OPENED)){
			world.setBlockState(pos, state.with(OPENED, false), Block.NOTIFY_ALL);
		}

		/*
		*
		* I can actually add here a logic based on a new state "DROPPED_ITEM",
		* so if it is true, assign false to it and then
		* schedule this same method again in 45 ticks,
		*  and  if false do nothing.
		*
		*
		* I can put it before the OPENED check, so I can handle multiple actions.
		*
		 */

	}

	// Method to count the number of MinerBlock instances in the current chunk
	private int countMinerBlocksInChunk(ServerWorld world, BlockPos pos) {
		int count = 0;
		Chunk chunk = world.getChunk(pos);
		for (BlockPos chunkPos : BlockPos.iterate(chunk.getPos().getStartX(), 0, chunk.getPos().getStartZ(), chunk.getPos().getEndX(), world.getHeight(), chunk.getPos().getEndZ())) {
			if (world.getBlockState(chunkPos).getBlock() instanceof MinerBlock) {
				count++;
			}
		}
		return count;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		// Add the HORIZONTAL_FACING property
		builder.add(POWERED, OPENED, DROPPED_ITEM);
	}

}
