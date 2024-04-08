package nikita.uniquescythe.enchantments.custom;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import nikita.uniquescythe.custom.WindExplosion;
import nikita.uniquescythe.enchantments.ModEnchantments;
import nikita.uniquescythe.particles.ModParticleTypes;
import nikita.uniquescythe.sounds.ModSounds;

public class WindBurstEnchantment extends Enchantment {

	//attempting to be cool here and code something without help and given by some-one else methods





	//to be honest, I don't believe that's gonna work. Smthing like 'no dmg multiplier' 'cause idk if item stack is received properly here(if received at all of coarse🤠👍).



	public WindBurstEnchantment() {
		super(Rarity.UNCOMMON,
			ClassTinkerers.getEnum(EnchantmentTarget.class, "MACE")
			,new EquipmentSlot[] {EquipmentSlot.MAINHAND});
	}


	@Override
	public int getMinPower(int level) {
		return 1;
	}


	@Override
	public int getMaxLevel() {
		return 1;
	}


	@Override
	public void onTargetDamaged(LivingEntity attacker, Entity target, int level) {

		ItemStack itemStack = attacker.getMainHandStack();
		int ifHasDensityEnchantment = EnchantmentHelper.getLevel(ModEnchantments.DENSITY, itemStack);

		int damageMultiplier;//var that I pass in the end as a multiplier for dmg

		if (ifHasDensityEnchantment >= 1){
			damageMultiplier = 15;
		} else {
			damageMultiplier = 5;
		}




		ServerWorld serverWorld = (ServerWorld)attacker.getWorld();
		ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)attacker;
		float soundVol = (attacker.fallDistance) / 10;
		//enchantment func
		World world = attacker.getWorld();





			int additionalDamage = ((int) attacker.fallDistance) * damageMultiplier;

			// Apply additional damage
			target.getWorld().playSound(null, target.getBlockPos(), ModSounds.MACE_BONK, SoundCategory.NEUTRAL, soundVol, 1f);
			target.damage(attacker.getDamageSources().generic(), additionalDamage);
			// Reset fall distance to prevent fall damage
			attacker.fallDistance = 0;

			if (world instanceof ServerWorld) {


					// Spawn smoke particles in a radius of 2 blocks
				serverWorld.spawnParticles(ModParticleTypes.WIND_EXPLOSION,
					attacker.getPos().getX()  + 0.5,
					attacker.getPos().getY()  + 0.5,
					attacker.getPos().getZ()  + 0.5,
					8, 1.5, 1.5, 1.5, 1);


				serverWorld.spawnParticles(ParticleTypes.SMOKE,
					attacker.getPos().getX()  + 0.5,
					attacker.getPos().getY()  + 0.5,
					attacker.getPos().getZ()  + 0.5,
					8, 1.5, 1.5, 1.5, 1);
			}




			((LivingEntity) attacker).removeStatusEffect(StatusEffects.WITHER);// 'cause without it, it can be laggy and super inconsistent
			attacker.getWorld().sendEntityStatus(attacker, (byte) 3);
			DamageSource damageSource = attacker.getDamageSources().generic();
			attacker.setVelocity(0, 0f,0);//reset velocity
			attacker.damage(damageSource, 0.00001f);
			attacker.addVelocity(0, 1.5f ,0);//boost to the sky
			attacker.damage(damageSource, 0.00001f);

			//sound
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


		super.onTargetDamaged(attacker, target, level);
	}
}
