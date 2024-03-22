package nikita.uniquescythe.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.ModItems;

public class ModBlocks {


	//BLOCKS REGISTRIES
	//
	public static final Block  TUFF_BRICKS = registerBlock("tuff_bricks",
		new Block(AbstractBlock.Settings.copy(Blocks.BRICKS)));//tuff bricks





	//ADDING TO ITEM TABS METHOD
	private static void addBlockToBuildingBlocksItemGroup(FabricItemGroupEntries entries) {
		//place to add items to the BuildingBlocks item tab
		entries.addItem(TUFF_BRICKS);

	}//adding items to combat tab *method*

	//HELPER METHODS
	//
	//method for the block itself
	private static Block registerBlock(String name, Block block) {
		registerBlockItem(name, block);
		return Registry.register(Registries.BLOCK, new Identifier(UniqueScythe.MOD_ID, name), block);
	}


	//method for an item that will represent block
	private static Item registerBlockItem(String name, Block block){
		return Registry.register(Registries.ITEM, new Identifier(UniqueScythe.MOD_ID, name),
			new BlockItem(block, new Item.Settings()));
	}


	public static void registerModBlocks() {

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(ModBlocks::addBlockToBuildingBlocksItemGroup);


		UniqueScythe.LOGGER.info("Registering ModBlocks for "+UniqueScythe.MOD_ID);//just an asfuck logger

	}
}
