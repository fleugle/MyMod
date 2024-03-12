package nikita.uniquescythe;

import net.fabricmc.api.ModInitializer;

import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticles;
import nikita.uniquescythe.sounds.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UniqueScythe implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "uniquescythe";//mod id for further usage
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Ghm Ghm, ladies and gentlemen, if you see that message, I declare though, you are either mentally ill little girl, either 30 years stinky man with no social life. ");
		//calling for item registry method in ModItems class
		ModItems.registerModItems();

		//calling for sound registry method in mod sounds
		ModSounds.initializeSounds();

		//calling for particles registry method in mod particles
		ModParticles.registerParticles();
	}
}



//just to check where i do commit my work i add this though.
