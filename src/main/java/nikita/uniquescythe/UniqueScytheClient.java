package nikita.uniquescythe;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import nikita.uniquescythe.blocks.ModBlocks;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.entities.client.*;
import nikita.uniquescythe.entities.client.BulletEntityRenderer;
import nikita.uniquescythe.particles.ModParticleTypes;
import nikita.uniquescythe.particles.custom.*;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.resource.loader.api.ResourceLoader;
import org.quiltmc.qsl.resource.loader.api.ResourcePackActivationType;


public class UniqueScytheClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

		QuiltLoader.getModContainer(UniqueScythe.MOD_ID)
			.ifPresent(modContainer -> ResourceLoader
				.registerBuiltinResourcePack(UniqueScythe.id("icons"),
					modContainer, ResourcePackActivationType.ALWAYS_ENABLED, Text.of("§bIcons")));

		QuiltLoader.getModContainer(UniqueScythe.MOD_ID)
			.ifPresent(modContainer -> ResourceLoader
				.registerBuiltinResourcePack(UniqueScythe.id("my_textures"),
					modContainer, ResourcePackActivationType.ALWAYS_ENABLED, Text.of("§bSMP Textures DEFAULT")));


		//load in backwards order - from bot to top = from top to bot





		//Particle factory registry
		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.WIND_EXPLOSION, WindExplosionParticle.Factory::new);

		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.FROSTY_SWEEP_ATTACK, FrostySweepAttackParticle.Factory::new);

		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.VOID_SWEEP_ATTACK, VoidSweepAttackParticle.Factory::new);

		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.VOID_CRIT, VoidDamageParticle.Factory ::new);

		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.FROSTY_CRIT, FrostyDamageParticle.Factory ::new);



		//mod model layers
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.WIND_CHARGE, WindChargeProjectileEntityModel::getTexturedModelData);//wind charge
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BULLET, BulletEntityModel::getTexturedModelData);//wind charge
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BREEZE, BreezeEntityModel::getTexturedModelData);//breeze



		EntityRendererRegistry.register(ModEntities.WIND_CHARGE_PROJECTILE, WindChargeProjectileEntityRenderer::new);

		EntityRendererRegistry.register(ModEntities.BREEZE, BreezeEntityRenderer::new );

		EntityRendererRegistry.register(ModEntities.BULLET_ENTITY, BulletEntityRenderer::new);





		//Blocks render layer maps
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_GRATE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_GRATE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_GRATE, RenderLayer.getCutout());
	}
}
