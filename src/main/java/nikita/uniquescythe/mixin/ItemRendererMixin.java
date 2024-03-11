package nikita.uniquescythe.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useBigScytheModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if ((stack.isOf(ModItems.FROSTY_SCYTHE) && renderMode != (ModelTransformationMode.GUI)) && (stack.isOf(ModItems.FROSTY_SCYTHE) && renderMode != (ModelTransformationMode.GROUND))) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "frosty_scythe_big", "inventory"));
        }//rendering big model if it should be displayed not in gui AND not on the ground
        return value;
    }

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useBigWardenersSword(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(ModItems.WARDENERS_SWORD) && renderMode != (ModelTransformationMode.GUI)) && (stack.isOf(ModItems.WARDENERS_SWORD) && renderMode != (ModelTransformationMode.GROUND)) && (stack.isOf(ModItems.WARDENERS_SWORD) && !leftHanded)) {
			return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "wardeners_sword_big", "inventory"));
		}//rendering big model if it should be displayed not in gui AND not on the ground
		return value;
	}

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useBigWardenersClosed(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(ModItems.WARDENERS_SWORD) && renderMode != (ModelTransformationMode.GUI)) && (stack.isOf(ModItems.WARDENERS_SWORD) && renderMode != (ModelTransformationMode.GROUND)) && (stack.isOf(ModItems.WARDENERS_SWORD) && leftHanded)) {
			return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "wardeners_sword_closed", "inventory"));
		}//rendering big model if it should be displayed not in gui AND not on the ground
		return value;
	}
}
