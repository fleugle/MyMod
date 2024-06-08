package nikita.uniquescythe.items.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import nikita.uniquescythe.utility.CommandsExecuter;

public class JoyBellItem extends SimplyDescribedItem{

	public JoyBellItem(Settings settings) {
		super(settings, "§9Brings Joy & Chaos :)");
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if(!world.isClient){
			ItemStack stack = user.getStackInHand(hand);
			NbtCompound tag = stack.getOrCreateNbt();


			if (user.getStackInHand(Hand.MAIN_HAND) == stack) {
				CommandsExecuter.executeCommand(user, "tp "+ user.getDisplayName().getString() + " ^ ^ ^10");
				user.getItemCooldownManager().set(this, 35);

			}
			else if (user.getStackInHand(Hand.OFF_HAND) == stack) {
				user.setStatusEffect(new StatusEffectInstance(ModStatusEffects.PHASE, 300, 0,false,false,true), user);
				user.getItemCooldownManager().set(this, 335);
			}

			tag.putInt("ConstHeight", (int) user.getY());


		}


		return TypedActionResult.success(user.getStackInHand(hand));
	}



}
