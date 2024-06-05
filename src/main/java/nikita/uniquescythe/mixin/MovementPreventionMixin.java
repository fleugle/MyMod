package nikita.uniquescythe.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBind;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import nikita.uniquescythe.utility.CommandsExecuter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


//-----------------------------------------------------------
// _____________________WORKS_________________________
//-----------------------------------------------------------



@Mixin(ClientPlayerEntity.class)
public class MovementPreventionMixin {

	@Inject(method = "tickMovement", at = @At("HEAD"), cancellable = true)
	private void preventJumpingInPhase(CallbackInfo ci) {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		assert player != null;
		String playerName = player.getDisplayName().getString();
		World world = player.getWorld();




		KeyBind jumpKey = MinecraftClient.getInstance().options.jumpKey;

		KeyBind forwardKey = MinecraftClient.getInstance().options.forwardKey;
		KeyBind backKey = MinecraftClient.getInstance().options.backKey;
		KeyBind rightKey = MinecraftClient.getInstance().options.rightKey;
		KeyBind leftKey = MinecraftClient.getInstance().options.leftKey;



		assert player != null;
		if (player.hasStatusEffect(ModStatusEffects.PHASE)) {// Replace with your custom status effect
			// Prevent all movement

				ci.cancel();


			//needed part to make it work for some reason.
		}
	}

	/*@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	private void onTick(CallbackInfo ci) {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;

		assert player != null;
		if (player.hasStatusEffect(ModStatusEffects.PHASE)) { // Replace with your custom status effect
			// Prevent jumping
			player.input.jumping = false;
			ci.cancel();
		}
	}*/

}
