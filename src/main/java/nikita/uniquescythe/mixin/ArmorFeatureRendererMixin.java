package nikita.uniquescythe.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin {

	@Inject(method = "render", at = @At("HEAD"), cancellable = true)
	public void onRender(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
		if (entity.hasStatusEffect(ModStatusEffects.PHASE)) { // Replace StatusEffects.SPEED with your custom status effect
			ci.cancel(); // Cancel the rendering of armor
		}
	}


}
