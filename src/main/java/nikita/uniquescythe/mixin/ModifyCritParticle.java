package nikita.uniquescythe.mixin;



import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

//@Mixin(ClientPlayerEntity.class)//change		// <-- Here I just specify the class

@Mixin(ClientPlayNetworkHandler.class)//change		// <-- Here I just specify the class
public abstract class ModifyCritParticle {



	//target is a thing that we target, and index is an argument we are changing.
	@ModifyArg(method = "onEntityAnimation",
		at = @At(value = "INVOKE",
			target = "Lnet/minecraft/client/particle/ParticleManager;addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;)V"),
		index = 1)
	private <T extends ParticleEffect> T changeCritAttackParticle(T particle) {//idk how to name it properly for now
		PlayerEntity player = MinecraftClient.getInstance().player;

		if (player != null){
			ItemStack mainHandItem = player.getMainHandStack();

			UniqueScythe.LOGGER.info("Changing crit particle ");
			if (mainHandItem.isOf(ModItems.WANDERERS_SWORD)) return (T) ModParticleTypes.VOID_CRIT;
			if (mainHandItem.isOf(ModItems.FROSTY_SCYTHE)) return (T) ModParticleTypes.FROSTY_CRIT;
		}




		return particle;


	}
}

