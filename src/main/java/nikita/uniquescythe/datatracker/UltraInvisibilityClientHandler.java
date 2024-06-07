package nikita.uniquescythe.datatracker;

import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UltraInvisibilityClientHandler {
	private static final Set<UUID> ultraInvisiblePlayers = new HashSet<>();

	public static void register() {
		ClientPlayNetworking.registerGlobalReceiver(new Identifier(UniqueScythe.MOD_ID, "ultra_invisibility"), (client, handler, buf, responseSender) -> {
			UUID playerId = buf.readUuid();
			boolean isUltraInvisible = buf.readBoolean();

			client.execute(() -> {
				if (isUltraInvisible) {
					ultraInvisiblePlayers.add(playerId);
				} else {
					ultraInvisiblePlayers.remove(playerId);
				}
			});
		});
	}

	public static boolean isPlayerUltraInvisible(UUID playerId) {
		return ultraInvisiblePlayers.contains(playerId);
	}
}
