package nikita.uniquescythe.status_effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.status_effects.custom.DelayedSatisfactionStatusEffect;

public class ModStatusEffects {


	public static final StatusEffect DELAYED_SATISFACTION = new DelayedSatisfactionStatusEffect();




	private static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect){
		return Registry.register(Registries.STATUS_EFFECT, new Identifier(UniqueScythe.MOD_ID, name), statusEffect);
	}

	public static void initialiseStatusEffects(){
		registerStatusEffect("delayed_satisfaction", DELAYED_SATISFACTION);
	}
}
