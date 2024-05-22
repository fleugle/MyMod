package nikita.uniquescythe.items.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class SimpleTalismanItem extends Item {

	public final float attackDamage;
	public final float attackSpeed;
	public final float movementSpeed;
	public final float resistance;
	public final float maxHealth;

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
		float resistance,
		float maxHealth,
		Settings settings
	) {
		super(settings);
		this.attackDamage = attackDamage;
		this.attackSpeed = attackSpeed;
		this.movementSpeed = movementSpeed;
		this.resistance = resistance;
		this.maxHealth = maxHealth;



		ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(
			EntityAttributes.GENERIC_ATTACK_DAMAGE,
			new EntityAttributeModifier(TALISMAN_DAMAGE_MODIFIER_ID, "Talisman modifier", (double)this.attackDamage, EntityAttributeModifier.Operation.ADDITION)
		);
		builder.put(
			EntityAttributes.GENERIC_ATTACK_SPEED,
			new EntityAttributeModifier(TALISMAN_ATTACK_SPEED_MODIFIER_ID, "Talisman modifier", (double)this.attackSpeed, EntityAttributeModifier.Operation.ADDITION)
		);
		builder.put(
			EntityAttributes.GENERIC_MAX_HEALTH,
			new EntityAttributeModifier(TALISMAN_MAX_HEALTH_MODIFIER_ID, "Talisman modifier", (double)this.maxHealth, EntityAttributeModifier.Operation.ADDITION)
		);
		builder.put(
			EntityAttributes.GENERIC_ARMOR,
			new EntityAttributeModifier(TALISMAN_RESISTANCE_MODIFIER_ID, "Talisman modifier", (double)this.resistance, EntityAttributeModifier.Operation.ADDITION)
		);
		builder.put(
			EntityAttributes.GENERIC_MOVEMENT_SPEED,
			new EntityAttributeModifier(TALISMAN_MOVEMENT_SPEED_MODIFIER_ID, "Talisman modifier", (double)this.movementSpeed, EntityAttributeModifier.Operation.ADDITION)
		);
		this.attributeModifiers = builder.build();
	}


	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(Text.literal(""));

		tooltip.add(Text.literal("Talisman"));
		if (this.attackDamage != 0
			|| this.attackSpeed != 0
			|| this.movementSpeed != 0
			|| this.maxHealth != 0
			|| this.resistance != 0
		) {
			tooltip.add(Text.literal("§7Grants buffs:"));
			if (this.attackDamage != 0) {
				tooltip.add(Text.literal("§5 + "+this.attackDamage+" attack damage"));
			}
			if (this.attackSpeed != 0) {
				tooltip.add(Text.literal("§5 + "+this.attackSpeed+" attack speed"));
			}
			if (this.movementSpeed != 0) {
				tooltip.add(Text.literal("§5 + "+this.movementSpeed+" movement speed"));
			}
			if (this.maxHealth != 0) {
				tooltip.add(Text.literal("§5 + "+this.maxHealth+" maximum health"));
			}
			if (this.resistance != 0) {
				tooltip.add(Text.literal("§5 + "+this.resistance+" damage resistance"));
			}
		}


		super.appendTooltip(stack, world, tooltip, context);
	}



	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
		return slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
	}

}
