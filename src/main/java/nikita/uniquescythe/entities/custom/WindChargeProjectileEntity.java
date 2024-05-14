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
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import nikita.uniquescythe.utility.WindExplosion;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticleTypes;
import nikita.uniquescythe.sounds.ModSounds;



public class WindChargeProjectileEntity extends ThrownItemEntity implements GeoEntity {

	private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);


	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
		controllerRegistrar.add(new AnimationController<>(this, "controllerName", 0, event ->
		{
			return event.setAndContinue(RawAnimation.begin()
				.thenLoop("idle"));
		}));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}














	public WindChargeProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
		this.setNoGravity(true);
	}

	public WindChargeProjectileEntity(LivingEntity livingEntity, World world) {
		super(ModEntities.WIND_CHARGE_PROJECTILE,livingEntity, world);
		this.setNoGravity(true);
	}













	@Override
	public float getTargetingMargin() {
		return 1.0F;
	}


	//this.idleState.restart(this.age);
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
		return ModItems.WIND_CHARGE;
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
				if (world instanceof ServerWorld) {
					ServerWorld serverWorld = (ServerWorld) world;

					// Spawn smoke particles in a radius of 2 blocks
					serverWorld.spawnParticles(ModParticleTypes.WIND_EXPLOSION,
						getPos().getX()  + 0.5,
						getPos().getY()  + 0.5,
						getPos().getZ()  + 0.5,
						8, 1, 1, 1, 1);
				}
				float explosionSize = 3f;
				this.getWorld().sendEntityStatus(this, (byte) 3);
				WindExplosion explosion = new WindExplosion(getWorld(), null, getPos().getX(), getPos().getY(), getPos().getZ(), explosionSize);
				explosion.collectBlocksAndDamageEntities();

				//sound on block collision
				getWorld().playSound(
					null,
					getPos().getX(),
					getPos().getY(),
					getPos().getZ(),
					ModSounds.WIND_CHARGE_BURST,
					SoundCategory.NEUTRAL,
					1F,
					0.4F / (getWorld().getRandom().nextFloat() * 0.4F + 0.8F)
				);
			}
			this.discard();
		}
	}


	@Override
	public boolean collides() {
		return true;
	}







}



