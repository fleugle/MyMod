package nikita.uniquescythe.items.custom;


import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.SingletonGeoAnimatable;
import mod.azure.azurelib.animatable.client.RenderProvider;
import mod.azure.azurelib.items.BaseGunItem;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import nikita.uniquescythe.geo.renderers.JusticeRevolverRenderer;

import java.util.function.Consumer;
import java.util.function.Supplier;


public class JusticeRevolverItem extends BaseGunItem {
	private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

	public JusticeRevolverItem(Settings properties) {
		super(properties);
		SingletonGeoAnimatable.registerSyncedAnimatable(this);
	}


	@Override
	public void createRenderer(Consumer<Object> consumer) {

		// Accepts a consumer to create a new renderer
		consumer.accept(new RenderProvider() {
			// Your render class made above
			private JusticeRevolverRenderer renderer = null;

			@Override
			public BuiltinModelItemRenderer getCustomRenderer() {
				// Check if renderer is null, create a new instance if so
				if (renderer == null)
					return new JusticeRevolverRenderer();
				// Return the existing renderer if it's not null
				return this.renderer;
			}
		});
	}

	@Override
	public Supplier<Object> getRenderProvider() {
		return renderProvider;
	}
}

