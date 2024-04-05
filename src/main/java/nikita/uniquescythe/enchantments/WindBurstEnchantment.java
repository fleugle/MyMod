package nikita.uniquescythe.enchantments;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class WindBurstEnchantment extends Enchantment {

	protected WindBurstEnchantment() {
		super(Rarity.UNCOMMON,
			ClassTinkerers.getEnum(EnchantmentTarget.class, "MACE")
			,new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}
}
