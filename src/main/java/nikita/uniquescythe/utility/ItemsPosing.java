package nikita.uniquescythe.utility;

import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.LivingEntity;
import nikita.uniquescythe.items.custom.GunItem;
import nikita.uniquescythe.items.custom.JusticeRevolverItem;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ClientOnly
public class ItemsPosing {

	public static void pointGun(ModelPart holdingArm, ModelPart otherArm, ModelPart head, LivingEntity entity, CallbackInfo callbackInfo, boolean rightArmed) {
		if(entity.getMainHandStack().getItem() instanceof GunItem || entity.getOffHandStack().getItem() instanceof GunItem) {//change it in advance, as well as GunItem class
			ModelPart modelPart = rightArmed ? holdingArm : otherArm;
			ModelPart modelPart2 = rightArmed ? otherArm : holdingArm;

			modelPart2.yaw = (rightArmed ? 0.5F : -0.5F) + head.yaw;

			modelPart2.pitch = ((float) (-Math.PI / 2) ) + head.pitch;

			//inn or away of the players body.
			modelPart.yaw =head.yaw + (rightArmed ? -0.5F : 0.5F);

			//this one seems to respond for a horizontal axis(is arm towards ceiling or floor)
			modelPart.pitch = (float) (-Math.PI / 2) + head.pitch;


			//head.pitch or head.yaw is to make arms follow the same as players head



			callbackInfo.cancel();
		}
	}



}
