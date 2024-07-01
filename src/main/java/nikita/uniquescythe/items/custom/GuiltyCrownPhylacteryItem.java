package nikita.uniquescythe.items.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Equippable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.sounds.ModSoundEvents;

public class GuiltyCrownPhylacteryItem extends PhylacteryBasedItem implements Equippable {

	public GuiltyCrownPhylacteryItem() {


		super(100);
	}

	@Override
	public EquipmentSlot getPreferredSlot() {
		return EquipmentSlot.HEAD;
	}

	@Override
	public SoundEvent getEquipSound() {
		return ModSoundEvents.SOULS_SOUNDS_LOW_PITCH;
	}

	@Override
	public TypedActionResult<ItemStack> use(Item item, World world, PlayerEntity entity, Hand hand) {
		return Equippable.super.use(item, world, entity, hand);
	}
}
