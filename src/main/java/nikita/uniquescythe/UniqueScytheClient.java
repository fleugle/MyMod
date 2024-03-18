package nikita.uniquescythe;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.entities.client.ModModelLayers;
import nikita.uniquescythe.entities.client.WindChargeProjectileEntityModel;
import nikita.uniquescythe.entities.client.WindChargeProjectileEntityRenderer;
import nikita.uniquescythe.particles.ModParticles;
import nikita.uniquescythe.particles.custom.VoidAttackParticle;

public class UniqueScytheClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
		//wtf is that
		ParticleFactoryRegistry.getInstance().register(ModParticles.VOID_ATTACK_PARTICLE, VoidAttackParticle.Factory::new);

		//EntityRendererRegistry.register(ModEntities.WIND_CHARGE_PROJECTILE, FlyingItemEntityRenderer::new); //FlyingItemEntityRenderer::new needs to be changed in  advance

		EntityRendererRegistry.register(ModEntities.WIND_CHARGE_PROJECTILE, WindChargeProjectileEntityRenderer::new); //FlyingItemEntityRenderer::new needs to be changed in  advance


		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.WIND_CHARGE, WindChargeProjectileEntityModel::getTexturedModelData);




	}
}
