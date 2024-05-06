package nikita.uniquescythe.entities.custom;

import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.particles.ModParticleTypes;
import nikita.uniquescythe.utility.GuiltyLevelSystem;

import static net.minecraft.advancement.criterion.ConstructBeaconCriterion.Conditions.level;

public class BulletEntity extends ThrownItemEntity /*implements GeoEntity*/ {

	//private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

	//private PlayerEntity shooter;

	public BulletEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
		this.setNoGravity(true);
	}

	public BulletEntity(LivingEntity livingEntity, World world/*, PlayerEntity shooter*/) {
		super(ModEntities.BULLET_ENTITY,livingEntity, world);
		//this.shooter = (PlayerEntity) livingEntity;
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
		float damageAmount;

		/*
		if (!entity.getWorld().isClient){
			if (entity.isPlayer()){

				int targetGuiltyLevel = GuiltyLevelSystem
					.getGuiltyLevel(
						(ServerPlayerEntity) entity,
						entity.getDisplayName().getString(),
						"PersistentGuiltyLevel");



				int shooterGuiltyLevel = GuiltyLevelSystem
					.getGuiltyLevel(
						(ServerPlayerEntity) shooter,
						entity.getDisplayName().getString(),
						"PersistentGuiltyLevel");

				damageAmount = (float) targetGuiltyLevel - shooterGuiltyLevel;
			}
			else damageAmount = 8;
			entity.damage(this.getDamageSources().thrown(this, this.getOwner()), damageAmount);
		}

		 */


		if(!getWorld().isClient()){
			World world = this.getWorld();
			if (world instanceof ServerWorld serverWorld) {

				// Spawn smoke particles in a radius of 2 blocks
				serverWorld.spawnParticles(ModParticleTypes.WIND_EXPLOSION,
					getPos().getX()  + 0.5,
					getPos().getY()  + 0.5,
					getPos().getZ()  + 0.5,
					8, 1, 1, 1, 1);
			}



		}


		this.discard();
	}






	/*

	//AZURELIB CODE PART

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "idle", 0, event ->
		{
			return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
		}));
	}





	 */




}
