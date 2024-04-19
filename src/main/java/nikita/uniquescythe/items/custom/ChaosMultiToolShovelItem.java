package nikita.uniquescythe.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class ChaosMultiToolShovelItem extends MiningToolItem {





	public ChaosMultiToolShovelItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
		super((float)attackDamage, attackSpeed, material, BlockTags.SHOVEL_MINEABLE, settings);
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
}
