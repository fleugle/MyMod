package nikita.uniquescythe.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.items.ModItems;

public class ChaosMultiToolItem extends MiningToolItem {









	public ChaosMultiToolItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
		super((float)attackDamage, attackSpeed, material, BlockTags.PICKAXE_MINEABLE, settings);
	}


	@Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {


        return super.postHit(stack, target, attacker);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {

        return ActionResult.FAIL;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }


	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

		ItemStack itemStack = user.getStackInHand(hand);

		changeMultitoolType(itemStack, user, hand);

		return TypedActionResult.success(user.getStackInHand(hand), false);
	}


	public static void changeMultitoolType(ItemStack itemStack, PlayerEntity user, Hand hand){


		if(itemStack.getItem() == ModItems.CHAOS_MULTITOOL) user.setStackInHand(hand, ModItems.CHAOS_MULTITOOL_AXE.getDefaultStack());
		if(itemStack.getItem() == ModItems.CHAOS_MULTITOOL_AXE) user.setStackInHand(hand, ModItems.CHAOS_MULTITOOL_SHOVEL.getDefaultStack());
		if(itemStack.getItem() == ModItems.CHAOS_MULTITOOL_SHOVEL) user.setStackInHand(hand, ModItems.CHAOS_MULTITOOL_HOE.getDefaultStack());
		if(itemStack.getItem() == ModItems.CHAOS_MULTITOOL_HOE) user.setStackInHand(hand, ModItems.CHAOS_MULTITOOL.getDefaultStack());

	}
}
