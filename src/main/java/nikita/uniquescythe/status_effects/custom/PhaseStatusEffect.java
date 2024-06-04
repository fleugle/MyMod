package nikita.uniquescythe.status_effects.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class PhaseStatusEffect extends StatusEffect {




	public PhaseStatusEffect() {
		super(
			StatusEffectType.BENEFICIAL,
			0xA9FDF3);// 0x is the same as # for rgb color
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
		if (entity instanceof PlayerEntity player && !player.getWorld().isClient) {

			player.setInvulnerable(true);
			player.setInvisible(true);





			//player.addExperience((this.conversionAmount) << amplifier); // Higher amplifier gives you EXP faster

		}
	}


	@Override
	public void onRemoved(LivingEntity entity, net.minecraft.entity.attribute.AttributeContainer attributes, int amplifier) {
		super.onRemoved(entity, attributes, amplifier);

		if (!entity.getWorld().isClient) {
			if(entity instanceof PlayerEntity player){//I can actually just write it like that, defining new variable in check
				ItemStack stackInMainHand = player.getStackInHand(Hand.MAIN_HAND);

				if(player.isInvulnerable()) player.setInvulnerable(false);
				if(player.isInvisible()) player.setInvisible(false);

			}

		}



	}



}
