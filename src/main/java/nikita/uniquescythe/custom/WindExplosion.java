package nikita.uniquescythe.custom;

import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public class WindExplosion extends Explosion {

	private final World world;
	private final double x;
	private final double y;
	private final double z;
	private final float radius;


	public WindExplosion(
		World world,
		Entity entity,
		double x,
		double y,
		double z,
		float radius
	) {
		super(world, entity, x, y, z, 3, false, DestructionType.KEEP);

		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.radius = radius;

    }






	@Override
	public void collectBlocksAndDamageEntities() {
		this.world.emitGameEvent(this.getEntity(), GameEvent.EXPLODE, new Vec3d(this.x, this.y, this.z));
		Set<BlockPos> set = Sets.<BlockPos>newHashSet();
		int i = 16;

		for(int j = 0; j < 16; ++j) {
			for(int k = 0; k < 16; ++k) {
				for(int l = 0; l < 16; ++l) {
					if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15) {
						double d = (double)((float)j / 15.0F * 2.0F - 1.0F);
						double e = (double)((float)k / 15.0F * 2.0F - 1.0F);
						double f = (double)((float)l / 15.0F * 2.0F - 1.0F);
						double g = Math.sqrt(d * d + e * e + f * f);
						d /= g;
						e /= g;
						f /= g;
						float h = this.radius * (0.7F + this.world.random.nextFloat() * 0.6F);
						double m = this.x;
						double n = this.y;
						double o = this.z;

						for(float p = 0.3F; h > 0.0F; h -= 0.22500001F) {
							BlockPos blockPos = BlockPos.create(m, n, o);
							BlockState blockState = this.world.getBlockState(blockPos);
							FluidState fluidState = this.world.getFluidState(blockPos);
							if (!this.world.isInBuildLimit(blockPos)) {
								break;
							}

							Optional<Float> optional = this.getBlastResistance(this, this.world, blockPos, blockState, fluidState);
							if (optional.isPresent()) {
								h -= (optional.get() + 0.3F) * 0.3F;
							}

							if (h > 0.0F && this.canDestroyBlock(this, this.world, blockPos, blockState, h)) {
								set.add(blockPos);
							}

							m += d * 0.3F;
							n += e * 0.3F;
							o += f * 0.3F;
						}
					}
				}
			}
		}

		this.getAffectedBlocks().addAll(set);
		float q = this.radius;
		int k = MathHelper.floor(this.x - (double)q - 1.0);
		int l = MathHelper.floor(this.x + (double)q + 1.0);
		int r = MathHelper.floor(this.y - (double)q - 1.0);
		int s = MathHelper.floor(this.y + (double)q + 1.0);
		int t = MathHelper.floor(this.z - (double)q - 1.0);
		int u = MathHelper.floor(this.z + (double)q + 1.0);
		List<Entity> list = this.world.getOtherEntities(this.getEntity(), new Box((double)k, (double)r, (double)t, (double)l, (double)s, (double)u));
		Vec3d vec3d = new Vec3d(this.x, this.y, this.z);



		// Calculate the center of the explosion
		Vec3d explosionCenter = new Vec3d(this.x, this.y, this.z);

		for(int v = 0; v < list.size(); ++v) {
			Entity entity = (Entity)list.get(v);
			if (!entity.isImmuneToExplosion()) {
				double distanceRatio = entity.squaredDistanceTo(explosionCenter) / (double) (this.radius * this.radius);

				// Calculate knockback force
				double knockbackForce = 1.0 - Math.min(distanceRatio, 1.0);

				// Get the direction of knockback
				Vec3d knockbackDirection = entity.getPos().subtract(explosionCenter).normalize();

				// Apply knockback force
				Vec3d knockback = knockbackDirection.multiply(knockbackForce * 1.3); // Adjust the multiplier as needed

				// Apply knockback to the entity
				entity.setVelocity(entity.getVelocity().add(knockback));



				double w = Math.sqrt(entity.squaredDistanceTo(vec3d)) / (double)q;
				if (w <= 1.0) {
					double x = entity.getX() - this.x;
					double y = (entity instanceof TntEntity ? entity.getY() : entity.getEyeY()) - this.y;
					double z = entity.getZ() - this.z;
					double aa = Math.sqrt(x * x + y * y + z * z);
					if (aa != 0.0) {
						x /= aa;
						y /= aa;
						z /= aa;
						double ab = (double)getExposure(vec3d, entity);
						double ac = (1.0 - w) * ab;
						entity.damage(this.getDamageSource(), 0.00001f);
						double ad;
						if (entity instanceof LivingEntity livingEntity) {
							ad = ProtectionEnchantment.transformExplosionKnockback(livingEntity, ac);
						} else {
							ad = ac;
						}

						x *= ad;
						y *= ad;
						z *= ad;
						Vec3d vec3d2 = new Vec3d(x, y, z);
						entity.setVelocity(entity.getVelocity().add(vec3d2));
						if (entity instanceof PlayerEntity playerEntity && !playerEntity.isSpectator() && (!playerEntity.isCreative() || !playerEntity.getAbilities().flying)) {
							this.getAffectedPlayers().put(playerEntity, vec3d2);
						}
					}
				}
			}
		}


	}





	public Optional<Float> getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState) {
		return blockState.isAir() && fluidState.isEmpty()
			? Optional.empty()
			: Optional.of(Math.max(blockState.getBlock().getBlastResistance(), fluidState.getBlastResistance()));
	}

	public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
		return true;
	}
}
