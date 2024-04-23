package nikita.uniquescythe.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.sounds.ModSounds;
import nikita.uniquescythe.utility.GetSuitableRandomNumber;
import nikita.uniquescythe.utility.SoundsManager;

public class EasterEggItem extends Item {
	private int soundPlayed;

	private GetSuitableRandomNumber suitableRandomNumber;

	public EasterEggItem(Settings settings) {
		super(settings);
		this.suitableRandomNumber = new GetSuitableRandomNumber();
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		soundPlayed = suitableRandomNumber.generateRandomIntNumber(3); //I need to create an object first, and only then to use methods from it.
		//BTW,  max value stands for a value that will never be reached, so i guess the correct way to do it here is to put a number of cases instead of an actual maxValue for int. Others not tested yet.

		//uhahaha hahahahaha hahaha ha ha ha ha ha haha
		if (user.getWorld() != null && !world.isClient) {

			if (soundPlayed == 0) {
				stopFunnySounds();
				user.getWorld().playSound(null, user.getBlockPos(), ModSounds.MUFLON, SoundCategory.AMBIENT, 20f, 1f);
				user.getItemCooldownManager().set(this, 150); // a way to make a cooldown for an item
			} else if (soundPlayed == 1) {
				stopFunnySounds();
				user.getWorld().playSound(null, user.getBlockPos(), ModSounds.EASTER_EGG, SoundCategory.AMBIENT, 20f, 1f);
				user.getItemCooldownManager().set(this, 100); // a way to make a cooldown for an item
			} else if (soundPlayed == 2) {
				stopFunnySounds();
				user.getWorld().playSound(null, user.getBlockPos(), ModSounds.SAD2_OGG, SoundCategory.AMBIENT, 1f, 1f);
				user.getItemCooldownManager().set(this, 30);
			}
			else {
				stopFunnySounds();
				user.getWorld().playSound(null, user.getBlockPos(), ModSounds.SAD2_OGG, SoundCategory.AMBIENT, 1f, 1f);
				user.getItemCooldownManager().set(this, 30);
			}

		}

		return TypedActionResult.pass(user.getStackInHand(hand));
	}


	private static void stopFunnySounds(){
		SoundsManager.stopSound(ModSounds.MUFLON, SoundCategory.AMBIENT);
		SoundsManager.stopSound(ModSounds.SAD2_OGG, SoundCategory.AMBIENT);
		SoundsManager.stopSound(ModSounds.EASTER_EGG, SoundCategory.AMBIENT);
	}

	public void setSuitableRandomNumber(GetSuitableRandomNumber suitableRandomNumber) {
		this.suitableRandomNumber = suitableRandomNumber;
	}
}





