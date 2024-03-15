
package nikita.uniquescythe.mixin;


import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import nikita.uniquescythe.items.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

	private static boolean isNewTotem(ItemStack itemStack){
		return itemStack.isOf(ModItems.FLUGELS_IMMORTALITY_DECLARATION);
	}


	@Inject(method = "getActiveTotemOfUndying", at = @At(value = "RETURN"), cancellable = true)
	private static void getActiveNewTotemOfUndying(PlayerEntity player, CallbackInfoReturnable<ItemStack> cir){
		for(Hand hand : Hand.values()) {
			ItemStack itemStack = player.getStackInHand(hand);
			if(isNewTotem(itemStack)) {
				cir.setReturnValue(itemStack);
			}
		}

	}
}
