package nikita.uniquescythe.items.custom;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.sounds.ModSoundEvents;

public class LightningChargeItem extends Item {
	public LightningChargeItem(Settings settings) {
		super(settings);
	}



	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		world.playSound(
			null,
			user.getX(),
			user.getY(),
			user.getZ(),
			ModSoundEvents.LIGHTNING_CHARGE_THROW,
			SoundCategory.NEUTRAL,
			2F,
			0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
		);
		if (!world.isClient) {

		}

		user.incrementStat(Stats.USED.getOrCreateStat(this));
		if (!user.getAbilities().creativeMode) {
			itemStack.decrement(1);
		}

		return TypedActionResult.success(itemStack, world.isClient());


	}
}

