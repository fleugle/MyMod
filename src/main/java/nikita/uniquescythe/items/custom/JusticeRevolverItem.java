package nikita.uniquescythe.items.custom;


import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.client.RenderProvider;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.util.AzureLibUtil;
import mod.azure.azurelib.util.RenderUtils;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import nikita.uniquescythe.geo.renderers.JusticeRevolverRenderer;
import nikita.uniquescythe.utility.ModToolMaterial;

import java.util.function.Consumer;
import java.util.function.Supplier;


public class JusticeRevolverItem extends Item implements GeoItem {
	private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
	private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);


	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return cache;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controllerName",
			event -> PlayState.CONTINUE));
	}


	@Override
	public void createRenderer(Consumer<Object> consumer) {
		// Accepts a consumer to create a new renderer
		consumer.accept(new RenderProvider() {
			// Your render class made above
			private JusticeRevolverRenderer renderer = new JusticeRevolverRenderer();

			@Override
			public BuiltinModelItemRenderer getCustomRenderer() {
				return this.renderer;
			}
		});
	}

	@Override
	public Supplier<Object> getRenderProvider() {
		// Returns the above renderProvider created above
		return renderProvider;
	}












	public JusticeRevolverItem(ModToolMaterial material, Settings settings) {
		super(settings);
	}







}

