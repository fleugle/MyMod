package nikita.uniquescythe.sounds;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;


public class ModSoundEvents {

	//items and just essential sounds
    public static SoundEvent SCYTHE_CHARGED = registerSound("sc_ch");

    public static SoundEvent SCYTHE_HIT = registerSound("sc_ht");

	public static SoundEvent EASTER_EGG = registerSound("e_g");

	public static SoundEvent WIND_CHARGE_BURST = registerSound("w_b");

	public static SoundEvent WIND_CHARGE_THROW = registerSound("w_th");

	public static SoundEvent LIGHTNING_CHARGE_THROW = registerSound("l-ning_th");

	public static SoundEvent MACE_BONK = registerSound("mace_b");

	public static SoundEvent MUFLON = registerSound("muflon");

	public static SoundEvent SAD2_OGG = registerSound("sad");

	public static SoundEvent KRIS_GET_THE_BANANA = registerSound("kris_get_the_banana");

	public static SoundEvent JUSTICE_SHOOT = registerSound("justice_shoot");

	public static SoundEvent EMPTY_GUN_SHOT = registerSound("empty_gun_shot");

	public static SoundEvent REVOLVER_RELOAD = registerSound("revolver_reload");

	public static SoundEvent DUPLICATE_SUCCESS = registerSound("duplicate_success");

	public static SoundEvent DUPLICATE_FAIL = registerSound("duplicate_fail");

	public static SoundEvent DING1 = registerSound("ding1");

	public static SoundEvent DING2 = registerSound("ding2");

	public static SoundEvent KARMA_CHARGE = registerSound("karma_charge");

	public static SoundEvent KARMA_RELEASE = registerSound("karma_release");

	public static SoundEvent PREPARE_MINER = registerSound("prepare_miner");

	public static SoundEvent CLOSE_MINER = registerSound("close_miner");




	//armor sounds
	public static SoundEvent INNOCENCE_APPROVED = registerSound("innc_apr");




	//blocks

	public static SoundEvent BLOCK_HEAVY_CORE_BREAK = registerSound("block.heavy_core.break");

	public static SoundEvent BLOCK_HEAVY_CORE_STEP = registerSound("block.heavy_core.step");

	public static SoundEvent BLOCK_HEAVY_CORE_PLACE = registerSound("block.heavy_core.place");

	public static SoundEvent BLOCK_HEAVY_CORE_HIT = registerSound("block.heavy_core.hit");

	public static SoundEvent BLOCK_HEAVY_CORE_FALL = registerSound("block.heavy_core.fall");

	public static SoundEvent BLOCK_COPPER_BULB_TURN_ON = registerSound("block.copper_bulb.turn_on");

	public static SoundEvent BLOCK_COPPER_BULB_TURN_OFF = registerSound("block.copper_bulb.turn_off");

	public static final SoundEvent BLOCK_COPPER_BULB_BREAK = registerSound("block.copper_bulb.break");

	public static final SoundEvent BLOCK_COPPER_BULB_STEP = registerSound("block.copper_bulb.step");

	public static final SoundEvent BLOCK_COPPER_BULB_PLACE = registerSound("block.copper_bulb.place");

	public static final SoundEvent BLOCK_COPPER_BULB_HIT = registerSound("block.copper_bulb.hit");

	public static final SoundEvent BLOCK_COPPER_BULB_FALL = registerSound("block.copper_bulb.fall");




	// actual registration of all the custom SoundEvents
    public static SoundEvent registerSound(String id) {
        SoundEvent sound = SoundEvent.createVariableRangeEvent(new Identifier(UniqueScythe.MOD_ID, id));
        return Registry.register(Registries.SOUND_EVENT, new Identifier(UniqueScythe.MOD_ID, id), sound);
    }

    // called in the ModInitializer implementing class
    // to initialize the ModSoundEvents class
    public static void initializeSounds() {
        UniqueScythe.LOGGER.info("Registering " + UniqueScythe.MOD_ID + " Sounds");
    }
}
