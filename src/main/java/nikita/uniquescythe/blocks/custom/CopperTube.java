package nikita.uniquescythe.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class CopperTube extends HorizontallyDirectionalBlock{
	public CopperTube(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (!world.isClient) {
			BlockPos belowPos = pos.down();
			BlockState belowBlockState = world.getBlockState(belowPos);
			Block belowBlock = belowBlockState.getBlock();

			if (belowBlock instanceof FurnaceBlock) {
				//enhanceFurnace(world, belowPos, (FurnaceBlock) belowBlock);
			} else if (belowBlock instanceof CraftingTableBlock) {
				//enhanceCraftingTable(world, belowPos, (CraftingTableBlock) belowBlock);
			}
		}
		return ActionResult.SUCCESS;
	}


	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockPos belowPos = pos.down();
		BlockState belowBlockState = world.getBlockState(belowPos);
		Block belowBlock = belowBlockState.getBlock();
		return belowBlock instanceof FurnaceBlock || belowBlock instanceof CraftingTableBlock;
	}
}
