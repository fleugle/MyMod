package nikita.uniquescythe.items.custom;


import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.item.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import nikita.uniquescythe.custom.WindExplosion;
import nikita.uniquescythe.sounds.ModSounds;


public class MaceItem extends AxeItem implements FabricItem {

	public MaceItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
		// Register the AttackEntityCallback
		AttackEntityCallback.EVENT.register((player, world, hand, entity, entityHitResult) -> {
			if (player.getMainHandStack().getItem() == this) {
				// Check if the player is holding this MaceItem
				if (player.fallDistance > 0) {
					// Calculate additional damage based on fall distance
					int additionalDamage = ((int) player.fallDistance) * 2;
					// Apply additional damage
					entity.damage(player.getDamageSources().generic(), additionalDamage);
					// Reset fall distance to prevent fall damage
					player.fallDistance = 0;
				}
			}
			return ActionResult.PASS;
		});
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		return ActionResult.FAIL;

	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		// sound
		attacker.getWorld().playSound(null, attacker.getBlockPos(), ModSounds.MACE_BONK, SoundCategory.PLAYERS, 7f, 1f);



		return super.postHit(stack, target, attacker);
	}
}


