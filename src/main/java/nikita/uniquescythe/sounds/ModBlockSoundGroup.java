package nikita.uniquescythe.sounds;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class ModBlockSoundGroup extends BlockSoundGroup {


	public static final BlockSoundGroup HEAVY_CORE = new BlockSoundGroup(1.0f, 1.0f, ModSounds.BLOCK_HEAVY_CORE_BREAK, ModSounds.BLOCK_HEAVY_CORE_STEP, ModSounds.BLOCK_HEAVY_CORE_PLACE, ModSounds.BLOCK_HEAVY_CORE_HIT, ModSounds.BLOCK_HEAVY_CORE_FALL);


	public ModBlockSoundGroup(float volume, float pitch, SoundEvent breakSound, SoundEvent stepSound, SoundEvent placeSound, SoundEvent hitSound, SoundEvent fallSound) {
		super(volume, pitch, breakSound, stepSound, placeSound, hitSound, fallSound);
	}
}
