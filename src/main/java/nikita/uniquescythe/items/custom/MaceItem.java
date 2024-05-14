package nikita.uniquescythe.items.custom;


import com.google.common.collect.ImmutableMultimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nikita.uniquescythe.enchantments.ModEnchantments;
import nikita.uniquescythe.sounds.ModSoundEvents;


public class MaceItem
	extends Item {
	private final ToolMaterial material;

	private final float attackDamage;


	public MaceItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
		super(settings.maxDamageIfAbsent(material.getDurability()));
		this.material = material;
		this.attackDamage = (float)attackDamage + material.getAttackDamage();
		ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(
			EntityAttributes.GENERIC_ATTACK_DAMAGE,
			new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", (double)this.attackDamage, EntityAttributeModifier.Operation.ADDITION)
		);
		builder.put(
			EntityAttributes.GENERIC_ATTACK_SPEED,
			new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", (double)attackSpeed, EntityAttributeModifier.Operation.ADDITION)
		);
	}


	public ToolMaterial getMaterial() {
		return this.material;
	}

	@Override
	public int getEnchantability() {
		return this.material.getEnchantability();
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient) {
		return this.material.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
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
		return true;
	}


	public static void applyAdditionalDamage(ItemStack itemStack, Entity target, LivingEntity attacker){
		int ifHasDensityEnchantment = EnchantmentHelper.getLevel(ModEnchantments.DENSITY, itemStack);

		int damageMultiplier;//var that I pass in the end as a multiplier for dmg

		if (ifHasDensityEnchantment >= 1){
			damageMultiplier = 15;
		} else {
			damageMultiplier = 5;
		}

		int additionalDamage = ((int) attacker.fallDistance) * damageMultiplier;

		// Apply additional damage
		target.getWorld().playSound(null, target.getBlockPos(), ModSoundEvents.MACE_BONK, SoundCategory.NEUTRAL, 1f, 1f);
		target.damage(attacker.getDamageSources().generic(), additionalDamage);
		// Reset fall distance to prevent fall damage
		attacker.fallDistance = 0;
	}



	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
		if (attacker instanceof ServerPlayerEntity) {
			if (attacker.getMainHandStack().getItem() == this){
				ServerWorld serverWorld = (ServerWorld)attacker.getWorld();
				ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)attacker;
				float soundVol = (attacker.fallDistance) / 10;

				if (serverPlayerEntity.fallDistance > 1.5f) {

					applyAdditionalDamage(stack, target, attacker);//func to apply additional dmg

				}

			}

		}
		return super.postHit(stack, target, attacker);
	}


	@Override
	public boolean isSuitableFor(BlockState state) {
		return state.isOf(Blocks.COBWEB);
	}

}

