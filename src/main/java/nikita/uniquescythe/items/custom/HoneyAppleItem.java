package nikita.uniquescythe.items.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class HoneyAppleItem extends Item {
	String description;

	public HoneyAppleItem(Settings settings, String description) {
		super(settings);
		this.description = description;
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);
		tooltip.add(Text.literal(description));
	}
}
