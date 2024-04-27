package nikita.uniquescythe.geo.renderers;

import mod.azure.azurelib.cache.object.GeoBone;
import mod.azure.azurelib.core.animatable.GeoAnimatable;
import mod.azure.azurelib.model.DefaultedItemGeoModel;
import mod.azure.azurelib.renderer.GeoArmorRenderer;
import mod.azure.azurelib.renderer.GeoRenderer;
import mod.azure.azurelib.renderer.layer.AutoGlowingGeoLayer;
import mod.azure.azurelib.renderer.layer.ItemArmorGeoLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.custom.InnocentNimbusItem;
import nikita.uniquescythe.items.custom.JusticeArmorItem;
import org.jetbrains.annotations.Nullable;


public final class InnocentNimbusRenderer extends GeoArmorRenderer<InnocentNimbusItem> {
	public InnocentNimbusRenderer() {
		super(new DefaultedItemGeoModel<>(new Identifier(UniqueScythe.MOD_ID, "armor/innocent_nimbus")));

		// Add the armor layer

		addRenderLayer(new AutoGlowingGeoLayer<>(this));

		//end
	}
}
