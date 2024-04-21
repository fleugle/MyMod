package nikita.uniquescythe.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ChaosMultiToolHoeItem extends MiningToolItem {









	public ChaosMultiToolHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
		super((float)attackDamage, attackSpeed, material, BlockTags.HOE_MINEABLE, settings);
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
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

		ItemStack itemStack = user.getStackInHand(hand);

		ChaosMultiToolItem.changeMultitoolType(itemStack, user, hand);

		return TypedActionResult.success(user.getStackInHand(hand), false);
	}

    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }


}
