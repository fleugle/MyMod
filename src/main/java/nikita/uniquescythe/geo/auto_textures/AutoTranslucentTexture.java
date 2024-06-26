package nikita.uniquescythe.geo.auto_textures;


import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderCall;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.texture.NativeImage;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormats;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Function;

import mod.azure.azurelib.cache.texture.GeoAbstractTexture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.resource.metadata.TextureResourceMetadata;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;



import static net.minecraft.client.render.RenderPhase.*;


public class AutoTranslucentTexture extends GeoAbstractTexture {
	protected final Identifier textureBase;

	private static final RenderPhase.Shader SHADER_STATE = new RenderPhase.Shader(GameRenderer::getRenderTypeEntityTranslucentShader);
	private static final RenderPhase.Transparency TRANSPARENCY_STATE = new RenderPhase.Transparency("translucent_transparency", () -> {
		RenderSystem.enableBlend();
		RenderSystem.disableCull();
		RenderSystem.defaultBlendFunc();
		//RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	}, () -> {
		RenderSystem.enableCull();
		RenderSystem.disableBlend();
		RenderSystem.defaultBlendFunc();
	});

	private static final Function<Identifier, RenderLayer> ENTITY_TRANSLUCENT = Util.memoize((texture) -> {
		RenderPhase.Texture textureState = new RenderPhase.Texture(texture, false, false);
		return RenderLayer.of("geo_translucent_layer",
			VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL,
			VertexFormat.DrawMode.QUADS,
			256,
			false,
			true,
			RenderLayer.MultiPhaseParameters.builder()
				.shader(SHADER_STATE)
				.texture(textureState)
				.transparency(TRANSPARENCY_STATE)
				.build(false));
	});


	public AutoTranslucentTexture(Identifier originalLocation) {
		this.textureBase = originalLocation;
	}






	@Override
	protected @Nullable RenderCall loadTexture(ResourceManager resourceManager, MinecraftClient mc) throws IOException {
		AbstractTexture originalTexture;
		try {
			originalTexture = (AbstractTexture) mc.submit(() -> mc.getTextureManager().getTexture(this.textureBase)).get();
		} catch (ExecutionException | InterruptedException e) {
			throw new IOException("Failed to load original texture: " + this.textureBase, e);
		}

		Resource textureBaseResource = (Resource) resourceManager.getResource(this.textureBase).get();
		NativeImage baseImage;
		if (originalTexture instanceof NativeImageBackedTexture dynamicTexture) {
			baseImage = dynamicTexture.getImage();
		} else {
			baseImage = NativeImage.read(textureBaseResource.open());
		}

		Optional<TextureResourceMetadata> textureBaseMeta = textureBaseResource.getMetadata().readMetadata(TextureResourceMetadata.READER);
		boolean blur = textureBaseMeta.isPresent() && ((TextureResourceMetadata) textureBaseMeta.get()).shouldBlur();
		boolean clamp = textureBaseMeta.isPresent() && ((TextureResourceMetadata) textureBaseMeta.get()).shouldClamp();

		return () -> uploadSimple(originalTexture.getGlId(), baseImage, blur, clamp);
	}

	public static RenderLayer getEntityTranslucent(Identifier texture) {
		return ENTITY_TRANSLUCENT.apply(texture);
	}
}
