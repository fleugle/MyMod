package nikita.uniquescythe.utility;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import nikita.uniquescythe.UniqueScythe;
import org.jetbrains.annotations.NotNull;

public class GuiltyLevelSystem {

	public static void addGuiltyLevelsToPlayer(PlayerEntity player, String playerName, int amount){


		CommandsExecuter.executeCommand(player, "scoreboard players add "+ playerName +" PersistentGuiltyLevel "+ amount);

	}

	public static void createIntScoreboadIfMissing(@NotNull Scoreboard scoreboard, ScoreboardCriterion criteria, String objectiveName){

		ScoreboardObjective objective = scoreboard.getObjective(objectiveName);

		if (objective == null) {
			// Create a new scoreboard named "GuiltyLevel"
			scoreboard.addObjective(objectiveName, criteria, Text.of(objectiveName), ScoreboardCriterion.RenderType.INTEGER);

			//additional check
			if (scoreboard.getObjective(objectiveName) != null){
				UniqueScythe.LOGGER.info("objective "+ objectiveName +" with criteria "+ criteria +" has been created");
			}else UniqueScythe.LOGGER.info("failed objective "+ objectiveName + " with criteria " + criteria + " creation");
		}else UniqueScythe.LOGGER.info(objectiveName + " objective "+ " with criteria " + criteria +"already exists");
	}

	public static int getGuiltyLevel(ServerPlayerEntity player, String playerName, String objectiveName){

		Scoreboard scoreboard = player.getWorld().getScoreboard();
		ScoreboardObjective objective = scoreboard.getObjective(objectiveName);
		scoreboard.getPlayerScore(playerName, objective);


		ScoreboardPlayerScore score = scoreboard.getPlayerScore(playerName, objective);


		return score.getScore();
	}



}
