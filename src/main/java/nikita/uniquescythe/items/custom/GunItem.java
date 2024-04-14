package nikita.uniquescythe.items.custom;


import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import nikita.uniquescythe.items.ModItems;

import java.util.Random;



public abstract class GunItem extends Item {
	public GunItem(Settings settings) {
		super(settings);
	}


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
			if (!worldIn.isClient) {
				Vec3d front = Vec3d.fromPolar(player.getPitch(), player.getYaw());
				Arm arm = hand == Hand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
				boolean isRightHand = arm == Arm.RIGHT;
				Vec3d side = Vec3d.fromPolar(0, player.getYaw() + (isRightHand ? 90 : -90));
				Vec3d down = Vec3d.fromPolar(player.getPitch() + 90, player.getYaw());
				fire(player, front, side.add(down).multiply(0.15));
			}
			player.playSound(fireSound(), 3.5f, 1);

			setLoaded(stack, false);
			stack.damage(1, player, (entity) -> {
				entity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
			});

			if (worldIn.isClient) setActiveStack(hand, stack);

			return TypedActionResult.consume(stack);

		} else if (haveAmmo) {
			setLoadingStage(stack, 1);

			player.setCurrentHand(hand);
			if (worldIn.isClient) setActiveStack(hand, stack);

			return TypedActionResult.consume(stack);

		} else {
			return TypedActionResult.fail(stack);
		}
	}

	@Override
	public void onStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		setLoadingStage(stack, 0);
	}

	@Override
	public void usageTick(World world, LivingEntity entity, ItemStack stack, int timeLeft) {
		if (!(entity instanceof PlayerEntity)) return;

		PlayerEntity player = (PlayerEntity) entity;
		int usingDuration = getMaxUseTime(stack) - timeLeft;
		int loadingStage = getLoadingStage(stack);

		if (loadingStage == 1 && usingDuration >= LOADING_STAGE_1) {
			player.playSound(MusketMod.SOUND_MUSKET_LOAD_0, 0.8f, 1);
			setLoadingStage(stack, 2);

		} else if (loadingStage == 2 && usingDuration >= LOADING_STAGE_2) {
			player.playSound(MusketMod.SOUND_MUSKET_LOAD_1, 0.8f, 1);
			setLoadingStage(stack, 3);

		} else if (loadingStage == 3 && usingDuration >= LOADING_STAGE_3) {
			player.playSound(MusketMod.SOUND_MUSKET_LOAD_2, 0.8f, 1);
			setLoadingStage(stack, 4);
		}

		if (world.isClientSide) {
			setActiveStack(player.getUsedItemHand(), stack);
			return;
		}

		if (usingDuration >= RELOAD_DURATION && !isLoaded(stack)) {
			if (!player.getAbilities().instabuild) {
				ItemStack ammoStack = findAmmo(player);
				if (ammoStack.isEmpty()) return;

				ammoStack.shrink(1);
				if (ammoStack.isEmpty()) player.getInventory().removeItem(ammoStack);
			}

			world.playSound(null, player.getX(), player.getY(), player.getZ(), MusketMod.SOUND_MUSKET_READY, player.getSoundSource(), 0.8f, 1);
			setLoaded(stack, true);
		}
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 72000;
	}
	public static boolean isAmmo(ItemStack stack) {
		return stack.getItem() == ModItems.CARTRIDGE;
	}
	public static boolean isLoaded(ItemStack stack) {
		return stack.getOrCreateNbt().getByte("loaded") != 0;
	}

	public static int getLoadingStage(ItemStack stack) {
		return stack.getOrCreateNbt().getInt("loadingStage");
	}

	public static ItemStack findAmmo(PlayerEntity player) {
		if (isAmmo(player.getEquippedStack(EquipmentSlot.OFFHAND))) {
			return player.getEquippedStack(EquipmentSlot.OFFHAND);

		} else if (isAmmo(player.getEquippedStack(EquipmentSlot.MAINHAND))) {
			return player.getEquippedStack(EquipmentSlot.MAINHAND);

		} else {
			for (int i = 0; i != player.getInventory().size(); ++i) {
				ItemStack itemstack = player.getInventory().getStack(i);
				if (isAmmo(itemstack)) return itemstack;
			}

			return ItemStack.EMPTY;
		}
	}
	public void fire(LivingEntity shooter, Vec3d direction) {
		fire(shooter, direction, Vec3d.ZERO);
	}

	public static void setLoaded(ItemStack stack, boolean loaded) {
		if (loaded) {
			stack.getOrCreateNbt().putByte("loaded", (byte)1);
		} else {
			stack.getOrCreateNbt().remove("loaded");
		}
	}

	public static void setActiveStack(Hand hand, ItemStack stack) {
		if (hand == Hand.MAIN_HAND) {
			activeMainHandStack = stack;
		} else {
			activeOffhandStack = stack;
		}
	}

	public static void setLoadingStage(ItemStack stack, int loadingStage) {
		if (loadingStage != 0) {
			stack.getOrCreateNbt().putInt("loadingStage", loadingStage);
		} else {
			stack.getOrCreateNbt().remove("loadingStage");
		}
	}

	public void fire(LivingEntity shooter, Vec3d direction, Vec3d smokeOriginOffset) {
		Random random = (Random) shooter.getRandom();
		World level = shooter.getWorld();

		float angle = (float) Math.PI * 2 * random.nextFloat();
		float gaussian = Math.abs((float) random.nextGaussian());
		if (gaussian > 4) gaussian = 4;

		float spread = bulletStdDev() * gaussian;

		// a plane perpendicular to direction
		Vec3d n1;
		Vec3d n2;
		if (Math.abs(direction.x) < 1e-5 && Math.abs(direction.z) < 1e-5) {
			n1 = new Vec3d(1, 0, 0);
			n2 = new Vec3d(0, 0, 1);
		} else {
			n1 = new Vec3d(-direction.z, 0, direction.x).normalize();
			n2 = direction.crossProduct(n1);
		}

		Vec3d motion = direction.multiply(Math.cos(spread))
			.add(n1.multiply(Math.sin(spread) * Math.sin(angle))) // signs are not important for random angle
			.add(n2.multiply(Math.sin(spread) * Math.cos(angle)))
			.multiply(bulletSpeed());

		Vec3d origin = new Vec3d(shooter.getX(), shooter.getEyeY(), shooter.getZ());
		/*
		BulletEntity bullet = new BulletEntity(level);
		bullet.setOwner(shooter);
		bullet.setPos(origin);
		bullet.setInitialSpeed(bulletSpeed());
		bullet.setDeltaMovement(motion);
		float t = random.nextFloat();
		bullet.damageMultiplier = t * damageMultiplierMin() + (1 - t) * damageMultiplierMax();

		level.addFreshEntity(bullet);
		MusketMod.sendSmokeEffect(shooter, origin.add(smokeOriginOffset), direction);

 		*///to modify later, when entity made
	}


}
