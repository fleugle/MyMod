package nikita.uniquescythe.mixin;



import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

//@Mixin(ClientPlayerEntity.class)//change		// <-- Here I just specify the class

@Mixin(ClientPlayerEntity.class)//change		// <-- Here I just specify the class
public abstract class ModifyCritParticle extends AbstractClientPlayerEntity {


	public ModifyCritParticle(ClientWorld world, GameProfile profile) {
		super(world, profile);
	}

	//target is a thing that we target, and index is an argument we are changing.
	@ModifyArg(method = "addCritParticles",
		at = @At(value = "INVOKE",
			target = "Lnet/minecraft/client/particle/ParticleManager;addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;)V"),
		index = 1)
	private <T extends ParticleEffect> T changeCritAttackParticle(T particle) {//idk how to name it properly for now
		PlayerEntity player = (PlayerEntity)(Object)this;

		//System.out.println("Changing crit particle to VOID_CRIT"); // Logging for debugging

		UniqueScythe.LOGGER.info("Changing crit particle ");
		if (player.getMainHandStack().isOf(ModItems.WANDERERS_SWORD)) return (T) ModParticleTypes.VOID_CRIT;


		return particle;
	}

	//for some reason just doesn't work
	//I need help with that.
}
