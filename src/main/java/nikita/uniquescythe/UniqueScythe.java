package nikita.uniquescythe;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.ScoreboardObjectiveUpdateS2CPacket;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.blocks.ModBlocks;
import nikita.uniquescythe.enchantments.ModEnchantments;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.entities.custom.BreezeEntity;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticleTypes;
import nikita.uniquescythe.sounds.ModSounds;
import nikita.uniquescythe.utility.CommandsExecuter;
import org.quiltmc.qsl.networking.api.PacketSender;
import org.quiltmc.qsl.networking.api.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//I've rolled back. good.
public class UniqueScythe implements ModInitializer {

	public static final String MOD_ID = "uniquescythe";//mod id for further usage
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

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







		//Attributes
		//registering for attributes for an entity(Breeze)
		FabricDefaultAttributeRegistry.register(ModEntities.BREEZE, BreezeEntity.createBreezeAttributes());


		//enchantments & stuff
		ModEnchantments.initialiseModEnchantments();

		EnchantmentTarget target = ClassTinkerers.getEnum(EnchantmentTarget.class, "MACE");
		LOGGER.info("Can enchant mace? " + target.isAcceptableItem(ModItems.MACE));




		ServerPlayConnectionEvents.JOIN.register(this::onPlayerJoin);
	}

	private void onPlayerJoin(ServerPlayNetworkHandler serverPlayNetworkHandler, PacketSender packetSender, MinecraftServer minecraftServer) {
		// Your code here
		// For example, send a welcome message to the player
		PlayerEntity player = serverPlayNetworkHandler.player;

		player.sendMessage(Text.of("Welcome to my SMP server!"), false);



		Scoreboard scoreboard = player.getWorld().getScoreboard();


		String objectiveName = "GuiltyLevel";
		ScoreboardObjective objective = scoreboard.getObjective(objectiveName);

		if (objective == null) {
			// Create a new scoreboard named "GuiltyLevel"
			scoreboard.addObjective(objectiveName, ScoreboardCriterion.AIR, Text.of(objectiveName), ScoreboardCriterion.RenderType.INTEGER);
			if (scoreboard.getObjective(objectiveName) != null){
				LOGGER.info("technically should have been added scoreboard");
			}else LOGGER.info("fail");
			// Additional setup or configuration for the new scoreboard can be done here
		}
	}

}



