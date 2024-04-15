package nikita.uniquescythe.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.*;

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


	public static final Item CHAOS_MULTITOOL = registerItem("chaos_multitool", new ChaosMultiToolItem(ModToolMaterial.CHAOS_WILL, 7, -3f, new Item.Settings().rarity(Rarity.EPIC)));

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
	public static final Item JUSTICE_HAT = registerItem("justice_hat", new Item(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1)));
	public static final Item CARTRIDGE = registerItem("cartridge", new Item(new Item.Settings().fireproof().maxCount(16)));
	// *end of the registering items section*






    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        //place to add items to the ingredient item tab
		entries.addItem(HEAVY_CORE);
        entries.addItem(FROSTY_STEEL);
		entries.addItem(CHAOS_WILL);
		entries.addItem(BREEZE_ROD);
		entries.addItem(AIR_BOTTLE);

    }//adding items to ingredient tab *method*

	private static void addItemsToToolsAndUtilitiesItemGroup(FabricItemGroupEntries entries) {
		//place to add items to the ingredient item tab
		entries.addItem(WIND_CHARGE);
		entries.addItem(CHAOS_MULTITOOL);
	}//adding items to tools and utilities tab *method*

    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        //place to add items to the ingredient item tab
		entries.addItem(JUSTICE_HAT);


		entries.addItem(CARTRIDGE);
		entries.addItem(JUSTICE_FRAGMENT);
		entries.addItem(FLUGELS_IMMORTALITY_DECLARATION);
        entries.addItem(FROSTY_SCYTHE);
		entries.addItem(JEVIL_SCYTHE);
        entries.addItem(WANDERERS_SWORD);
		entries.addItem(MACE);
		entries.addItem(JUSTICE_REVOLVER);
    }//adding items to combat tab *method*


    //helper method to register items
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(UniqueScythe.MOD_ID,name), item);
    }

    public static void registerModItems(){
        //logger output to see if mod actually registers items


		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS_AND_UTILITIES).register(ModItems::addItemsToToolsAndUtilitiesItemGroup);//calling for the private method in order to  add item to the ingridient tab
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);//calling for the private method in order to  add item to the ingridient tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);//calling for the private method in order to  add item to the ingridient tab
    }
}
