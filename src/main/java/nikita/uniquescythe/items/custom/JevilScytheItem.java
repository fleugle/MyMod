package nikita.uniquescythe.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.function.CommandFunctionManager;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import nikita.uniquescythe.utility.CommandsExecuter;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class JevilScytheItem extends SwordItem {


    public JevilScytheItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }




	@Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

		CommandsExecuter.executeCommand(attacker, "function uniquescythe:give_diamond");
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
