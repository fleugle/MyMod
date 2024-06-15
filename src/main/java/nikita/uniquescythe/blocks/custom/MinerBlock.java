package nikita.uniquescythe.blocks.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinerBlock extends HorizontallyDirectionalBlock{
	public MinerBlock(Settings settings) {
		super(settings);
	}

	// Method to scan blocks underneath
	public void scanBlocksBelow(World world, BlockPos pos) {
		if (!world.isClient) {
			boolean isCharged = world.isReceivingRedstonePower(pos);
			int blocksFound = 0;
			boolean shouldDropIron = false;
			boolean shouldDropRedstone = false;
			boolean shouldDropDiamonds = false;
			boolean shouldDropGold = false;
			boolean shouldDropAmethyst = false;



			for (int i = 1; i <= 50; i++) {
				BlockPos checkPos = pos.down(i);
				BlockState checkState = world.getBlockState(checkPos);

				// Check for a specific block, for example, an iron ore block
				if (checkState.isOf(Blocks.IRON_ORE)) {
					shouldDropIron = true;

				} else shouldDropIron = false;
				if (checkState.isOf(Blocks.GOLD_ORE)) {
					shouldDropGold= true;

				}
				if (checkState.isOf(Blocks.REDSTONE_ORE)) {
					shouldDropRedstone= true;

				}
				if (checkState.isOf(Blocks.DIAMOND_ORE)) {
					shouldDropDiamonds= true;

				}
				if (checkState.isOf(Blocks.AMETHYST_BLOCK)
				|| checkState.isOf(Blocks.AMETHYST_CLUSTER)) {
					shouldDropAmethyst= true;

				}


				if (!checkState.isAir() && checkState.isSolidBlock(world, checkPos)) {

					blocksFound++;

				}

				if (checkState.isAir()) {
					break; // Stop scanning if an air block is found
				}


			}

			// If the block is charged and the specified block was found
			if (isCharged && blocksFound > 0) {
				// Drop the item
				ItemEntity itemEntity = null;
				List<ItemStack> possibleItems = new ArrayList<>();

				if (shouldDropDiamonds) {
					possibleItems.add(new ItemStack(Items.DIAMOND));
				}

				if (shouldDropIron) {
					possibleItems.add(new ItemStack(Items.IRON_INGOT));
				}

				if (shouldDropGold) {
					possibleItems.add(new ItemStack(Items.GOLD_INGOT));
				}

				if (shouldDropRedstone) {
					possibleItems.add(new ItemStack(Items.REDSTONE));
				}

				if (shouldDropAmethyst) {
					possibleItems.add(new ItemStack(Items.AMETHYST_SHARD));
				}

				if (!possibleItems.isEmpty()) {
					Random random = new Random();
					ItemStack selectedItem = possibleItems.get(random.nextInt(possibleItems.size()));
					itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), selectedItem);
					world.spawnEntity(itemEntity);
				}

				// Set the block on cooldown (example: 5 minutes)
				// You'll need to handle the cooldown logic separately
			}

			// If there are other instances of the block
			// You'll need to scan the surrounding area and count instances of MinerBlock
			// This can be done similarly to the scanning logic above
		}
	}


}
