package nikita.uniquescythe;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.blocks.ModBlocks;
import nikita.uniquescythe.enchantments.ModEnchantments;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.entities.custom.BreezeEntity;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticleTypes;
import nikita.uniquescythe.sounds.ModSounds;
import org.quiltmc.qsl.block.content.registry.api.BlockContentRegistries;
import org.quiltmc.qsl.block.content.registry.api.ReversibleBlockEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//I've rolled back. good.
public class UniqueScythe implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	//


	private static TagKey<Block> create(String id) {
		return TagKey.of(RegistryKeys.BLOCK, new Identifier(id));
	}
	public static final TagKey<Block> ULTIMATE_MINEABLE = create("mineable/ultimate");


	public static final String MOD_ID = "uniquescythe";//mod id for further usage
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.





		LOGGER.info("Ghm Ghm, ladies and gentlemen, if you see that message, I declare though, you are either mentally ill little girl, either 30 years stinky man with no social life. (Of coarse if you are not me, because I'm perfect)");
		//calling for item registry method in ModItems class
		ModItems.registerModItems();

		//calling for sound registry method in mod sounds
		ModSounds.initializeSounds();

		//particles
		ModParticleTypes.initialiseModParticleTypes();

		//calling for blocks registry method in mod blocks
		ModBlocks.registerModBlocks();


		/*
		ReversibleBlockEntry copperToExposed = new ReversibleBlockEntry(ModBlocks.EXPOSED_COPPER_GRATE, true);
		ReversibleBlockEntry exposedToOxidized = new ReversibleBlockEntry(ModBlocks.OXIDIZED_COPPER_GRATE, true);


		BlockContentRegistries.OXIDIZABLE.put(ModBlocks.COPPER_GRATE,copperToExposed);
		BlockContentRegistries.OXIDIZABLE.put(ModBlocks.EXPOSED_COPPER_GRATE,exposedToOxidized);

		 */




		//Attributes
		//registering for attributes for an entity(Breeze)
		FabricDefaultAttributeRegistry.register(ModEntities.BREEZE, BreezeEntity.createBreezeAttributes());


		//enchantments & stuff
		ModEnchantments.initialiseModEnchantments();

		EnchantmentTarget target = ClassTinkerers.getEnum(EnchantmentTarget.class, "MACE");
		LOGGER.info("Can enchant mace? " + target.isAcceptableItem(ModItems.MACE));
	}
}



