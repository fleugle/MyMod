package nikita.uniquescythe.items.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.sounds.ModSoundEvents;
import nikita.uniquescythe.status_effects.ModStatusEffects;
import nikita.uniquescythe.utility.CommandsExecuter;
import nikita.uniquescythe.utility.SoundsManager;

public class JoyBellItem extends SimplyDescribedItem{

	public JoyBellItem(Settings settings) {
		super(settings, "§9Brings Joy & Chaos :)");
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if(!world.isClient){
			CommandsExecuter.executeCommand(user, "particle uniquescythe:bell_use ~ ~1 ~");
			ItemStack stack = user.getStackInHand(hand);
			NbtCompound tag = stack.getOrCreateNbt();
			tag.putDouble("Y", user.getY());
			tag.putDouble("X", user.getX());
			tag.putDouble("Z", user.getZ());

			SoundsManager.playPlayersSoundFromPlayer(user, ModSoundEvents.DING1, 1);



			if (user.getStackInHand(Hand.MAIN_HAND) == stack) {
				CommandsExecuter.executeCommand(user, "tp "+ user.getDisplayName().getString() + " ^ ^ ^10");
				user.getItemCooldownManager().set(this, 35);
				tag.putInt("delay", 5);
				tag.putBoolean("shouldWait", true);

				//CommandsExecuter.executeCommand(user, "particle uniquescythe:bell_use ~ ~1 ~");

			}
			else if (user.getStackInHand(Hand.OFF_HAND) == stack) {
				user.setStatusEffect(new StatusEffectInstance(
					ModStatusEffects.PHASE,
					300,
					0,
					false,
					false,
					true
				), user);
				user.getItemCooldownManager().set(this, 335);
				tag.putInt("delay", 5);
				tag.putBoolean("shouldWait", true);


			}

		}


		return TypedActionResult.success(user.getStackInHand(hand));
	}


	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		NbtCompound tag = stack.getOrCreateNbt();
		if (tag.getBoolean("shouldWait")) {
			if(entity instanceof PlayerEntity player && !world.isClient){
				if(tag.getInt("delay") > 0){
					int delay = tag.getInt("delay");
					if (delay == 1) {
						CommandsExecuter.executeCommand(player, "particle uniquescythe:bell_use ~ ~1 ~");
						SoundsManager.playPlayersSoundFromPlayer(player, ModSoundEvents.DING2, 1);
						tag.putInt("delay", 0);
						tag.putBoolean("shouldWait", false);
					}
					else {
						delay--;
						tag.putInt("delay", delay);
					}
				}
			}
		}
	}


	@Override
	public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
		return false;
	}
}
