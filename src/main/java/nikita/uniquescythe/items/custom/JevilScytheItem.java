package nikita.uniquescythe.items.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import nikita.uniquescythe.sounds.ModSounds;

public class JevilScytheItem extends SwordItem {



    public JevilScytheItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
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
