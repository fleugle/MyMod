package nikita.uniquescythe.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


//-----------------------------------------------------------
// _____________________WORKS_________________________
//-----------------------------------------------------------
@Mixin(ClientPlayerEntity.class)
public class MovementPreventionMixin {

	@Inject(method = "tickMovement", at = @At("HEAD"), cancellable = true)
	private void onTickMovement(CallbackInfo ci) {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;

		assert player != null;
		if (player.hasStatusEffect(ModStatusEffects.PHASE)) { // Replace with your custom status effect
			// Prevent all movement
			player.input.forwardMovement = 0;
			player.input.sidewaysMovement = 0;
			player.input.jumping = false;
			ci.cancel();//needed part to make it work for some reason.
		}
	}

	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	private void onTick(CallbackInfo ci) {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;

		assert player != null;
		if (player.hasStatusEffect(ModStatusEffects.PHASE)) { // Replace with your custom status effect
			// Prevent jumping
			player.input.jumping = false;
			ci.cancel();
		}
	}

}
