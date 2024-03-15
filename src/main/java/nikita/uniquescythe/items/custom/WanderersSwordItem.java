package nikita.uniquescythe.items.custom;



import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nikita.uniquescythe.sounds.ModSounds;


public class WanderersSwordItem extends AxeItem {
	private static final int COOLDOWN_TICKS = 20 * 5; // 5 seconds cooldown
	public WanderersSwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		return ActionResult.FAIL;
	}

	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 80, 1));
		target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));



		return super.postHit(stack, target, attacker);
	}
	//to do something if right-clicked too
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (user.getWorld() != null && !world.isClient() && hand == Hand.MAIN_HAND) {//IMPORTANT!! FOR use method it is needed to specify, where action is performed. (server or client)
			//I've also code it work only if in main hand

			user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 2400, 3));
			user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 900, 2));
			user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 900, 2));
			user.getItemCooldownManager().set(this, 2400); // a way to make a cooldown for an item
		}
		return TypedActionResult.pass(user.getStackInHand(hand));

	}
	@Override
	public int getMaxUseTime(ItemStack stack) {


		return 25;
	}

	@Override
	public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
		return false;
	}

}
