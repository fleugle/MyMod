package nikita.uniquescythe.utility;

import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.JusticeArmorItem;
import org.jetbrains.annotations.NotNull;

public class GuiltyLevelSystem {
	public static String PERSISTENT_GUILTY_LEVEL = "PersistentGuiltyLevel";
	public static String GENERAL_KILLS_GUILTY_LEVEL = "GeneralKillsGuiltyLevel";
	public static String PLAYERS_KILL_GUILTY_ADDITION = "PlayersKillGuiltyAddition";


	public static void addGuiltyLevelsToPlayer(ServerPlayerEntity player, String playerName, int amount){


		CommandsExecuter.executeCommand(player, "scoreboard players add "+ playerName +" PersistentGuiltyLevel "+ amount);
		updateGuiltyLevelPerEachEntityKill(player, playerName);

	}


	public static void updateGuiltyLevelPerEachEntityKill(ServerPlayerEntity player, String playerName){



		if ((getGuiltyLevel(player, playerName, GENERAL_KILLS_GUILTY_LEVEL) > 0) || (getGuiltyLevel(player, playerName, PLAYERS_KILL_GUILTY_ADDITION) > 0)){
			//get values and write all of them as


			int amount = 0;
			if (player.getArmorItems() instanceof JusticeArmorItem) {
				amount = (getGuiltyLevel(player, playerName, GENERAL_KILLS_GUILTY_LEVEL) - 1)
					+ (getGuiltyLevel(player, playerName, PLAYERS_KILL_GUILTY_ADDITION))
					+ (getGuiltyLevel(player, playerName, PERSISTENT_GUILTY_LEVEL));
			}
			else {
				amount = (getGuiltyLevel(player, playerName, GENERAL_KILLS_GUILTY_LEVEL))
					+ (getGuiltyLevel(player, playerName, PLAYERS_KILL_GUILTY_ADDITION) * 5)
					+ (getGuiltyLevel(player, playerName, PERSISTENT_GUILTY_LEVEL));
			}


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





	public static int getGuiltyLevel(ServerPlayerEntity player, String playerName, String objectiveName){

		Scoreboard scoreboard = player.getWorld().getScoreboard();
		ScoreboardObjective objective = scoreboard.getObjective(objectiveName);
		scoreboard.getPlayerScore(playerName, objective);


		ScoreboardPlayerScore score = scoreboard.getPlayerScore(playerName, objective);


		return score.getScore();
	}




}
