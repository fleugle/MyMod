package nikita.uniquescythe.status_effects.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBind;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import nikita.uniquescythe.datatracker.UltraInvisibilityTracker;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.utility.CommandsExecuter;

import java.util.Map;

public class PhaseStatusEffect extends StatusEffect {

	private boolean shouldStayOnADefinedHeight = false;

	private double xPos = 0;
	private double zPos = 0;
	private double yPos = 0;






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

			UltraInvisibilityTracker.updateUltraInvisibilityStatus((ServerPlayerEntity) player, true);
			player.setInvulnerable(true);
			keepHeight(player);
			if(entity.isSneaking()){
				CommandsExecuter.executeCommand(player,"effect clear "+ player.getDisplayName().getString() + " uniquescythe:phase");
			}

		}
	}


	@Override
	public void onRemoved(LivingEntity entity, net.minecraft.entity.attribute.AttributeContainer attributes, int amplifier) {
		this.xPos = 0;
		this.yPos = 0;
		this.zPos = 0;


		super.onRemoved(entity, attributes, amplifier);
		this.shouldStayOnADefinedHeight = false;

		if (!entity.getWorld().isClient) {
			if(entity instanceof PlayerEntity player){//I can actually just write it like that, defining new variable in check

				UltraInvisibilityTracker.updateUltraInvisibilityStatus((ServerPlayerEntity) player, false);
				if(player.isInvulnerable()) player.setInvulnerable(false);

			}

		}

	}

	@Override
	public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier){
		super.onApplied(entity, attributes, amplifier);

		this.xPos = entity.getX();
		this.zPos = entity.getZ();
		this.yPos = entity.getY();
	}



	private void keepHeight(PlayerEntity player){

		if (this.shouldStayOnADefinedHeight && !player.getWorld().isClient) {

			CommandsExecuter.executeCommandOnServer(player.getWorld(), "tp "+player.getDisplayName().getString() + " "+ xPos + " "+ yPos + " " + this.zPos);
			player.fallDistance = 0;
		}
	}
}
