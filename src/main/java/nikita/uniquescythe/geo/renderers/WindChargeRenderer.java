package nikita.uniquescythe.geo.renderers;


import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;
import nikita.uniquescythe.geo.models.WindChargeModel;
import nikita.uniquescythe.geo.render_layers.TranslucentLayer;

public class WindChargeRenderer extends GeoEntityRenderer<WindChargeProjectileEntity> {

	public WindChargeRenderer(EntityRendererFactory.Context renderManager) {
		super(renderManager, new WindChargeModel());
		addRenderLayer(new TranslucentLayer<>(this));
	}

	@Override
	public boolean hasLabel(WindChargeProjectileEntity animatable) {
		return false;
	}
}
