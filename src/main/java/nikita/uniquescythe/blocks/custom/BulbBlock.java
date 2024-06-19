package nikita.uniquescythe.blocks.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import net.minecraft.world.World;
import nikita.uniquescythe.sounds.ModSoundEvents;

public class BulbBlock
	extends Block {
	//public static final MapCodec<BulbBlock> CODEC = BulbBlock.createCodec(BulbBlock::new);
	public static final BooleanProperty POWERED = Properties.POWERED;
	public static final BooleanProperty LIT = Properties.LIT;




	public BulbBlock(AbstractBlock.Settings settings) {
		super(settings);
		this.setDefaultState((BlockState)((BlockState)this.getDefaultState().with(LIT, false)).with(POWERED, false));
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		if (oldState.getBlock() != state.getBlock() && world instanceof ServerWorld) {
			ServerWorld serverWorld = (ServerWorld)world;
			world.scheduleBlockTick(pos, this, 1);
		}
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
		if (world instanceof ServerWorld) {

			ServerWorld serverWorld = (ServerWorld)world;
			world.scheduleBlockTick(pos, this, 1);
		}
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
		if (world instanceof ServerWorld) {

			ServerWorld serverWorld = (ServerWorld)world;
			this.update(state, serverWorld, pos);
		}
	}

	public void update(BlockState state, ServerWorld world, BlockPos pos) {
		boolean isReceivingRedstonePower = world.isReceivingRedstonePower(pos);
		BlockState blockState = state;

		if (isReceivingRedstonePower == state.get(POWERED)) {
			return;
		}
		if (!state.get(POWERED).booleanValue()) {
			world.playSound(null, pos, (blockState = (BlockState)blockState.cycle(LIT)).get(LIT) != false ? ModSoundEvents.BLOCK_COPPER_BULB_TURN_ON : ModSoundEvents.BLOCK_COPPER_BULB_TURN_OFF, SoundCategory.BLOCKS);
		}
		world.setBlockState(pos, (BlockState)blockState.with(POWERED, isReceivingRedstonePower), Block.NOTIFY_ALL);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(LIT, POWERED);
	}

	@Override
	public boolean hasComparatorOutput(BlockState state) {
		return true;
	}

	@Override
	public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return world.getBlockState(pos).get(LIT) != false ? 15 : 0;
	}
}
