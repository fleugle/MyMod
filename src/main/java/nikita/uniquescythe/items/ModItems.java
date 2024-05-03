package nikita.uniquescythe.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.*;
import nikita.uniquescythe.utility.ModArmorMaterials;
import nikita.uniquescythe.utility.ModToolMaterial;

import static nikita.uniquescythe.blocks.ModBlocks.HEAVY_CORE;

//some sort of helper class that will register our custom items
public class ModItems {

    // *start of the registering items section*

    //     FROSTY_STEAL registry
    public static final Item FROSTY_STEEL = registerItem("frosty_steel", new Item(new Item.Settings())); //ingridient needed to create the scythe


	//     CHAOS_WILL registry
	public static final Item CHAOS_WILL = registerItem("chaos_will", new Item(new Item.Settings()));

	//AIR_BOTTLE
	public static final Item AIR_BOTTLE = registerItem("air_bottle", new Item(new Item.Settings().maxCount(16)));

	//Flugel's immortality declaration
	public static final Item FLUGELS_IMMORTALITY_DECLARATION = registerItem("flugels_immortality_declaration", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));


    //FROSTY_SCYTHE registry
    public static final Item FROSTY_SCYTHE = registerItem("frosty_scythe", new FrostyScytheItem(ModToolMaterial.FROSTY_STEEL,12, -3f, new Item.Settings().rarity(Rarity.RARE))); //The scythe

	//JEVIL_SCYTHE registry
	public static final Item JEVIL_SCYTHE = registerItem("jevil_scythe", new JevilScytheItem(ModToolMaterial.CHAOS_WILL,12, -3f, new Item.Settings().rarity(Rarity.EPIC)));


	public static final Item CHAOS_MULTITOOL = registerItem("chaos_multitool", new ChaosMultiToolItem(ModToolMaterial.CHAOS_WILL, -3, 100f, new Item.Settings().rarity(Rarity.EPIC)));

	public static final Item CHAOS_MULTITOOL_AXE = registerItem("chaos_multitool_axe", new ChaosMultiToolAxeItem(ModToolMaterial.CHAOS_WILL, -3, 100f, new Item.Settings().rarity(Rarity.EPIC)));

	public static final Item CHAOS_MULTITOOL_SHOVEL = registerItem("chaos_multitool_shovel", new ChaosMultiToolShovelItem(ModToolMaterial.CHAOS_WILL, -3, 100f, new Item.Settings().rarity(Rarity.EPIC)));

	public static final Item CHAOS_MULTITOOL_HOE = registerItem("chaos_multitool_hoe", new ChaosMultiToolHoeItem(ModToolMaterial.CHAOS_WILL, -3, 100f, new Item.Settings().rarity(Rarity.EPIC)));

	//EASTEREGG
	public static final Item EASTER_EGG = registerItem("easter_egg", new EasterEggItem(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1)));

	//WIND CHARGE PROTOTYPE
	public static final Item WIND_CHARGE = registerItem("wind_charge", new WindChargeItem(new Item.Settings().maxCount(64)));

    //WANDERERS_SWORD registry
    public static final Item WANDERERS_SWORD = registerItem("wanderers_sword", new WanderersSwordItem(ModToolMaterial.FROSTY_STEEL,1, 100f, new Item.Settings()));

	//BREEZE_ROD registry
	public static final Item BREEZE_ROD = registerItem("breeze_rod", new Item(new Item.Settings().maxCount(64)));

	//MACE registry
	public static final Item MACE = registerItem("mace", new MaceItem(ModToolMaterial.BREEZE_ROD,6,20, new Item.Settings().maxCount(1)));

	public static final Item JUSTICE_REVOLVER = registerItem("justice_revolver", new JusticeRevolverItem(ModToolMaterial.TRIUMPH_OF_JUSTICE,new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

	public static final Item JUSTICE_FRAGMENT = registerItem("justice_fragment", new Item(new Item.Settings().fireproof().maxCount(16).rarity(Rarity.UNCOMMON)));

	public static final Item CARTRIDGE = registerItem("cartridge", new Item(new Item.Settings().fireproof().maxCount(16)));
	// *end of the registering items section*

	public static final Item JUSTICE_HAT = registerItem("justice_hat", new JusticeArmorItem(ModArmorMaterials.JUSTICE, ArmorItem.ArmorSlot.HELMET,new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

	public static final Item COWBOYS_CLOAK = registerItem("cowboys_cloak", new JusticeArmorItem(ModArmorMaterials.JUSTICE, ArmorItem.ArmorSlot.CHESTPLATE,new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));


	public static final Item INNOCENT_NIMBUS = registerItem("innocent_nimbus", new InnocentNimbusItem(ModArmorMaterials.INNOCENCE, ArmorItem.ArmorSlot.HELMET,new Item.Settings().maxCount(1).rarity(Rarity.RARE)));



    //helper method to register items
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(UniqueScythe.MOD_ID,name), item);
    }

    public static void registerModItems(){

		//TOOLS AND UTILITIES TAB ITEMS REGISTRY
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS_AND_UTILITIES).register(entries -> {
			entries.addAfter(Items.FIRE_CHARGE, WIND_CHARGE);
			entries.addAfter(Items.NETHERITE_HOE, CHAOS_MULTITOOL);
		});


		//INGREDIENTS TAB ITEMS REGISTRY
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addBefore(Items.SHULKER_SHELL, HEAVY_CORE);
			entries.addAfter(Items.GOLD_INGOT, FROSTY_STEEL);
			entries.addBefore(Items.FIRE_CHARGE, CHAOS_WILL);
			entries.addAfter(Items.BLAZE_ROD, BREEZE_ROD);
			entries.addBefore(Items.DRAGON_BREATH, AIR_BOTTLE);
		});


		//COMBAT TAB ITEMS REGISTRY
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			//armor & equipment
			entries.addBefore(Items.LEATHER_HORSE_ARMOR, INNOCENT_NIMBUS);
			entries.addBefore(Items.LEATHER_HORSE_ARMOR, JUSTICE_HAT);
			entries.addBefore(Items.LEATHER_HORSE_ARMOR ,COWBOYS_CLOAK);



			//weapons
			entries.addBefore(Items.SHIELD ,MACE);
			entries.addBefore(Items.SHIELD ,WANDERERS_SWORD);
			entries.addBefore(Items.SHIELD ,FROSTY_SCYTHE);
			entries.addBefore(Items.SHIELD ,JEVIL_SCYTHE);
			entries.addBefore(Items.FIREWORK_ROCKET, JUSTICE_REVOLVER);



			//utility items
			entries.addBefore(Items.TNT, FLUGELS_IMMORTALITY_DECLARATION);



			//ammo
			entries.addItem(CARTRIDGE);
			entries.addItem(JUSTICE_FRAGMENT);
		});//calling for the private method in order to  add item to the ingridient tab
    }
}
