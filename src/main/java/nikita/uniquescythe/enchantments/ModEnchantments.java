package nikita.uniquescythe.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.enchantments.custom.DensityEnchantment;
import nikita.uniquescythe.enchantments.custom.WindBurstEnchantment;

public class ModEnchantments {


	public static final Enchantment WIND_BURST = new WindBurstEnchantment();

	public static final Enchantment DENSITY = new DensityEnchantment();

	static Enchantment registerEnchantment(String name, Enchantment enchantment){
		return Registry.register(Registries.ENCHANTMENT, new Identifier(UniqueScythe.MOD_ID, name), enchantment);
	}

	public static void initialiseModEnchantments(){
		UniqueScythe.LOGGER.info("Registering " + UniqueScythe.MOD_ID + " Wind burst enchantment");



		registerEnchantment("wind_burst", WIND_BURST);
		registerEnchantment("density", DENSITY);
	}
}
