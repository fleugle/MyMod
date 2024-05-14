package nikita.uniquescythe.items.custom;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;
import nikita.uniquescythe.sounds.ModSoundEvents;

public class WindChargeItem extends Item {
	public WindChargeItem(Settings settings) {
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
			ModSoundEvents.WIND_CHARGE_THROW,
			SoundCategory.NEUTRAL,
			2F,
			0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
		);
		if (!world.isClient) {
			WindChargeProjectileEntity windChargeProjectileEntity = new WindChargeProjectileEntity(user, world);
			windChargeProjectileEntity.setItem(itemStack);
			windChargeProjectileEntity.setProperties(user, user.getPitch(), user.getYaw(), 1.0F, 2F, 1F);
			world.spawnEntity(windChargeProjectileEntity);
			user.getItemCooldownManager().set(this, 7);
		}

		user.incrementStat(Stats.USED.getOrCreateStat(this));
		if (!user.getAbilities().creativeMode) {
			itemStack.decrement(1);
		}

		return TypedActionResult.success(itemStack, world.isClient());


	}
}

