package nikita.uniquescythe.geo.models;

import mod.azure.azurelib.model.GeoModel;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.items.custom.InnocentNimbusItem;

public class InnocentNimbusModel extends GeoModel<InnocentNimbusItem> {
	// Models must be stored in assets/<modid>/geo with subfolders supported inside the geo folder
	private static final Identifier model = new Identifier("uniquescythe", "geo/item/armor/innocent_nimbus.geo.json");
	// Textures must be stored in assets/<modid>/textures with subfolders supported inside the textures folder
	private static final Identifier texture = new Identifier("uniquescythe", "textures/armor/innocent_nimbus.png");
	// Animations must be stored in assets/<modid>/animations with subfolders supported inside the animations folder
	private static final Identifier animation = new Identifier("uniquescythe", "animations/item/armor/innocent_nimbus.animation.json");

	@Override
	public Identifier getModelResource(InnocentNimbusItem object) {
		return this.model;
	}

	@Override
	public Identifier getTextureResource(InnocentNimbusItem object) {
		return this.texture;
	}

	@Override
	public Identifier getAnimationResource(InnocentNimbusItem object) {
		return this.animation;
	}
}
