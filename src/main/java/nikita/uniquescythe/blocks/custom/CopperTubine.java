package nikita.uniquescythe.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class CopperTubine extends HorizontallyDirectionalBlock{
	public CopperTubine(Settings settings) {
		super(settings);
	}


	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockPos belowPos = pos.down();
		BlockState belowBlockState = world.getBlockState(belowPos);
		Block belowBlock = belowBlockState.getBlock();
		return belowBlock instanceof FurnaceBlock || belowBlock instanceof CraftingTableBlock;
	}
}
