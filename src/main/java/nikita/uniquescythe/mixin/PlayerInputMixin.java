package nikita.uniquescythe.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBind;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class PlayerInputMixin {

	@Shadow
	@Nullable
	public ClientPlayerEntity player;

	@Inject(method = "handleInputEvents", at = @At("HEAD"), cancellable = true)
	private void onHandleInputEvents(CallbackInfo ci) {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;

		//KeyBind useKey = MinecraftClient.getInstance().options.useKey;
		KeyBind attackKey = MinecraftClient.getInstance().options.attackKey;

		/*KeyBind forwardKey = MinecraftClient.getInstance().options.forwardKey;
		KeyBind backKey = MinecraftClient.getInstance().options.backKey;
		KeyBind rightKey = MinecraftClient.getInstance().options.rightKey;
		KeyBind leftKey = MinecraftClient.getInstance().options.leftKey;*/



		if (player != null && player.hasStatusEffect(ModStatusEffects.PHASE)) {
			/*if (useKey.wasPressed() || useKey.isPressed()){
				ci.cancel();
			}*/

			if (attackKey.wasPressed() || attackKey.isPressed()){
				ci.cancel();
			}

			/*if (forwardKey.wasPressed() || forwardKey.isPressed()
				|| backKey.wasPressed() || backKey.isPressed()){
				player.input.forwardMovement = 0;
				ci.cancel();
			}

			if (rightKey.wasPressed() || rightKey.isPressed()
				|| leftKey.wasPressed() || leftKey.isPressed()){
				player.input.sidewaysMovement = 0;
				ci.cancel();
			}*/

		}


	}
}
