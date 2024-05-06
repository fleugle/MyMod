package nikita.uniquescythe.entities.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.azure.azurelib.model.DefaultedEntityGeoModel;
import mod.azure.azurelib.renderer.GeoEntityRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.entities.custom.BulletEntity;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;
import nikita.uniquescythe.utility.ModRenderLayers;

public class BulletEntityRenderer extends EntityRenderer<BulletEntity> {

	private static final Identifier TEXTURE = new Identifier(UniqueScythe.MOD_ID,"textures/entity/copper_bullet.png"); // this is your texture

	//private static final RenderLayer TRANSLUCENT_TEXTURE = RenderLayer.getEntityTranslucent(TEXTURE);

	private final BulletEntityModel model; //gets the model

	public BulletEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.model = new BulletEntityModel(context.getPart(ModModelLayers.BULLET)); //tells the model to get the part you want
	}




	public void render(BulletEntity BulletEntityModel, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		//matrixStack.push(); //you'll need a push and pop to add the model
		// Apply animations here
		float h = (float)BulletEntityModel.age + g;
		VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(ModRenderLayers.getEntityTranslucent(TEXTURE/*, this.getXOffset(h) % 1.0f, 0.0f)*/)); //applies the texture to the model
		this.model.setAngles(BulletEntityModel, 0.0f, 0.0f, h, 0.0f, 0.0f);
		this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F); //actually renders shit
		//matrixStack.pop();



		super.render(BulletEntityModel, f, g, matrixStack, vertexConsumerProvider, i);
	}

	protected float getXOffset(float tickDelta) {
		return tickDelta * 0.03f;
	}
	@Override
	public Identifier getTexture(BulletEntity bulletEntity) {
		return TEXTURE;
	}


}
