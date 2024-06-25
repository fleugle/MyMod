package nikita.uniquescythe.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class CopperPipeBlock extends HorizontallyDirectionalBlock{
	public static final BooleanProperty POWERED = Properties.POWERED;

	public CopperPipeBlock(Settings settings) {
		super(settings);
		setDefaultState(this.stateManager.getDefaultState()
			.with(POWERED, false)
		);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(POWERED);
	}




	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockPos belowPos = pos.down();
		BlockState belowBlockState = world.getBlockState(belowPos);
		Block belowBlock = belowBlockState.getBlock();
		return
				belowBlock instanceof FurnaceBlock
				|| belowBlock instanceof MinerBlock
				|| belowBlock instanceof BlastFurnaceBlock
				|| belowBlock instanceof SmokerBlock;
	}


	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		boolean isPowered = world.isReceivingRedstonePower(pos);
		if (oldState.getBlock() != state.getBlock() && world instanceof ServerWorld) {
			if (isPowered != state.get(POWERED) && world instanceof ServerWorld) world.setBlockState(pos, state.with(POWERED, isPowered), Block.NOTIFY_ALL);
		}
		if (state.get(POWERED)) world.scheduleBlockTick(pos,this, 20);
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
		boolean isPowered = world.isReceivingRedstonePower(pos);
		if (isPowered != state.get(POWERED) && world instanceof ServerWorld) {
			world.setBlockState(pos, state.with(POWERED, isPowered), Block.NOTIFY_ALL);
		}
		if (state.get(POWERED)) world.scheduleBlockTick(pos,this, 20);
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {

		if (world instanceof ServerWorld && state.get(POWERED)) {


			world.spawnParticles(ParticleTypes.LARGE_SMOKE,
				pos.getX()  + 0.5,
				pos.getY()  + 1.15,
				pos.getZ()  + 0.5,
				0, 0, 3, 0, 0);

			/*world.spawnParticles(ParticleTypes.SMOKE,
				pos.getX()  + 0.5,
				pos.getY()  + 1.15,
				pos.getZ()  + 0.5,
				8, 0.15, 0.25, 0.15, 0.05);*/

		}

		world.scheduleBlockTick(pos,this, 20);

	}
}
