package nikita.uniquescythe.items.custom;


import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nikita.uniquescythe.custom.WindExplosion;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.sounds.ModSounds;

/*

public class MaceItem extends AxeItem implements FabricItem {

	public MaceItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
		// Register the AttackEntityCallback
		AttackEntityCallback.EVENT.register((player, world, hand, entity, entityHitResult) -> {
			if (player.getMainHandStack().getItem() == this) {
				// Check if the player is holding this MaceItem
				if (player.fallDistance > 0) {
					// Calculate additional damage based on fall distance
					int additionalDamage = ((int) player.fallDistance) * 5;
					float soundVol = (player.fallDistance) / 10;
					// Apply additional damage
					entity.getWorld().playSound(null, entity.getBlockPos(), ModSounds.MACE_BONK, SoundCategory.NEUTRAL, soundVol, 1f);
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

	/*
	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		// sound
		attacker.getWorld().playSound(null, attacker.getBlockPos(), ModSounds.MACE_BONK, SoundCategory.PLAYERS, 7f, 1f);



		return super.postHit(stack, target, attacker);
	}


}

*/


public class MaceItem
	extends Item {
	private static final int ATTACK_DAMAGE_MODIFIER_VALUE = 6;
	private static final float ATTACK_SPEED_MODIFIER_VALUE = -2.4f;
	public static final float MINING_SPEED_MULTIPLIER = 1.5f;

	public MaceItem(Item.Settings settings) {
		super(settings);
	}

	@Override
	public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
		return !miner.isCreative();
	}

	@Override
	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
		return state.isOf(Blocks.COBWEB) ? 15.0f : 1.5f;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}


	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (attacker instanceof ServerPlayerEntity) {
			if (attacker.getMainHandStack().getItem() == this){
				ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)attacker;
				if (serverPlayerEntity.fallDistance > 1.5f) {
					ServerWorld serverWorld = (ServerWorld)attacker.getWorld();

					int additionalDamage = ((int) attacker.fallDistance) * 5;
					float soundVol = (attacker.fallDistance) / 10;
					// Apply additional damage
					target.getWorld().playSound(null, target.getBlockPos(), ModSounds.MACE_BONK, SoundCategory.NEUTRAL, soundVol, 1f);
					target.damage(attacker.getDamageSources().generic(), additionalDamage);
					// Reset fall distance to prevent fall damage
					attacker.fallDistance = 0;



					World world = attacker.getWorld();
					if (world instanceof ServerWorld) {

							// Spawn smoke particles in a radius of 2 blocks
							serverWorld.spawnParticles(ParticleTypes.EXPLOSION,
								target.getPos().getX()  + 0.5,
								target.getPos().getY()  + 0.5,
								target.getPos().getZ()  + 0.5,
								30, soundVol*2, soundVol*2, soundVol*2, 0.1);

							serverWorld.spawnParticles(ParticleTypes.SMOKE,
								target.getPos().getX()  + 0.5,
								target.getPos().getY()  + 0.5,
								target.getPos().getZ()  + 0.5,
								30, soundVol*2, soundVol*2, soundVol*2, 0.1);
					}
					float explosionSize = soundVol;
					attacker.getWorld().sendEntityStatus(attacker, (byte) 3);
					WindExplosion explosion = new WindExplosion(target.getWorld(), null, target.getPos().getX(), target.getPos().getY(), target.getPos().getZ(), explosionSize);
					explosion.collectBlocksAndDamageEntities();

					//sound on block collision
					attacker.getWorld().playSound(
						null,
						attacker.getPos().getX(),
						attacker.getPos().getY(),
						attacker.getPos().getZ(),
						ModSounds.WIND_CHARGE_BURST,
						SoundCategory.NEUTRAL,
						1F,
						0.4F / (attacker.getWorld().getRandom().nextFloat() * 0.4F + 0.8F)
					);


				}
			}




		}
		return super.postHit(stack, target, attacker);
	}


	@Override
	public boolean isSuitableFor(BlockState state) {
		return state.isOf(Blocks.COBWEB);
	}


	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient) {
		return ingredient.isOf(ModItems.BREEZE_ROD);
	}

}

