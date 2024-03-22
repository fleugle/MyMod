package nikita.uniquescythe;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.entities.client.*;
import nikita.uniquescythe.particles.ModParticles;
import nikita.uniquescythe.particles.custom.VoidAttackParticle;

public class UniqueScytheClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
		//wtf is that
		ParticleFactoryRegistry.getInstance().register(ModParticles.VOID_ATTACK_PARTICLE, VoidAttackParticle.Factory::new);


		//mod model layers
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.WIND_CHARGE, WindChargeProjectileEntityModel::getTexturedModelData);//wind charge

		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BREEZE, BreezeEntityModel::getTexturedModelData);//breeze

		EntityRendererRegistry.register(ModEntities.WIND_CHARGE_PROJECTILE, WindChargeProjectileEntityRenderer::new);

		EntityRendererRegistry.register(ModEntities.BREEZE, BreezeEntityRenderer::new );










	}
}
