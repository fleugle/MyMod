package nikita.uniquescythe.utility;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public class SoundsManager {

	public static void stopSound(SoundEvent soundEvent, SoundCategory soundCategory){

		MinecraftClient client = MinecraftClient.getInstance();
		client.getSoundManager().stopSounds(soundEvent.getId(), soundCategory);
	}
}
