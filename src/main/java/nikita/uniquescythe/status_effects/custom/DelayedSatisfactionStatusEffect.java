package nikita.uniquescythe.status_effects.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

public class DelayedSatisfactionStatusEffect extends StatusEffect {
	private int countDownToEffect = 0;

	public DelayedSatisfactionStatusEffect() {
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

			if (this.countDownToEffect == 199/* = 10 seconds */){
				entity.heal((float)Math.max(4 << amplifier, 0));
				this.countDownToEffect = 0;
			}
			else this.countDownToEffect++;
		}
	}


}
