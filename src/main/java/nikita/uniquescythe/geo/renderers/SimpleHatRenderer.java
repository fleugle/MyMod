package nikita.uniquescythe.geo.renderers;

import mod.azure.azurelib.model.DefaultedItemGeoModel;
import mod.azure.azurelib.renderer.GeoArmorRenderer;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.SimpleHatItem;


public final class SimpleHatRenderer extends GeoArmorRenderer<SimpleHatItem> {
	public SimpleHatRenderer() {
		super(new DefaultedItemGeoModel<>(new Identifier(UniqueScythe.MOD_ID, "armor/simple_hat")));

	}
}
