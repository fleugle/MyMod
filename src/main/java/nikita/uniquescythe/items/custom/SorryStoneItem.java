package nikita.uniquescythe.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.utility.GuiltyLevelSystem;

public class SorryStoneItem extends Item {
	public SorryStoneItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){

		if (!world.isClient){//if the problem is in casting unexpected client side classes to server side ones, the problem is that i haven't split them on 2 different cases: client and server
			GuiltyLevelSystem.subtractGuiltyLevel((ServerPlayerEntity) user, user.getDisplayName().getString(), 5, 5);
			ItemStack stack = user.getStackInHand(hand);
			stack.decrement(1);
			user.kill();

			return TypedActionResult.pass(user.getStackInHand(hand));
		}
		else return TypedActionResult.fail(user.getStackInHand(hand));
	}
}
