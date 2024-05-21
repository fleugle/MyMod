package nikita.uniquescythe.items.custom;


import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.SingletonGeoAnimatable;
import mod.azure.azurelib.animatable.client.RenderProvider;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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


public class OneShotMusket extends GunItem {

	/*
	Color
(Minecraft Name)	Chat Code	MOTD Code	Decimal	Hexadecimal
Dark Red
(dark_red)	§4	\u00A74	11141120	AA0000
Red
(red)	§c	\u00A7c	16733525	FF5555
Gold
(gold)	§6	\u00A76	16755200	FFAA00
Yellow
(yellow)	§e	\u00A7e	16777045	FFFF55
Dark Green
(dark_green)	§2	\u00A72	43520	00AA00
Green
(green)	§a	\u00A7a	5635925	55FF55
Aqua
(aqua)	§b	\u00A7b	5636095	55FFFF
Dark Aqua
(dark_aqua)	§3	\u00A73	43690	00AAAA
Dark Blue
(dark_blue)	§1	\u00A71	170	0000AA
Blue
(blue)	§9	\u00A79	5592575	5555FF
Light Purple
(light_purple)	§d	\u00A7d	16733695	FF55FF
Dark Purple
(dark_purple)	§5	\u00A75	11141290	AA00AA
White
(white)	§f	\u00A7f	16777215	FFFFFF
Gray
(gray)	§7	\u00A77	11184810	AAAAAA
Dark Gray
(dark_gray)	§8	\u00A78	5592405	555555
Black
(black)	§0	\u00A70	0	000000
	 */

	//https://www.digminecraft.com/lists/color_list_pc.php

	//CONSTRUCTOR\\
	public OneShotMusket(Settings properties) {
		super(
			1,
			20,
			40,
			"§4Give me MORE BULLETS",
			"§4 - Kills.",
			"§4 - Forbidden Bullet",
			properties);
		SingletonGeoAnimatable.registerSyncedAnimatable(this);
	}




//change
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

	//change
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
		return false;
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient) {
		return false;
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

