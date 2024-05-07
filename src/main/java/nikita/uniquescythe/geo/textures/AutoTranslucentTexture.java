package nikita.uniquescythe.geo.textures;


import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderCall;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.texture.NativeImage;
import com.mojang.blaze3d.vertex.VertexFormats;
import com.mojang.blaze3d.vertex.VertexFormat.DrawMode;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import mod.azure.azurelib.AzureLib;
import mod.azure.azurelib.cache.texture.GeoAbstractTexture;
import mod.azure.azurelib.platform.Services;
import mod.azure.azurelib.resource.GeoGlowingTextureMeta;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.RenderLayer.MultiPhaseParameters;
import net.minecraft.client.resource.metadata.TextureResourceMetadata;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;

public class AutoTranslucentTexture extends GeoAbstractTexture {

	private static final RenderPhase.Shader SHADER_STATE = new RenderPhase.Shader(GameRenderer::getRenderTypeEntityTranslucentEmissiveShader);
	private static final RenderPhase.Transparency TRANSPARENCY_STATE = new RenderPhase.Transparency("translucent_transparency", () -> {
		RenderSystem.enableBlend();
		RenderSystem.disableCull();
		RenderSystem.blendFuncSeparate(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA, SourceFactor.ONE, DestFactor.ONE_MINUS_SRC_ALPHA);
	}, () -> {
		RenderSystem.disableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableCull();
	});
	private static final RenderPhase.WriteMaskState WRITE_MASK = new RenderPhase.WriteMaskState(true, true);
	private static final Function<Identifier, RenderLayer> RENDER_TYPE_FUNCTION = Util.memoize((texture) -> {
		RenderPhase.Texture textureState = new RenderPhase.Texture(texture, false, false);
		return RenderLayer.of("geo_glowing_layer", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, DrawMode.QUADS, 256, false, true, MultiPhaseParameters.builder().shader(SHADER_STATE).texture(textureState).transparency(TRANSPARENCY_STATE).writeMaskState(WRITE_MASK).build(false));
	});

	protected final Identifier textureBase;
	protected final Identifier glowLayer;

	public AutoTranslucentTexture(Identifier originalLocation, Identifier location) {
		this.textureBase = originalLocation;
		this.glowLayer = location;
	}

	protected static Identifier getEmissiveResource(Identifier baseResource) {
		Identifier path = appendToPath(baseResource, "_transparencymask");
		generateTexture(path, (textureManager) -> {
			textureManager.registerTexture(path, new mod.azure.azurelib.cache.texture.AutoGlowingTexture(baseResource, path));
		});
		return path;
	}

	protected @Nullable RenderCall loadTexture(ResourceManager resourceManager, MinecraftClient mc) throws IOException {
		AbstractTexture originalTexture;
		try {
			originalTexture = (AbstractTexture)mc.submit(() -> {
				return mc.getTextureManager().getTexture(this.textureBase);
			}).get();
		} catch (ExecutionException | InterruptedException var14) {
			Exception e = var14;
			throw new IOException("Failed to load original texture: " + this.textureBase, e);
		}

		Resource textureBaseResource = (Resource)resourceManager.getResource(this.textureBase).get();
		NativeImage var10000;
		if (originalTexture instanceof NativeImageBackedTexture dynamicTexture) {
			var10000 = dynamicTexture.getImage();
		} else {
			var10000 = NativeImage.read(textureBaseResource.open());
		}

		NativeImage baseImage = var10000;
		NativeImage glowImage = null;
		Optional<TextureResourceMetadata> textureBaseMeta = textureBaseResource.getMetadata().readMetadata(TextureResourceMetadata.READER);
		boolean blur = textureBaseMeta.isPresent() && ((TextureResourceMetadata)textureBaseMeta.get()).shouldBlur();
		boolean clamp = textureBaseMeta.isPresent() && ((TextureResourceMetadata)textureBaseMeta.get()).shouldClamp();

		try {
			Optional<Resource> glowLayerResource = resourceManager.getResource(this.glowLayer);
			GeoGlowingTextureMeta glowLayerMeta = null;
			if (glowLayerResource.isPresent()) {
				glowImage = NativeImage.read(((Resource)glowLayerResource.get()).open());
				glowLayerMeta = GeoGlowingTextureMeta.fromExistingImage(glowImage);
			} else {
				Optional<GeoGlowingTextureMeta> meta = textureBaseResource.getMetadata().readMetadata(GeoGlowingTextureMeta.DESERIALIZER);
				if (meta.isPresent()) {
					glowLayerMeta = (GeoGlowingTextureMeta)meta.get();
					glowImage = new NativeImage(baseImage.getWidth(), baseImage.getHeight(), true);
				}
			}

			if (glowLayerMeta != null) {
				glowLayerMeta.createImageMask(baseImage, glowImage);
				if (Services.PLATFORM.isDevelopmentEnvironment()) {
					this.printDebugImageToDisk(this.textureBase, baseImage);
					this.printDebugImageToDisk(this.glowLayer, glowImage);
				}
			}
		} catch (IOException var13) {
			IOException e = var13;
			AzureLib.LOGGER.warn("Resource failed to open for glowlayer meta: {}", this.glowLayer, e);
		}

		NativeImage mask = glowImage;
		return mask == null ? null : () -> {
			uploadSimple(this.getGlId(), mask, blur, clamp);
			if (originalTexture instanceof NativeImageBackedTexture dynamicTexture) {
				dynamicTexture.upload();
			} else {
				uploadSimple(originalTexture.getGlId(), baseImage, blur, clamp);
			}

		};
	}

	public static RenderLayer getRenderType(Identifier texture) {
		return (RenderLayer)RENDER_TYPE_FUNCTION.apply(getEmissiveResource(texture));
	}
}
