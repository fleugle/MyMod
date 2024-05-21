package nikita.uniquescythe.items.custom;


import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.SingletonGeoAnimatable;
import mod.azure.azurelib.animatable.client.RenderProvider;

import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import nikita.uniquescythe.entities.custom.JusticeBulletEntity;
import nikita.uniquescythe.geo.renderers.JusticeRevolverRenderer;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.sounds.ModSoundEvents;

import java.util.function.Consumer;
import java.util.function.Supplier;


public class JusticeRevolverItem extends GunItem {


	//CONSTRUCTOR\\
	public JusticeRevolverItem(Settings properties) {
		super(
			6,
			10,
			60,
			"§6Hey, partner! Don't forget to charge it next time, Okay?",
			"§2 - Judges target",
			"§2 - Justice Bullet",
			properties);
		SingletonGeoAnimatable.registerSyncedAnimatable(this);
	}





	//GUN ITEM CLASS OVERRIDES\\
	@Override
	public Item getAmmoItem(){
		return ModItems.JUSTICE_BULLET;
	}

	@Override
	public SoundEvent getShootingSound(){
		return ModSoundEvents.JUSTICE_SHOOT;
	}

	@Override
	public SoundEvent getReloadSound(){
		return ModSoundEvents.REVOLVER_RELOAD;
	}

	@Override
	public SoundEvent getEmptySound(){
		return ModSoundEvents.EMPTY_GUN_SHOT;
	}

	@Override
	public void createFiringParticles(World world, PlayerEntity shooter){
		if (world instanceof ServerWorld serverWorld) {

			// Spawn smoke particles in a radius of 2 blocks
			serverWorld.spawnParticles(ParticleTypes.SMOKE,
				shooter.getX() + 0,
				shooter.getY()  + 0.5,
				shooter.getZ()  + 0,
				4, 0.5, 0.5, 0.5, 0.03);
		}
	}

	@Override
	public void createProjectile(World world, PlayerEntity shooter, ItemStack stackWithGun){
		JusticeBulletEntity justiceBulletEntity = new JusticeBulletEntity(shooter, world);
		justiceBulletEntity.setItem(stackWithGun);
		justiceBulletEntity.setBulletProperties(shooter, shooter.getPitch(), shooter.getYaw(), 1.0F, 100F, 0F);
		world.spawnEntity(justiceBulletEntity);
	}





	//DEFAULT ITEM CLASSES OVERRIDES\\
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient) {
		return ingredient.isOf(ModItems.JUSTICE_SHARD);
	}




	//AZURELIB PART\\

	private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

	@Override
	public void createRenderer(Consumer<Object> consumer) {

		// Accepts a consumer to create a new renderer
		consumer.accept(new RenderProvider() {
			// Your render class made above
			private JusticeRevolverRenderer renderer = null;

			@Override
			public BuiltinModelItemRenderer getCustomRenderer() {
				// Check if renderer is null, create a new instance if so
				if (renderer == null)
					return new JusticeRevolverRenderer();
				// Return the existing renderer if it's not null
				return this.renderer;
			}
		});
	}

	@Override
	public Supplier<Object> getRenderProvider() {
		return renderProvider;
	}


}

