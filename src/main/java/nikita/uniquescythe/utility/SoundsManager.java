package nikita.uniquescythe.utility;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import nikita.uniquescythe.sounds.ModSounds;
import org.jetbrains.annotations.NotNull;

public class SoundsManager {

	public static void stopSound(@NotNull SoundEvent soundEvent, SoundCategory soundCategory){

		MinecraftClient client = MinecraftClient.getInstance();
		client.getSoundManager().stopSounds(soundEvent.getId(), soundCategory);
	}

	public static void playSoundOnSpot(@NotNull Entity entity, SoundEvent soundEvent, SoundCategory soundCategory, float volume, float pitch){
		entity.getWorld().playSound(null, entity.getBlockPos(), soundEvent, soundCategory, volume, pitch);
	}

	public static void playSoundOnSpot(@NotNull Entity entity, SoundEvent soundEvent, SoundCategory soundCategory, float volume){
		entity.getWorld().playSound(null, entity.getBlockPos(), soundEvent, soundCategory, volume, 1f);
	}

	public static void playAmbientSoundOnSpot(@NotNull Entity entity, SoundEvent soundEvent, float volume, float pitch){
		entity.getWorld().playSound(null, entity.getBlockPos(), soundEvent, SoundCategory.AMBIENT, volume, pitch);
	}

	public static void playPlayersSoundOnSpot(@NotNull Entity entity, SoundEvent soundEvent, float volume, float pitch){
		entity.getWorld().playSound(null, entity.getBlockPos(), soundEvent, SoundCategory.PLAYERS, volume, pitch);
	}

	public static void playNeutralSoundOnSpot(@NotNull Entity entity, SoundEvent soundEvent, float volume, float pitch){
		entity.getWorld().playSound(null, entity.getBlockPos(), soundEvent, SoundCategory.NEUTRAL, volume, pitch);
	}

	public static void playAmbientSoundOnSpot(@NotNull Entity entity, SoundEvent soundEvent, float volume){
		entity.getWorld().playSound(null, entity.getBlockPos(), soundEvent, SoundCategory.AMBIENT, volume, 1f);
	}

	public static void playPlayersSoundOnSpot(@NotNull Entity entity, SoundEvent soundEvent, float volume){
		entity.getWorld().playSound(null, entity.getBlockPos(), soundEvent, SoundCategory.PLAYERS, volume, 1f);
	}

	public static void playNeutralSoundOnSpot(@NotNull Entity entity, SoundEvent soundEvent, float volume){
		entity.getWorld().playSound(null, entity.getBlockPos(), soundEvent, SoundCategory.NEUTRAL, volume, 1f);
	}
}
