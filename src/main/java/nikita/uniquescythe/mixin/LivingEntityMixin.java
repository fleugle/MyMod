package nikita.uniquescythe.mixin;;


import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerTask;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.Optional;

/*The goal of this Mixin class is to give the totems the same ability to save the player from death, along with
some unique custom features*/

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin  extends Entity{


	@Shadow
	public  native ItemStack getStackInHand(Hand hand_1);

	@Shadow
	public native boolean hasStatusEffect(StatusEffect effect);

	@Shadow public native void setHealth(float health);

	@Shadow public native boolean clearStatusEffects();

	@Shadow public native boolean addStatusEffect(StatusEffectInstance statusEffectInstance_1);

	@Shadow public native EntityGroup getGroup();



	public MinecraftServer the_server = getServer();

	protected LivingEntityMixin(EntityType<?> entityType_1, World world_1) {
		super(entityType_1, world_1);
	}



	@Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = true)
	public void useExplosiveTotem(DamageSource damageSource_1, CallbackInfoReturnable<Boolean> callback) {
		/*inits PlayerEntity entity, which is a copy of this casted to Living Entity and then PlayerEntity*/
		Entity entity =  this;



		/*ItemStack object that is set to the offhand item that entity is carrying*/
		ItemStack offhand_stack = ((LivingEntityMixin) entity).getStackInHand(Hand.OFF_HAND);

		ItemStack mainhand_stack = ((LivingEntityMixin) entity).getStackInHand(Hand.MAIN_HAND);

		//Executes if the item in offhand_stack is equal to the explosive totem of Undying
		if ((offhand_stack.getItem() == MoreTotemsMod.EXPLOSIVE_TOTEM_OF_UNDYING) || (mainhand_stack.getItem() == MoreTotemsMod.EXPLOSIVE_TOTEM_OF_UNDYING) ) {

			/*If the damagesource is something that could kill a player in creative mode, the totem does not work*/
			if (damageSource_1.getType().equals(DamageTypes.OUT_OF_WORLD)) {

				callback.setReturnValue(false);
			}
			else {
				/*sets copy to offhand_stack*/
				/*deletes explosive totem from offhand*/

				if((offhand_stack.getItem() == MoreTotemsMod.EXPLOSIVE_TOTEM_OF_UNDYING)) {
					offhand_stack.decrement(1);
				}
				else if((mainhand_stack.getItem() == MoreTotemsMod.EXPLOSIVE_TOTEM_OF_UNDYING)){

					mainhand_stack.decrement(1);

				}



				/*totem saves player from an untimely death*/
				this.setHealth(1.0F);
				this.clearStatusEffects();
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 125, 2));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 350, 4));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 2));
				this.getWorld().sendEntityStatus(this, (byte)35);

				/*Spawns a tntEntity on the player upon use of Explosive Totem*/

				TntEntity tntEntity = EntityType.TNT.create(getWorld());
				tntEntity.setFuse(5);
				tntEntity.refreshPositionAndAngles(this.getX() , this.getY() , this.getZ(), 0, 0);
				getWorld().spawnEntity(tntEntity);

				callback.setReturnValue(true);




			}

		}


	}



	@Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = true)
	public void useGhastlyTotem(DamageSource damageSource_1, CallbackInfoReturnable<Boolean> callback) {
		/*inits PlayerEntity entity, which is a copy of this casted to Living Entity and then PlayerEntity*/
		Entity entity =  this;

		/*ItemStack object that is set to the offhand item that entity is carrying*/
		ItemStack offhand_stack = ((LivingEntityMixin) entity).getStackInHand(Hand.OFF_HAND);

		ItemStack mainhand_stack = ((LivingEntityMixin) entity).getStackInHand(Hand.MAIN_HAND);

		//Executes if the item in offhand_stack is equal to the ghastly totem of Undying
		if ((offhand_stack.getItem() == MoreTotemsMod.GHASTLY_TOTEM_OF_UNDYING) || (mainhand_stack.getItem() == MoreTotemsMod.GHASTLY_TOTEM_OF_UNDYING)) {

			/*If the damagesource is something that could kill a player in creative mode, the totem does not work*/
			if (damageSource_1.getType().equals(DamageTypes.OUT_OF_WORLD)) {

				callback.setReturnValue(false);
			}
			else {
				/*sets copy to offhand_stack*/

				if((offhand_stack.getItem() == MoreTotemsMod.GHASTLY_TOTEM_OF_UNDYING)) {
					offhand_stack.decrement(1);
				}
				else if((mainhand_stack.getItem() == MoreTotemsMod.GHASTLY_TOTEM_OF_UNDYING)){

					mainhand_stack.decrement(1);

				}


				/*if the offhand_stack_copy is not empty, then execute*/


				/*totem saves player from an untimely death*/
				this.setHealth(1.0F);
				this.clearStatusEffects();
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1325, 1));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1525, 2));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 1000, 1));
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 1750, 1));


				this.getWorld().sendEntityStatus(this, (byte)35);

				callback.setReturnValue(true);

			}

		}

	}

}
