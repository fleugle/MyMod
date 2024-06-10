package nikita.uniquescythe.items.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.networking.FrostyScytheTracker;
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
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(!world.isClient && entity instanceof LivingEntity livingEntity){
			if (livingEntity.getStackInHand(Hand.MAIN_HAND).getItem().equals(ModItems.FROSTY_SCYTHE)
			&& livingEntity instanceof ServerPlayerEntity serverPlayerEntity){
				FrostyScytheTracker.updateFrostyScytheStatus(serverPlayerEntity, true);
			}
			else FrostyScytheTracker.updateFrostyScytheStatus((ServerPlayerEntity) entity, false);
		}
	}


    @Override
    public boolean hasGlint(ItemStack stack) {
        return getIceCageChargeState(stack) == 7;
    }
}
