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

	private int getSouls(ItemStack stack) {
		NbtCompound tag = stack.getOrCreateNbt();
		return tag.contains(SoulsSystem.SOULS) ? tag.getInt(SoulsSystem.SOULS) : 0;
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected){
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

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		//tooltip.add(Text.literal(""));
		tooltip.add(Text.literal("§5Phylactery"));
		tooltip.add(Text.literal("§9Souls collected: " + getSouls(stack) + " / " + this.maxCapacity));
		super.appendTooltip(stack, world, tooltip, context);
	}

	public int getMaxCapacity(){
		return this.maxCapacity;
	}

}
