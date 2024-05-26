package nikita.uniquescythe.items.custom;



import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.utility.KarmaSystem;


public class WanderersSwordItem extends SwordItem {


	public WanderersSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
		AttackEntityCallback.EVENT.register((player, world, hand, entity, entityHitResult) -> {
			if (player.getMainHandStack().getItem() == this && !player.isSpectator() && !world.isClient) {
				KarmaSystem.addKarmaToPlayer((ServerPlayerEntity) player, player.getDisplayName().getString(), 6);
			}
			return ActionResult.PASS;
		});
	}


	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		return ActionResult.FAIL;
	}

	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 80, 1));
		target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));
		target.damage(target.getDamageSources().generic(), (float) KarmaSystem.getKarma((ServerPlayerEntity)attacker, attacker.getDisplayName().getString()));
		KarmaSystem.setKarmaToPlayer((ServerPlayerEntity) attacker, attacker.getDisplayName().getString(), 0);


		return super.postHit(stack, target, attacker);
	}
	//to do something if right-clicked too
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (user.getWorld() != null && !world.isClient()) {

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
