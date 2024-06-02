package nikita.uniquescythe.utility;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Random;

public class RandomEffectUtil {
	public static void applyRandomEffect(ServerPlayerEntity player) {
		// Get all registered status effects
		StatusEffectInstance[] effects = Registries.STATUS_EFFECT.stream()
			.map(effect -> new StatusEffectInstance(effect, 500))
			.toArray(StatusEffectInstance[]::new);

		// Randomly select an effect
		Random random = new Random();
		StatusEffectInstance effectToApply = effects[random.nextInt(effects.length)];

		// Apply the effect to the player
		player.addStatusEffect(effectToApply);
	}
}
