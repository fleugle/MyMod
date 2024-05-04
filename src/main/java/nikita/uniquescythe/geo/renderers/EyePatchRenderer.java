package nikita.uniquescythe.geo.renderers;

import mod.azure.azurelib.model.DefaultedItemGeoModel;
import mod.azure.azurelib.renderer.GeoArmorRenderer;
import mod.azure.azurelib.renderer.layer.AutoGlowingGeoLayer;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.EyePatchItem;


public final class EyePatchRenderer extends GeoArmorRenderer<EyePatchItem> {
	public EyePatchRenderer() {
		super(new DefaultedItemGeoModel<>(new Identifier(UniqueScythe.MOD_ID, "armor/eye_patch")));

		// Add the armor layer
		//addRenderLayer(new AutoGlowingGeoLayer<>(this));
		//end
	}
}
