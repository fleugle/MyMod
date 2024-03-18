package nikita.uniquescythe.items.custom;

import com.google.common.collect.BiMap;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

import java.util.Optional;


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
		if (attacker.fallDistance > 5) {
			// Calculate additional damage based on fall distance

		}


		return super.postHit(stack, target, attacker);
	}
}


