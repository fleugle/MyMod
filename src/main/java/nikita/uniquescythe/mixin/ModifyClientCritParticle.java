package nikita.uniquescythe.mixin;



import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

//@Mixin(ClientPlayerEntity.class)//change		// <-- Here I just specify the class

@Mixin(ClientPlayNetworkHandler.class)//change		// <-- Here I just specify the class
public abstract class ModifyClientCritParticle {



	//target is a thing that we target, and index is an argument we are changing.
	@ModifyArg(method = "onEntityAnimation",
		at = @At(value = "INVOKE",
			target = "Lnet/minecraft/client/particle/ParticleManager;addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;)V"),
		index = 1)
	private <T extends ParticleEffect> T changeCritAttackParticle(T particle) {//idk how to name it properly for now
		ClientPlayNetworkHandler player = (ClientPlayNetworkHandler)(Object)this;


		UniqueScythe.LOGGER.info("Changing crit particle ");
		if (true) return (T) ModParticleTypes.VOID_CRIT;
		return particle;


	}

	// I've learned how to do modify arg by myself, but it seems it is not what we are looking for
	// I mean the class and the mixin for it. It is working, but isn't changing the crit particle :(

	/*
	[12:18:32] [Render thread/ERROR] (Minecraft) Error executing task on Client
 java.lang.ClassCastException: class net.minecraft.client.network.ClientPlayNetworkHandler cannot be cast to class net.minecraft.entity.player.PlayerEntity (net.minecraft.client.network.ClientPlayNetworkHandler and net.minecraft.entity.player.PlayerEntity are in unnamed module of loader org.quiltmc.loader.impl.launch.knot.KnotClassLoader @fad74ee)
	at net.minecraft.client.network.ClientPlayNetworkHandler.modify$znf000$uniquescythe$changeCritAttackParticle(ClientPlayNetworkHandler.java:5734) ~[minecraft-merged-3b8fe05838-1.20.1-org.quiltmc.quilt-mappings.1_20_1.1.20.1+build.23-v2.jar:?]
	at net.minecraft.client.network.ClientPlayNetworkHandler.onEntityAnimation(ClientPlayNetworkHandler.java:965) ~[minecraft-merged-3b8fe05838-1.20.1-org.quiltmc.quilt-mappings.1_20_1.1.20.1+build.23-v2.jar:?]
	at net.minecraft.network.packet.s2c.play.EntityAnimationS2CPacket.apply(EntityAnimationS2CPacket.java:36) ~[minecraft-merged-3b8fe05838-1.20.1-org.quiltmc.quilt-mappings.1_20_1.1.20.1+build.23-v2.jar:?]
	at net.minecraft.network.packet.s2c.play.EntityAnimationS2CPacket.apply(EntityAnimationS2CPacket.java:8) ~[minecraft-merged-3b8fe05838-1.20.1-org.quiltmc.quilt-mappings.1_20_1.1.20.1+build.23-v2.jar:?]
	at net.minecraft.network.NetworkThreadUtils.method_11072(NetworkThreadUtils.java:22) ~[minecraft-merged-3b8fe05838-1.20.1-org.quiltmc.quilt-mappings.1_20_1.1.20.1+build.23-v2.jar:?]
	at net.minecraft.util.thread.ThreadExecutor.executeTask(ThreadExecutor.java:156) ~[minecraft-merged-3b8fe05838-1.20.1-org.quiltmc.quilt-mappings.1_20_1.1.20.1+build.23-v2.jar:?]
	at net.minecraft.util.thread.ReentrantThreadExecutor.executeTask(ReentrantThreadExecutor.java:23) ~[minecraft-merged-3b8fe05838-1.20.1-org.quiltmc.quilt-mappings.1_20_1.1.20.1+build.23-v2.jar:?]
	at net.minecraft.util.thread.ThreadExecutor.runTask(ThreadExecutor.java:130) ~[minecraft-merged-3b8fe05838-1.20.1-org.quiltmc.quilt-mappings.1_20_1.1.20.1+build.23-v2.jar:?]
	at net.minecraft.util.thread.ThreadExecutor.runTasks(ThreadExecutor.java:115) ~[minecraft-merged-3b8fe05838-1.20.1-org.quiltmc.quilt-mappings.1_20_1.1.20.1+build.23-v2.jar:?]
	at net.minecraft.client.MinecraftClient.render(MinecraftClient.java:1175) ~[minecraft-merged-3b8fe05838-1.20.1-org.quiltmc.quilt-mappings.1_20_1.1.20.1+build.23-v2.jar:?]
	at net.minecraft.client.MinecraftClient.run(MinecraftClient.java:802) ~[minecraft-merged-3b8fe05838-1.20.1-org.quiltmc.quilt-mappings.1_20_1.1.20.1+build.23-v2.jar:?]
	at net.minecraft.client.main.Main.main(Main.java:250) ~[minecraft-merged-3b8fe05838-1.20.1-org.quiltmc.quilt-mappings.1_20_1.1.20.1+build.23-v2.jar:?]
	at jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[?:?]
	at jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[?:?]
	at jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[?:?]
	at java.lang.reflect.Method.invoke(Method.java:568) ~[?:?]
	at org.quiltmc.loader.impl.game.minecraft.MinecraftGameProvider.launch(MinecraftGameProvider.java:551) ~[quilt-loader-0.24.0.jar:?]
	at org.quiltmc.loader.impl.launch.knot.Knot.launch(Knot.java:84) ~[quilt-loader-0.24.0.jar:?]
	at org.quiltmc.loader.impl.launch.knot.KnotClient.main(KnotClient.java:28) ~[quilt-loader-0.24.0.jar:?]
	at net.fabricmc.devlaunchinjector.Main.main(Main.java:86) ~[dev-launch-injector-0.2.1+build.8.jar:?]
	 */
}

