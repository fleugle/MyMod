package nikita.uniquescythe.items;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;


//put it easy, this is a material constructor for a tool made of a specific material(as i understand), so that we don't need to create an entirely new class for new material
public enum ModToolMaterial implements ToolMaterial {
    //making frosty steel tool material

	FROSTY_STEEL(2, 650,1f, 4f, 26, () -> Ingredient.ofItems(ModItems.FROSTY_STEEL)),
	CHAOS_WILL(200, 0,200f, 4f, 0, Ingredient::empty),//not sure that thing works properly
	BREEZE_ROD(0, 500,0f, 7, 0, () -> Ingredient.ofItems(ModItems.BREEZE_ROD));//put comma to add new





	private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
