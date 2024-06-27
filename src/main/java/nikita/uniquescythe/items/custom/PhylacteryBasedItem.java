package nikita.uniquescythe.items.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import nikita.uniquescythe.utility.SoulsSystem;

import java.util.List;

public abstract class PhylacteryBasedItem extends Item {
	private final int maxCapacity;

	public PhylacteryBasedItem(Settings settings, int maxCapacity) {
		super(settings);
		this.maxCapacity = maxCapacity;
	}

	public int getSouls(ItemStack stack) {
		NbtCompound tag = stack.getOrCreateNbt();
		if (!tag.contains(SoulsSystem.SOULS)) {
			tag.putInt(SoulsSystem.SOULS, 0);
		}
		return tag.contains(SoulsSystem.SOULS) ? tag.getInt(SoulsSystem.SOULS) : 0;

	}

	public void subtractSouls(ItemStack stack, int amount) {
		NbtCompound tag = stack.getOrCreateNbt();
		tag.putInt(SoulsSystem.SOULS, getSouls(stack) - amount);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected){
		if (!world.isClient) {
			getSouls(stack);
			if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
				if (this.maxCapacity > getSouls(stack)) {
					SoulsSystem.addSoulsToPossibleItems(serverPlayerEntity, serverPlayerEntity.getDisplayName().getString(), stack);
					if (this.maxCapacity < getSouls(stack)) {
						SoulsSystem.setSouls(serverPlayerEntity, serverPlayerEntity.getDisplayName().getString(), this.maxCapacity);
					}
				}
			}
		}
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		//tooltip.add(Text.literal(""));
		assert world != null;

		tooltip.add(Text.literal("§5Phylactery"));
		tooltip.add(Text.literal("§9Souls collected: " + getSouls(stack) + " / " + this.maxCapacity));

		super.appendTooltip(stack, world, tooltip, context);
	}

	public int getMaxCapacity(){
		return this.maxCapacity;
	}

}
