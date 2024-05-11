package nikita.uniquescythe.geo.renderers;

import mod.azure.azurelib.model.DefaultedItemGeoModel;
import mod.azure.azurelib.renderer.GeoItemRenderer;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.TestAnimatedItem;


public class TestAnimatedItemRenderer extends GeoItemRenderer<TestAnimatedItem> {
	public TestAnimatedItemRenderer() {
		super(new DefaultedItemGeoModel<>(new Identifier(UniqueScythe.MOD_ID, "justice_revolver")));
	}
}

