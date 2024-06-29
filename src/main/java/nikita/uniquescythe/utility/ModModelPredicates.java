package nikita.uniquescythe.utility;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.items.custom.ExperiencePhylactery;

public class ModModelPredicates {

	public static void registerModModelPredicates(){
		registerExperiencePhylactery();
	}

	private static void registerExperiencePhylactery(){
		ModelPredicateProviderRegistry.register(ModItems.EXPERIENCE_PHYLACTERY, new Identifier("percentage"),
			(stack, world, entity, seed) -> {
				NbtCompound tag = stack.getOrCreateNbt();
				ExperiencePhylactery experiencePhylactery = (ExperiencePhylactery) stack.getItem();

				return (float) (tag.getInt(SoulsSystem.SOULS) / experiencePhylactery.getMaxCapacity());
			}
			);
	}
}
