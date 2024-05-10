package nikita.uniquescythe.geo.renderers;

import mod.azure.azurelib.model.DefaultedItemGeoModel;
import mod.azure.azurelib.renderer.GeoArmorRenderer;
import mod.azure.azurelib.renderer.GeoItemRenderer;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.geo.models.JusticeRevolverModel;
import nikita.uniquescythe.items.custom.JusticeArmorItem;
import nikita.uniquescythe.items.custom.JusticeRevolverItem;


public final class JusticeRevolverRenderer extends GeoItemRenderer<JusticeRevolverItem> {
	public JusticeRevolverRenderer() {
		super(new JusticeRevolverModel());

	}

}
