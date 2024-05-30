package nikita.uniquescythe.items.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public abstract class SpecialTalismanItem extends SimpleTalismanItem{
	public SpecialTalismanItem(float attackDamage, float attackSpeed, float movementSpeed, float armor, float addHealth, Settings settings) {
		super(attackDamage, attackSpeed, movementSpeed, armor, addHealth, settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (!world.isClient) {
			if(entity.isPlayer()){
				PlayerEntity player = (PlayerEntity) entity;
				if(player.getStackInHand(Hand.OFF_HAND).getItem() == getCurentItem()){
					player.addStatusEffect(new StatusEffectInstance(getStatusEffect(), 60, 0, false, false,true));
				}
			}
		}
	}

	public StatusEffect getStatusEffect(){
		return null;
	}

	public Item getCurentItem(){
		return null;
	}
}
