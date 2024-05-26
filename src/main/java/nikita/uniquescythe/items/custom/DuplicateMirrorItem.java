package nikita.uniquescythe.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DuplicateMirrorItem extends Item {

	private final Hand mainHand = Hand.MAIN_HAND;
	private final Hand offHand = Hand.OFF_HAND;



	public DuplicateMirrorItem(Settings settings) {
		super(settings);
	}


	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {


		copyItemInOppositeHand(user, hand);

		return TypedActionResult.success(user.getStackInHand(hand));

	}

	public void copyItemInOppositeHand(PlayerEntity user,Hand hand){

		Hand oppositeHand = hand == Hand.MAIN_HAND ? this.offHand : this.mainHand;
		ItemStack amethystStack = new ItemStack(Items.AMETHYST_SHARD);

		 if (!user.getStackInHand(oppositeHand).isEmpty() && user.getInventory().contains(amethystStack)){

			ItemStack stackInOppositeHand = user.getStackInHand(oppositeHand);

			 // Check if the item stack in the opposite hand is less than the max stack size
			 if (stackInOppositeHand.getCount() < stackInOppositeHand.getMaxCount()/2) {
				 // Increase the amount by 1
				 stackInOppositeHand.increment(stackInOppositeHand.getCount());
			 } else {
				 // Find the first empty slot in the inventory
				 boolean addedToInventory = false;
				 for (int i = 0; i < user.getInventory().size(); i++) {
					 if (user.getInventory().getStack(i).isEmpty()) {
						 user.giveItemStack(stackInOppositeHand);
						 addedToInventory = true;
						 break;
					 }
				 }

				 // If there's no empty slot, you might want to handle this case (e.g., drop the item)
				 if (!addedToInventory) {

					 // Optional: drop the item if no empty slot is found
					 user.dropItem(stackInOppositeHand, false);
				 }
			 }
			 if (!user.getAbilities().creativeMode) {
				 amethystStack.decrement(1);
			 }
		 }

	}


}


