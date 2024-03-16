package nikita.uniquescythe.items.custom;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.entity.damage.DamageSource;


import java.util.List;

public class WindChargeItem extends Item {
	public WindChargeItem(Settings settings) {
		super(settings);
	}

	// Inside your custom explosive item or block class
	public void windExplode(World world, BlockPos pos) {
		float explosionSize = 1f; // Adjust the explosion radius
		float knockbackStrength = 2.5f; // Adjust knockback strength
		float damage = 1.0f; // Set the desired damage value


		// Create the explosion
		world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), explosionSize, World.ExplosionSourceType.NONE);

		// Apply knockback to nearby living entities
		List<? extends Entity> nearbyEntities = world.getOtherEntities(null, new Box(pos).expand(explosionSize), entity -> entity instanceof LivingEntity);
		for (Entity entity : nearbyEntities) {
			LivingEntity livingEntity = (LivingEntity) entity;

			// Calculate knockback direction
			double dx = livingEntity.getX() - pos.getX();
			double dy = livingEntity.getY() - pos.getY();
			double dz = livingEntity.getZ() - pos.getZ();
			double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

			// Apply knockback velocity
			double knockbackX = dx / distance * knockbackStrength;
			double knockbackY = dy / distance * knockbackStrength;
			double knockbackZ = dz / distance * knockbackStrength;
			livingEntity.addVelocity(knockbackX, knockbackY, knockbackZ);
		}
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if(user.getWorld() != null &&!world.isClient){
			windExplode(world , user.getBlockPos() );
			user.getItemCooldownManager().set(this, 7); // a way to make a cooldown for an item

		}
		return TypedActionResult.pass(user.getStackInHand(hand));
	}
}

