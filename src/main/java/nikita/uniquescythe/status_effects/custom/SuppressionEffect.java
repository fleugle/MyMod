package nikita.uniquescythe.status_effects.custom;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.PhylacteryBasedItem;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import nikita.uniquescythe.utility.CommandsExecuter;
import nikita.uniquescythe.utility.GuiltyLevelSystem;
import nikita.uniquescythe.utility.RandomEffectUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SuppressionEffect extends StatusEffect {

	public SuppressionEffect() {
		super(
			StatusEffectType.HARMFUL,
			0x009B93);// 0x is the same as # for rgb color
	}

	// This method is called every tick to check whether it should apply the status effect or not
	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		// In our case, we just make it return true so that it applies the status effect every tick.
		return true;
	}

	@Override
	public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
		super.onApplied(entity,attributes,amplifier);

		if(entity instanceof PlayerEntity player){
			if(!entity.getWorld().isClient && player instanceof ServerPlayerEntity serverPlayerEntity){
				GuiltyLevelSystem.addGuiltyLevelsToPlayer(serverPlayerEntity, serverPlayerEntity.getDisplayName().getString(), 1);
			}
		}

	}

	// This method is called when it applies the status effect. We implement custom functionality here.
	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		if (!entity.hasStatusEffect(StatusEffects.RESISTANCE)) {
			if(!entity.getWorld().isClient){
				// Iterate over all status effects and remove only beneficial ones
				for (StatusEffectInstance effectInstance : entity.getStatusEffects()) {
					StatusEffect effect = effectInstance.getEffectType();
					if (effect.isBeneficial()) {
						entity.removeStatusEffect(effect);
					}
				}

			}
			if (entity instanceof PlayerEntity player && !player.getWorld().isClient) {

				List<ItemStack> itemsList = itemStacksFromInventory(player);
				for (ItemStack itemStack : itemsList) {
					player.getItemCooldownManager().set(itemStack.getItem(), 500);
				}
			}
		}
	}

	@Override
	public void onRemoved(LivingEntity entity, net.minecraft.entity.attribute.AttributeContainer attributes, int amplifier) {
		super.onRemoved(entity, attributes, amplifier);

		if (!entity.getWorld().isClient) {
			if(entity instanceof PlayerEntity player){//I can actually just write it like that, defining new variable in check

				if(player instanceof ServerPlayerEntity serverPlayerEntity){
					GuiltyLevelSystem.subtractGuiltyLevel(serverPlayerEntity, serverPlayerEntity.getDisplayName().getString(), 1);
				}

			}

		}


	}


	private List<ItemStack> itemStacksFromInventory(PlayerEntity player){
		List<ItemStack> itemsList = new ArrayList<>();

		// Iterate over the main inventory
		DefaultedList<ItemStack> mainInventory = player.getInventory().main;
		for (ItemStack itemStack : mainInventory) {
			if (!itemStack.isEmpty()) {
				itemsList.add(itemStack);
			}
		}

		// Iterate over the armor inventory
		DefaultedList<ItemStack> armorInventory = player.getInventory().armor;
		for (ItemStack itemStack : armorInventory) {
			if (!itemStack.isEmpty()) {
				itemsList.add(itemStack);
			}
		}

		// Iterate over the offhand inventory
		DefaultedList<ItemStack> offHandInventory = player.getInventory().offHand;
		for (ItemStack itemStack : offHandInventory) {
			if (!itemStack.isEmpty()) {
				itemsList.add(itemStack);
			}
		}

		return itemsList;
	}

}
