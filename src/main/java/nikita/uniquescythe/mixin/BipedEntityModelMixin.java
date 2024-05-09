package nikita.uniquescythe.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import net.minecraft.entity.LivingEntity;
import nikita.uniquescythe.items.custom.GunItem;
import nikita.uniquescythe.utility.ItemsPosing;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin {

	@Shadow
	public @Final ModelPart head;

	@Shadow
	public @Final ModelPart rightArm;

	@Shadow
	public @Final ModelPart leftArm;

	@Inject(
		method = {"positionRightArm", "positionLeftArm"},
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/render/entity/model/CrossbowPosing;hold(Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/model/ModelPart;Z)V",
			shift = At.Shift.AFTER
		),
		cancellable = true
	)
	public void poseArms(LivingEntity entity, CallbackInfo ci) {
		if(entity.getMainHandStack().getItem() instanceof GunItem || entity.getOffHandStack().getItem() instanceof GunItem) {

			ItemsPosing.pointGun(this.rightArm, this.leftArm, this.head, true);


			ci.cancel();
		}
	}
}
