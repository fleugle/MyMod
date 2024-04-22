package nikita.uniquescythe.geo.models;

import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.JusticeArmorItem;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class JusticeArmorModel extends GeoModel<JusticeArmorItem> {

	private final Identifier modelResource = new Identifier(UniqueScythe.MOD_ID, "geo/item/armor/justice_armor.geo.json");
	private final Identifier textureResource = new Identifier(UniqueScythe.MOD_ID, "textures/armor/justice_armor");





	@Override
	public Identifier getModelResource(JusticeArmorItem animatable) {
		return this.modelResource;
	}

	@Override
	public Identifier getTextureResource(JusticeArmorItem animatable) {
		return null;
	}

	@Override
	public Identifier getAnimationResource(JusticeArmorItem animatable) {
		return null;
	}
}
