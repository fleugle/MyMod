package nikita.uniquescythe.entities.custom;

import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animatable.instance.SingletonAnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticleTypes;
import nikita.uniquescythe.utility.GuiltyLevelSystem;

public class JusticeBulletEntity extends ThrownItemEntity implements GeoEntity {

	private LivingEntity shooter;

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


























	public JusticeBulletEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
		this.setNoGravity(true);
	}

	public JusticeBulletEntity(LivingEntity livingEntity, World world/*, PlayerEntity shooter*/) {
		super(ModEntities.JUSTICE_BULLET_ENTITY,livingEntity, world);
		this.shooter = livingEntity;
		this.setNoGravity(true);

	}






	public void setBulletProperties(Entity user, float pitch, float yaw, float roll, float modifierZ, float modifierXYZ) {
		float f = -MathHelper.sin(yaw * (float) (Math.PI / 180.0)) * MathHelper.cos(pitch * (float) (Math.PI / 180.0));
		float g = -MathHelper.sin((pitch + roll) * (float) (Math.PI / 180.0));
		float h = MathHelper.cos(yaw * (float) (Math.PI / 180.0)) * MathHelper.cos(pitch * (float) (Math.PI / 180.0));
		this.setVelocity((double)f, (double)g, (double)h, modifierZ, modifierXYZ);
		Vec3d vec3d = user.getVelocity();
		this.setVelocity(this.getVelocity().add(vec3d.x, user.isOnGround() ? 0.0 : vec3d.y, vec3d.z));
	}




	@Override
	protected boolean canHit(Entity entity) {
		return super.canHit(entity) ;
	}

	@Override
	public float getTargetingMargin() {
		return 1.0F;
	}


	//this.idleState.restart(this.age);




	@Override
	protected Item getDefaultItem() {
		return ModItems.BULLET;
	}



	@Override
	public Packet<ClientPlayPacketListener> createSpawnPacket() {
		return new EntitySpawnS2CPacket(this);
	}


	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {

		super.onEntityHit(entityHitResult);
		Entity entity = entityHitResult.getEntity();


		if (shooter != null) {
			if (!shooter.getWorld().isClient){
				float damageAmount = 0;

				int shooterGuiltyLevel = GuiltyLevelSystem.getGuiltyLevel(
					(ServerPlayerEntity) shooter,
					shooter.getDisplayName().getString(),
					"PersistentGuiltyLevel"
				);
				if (entity.isPlayer()) {



					if (entity instanceof PlayerEntity) {
						int targetGuiltyLevel = GuiltyLevelSystem.getGuiltyLevel(
							(ServerPlayerEntity) entity,
							entity.getDisplayName().getString(),
							"PersistentGuiltyLevel"
						);


						damageAmount = (float) targetGuiltyLevel - shooterGuiltyLevel;
					}
				}
				else if (entity instanceof HostileEntity) {

					damageAmount = 50f - shooterGuiltyLevel;

				} else {
					damageAmount = 8f - shooterGuiltyLevel;
				}


				if (damageAmount < 0) {
					damageAmount = 0 - damageAmount;
					shooter.damage(shooter.getDamageSources().magic(), damageAmount);
				}
				else entity.damage(this.getDamageSources().thrown(this, this.getOwner()), damageAmount);
			}
		}


		if(!getWorld().isClient()){
			World world = this.getWorld();
			if (world instanceof ServerWorld serverWorld) {

				// Spawn smoke particles in a radius of 2 blocks
				serverWorld.spawnParticles(ModParticleTypes.WIND_EXPLOSION,
					shooter.getX()  + 0.5,
					shooter.getY()  + 0.5,
					shooter.getZ()  + 0.5,
					8, 1, 1, 1, 1);
			}



		}


		this.discard();
	}




}
