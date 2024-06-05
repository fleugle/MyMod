package nikita.uniquescythe.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBind;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyBind.class)
public class KeyBindMixin {

	@Inject(method = "isPressed", at = @At("HEAD"), cancellable = true)
	private void MovementControlsSuppression(CallbackInfoReturnable<Boolean> cir) {
		MinecraftClient client = MinecraftClient.getInstance();
		ClientPlayerEntity player = client.player;

		if (player != null && player.hasStatusEffect(ModStatusEffects.PHASE)) { // Replace with your custom status effect
			KeyBind keyBind = (KeyBind) (Object) this;

			if (keyBind.equals(client.options.jumpKey) ) {

				cir.setReturnValue(false);
			}



			if (keyBind.equals(client.options.forwardKey)
				|| keyBind.equals(client.options.backKey)
				|| keyBind.equals(client.options.rightKey)
				|| keyBind.equals(client.options.leftKey)) {

				cir.setReturnValue(false);
			}
		}
	}

}
