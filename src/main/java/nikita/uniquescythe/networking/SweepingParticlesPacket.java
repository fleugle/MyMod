package nikita.uniquescythe.networking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import nikita.uniquescythe.UniqueScythe;


public class SweepingParticlesPacket {
	public static final Identifier PARTICLE_PACKET_ID = new Identifier(UniqueScythe.MOD_ID, "particle_packet");

	public static void sendParticlePacket(ServerPlayerEntity player, ParticleEffect particleEffect, Vec3d pos) {
		PacketByteBuf buf = PacketByteBufs.create();
		buf.writeVarInt(Registries.PARTICLE_TYPE.getRawId((ParticleType<?>) particleEffect));
		buf.writeDouble(pos.x);
		buf.writeDouble(pos.y);
		buf.writeDouble(pos.z);

		ServerPlayNetworking.send(player, PARTICLE_PACKET_ID, buf);
	}

	public static void register() {
		ServerPlayNetworking.registerGlobalReceiver(PARTICLE_PACKET_ID, (server, player, handler, buf, responseSender) -> {
			int particleId = buf.readVarInt();
			double x = buf.readDouble();
			double y = buf.readDouble();
			double z = buf.readDouble();
			ParticleEffect particleEffect = (ParticleEffect) Registries.PARTICLE_TYPE.get(particleId);

			server.execute(() -> {
				if (particleEffect != null) {
					player.getWorld().addParticle(particleEffect, x, y, z, 0.0, 0.0, 0.0);
				}
			});
		});
	}
}
