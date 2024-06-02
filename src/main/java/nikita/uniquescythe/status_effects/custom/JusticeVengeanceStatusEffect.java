package nikita.uniquescythe.status_effects.custom;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.sounds.ModSoundEvents;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import nikita.uniquescythe.utility.CommandsExecuter;
import nikita.uniquescythe.utility.KarmaSystem;
import nikita.uniquescythe.utility.SoundsManager;

public class JusticeVengeanceStatusEffect extends StatusEffect {

	public int conversionAmount = 1;

	public boolean hitNotDetected = true;

	public int maxAmount = 1;


	public JusticeVengeanceStatusEffect() {
		super(
			StatusEffectType.BENEFICIAL,
			0xEC8C06);// 0x is the same as # for rgb color
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
			this.hitNotDetected = true;
			registerHitAttempt();
			player.addExperience((this.conversionAmount) << amplifier); // Higher amplifier gives you EXP faster
		}
	}


	@Override
	public void onRemoved(LivingEntity entity, net.minecraft.entity.attribute.AttributeContainer attributes, int amplifier) {
		super.onRemoved(entity, attributes, amplifier);

		if (!entity.getWorld().isClient) {
			if(entity instanceof PlayerEntity player){//I can actually just write it like that, defining new variable in check
				ItemStack stackInMainHand = player.getStackInHand(Hand.MAIN_HAND);
				this.maxAmount = stackInMainHand.getCount();

				if(this.conversionAmount > this.maxAmount){
					this.conversionAmount = this.maxAmount;
				}

				if(stackInMainHand.getItem() == Items.AMETHYST_SHARD){

					stackInMainHand.decrement(conversionAmount);
					CommandsExecuter.executeCommand(entity, "give "+ player.getDisplayName().getString() +  " " + UniqueScythe.MOD_ID + ":" + "justice_shard" +" " + conversionAmount);

				}

			}

		}


		this.conversionAmount = 1;

		this.hitNotDetected = true;

		this.maxAmount = 1;
	}

	private void registerHitAttempt(){
		if (this.hitNotDetected) {
			AttackEntityCallback.EVENT.register((player, world, hand, entity, entityHitResult) -> {
				if (!world.isClient && player.hasStatusEffect(ModStatusEffects.JUSTICE_VENGEANCE)) {
					if (this.hitNotDetected) {
						this.conversionAmount += 1;
					}
					this.hitNotDetected = false;
				}

				return ActionResult.PASS;
			});
		}

	}

}
