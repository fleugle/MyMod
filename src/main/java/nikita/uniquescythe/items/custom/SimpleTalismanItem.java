package nikita.uniquescythe.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.gui.screen.world.EditWorldScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class SimpleTalismanItem extends Item {

	public final float attackDamage;
	public final float attackSpeed;
	public final float movementSpeed;
	public final float armor;
	public final float addHealth;

	public static final UUID TALISMAN_DAMAGE_MODIFIER_ID = UUID.fromString("3C0E2920-1886-11EF-9814-3B8F3FC9398A");
	public static final UUID TALISMAN_RESISTANCE_MODIFIER_ID = UUID.fromString("EC0002D0-1887-11EF-9814-3B8F3FC9398A");
	public static final UUID TALISMAN_MOVEMENT_SPEED_MODIFIER_ID = UUID.fromString("9481C980-1887-11EF-9814-3B8F3FC9398A");
	public static final UUID TALISMAN_ATTACK_SPEED_MODIFIER_ID = UUID.fromString("D5A00E30-1888-11EF-9814-3B8F3FC9398A");
	public static final UUID TALISMAN_MAX_HEALTH_MODIFIER_ID = UUID.fromString("87B34140-188A-11EF-9814-3B8F3FC9398A");

	private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;


	public SimpleTalismanItem(
		float attackDamage,
		float attackSpeed,
		float movementSpeed,
		float armor,
		float addHealth,
		Settings settings
	) {
		super(settings);
		this.attackDamage = attackDamage;
		this.attackSpeed = 	attackSpeed;
		this.movementSpeed = movementSpeed;
		this.armor = armor;
		this.addHealth = addHealth;



		List<Map.Entry<EntityAttribute, EntityAttributeModifier>> attributes = new ArrayList<>();
		attributes.add(new AbstractMap.SimpleEntry<>(EntityAttributes.GENERIC_ATTACK_DAMAGE,
			new EntityAttributeModifier(TALISMAN_DAMAGE_MODIFIER_ID, "Talisman modifier", this.attackDamage, EntityAttributeModifier.Operation.ADDITION)));
		attributes.add(new AbstractMap.SimpleEntry<>(EntityAttributes.GENERIC_ATTACK_SPEED,
			new EntityAttributeModifier(TALISMAN_ATTACK_SPEED_MODIFIER_ID, "Talisman modifier", this.attackSpeed, EntityAttributeModifier.Operation.ADDITION)));
		attributes.add(new AbstractMap.SimpleEntry<>(EntityAttributes.GENERIC_MAX_HEALTH,
			new EntityAttributeModifier(TALISMAN_MAX_HEALTH_MODIFIER_ID, "Talisman modifier", this.addHealth, EntityAttributeModifier.Operation.ADDITION)));
		attributes.add(new AbstractMap.SimpleEntry<>(EntityAttributes.GENERIC_ARMOR,
			new EntityAttributeModifier(TALISMAN_RESISTANCE_MODIFIER_ID, "Talisman modifier", this.armor, EntityAttributeModifier.Operation.ADDITION)));
		attributes.add(new AbstractMap.SimpleEntry<>(EntityAttributes.GENERIC_MOVEMENT_SPEED,
			new EntityAttributeModifier(TALISMAN_MOVEMENT_SPEED_MODIFIER_ID, "Talisman modifier", this.movementSpeed, EntityAttributeModifier.Operation.ADDITION)));

		// Sort the attributes by their value in descending order
		attributes.sort((a, b) -> Double.compare(b.getValue().getValue(), a.getValue().getValue()));

		// Build the attributeModifiers map
		ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
		for (Map.Entry<EntityAttribute, EntityAttributeModifier> entry : attributes) {
			builder.put(entry.getKey(), entry.getValue());
		}

		this.attributeModifiers = builder.build();
	}



	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
		return slot == EquipmentSlot.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
	}



}
