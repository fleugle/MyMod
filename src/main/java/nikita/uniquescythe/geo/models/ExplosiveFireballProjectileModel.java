package nikita.uniquescythe.geo.models;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.entities.custom.ExplosiveFireballProjectileEntity;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;

public class ExplosiveFireballProjectileModel extends GeoModel<ExplosiveFireballProjectileEntity> {
	@Override
	public Identifier getModelResource(ExplosiveFireballProjectileEntity explosiveFireballProjectileEntity) {
		return new Identifier(UniqueScythe.MOD_ID, "geo/entity/explosive_fireball_projectile.geo.json");
	}

	@Override
	public Identifier getTextureResource(ExplosiveFireballProjectileEntity explosiveFireballProjectileEntity) {
		return new Identifier(UniqueScythe.MOD_ID, "textures/entity/explosive_fireball_projectile_entity.png");
	}

	@Override
	public Identifier getAnimationResource(ExplosiveFireballProjectileEntity explosiveFireballProjectileEntity) {
		return new Identifier(UniqueScythe.MOD_ID, "animations/entity/explosive_fireball.animation.json");
	}
}
