package nikita.uniquescythe.blocks.custom;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;

import net.minecraft.block.Oxidizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;


public class OxidizableBulbBlock
	extends BulbBlock
	implements Oxidizable {
	private final Oxidizable.OxidizationLevel oxidizationLevel;



	public OxidizableBulbBlock(Oxidizable.OxidizationLevel oxidizationLevel, AbstractBlock.Settings settings) {
		super(settings);
		this.oxidizationLevel = oxidizationLevel;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		this.tickDegradation(state, world, pos, random);
	}

	@Override
	public boolean hasRandomTicks(BlockState state) {
		return Oxidizable.getIncreasedOxidationBlock(state.getBlock()).isPresent();
	}


	public Oxidizable.OxidizationLevel getDegradationLevel() {
		return this.oxidizationLevel;
	}

}
