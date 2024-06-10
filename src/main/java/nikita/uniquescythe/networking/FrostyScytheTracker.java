package nikita.uniquescythe.networking;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import org.quiltmc.qsl.networking.api.ServerPlayConnectionEvents;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class FrostyScytheTracker {

	private static final Identifier HAS_FROSTY_SCYTHE_PACKET_ID = new Identifier(UniqueScythe.MOD_ID, "ultra_invisibility");

	public static void updateFrostyScytheStatus(ServerPlayerEntity player, boolean hasFrostyScythe) {
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		buf.writeUuid(player.getUuid());
		buf.writeBoolean(hasFrostyScythe);

		// Send the packet to all players
		for (ServerPlayerEntity p : player.getServer().getPlayerManager().getPlayerList()) {
			ServerPlayNetworking.send(p, HAS_FROSTY_SCYTHE_PACKET_ID, buf);
		}
	}

	public static void syncFrostyScytheStatus(ServerPlayerEntity targetPlayer) {
		for (ServerPlayerEntity player : targetPlayer.getServer().getPlayerManager().getPlayerList()) {
			PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
			buf.writeUuid(player.getUuid());
			boolean isUltraInvisible = UltraInvisibilityClientHandler.isPlayerUltraInvisible(player.getUuid());
			buf.writeBoolean(isUltraInvisible);
			ServerPlayNetworking.send(targetPlayer, HAS_FROSTY_SCYTHE_PACKET_ID, buf);
		}
	}

	public static void register() {
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			ServerPlayerEntity player = handler.getPlayer();
			syncFrostyScytheStatus(player);
		});
	}
}
