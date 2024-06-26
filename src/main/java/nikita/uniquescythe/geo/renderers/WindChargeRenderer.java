package nikita.uniquescythe.geo.renderers;


import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;
import nikita.uniquescythe.geo.models.WindChargeModel;

public final class WindChargeRenderer extends GeoEntityRenderer<WindChargeProjectileEntity> {

	private static final Identifier TEXTURE = new Identifier("uniquescythe","textures/entity/wind_charge.png");
	private static final RenderLayer LAYER = RenderLayer.getEntityTranslucent(TEXTURE);

	public WindChargeRenderer(EntityRendererFactory.Context renderManager) {
		super(renderManager, new WindChargeModel());
	}

	@Override
	public void render(WindChargeProjectileEntity entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
		this.animatable = entity;
		VertexConsumer vertexConsumer = bufferSource.getBuffer(LAYER);
		this.defaultRender(poseStack, entity, bufferSource, (RenderLayer)null, vertexConsumer, entityYaw, partialTick, packedLight);
	}

	@Override
	public boolean hasLabel(WindChargeProjectileEntity animatable) {
		return false;
	}
}
