package nikita.uniquescythe.mixin;



import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

//@Mixin(ClientPlayerEntity.class)//change
public abstract class ModifyCritParticle {

	/*
	@ModifyArg(method = "addCritParticles",
		at = @At(value = "INVOKE",
			target = "Lnet/minecraft/client/particle/ParticleManager;addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;)V"),
		index = 1)
	private <T extends ParticleEffect> T changeCritAttackParticle(T particle) {//idk how to name it properly for now
		PlayerEntity player = (PlayerEntity)(Object)this;

		if (player.getMainHandStack().isOf(ModItems.WANDERERS_SWORD)) return (T) ModParticleTypes.VOID_CRIT;


		return particle;
	}

	// I've learned how to do modify arg by myself, but it seems it is not what we are looking for
	// I mean the class and the mixin for it. It is working, but isn't changing the crit particle :(
	 */
}
