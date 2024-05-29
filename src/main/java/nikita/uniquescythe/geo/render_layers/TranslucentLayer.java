package nikita.uniquescythe.geo.render_layers;

import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.azure.azurelib.cache.object.BakedGeoModel;
import mod.azure.azurelib.renderer.GeoRenderer;
import mod.azure.azurelib.renderer.layer.GeoRenderLayer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;
import nikita.uniquescythe.geo.auto_textures.AutoTranslucentTexture;

public class TranslucentLayer<T extends WindChargeProjectileEntity> extends GeoRenderLayer<T> {
	public TranslucentLayer(GeoRenderer<T> renderer) {
		super(renderer);
	}

	protected RenderLayer getRenderType(T animatable) {
		return AutoTranslucentTexture.getRenderType(this.getTextureResource(animatable));
	}

	public void render(MatrixStack poseStack, T animatable, BakedGeoModel bakedModel, RenderLayer renderType, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
		RenderLayer translucentRenderType = this.getRenderType(animatable);
		this.getRenderer().reRender(bakedModel, poseStack, bufferSource, animatable, translucentRenderType, bufferSource.getBuffer(translucentRenderType), partialTick, -100, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1F);
	}
}
