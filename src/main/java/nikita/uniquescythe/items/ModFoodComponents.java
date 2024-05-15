package nikita.uniquescythe.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import nikita.uniquescythe.status_effects.ModStatusEffects;

public class ModFoodComponents {

	public static final FoodComponent LOW_HONEY_APPLE = new FoodComponent.Builder()
		.hunger(5)
		.saturationModifier(1.2F)
		.statusEffect(new StatusEffectInstance(ModStatusEffects.DELAYED_SATISFACTION, 200, 1), 1.0F)
		.alwaysEdible()
		.build();

	public static final FoodComponent MID_HONEY_APPLE = new FoodComponent.Builder()
		.hunger(5)
		.saturationModifier(1.2F)
		.statusEffect(new StatusEffectInstance(ModStatusEffects.DELAYED_SATISFACTION, 600, 2), 1.0F)
		.alwaysEdible()
		.build();

	public static final FoodComponent HIGH_HONEY_APPLE = new FoodComponent.Builder()
		.hunger(5)
		.saturationModifier(1.2F)
		.statusEffect(new StatusEffectInstance(ModStatusEffects.DELAYED_SATISFACTION, 1200, 4), 1.0F)
		.alwaysEdible()
		.build();

	private static FoodComponent.Builder create(int hunger) {
		return new FoodComponent.Builder().hunger(hunger).saturationModifier(0.6F);
	}
}
