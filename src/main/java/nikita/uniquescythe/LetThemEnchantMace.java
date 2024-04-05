package nikita.uniquescythe;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.items.custom.MaceItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

public class LetThemEnchantMace extends EnchantmentTargetMixin{
	@Override
	public boolean isAcceptableItem(Item item) {
		return item instanceof MaceItem;
	}
}


@Mixin(EnchantmentTarget.class)
abstract class EnchantmentTargetMixin {
	@Shadow
	abstract boolean isAcceptableItem(Item item);
}

//I guess just needs to be out of the mixin package while being mixin, idk why
