package nikita.uniquescythe.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class WindBurstEnchantment extends Enchantment {

	protected WindBurstEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON,new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}
}
