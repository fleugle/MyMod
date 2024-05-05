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
import nikita.uniquescythe.entities.custom.BulletEntity;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

public class ModEntities {

	public static final EntityType<WindChargeProjectileEntity> WIND_CHARGE_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
		new Identifier(UniqueScythe.MOD_ID, "wind_charge_projectile"),
		QuiltEntityTypeBuilder.<WindChargeProjectileEntity>create(SpawnGroup.MISC, WindChargeProjectileEntity :: new)
			.setDimensions(EntityDimensions.fixed(0.5f, 0.25f)).build());


	public static final EntityType<BreezeEntity> BREEZE = Registry.register(Registries.ENTITY_TYPE,
		new Identifier(UniqueScythe.MOD_ID, "breeze"),
		QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, BreezeEntity :: new)
			.setDimensions(EntityDimensions.fixed(1f,2f)).build());

	public static final EntityType<BulletEntity> BULLET_ENTITY = Registry.register(Registries.ENTITY_TYPE,
		new Identifier(UniqueScythe.MOD_ID, "bullet"),
		QuiltEntityTypeBuilder.<BulletEntity>create(SpawnGroup.MISC, BulletEntity :: new)
			.setDimensions(EntityDimensions.fixed(0.5f, 0.25f)).build());


}
