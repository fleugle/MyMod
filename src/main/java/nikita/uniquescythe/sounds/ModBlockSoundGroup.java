package nikita.uniquescythe.sounds;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;

public class ModBlockSoundGroup extends BlockSoundGroup {


	public static final BlockSoundGroup HEAVY_CORE = new BlockSoundGroup(1.0f, 1.0f, ModSoundEvents.BLOCK_HEAVY_CORE_BREAK, ModSoundEvents.BLOCK_HEAVY_CORE_STEP, ModSoundEvents.BLOCK_HEAVY_CORE_PLACE, ModSoundEvents.BLOCK_HEAVY_CORE_HIT, ModSoundEvents.BLOCK_HEAVY_CORE_FALL);


	public ModBlockSoundGroup(float volume, float pitch, SoundEvent breakSound, SoundEvent stepSound, SoundEvent placeSound, SoundEvent hitSound, SoundEvent fallSound) {
		super(volume, pitch, breakSound, stepSound, placeSound, hitSound, fallSound);
	}
}
