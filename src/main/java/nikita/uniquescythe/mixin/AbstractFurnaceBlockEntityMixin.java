package nikita.uniquescythe.mixin;


import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nikita.uniquescythe.blocks.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static nikita.uniquescythe.blocks.custom.CopperPipeBlock.POWERED;


@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin extends LockableContainerBlockEntity {

	protected AbstractFurnaceBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
		super(blockEntityType, blockPos, blockState);

	}

	@Inject(method = "getCookTime", at = @At("RETURN"), cancellable = true)
	private static void modifyGetCookTime(World world, AbstractFurnaceBlockEntity blockEntity, CallbackInfoReturnable<Integer> cir) {


		BlockPos pos = blockEntity.getPos();
		BlockPos posAbove = pos.up(1);
		BlockState checkState = world.getBlockState(posAbove);

		int originalCookTime = cir.getReturnValue();

		int modifiedCookTime;

		if (checkState.isOf(ModBlocks.COPPER_PIPE) && checkState.get(POWERED)) {
			modifiedCookTime = originalCookTime / 10;
		}

		else modifiedCookTime = originalCookTime;

		cir.setReturnValue(modifiedCookTime);
	}
}
