package nikita.uniquescythe.mixin;


import com.mojang.authlib.GameProfile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityAnimationS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nikita.uniquescythe.items.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {


	public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
		super(world, pos, yaw, gameProfile);
	}

	@Shadow
	public ServerWorld getServerWorld(){
		return (ServerWorld)this.getWorld();
	};


	@Inject(method = "addCritParticles", at = @At("HEAD"), cancellable = true)
	public void sendCustomParticlesPacket(Entity target, CallbackInfo ci){

		PlayerEntity player = this;

		if (player.getStackInHand(Hand.MAIN_HAND).getItem().equals(ModItems.FROSTY_SCYTHE)) {
			ci.cancel();
			this.getServerWorld().getChunkManager().sendToNearbyPlayers(this, new EntityAnimationS2CPacket(target, 425661));

		} else if (player.getStackInHand(Hand.MAIN_HAND).getItem().equals(ModItems.WANDERERS_SWORD)) {
			ci.cancel();
			this.getServerWorld().getChunkManager().sendToNearbyPlayers(this, new EntityAnimationS2CPacket(target, 613140));

		}
		//425661 id for frosty scythe, 613140 for wanderers sword (6 digits)
	}

	@Inject(method = "addEnchantedHitParticles", at = @At("HEAD"), cancellable = true)
	public void sendCustomEnchantedParticlesPacket(Entity target, CallbackInfo ci){

		PlayerEntity player = this;

		if (player.getStackInHand(Hand.MAIN_HAND).getItem().equals(ModItems.FROSTY_SCYTHE)) {
			this.getServerWorld().getChunkManager().sendToNearbyPlayers(this, new EntityAnimationS2CPacket(target, 425661));
			ci.cancel();
		} else if (player.getStackInHand(Hand.MAIN_HAND).getItem().equals(ModItems.WANDERERS_SWORD)) {
			this.getServerWorld().getChunkManager().sendToNearbyPlayers(this, new EntityAnimationS2CPacket(target, 613140));
			ci.cancel();
		}
		//425661 id for frosty scythe, 613140 for wanderers sword (6 digits)
	}

}
