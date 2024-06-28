package nikita.uniquescythe;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.blocks.ModBlocks;
import nikita.uniquescythe.networking.SweepingParticlesPacket;
import nikita.uniquescythe.networking.UltraInvisibilityTracker;
import nikita.uniquescythe.enchantments.ModEnchantments;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.entities.custom.BreezeEntity;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticleTypes;
import nikita.uniquescythe.potions.ModPotions;
import nikita.uniquescythe.sounds.ModSoundEvents;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import nikita.uniquescythe.utility.CommandsExecuter;
import nikita.uniquescythe.utility.GuiltyLevelSystem;
import nikita.uniquescythe.utility.ModLootTablesModifiers;
import nikita.uniquescythe.utility.SoulsSystem;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.networking.api.PacketSender;
import org.quiltmc.qsl.networking.api.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//I've rolled back. good.
public class UniqueScythe implements ModInitializer {

	public static final String MOD_ID = "uniquescythe";//mod id for further usage
	public static final String GAME_ID = "minecraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize(ModContainer mod) {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.







		LOGGER.info("Ghm Ghm, ladies and gentlemen, if you see that message, I declare though, you are either mentally ill little girl, either 30 years stinky man with no social life. (Of coarse if you are not me, because I'm perfect)");
		//calling for item registry method in ModItems class
		ModItems.registerModItems();

		//calling for sound registry method in mod sounds
		ModSoundEvents.initializeSounds();

		//particles
		ModParticleTypes.initialiseModParticleTypes();

		//calling for blocks registry method in mod blocks
		ModBlocks.registerModBlocks();

		//status effects
		ModStatusEffects.initialiseStatusEffects();

		//loot tables modifiers
		ModLootTablesModifiers.modifyLootTables();

		//potions
		ModPotions.registerPotionsRecipes();






		//Attributes
		//registering for attributes for an entity(Breeze)
		FabricDefaultAttributeRegistry.register(ModEntities.BREEZE, BreezeEntity.createBreezeAttributes());


		//enchantments & stuff
		ModEnchantments.initialiseModEnchantments();

		EnchantmentTarget target = ClassTinkerers.getEnum(EnchantmentTarget.class, "MACE");
		LOGGER.info("Can enchant mace? " + target.isAcceptableItem(ModItems.MACE));

		UltraInvisibilityTracker.register();
		SweepingParticlesPacket.register();




		ServerPlayConnectionEvents.JOIN.register(this::onPlayerJoin);


		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

				SoulsSystem.updateSouls(player, player.getDisplayName().getString());

				GuiltyLevelSystem.updateGuiltyLevelPerEachEntityKill(player, player.getDisplayName().getString());

			}
		});

	}

	private void onPlayerJoin(ServerPlayNetworkHandler serverPlayNetworkHandler, PacketSender packetSender, MinecraftServer minecraftServer) {
		// Your code here
		// For example, send a welcome message to the player
		PlayerEntity player = serverPlayNetworkHandler.player;

		player.sendMessage(Text.of("Welcome to my SMP server!"), false);



		Scoreboard scoreboard = player.getWorld().getScoreboard();


		CommandsExecuter.createIntScoreboadIfMissing(scoreboard, ScoreboardCriterion.DUMMY, GuiltyLevelSystem.PERSISTENT_GUILTY_LEVEL);
		CommandsExecuter.createIntScoreboadIfMissing(scoreboard, ScoreboardCriterion.TOTAL_KILL_COUNT, GuiltyLevelSystem.GENERAL_KILLS_GUILTY_LEVEL);
		CommandsExecuter.createIntScoreboadIfMissing(scoreboard, ScoreboardCriterion.PLAYER_KILL_COUNT, GuiltyLevelSystem.PLAYERS_KILL_GUILTY_ADDITION);
		//CommandsExecuter.createIntScoreboadIfMissing(scoreboard, ScoreboardCriterion.DUMMY, SoulsSystem.SOULS);
		CommandsExecuter.createIntScoreboadIfMissing(scoreboard, ScoreboardCriterion.DUMMY, "Karma");
	}


}



