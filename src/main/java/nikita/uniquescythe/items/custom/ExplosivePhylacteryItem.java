package nikita.uniquescythe.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.entities.custom.ExplosiveFireballProjectileEntity;
import nikita.uniquescythe.sounds.ModSoundEvents;
import nikita.uniquescythe.utility.SoundsManager;

public class ExplosivePhylacteryItem extends PhylacteryBasedItem{

	public ExplosivePhylacteryItem() {
		super(50);
	}


	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

		if (!world.isClient) {
			ItemStack stack = user.getStackInHand(hand);
			int soulsAmount = getOrCreateSoulsOnStack(stack);

			if(soulsAmount >= 10){
				//sound of successful soul usage
				subtractSouls(stack, 10);

				ExplosiveFireballProjectileEntity explosiveFireballProjectileEntity	 = new ExplosiveFireballProjectileEntity(user, world);

				explosiveFireballProjectileEntity.setItem(stack);

				explosiveFireballProjectileEntity.setProperties(user, user.getPitch(), user.getYaw(), 1.0F, 2F, 1F);

				world.spawnEntity(explosiveFireballProjectileEntity);

				SoundsManager.playPlayersSoundFromPlayer(user, SoundEvents.ENTITY_WITHER_SHOOT, 1);

			}
			else {
				//sound of a failure (your voice)

				SoundsManager.playPlayersSoundFromPlayer(user, ModSoundEvents.SOUL_STEAL_FAIL, 1);
				SoundsManager.playPlayersSoundFromPlayer(user, ModSoundEvents.SOUL_STEAL_FAIL, 1);
				SoundsManager.playPlayersSoundFromPlayer(user, ModSoundEvents.SOUL_STEAL_FAIL, 1);



			}
			user.getItemCooldownManager().set(this, 60);
		}

		return TypedActionResult.success(user.getStackInHand(hand));
	}

}
