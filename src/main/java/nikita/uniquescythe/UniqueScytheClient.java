package nikita.uniquescythe;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.DamageParticle;
import net.minecraft.client.particle.ExplosionEmitterParticle;
import net.minecraft.client.particle.ExplosionLargeParticle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.render.RenderLayer;
import nikita.uniquescythe.blocks.ModBlocks;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.entities.client.*;
import nikita.uniquescythe.particles.ModParticleTypes;
import nikita.uniquescythe.particles.custom.FrostySweepAttackParticle;
import nikita.uniquescythe.particles.custom.VoidDamageParticle;
import nikita.uniquescythe.particles.custom.VoidSweepAttackParticle;
import nikita.uniquescythe.particles.custom.WindExplosionParticle;


public class UniqueScytheClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {




		//Particle factory registry
		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.WIND_EXPLOSION, WindExplosionParticle.Factory::new);

		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.FROSTY_SWEEP_ATTACK, FrostySweepAttackParticle.Factory::new);

		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.VOID_SWEEP_ATTACK, VoidSweepAttackParticle.Factory::new);

		ParticleFactoryRegistry.getInstance().register(ModParticleTypes.VOID_CRIT, VoidDamageParticle.Factory ::new);




		//mod model layers
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.WIND_CHARGE, WindChargeProjectileEntityModel::getTexturedModelData);//wind charge

		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.JUSTICE_ARMOR, JusticeArmorModel::getTexturedModelData);//wind charge

		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BREEZE, BreezeEntityModel::getTexturedModelData);//breeze



		EntityRendererRegistry.register(ModEntities.WIND_CHARGE_PROJECTILE, WindChargeProjectileEntityRenderer::new);

		EntityRendererRegistry.register(ModEntities.BREEZE, BreezeEntityRenderer::new );






		//Blocks render layer maps
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_GRATE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.EXPOSED_COPPER_GRATE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OXIDIZED_COPPER_GRATE, RenderLayer.getCutout());
	}
}
