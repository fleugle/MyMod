package nikita.uniquescythe.utility;

import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.network.ServerPlayerEntity;

public class KarmaSystem {

	public static void lowerKarma(ServerPlayerEntity player, String playerName, int amount){

		//applies new values
		CommandsExecuter.executeCommand(player, "scoreboard players remove "+ playerName +" Karma "+ amount);

	}

	public static void addKarmaToPlayer(ServerPlayerEntity player, String playerName, int amount){


		CommandsExecuter.executeCommand(player, "scoreboard players add "+ playerName +" Karma "+ amount);

	}

	public static void setKarmaToPlayer(ServerPlayerEntity player, String playerName, int amount){


		CommandsExecuter.executeCommand(player, "scoreboard players set "+ playerName +" Karma "+ amount);

	}


	public static int getKarma(ServerPlayerEntity player, String playerName){

		Scoreboard scoreboard = player.getWorld().getScoreboard();
		ScoreboardObjective objective = scoreboard.getObjective("Karma");
		scoreboard.getPlayerScore(playerName, objective);


		ScoreboardPlayerScore score = scoreboard.getPlayerScore(playerName, objective);


		return score.getScore();
	}

}
