package nikita.uniquescythe.utility;

import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import org.quiltmc.loader.api.minecraft.ClientOnly;

@ClientOnly
public class ItemsPosing {
	public static void pointGun(ModelPart holdingArm, ModelPart otherArm, ModelPart head, boolean rightArmed) {
		ModelPart modelPart = rightArmed ? holdingArm : otherArm;
		ModelPart modelPart2 = rightArmed ? otherArm : holdingArm;

		modelPart2.yaw = (rightArmed ? 0.6F : -0.6F) + head.yaw;

		modelPart2.pitch = -1.5F + head.pitch;

		//inn or away of the players body.
		modelPart.yaw = head.yaw -0.5f;

		//this one seems to respond for a horizontal axis(is arm towards ceiling or floor)
		modelPart.pitch = (float) (-Math.PI / 2) + head.pitch ;
	}

}
