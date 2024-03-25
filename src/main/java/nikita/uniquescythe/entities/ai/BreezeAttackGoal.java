package nikita.uniquescythe.entities.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import nikita.uniquescythe.entities.custom.BreezeEntity;

public class BreezeAttackGoal extends ProjectileAttackGoal {

	private final BreezeEntity entity;
	private int attackDelay = 20;
	private int ticksUntilNextAttack = 20;
	private boolean shouldCountTillNextAttack = false;

	private float shootRange;


	public BreezeAttackGoal(RangedAttackMob mob, double mobSpeed, int intervalTicks, float maxShootRange) {
		super(mob, mobSpeed, intervalTicks, maxShootRange);
		entity = ((BreezeEntity) mob);
		this.shootRange = maxShootRange;
	}

	@Override
	public void start(){
		super.start();

		attackDelay = 18;
		ticksUntilNextAttack = 12;


	}


	/*
	// Has additional paramter
	@Override
	protected void attack(LivingEntity pEnemy, double pDistToEnemySqr) {
		if (isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
			shouldCountTillNextAttack = true;

			if(isTimeToStartAttackAnimation()) {
				entity.setAttacking(true);
			}

			if(isTimeToAttack()) {
				this.mob.getLookControl().lookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
				performAttack(pEnemy);
			}
		} else {
			resetAttackCooldown();
			shouldCountTillNextAttack = false;
			entity.setAttacking(false);
			entity.attackAnimationTimeout = 0;
		}
	}

	 */



	private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy){
		return this.entity.distanceTo(pEnemy) <= this.shootRange;
	}

	protected void resetAttackCooldown(){
		this.ticksUntilNextAttack = this.getTickCount(30);
	}


	protected boolean isTimeToStartAttackAnimation(){
		return this.ticksUntilNextAttack <=attackDelay;
	}

	protected boolean isTimeToAttack() {
		return this.ticksUntilNextAttack <= 0;
	}

	protected void performAttack(LivingEntity pEnemy) {
		this.resetAttackCooldown();
		//this.entity.getLookControl().lookAt(pEnemy.getX(),pEnemy.getY(),pEnemy.getZ());

		this.entity.tryAttack(pEnemy);
	}

	@Override
	public void tick(){
		super.tick();

		if(shouldCountTillNextAttack){
			this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack -1, 0);
		}

	}


	@Override
	public void stop(){
		entity.setAttacking(false);
		super.stop();
	}

}
