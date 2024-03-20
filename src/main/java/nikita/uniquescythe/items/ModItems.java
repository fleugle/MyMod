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

//some sort of helper class that will register our custom items
public class ModItems {

    // *start of the registering items section*

    //     FROSTY_STEAL registry
    public static final Item FROSTY_STEEL = registerItem("frosty_steel", new Item(new FabricItemSettings())); //ingridient needed to create the scythe

	//Flugel's immortality declaration
	public static final Item FLUGELS_IMMORTALITY_DECLARATION = registerItem("flugels_immortality_declaration", new Item(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC))); //ingridient needed to create the scythe


    //FROSTY_SCYTHE registry
    public static final Item FROSTY_SCYTHE = registerItem("frosty_scythe", new FrostyScytheItem(ModToolMaterial.FROSTY_STEEL,12, -3f, new FabricItemSettings())); //The scythe

	//EASTEREGG
	public static final Item EASTER_EGG = registerItem("easter_egg", new EasterEggItem(new FabricItemSettings().rarity(Rarity.UNCOMMON)));

	//WIND CHARGE PROTOTYPE
	public static final Item WIND_CHARGE = registerItem("wind_charge", new WindChargeItem(new FabricItemSettings().maxCount(64)));

    //WANDERERS_SWORD registry
    public static final Item WANDERERS_SWORD = registerItem("wanderers_sword", new WanderersSwordItem(ModToolMaterial.FROSTY_STEEL,1, 100f, new FabricItemSettings()));

	//BREEZE_ROD registry
	public static final Item BREEZE_ROD = registerItem("breeze_rod", new Item(new FabricItemSettings().maxCount(16)));

	//MACE registry
	public static final Item MACE = registerItem("mace", new MaceItem(ModToolMaterial.BREEZE_ROD,1, -3f, new FabricItemSettings())); //The scythe

	// *end of the registering items section*






    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        //place to add items to the ingredient item tab
        entries.addItem(FROSTY_STEEL);
		entries.addItem(BREEZE_ROD);

    }//adding items to ingredient tab *method*

	private static void addItemsToToolsAndUtilitiesItemGroup(FabricItemGroupEntries entries) {
		//place to add items to the ingredient item tab
		entries.addItem(WIND_CHARGE);
	}//adding items to tools and utilities tab *method*

    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        //place to add items to the ingredient item tab
		entries.addItem(FLUGELS_IMMORTALITY_DECLARATION);
        entries.addItem(FROSTY_SCYTHE);
        entries.addItem(WANDERERS_SWORD);
		entries.addItem(MACE);
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
