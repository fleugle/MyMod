package nikita.uniquescythe.utility;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import nikita.uniquescythe.items.custom.PhylacteryBasedItem;


import static nikita.uniquescythe.utility.GuiltyLevelSystem.*;

public class SoulsSystem {
	public static String SOULS = "Souls";


	public static boolean updateSouls(ServerPlayerEntity player, String playerName){



		if ((getScore(player, playerName, GENERAL_KILLS_GUILTY_LEVEL) > 0) || (getScore(player, playerName, PLAYERS_KILL_GUILTY_ADDITION) > 0)){

			//updates guilty level and in the end returns true or false, that is needed for optimization
			updateGuiltyLevelPerEachEntityKill(player, playerName);



			return true;

		} else return false;



	}

	public static void resetSouls(ServerPlayerEntity player, String playerName/*, int amount*/){

		if (updateSouls( player, playerName)){

		}
		//applies new values

	}

	/*public static void setSouls(ServerPlayerEntity player, String playerName, int amount){

		if (updateSouls( player, playerName)){

		}
		//applies new values
		//CommandsExecuter.executeCommand(player, "scoreboard players set "+ playerName +" "+ SOULS +" "+ amount);

	}*/



	public static void addSoulsToPossibleItems(ServerPlayerEntity player, String playerName, int maxAmount){
		World world = player.getWorld();
		if (!world.isClient  && updateSouls(player, playerName)) {
			//updateSouls( player, playerName);

			NbtCompound tag = null;
			if (getFirstAvaliablePhylacteryItemStack(player) != null) {
				tag = getFirstAvaliablePhylacteryItemStack(player).getOrCreateNbt();
			}
			int soulsAmount = (getScore(player, playerName, GENERAL_KILLS_GUILTY_LEVEL))
				+ (getScore(player, playerName, PLAYERS_KILL_GUILTY_ADDITION) * 5)
				/*+ (getScore(player, playerName, SOULS))*/;
			if(soulsAmount > 0 && soulsAmount < maxAmount && tag != null) {
				int currentSoulsOnStack = tag.getInt(SOULS);
				int finalSoulsAmount = currentSoulsOnStack + soulsAmount;

				if (finalSoulsAmount >= maxAmount) finalSoulsAmount = maxAmount;

				tag.putInt(SOULS, finalSoulsAmount);
				resetSouls(player, playerName);
				//sounds + souls particles here
				//SoundsManager.playPlayersSoundFromPlayer(player, SoundEvents.BLOCK_SCULK_CHARGE, 1f);

				if (world instanceof ServerWorld) {


					((ServerWorld) world).spawnParticles(ParticleTypes.SCULK_SOUL,
						player.getPos().getX()  + 0,
						player.getPos().getY()  + 1,
						player.getPos().getZ()  + 0,
						15, 0.70, 0.55, 0.70, 0);

					((ServerWorld) world).spawnParticles(ParticleTypes.SCULK_CHARGE_POP,
						player.getPos().getX()  + 0,
						player.getPos().getY()  + 1,
						player.getPos().getZ()  + 0,
						15, 0.80, 0.70, 0.80, 0);

					((ServerWorld) world).spawnParticles(ParticleTypes.SOUL_FIRE_FLAME,
						player.getPos().getX()  + 0,
						player.getPos().getY()  + 1,
						player.getPos().getZ()  + 0,
						15, 0.20, 0.20, 0.20, 0);
				}
			}

		}

	}




	public static int getScore(ServerPlayerEntity player, String playerName, String objectiveName){

		Scoreboard scoreboard = player.getWorld().getScoreboard();
		ScoreboardObjective objective = scoreboard.getObjective(objectiveName);
		scoreboard.getPlayerScore(playerName, objective);


		ScoreboardPlayerScore score = scoreboard.getPlayerScore(playerName, objective);


		return score.getScore();
	}

	private static ItemStack getFirstAvaliablePhylacteryItemStack(ServerPlayerEntity player){
		ItemStack phylacteryBasedItemStack = null;

		for (int i = 0; i < player.getInventory().size(); i++) {
			ItemStack inventoryStack = player.getInventory().getStack(i);
			if (inventoryStack.getItem() instanceof PhylacteryBasedItem) {
				phylacteryBasedItemStack = inventoryStack;

				break; // Exit the loop after finding
			}


		}


		return phylacteryBasedItemStack;
	}




}
