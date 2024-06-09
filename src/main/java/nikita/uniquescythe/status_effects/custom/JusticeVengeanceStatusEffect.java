package nikita.uniquescythe.status_effects.custom;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import nikita.uniquescythe.utility.CommandsExecuter;
import nikita.uniquescythe.utility.GuiltyLevelSystem;

public class JusticeVengeanceStatusEffect extends StatusEffect {

	public int conversionAmount = 1;

	public boolean hitNotDetected = true;

	public int maxAmount = 1;

	public double lifestealAmount = 0;



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
			player.addExperience((this.conversionAmount - 1) << amplifier); // Higher amplifier gives you EXP faster
		}
	}


	@Override
	public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
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


						if (entity instanceof LivingEntity livingEntityTarget && !livingEntityTarget.hasStatusEffect(ModStatusEffects.JUSTICE_VENGEANCE)) {
							if(entity instanceof ServerPlayerEntity serverTarget
								&& player instanceof ServerPlayerEntity serverPlayerEntity){
								int targetsGuilty = GuiltyLevelSystem.getGuiltyLevel(serverTarget, serverTarget.getDisplayName().getString(), GuiltyLevelSystem.PERSISTENT_GUILTY_LEVEL);

								int attackersGuilty = GuiltyLevelSystem.getGuiltyLevel(serverPlayerEntity, serverPlayerEntity.getDisplayName().getString(), GuiltyLevelSystem.PERSISTENT_GUILTY_LEVEL);

								int result = attackersGuilty - targetsGuilty;

								//lifesteal needs change. ?or not?
								if(result >= 0 ){
									this.lifestealAmount = 0;
								}
								else {
									this.lifestealAmount = serverPlayerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
									if(serverPlayerEntity.isAlive()
										&& !serverPlayerEntity.isSpectator()
										&& !serverTarget.isSpectator()
										&& serverTarget.isAlive()
										&& serverTarget.isAttackable()
									) serverPlayerEntity.heal( (float) this.lifestealAmount * 0.12f * (float) -(result));
								}

							}
							else if (player instanceof ServerPlayerEntity serverPlayerEntity) {
								int attackersGuilty = GuiltyLevelSystem.getGuiltyLevel(serverPlayerEntity, serverPlayerEntity.getDisplayName().getString(), GuiltyLevelSystem.PERSISTENT_GUILTY_LEVEL);
								int result;
								if(entity instanceof HostileEntity) {

									result = attackersGuilty - 50;
								}
								else {

									result = attackersGuilty - 8;
								}
								this.lifestealAmount = result >= 0 ? 0 : serverPlayerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
								if(serverPlayerEntity.isAlive()
									&& !serverPlayerEntity.isSpectator()
									&& !entity.isSpectator()
									&& entity.isAlive()
									&& entity.isAttackable()
								) serverPlayerEntity.heal( (float) this.lifestealAmount * 0.12f * (float) -(result));
							}
						}




					}
					this.hitNotDetected = false;
				}

				return ActionResult.PASS;
			});
		}

	}



}
