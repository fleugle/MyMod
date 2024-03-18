package nikita.uniquescythe.items.custom;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;


public class MaceItem extends SwordItem implements FabricItem {

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
}


