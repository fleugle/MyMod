package nikita.uniquescythe.entities.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.lwjgl.system.SharedLibrary;

public class BreezeEntity extends MobEntity {
	public BreezeEntity(EntityType<? extends MobEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void initGoals(){
		this.goalSelector.add(0, new SwimGoal(this));
		//this.goalSelector.add(1, new AttackGoal(this));
	}


	public static DefaultAttributeContainer.Builder createBreezeAttributes(){
		return MobEntity.createAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 25)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1)
			.add(EntityAttributes.GENERIC_FLYING_SPEED, 1)
			.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 25);
	}

}
