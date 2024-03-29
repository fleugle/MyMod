package nikita.uniquescythe.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;


public class ModSounds {
    // ITEM_METAL_WHISTLE is the name of the custom sound event
    // and is called in the mod to use the custom sound
    public static SoundEvent SCYTHE_CHARGED = registerSound("sc_ch");
    public static SoundEvent SCYTHE_HIT = registerSound("sc_ht");

	public static SoundEvent EASTER_EGG = registerSound("e_g");

	public static SoundEvent WIND_CHARGE_BURST = registerSound("w_b");


	public static SoundEvent WIND_CHARGE_THROW = registerSound("w_th");

	public static SoundEvent LIGHTNING_CHARGE_THROW = registerSound("l-ning_th");

	public static SoundEvent MACE_BONK = registerSound("mace_b");

	public static SoundEvent MUFLON = registerSound("muflon");

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
