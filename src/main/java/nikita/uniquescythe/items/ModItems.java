package nikita.uniquescythe.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.FrostyScytheItem;

//some sort of helper class that will register our custom items
public class ModItems {

    // *start of the registering items section*

    //     FROSTY_STEAL registry
    public static final Item FROSTY_STEEL = registerItem("frosty_steel", new Item(new FabricItemSettings())); //ingridient needed to create the scythe


    //FROSTY_SCYTHE registry
    public static final Item FROSTY_SCYTHE = registerItem("frosty_scythe", new FrostyScytheItem(ModToolMaterial.FROSTY_STEEL,12, -3f, new FabricItemSettings())); //The scythe


    //FROSTY_DAGGER registry
    //public static final Item FROSTY_DAGGER = registerItem("frosty_dagger", new SwordItem(ModToolMaterial.FROSTY_STEEL,7, 100f, new FabricItemSettings())); //The dagger


    // *end of the registering items section*






    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        //place to add items to the ingredient item tab
        entries.addItem(FROSTY_STEEL);
    }//adding items to ingredient tab *method*

    private static void addItemsToCombatItemGroup(FabricItemGroupEntries entries) {
        //place to add items to the ingredient item tab
        entries.addItem(FROSTY_SCYTHE);
        //entries.add(FROSTY_DAGGER);
    }//adding items to ingredient tab *method*

    //helper method to register items
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(UniqueScythe.MOD_ID,name), item);
    }

    public static void registerModItems(){
        //logger output to see if mod actually registers items
        UniqueScythe.LOGGER.info("Registering Mod Items for " +UniqueScythe.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);//calling for the private method in order to  add item to the ingridient tab
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToCombatItemGroup);//calling for the private method in order to  add item to the ingridient tab
    }
}
