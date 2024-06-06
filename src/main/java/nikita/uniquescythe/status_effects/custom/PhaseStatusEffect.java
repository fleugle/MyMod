package nikita.uniquescythe.status_effects.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBind;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.utility.CommandsExecuter;

public class PhaseStatusEffect extends StatusEffect {

	private boolean shouldStayOnADefinedHeight = false;






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
		this.shouldStayOnADefinedHeight = true;

		if (entity instanceof PlayerEntity player && !player.getWorld().isClient) {



			player.setInvulnerable(true);
			player.setInvisible(true);

			keepHeight(player);




			if(entity.isSneaking()){
				CommandsExecuter.executeCommand(player,"effect clear "+ player.getDisplayName().getString() + " uniquescythe:phase");
			}



		}
	}


	@Override
	public void onRemoved(LivingEntity entity, net.minecraft.entity.attribute.AttributeContainer attributes, int amplifier) {
		super.onRemoved(entity, attributes, amplifier);
		this.shouldStayOnADefinedHeight = false;

		if (!entity.getWorld().isClient) {
			if(entity instanceof PlayerEntity player){//I can actually just write it like that, defining new variable in check
				ItemStack stackInMainHand = player.getStackInHand(Hand.MAIN_HAND);

				if(player.isInvulnerable()) player.setInvulnerable(false);
				if(player.isInvisible() && !player.hasStatusEffect(StatusEffects.INVISIBILITY)) player.setInvisible(false);


			}

		}

	}


	private void keepHeight(PlayerEntity player){

		double playerYpos;
		if (this.shouldStayOnADefinedHeight) {
			if (player.getStackInHand(Hand.MAIN_HAND).getItem().equals(ModItems.JOY_BELL)
				||player.getStackInHand(Hand.OFF_HAND).getItem().equals(ModItems.JOY_BELL)) {

				ItemStack stack = player.getStackInHand(Hand.MAIN_HAND).getItem().equals(ModItems.JOY_BELL) ? player.getStackInHand(Hand.MAIN_HAND) : player.getStackInHand(Hand.OFF_HAND);

				playerYpos = stack.getOrCreateNbt().contains("ConstHeight") ? stack.getOrCreateNbt().getInt("ConstHeight") : player.getY();

			}
			else playerYpos = player.getY();

			CommandsExecuter.executeCommand(player, "tp "+player.getDisplayName().getString() + " ~ "+ playerYpos + " ~");
			player.fallDistance = 0;
		}
	}
}
