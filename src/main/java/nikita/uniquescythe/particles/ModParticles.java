package nikita.uniquescythe.particles;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;


public class ModParticles {
	public static final DefaultParticleType VOID_ATTACK_PARTICLE = FabricParticleTypes.simple();

	public static void registerParticles(){
		Registry.register(Registries.PARTICLE_TYPE, new Identifier(UniqueScythe.MOD_ID,"void_attack_particle"),
			VOID_ATTACK_PARTICLE);
	}


}
