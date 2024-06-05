package nikita.uniquescythe.utility;

import net.minecraft.entity.Entity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import nikita.uniquescythe.UniqueScythe;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandsExecuter {

	public static void executeCommand(Entity entity, String command){
		UniqueScythe.LOGGER.info("Executing command "+ command + " for entity " + entity);//just a nice logger
		CommandManager commandManager = Objects.requireNonNull(entity.getServer()).getCommandManager();
		ServerCommandSource commandSource = entity.getCommandSource();


		commandManager.executePrefixedCommand(commandSource.withSilent(), command);
		//withSilent makes the command execution not appear in the game chat.
	}

	public static void executeCommandOnServer( World world, String command ){
		UniqueScythe.LOGGER.info("Executing command "+ command + " on server " );//just a nice logger
		CommandManager commandManager = Objects.requireNonNull(world.getServer()).getCommandManager();
		ServerCommandSource commandSource = world.getServer().getCommandSource();


		commandManager.executePrefixedCommand(commandSource.withSilent(), command);
		//withSilent makes the command execution not appear in the game chat.
	}

	public static void createIntScoreboadIfMissing(@NotNull Scoreboard scoreboard, ScoreboardCriterion criteria, String objectiveName){

		ScoreboardObjective objective = scoreboard.getObjective(objectiveName);

		if (objective == null) {
			// Create a new scoreboard named "GuiltyLevel"
			scoreboard.addObjective(objectiveName, criteria, Text.of(objectiveName), ScoreboardCriterion.RenderType.INTEGER);

			//additional check
			if (scoreboard.getObjective(objectiveName) != null){
				UniqueScythe.LOGGER.info("objective {} with criteria {} has been created", objectiveName, criteria);
			}else UniqueScythe.LOGGER.info("failed objective {} with criteria {} creation", objectiveName, criteria);
		}else UniqueScythe.LOGGER.info("{} objective  with criteria {} already exists", objectiveName, criteria);
	}

}
