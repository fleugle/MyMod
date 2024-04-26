package nikita.uniquescythe.geo.renderers;

import mod.azure.azurelib.model.DefaultedItemGeoModel;
import mod.azure.azurelib.renderer.GeoArmorRenderer;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.InnocentNimbusItem;
import nikita.uniquescythe.items.custom.JusticeArmorItem;


public final class InnocentNimbusRenderer extends GeoArmorRenderer<InnocentNimbusItem> {
	public InnocentNimbusRenderer() {
		super(new DefaultedItemGeoModel<>(new Identifier(UniqueScythe.MOD_ID, "armor/innocent_nimbus")));
	}
}
