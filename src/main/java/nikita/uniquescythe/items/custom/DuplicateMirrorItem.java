package nikita.uniquescythe.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.items.ModItems;

public class DuplicateMirrorItem extends Item {

	private final Hand mainHand = Hand.MAIN_HAND;
	private final Hand offHand = Hand.OFF_HAND;



	public DuplicateMirrorItem(Settings settings) {
		super(settings);
	}


	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {


		copyItemInOppositeHand(user, hand);
		user.getItemCooldownManager().set(this, 25);

		return TypedActionResult.success(user.getStackInHand(hand));

	}

	public void copyItemInOppositeHand(PlayerEntity user,Hand hand){

		Hand oppositeHand = hand == Hand.MAIN_HAND ? this.offHand : this.mainHand;
		ItemStack chaosShardStack = new ItemStack(ModItems.CHAOS_SHARD);

		 if (!user.getStackInHand(oppositeHand).isEmpty() && !user.getWorld().isClient){

			 ItemStack stackInOppositeHand = user.getStackInHand(oppositeHand);
			 ItemStack stackGiven = new ItemStack(stackInOppositeHand.getItem().asItem(), stackInOppositeHand.getCount());

			 if (user.getInventory().contains(chaosShardStack) || user.getAbilities().creativeMode ) {
				 if (user.getStackInHand(oppositeHand).getItem() != ModItems.CHAOS_SHARD) {
					 user.giveItemStack(stackGiven);
				 }
				 if (!user.getAbilities().creativeMode) {
					 chaosShardStack.decrement(1);
				 }
			 }

		 }

	}


}


