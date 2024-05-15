package nikita.uniquescythe.particles;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;


public class ModParticleTypes {

	public static final DefaultParticleType WIND_EXPLOSION = FabricParticleTypes.simple();//simple particle for wind explosion I guess
	public static final DefaultParticleType FROSTY_SWEEP_ATTACK = FabricParticleTypes.simple();
	public static final DefaultParticleType VOID_SWEEP_ATTACK = FabricParticleTypes.simple();
	public static final DefaultParticleType VOID_CRIT = FabricParticleTypes.simple();
	public static final DefaultParticleType FROSTY_CRIT = FabricParticleTypes.simple();
	//public static final DefaultParticleType JUSTICE_HIT = FabricParticleTypes.simple();

	static DefaultParticleType modParticleTypesRegistry(String name, DefaultParticleType particleType){
		return Registry.register(Registries.PARTICLE_TYPE, new Identifier(UniqueScythe.MOD_ID, name), particleType);
	}



	public static void initialiseModParticleTypes(){

		UniqueScythe.LOGGER.info("Registering " + UniqueScythe.MOD_ID + " Particle Types");

		//modParticleTypesRegistry("name", FabricParticleTypes.simple()); -> for simple particles
		modParticleTypesRegistry("wind_explosion", WIND_EXPLOSION);//simple particle for wind explosion I guess
		modParticleTypesRegistry("frosty_sweep_attack", FROSTY_SWEEP_ATTACK);
		modParticleTypesRegistry("void_sweep_attack", VOID_SWEEP_ATTACK);
		modParticleTypesRegistry("void_crit", VOID_CRIT);
		modParticleTypesRegistry("frosty_crit", FROSTY_CRIT);
		//modParticleTypesRegistry("justice_hit", JUSTICE_HIT);

	}
}
