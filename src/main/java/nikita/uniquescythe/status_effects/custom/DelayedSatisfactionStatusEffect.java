package nikita.uniquescythe.status_effects.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

public class DelayedSatisfactionStatusEffect extends StatusEffect {
	protected DelayedSatisfactionStatusEffect() {
		super(
			StatusEffectType.BENEFICIAL,
			0xFE7A38);// 0x is the same as # for rgb color
	}

	// This method is called every tick to check whether it should apply the status effect or not
	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		// In our case, we just make it return true so that it applies the status effect every tick.
		return true;
	}

	// This method is called when it applies the status effect. We implement custom functionality here.
	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity) {
			((PlayerEntity) entity).addExperience(1 << amplifier); // Higher amplifier gives you EXP faster
		}
	}


}
