package nikita.uniquescythe.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinerBlock extends HorizontallyDirectionalBlock{
	public static final BooleanProperty POWERED = Properties.POWERED;
	public static final BooleanProperty OPENED = BooleanProperty.of("opened");


	public MinerBlock(Settings settings) {
		super(settings);
		setDefaultState(this.stateManager.getDefaultState().with(POWERED, false));
	}

	// Method to scan blocks underneath
	public void scanBlocksBelow(BlockState state, ServerWorld world, BlockPos pos) {
		if (!world.isClient) {
			boolean isCharged = world.isReceivingRedstonePower(pos);

			boolean shouldDropIron = false;
			boolean shouldDropRedstone = false;
			boolean shouldDropDiamonds = false;
			boolean shouldDropGold = false;
			boolean shouldDropAmethyst = false;
			boolean shouldDropEmeralds = false;
			boolean shouldDropCopper = false;
			boolean shouldDropLapis = false;


			world.setBlockState(pos, (BlockState)state.with(POWERED, isCharged), Block.NOTIFY_ALL);


			if (isCharged) {
				for (int i = 1; i <= 50; i++) {
					BlockState checkState = null;
					BlockPos checkPos = pos.down(i);
					checkState = world.getBlockState(checkPos);
					if (!checkState.isAir() && checkState.isSolidBlock(world, checkPos)) {


						// Check for a specific block, for example, an iron ore block
						if (checkState.isOf(Blocks.IRON_ORE)
							|| checkState.isOf(Blocks.DEEPSLATE_IRON_ORE)) {
							shouldDropIron = true;

						} else shouldDropIron = false;
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

						// Drop the item
						ItemEntity itemEntity;
						List<ItemStack> possibleItems = new ArrayList<>();

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


						if (!possibleItems.isEmpty()) {
							Random random = new Random();
							ItemStack selectedItem = possibleItems.get(random.nextInt(possibleItems.size()));
							itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), selectedItem);
							world.spawnEntity(itemEntity);
						}

					} else break;
				}
			}

			// If the block is charged and the specified block was found

		}
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		// Add the HORIZONTAL_FACING property
		builder.add(POWERED, OPENED);
	}

}
