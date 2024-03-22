package nikita.uniquescythe.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.entities.custom.BreezeEntity;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;

public class ModEntities {

	public static final EntityType<WindChargeProjectileEntity> WIND_CHARGE_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
		new Identifier(UniqueScythe.MOD_ID, "wind_charge_projectile"),
		FabricEntityTypeBuilder.<WindChargeProjectileEntity>create(SpawnGroup.MISC, WindChargeProjectileEntity :: new)
			.dimensions(EntityDimensions.fixed(0.5f, 0.25f)).build());


	public static final EntityType<BreezeEntity> BREEZE = Registry.register(Registries.ENTITY_TYPE,
		new Identifier(UniqueScythe.MOD_ID, "breeze"),
		FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BreezeEntity :: new)
			.dimensions(EntityDimensions.fixed(1f,2f)).build());
}
