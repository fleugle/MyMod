package nikita.uniquescythe.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBind;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class PlayerInputMixin {

	@Inject(method = "handleInputEvents", at = @At("HEAD"), cancellable = true)
	private void onHandleInputEvents(CallbackInfo info) {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;


		if (player != null && player.hasStatusEffect(ModStatusEffects.PHASE)) {
			info.cancel(); // This cancels the input events, effectively disabling player controls
		}


	}
}
