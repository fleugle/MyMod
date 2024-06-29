package nikita.uniquescythe.items;

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

    public static final Item FROSTY_STEEL = registerItem("frosty_steel", new Item(new Item.Settings())); //ingridient needed to create the scythe

	public static final Item COPPER_NUGGET = registerItem("copper_nugget", new Item(new Item.Settings()));

	public static final Item CHAOS_SHARD = registerItem("chaos_shard", new SimplyDescribedItem(new Item.Settings(),"§9Infused with Chaos"));

	public static final Item JUSTICE_SHARD = registerItem("justice_shard", new SimplyDescribedItem(new Item.Settings(),"§9Infused with Justice"));

	public static final Item AIR_BOTTLE = registerItem("air_bottle", new Item(new Item.Settings().maxCount(16)));

	public static final Item FLUGELS_IMMORTALITY_DECLARATION = registerItem("flugels_immortality_declaration", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).maxDamage(50)));

    public static final Item FROSTY_SCYTHE = registerItem("frosty_scythe", new FrostyScytheItem(ModToolMaterial.FROSTY_STEEL,12, -3f, new Item.Settings().rarity(Rarity.RARE))); //The scythe

	public static final Item JEVIL_SCYTHE = registerItem("jevil_scythe", new JevilScytheItem(ModToolMaterial.CHAOS_WILL,12, -3f, new Item.Settings().rarity(Rarity.EPIC)));

	public static final Item CHAOS_MULTITOOL = registerItem("chaos_multitool", new ChaosMultiToolItem(ModToolMaterial.CHAOS_WILL, -3, 100f, new Item.Settings().rarity(Rarity.EPIC)));

	public static final Item CHAOS_MULTITOOL_AXE = registerItem("chaos_multitool_axe", new ChaosMultiToolAxeItem(ModToolMaterial.CHAOS_WILL, -3, 100f, new Item.Settings().rarity(Rarity.EPIC)));

	public static final Item CHAOS_MULTITOOL_SHOVEL = registerItem("chaos_multitool_shovel", new ChaosMultiToolShovelItem(ModToolMaterial.CHAOS_WILL, -3, 100f, new Item.Settings().rarity(Rarity.EPIC)));

	public static final Item CHAOS_MULTITOOL_HOE = registerItem("chaos_multitool_hoe", new ChaosMultiToolHoeItem(ModToolMaterial.CHAOS_WILL, -3, 100f, new Item.Settings().rarity(Rarity.EPIC)));

	public static final Item EASTER_EGG = registerItem("easter_egg", new EasterEggItem(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1)));

	public static final Item WIND_CHARGE = registerItem("wind_charge", new WindChargeItem(new Item.Settings().maxCount(64)));

    public static final Item WANDERERS_SWORD = registerItem("wanderers_sword", new WanderersSwordItem(ModToolMaterial.FROSTY_STEEL,1, 96f, new Item.Settings()));

	public static final Item BREEZE_ROD = registerItem("breeze_rod", new Item(new Item.Settings().maxCount(64)));

	public static final Item MACE = registerItem("mace", new MaceItem(ModToolMaterial.BREEZE_ROD,6,20, new Item.Settings().maxCount(1)));

	public static final Item JUSTICE_REVOLVER = registerItem("justice_revolver", new JusticeRevolverItem(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON).maxDamageIfAbsent(500)));

	public static final Item BULLET = registerItem("bullet", new Item(new Item.Settings().maxCount(16)));

	public static final Item SACRED_BULLET = registerItem("sacred_bullet", new Item(new Item.Settings().maxCount(16).rarity(Rarity.EPIC)));

	public static final Item JUSTICE_BULLET = registerItem("justice_bullet", new Item(new Item.Settings().maxCount(16).rarity(Rarity.UNCOMMON)));

	public static final Item JUSTICE_HAT = registerItem("justice_hat", new JusticeArmorItem(ModArmorMaterials.JUSTICE, ArmorItem.ArmorSlot.HELMET,new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

	public static final Item COWBOYS_CLOAK = registerItem("cowboys_cloak", new JusticeArmorItem(ModArmorMaterials.JUSTICE, ArmorItem.ArmorSlot.CHESTPLATE,new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

	public static final Item SIMPLE_HAT = registerItem("simple_hat", new SimpleHatItem(ModArmorMaterials.INNOCENCE, ArmorItem.ArmorSlot.HELMET,new Item.Settings().maxCount(1).rarity(Rarity.RARE)));

	public static final Item SORRY_STONE = registerItem("sorry_stone", new SorryStoneItem(new Item.Settings().maxCount(16).fireproof().rarity(Rarity.RARE)));

	public static final Item DUPLICATE_MIRROR = registerItem("duplicate_mirror", new DuplicateMirrorItem(new Item.Settings().rarity(Rarity.EPIC).maxDamageIfAbsent(11)));

	public static final Item STAR_TRINKET = registerItem("star_trinket", new Item( new Item.Settings()));

	public static final Item JOY_BELL = registerItem("joy_bell", new JoyBellItem( new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));

	public static final Item EXPLOSIVE_PHYLACTERY = registerItem("explosive_phylactery", new ExplosivePhylacteryItem());

	public static final Item EXPERIENCE_PHYLACTERY = registerItem("experience_phylactery", new ExperiencePhylactery());




	public static final Item TETOS_TALISMAN = registerItem("tetos_talisman", new SimpleTalismanItem(
		3f, 0.5f, 0.3f,-3f, 4, new Item.Settings().maxCount(1).rarity(Rarity.RARE))
	);

	public static final Item LEONS_TALISMAN = registerItem("leons_talisman", new SneakyTalismanItem(
		-3f, 0f, 0.5f,-10f, -8, new Item.Settings().maxCount(1).rarity(Rarity.RARE))
	);



	public static final Item HONEY_APPLE = registerItem(
		"honey_apple",
		new HoneyAppleItem(new Item.Settings().food(ModFoodComponents.HONEY_APPLE)));

	public static final Item GOLDEN_BERRIES = registerItem(
		"golden_berries",
		new Item(new Item.Settings().food(ModFoodComponents.GOLDEN_BERRIES))
	);




	// *end of the registering items section*


    //helper method to register items
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(UniqueScythe.MOD_ID,name), item);
    }

    public static void registerModItems(){

		//TOOLS AND UTILITIES TAB ITEMS REGISTRY
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS_AND_UTILITIES).register(entries -> {
			entries.addAfter(Items.FIRE_CHARGE, WIND_CHARGE);
			entries.addAfter(Items.NETHERITE_HOE, CHAOS_MULTITOOL);
			entries.addItem(SORRY_STONE);
			entries.addItem(Items.BUNDLE);
			entries.addAfter(Items.SPYGLASS, DUPLICATE_MIRROR);
			entries.addAfter(DUPLICATE_MIRROR, JOY_BELL);
		});


		//INGREDIENTS TAB ITEMS REGISTRY
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addAfter(Items.IRON_NUGGET, COPPER_NUGGET);
			entries.addBefore(Items.SHULKER_SHELL, HEAVY_CORE);
			entries.addAfter(Items.GOLD_INGOT, FROSTY_STEEL);
			entries.addAfter(Items.BLAZE_ROD, BREEZE_ROD);
			entries.addBefore(Items.DRAGON_BREATH, AIR_BOTTLE);
			entries.addAfter(Items.AMETHYST_SHARD, JUSTICE_SHARD);
			entries.addBefore(JUSTICE_SHARD, CHAOS_SHARD);
			entries.addAfter(Items.GOLDEN_CARROT, STAR_TRINKET);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINKS).register(entries -> {
			entries.addAfter(Items.APPLE, HONEY_APPLE);
			entries.addAfter(Items.GOLDEN_CARROT,GOLDEN_BERRIES);
		});

		//COMBAT TAB ITEMS REGISTRY
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			//armor & equipment
			entries.addBefore(Items.LEATHER_HORSE_ARMOR, SIMPLE_HAT);
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
			entries.addAfter(FLUGELS_IMMORTALITY_DECLARATION, TETOS_TALISMAN);
			entries.addAfter(TETOS_TALISMAN, LEONS_TALISMAN);



			//ammo
			entries.addItem(BULLET);
			entries.addAfter(BULLET, JUSTICE_BULLET);

			//phylactery
			entries.addAfter(MACE,EXPLOSIVE_PHYLACTERY);


		});//calling for the private method in order to  add item to the ingridient tab
    }
}
