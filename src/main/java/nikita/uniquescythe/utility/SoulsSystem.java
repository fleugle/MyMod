package nikita.uniquescythe.utility;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import nikita.uniquescythe.items.custom.PhylacteryBasedItem;
import nikita.uniquescythe.sounds.ModSoundEvents;


import static nikita.uniquescythe.utility.GuiltyLevelSystem.*;

public class SoulsSystem {
	public static String SOULS = "Souls";
	public static String MAX_SOULS = "MaxSouls";


	public static void addSoulsToPossibleItems(ServerPlayerEntity player, String playerName){
		World world = player.getWorld();
		if (!world.isClient) {
			if ((getScore(player, playerName, GENERAL_KILLS_GUILTY_LEVEL) > 0) || (getScore(player, playerName, PLAYERS_KILL_GUILTY_ADDITION) > 0)) {
				//updateSouls( player, playerName);

				NbtCompound tag = null;
				ItemStack phylacteryBasedItemStack = null;
				if (getFirstAvaliablePhylacteryItemStack(player) != null) {
					tag = getFirstAvaliablePhylacteryItemStack(player).getOrCreateNbt();
					phylacteryBasedItemStack = getFirstAvaliablePhylacteryItemStack(player);
				}
				int soulsAmount = (getScore(player, playerName, GENERAL_KILLS_GUILTY_LEVEL))
					/*+ (getScore(player, playerName, PLAYERS_KILL_GUILTY_ADDITION) * 5)*/
					/*+ (getScore(player, playerName, SOULS))*/;
				if(soulsAmount > 0 && tag != null) {
					int currentSoulsOnStack = tag.getInt(SOULS);
					int maxCapacity = getMaxSoulsAmount(phylacteryBasedItemStack);
					int finalSoulsAmount = currentSoulsOnStack + soulsAmount;

					if (finalSoulsAmount >= maxCapacity) finalSoulsAmount = maxCapacity;

					tag.putInt(SOULS, finalSoulsAmount);
					//resetSouls(player, playerName);
					//sounds + souls particles here
					SoundsManager.playPlayersSoundFromPlayer(player, ModSoundEvents.SOUL_STEAL, 3f);
					SoundsManager.playPlayersSoundFromPlayer(player, ModSoundEvents.SOUL_STEAL, 3f);

					//particles
					if (world instanceof ServerWorld) {


						((ServerWorld) world).spawnParticles(ParticleTypes.SCULK_SOUL,
							player.getPos().getX()  + 0,
							player.getPos().getY()  + 1,
							player.getPos().getZ()  + 0,
							5, 0.70, 0.55, 0.70, 0);

						((ServerWorld) world).spawnParticles(ParticleTypes.SCULK_CHARGE_POP,
							player.getPos().getX()  + 0,
							player.getPos().getY()  + 1,
							player.getPos().getZ()  + 0,
							5, 0.80, 0.70, 0.80, 0);

						((ServerWorld) world).spawnParticles(ParticleTypes.SOUL_FIRE_FLAME,
							player.getPos().getX()  + 0,
							player.getPos().getY()  + 1,
							player.getPos().getZ()  + 0,
							5, 0.20, 0.20, 0.20, 0);
					}
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

	private static int getSoulsOnStack(ItemStack stack) {
		NbtCompound tag = stack.getOrCreateNbt();
		return tag.contains(SoulsSystem.SOULS) ? tag.getInt(SoulsSystem.SOULS) : 0;
	}

	private static boolean isPhylacteryStack(ItemStack stack){
		NbtCompound tag = stack.getOrCreateNbt();
		return tag.contains(SoulsSystem.SOULS);
	}

	private static ItemStack getFirstAvaliablePhylacteryItemStack(ServerPlayerEntity player){
		ItemStack phylacteryBasedItemStack = null;

		for (int i = 0; i < player.getInventory().size(); i++) {
			ItemStack inventoryStack = player.getInventory().getStack(i);
			if (inventoryStack.getItem() instanceof PhylacteryBasedItem) {
				phylacteryBasedItemStack = inventoryStack;
				int maxSoulsAmount = getMaxSoulsAmount(phylacteryBasedItemStack);

				if (isPhylacteryStack(phylacteryBasedItemStack) && getSoulsOnStack(phylacteryBasedItemStack) < maxSoulsAmount) {
					break; // Exit the loop after finding
				}
			}
		}

		return phylacteryBasedItemStack;
	}

	private static int getMaxSoulsAmount(ItemStack stack){

		if (isPhylacteryStack(stack) && stack.getItem() instanceof PhylacteryBasedItem phylacteryBasedItem){

			return phylacteryBasedItem.getMaxCapacity();
		}

		return 0;
	}




}
