package nikita.uniquescythe.geo.models;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;

public class WindChargeModel extends GeoModel<WindChargeProjectileEntity> {
	@Override
	public Identifier getModelResource(WindChargeProjectileEntity windChargeProjectileEntity) {
		return new Identifier(UniqueScythe.MOD_ID, "geo/entity/wind_charge.geo.json");
	}

	@Override
	public Identifier getTextureResource(WindChargeProjectileEntity windChargeProjectileEntity) {
		return new Identifier(UniqueScythe.MOD_ID, "textures/entity/wind_charge.png");
	}

	@Override
	public Identifier getAnimationResource(WindChargeProjectileEntity windChargeProjectileEntity) {
		return new Identifier(UniqueScythe.MOD_ID, "animations/entity/wind_charge.animation.json");
	}
}
