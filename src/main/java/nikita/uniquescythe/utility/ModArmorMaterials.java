package nikita.uniquescythe.utility;


import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.ModItems;

import java.util.function.Supplier;


public enum ModArmorMaterials implements  ArmorMaterial {

	//enums for armor materials
	JUSTICE(
		"justice",
		1,
		new int[]{3,3,3,3},
		0,
		SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
		20f,
		20f,
		() -> Ingredient.ofItems(ModItems.JUSTICE_FRAGMENT)
	)//do NOT put a ; here, it is already put below, and if you want to add new enum, just put a comma

	;

	private final String name;
	private final int durabilityMultiplier;
	private final int[] protectionLevels;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Supplier<Ingredient> repairIngridient;

	private static final int[] BASE_DURABILITY = {10, 16, 15, 13};

    ModArmorMaterials(
			String name,
			int durabilityMultiplier,
			int[] protectionLevels,
			int enchantability,
			SoundEvent equipSound,
			float toughness,
			float knockbackResistance,
			Supplier<Ingredient> repairIngridient
	)
	{
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionLevels = protectionLevels;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngridient = repairIngridient;
    }


    @Override
	public int getDurability(ArmorItem.ArmorSlot slot) {
		return BASE_DURABILITY[slot.ordinal()] * this.durabilityMultiplier;
	}

	@Override
	public int getProtection(ArmorItem.ArmorSlot slot) {
		return this.protectionLevels[slot.ordinal()];
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.equipSound;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngridient.get();
	}

	@Override
	public String getName() {
		return UniqueScythe.MOD_ID + ':' + this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return this.knockbackResistance;
	}

}
