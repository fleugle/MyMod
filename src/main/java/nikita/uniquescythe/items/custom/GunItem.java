package nikita.uniquescythe.items.custom;


import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.Animation;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.items.BaseGunItem;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import nikita.uniquescythe.entities.custom.BulletEntity;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;
import nikita.uniquescythe.items.ModItems;
import net.minecraft.util.math.MathHelper;
import nikita.uniquescythe.mixin.LivingEntityMixin;
import nikita.uniquescythe.sounds.ModSounds;
import nikita.uniquescythe.utility.GuiltyLevelSystem;
import nikita.uniquescythe.utility.SoundsManager;

import java.util.Iterator;
import java.util.List;
import java.util.Random;



public abstract class GunItem extends Item implements GeoItem {
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
	private ItemStack persistantItemStack;
	private final ToolMaterial toolMaterial;
	private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
	private BlockPos lightBlockPos = null;
	public int maxAmmo;
	public int shootingDelay;
	public int reloadTime;
	public double maxShootingRange;
	public String notificationAboutAmmo;





	//needed implementation for azurelib code part
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController[]{(new AnimationController(this, "shoot_controller", (event) -> {
			return PlayState.CONTINUE;
		}))
			.triggerableAnim("shoot", RawAnimation.begin().then("shoot", Animation.LoopType.PLAY_ONCE))
			.triggerableAnim("empty_shoot", RawAnimation.begin().then("empty_shoot", Animation.LoopType.PLAY_ONCE))
			.triggerableAnim("reload", RawAnimation.begin().then("reload", Animation.LoopType.HOLD_ON_LAST_FRAME))
			.triggerableAnim("idle", RawAnimation.begin().then("idle", Animation.LoopType.LOOP))});
	}
	//base model of animations nomenclature is present above. If I need a different on - override in an item class






	public GunItem(
		ToolMaterial toolMaterial,
		int maxAmmo,
		int shootingDelay,
		int reloadTime,
		double maxShootingRange,
		String notificationAboutAmmo,
		Settings settings) {
		super( settings );

		this.toolMaterial = toolMaterial;
		this.maxAmmo = maxAmmo;
		this.shootingDelay = shootingDelay;
		this.reloadTime = reloadTime;
		this.maxShootingRange = maxShootingRange;
		this.notificationAboutAmmo = notificationAboutAmmo;

	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

		super.use(world, user, hand);

		ItemStack mainhand_stack = user.getStackInHand(Hand.MAIN_HAND);

		ItemStack offhand_stack = user.getStackInHand(Hand.OFF_HAND);
		ItemStack itemStack = user.getStackInHand(hand);

		if (!world.isClient) {
			if (offhand_stack.getItem() == getAmmoItem()) {
				BulletEntity bulletEntity = new BulletEntity(user, world);
				bulletEntity.setItem(itemStack);
				bulletEntity.setBulletProperties(user, user.getPitch(), user.getYaw(), 1.0F, 100F, 0F);
				world.spawnEntity(bulletEntity);
				user.getItemCooldownManager().set(this, this.shootingDelay);
				SoundsManager.playPlayersSoundOnSpot(user, getShootingSound(), 1f);
				offhand_stack.decrement(1);

			}
			else if (mainhand_stack.getItem() == getAmmoItem()){
				BulletEntity bulletEntity = new BulletEntity(user, world);
				bulletEntity.setItem(itemStack);
				bulletEntity.setBulletProperties(user, user.getPitch(), user.getYaw(), 1.0F, 100F, 0F);
				world.spawnEntity(bulletEntity);
				user.getItemCooldownManager().set(this, this.shootingDelay);
				SoundsManager.playPlayersSoundOnSpot(user, getShootingSound(), 1f);
				mainhand_stack.decrement(1);
			}
			else {
				SoundsManager.playPlayersSoundOnSpot(user, getEmptySound(), 1f);
				user.getItemCooldownManager().set(this, this.shootingDelay);
			}


		}

		if (!user.getAbilities().creativeMode) {
			itemStack.damage(1, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
		}


		return TypedActionResult.pass(itemStack);

	}

	public void hitScanShoot(World world, PlayerEntity shooter, ItemStack stackWithGun, double shootingRange){


		if (!world.isClient) {

			if (hasEnoughAmmoInWeapon(stackWithGun)){
				EntityHitResult hitResult = hitscanTrace(shooter, shootingRange, 1.0F); // Perform hitscan with a range of 50 blocks

				if (hitResult != null) {
					Entity entity = hitResult.getEntity();
					float damageAmount;

					int shooterGuiltyLevel = GuiltyLevelSystem.getGuiltyLevel(
						(ServerPlayerEntity) shooter,
						shooter.getDisplayName().getString(),
						"PersistentGuiltyLevel"
					);

					if (entity instanceof PlayerEntity) {
						int targetGuiltyLevel = GuiltyLevelSystem.getGuiltyLevel(
							(ServerPlayerEntity) entity,
							entity.getDisplayName().getString(),
							"PersistentGuiltyLevel"
						);



						damageAmount = (float) targetGuiltyLevel - shooterGuiltyLevel;
					} else if (entity instanceof HostileEntity) {

						damageAmount = 50f - shooterGuiltyLevel;

					} else {

						damageAmount = 8f - shooterGuiltyLevel;
					}



					if (damageAmount < 0) {
						damageAmount = 0 - damageAmount;
						shooter.damage(shooter.getDamageSources().generic(), damageAmount);
					}
					else entity.damage(entity.getDamageSources().generic(), damageAmount);

				}
				SoundsManager.playPlayersSoundOnSpot(shooter, getShootingSound(), 1f);
			}
			else {
				notifyShooterAboutAmmo(shooter);
				SoundsManager.playPlayersSoundOnSpot(shooter, getEmptySound(), 1f);
			}




			shooter.getItemCooldownManager().set(this, this.shootingDelay); // Set cooldown
		}

		shooter.incrementStat(Stats.USED.getOrCreateStat(this));
		if (!shooter.getAbilities().creativeMode) {
			stackWithGun.damage(1, shooter, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
		}

	}

	public void tryToReloadGun(World world, PlayerEntity shooter, ItemStack stackWithGun, ItemStack mainhand_stack, ItemStack offhand_stack){

		int ammoAmount = getAmmoAmount(stackWithGun);

		if(!world.isClient){
			if (!hasFullAmmoInWeapon(stackWithGun)){

				int howMuchAmmoItNeeds = maxAmmo - ammoAmount;
				if(mainhand_stack.getItem() == getAmmoItem()){
					int howMuchAmmoIsPresent = mainhand_stack.getCount();
					consumeNeededAmountOfAmmoAndPutItInTheGun(stackWithGun, mainhand_stack, howMuchAmmoItNeeds, howMuchAmmoIsPresent);

					shooter.getItemCooldownManager().set(this, this.reloadTime);
				} else if (offhand_stack.getItem() == getAmmoItem()) {
					int howMuchAmmoIsPresent = offhand_stack.getCount();
					consumeNeededAmountOfAmmoAndPutItInTheGun(stackWithGun, offhand_stack, howMuchAmmoItNeeds, howMuchAmmoIsPresent);

					shooter.getItemCooldownManager().set(this, this.reloadTime);
				}
				else {
					hitScanShoot(world, shooter, stackWithGun, this.maxShootingRange);
				}

			}
		}

	}

	private static EntityHitResult hitscanTrace(PlayerEntity player, double range, float ticks) {
		Vec3d look = player.getRotationVec(ticks);
		Vec3d start = player.getCameraPosVec(ticks);
		Vec3d end = new Vec3d(player.getX() + look.x * range, player.getEyeY() + look.y * range, player.getZ() + look.z * range);
		double traceDistance = player.getWorld().raycast(new RaycastContext(start, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player)).getPos().squaredDistanceTo(end);
		Iterator var9 = player.getWorld().getOtherEntities(player, player.getBoundingBox().stretch(look.multiply(traceDistance)).stretch(3.0, 3.0, 3.0), (entity) -> {
			return !entity.isSpectator() && entity.collides() && entity instanceof LivingEntity;
		}).iterator();

		Entity possible;
		do {
			if (!var9.hasNext()) {
				return null;
			}

			possible = (Entity)var9.next();
		} while(!possible.getBoundingBox().expand(0.3).raycast(start, end).isPresent() || !(start.squaredDistanceTo((Vec3d)possible.getBoundingBox().expand(0.3).raycast(start, end).get()) < traceDistance));

		return ProjectileUtil.getEntityCollision(player.getWorld(), player, start, end, player.getBoundingBox().stretch(look.multiply(traceDistance)).expand(3.0, 3.0, 3.0), (target) -> {
			return !target.isSpectator() && player.isAttackable() && player.canSee(target);
		});
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);
		tooltip.add(Text.translatable("Ammo: " + getAmmoAmount(stack) + " / " + this.maxAmmo));
	}

	private int getAmmoAmount(ItemStack stack) {

		NbtCompound tag = stack.getOrCreateNbt();
		return tag.contains("ammoAmount") ? tag.getInt("ammoAmount") : 0;
	}

	private void setAmmoAmount(ItemStack stack, int ammoAmount) {

		NbtCompound tag = stack.getOrCreateNbt();
		tag.putInt("ammoAmount", ammoAmount);
	}

	public Item getAmmoItem(){
		return null;
	}

	public SoundEvent getShootingSound(){
		return null;
	}

	public SoundEvent getEmptySound(){
		return ModSounds.EMPTY_GUN_SHOT;
	}

	private boolean hasEnoughAmmoInWeapon(ItemStack itemStack){

		if (getAmmoAmount(itemStack) > 0) return true;
		else return false;
	}

	private void notifyShooterAboutAmmo(PlayerEntity shooter){
		shooter.sendMessage(Text.of(notificationAboutAmmo), true);
	}

	private boolean hasFullAmmoInWeapon(ItemStack itemStack){

		if (getAmmoAmount(itemStack) == this.maxAmmo) return true;
		else return false;
	}

	private void consumeNeededAmountOfAmmoAndPutItInTheGun(ItemStack stackWithGunItem,ItemStack stackWithAmmo, int neededAmount, int amountPresent){
		if (amountPresent <= neededAmount) {
			stackWithAmmo.decrement(amountPresent);
			setAmmoAmount(stackWithGunItem, getAmmoAmount(stackWithGunItem) + amountPresent);
		}else {
			stackWithAmmo.decrement(this.maxAmmo);
			setAmmoAmount(stackWithGunItem, getAmmoAmount(stackWithGunItem) + neededAmount);
		}

	}
}
