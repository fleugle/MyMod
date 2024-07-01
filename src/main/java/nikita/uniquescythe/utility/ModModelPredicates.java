package nikita.uniquescythe.utility;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.items.custom.ExperiencePhylacteryItem;

public class ModModelPredicates {

	public static void registerModModelPredicates(){

		registerExperiencePhylactery();
		registerDuplicateMirror();


	}

	private static void registerExperiencePhylactery(){
		ModelPredicateProviderRegistry.register(ModItems.EXPERIENCE_PHYLACTERY, new Identifier("uniquescythe", "percentage"),
			(stack, world, entity, seed) -> {
				NbtCompound tag = stack.getOrCreateNbt();
				ExperiencePhylacteryItem experiencePhylacteryItem = (ExperiencePhylacteryItem) stack.getItem();
				float rawPercentage = (float) tag.getInt(SoulsSystem.SOULS) / experiencePhylacteryItem.getMaxCapacity();

				float finalValue = 0f;
				if (rawPercentage >= 0.001) finalValue = 0.1f;
				if (rawPercentage >= 0.2 ) finalValue = 0.2f;
				if (rawPercentage >= 0.3 ) finalValue = 0.3f;
				if (rawPercentage >= 0.4 ) finalValue = 0.4f;
				if (rawPercentage >= 0.5 ) finalValue = 0.5f;
				if (rawPercentage >= 0.6 ) finalValue = 0.6f;
				if (rawPercentage >= 0.7 ) finalValue = 0.7f;
				if (rawPercentage >= 0.8 ) finalValue = 0.8f;
				if (rawPercentage >= 0.9 ) finalValue = 0.9f;
				if (rawPercentage == 1) finalValue = 1f;

				return finalValue;
			});

	}

	private static void registerDuplicateMirror() {
		ModelPredicateProviderRegistry.register(ModItems.DUPLICATE_MIRROR, new Identifier("uniquescythe", "active"),
			(stack, world, entity, seed) -> {
				int durability = stack.getMaxDamage() - stack.getDamage();
				return durability > 1 ? 0.0f : 1.0f;
			});
	}
}
