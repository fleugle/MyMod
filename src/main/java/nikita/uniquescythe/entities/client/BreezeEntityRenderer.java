package nikita.uniquescythe.entities.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Axis;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.util.math.BlockPos;
import nikita.uniquescythe.entities.animation.ModAnimations;
import nikita.uniquescythe.entities.custom.BreezeEntity;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;

public class BreezeEntityRenderer extends EntityRenderer<BreezeEntity> {


	private static final Identifier TEXTURE = new Identifier("uniquescythe","textures/entity/breeze/breeze.png"); // this is your texture

	private static final RenderLayer TRANSLUCENT_TEXTURE = RenderLayer.getEntityTranslucent(TEXTURE);

	private final BreezeEntityModel<BreezeEntity> model; //gets the model

	public BreezeEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.model = new BreezeEntityModel<>(context.getPart(ModModelLayers.BREEZE)); //tells the model to get the part you want
	}


	protected int getBlockLight(BreezeEntity breezeEntity, BlockPos blockPos) {
		return 15;
	}

	public void render(BreezeEntity breezeEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		matrixStack.push(); //you'll need a push and pop to add the model
		// Apply animations here
		this.model.setAngles(breezeEntity,1,1,2000,1,1);

		VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(TRANSLUCENT_TEXTURE); //applies the texture to the model
		this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F); //actually renders shit
		matrixStack.pop();



		super.render(breezeEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}

	@Override
	public Identifier getTexture(BreezeEntity breezeEntity) {
		return TEXTURE;
	}
}
