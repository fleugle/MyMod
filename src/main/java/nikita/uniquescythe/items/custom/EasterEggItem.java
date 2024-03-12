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

	public EasterEggItem(Settings settings) {
		super(settings);
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		user.getWorld().playSound(null, user.getBlockPos(), ModSounds.EASTER_EGG, SoundCategory.AMBIENT, 20f, 1f);


		return TypedActionResult.pass(user.getStackInHand(hand));
	}
}
