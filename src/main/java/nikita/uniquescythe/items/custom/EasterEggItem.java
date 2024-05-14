package nikita.uniquescythe.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.sounds.ModSoundEvents;
import nikita.uniquescythe.utility.GetSuitableRandomNumber;
import nikita.uniquescythe.utility.SoundsManager;
import org.jetbrains.annotations.NotNull;

public class EasterEggItem extends Item {
	private int soundPlayed;

	private GetSuitableRandomNumber suitableRandomNumber;

	public EasterEggItem(Settings settings) {
		super(settings);
		this.suitableRandomNumber = new GetSuitableRandomNumber();
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, @NotNull PlayerEntity user, Hand hand) {
		soundPlayed = suitableRandomNumber.generateRandomIntNumber(4); //I need to create an object first, and only then to use methods from it.
		//BTW,  max value stands for a value that will never be reached, so i guess the correct way to do it here is to put a number of cases instead of an actual maxValue for int. Others not tested yet.

		//uhahaha hahahahaha hahaha ha ha ha ha ha haha
		if (user.getWorld() != null && !world.isClient) {

			if (soundPlayed == 0) {
				stopFunnySounds();
				SoundsManager.playNeutralSoundOnSpot(user, ModSoundEvents.MUFLON,5f);
				user.getItemCooldownManager().set(this, 150); // a way to make a cooldown for an item
			} else if (soundPlayed == 1) {
				stopFunnySounds();
				SoundsManager.playNeutralSoundOnSpot(user, ModSoundEvents.EASTER_EGG,5f);
				user.getItemCooldownManager().set(this, 100); // a way to make a cooldown for an item
			} else if (soundPlayed == 2) {
				stopFunnySounds();
				SoundsManager.playNeutralSoundOnSpot(user, ModSoundEvents.SAD2_OGG,5f);
				user.getItemCooldownManager().set(this, 30);
			} else if (soundPlayed == 3) {
				stopFunnySounds();
				SoundsManager.playNeutralSoundOnSpot(user, ModSoundEvents.KRIS_GET_THE_BANANA,5f);
				user.getItemCooldownManager().set(this, 70);
			}
			else {
				stopFunnySounds();
				SoundsManager.playNeutralSoundOnSpot(user, ModSoundEvents.EASTER_EGG,5f);
				user.getItemCooldownManager().set(this, 30);
			}

		}

		return TypedActionResult.pass(user.getStackInHand(hand));
	}


	private static void stopFunnySounds(){
		SoundsManager.stopSound(ModSoundEvents.KRIS_GET_THE_BANANA, SoundCategory.NEUTRAL);
		SoundsManager.stopSound(ModSoundEvents.MUFLON, SoundCategory.NEUTRAL);
		SoundsManager.stopSound(ModSoundEvents.SAD2_OGG, SoundCategory.NEUTRAL);
		SoundsManager.stopSound(ModSoundEvents.EASTER_EGG, SoundCategory.NEUTRAL);
	}

	public void setSuitableRandomNumber(GetSuitableRandomNumber suitableRandomNumber) {
		this.suitableRandomNumber = suitableRandomNumber;
	}
}





