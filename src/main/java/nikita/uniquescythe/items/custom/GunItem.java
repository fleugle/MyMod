package nikita.uniquescythe.items.custom;


import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.Animation;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nikita.uniquescythe.utility.SoundsManager;

import java.util.List;


public abstract class GunItem extends Item implements GeoItem {
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
	private ItemStack persistantItemStack;
	private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
	private BlockPos lightBlockPos = null;
	public int maxAmmo;
	public int shootingDelay;//not less than 10, 'cause in minecraft there is invulnerability timer for 10 ticks.
	public int reloadTime;
	public String notificationAboutAmmo;
	public String damageDescription;
	public String requiresBullet;




	//needed implementation for azurelib code part
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController[]{(new AnimationController(this, "shooting_controller", (event) -> {
			return PlayState.CONTINUE;
		}))
			.triggerableAnim("shoot", RawAnimation.begin().then("shoot", Animation.LoopType.PLAY_ONCE))
			.triggerableAnim("empty_shoot", RawAnimation.begin().then("empty_shoot", Animation.LoopType.PLAY_ONCE))
			.triggerableAnim("reload", RawAnimation.begin().then("reload", Animation.LoopType.PLAY_ONCE))
		});
	}
	//base model of animations nomenclature is present above. If I need a different on - override in an item class






	public GunItem(

		int maxAmmo,
		int shootingDelay,
		int reloadTime,
		String notificationAboutAmmo,
		String damageDescription,
		String requiresBullet,
		Settings settings)
	{

		super( settings );

		this.maxAmmo = maxAmmo;
		this.shootingDelay = shootingDelay;
		this.reloadTime = reloadTime;
		this.notificationAboutAmmo = notificationAboutAmmo;
		this.damageDescription = damageDescription;
		this.requiresBullet = requiresBullet;


	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

		super.use(world, user, hand);

		ItemStack mainhand_stack = user.getStackInHand(Hand.MAIN_HAND);

		ItemStack offhand_stack = user.getStackInHand(Hand.OFF_HAND);
		ItemStack itemStack = user.getStackInHand(hand);



		if (!user.getItemCooldownManager().isCoolingDown(this)) {
			if (!world.isClient) {
				tryToReloadGun(world, user, itemStack, mainhand_stack, offhand_stack);
			}
		}





		return TypedActionResult.pass(itemStack);

	}

	public void projectileShoot(World world, PlayerEntity shooter, ItemStack stackWithGun){


		if (!world.isClient) {

			if (hasEnoughAmmoInWeapon(stackWithGun) || shooter.getAbilities().creativeMode){
				createProjectile(world, shooter, stackWithGun);
				shooter.getItemCooldownManager().set(this, this.shootingDelay);

				createFiringParticles(world, shooter);

				if (!shooter.getAbilities().creativeMode) {
					setAmmoAmount(stackWithGun, getAmmoAmount(stackWithGun) - 1);
				}
				triggerAnim(shooter, GeoItem.getOrAssignId(stackWithGun, (ServerWorld) world), "shooting_controller", "shoot");
				SoundsManager.playPlayersSoundFromPlayer(shooter, getShootingSound(), 1f);

				if (!shooter.getAbilities().creativeMode) {
					stackWithGun.damage(1, shooter, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
				}
			}
			else {
				notifyShooterAboutAmmo(shooter);
				shooter.getItemCooldownManager().set(this, this.shootingDelay);
				triggerAnim(shooter, GeoItem.getOrAssignId(stackWithGun, (ServerWorld) world), "shooting_controller", "empty_shoot");
				SoundsManager.playPlayersSoundFromPlayer(shooter, getEmptySound(), 1f);
			}




			shooter.getItemCooldownManager().set(this, this.shootingDelay); // Set cooldown

		}

		shooter.incrementStat(Stats.USED.getOrCreateStat(this));


	}

	public void tryToReloadGun(World world, PlayerEntity shooter, ItemStack stackWithGun, ItemStack mainhand_stack, ItemStack offhand_stack){

		int ammoAmount = getAmmoAmount(stackWithGun);

		if(!world.isClient){
			if (!hasFullAmmoInWeapon(stackWithGun)){

				int howMuchAmmoItNeeds = maxAmmo - ammoAmount;
				if(mainhand_stack.getItem() == getAmmoItem()){

					int howMuchAmmoIsPresent = mainhand_stack.getCount();
					consumeNeededAmountOfAmmoAndPutItInTheGun(shooter,stackWithGun, mainhand_stack, howMuchAmmoItNeeds, howMuchAmmoIsPresent);
					SoundsManager.playPlayersSoundFromPlayer(shooter, getReloadSound(), 1f);
					triggerAnim(shooter, GeoItem.getOrAssignId(stackWithGun, (ServerWorld) world), "shooting_controller", "reload");
					shooter.getItemCooldownManager().set(this, this.reloadTime);
				} else if (offhand_stack.getItem() == getAmmoItem()) {

					int howMuchAmmoIsPresent = offhand_stack.getCount();
					consumeNeededAmountOfAmmoAndPutItInTheGun(shooter,stackWithGun, offhand_stack, howMuchAmmoItNeeds, howMuchAmmoIsPresent);
					SoundsManager.playPlayersSoundFromPlayer(shooter, getReloadSound(), 1f);
					triggerAnim(shooter, GeoItem.getOrAssignId(stackWithGun, (ServerWorld) world), "shooting_controller", "reload");
					shooter.getItemCooldownManager().set(this, this.reloadTime);
				}
				else {
					projectileShoot(world, shooter, stackWithGun);
				}

			}
			else projectileShoot(world, shooter, stackWithGun);
		}

	}


	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		super.appendTooltip(stack, world, tooltip, context);
		tooltip.add(Text.literal(""));
		tooltip.add(Text.literal("§7" + "Ammo: " + getAmmoAmount(stack) + " / " + this.maxAmmo));
		tooltip.add(Text.literal("§7On shoot:"));
		tooltip.add(Text.literal(damageDescription));
		tooltip.add(Text.literal("§7Requires:"));
		tooltip.add(Text.literal(requiresBullet));
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

	public SoundEvent getReloadSound(){
		return null;
	}

	public SoundEvent getEmptySound(){
		return null;
	}

	private boolean hasEnoughAmmoInWeapon(ItemStack itemStack){

		if (getAmmoAmount(itemStack) > 0) return true;
		else return false;
	}

	public void createProjectile(World world, PlayerEntity shooter, ItemStack stackWithGun){

	}

	public void createFiringParticles(World world, PlayerEntity shooter){

	}

	private void notifyShooterAboutAmmo(PlayerEntity shooter){
		shooter.sendMessage(Text.of(notificationAboutAmmo), true);
	}

	private boolean hasFullAmmoInWeapon(ItemStack itemStack){

		if (getAmmoAmount(itemStack) == this.maxAmmo) return true;
		else return false;
	}

	private void consumeNeededAmountOfAmmoAndPutItInTheGun(PlayerEntity shooter, ItemStack stackWithGunItem,ItemStack stackWithAmmo, int neededAmount, int amountPresent){
		if (amountPresent <= neededAmount) {
			if (!shooter.getAbilities().creativeMode) {
				stackWithAmmo.decrement(amountPresent);
			}
			setAmmoAmount(stackWithGunItem, getAmmoAmount(stackWithGunItem) + amountPresent);
		}else {
			if (!shooter.getAbilities().creativeMode) {
				stackWithAmmo.decrement(neededAmount);
			}
			setAmmoAmount(stackWithGunItem, getAmmoAmount(stackWithGunItem) + neededAmount);
		}

	}

	@Override
	public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
		return false;
	}

}
