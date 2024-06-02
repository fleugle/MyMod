package nikita.uniquescythe.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.utility.CommandsExecuter;

public class JoyBellItem extends SimplyDescribedItem{

	public JoyBellItem(Settings settings) {
		super(settings, "Brings Joy & Chaos :)");
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if(!world.isClient){
			ItemStack stack = user.getStackInHand(hand);

			if (user.getStackInHand(Hand.MAIN_HAND) == stack) {
				CommandsExecuter.executeCommand(user, "tp "+ user.getDisplayName().getString() + " ^ ^ ^10");

			}
			else if (user.getStackInHand(Hand.OFF_HAND) == stack) {

			}

			user.getItemCooldownManager().set(this, 35);

		}


		return TypedActionResult.pass(user.getStackInHand(hand));
	}

}
