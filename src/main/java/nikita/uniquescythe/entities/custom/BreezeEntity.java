package nikita.uniquescythe.entities.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.lwjgl.system.SharedLibrary;

public class BreezeEntity extends HostileEntity implements RangedAttackMob {

	public final AnimationState idleState = new AnimationState();
	public final AnimationState attackState = new AnimationState();
	public final AnimationState triggerState = new AnimationState();
	public final AnimationState sprintState = new AnimationState();


	private int idleAnimationTimeout = 0;
	private int attackAnimationTimeout = 0;
	private int triggerAnimationTimeout = 0;
	private int sprintAnimationTimeout = 0;

	private boolean isAttacking;
	private boolean isTriggering;
	private boolean isSprinting;

	private void setupAnimationStates() {

		if(isAttacking && !isSprinting){
			idleAnimationTimeout =0;

		}

		if (this.idleAnimationTimeout <= 0) {
			this.idleAnimationTimeout = 29;
			this.idleState.restart(this.age);
		} else {
			--this.idleAnimationTimeout;
		}
	}

	public BreezeEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 8.0F);
		this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 8.0F);
		this.experiencePoints = 10;
	}

	@Override
	public void tick() {
		super.tick();
		setupAnimationStates();
	}




	@Override
	protected void initGoals(){
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(3, new ProjectileAttackGoal(this, 0.3f,15,6));
		this.goalSelector.add(5, new GoToWalkTargetGoal(this, 0.3f));
		this.goalSelector.add(7, new WanderAroundFarGoal(this, 0.3f, 0.0F));
		this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(8, new LookAroundGoal(this));
		this.targetSelector.add(1, new RevengeGoal(this).setGroupRevenge());
		this.targetSelector.add(2, new TargetGoal(this, PlayerEntity.class, true));
	}




	public static DefaultAttributeContainer.Builder createBreezeAttributes(){
		return MobEntity.createAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 35)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1)
			.add(EntityAttributes.GENERIC_FLYING_SPEED, 1)
			.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0);
	}

	@Override
	public void attack(LivingEntity target, float pullProgress) {

	}
}
