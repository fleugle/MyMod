package nikita.uniquescythe.custom;

import net.minecraft.item.Item;
import nikita.uniquescythe.items.custom.MaceItem;
import nikita.uniquescythe.mixin.CustomEnchantable;

public class MaceEnchantmentTarget implements CustomEnchantable {
	@Override
	public boolean isAcceptableItem(Item item) {
			return item instanceof MaceItem;
	}

}
