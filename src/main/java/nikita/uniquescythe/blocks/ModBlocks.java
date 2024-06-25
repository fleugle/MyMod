package nikita.uniquescythe.blocks;


import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.blocks.custom.*;
import nikita.uniquescythe.sounds.ModBlockSoundGroup;

public class ModBlocks {


	//BLOCKS REGISTRIES
	//
	public static final Block  TUFF_BRICKS = registerBlock("tuff_bricks",
		new Block(AbstractBlock.Settings.copy(Blocks.BRICKS)));//tuff bricks

	public static final Block POLISHED_TUFF = registerBlock("polished_tuff",
		new Block(AbstractBlock.Settings.copy(Blocks.POLISHED_GRANITE)));

	public static final Block  HEAVY_CORE = registerBlock("heavy_core",
		new HeavyCoreBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN).mapColor(MapColor.DEEPSLATE).sounds(ModBlockSoundGroup.HEAVY_CORE)));//heavy core



	public static final Block  COPPER_GRATE = registerBlock("copper_grate",
		new CopperGrateBlock(Oxidizable.OxidizationLevel.UNAFFECTED,
			AbstractBlock.Settings.create()
				.mapColor(MapColor.ORANGE)
				.nonOpaque().strength(4.0f)
				.requiresTool().strength(3.0F, 6.0F)
				.sounds(BlockSoundGroup.COPPER)));//copper grate


	public static final Block  OXIDIZED_COPPER_GRATE = registerBlock("oxidized_copper_grate",
		new CopperGrateBlock(Oxidizable.OxidizationLevel.OXIDIZED,
			AbstractBlock.Settings.create().mapColor(MapColor.WARPED_NYLIUM)
				.requiresTool()
				.nonOpaque().strength(4.0f)
				.strength(3.0F, 6.0F)
				.sounds(BlockSoundGroup.COPPER)));//oxidized copper grate

	public static final Block  EXPOSED_COPPER_GRATE = registerBlock("exposed_copper_grate",
		new CopperGrateBlock(Oxidizable.OxidizationLevel.EXPOSED,
			AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY_TERRACOTTA)
				.requiresTool()
				.nonOpaque().strength(4.0f)
				.strength(3.0F, 6.0F)
				.sounds(BlockSoundGroup.COPPER)));//exposed copper grate


	public static final Block  WAXED_COPPER_GRATE = registerBlock("waxed_copper_grate",
		new WaxedCopperGrateBlock(
			AbstractBlock.Settings.create()
				.mapColor(MapColor.ORANGE)
				.nonOpaque().strength(4.0f)
				.requiresTool().strength(3.0F, 6.0F)
				.sounds(BlockSoundGroup.COPPER)));//copper grate


	public static final Block  WAXED_OXIDIZED_COPPER_GRATE = registerBlock("waxed_oxidized_copper_grate",
		new WaxedCopperGrateBlock(
			AbstractBlock.Settings.create().mapColor(MapColor.WARPED_NYLIUM)
				.requiresTool()
				.nonOpaque().strength(4.0f)
				.strength(3.0F, 6.0F)
				.sounds(BlockSoundGroup.COPPER)));//oxidized copper grate

	public static final Block  WAXED_EXPOSED_COPPER_GRATE = registerBlock("waxed_exposed_copper_grate",
		new WaxedCopperGrateBlock(
			AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY_TERRACOTTA)
				.requiresTool()
				.nonOpaque().strength(4.0f)
				.strength(3.0F, 6.0F)
				.sounds(BlockSoundGroup.COPPER)));//exposed copper grate



	public static final Block COPPER_BULB = registerBlock("copper_bulb",
		(Block)new OxidizableBulbBlock(
			Oxidizable.OxidizationLevel.UNAFFECTED,
			AbstractBlock.Settings.create()
				.mapColor(MapColor.ORANGE)
				.strength(3.0f, 6.0f)
				.sounds(ModBlockSoundGroup.COPPER_BULB)
				.requiresTool()
				.solidBlock(Blocks::never)
				.luminance(Blocks.createLightLevelFromLitBlockState(15))));

	public static final Block EXPOSED_COPPER_BULB = registerBlock("exposed_copper_bulb",
		(Block)new OxidizableBulbBlock(
			Oxidizable.OxidizationLevel.UNAFFECTED,
			AbstractBlock.Settings.create()
				.mapColor(MapColor.LIGHT_GRAY_TERRACOTTA)
				.strength(3.0f, 6.0f)
				.sounds(ModBlockSoundGroup.COPPER_BULB)
				.requiresTool()
				.solidBlock(Blocks::never)
				.luminance(Blocks.createLightLevelFromLitBlockState(12))));

	public static final Block WEATHERED_COPPER_BULB = registerBlock("weathered_copper_bulb",
		(Block)new OxidizableBulbBlock(
			Oxidizable.OxidizationLevel.UNAFFECTED,
			AbstractBlock.Settings.create()
				.mapColor(MapColor.WARPED_STEM)
				.strength(3.0f, 6.0f)
				.sounds(ModBlockSoundGroup.COPPER_BULB)
				.requiresTool()
				.solidBlock(Blocks::never)
				.luminance(Blocks.createLightLevelFromLitBlockState(8))));

	public static final Block OXIDIZED_COPPER_BULB = registerBlock("oxidized_copper_bulb",
		(Block)new OxidizableBulbBlock(
			Oxidizable.OxidizationLevel.UNAFFECTED,
			AbstractBlock.Settings.create()
				.mapColor(MapColor.WARPED_NYLIUM)
				.strength(3.0f, 6.0f)
				.sounds(ModBlockSoundGroup.COPPER_BULB)
				.requiresTool()
				.solidBlock(Blocks::never)
				.luminance(Blocks.createLightLevelFromLitBlockState(4))));

	public static final Block WAXED_COPPER_BULB = registerBlock("waxed_copper_bulb",
		(Block)new BulbBlock(AbstractBlock.Settings.copy(COPPER_BULB)));

	public static final Block WAXED_EXPOSED_COPPER_BULB = registerBlock("waxed_exposed_copper_bulb",
		(Block)new BulbBlock(AbstractBlock.Settings.copy(EXPOSED_COPPER_BULB)));

	public static final Block WAXED_WEATHERED_COPPER_BULB = registerBlock("waxed_weathered_copper_bulb",
		(Block)new BulbBlock(AbstractBlock.Settings.copy(WEATHERED_COPPER_BULB)));

	public static final Block WAXED_OXIDIZED_COPPER_BULB = registerBlock("waxed_oxidized_copper_bulb",
		(Block)new BulbBlock(AbstractBlock.Settings.copy(OXIDIZED_COPPER_BULB)));

	public static final Block MINER = registerBlock("miner",
		(Block)new MinerBlock(AbstractBlock.Settings.create()
			.mapColor(MapColor.STONE)
			.instrument(NoteBlockInstrument.BASEDRUM)
			.requiresTool()
			.strength(3.5F)));

	public static final Block COPPER_PIPE = registerBlock("copper_pipe",
		(Block)new CopperPipeBlock(AbstractBlock.Settings.copy(COPPER_GRATE)));


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

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
			entries.addItem(POLISHED_TUFF);
			entries.addItem(TUFF_BRICKS);
			entries.addItem(COPPER_GRATE);
			entries.addItem(EXPOSED_COPPER_GRATE);
			entries.addItem(OXIDIZED_COPPER_GRATE);
			entries.addItem(WAXED_COPPER_GRATE);
			entries.addItem(WAXED_EXPOSED_COPPER_GRATE);
			entries.addItem(WAXED_OXIDIZED_COPPER_GRATE);

			entries.addItem(COPPER_BULB);
			entries.addItem(EXPOSED_COPPER_BULB);
			entries.addItem(WEATHERED_COPPER_BULB);
			entries.addItem(OXIDIZED_COPPER_BULB);
			entries.addItem(WAXED_COPPER_BULB);
			entries.addItem(WAXED_EXPOSED_COPPER_GRATE);
			entries.addItem(WAXED_WEATHERED_COPPER_BULB);
			entries.addItem(WAXED_OXIDIZED_COPPER_BULB);

			entries.addItem(HEAVY_CORE);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE_BLOCKS).register(entries -> {

			entries.addAfter(Blocks.STICKY_PISTON, MINER);
			entries.addAfter(Blocks.LIGHTNING_ROD, COPPER_PIPE);
			entries.addItem(WAXED_COPPER_BULB);
			entries.addItem(WAXED_EXPOSED_COPPER_BULB);
			entries.addItem(WAXED_WEATHERED_COPPER_BULB);
			entries.addItem(WAXED_OXIDIZED_COPPER_BULB);

		});





		UniqueScythe.LOGGER.info("Registering ModBlocks for "+UniqueScythe.MOD_ID);
	}
}
