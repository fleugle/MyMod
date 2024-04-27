package nikita.uniquescythe.utility;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Axis;
import net.minecraft.util.math.MathHelper;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.items.custom.GunItem;

public class RenderHelper {
	public static boolean disableMainHandEquipAnimation;
	public static boolean disableOffhandEquipAnimation;



	public static void renderSpecificFirstPersonHand(HeldItemRenderer renderer,
													 AbstractClientPlayerEntity player,
													 Hand hand,
													 float partialTicks,
													 float interpolatedPitch,
													 float swingProgress, float equipProgress,
													 ItemStack stack, MatrixStack matrixStack,
													 VertexConsumerProvider render, int packedLight) {
		Arm handside = hand == Hand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
		boolean isRightHand = handside == Arm.RIGHT;
		float sign = isRightHand ? 1 : -1;

		GunItem gunItem = (GunItem)stack.getItem();
		if (!gunItem.canUseFrom(player, hand)) {
			matrixStack.push();
			matrixStack.translate(sign * 0.5, -0.5 - 0.6 * equipProgress, -0.7);
			matrixStack.multiply(Axis.X_POSITIVE.rotationDegrees(70));
			renderer.renderItem(player, stack, isRightHand ? ModelTransformationMode.FIRST_PERSON_RIGHT_HAND : ModelTransformationMode.FIRST_PERSON_LEFT_HAND, !isRightHand, matrixStack, render, packedLight);
			matrixStack.pop();
			return;
		}

		if (stack == GunItem.getActiveStack(hand)) {
			setEquipAnimationDisabled(hand, true);
		}

		matrixStack.push();
		matrixStack.translate(sign * 0.15, -0.25, -0.35);

		if (swingProgress > 0) {
			float swingSharp = MathHelper.sin(MathHelper.sqrt(swingProgress) * (float)Math.PI);
			float swingNormal = MathHelper.sin(swingProgress * (float)Math.PI);

			matrixStack.translate(sign * 0.05 * (1 - swingNormal), 0.05 * (1 - swingNormal), 0.05 - 0.4 * swingSharp);
			matrixStack.multiply(Axis.X_POSITIVE.rotationDegrees(180 + sign * 20 * (1 - swingSharp)));


		} else if (player.isUsingItem() && player.getActiveHand() == hand) {
			float usingDuration = stack.getMaxUseTime() - (player.getItemUseTimeLeft() - partialTicks + 1);
			if (usingDuration > 0 && usingDuration < GunItem.RELOAD_DURATION) {
				matrixStack.translate(0, -0.3, 0.05);
				matrixStack.multiply(Axis.X_POSITIVE.rotationDegrees(60));
				matrixStack.multiply(Axis.Y_POSITIVE.rotationDegrees(10));

				if (usingDuration >= 8 && usingDuration <= 14 || usingDuration >= 18 && usingDuration <= 24) {
					if (usingDuration >= 18) usingDuration -= 10;
					float t;
					if (usingDuration < 10) {
						t = (usingDuration - 8) / 2;
						t = MathHelper.sin((float)Math.PI / 2 * MathHelper.sqrt(t));
					} else {
						t = (14 - usingDuration) / 4;
					}
					matrixStack.translate(0, 0, 0.025 * t);
				}
				if (gunItem == ModItems.JUSTICE_REVOLVER) {
					matrixStack.translate(0, 0, -0.12);
				}
			}
		} else {
			if (isEquipAnimationDisabled(hand)) {
				if (equipProgress == 0) {
					setEquipAnimationDisabled(hand, false);
				}
			} else {
				matrixStack.translate(0, -0.6 * equipProgress, 0);
			}
		}

		renderer.renderItem(player, stack, isRightHand ? ModelTransformationMode.FIRST_PERSON_RIGHT_HAND : ModelTransformationMode.FIRST_PERSON_LEFT_HAND, !isRightHand, matrixStack, render, packedLight);
		matrixStack.pop();
	}

	public static boolean isEquipAnimationDisabled(Hand hand) {
		if (hand == Hand.MAIN_HAND) {
			return disableMainHandEquipAnimation;
		} else {
			return disableOffhandEquipAnimation;
		}
	}

	public static void setEquipAnimationDisabled(Hand hand, boolean disabled) {
		if (hand == Hand.MAIN_HAND) {
			disableMainHandEquipAnimation = disabled;
		} else {
			disableOffhandEquipAnimation = disabled;
		}
	}
}
