package nikita.uniquescythe.entities.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.world.World;

public class BulletEntity extends ExplosiveProjectileEntity {

	public static final TrackedData<Float> INITIAL_SPEED = DataTracker.registerData(BulletEntity.class, TrackedDataHandlerRegistry.FLOAT);

	public static final double GRAVITY = 0.05;
	public static final double AIR_FRICTION = 0.99;
	public static final double WATER_FRICTION = 0.6;
	public static final short LIFETIME = 50;

	public static double maxDistance;

	public float damageMultiplier;
	public float distanceTravelled;
	public short tickCounter;


	protected BulletEntity(EntityType<? extends ExplosiveProjectileEntity> entityType, World world) {
		super(entityType, world);
	}

	/*
	public BulletEntity(World world) {
		this(MusketMod.BULLET_ENTITY_TYPE, world);
	}

	 */

	public boolean isFirstTick() {
		return tickCounter == 0;
	}

	/*
	public DamageSource causeMusketDamage(BulletEntity bullet, Entity attacker) {
		return (new IndirectEntityDamageSource("musket", bullet, attacker)).setProjectile();
	}

	 */




}
