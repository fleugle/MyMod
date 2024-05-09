package nikita.uniquescythe.items.custom;


import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.Arm;
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

import java.util.Random;



public abstract class GunItem extends Item {


	public GunItem(Settings settings) {
		super(settings);
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
