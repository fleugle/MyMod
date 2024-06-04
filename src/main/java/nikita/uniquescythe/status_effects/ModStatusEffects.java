package nikita.uniquescythe.status_effects;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.status_effects.custom.ChaosStatusEffect;
import nikita.uniquescythe.status_effects.custom.DelayedSatisfactionStatusEffect;
import nikita.uniquescythe.status_effects.custom.JusticeVengeanceStatusEffect;
import nikita.uniquescythe.status_effects.custom.PhaseStatusEffect;


public class ModStatusEffects {


	public static final StatusEffect DELAYED_SATISFACTION = new DelayedSatisfactionStatusEffect();

	public static final StatusEffect JUSTICE_VENGEANCE	 = new JusticeVengeanceStatusEffect();

	public static final StatusEffect CHAOS = new ChaosStatusEffect();

	public static final StatusEffect PHASE = new PhaseStatusEffect()/*.addAttributeModifier(
		EntityAttributes.GENERIC_MOVEMENT_SPEED,
		"7107DE5E-7CE8-4030-940E-514C1F160890",
		-9999F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL
	)*/;





	private static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect){
		return Registry.register(Registries.STATUS_EFFECT, new Identifier(UniqueScythe.MOD_ID, name), statusEffect);
	}

	public static void initialiseStatusEffects(){
		registerStatusEffect("delayed_satisfaction", DELAYED_SATISFACTION);
		registerStatusEffect("justice_vengeance", JUSTICE_VENGEANCE);
		registerStatusEffect("chaos", CHAOS);
		registerStatusEffect("phase", PHASE);


	}
}
