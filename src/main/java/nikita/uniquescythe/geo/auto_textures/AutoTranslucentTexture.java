package nikita.uniquescythe.geo.auto_textures;


import com.mojang.blaze3d.systems.RenderCall;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormats;
import java.io.IOException;
import java.util.Optional;
import java.util.function.BiFunction;
import mod.azure.azurelib.cache.texture.GeoAbstractTexture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;



import static net.minecraft.client.render.RenderPhase.*;


public class AutoTranslucentTexture extends GeoAbstractTexture {




	private static final BiFunction<Identifier, Boolean, RenderLayer> ENTITY_TRANSLUCENT = Util.memoize(
		(BiFunction)((texture, affectsOutline) -> {
			RenderLayer.MultiPhaseParameters multiPhaseParameters = RenderLayer.MultiPhaseParameters.builder()
				.shader(ENTITY_TRANSLUCENT_SHADER)
				.texture(new RenderPhase.Texture((Identifier) texture, false, false))
				.transparency(TRANSLUCENT_TRANSPARENCY)
				.cull(DISABLE_CULLING)
				.lightmap(ENABLE_LIGHTMAP)
				.overlay(ENABLE_OVERLAY_COLOR)
				.build((Boolean) affectsOutline);
			return RenderLayer.of(
				"entity_translucent", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, true, multiPhaseParameters
			);
		})
	);





	public static RenderLayer getEntityTranslucent(Identifier texture, boolean affectsOutline) {
		return ENTITY_TRANSLUCENT.apply(texture, affectsOutline);
	}

	@Override
	protected @Nullable RenderCall loadTexture(ResourceManager resourceManager, MinecraftClient minecraftClient) throws IOException {
		return null;
	}
}
