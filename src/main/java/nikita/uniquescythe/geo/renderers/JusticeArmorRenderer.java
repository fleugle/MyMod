package nikita.uniquescythe.geo.renderers;

import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.JusticeArmorItem;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public final class JusticeArmorRenderer extends GeoArmorRenderer<JusticeArmorItem> {
	public JusticeArmorRenderer() {
		super(new DefaultedItemGeoModel<>(new Identifier(UniqueScythe.MOD_ID, "armor/justice_armor")));
	}
}
