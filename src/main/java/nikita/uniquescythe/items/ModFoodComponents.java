package nikita.uniquescythe.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {

	public static final FoodComponent HONEY_APPLE = new FoodComponent.Builder()
		.hunger(5)
		.saturationModifier(1.2F)
		.alwaysEdible()
		.build();

	public static final FoodComponent GOLDEN_BERRIES = new FoodComponent.Builder()
		.hunger(2)
		.saturationModifier(1.2F)
		.alwaysEdible()
		.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 50, 14, false, true, true), 1.0f)
		.statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 50, 4, false, true, true), 1.0f)
		.statusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 50, 4, false, true, true), 1.0f)
		.build();


	private static FoodComponent.Builder create(int hunger) {
		return new FoodComponent.Builder().hunger(hunger).saturationModifier(0.6F);
	}
}
