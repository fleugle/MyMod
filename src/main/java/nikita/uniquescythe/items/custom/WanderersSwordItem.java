package nikita.uniquescythe.items.custom;



import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;



public class WanderersSwordItem extends AxeItem {
	public static int cooldown;
	public WanderersSwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		return ActionResult.FAIL;
	}

	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 80, 1));
		target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));



		return super.postHit(stack, target, attacker);
	}

	//to do something if right-clicked too
}
