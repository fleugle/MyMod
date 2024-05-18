package nikita.uniquescythe.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nikita.uniquescythe.status_effects.ModStatusEffects;


public class HoneyAppleItem extends Item {


	public HoneyAppleItem(Settings settings) {
		super(settings);

	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

		user.setStatusEffect(new StatusEffectInstance(ModStatusEffects.DELAYED_SATISFACTION), user);
		return this.isFood() ? user.eatFood(world, stack) : stack;

	}


}
