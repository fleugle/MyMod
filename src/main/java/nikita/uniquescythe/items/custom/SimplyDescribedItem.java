package nikita.uniquescythe.items.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class SimplyDescribedItem extends Item {
	String description;

	public SimplyDescribedItem(Settings settings, String description) {
		super(settings);
		this.description = description;
	}

	//https://www.digminecraft.com/lists/color_list_pc.php
	//§5 - Dark Purple. §9 - Blue. §1 - Dark Blue
	//§7 - Gray. §8 - Dark Gray
	//§2 - Dark Green. §a - Green
	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.literal(""));

		tooltip.add(Text.literal(description));
		super.appendTooltip(stack, world, tooltip, context);
	}
}
