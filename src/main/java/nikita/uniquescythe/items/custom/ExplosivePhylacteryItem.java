package nikita.uniquescythe.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ExplosivePhylacteryItem extends PhylacteryBasedItem{

	public ExplosivePhylacteryItem(Settings settings) {
		super(settings, 50);
	}


	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {



		return TypedActionResult.success(user.getStackInHand(hand));
	}

}
