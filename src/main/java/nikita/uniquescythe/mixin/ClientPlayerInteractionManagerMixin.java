package nikita.uniquescythe.mixin;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

	@Inject(method = "attackBlock", at = @At("HEAD"), cancellable = true)
	private void onAttackBlock(CallbackInfoReturnable<Boolean> cir) {
		MinecraftClient client = MinecraftClient.getInstance();
		ClientPlayerEntity player = client.player;

		if (player != null && player.hasStatusEffect(ModStatusEffects.PHASE)) {
			cir.setReturnValue(false); // Cancel block attack
		}
	}


	@Inject(method = "attackEntity", at = @At("HEAD"), cancellable = true)
	private void onAttackEntity(CallbackInfo ci){
		MinecraftClient client = MinecraftClient.getInstance();
		ClientPlayerEntity player = client.player;

		if (player != null && player.hasStatusEffect(ModStatusEffects.PHASE)) {
			ci.cancel(); // Cancel entity attack
		}
	}


	@Inject(method = "interactItem", at = @At("HEAD"), cancellable = true)
	private void onInteractItem(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
		MinecraftClient client = MinecraftClient.getInstance();


		if (player != null && player.hasStatusEffect(ModStatusEffects.PHASE)) {
			cir.setReturnValue(ActionResult.FAIL); // Cancel entity attack
		}
	}

	@Inject(method = "interactBlock", at = @At("HEAD"), cancellable = true)
	private void onInteractBlock(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable<ActionResult> cir){
		MinecraftClient client = MinecraftClient.getInstance();


		if (player != null && player.hasStatusEffect(ModStatusEffects.PHASE)) {
			cir.setReturnValue(ActionResult.FAIL); // Cancel entity attack
		}
	}



}
