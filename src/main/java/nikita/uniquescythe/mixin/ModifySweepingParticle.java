package nikita.uniquescythe.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.stat.Stat;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.networking.FrostyScytheClientHandler;
import nikita.uniquescythe.networking.FrostyScytheTracker;
import nikita.uniquescythe.particles.ModParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@SuppressWarnings("UnreachableCode")
@Mixin(PlayerEntity.class)
public abstract class ModifySweepingParticle extends LivingEntity {

	@Shadow
	protected abstract void takeShieldHit(LivingEntity attacker);

	@Shadow
	public abstract void increaseStat(Stat<?> stat, int amount);

	protected ModifySweepingParticle(EntityType<?> entityType_1, World world){
		super((EntityType<? extends LivingEntity>) entityType_1,world);
	}

	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/server/world/ServerWorld;spawnParticles(Lnet/minecraft/particle/ParticleEffect;DDDIDDDD)I",
			ordinal = 0
		),
		method = "spawnSweepAttackParticles"
	)
	private <T extends ParticleEffect> T changeSweepAttackParticle(T particle) {//idk how to name it properly for now

		//previously was getting a client
		LivingEntity livingEntity = this;

		//i guess this if statements do the work here. to be experimented with in advance
		if(livingEntity instanceof PlayerEntity player){
			if (player.getMainHandStack().isOf(ModItems.FROSTY_SCYTHE) && FrostyScytheClientHandler.isPlayerHoldingFrostyScythe(player.getUuid())) return (T) ModParticleTypes.FROSTY_SWEEP_ATTACK;
			if (player.getMainHandStack().isOf(ModItems.WANDERERS_SWORD)) return (T) ModParticleTypes.VOID_SWEEP_ATTACK;
		}
		return particle;
	}
}
