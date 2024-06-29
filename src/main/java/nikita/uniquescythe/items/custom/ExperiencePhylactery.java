package nikita.uniquescythe.items.custom;

import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import nikita.uniquescythe.sounds.ModSoundEvents;
import nikita.uniquescythe.utility.SoundsManager;

public class ExperiencePhylactery extends PhylacteryBasedItem{

	public ExperiencePhylactery() {
		super(250);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

		if (!world.isClient) {
			ItemStack stack = user.getStackInHand(hand);
			int soulsAmount = getOrCreateSoulsOnStack(stack);

			if(soulsAmount >= 10){
				double x = user.getX();
				double y = user.getY();
				double z = user.getZ();

				subtractSouls(stack, 10);

				world.spawnEntity(new ExperienceOrbEntity(world, x, y, z, 20));

				if (world instanceof ServerWorld) {


					/*((ServerWorld) world).spawnParticles(ParticleTypes.HAPPY_VILLAGER,
						user.getPos().getX()  + 0,
						user.getPos().getY()  + 1,
						user.getPos().getZ()  + 0,
						8, 0.8, 0.80, 0.80, 0);
					*/
					((ServerWorld) world).spawnParticles(ParticleTypes.SCULK_CHARGE_POP,
						user.getPos().getX()  + 0,
						user.getPos().getY()  + 1,
						user.getPos().getZ()  + 0,
						8, 0.80, 0.70, 0.80, 0);

					((ServerWorld) world).spawnParticles(ParticleTypes.FLASH,
						user.getPos().getX()  + 0,
						user.getPos().getY()  + 1,
						user.getPos().getZ()  + 0,
						1, 0, 0, 0, 0);

				}


				SoundsManager.playPlayersSoundFromPlayer(user, ModSoundEvents.SOUL_STEAL, 3f, 0.65f);
				SoundsManager.playPlayersSoundFromPlayer(user, ModSoundEvents.SOUL_STEAL, 3f, 0.65f);
				SoundsManager.playPlayersSoundFromPlayer(user, ModSoundEvents.SOUL_STEAL, 3f, 0.65f);
				SoundsManager.playPlayersSoundFromPlayer(user, ModSoundEvents.SOUL_STEAL, 3f, 0.65f);
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
