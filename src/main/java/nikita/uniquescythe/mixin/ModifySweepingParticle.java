package nikita.uniquescythe.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@SuppressWarnings("UnreachableCode")
@Mixin(PlayerEntity.class)
public abstract class ModifySweepingParticle {


	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/server/world/ServerWorld;spawnParticles(Lnet/minecraft/particle/ParticleEffect;DDDIDDDD)I",
			ordinal = 0
		),
		method = "spawnSweepAttackParticles"
	)
	private <T extends ParticleEffect> T changeSweepAttackParticle(T particle) {//idk how to name it properly for now
		PlayerEntity player = (PlayerEntity)(Object)this;
		//i guess this if statements do the work here. to be experimented with in advance
		if (player.getMainHandStack().isOf(ModItems.FROSTY_SCYTHE)) return (T) ModParticleTypes.FROSTY_SWEEP_ATTACK;
		if (player.getMainHandStack().isOf(ModItems.WANDERERS_SWORD)) return (T) ModParticleTypes.VOID_SWEEP_ATTACK;
		return particle;
	}
}
