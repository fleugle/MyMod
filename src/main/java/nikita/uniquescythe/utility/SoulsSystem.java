package nikita.uniquescythe.utility;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.network.ServerPlayerEntity;
import nikita.uniquescythe.items.custom.JusticeArmorItem;

import static nikita.uniquescythe.utility.GuiltyLevelSystem.*;

public class SoulsSystem {
	public static String SOULS = "Souls";


	public static boolean updateSouls(ServerPlayerEntity player, String playerName){



		if ((getSouls(player, playerName, GENERAL_KILLS_GUILTY_LEVEL) > 0) || (getSouls(player, playerName, PLAYERS_KILL_GUILTY_ADDITION) > 0)){
			//get values and write all of them as





			int amount = (getSouls(player, playerName, GENERAL_KILLS_GUILTY_LEVEL))
					+ (getSouls(player, playerName, PLAYERS_KILL_GUILTY_ADDITION) * 5)
					+ (getSouls(player, playerName, SOULS));


			//applies new values
			CommandsExecuter.executeCommand(player, "scoreboard players add "+ playerName +" "+ SOULS +" "+ amount);

			updateGuiltyLevelPerEachEntityKill(player, playerName);



			return true;

		} else return false;



	}

	public static void resetSouls(ServerPlayerEntity player, String playerName/*, int amount*/){

		updateSouls( player, playerName);
		//applies new values
		CommandsExecuter.executeCommand(player, "scoreboard players reset "+ playerName +" "+ SOULS /*+" "+ amount*/);

	}

	public static void setSouls(ServerPlayerEntity player, String playerName, int amount){

		updateSouls( player, playerName);
		//applies new values
		CommandsExecuter.executeCommand(player, "scoreboard players set "+ playerName +" "+ SOULS +" "+ amount);

	}



	public static void addSoulsToPossibleItems(ServerPlayerEntity player, String playerName, ItemStack phylacteryBasedStack){

		updateSouls( player, playerName);
		NbtCompound tag = phylacteryBasedStack.getOrCreateNbt();
		int soulsAmount = getSouls(player, playerName, SOULS);
		if(tag.contains(SOULS)) {
			tag.putInt(SOULS, soulsAmount);
			resetSouls(player, playerName);
		}

	}




	public static int getSouls(ServerPlayerEntity player, String playerName, String objectiveName){

		Scoreboard scoreboard = player.getWorld().getScoreboard();
		ScoreboardObjective objective = scoreboard.getObjective(objectiveName);
		scoreboard.getPlayerScore(playerName, objective);


		ScoreboardPlayerScore score = scoreboard.getPlayerScore(playerName, objective);


		return score.getScore();
	}




}
