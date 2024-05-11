package nikita.uniquescythe.items.custom;

import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.SingletonGeoAnimatable;
import mod.azure.azurelib.animatable.client.RenderProvider;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;

import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.util.AzureLibUtil;

import net.minecraft.client.render.item.BuiltinModelItemRenderer;

import net.minecraft.item.Item;
import nikita.uniquescythe.geo.renderers.JusticeRevolverRenderer;
import nikita.uniquescythe.geo.renderers.TestAnimatedItemRenderer;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class TestAnimatedItem extends Item implements GeoItem {
	private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
	private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

	public TestAnimatedItem(Settings properties) {
		super(properties);

		// Register our item as server-side handled.
		// This enables both animation data syncing and server-side animation triggering
		SingletonGeoAnimatable.registerSyncedAnimatable(this);
	}

	// Utilise our own render hook to define our custom renderer
	@Override
	public void createRenderer(Consumer<Object> consumer) {
		consumer.accept(new RenderProvider() {
			private TestAnimatedItemRenderer renderer;

			@Override
			public BuiltinModelItemRenderer getCustomRenderer() {
				if (this.renderer == null)
					this.renderer = new TestAnimatedItemRenderer();

				return this.renderer;
			}
		});
	}

	@Override
	public Supplier<Object> getRenderProvider() {
		return this.renderProvider;
	}

	// Let's add our animation controller
	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controllerName",
			event -> PlayState.CONTINUE));
	}



	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
