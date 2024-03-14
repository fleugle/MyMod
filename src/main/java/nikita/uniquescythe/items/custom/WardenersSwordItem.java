package nikita.uniquescythe.items.custom;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class WardenersSwordItem extends AxeItem {
	public static int cooldown;
	public WardenersSwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		return ActionResult.FAIL;
	}

	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 1));
		target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));



		return super.postHit(stack, target, attacker);
	}

	//to do something if right-clicked too
}
