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

		KeyBind attackKey = MinecraftClient.getInstance().options.attackKey;
		KeyBind swapHandsKey = MinecraftClient.getInstance().options.swapHandsKey;



		if (player != null && player.hasStatusEffect(ModStatusEffects.PHASE)) {

			if (attackKey.isPressed() || swapHandsKey.isPressed() ){
				ci.cancel();
			}

		}


	}
}
