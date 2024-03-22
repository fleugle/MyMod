package nikita.uniquescythe.entities.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.SlimeOverlayFeatureRenderer;
import net.minecraft.client.render.entity.model.CamelEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.CamelEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Axis;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.util.math.BlockPos;
import nikita.uniquescythe.entities.animation.ModAnimations;
import nikita.uniquescythe.entities.custom.BreezeEntity;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;

@Environment(EnvType.CLIENT)
public class BreezeEntityRenderer extends MobEntityRenderer<BreezeEntity, BreezeEntityModel<BreezeEntity>> {
	private static final Identifier TEXTURE = new Identifier("uniquescythe","textures/entity/breeze/breeze.png"); // this is your texture
	private static final RenderLayer LAYER = RenderLayer.getEntityTranslucent(TEXTURE);


	public BreezeEntityRenderer(EntityRendererFactory.Context context) {

		super(context, new BreezeEntityModel<>(context.getPart(ModModelLayers.BREEZE)), 0.7F);
		this.addFeature(new BreezeWindFeatureRenderer(this));

	}

	@Override
	public void render(BreezeEntity breezeEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {



		BreezeEntityModel breezeEntityModel = (BreezeEntityModel)this.getModel();
		BreezeEntityRenderer.updatePartVisibility(breezeEntityModel, breezeEntityModel.getBody(), breezeEntityModel.getHead());


		super.render(breezeEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}

	public Identifier getTexture(BreezeEntity breezeEntity) {
		return TEXTURE;
	}

	public static BreezeEntityModel<BreezeEntity> updatePartVisibility(BreezeEntityModel<BreezeEntity> model, ModelPart... modelParts) {
		model.getHead().visible = false;
		model.getBody().visible = false;
		model.getWindBody().visible = false;
		for (ModelPart modelPart : modelParts) {
			modelPart.visible = true;
		}
		return model;
	}
}
