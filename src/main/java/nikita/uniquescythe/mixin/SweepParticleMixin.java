package nikita.uniquescythe.mixin;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.items.particles.ModParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PlayerEntity.class)
public abstract class SweepParticleMixin {
	@ModifyArg(
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/server/world/ServerWorld;spawnParticles(Lnet/minecraft/particle/ParticleEffect;DDDIDDDD)I",
			ordinal = 0
		),
		method = "spawnSweepAttackParticles"
	)
	private <T extends ParticleEffect> T changeUnpoweredSlotTexture(T particle) {
		PlayerEntity player = (PlayerEntity)(Object)this;
		if (player.getMainHandStack().isOf(ModItems.WARDENERS_SWORD)) return ModParticles.;

		return particle;
	}
}
