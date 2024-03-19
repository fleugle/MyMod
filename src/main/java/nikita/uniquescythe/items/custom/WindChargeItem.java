package nikita.uniquescythe.items.custom;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import nikita.uniquescythe.custom.WindExplosion;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;
import nikita.uniquescythe.sounds.ModSounds;


import java.util.List;

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
			ModSounds.WIND_CHARGE_THROW,
			SoundCategory.NEUTRAL,
			2F,
			0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
		);
		if (!world.isClient) {
			WindChargeProjectileEntity windChargeProjectileEntity = new WindChargeProjectileEntity(user, world);
			windChargeProjectileEntity.setItem(itemStack);
			windChargeProjectileEntity.setWindProjectyleProperties(user, user.getPitch(), user.getYaw(), 1.0F, 1.5F, 0F);
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

