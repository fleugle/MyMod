package nikita.uniquescythe.items.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.utility.CommandsExecuter;
import org.jetbrains.annotations.NotNull;

public class DuplicateMirrorItem extends SimplyDescribedItem {

	private final Hand mainHand = Hand.MAIN_HAND;
	private final Hand offHand = Hand.OFF_HAND;


	public static boolean isUsable(ItemStack stack) {
		return stack.getDamage() < stack.getMaxDamage() - 1;
	}

	public DuplicateMirrorItem(Settings settings) {
		super(settings, "§9Needs repair after 10th use");
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient) {
		return ingredient.isOf(ModItems.CHAOS_SHARD);
	}



	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {


		if (isUsable(user.getStackInHand(hand))) {
			copyItemInOppositeHand(user, hand);
			user.getItemCooldownManager().set(this, 25);

			return TypedActionResult.success(user.getStackInHand(hand));
		}
		else return TypedActionResult.fail(user.getStackInHand(hand));

	}

	public void copyItemInOppositeHand(@NotNull PlayerEntity user, Hand hand){

		Hand oppositeHand = hand == Hand.MAIN_HAND ? this.offHand : this.mainHand;
		Hand currentHand = hand == Hand.MAIN_HAND ? this.mainHand : this.offHand;
		ItemStack chaosShardStack = new ItemStack(ModItems.CHAOS_SHARD);

		 if (!user.getStackInHand(oppositeHand).isEmpty() && !user.getWorld().isClient){

			 ItemStack stackInCurrentHand = user.getStackInHand(currentHand);
			 ItemStack stackInOppositeHand = user.getStackInHand(oppositeHand);
			 ItemStack stackGiven = new ItemStack(stackInOppositeHand.getItem().asItem(), stackInOppositeHand.getCount());

			 if (user.getInventory().contains(chaosShardStack) || user.getAbilities().creativeMode ) {
				 if (user.getStackInHand(oppositeHand).getItem() != ModItems.CHAOS_SHARD ) {
					 //user.giveItemStack(stackGiven);
					 String translationKey = stackGiven.getTranslationKey(); // Replace with your actual translation key
					 String[] parts = translationKey.split("\\.");
					 String modId = parts[1]; // Get the second part (mod ID)
					 String itemId = parts[2]; // Get the third part (item ID
					 CommandsExecuter.executeCommand(user, "give "+ user.getDisplayName().getString() +  " " + modId + ":" + itemId +" " + stackGiven.getCount());
					 if (!user.getAbilities().creativeMode) {
						 stackInCurrentHand.damage(1, user, e -> e.sendEquipmentBreakStatus(
							 currentHand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND
						 ));
						 for (int i = 0; i < user.getInventory().size(); i++) {
							 ItemStack inventoryStack = user.getInventory().getStack(i);
							 if (inventoryStack.getItem() == ModItems.CHAOS_SHARD) {
								 inventoryStack.decrement(1); // Decrement the Chaos Shard stack
								 break; // Exit the loop after finding and decrementing
							 }
						 }
					 }
				 }

			 }

		 }

	}



}


