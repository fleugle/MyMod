package nikita.uniquescythe.items.custom;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import nikita.uniquescythe.items.ModItems;

public class SneakyTalismanItem extends SpecialTalismanItem{

	public SneakyTalismanItem(float attackDamage, float attackSpeed, float movementSpeed, float armor, float addHealth, Settings settings) {
		super(attackDamage, attackSpeed, movementSpeed, armor, addHealth, settings);
	}

	@Override
	public StatusEffect getStatusEffect(){
		return StatusEffects.INVISIBILITY;
	}

	@Override
	public Item getCurentItem(){
		return ModItems.LEONS_TALISMAN;
	}

}
