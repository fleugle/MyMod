package nikita.uniquescythe.geo.models;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;
import nikita.uniquescythe.items.custom.JusticeRevolverItem;

public class JusticeRevolverModel extends GeoModel<JusticeRevolverItem> {
	@Override
	public Identifier getModelResource(JusticeRevolverItem windChargeProjectileEntity) {
		return new Identifier(UniqueScythe.MOD_ID, "geo/item/justice_revolver.geo.json");
	}

	@Override
	public Identifier getTextureResource(JusticeRevolverItem windChargeProjectileEntity) {
		return new Identifier(UniqueScythe.MOD_ID, "textures/item/justice_revolver_big.png");
	}

	@Override
	public Identifier getAnimationResource(JusticeRevolverItem windChargeProjectileEntity) {
		return new Identifier(UniqueScythe.MOD_ID, "animations/item/justice_revolver/justice_revolver.animation.json");
	}
}
