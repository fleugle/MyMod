package nikita.uniquescythe.items.custom;


import net.minecraft.item.*;
import net.minecraft.util.ActionResult;


public class WardenersSwordItem extends AxeItem {
	public WardenersSwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		return ActionResult.FAIL;
	}


}
