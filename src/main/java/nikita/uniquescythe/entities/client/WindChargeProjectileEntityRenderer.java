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
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;

public class WindChargeProjectileEntityRenderer extends EntityRenderer<WindChargeProjectileEntity> {


	private static final Identifier TEXTURE = new Identifier("uniquescythe","textures/entity/wind_charge.png"); // this is your texture

	private static final RenderLayer TRANSLUCENT_TEXTURE = RenderLayer.getEntityTranslucent(TEXTURE);

	private final WindChargeProjectileEntityModel<WindChargeProjectileEntity> model; //gets the model

	public WindChargeProjectileEntityRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.model = new WindChargeProjectileEntityModel<>(context.getPart(ModModelLayers.WIND_CHARGE)); //tells the model to get the part you want
	}


	protected int getBlockLight(WindChargeProjectileEntity windChargeProjectileEntity, BlockPos blockPos) {
		return 15;
	}

	public void render(WindChargeProjectileEntity windChargeProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		matrixStack.push(); //you'll need a push and pop to add the model
		// Apply animations here
		this.model.setAngles(windChargeProjectileEntity,1,1,2000,1,1);

		VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(TRANSLUCENT_TEXTURE); //applies the texture to the model
		this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F); //actually renders shit
		matrixStack.pop();



		super.render(windChargeProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}

	@Override
	public Identifier getTexture(WindChargeProjectileEntity windChargeProjectileEntity) {
		return TEXTURE;
	}
}
