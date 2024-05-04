package nikita.uniquescythe.mixin;


import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.items.custom.GunItem;
import nikita.uniquescythe.utility.GuiltyLevelSystem;
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


	@Shadow
	public abstract boolean damage(DamageSource source, float amount);

	public MinecraftServer the_server = getServer();

	protected LivingEntityMixin(EntityType<?> entityType_1, World world_1) {
		super(entityType_1, world_1);
	}



	@SuppressWarnings("UnreachableCode")
	@Inject(at = @At("HEAD"), method = "tryUseTotem", cancellable = true)
	public void useFlugelsImmortalityTotem(DamageSource damageSource_1, CallbackInfoReturnable<Boolean> callback) {
		/*inits PlayerEntity entity, which is a copy of this casted to Living Entity and then PlayerEntity*/
		Entity entity =  this;




		/*ItemStack object that is set to the offhand item that entity is carrying*/


		ItemStack mainhand_stack = ((LivingEntityMixin) entity).getStackInHand(Hand.MAIN_HAND);

		ItemStack offhand_stack = ((LivingEntityMixin) entity).getStackInHand(Hand.OFF_HAND);


		if ( (mainhand_stack.getItem() == ModItems.FLUGELS_IMMORTALITY_DECLARATION) || (offhand_stack.getItem() == ModItems.FLUGELS_IMMORTALITY_DECLARATION )) {

			/*If the damagesource is something that could kill a player in creative mode, the totem does not work*/
			if (damageSource_1.getType().equals(DamageTypes.OUT_OF_WORLD)) {

				callback.setReturnValue(false);
			}
			else {
				GuiltyLevelSystem.addGuiltyLevelsToPlayer((ServerPlayerEntity) entity, entity.getDisplayName().getString(), 200000, 500);

				if (mainhand_stack.getItem() == ModItems.FLUGELS_IMMORTALITY_DECLARATION) {
					// Damage the item in the main hand
					mainhand_stack.damage(1, (LivingEntity)entity, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
				}

				else if (offhand_stack.getItem() == ModItems.FLUGELS_IMMORTALITY_DECLARATION) {
					// Damage the item in the off hand
					offhand_stack.damage(1, (LivingEntity)entity, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
				}

				else if ((mainhand_stack.getItem() == ModItems.FLUGELS_IMMORTALITY_DECLARATION) && (offhand_stack.getItem() == ModItems.FLUGELS_IMMORTALITY_DECLARATION)) {
					// Damage the item in the main hand
					mainhand_stack.damage(1, (LivingEntity)entity, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
				}




				/*totem saves player from an untimely death*/
				this.setHealth(20.0F);
				this.clearStatusEffects();
				this.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 500, 8));
				//this.getWorld().sendEntityStatus(this, (byte)35);
				callback.setReturnValue(true);

			}

		}


	}


}
