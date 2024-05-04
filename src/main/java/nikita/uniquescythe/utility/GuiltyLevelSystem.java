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

	public static void addGuiltyLevelsToPlayer(ServerPlayerEntity player, String playerName, int amount, int multiplier){


		CommandsExecuter.executeCommand(player, "scoreboard players add "+ playerName +" PersistentGuiltyLevel "+ amount);
		updateGuiltyLevelPerEachEntityKill(player, playerName, multiplier);

	}


	public static void updateGuiltyLevelPerEachEntityKill(ServerPlayerEntity player, String playerName, int multiplier){

		int timeOut = 0;

		if (timeOut == 0){
			//get values and write all of them as
			int amount = (getGuiltyLevel(player, playerName, "GeneralKillsGuiltyLevel"))
				+ (getGuiltyLevel(player, playerName, "PlayersKillGuiltyAddition") * multiplier)
				+ (getGuiltyLevel(player, playerName, "PersistentGuiltyLevel")) ;



			//resets values for goods
			CommandsExecuter.executeCommand(player, "scoreboard players reset "+playerName+" PersistentGuiltyLevel");
			CommandsExecuter.executeCommand(player, "scoreboard players reset "+playerName+" GeneralKillsGuiltyLevel");
			CommandsExecuter.executeCommand(player, "scoreboard players reset "+playerName+" PlayersKillGuiltyAddition");



			//applies new values
			CommandsExecuter.executeCommand(player, "scoreboard players add "+ playerName +" PersistentGuiltyLevel "+ amount);

			timeOut++;

			if (timeOut == 100) timeOut = 0;
		}


	}

	public static void removeGuiltyLevel(ServerPlayerEntity player, String playerName, int amount, int multiplier){

		updateGuiltyLevelPerEachEntityKill( player, playerName, multiplier);
		//applies new values
		CommandsExecuter.executeCommand(player, "scoreboard players add "+ playerName +" PersistentGuiltyLevel "+ amount);

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


	public static int getGuiltyLevel(ServerPlayerEntity player, String playerName, String objectiveName){

		Scoreboard scoreboard = player.getWorld().getScoreboard();
		ScoreboardObjective objective = scoreboard.getObjective(objectiveName);
		scoreboard.getPlayerScore(playerName, objective);


		ScoreboardPlayerScore score = scoreboard.getPlayerScore(playerName, objective);


		return score.getScore();
	}




}
