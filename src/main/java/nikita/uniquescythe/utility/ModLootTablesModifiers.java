package nikita.uniquescythe.utility;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.blocks.ModBlocks;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.potions.ModPotions;

public class ModLootTablesModifiers {

	public static final Identifier ANCIENT_CITY_CHESTS_ID =
		new Identifier(UniqueScythe.GAME_ID, "chests/ancient_city");

	public static final Identifier BASTION_TREASURE_CHEST_ID =
		new Identifier(UniqueScythe.GAME_ID, "chests/bastion_treasure");

	public static final Identifier RUINED_PORTAL_CHEST_ID =
		new Identifier(UniqueScythe.GAME_ID, "chests/ruined_portal");

	public static final Identifier DESERT_PYRAMID_CHEST_ID =
		new Identifier(UniqueScythe.GAME_ID, "chests/desert_pyramid");

	public static final Identifier WOODLAND_MANSION_CHEST_ID =
		new Identifier(UniqueScythe.GAME_ID, "chests/woodland_mansion");

	public static final Identifier ABANDONED_MINESHAFT_CHEST_ID =
		new Identifier(UniqueScythe.GAME_ID, "chests/abandoned_mineshaft");


	public static void modifyLootTables(){

		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {

			if(ANCIENT_CITY_CHESTS_ID.equals(id)){
				UniqueScythe.LOGGER.info("ANCIENT_CITY_CHESTS_ID items were modified!");
				LootPool.Builder poolBuilder = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.25f))
					.with(ItemEntry.builder(ModBlocks.HEAVY_CORE))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
				tableBuilder.pool(poolBuilder.build());
			}

			if(DESERT_PYRAMID_CHEST_ID.equals(id)){
				UniqueScythe.LOGGER.info("DESERT_PYRAMID_CHEST_ID items were modified!");
				LootPool.Builder poolBuilder = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.55f))
					.with(ItemEntry.builder(ModItems.JUSTICE_SHARD))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4f)).build());
				tableBuilder.pool(poolBuilder.build());
			}

			if(RUINED_PORTAL_CHEST_ID.equals(id)){
				UniqueScythe.LOGGER.info("RUINED_PORTAL_CHEST_ID items were modified!");
				LootPool.Builder poolBuilder = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.55f))
					.with(ItemEntry.builder(ModItems.CHAOS_SHARD))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4f)).build());
				tableBuilder.pool(poolBuilder.build());
			}

			if(WOODLAND_MANSION_CHEST_ID.equals(id)){
				UniqueScythe.LOGGER.info("WOODLAND_MANSION_CHEST_ID items were modified!");
				LootPool.Builder poolBuilder = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.25f))
					.with(ItemEntry.builder(ModItems.LEONS_TALISMAN))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1f)).build());
				tableBuilder.pool(poolBuilder.build());
			}

			if(ABANDONED_MINESHAFT_CHEST_ID.equals(id)){
				UniqueScythe.LOGGER.info("ABANDONED_MINESHAFT_CHEST_ID items were modified!");
				LootPool.Builder poolBuilder = LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.conditionally(RandomChanceLootCondition.builder(0.15f))
					.with(ItemEntry.builder(ModItems.TETOS_TALISMAN))
					.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1f)).build());
				tableBuilder.pool(poolBuilder.build());
			}

		});
	}


	/*
	*
	* Certainly! This code is part of a Minecraft mod that uses the Fabric mod development toolkit. It's designed to modify the loot tables in the game, specifically for the chests found in the Ancient City structure. Here's a breakdown of what each part does:
	* - `public class ModLootTablesModifiers`: This line defines a new class named `ModLootTablesModifiers`. Think of a class as a blueprint for creating objects that share certain characteristics and behaviors.

	* - `public static final Identifier ANCIENT_CITY_CHESTS_ID`: Here, we're declaring a constant identifier for the loot table of chests found in the Ancient City. This is like giving a unique name to recognize this specific loot table.

	* - `public static void modifyLootTables()`: This method is where the modification of the loot tables will happen. It's a set of instructions that will be run to change the loot.

	* - Inside the `modifyLootTables` method, there's a registration process for an event listener:

  		- `LootTableEvents.MODIFY.register(...)`: This line is telling the game, "Hey, whenever you're modifying loot tables, also run this code I'm providing."

	* - Within the event listener, there's a conditional check:

  		- `if(ANCIENT_CITY_CHESTS_ID.equals(id))`: This is like saying, "If the loot table being modified is the one for the Ancient City chests, then do the following."

		- `UniqueScythe.LOGGER.info(...)`: This is a logging statement. It's used for developers to see messages in the console. In this case, it will print out a message saying that the Ancient City chest items were modified.

		- `LootPool.Builder poolBuilder = LootPool.builder()`: This part is creating a new loot pool, which is a collection of items that can be found in a chest.


	* - The `poolBuilder` is then configured with several methods:

  		- `.rolls(...)`: This sets how many times the game will try to pick items from this pool. In this case, it's set to 1 roll.

  		- `.conditionally(...)`: This adds a condition for the loot to appear. The `0.25f` means there's a 25% chance for the loot to be added to the chest.

  		- `.with(...)`: This specifies what item will be added to the loot pool. `ModBlocks.HEAVY_CORE` refers to a custom item defined elsewhere in the mod.

 		 - `.apply(...)`: This applies a function to the items in the pool. Here, it's setting the count of the item to be always 1.


	* - Finally, `tableBuilder.pool(poolBuilder.build())`: This adds the newly created loot pool to the loot table of the Ancient City chests.


	* In simple terms, this code is telling Minecraft to add a new item to the Ancient City chests with a 25% chance of appearing, and if it does appear, there will only be one of this item. This is all part of expanding the game's content through modding! 🛠️
	 */
}
