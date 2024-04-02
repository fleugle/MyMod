package nikita.uniquescythe.mixin;

import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import nikita.uniquescythe.items.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

public class LetThemEnchantMace extends EnchantmentTargetMixin{
	@Override
	public boolean isAcceptableItem(Item item) {
		return item == ModItems.MACE;
	}
}


@Mixin(EnchantmentTarget.class)
abstract class EnchantmentTargetMixin {
	@Shadow
	abstract boolean isAcceptableItem(Item item);
}
