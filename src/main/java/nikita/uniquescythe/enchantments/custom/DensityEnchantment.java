package nikita.uniquescythe.enchantments.custom;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import nikita.uniquescythe.items.custom.MaceItem;

public class DensityEnchantment extends Enchantment {

	public DensityEnchantment() {
		super(Rarity.UNCOMMON,
			ClassTinkerers.getEnum(EnchantmentTarget.class, "MACE")
			,new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}


	@Override
	public int getMinPower(int level) {
		return 1;
	}


	@Override
	public int getMaxLevel() {
		return 1;
	}


	@Override
	public void onTargetDamaged(LivingEntity attacker, Entity target, int level) {

		ItemStack stack = attacker.getMainHandStack();
		MaceItem.applyAdditionalDamage(stack,target,attacker);
		super.onTargetDamaged(attacker, target, level);
	}
}
