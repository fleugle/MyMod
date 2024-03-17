package nikita.uniquescythe.entities.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import nikita.uniquescythe.custom.WindExplosion;
import nikita.uniquescythe.entities.ModEntities;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.sounds.ModSounds;


import java.util.List;

public class WindChargeProjectileEntity extends ThrownItemEntity {






	public WindChargeProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
		this.setNoGravity(true);
	}

	public WindChargeProjectileEntity(LivingEntity LivingEntity, World world) {
		super(ModEntities.WIND_CHARGE_PROJECTILE,LivingEntity, world);
		this.setNoGravity(true);
	}





	public void setWindProjectyleProperties(Entity user, float pitch, float yaw, float roll, float modifierZ, float modifierXYZ) {
		float f = -MathHelper.sin(yaw * (float) (Math.PI / 180.0)) * MathHelper.cos(pitch * (float) (Math.PI / 180.0));
		float g = -MathHelper.sin((pitch + roll) * (float) (Math.PI / 180.0));
		float h = MathHelper.cos(yaw * (float) (Math.PI / 180.0)) * MathHelper.cos(pitch * (float) (Math.PI / 180.0));
		this.setVelocity((double)f, (double)g, (double)h, modifierZ, modifierXYZ);
		Vec3d vec3d = user.getVelocity();
		this.setVelocity(this.getVelocity().add(vec3d.x, user.isOnGround() ? 0.0 : vec3d.y, vec3d.z));
	}

	@Override
	protected Item getDefaultItem() {
		return ModItems.WIND_CHARGE;
	}

	@Override
	public Packet<ClientPlayPacketListener> createSpawnPacket() {
		return new EntitySpawnS2CPacket(this);
	}

	@Override
	protected void onBlockHit(BlockHitResult blockHitResult) {

		if(!this.getWorld().isClient()){
			float explosionSize = 3f;
			this.getWorld().sendEntityStatus(this, (byte) 3);
			WindExplosion explosion = new WindExplosion(getWorld(), null, getPos().getX(), getPos().getY(), getPos().getZ(), explosionSize);
			explosion.collectBlocksAndDamageEntities();



			/*
			// Apply knockback to nearby living entities
			List<? extends Entity> nearbyEntities = getWorld().getOtherEntities(null, new Box(getBlockPos()).expand(explosionSize), entity -> entity instanceof LivingEntity);
			for (Entity entity : nearbyEntities) {
				LivingEntity livingEntity = (LivingEntity) entity;



				livingEntity.addVelocity(1.3, 1.3,1.3);//boost to the sky
			}

			 */


			//sound on block collision
			getWorld().playSound(
				null,
				getPos().getX(),
				getPos().getY(),
				getPos().getZ(),
				ModSounds.WIND_CHARGE_BURST,
				SoundCategory.AMBIENT,
				1F,
				0.4F / (getWorld().getRandom().nextFloat() * 0.4F + 0.8F)
			);

		}

		this.discard();
		super.onBlockHit(blockHitResult);
	}


}
