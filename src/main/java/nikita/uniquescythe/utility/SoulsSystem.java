package nikita.uniquescythe.utility;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;


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
		World world = player.getWorld();
		if (!world.isClient) {
			updateSouls( player, playerName);
			NbtCompound tag = phylacteryBasedStack.getOrCreateNbt();
			int soulsAmount = getSouls(player, playerName, SOULS);
			if(tag.contains(SoulsSystem.SOULS) && soulsAmount > 0) {
				int currentSoulsOnStack = tag.getInt(SOULS);
				int finalSoulsAmount = currentSoulsOnStack + soulsAmount;
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




	public static int getSouls(ServerPlayerEntity player, String playerName, String objectiveName){

		Scoreboard scoreboard = player.getWorld().getScoreboard();
		ScoreboardObjective objective = scoreboard.getObjective(objectiveName);
		scoreboard.getPlayerScore(playerName, objective);


		ScoreboardPlayerScore score = scoreboard.getPlayerScore(playerName, objective);


		return score.getScore();
	}




}
