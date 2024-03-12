package nikita.uniquescythe;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import nikita.uniquescythe.particles.ModParticles;
import nikita.uniquescythe.particles.custom.VoidAttackParticle;

public class UniqueScytheClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
		//wtf is that
		ParticleFactoryRegistry.getInstance().register(ModParticles.VOID_ATTACK_PARTICLE, VoidAttackParticle.Factory::new);
    }
}
