package nikita.uniquescythe.utility;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;





import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public final class ModBlockTags {
	public static final TagKey<Block> EVERYTHING = create("everything");

	private ModBlockTags() {
	}

	private static TagKey<Block> create(String id) {
		return TagKey.of(RegistryKeys.BLOCK, new Identifier(id));
	}
}
