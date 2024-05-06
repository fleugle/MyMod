package nikita.uniquescythe.geo.renderers;

import mod.azure.azurelib.model.DefaultedEntityGeoModel;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.entities.custom.BulletEntity;

public class BulletEntityRenderer extends GeoEntityRenderer<BulletEntity> {

	public BulletEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new DefaultedEntityGeoModel<>(new Identifier(UniqueScythe.MOD_ID, "copper_bullet")));

	}
}
