package nikita.uniquescythe.early_risers;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.enchantment.EnchantmentTarget;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.MaceItem;
import org.quiltmc.loader.api.MappingResolver;
import org.quiltmc.loader.api.QuiltLoader;

public class MaceEnumAdder implements Runnable{
	@Override
	public void run() {

		MappingResolver remapper = QuiltLoader.getMappingResolver();
		String enchantmentTarget = remapper.mapClassName("intermediary", "net.minecraft.class_1886");
		ClassTinkerers.enumBuilder(enchantmentTarget).addEnumSubclass("MACE", "nikita.uniquescythe.mixin.LetThemEnchantMace").build();
	}
}
