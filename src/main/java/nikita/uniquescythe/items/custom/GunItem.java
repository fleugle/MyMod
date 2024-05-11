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
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import nikita.uniquescythe.entities.custom.BulletEntity;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;
import nikita.uniquescythe.items.ModItems;
import net.minecraft.util.math.MathHelper;
import nikita.uniquescythe.mixin.LivingEntityMixin;
import nikita.uniquescythe.sounds.ModSounds;
import nikita.uniquescythe.utility.SoundsManager;

import java.util.List;
import java.util.Random;



public abstract class GunItem extends Item implements GeoItem {
	private final ToolMaterial toolMaterial;
	private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
	private BlockPos lightBlockPos = null;


	private int maxAmmo;




	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController[]{(new AnimationController(this, "shoot_controller", (event) -> {
			return PlayState.CONTINUE;
		})).triggerableAnim("firing", RawAnimation.begin().then("firing", Animation.LoopType.PLAY_ONCE)).triggerableAnim("reload", RawAnimation.begin().then("reload", Animation.LoopType.PLAY_ONCE))});
	}

	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}


	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, int ammo) {
		tooltip.add(Text.translatable("Ammo: " + ammo + " / " + maxAmmo));
	}



	public GunItem(ToolMaterial toolMaterial, int maxAmmo,Settings settings) {
		super( settings );

		this.toolMaterial = toolMaterial;
		this.maxAmmo = maxAmmo;
	}






	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {


		ItemStack mainhand_stack = user.getStackInHand(Hand.MAIN_HAND);

		ItemStack offhand_stack = user.getStackInHand(Hand.OFF_HAND);
		ItemStack itemStack = user.getStackInHand(hand);

		if (!world.isClient) {
			if (offhand_stack.getItem() == ModItems.BULLET) {
				BulletEntity bulletEntity = new BulletEntity(user, world);
				bulletEntity.setItem(itemStack);
				bulletEntity.setBulletProperties(user, user.getPitch(), user.getYaw(), 1.0F, 100F, 0F);
				world.spawnEntity(bulletEntity);
				user.getItemCooldownManager().set(this, 20);
				SoundsManager.playPlayersSoundOnSpot(user, ModSounds.SCYTHE_HIT, 1f);
				offhand_stack.decrement(1);

			}
			else if (mainhand_stack.getItem() == ModItems.BULLET){
				BulletEntity bulletEntity = new BulletEntity(user, world);
				bulletEntity.setItem(itemStack);
				bulletEntity.setBulletProperties(user, user.getPitch(), user.getYaw(), 1.0F, 100F, 0F);
				world.spawnEntity(bulletEntity);
				user.getItemCooldownManager().set(this, 20);
				SoundsManager.playPlayersSoundOnSpot(user, ModSounds.SCYTHE_HIT, 1f);
				mainhand_stack.decrement(1);
			}
			else {
				SoundsManager.playPlayersSoundOnSpot(user, ModSounds.SAD2_OGG, 1f);
				user.getItemCooldownManager().set(this, 20);
			}


		}

		user.incrementStat(Stats.USED.getOrCreateStat(this));
		if (!user.getAbilities().creativeMode) {
			itemStack.damage(1, user, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
		}


		return TypedActionResult.success(itemStack, false);


	}

}
