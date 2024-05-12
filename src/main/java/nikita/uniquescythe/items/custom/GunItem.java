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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
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



	//needed implementation for azurelib code part
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController[]{(new AnimationController(this, "shoot_controller", (event) -> {
			return PlayState.CONTINUE;
		})).triggerableAnim("firing", RawAnimation.begin().then("firing", Animation.LoopType.PLAY_ONCE)).triggerableAnim("reload", RawAnimation.begin().then("reload", Animation.LoopType.PLAY_ONCE))});
	}









	public GunItem(ToolMaterial toolMaterial, int maxAmmo, int shootingDelay,Settings settings) {
		super( settings );

		this.toolMaterial = toolMaterial;
		this.maxAmmo = maxAmmo;
		this.shootingDelay = shootingDelay;

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


		return TypedActionResult.pass(itemStack/*, false*/);

	}


	public static EntityHitResult hitscanTrace(PlayerEntity player, double range, float ticks) {
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
		tooltip.add(Text.translatable("Ammo: " + getCurrentAmmo(stack) + " / " + this.maxAmmo));
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


	public int getCurrentAmmo(ItemStack itemStack){
		int ammo = 0;
		return ammo;
	}

}
