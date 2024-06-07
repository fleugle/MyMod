package nikita.uniquescythe.datatracker;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class UltraInvisibilityTracker {

	private static final Identifier ULTRA_INVISIBILITY_PACKET_ID = new Identifier(UniqueScythe.MOD_ID, "ultra_invisibility");

	public static void updateUltraInvisibilityStatus(ServerPlayerEntity player, boolean isUltraInvisible) {
		PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
		buf.writeUuid(player.getUuid());
		buf.writeBoolean(isUltraInvisible);

		// Send the packet to all players
		for (ServerPlayerEntity p : player.getServer().getPlayerManager().getPlayerList()) {
			ServerPlayNetworking.send(p, ULTRA_INVISIBILITY_PACKET_ID, buf);
		}
	}
}
