package nikita.uniquescythe.entities.custom;

import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animatable.instance.SingletonAnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticleTypes;
import nikita.uniquescythe.sounds.ModSoundEvents;
import nikita.uniquescythe.utility.SoundsManager;
import nikita.uniquescythe.utility.WindExplosion;


public class ExplosiveFireballProjectileEntity extends ThrownItemEntity implements GeoEntity {

	private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);


	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
		controllerRegistrar.add(new AnimationController<>(this, "explosiveFireballController", 0, event ->
		{
			return event.setAndContinue(RawAnimation.begin()
				.thenLoop("idle"));
		}));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}














	public ExplosiveFireballProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
		this.setNoGravity(true);
	}

	public ExplosiveFireballProjectileEntity(LivingEntity livingEntity, World world) {
		super(ModEntities.EXPLOSIVE_FIREBALL_PROJECTILE,livingEntity, world);
		this.setNoGravity(true);
	}













	@Override
	public float getTargetingMargin() {
		return 1.0F;
	}

	@Override
	public void tick() {
		this.baseTick();



		HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
		boolean bl = false;
		if (hitResult.getType() == HitResult.Type.BLOCK) {
			BlockPos blockPos = ((BlockHitResult)hitResult).getBlockPos();
			BlockState blockState = this.getWorld().getBlockState(blockPos);
			if (blockState.isOf(Blocks.NETHER_PORTAL)) {
				this.setInNetherPortal(blockPos);
				bl = true;
			} else if (blockState.isOf(Blocks.END_GATEWAY)) {
				BlockEntity blockEntity = this.getWorld().getBlockEntity(blockPos);
				if (blockEntity instanceof EndGatewayBlockEntity && EndGatewayBlockEntity.canTeleport(this)) {
					EndGatewayBlockEntity.tryTeleportingEntity(this.getWorld(), blockPos, blockState, this, (EndGatewayBlockEntity)blockEntity);
				}

				bl = true;
			}
		}

		if (hitResult.getType() != HitResult.Type.MISS && !bl) {
			this.onCollision(hitResult);
		}

		this.checkBlockCollision();
		Vec3d vec3d = this.getVelocity();
		double d = this.getX() + vec3d.x;
		double e = this.getY() + vec3d.y;
		double f = this.getZ() + vec3d.z;
		this.updateRotation();
		float h = 1;

		this.setVelocity(vec3d.multiply((double)h));
		if (!this.hasNoGravity()) {
			Vec3d vec3d2 = this.getVelocity();
			this.setVelocity(vec3d2.x, vec3d2.y - (double)this.getGravity(), vec3d2.z);
		}

		this.setPosition(d, e, f);
	}

	@Override
	protected Item getDefaultItem() {
		return null;
	}

	@Override
	public Packet<ClientPlayPacketListener> createSpawnPacket() {
		return new EntitySpawnS2CPacket(this);
	}

	@Override
	protected void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		if (!this.getWorld().isClient) {
			if(!getWorld().isClient()){
				World world = this.getWorld();

				if (world instanceof ServerWorld serverWorld) {

					// Spawn smoke particles in a radius of 2 blocks
					serverWorld.spawnParticles(ParticleTypes.EXPLOSION,
						getPos().getX()  + 0.5,
						getPos().getY()  + 0.5,
						getPos().getZ()  + 0.5,
						8, 2, 2, 2, 0);

					// Spawn smoke particles in a radius of 2 blocks
					serverWorld.spawnParticles(ParticleTypes.FLAME,
						getPos().getX()  + 0.5,
						getPos().getY()  + 0.5,
						getPos().getZ()  + 0.5,
						8, 1, 1, 1, 0);
				}

				float explosionSize = 10f;
				/*this.getWorld().sendEntityStatus(this, (byte) 3);
				Explosion explosion = new Explosion(
					getWorld(),
					this ,
					getPos().getX(),
					getPos().getY(),
					getPos().getZ(),
					explosionSize,
					false,
					Explosion.DestructionType.DESTROY

				);
				explosion.collectBlocksAndDamageEntities();*/

				this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)explosionSize, World.ExplosionSourceType.MOB);

				//sound on block collision
				SoundsManager.playNeutralSoundOnSpot(this, ModSoundEvents.WIND_CHARGE_BURST, 1, 1);
			}
			this.discard();
		}
	}

	@Override
	public boolean collides() {
		return true;
	}

}



