package nikita.uniquescythe.entities.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.entities.custom.BreezeEntity;


@Environment(value=EnvType.CLIENT)
public class BreezeEyebrowsFeatureRenderer
	extends FeatureRenderer<BreezeEntity, BreezeEntityModel<BreezeEntity>> {
	private static final Identifier TEXTURE = new Identifier("uniquescythe","textures/entity/breeze/breeze.png");
	private static final RenderLayer LAYER = RenderLayer.getEntityTranslucent(TEXTURE);
	private static final BreezeEntityModel<BreezeEntity> model = new BreezeEntityModel(BreezeEntityModel.getTexturedModelData().createModel());

	public BreezeEyebrowsFeatureRenderer(FeatureRendererContext<BreezeEntity, BreezeEntityModel<BreezeEntity>> breezeModel) {
		super(breezeModel);
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, BreezeEntity breezeEntity, float f, float g, float h, float j, float k, float l) {

		VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(LAYER);
		this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1F);


		this.model.setAngles(breezeEntity, f, g, j, k, l);
		BreezeEntityRenderer.updatePartVisibility(model, model.getEyebrows()).render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);

	}

}


