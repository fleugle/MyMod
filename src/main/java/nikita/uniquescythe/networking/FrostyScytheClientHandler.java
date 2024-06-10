package nikita.uniquescythe.networking;

import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FrostyScytheClientHandler {
	private static final Set<UUID> playersWithFrostyScythe = new HashSet<>();

	public static void register() {
		ClientPlayNetworking.registerGlobalReceiver(new Identifier(UniqueScythe.MOD_ID, "ultra_invisibility"), (client, handler, buf, responseSender) -> {
			UUID playerId = buf.readUuid();
			boolean hasFrostyScythe = buf.readBoolean();

			client.execute(() -> {
				if (hasFrostyScythe) {
					playersWithFrostyScythe.add(playerId);
				} else {
					playersWithFrostyScythe.remove(playerId);
				}
			});
		});
	}

	public static boolean isPlayerHoldingFrostyScythe(UUID playerId) {
		return playersWithFrostyScythe.contains(playerId);
	}
}
