package nikita.uniquescythe;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.ExplosionEmitterParticle;
import net.minecraft.client.particle.ExplosionLargeParticle;
import net.minecraft.client.render.RenderLayer;
import nikita.uniquescythe.blocks.ModBlocks;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.entities.client.*;
import nikita.uniquescythe.particles.ModParticleTypes;
import nikita.uniquescythe.particles.custom.WindExplosionParticle;


public class UniqueScytheClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
		//wtf is that
		/* Adds our particle textures to vanilla's Texture Atlas so it can be shown properly.
		 * Modify the namespace and particle id accordingly.
		 *
		 * This is only used if you plan to add your own textures for the particle. Otherwise, remove  this.
		ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
			registry.register(new Identifier("modid", "particle/green_flame"));
		}));
		*/


		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.WIND_EXPLOSION, WindExplosionParticle.Factory::new);






		//mod model layers
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.WIND_CHARGE, WindChargeProjectileEntityModel::getTexturedModelData);//wind charge

		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BREEZE, BreezeEntityModel::getTexturedModelData);//breeze

		EntityRendererRegistry.register(ModEntities.WIND_CHARGE_PROJECTILE, WindChargeProjectileEntityRenderer::new);

		EntityRendererRegistry.register(ModEntities.BREEZE, BreezeEntityRenderer::new );








		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_GRATE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_GRATE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_GRATE, RenderLayer.getCutout());






	}
}
