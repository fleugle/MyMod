package nikita.uniquescythe.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;


public class ModSounds {
    public static SoundEvent SCYTHE_CHARGED = registerSound("sc_ch");
    public static SoundEvent SCYTHE_HIT = registerSound("sc_ht");

	public static SoundEvent EASTER_EGG = registerSound("e_g");

	public static SoundEvent WIND_CHARGE_BURST = registerSound("w_b");


	public static SoundEvent WIND_CHARGE_THROW = registerSound("w_th");

	public static SoundEvent LIGHTNING_CHARGE_THROW = registerSound("l-ning_th");

	public static SoundEvent MACE_BONK = registerSound("mace_b");

	public static SoundEvent MUFLON = registerSound("muflon");

	public static SoundEvent SAD2_OGG = registerSound("sad");



	//armor sounds
	public static SoundEvent INNOCENCE_APPROVED = registerSound("innc_apr");




	//blocks

	public static SoundEvent BLOCK_HEAVY_CORE_BREAK = registerSound("block.heavy_core.break");

	public static SoundEvent BLOCK_HEAVY_CORE_STEP = registerSound("block.heavy_core.step");

	public static SoundEvent BLOCK_HEAVY_CORE_PLACE = registerSound("block.heavy_core.place");

	public static SoundEvent BLOCK_HEAVY_CORE_HIT = registerSound("block.heavy_core.hit");

	public static SoundEvent BLOCK_HEAVY_CORE_FALL = registerSound("block.heavy_core.fall");




	// actual registration of all the custom SoundEvents
    static SoundEvent registerSound(String id) {
        SoundEvent sound = SoundEvent.createVariableRangeEvent(new Identifier(UniqueScythe.MOD_ID, id));
        return Registry.register(Registries.SOUND_EVENT, new Identifier(UniqueScythe.MOD_ID, id), sound);
    }

    // called in the ModInitializer implementing class
    // to initialize the ModSounds class
    public static void initializeSounds() {
        UniqueScythe.LOGGER.info("Registering " + UniqueScythe.MOD_ID + " Sounds");
    }
}
