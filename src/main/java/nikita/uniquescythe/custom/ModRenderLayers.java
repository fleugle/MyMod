package nikita.uniquescythe.custom;

import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormats;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.util.Identifier;

public class ModRenderLayers extends RenderLayer {
	public ModRenderLayers(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
		super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
	}




	public static RenderLayer getBreezeWind(Identifier texture, float x, float y) {
		return RenderLayer.of("breeze_wind",
			VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL,
			VertexFormat.DrawMode.QUADS,
			1536,
			false,
			true,
			MultiPhaseParameters
				.builder()
				.texture(new RenderPhase.Texture(texture, false, false))
				.texturing(new RenderPhase.OffsetTexturing(x, y))
				.transparency(TRANSLUCENT_TRANSPARENCY)
				.cull(DISABLE_CULLING)
				.lightmap(ENABLE_LIGHTMAP)
				.overlay(DISABLE_OVERLAY_COLOR).build(false));
	}
}
