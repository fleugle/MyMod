package nikita.uniquescythe.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.FrostyScytheItem;
import nikita.uniquescythe.items.custom.WardenersSwordItem;

//some sort of helper class that will register our custom items
public class ModItems {

    // *start of the registering items section*

    //     FROSTY_STEAL registry
    public static final Item FROSTY_STEEL = registerItem("frosty_steel", new Item(new FabricItemSettings())); //ingridient needed to create the scythe


    //FROSTY_SCYTHE registry
    public static final Item FROSTY_SCYTHE = registerItem("frosty_scythe", new FrostyScytheItem(ModToolMaterial.FROSTY_STEEL,12, -3f, new FabricItemSettings())); //The scythe

	//EASTEREGG
	public static final Item EASTER_EGG = registerItem("easter_egg", new Item(new FabricItemSettings()));

    //WARDENERS_SWORD registry
    public static final Item WARDENERS_SWORD = registerItem("wardeners_sword", new WardenersSwordItem(ModToolMaterial.FROSTY_STEEL,1, 100f, new FabricItemSettings())); //The dagger


    // *end of the registering items section*






    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        //place to add items to the ingredient item tab
        entries.addItem(FROSTY_STEEL);
    }//adding items to ingredient tab *method*

    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        //place to add items to the ingredient item tab
        entries.addItem(FROSTY_SCYTHE);
        entries.addItem(WARDENERS_SWORD);
    }//adding items to ingredient tab *method*

    //helper method to register items
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(UniqueScythe.MOD_ID,name), item);
    }

    public static void registerModItems(){
        //logger output to see if mod actually registers items


        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);//calling for the private method in order to  add item to the ingridient tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);//calling for the private method in order to  add item to the ingridient tab
    }
}
