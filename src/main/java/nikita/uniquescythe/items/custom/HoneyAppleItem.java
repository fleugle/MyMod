package nikita.uniquescythe.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import nikita.uniquescythe.status_effects.ModStatusEffects;


public class HoneyAppleItem extends Item {


	private static final String HONEY_LEVEL_TAG = "HoneyLevel";


	public HoneyAppleItem(Settings settings) {
		super(settings);

	}

	private int getHoneyLevelState(ItemStack stack) {
		NbtCompound tag = stack.getOrCreateNbt();
		return tag.contains(HONEY_LEVEL_TAG) ? tag.getInt(HONEY_LEVEL_TAG) : 0;
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {


		switch (getHoneyLevelState(stack)){
			case 0:
				user.setStatusEffect(new StatusEffectInstance(ModStatusEffects.DELAYED_SATISFACTION, 300,0), user);
				break;
			case 1:
				user.setStatusEffect(new StatusEffectInstance(ModStatusEffects.DELAYED_SATISFACTION, 900,1), user);
				break;
			case 2:
				user.setStatusEffect(new StatusEffectInstance(ModStatusEffects.DELAYED_SATISFACTION, 1700,2), user);
				break;
		}


		return this.isFood() ? user.eatFood(world, stack) : stack;

	}


}
