package nikita.uniquescythe.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import nikita.uniquescythe.utility.GuiltyLevelSystem;
import org.jetbrains.annotations.NotNull;

public class JevilScytheItem extends SwordItem {


    public JevilScytheItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }




	@Override
    public boolean postHit(ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {


		String playerName = attacker.getDisplayName().getString();
		GuiltyLevelSystem.addGuiltyLevelsToPlayer((ServerPlayerEntity) attacker, playerName, 5);

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
