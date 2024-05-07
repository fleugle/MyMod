package nikita.uniquescythe.geo.renderers;

import mod.azure.azurelib.model.GeoModel;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;
import nikita.uniquescythe.geo.models.WindChargeModel;

public class WindChargeRenderer extends GeoEntityRenderer<WindChargeProjectileEntity> {

	public WindChargeRenderer(EntityRendererFactory.Context renderManager) {
		super(renderManager, new WindChargeModel());
	}

	@Override
	public boolean hasLabel(WindChargeProjectileEntity animatable) {
		return false;
	}
}
