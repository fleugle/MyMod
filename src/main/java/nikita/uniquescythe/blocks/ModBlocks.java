package nikita.uniquescythe.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.blocks.custom.AbstractCopperGrateBlock;
import nikita.uniquescythe.items.ModItems;

public class ModBlocks {


	//BLOCKS REGISTRIES
	//
	public static final Block  TUFF_BRICKS = registerBlock("tuff_bricks",
		new Block(AbstractBlock.Settings.copy(Blocks.BRICKS)));//tuff bricks


	public static final Block  COPPER_GRATE = registerBlock("copper_grate",
		new AbstractCopperGrateBlock(Oxidizable.OxidizationLevel.UNAFFECTED,
			AbstractBlock.Settings.create()
				.mapColor(MapColor.ORANGE)
				.nonOpaque().strength(4.0f)
				.requiresTool().strength(3.0F, 6.0F)
				.sounds(BlockSoundGroup.COPPER)));//copper grate


	public static final Block  OXIDIZED_COPPER_GRATE = registerBlock("oxidized_copper_grate",
		new AbstractCopperGrateBlock(Oxidizable.OxidizationLevel.OXIDIZED,
			AbstractBlock.Settings.create().mapColor(MapColor.WARPED_NYLIUM)
				.requiresTool()
				.nonOpaque().strength(4.0f)
				.strength(3.0F, 6.0F)
				.sounds(BlockSoundGroup.COPPER)));//oxidized copper grate

	public static final Block  EXPOSED_COPPER_GRATE = registerBlock("exposed_copper_grate",
		new AbstractCopperGrateBlock(Oxidizable.OxidizationLevel.EXPOSED,
			AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY_TERRACOTTA)
				.requiresTool()
				.nonOpaque().strength(4.0f)
				.strength(3.0F, 6.0F)
				.sounds(BlockSoundGroup.COPPER)));//exposed copper grate



	//ADDING TO ITEM TABS METHOD
	private static void addBlockToBuildingBlocksItemGroup(FabricItemGroupEntries entries) {
		//place to add items to the BuildingBlocks item tab
		entries.addItem(TUFF_BRICKS);
		entries.addItem(COPPER_GRATE);
		entries.addItem(EXPOSED_COPPER_GRATE);
		entries.addItem(OXIDIZED_COPPER_GRATE);

	}//adding items to combat tab *method*

	//HELPER METHODS
	//
	//method for the block itself
	private static Block registerBlock(String name, Block block) {
		registerBlockItem(name, block);
		//OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.COPPER_GRATE,ModBlocks.EXPOSED_COPPER_GRATE);
		//OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks.EXPOSED_COPPER_GRATE,ModBlocks.OXIDIZED_COPPER_GRATE);
		return Registry.register(Registries.BLOCK, new Identifier(UniqueScythe.MOD_ID, name), block);
	}


	//method for an item that will represent block
	private static Item registerBlockItem(String name, Block block){
		return Registry.register(Registries.ITEM, new Identifier(UniqueScythe.MOD_ID, name),
			new BlockItem(block, new Item.Settings()));
	}


	public static void registerModBlocks() {

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModBlocks::addBlockToBuildingBlocksItemGroup);




		UniqueScythe.LOGGER.info("Registering ModBlocks for "+UniqueScythe.MOD_ID);
	}
}
