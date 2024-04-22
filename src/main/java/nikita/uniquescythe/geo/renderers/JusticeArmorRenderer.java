package nikita.uniquescythe.geo.renderers;

import mod.azure.azurelib.model.DefaultedItemGeoModel;
import mod.azure.azurelib.renderer.GeoArmorRenderer;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.JusticeArmorItem;


public final class JusticeArmorRenderer extends GeoArmorRenderer<JusticeArmorItem> {
	public JusticeArmorRenderer() {
		super(new DefaultedItemGeoModel<>(new Identifier(UniqueScythe.MOD_ID, "armor/justice_armor")));
	}
}
