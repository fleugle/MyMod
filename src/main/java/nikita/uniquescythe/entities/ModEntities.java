package nikita.uniquescythe.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;

public class ModEntities {

	public static final EntityType<WindChargeProjectileEntity> WIND_CHARGE_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
		new Identifier(UniqueScythe.MOD_ID, "wind_charge_projectile"),
		FabricEntityTypeBuilder.<WindChargeProjectileEntity>create(SpawnGroup.MISC, WindChargeProjectileEntity :: new)
			.dimensions(EntityDimensions.fixed(0.35f, 0.35f)).build());
}
