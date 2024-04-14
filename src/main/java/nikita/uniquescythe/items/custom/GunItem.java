package nikita.uniquescythe.items.custom;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;


public abstract class GunItem extends Item {
	public static final int LOADING_STAGE_1 = 5;
	public static final int LOADING_STAGE_2 = 10;
	public static final int LOADING_STAGE_3 = 20;
	public static final int RELOAD_DURATION = 30;

	// for RenderHelper
	public static ItemStack activeMainHandStack;
	public static ItemStack activeOffhandStack;
	public GunItem(Settings settings) {
		super(settings);
	}

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




	public void fire(LivingEntity shooter, Vec3d direction) {
		fire(shooter, direction);
	}
}
