package nikita.uniquescythe.utility;

import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import nikita.uniquescythe.UniqueScythe;
import org.jetbrains.annotations.NotNull;

public class GuiltyLevelSystem {

	public static void addGuiltyLevelsToPlayer(ServerPlayerEntity player, String playerName, int amount){


		CommandsExecuter.executeCommand(player, "scoreboard players add "+ playerName +" PersistentGuiltyLevel "+ amount);
		updateGuiltyLevelPerEachEntityKill(player, playerName);

	}


	public static void updateGuiltyLevelPerEachEntityKill(ServerPlayerEntity player, String playerName){



		if ((getGuiltyLevel(player, playerName, "GeneralKillsGuiltyLevel") > 0) || (getGuiltyLevel(player, playerName, "PlayersKillGuiltyAddition") > 0)){
			//get values and write all of them as
			int amount = (getGuiltyLevel(player, playerName, "GeneralKillsGuiltyLevel"))
				+ (getGuiltyLevel(player, playerName, "PlayersKillGuiltyAddition") * 5)
				+ (getGuiltyLevel(player, playerName, "PersistentGuiltyLevel")) ;



			//resets values for goods
			CommandsExecuter.executeCommand(player, "scoreboard players reset "+playerName+" PersistentGuiltyLevel");
			CommandsExecuter.executeCommand(player, "scoreboard players reset "+playerName+" GeneralKillsGuiltyLevel");
			CommandsExecuter.executeCommand(player, "scoreboard players reset "+playerName+" PlayersKillGuiltyAddition");



			//applies new values
			CommandsExecuter.executeCommand(player, "scoreboard players add "+ playerName +" PersistentGuiltyLevel "+ amount);





		}



	}

	public static void subtractGuiltyLevel(ServerPlayerEntity player, String playerName, int amount){

		updateGuiltyLevelPerEachEntityKill( player, playerName);
		//applies new values
		CommandsExecuter.executeCommand(player, "scoreboard players remove "+ playerName +" PersistentGuiltyLevel "+ amount);

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
