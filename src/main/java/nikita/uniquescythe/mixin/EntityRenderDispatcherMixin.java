package nikita.uniquescythe.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.WorldView;
import nikita.uniquescythe.datatracker.UltraInvisibilityClientHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {

	@Inject(method = "renderShadow", at = @At("HEAD"), cancellable = true)
	private static void onRenderShadow(
		MatrixStack matrices,
		VertexConsumerProvider vertexConsumers,
		Entity entity,
		float opacity,
		float tickDelta,
		WorldView world,
		float radius,
		CallbackInfo ci) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) entity;
			if (UltraInvisibilityClientHandler.isPlayerUltraInvisible(playerEntity.getUuid())) {
				ci.cancel();
			}
		}
	}
}
