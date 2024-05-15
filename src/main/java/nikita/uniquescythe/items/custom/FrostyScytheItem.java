package nikita.uniquescythe.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import nikita.uniquescythe.sounds.ModSoundEvents;
import net.minecraft.nbt.NbtCompound;
import nikita.uniquescythe.utility.SoundsManager;

public class FrostyScytheItem extends SwordItem {

    private static final String ICE_CAGE_CHARGE_STATE_TAG = "IceCageChargeState";

    public FrostyScytheItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    private int getIceCageChargeState(ItemStack stack) {
        NbtCompound tag = stack.getOrCreateNbt();
        return tag.contains(ICE_CAGE_CHARGE_STATE_TAG) ? tag.getInt(ICE_CAGE_CHARGE_STATE_TAG) : 0;
    }

    private void setIceCageChargeState(ItemStack stack, int chargeState) {
        NbtCompound tag = stack.getOrCreateNbt();
        tag.putInt(ICE_CAGE_CHARGE_STATE_TAG, chargeState);
    }

    private void removeIceCageChargeState(ItemStack stack) {
        NbtCompound tag = stack.getOrCreateNbt();
        tag.remove(ICE_CAGE_CHARGE_STATE_TAG);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int chargeState = getIceCageChargeState(stack);
        chargeState += 1;
        setIceCageChargeState(stack, chargeState);

        if (chargeState == 8) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 100));

			SoundsManager.playPlayersSoundFromPlayer(attacker, ModSoundEvents.SCYTHE_HIT, 5f);

            target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 400, 1));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 2));
            removeIceCageChargeState(stack); // Reset the charge state
        }

        if (chargeState == 7) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 400, 1));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 400, 1));
            SoundsManager.playPlayersSoundFromPlayer(attacker, ModSoundEvents.SCYTHE_CHARGED, 5f);
        }

        return super.postHit(stack, target, attacker);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {

        return ActionResult.FAIL;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return getIceCageChargeState(stack) == 7;
    }
}
