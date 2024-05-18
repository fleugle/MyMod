package nikita.uniquescythe.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import nikita.uniquescythe.status_effects.ModStatusEffects;

public class ModFoodComponents {

	public static final FoodComponent HONEY_APPLE = new FoodComponent.Builder()
		.hunger(5)
		.saturationModifier(1.2F)
		.alwaysEdible()
		.build();


	private static FoodComponent.Builder create(int hunger) {
		return new FoodComponent.Builder().hunger(hunger).saturationModifier(0.6F);
	}
}
