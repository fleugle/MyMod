package nikita.uniquescythe.geo.renderers;

import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.geo.models.JusticeArmorModel;
import nikita.uniquescythe.items.custom.JusticeArmorItem;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class JusticeArmorRenderer extends GeoArmorRenderer<JusticeArmorItem> {
	public JusticeArmorRenderer(GeoModel<JusticeArmorItem> model) {
		super(new JusticeArmorModel());
	}
}