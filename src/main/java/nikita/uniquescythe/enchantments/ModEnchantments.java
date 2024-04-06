package nikita.uniquescythe.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.enchantments.custom.WindBurstEnchantment;

public class ModEnchantments {



	static Enchantment registerEnchantment(String name, Enchantment enchantment){
		return Registry.register(Registries.ENCHANTMENT, new Identifier(UniqueScythe.MOD_ID, name), enchantment);
	}

	public static void initialiseModEnchantments(){
		UniqueScythe.LOGGER.info("Registering " + UniqueScythe.MOD_ID + " Wind burst enchantment");


		//here I need to put all of my enchantments as variables.
		//don't blame me, I've invented this myself, it can be not elegant at all though.
		final Enchantment WIND_BURST = registerEnchantment("wind_burst", new WindBurstEnchantment());
	}
}
