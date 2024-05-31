package nikita.uniquescythe;

import net.fabricmc.api.ClientModInitializer;

import org. quiltmc. qsl. block. extensions. api. client.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import nikita.uniquescythe.blocks.ModBlocks;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.entities.client.*;
import nikita.uniquescythe.geo.renderers.JusticeBulletRenderer;
import nikita.uniquescythe.geo.renderers.WindChargeRenderer;
import nikita.uniquescythe.particles.ModParticleTypes;
import nikita.uniquescythe.particles.custom.*;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;


public class UniqueScytheClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

		//build-in texture packs
		QuiltLoader.getModContainer(UniqueScythe.MOD_ID)
			.ifPresent(modContainer -> ResourceLoader
				.registerBuiltinResourcePack(UniqueScythe.id("icons"),
					modContainer, ResourcePackActivationType.ALWAYS_ENABLED, Text.of("§bIcons")));

		QuiltLoader.getModContainer(UniqueScythe.MOD_ID)
			.ifPresent(modContainer -> ResourceLoader
				.registerBuiltinResourcePack(UniqueScythe.id("my_textures"),
					modContainer, ResourcePackActivationType.ALWAYS_ENABLED, Text.of("§bSMP Textures DEFAULT")));

		QuiltLoader.getModContainer(UniqueScythe.MOD_ID)
			.ifPresent(modContainer -> ResourceLoader
				.registerBuiltinResourcePack(UniqueScythe.id("better_redstone"),
					modContainer, ResourcePackActivationType.NORMAL, Text.of("§bBetter Redstone")));

		QuiltLoader.getModContainer(UniqueScythe.MOD_ID)
			.ifPresent(modContainer -> ResourceLoader
				.registerBuiltinResourcePack(UniqueScythe.id("legacy_copper"),
					modContainer, ResourcePackActivationType.DEFAULT_ENABLED, Text.of("§bBetter Copper Textures")));






		//Particle factory registry
		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.WIND_EXPLOSION, WindExplosionParticle.Factory::new);

		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.FROSTY_SWEEP_ATTACK, FrostySweepAttackParticle.Factory::new);

		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.VOID_SWEEP_ATTACK, VoidSweepAttackParticle.Factory::new);

		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.VOID_CRIT, VoidDamageParticle.Factory ::new);

		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.FROSTY_CRIT, FrostyDamageParticle.Factory ::new);

		//ParticleFactoryRegistry.getInstance().register(ModParticleTypes.JUSTICE_HIT, JusticeHitParticle.Factory ::new);



		//mod model layers
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BREEZE, BreezeEntityModel::getTexturedModelData);//breeze



		EntityRendererRegistry.register(ModEntities.WIND_CHARGE_PROJECTILE, WindChargeRenderer::new);
		EntityRendererRegistry.register(ModEntities.JUSTICE_BULLET_ENTITY, JusticeBulletRenderer::new);

		EntityRendererRegistry.register(ModEntities.BREEZE, BreezeEntityRenderer::new );







		//Blocks render layer maps
		BlockRenderLayerMap.put(RenderLayer.getCutout(), ModBlocks.COPPER_GRATE);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), ModBlocks.EXPOSED_COPPER_GRATE);
		BlockRenderLayerMap.put(RenderLayer.getCutout() ,ModBlocks.OXIDIZED_COPPER_GRATE );
		BlockRenderLayerMap.put(RenderLayer.getCutout(), ModBlocks.WAXED_COPPER_GRATE);
		BlockRenderLayerMap.put(RenderLayer.getCutout(), ModBlocks.WAXED_EXPOSED_COPPER_GRATE);
		BlockRenderLayerMap.put(RenderLayer.getCutout() ,ModBlocks.WAXED_OXIDIZED_COPPER_GRATE );




	}
}
