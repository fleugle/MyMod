package nikita.uniquescythe.blocks.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MinerBlock extends HorizontallyDirectionalBlock{
	public MinerBlock(Settings settings) {
		super(settings);
	}

	// Method to scan blocks underneath
	public void scanBlocksBelow(World world, BlockPos pos) {
		if (!world.isClient) {
			boolean isCharged = world.isReceivingRedstonePower(pos);
			int blocksFound = 0;
			boolean shouldDropIron;
			boolean shouldDropRedstone;
			boolean shouldDropDiamonds;
			boolean shouldDropGold;
			boolean shouldDropAmethyst;
			ItemStack dropItem = new ItemStack(Items.DIAMOND); // Example item to drop

			for (int i = 1; i <= 50; i++) {
				BlockPos checkPos = pos.down(i);
				BlockState checkState = world.getBlockState(checkPos);

				// Check for a specific block, for example, an iron ore block
				if (checkState.isOf(Blocks.IRON_ORE)) {
					shouldDropIron = true;
					blocksFound++;
				}
				if (checkState.isOf(Blocks.GOLD_ORE)) {
					shouldDropIron = true;
					blocksFound++;
				}

				if (checkState.isAir()) {
					break; // Stop scanning if an air block is found
				}


			}

			// If the block is charged and the specified block was found
			if (isCharged && blocksFound > 0) {
				// Drop the item
				ItemEntity itemEntity = new ItemEntity((ServerWorld) world, pos.getX(), pos.getY(), pos.getZ(), dropItem);
				world.spawnEntity(itemEntity);

				// Set the block on cooldown (example: 5 minutes)
				// You'll need to handle the cooldown logic separately
			}

			// If there are other instances of the block
			// You'll need to scan the surrounding area and count instances of MinerBlock
			// This can be done similarly to the scanning logic above
		}
	}


}
