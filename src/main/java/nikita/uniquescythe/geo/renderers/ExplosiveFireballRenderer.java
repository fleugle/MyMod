package nikita.uniquescythe.geo.renderers;


import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import mod.azure.azurelib.renderer.layer.AutoGlowingGeoLayer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.entities.custom.ExplosiveFireballProjectileEntity;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;
import nikita.uniquescythe.geo.models.ExplosiveFireballProjectileModel;
import nikita.uniquescythe.geo.models.WindChargeModel;
import nikita.uniquescythe.geo.render_layers.CustomGlowingGeoLayer;

public final class ExplosiveFireballRenderer extends GeoEntityRenderer<ExplosiveFireballProjectileEntity> {

	public ExplosiveFireballRenderer(EntityRendererFactory.Context renderManager) {
		super(renderManager, new ExplosiveFireballProjectileModel());
		addRenderLayer(new CustomGlowingGeoLayer<>(this));
	}

	@Override
	public boolean hasLabel(ExplosiveFireballProjectileEntity animatable) {
		return false;
	}
}
