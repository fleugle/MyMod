package nikita.uniquescythe.items.custom;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.logging.Level;


public abstract class GunItem extends Item {
	public GunItem(Settings settings) {
		super(settings);
	}
	/*
	public static final int LOADING_STAGE_1 = 5;
	public static final int LOADING_STAGE_2 = 10;
	public static final int LOADING_STAGE_3 = 20;
	public static final int RELOAD_DURATION = 30;

	// for RenderHelper
	public static ItemStack activeMainHandStack;
	public static ItemStack activeOffhandStack;


	public abstract float bulletStdDev();
	public abstract float bulletSpeed();
	public abstract float damageMultiplierMin();
	public abstract float damageMultiplierMax();
	public abstract SoundEvent fireSound();
	public abstract boolean twoHanded();

	public boolean canUseFrom(PlayerEntity player, Hand hand) {
		if (hand == Hand.MAIN_HAND) {
			return true;
		}
		if (twoHanded()) {
			return false;
		}
			ItemStack mainHandStack = player.getStackInHand(Hand.MAIN_HAND);
		if (!mainHandStack.isEmpty() && mainHandStack.getItem() instanceof GunItem) {
			return !((GunItem)mainHandStack.getItem()).twoHanded();
		}
		return true;
	}

	@Override
	public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
		if (!canUseFrom(player, hand)) return super.use(worldIn, player, hand);

		ItemStack stack = player.getStackInHand(hand);
		boolean creative = player.getAbilities().creativeMode;

		if (player.isSubmergedIn(FluidTags.WATER) && !creative) {
			return TypedActionResult.fail(stack);
		}

		// shoot from left hand if both are loaded
		if (hand == Hand.MAIN_HAND && !twoHanded() && isLoaded(stack)) {
			ItemStack offhandStack = player.getStackInHand(Hand.OFF_HAND);
			if (!offhandStack.isEmpty() && offhandStack.getItem() instanceof GunItem) {
				GunItem offhandGun = (GunItem)offhandStack.getItem();
				if (!offhandGun.twoHanded() && isLoaded(offhandStack)) {
					return TypedActionResult.pass(stack);
				}
			}
		}

		boolean haveAmmo = !findAmmo(player).isEmpty() || creative;
		boolean loaded = isLoaded(stack);

		if (loaded) {
			if (!worldIn.isClientSide) {
				Vec3 front = Vec3.directionFromRotation(player.getXRot(), player.getYRot());
				HumanoidArm arm = hand == InteractionHand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
				boolean isRightHand = arm == HumanoidArm.RIGHT;
				Vec3 side = Vec3.directionFromRotation(0, player.getYRot() + (isRightHand ? 90 : -90));
				Vec3 down = Vec3.directionFromRotation(player.getXRot() + 90, player.getYRot());
				fire(player, front, side.add(down).scale(0.15));
			}
			player.playSound(fireSound(), 3.5f, 1);

			setLoaded(stack, false);
			stack.hurtAndBreak(1, player, (entity) -> {
				entity.broadcastBreakEvent(hand);
			});

			if (worldIn.isClientSide) setActiveStack(hand, stack);

			return InteractionResultHolder.consume(stack);

		} else if (haveAmmo) {
			setLoadingStage(stack, 1);

			player.startUsingItem(hand);
			if (worldIn.isClientSide) setActiveStack(hand, stack);

			return InteractionResultHolder.consume(stack);

		} else {
			return InteractionResultHolder.fail(stack);
		}
	}



	public static boolean isAmmo(ItemStack stack) {
		return stack.getItem() == MusketMod.CARTRIDGE;
	}
	public static boolean isLoaded(ItemStack stack) {
		return stack.getOrCreateNbt().getByte("loaded") != 0;
	}

	public static ItemStack findAmmo(PlayerEntity player) {
		if (isAmmo(player.getItemBySlot(EquipmentSlot.OFFHAND))) {
			return player.getItemBySlot(EquipmentSlot.OFFHAND);

		} else if (isAmmo(player.getItemBySlot(EquipmentSlot.MAINHAND))) {
			return player.getItemBySlot(EquipmentSlot.MAINHAND);

		} else {
			for (int i = 0; i != player.getInventory().getContainerSize(); ++i) {
				ItemStack itemstack = player.getInventory().getItem(i);
				if (isAmmo(itemstack)) return itemstack;
			}

			return ItemStack.EMPTY;
		}
	}
	public void fire(LivingEntity shooter, Vec3d direction) {
		fire(shooter, direction);
	}

	 */
}
