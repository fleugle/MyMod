package nikita.uniquescythe.items.custom;


import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nikita.uniquescythe.custom.WindExplosion;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.sounds.ModSounds;



public class MaceItem
	extends Item {
	private static final int ATTACK_DAMAGE_MODIFIER_VALUE = 6;
	private static final float ATTACK_SPEED_MODIFIER_VALUE = -2.4f;
	public static final float MINING_SPEED_MULTIPLIER = 1.5f;
	private final ToolMaterial material;

	private final float attackDamage;
	private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

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
		this.attributeModifiers = builder.build();
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


	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
		if (attacker instanceof ServerPlayerEntity) {
			if (attacker.getMainHandStack().getItem() == this){
				ServerWorld serverWorld = (ServerWorld)attacker.getWorld();
				ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)attacker;
				float soundVol = (attacker.fallDistance) / 10;



				if (serverPlayerEntity.fallDistance > 1.5f) {


					int additionalDamage = ((int) attacker.fallDistance) * 5;

					// Apply additional damage
					target.getWorld().playSound(null, target.getBlockPos(), ModSounds.MACE_BONK, SoundCategory.NEUTRAL, soundVol, 1f);
					target.damage(attacker.getDamageSources().generic(), additionalDamage);
					// Reset fall distance to prevent fall damage
					attacker.fallDistance = 0;


					//enchantment func
					World world = attacker.getWorld();
					if (world instanceof ServerWorld) {

						// Spawn smoke particles in a radius of 2 blocks
						serverWorld.spawnParticles(ParticleTypes.EXPLOSION,
							attacker.getPos().getX()  + 0.5,
							attacker.getPos().getY()  + 0.5,
							attacker.getPos().getZ()  + 0.5,
							30, soundVol*2, soundVol*2, soundVol*2, 0.1);

						serverWorld.spawnParticles(ParticleTypes.SMOKE,
							attacker.getPos().getX()  + 0.5,
							attacker.getPos().getY()  + 0.5,
							attacker.getPos().getZ()  + 0.5,
							30, soundVol*2, soundVol*2, soundVol*2, 0.1);
					}



					attacker.getWorld().sendEntityStatus(attacker, (byte) 3);
					WindExplosion explosion = new WindExplosion(attacker.getWorld(), null, attacker.getPos().getX(), attacker.getPos().getY() - 1, attacker.getPos().getZ(), soundVol);
					explosion.collectBlocksAndDamageEntities();

					DamageSource damageSource = attacker.getRecentDamageSource();
					if (soundVol > 2){
						soundVol = 2;
					}

					attacker.setVelocity(0, 0f,0);//reset velocity
					attacker.addVelocity(0, soundVol + 0.6f ,0);//boost to the sky
					attacker.damage(damageSource, 0.00001f);

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

}

