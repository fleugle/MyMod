package nikita.uniquescythe.potions;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.status_effects.ModStatusEffects;

public class ModPotions {

	public static final Potion JUSTICE_POTION = registerPotions("justice_potion", ModStatusEffects.JUSTICE_VENGEANCE, 600, 0);
	public static final Potion CHAOS_POTION = registerPotions("chaos_potion", ModStatusEffects.CHAOS, 600, 0);

	public static Potion registerPotions(String id, StatusEffect statusEffect, int duration, int amplifier){
		return Registry.register(Registries.POTION, new Identifier(UniqueScythe.MOD_ID, id),
			new Potion(new StatusEffectInstance(statusEffect, duration, amplifier, false,true,true)));
	}

	public static void registerPotionsRecipes(){
		BrewingRecipeRegistry.registerPotionRecipe(Potions.MUNDANE, ModItems.JUSTICE_SHARD, ModPotions.JUSTICE_POTION);
		BrewingRecipeRegistry.registerPotionRecipe(Potions.MUNDANE, ModItems.CHAOS_SHARD, ModPotions.CHAOS_POTION);
	}
}
