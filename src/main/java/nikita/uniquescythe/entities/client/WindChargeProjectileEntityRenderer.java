package nikita.uniquescythe.entities.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.render.OverlayTexture;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;

public class WindChargeProjectileEntityRenderer extends FlyingItemEntityRenderer<WindChargeProjectileEntity> {

	public WindChargeProjectileEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
	}
	@Override
	public void render(WindChargeProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		matrices.push();
		matrices.scale(1, 1, 1);
		matrices.multiply(this.dispatcher.getRotation());
		matrices.multiply(Axis.Y_POSITIVE.rotationDegrees(180.0F));

		// Get the model data for your WindChargeProjectileEntityModel
		WindChargeProjectileEntityModel<WindChargeProjectileEntity> model = new WindChargeProjectileEntityModel<>(WindChargeProjectileEntityModel.getTexturedModelData().createModel());

		// Render the model
		model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(this.getTexture(entity))), light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

		matrices.pop();
	}
}
