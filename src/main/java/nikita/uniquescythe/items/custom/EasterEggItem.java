package nikita.uniquescythe.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.sounds.ModSounds;

public class EasterEggItem extends Item {
	private int soundPlayed = 0;

	public EasterEggItem(Settings settings) {
		super(settings);
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		//uhahaha hahahahaha hahaha ha ha ha ha ha haha
		if (user.getWorld() != null && !world.isClient) {

			if (soundPlayed == 0){
				user.getWorld().playSound(null, user.getBlockPos(), ModSounds.MUFLON, SoundCategory.AMBIENT, 20f, 1f);
				user.getItemCooldownManager().set(this, 3000); // a way to make a cooldown for an item
				soundPlayed++;
			}
			else if (soundPlayed == 1){
				user.getWorld().playSound(null, user.getBlockPos(), ModSounds.EASTER_EGG, SoundCategory.AMBIENT, 20f, 1f);
				user.getItemCooldownManager().set(this, 180); // a way to make a cooldown for an item
				soundPlayed++;
			}
			else {
				//add HI-HI-HI-HA sound here
				soundPlayed = 0;
			}


		}

		return TypedActionResult.pass(user.getStackInHand(hand));
	}
}
