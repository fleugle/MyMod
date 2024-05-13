package nikita.uniquescythe.items.custom;


import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.SingletonGeoAnimatable;
import mod.azure.azurelib.animatable.client.RenderProvider;

import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.geo.renderers.JusticeRevolverRenderer;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.sounds.ModSounds;

import java.util.function.Consumer;
import java.util.function.Supplier;


public class JusticeRevolverItem extends GunItem {


	public JusticeRevolverItem(ToolMaterial toolMaterial, Settings properties) {
		super(
			toolMaterial,
			6,
			5,
			60,
			50D,
			"§6Hey pardner, you'll need some bullets for that!",
			properties);
		SingletonGeoAnimatable.registerSyncedAnimatable(this);
	}

	@Override
	public Item getAmmoItem(){
		return ModItems.JUSTICE_BULLET;
	}

	@Override
	public SoundEvent getShootingSound(){
		return ModSounds.JUSTICE_SHOOT;
	}






	//AZURELIB PART

	private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

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

