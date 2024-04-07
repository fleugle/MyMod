package nikita.uniquescythe.particles.custom;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class WindExplosionParticle extends SpriteBillboardParticle {
	private final SpriteProvider spriteProvider;

	protected WindExplosionParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
		super(world, x, y, z);
		this.spriteProvider = spriteProvider;
		this.setSpriteForAge(spriteProvider);
		this.maxAge = 12 + this.random.nextInt(4);
		this.scale = 1.0f;
		this.setBoundingBoxSpacing(1.0f, 1.0f);
	}

	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_LIT;
	}

	@Override
	public int getBrightness(float tint) {
		return 0xF000F0;
	}

	@Override
	public void tick() {
		if (this.age++ >= this.maxAge) {
			this.markDead();
			return;
		}
		this.setSpriteForAge(this.spriteProvider);
	}


	@Environment(EnvType.CLIENT)
	public static class Factory
		implements ParticleFactory<DefaultParticleType> {
		private final SpriteProvider spriteProvider;

		public Factory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			return new WindExplosionParticle(clientWorld, d, e, f, this.spriteProvider);
		}
	}
}
