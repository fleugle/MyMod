package nikita.uniquescythe.geo.renderers;


import mod.azure.azurelib.model.DefaultedEntityGeoModel;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.entities.custom.JusticeBulletEntity;


public final class JusticeBulletRenderer extends GeoEntityRenderer<JusticeBulletEntity> {

	public JusticeBulletRenderer(EntityRendererFactory.Context renderManager) {
		super(renderManager, new DefaultedEntityGeoModel<>(new Identifier(UniqueScythe.MOD_ID, "justice_bullet")));
	}

	@Override
	public boolean hasLabel(JusticeBulletEntity animatable) {
		return false;
	}
}
