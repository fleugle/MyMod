package nikita.uniquescythe.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import nikita.uniquescythe.UniqueScythe;
import nikita.uniquescythe.items.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useBigScytheModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (((stack.isOf(ModItems.FROSTY_SCYTHE) && renderMode == (ModelTransformationMode.THIRD_PERSON_RIGHT_HAND))
			|| (stack.isOf(ModItems.FROSTY_SCYTHE) && renderMode == (ModelTransformationMode.THIRD_PERSON_LEFT_HAND))
			|| (stack.isOf(ModItems.FROSTY_SCYTHE) && renderMode == (ModelTransformationMode.FIRST_PERSON_LEFT_HAND))
			|| (stack.isOf(ModItems.FROSTY_SCYTHE) && renderMode == (ModelTransformationMode.FIRST_PERSON_RIGHT_HAND)))){

            return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "frosty_scythe_big", "inventory"));
        }
        return value;
    }

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useBigWardenersSword(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(ModItems.WANDERERS_SWORD) && renderMode == (ModelTransformationMode.THIRD_PERSON_RIGHT_HAND))
		|| (stack.isOf(ModItems.WANDERERS_SWORD) && renderMode == (ModelTransformationMode.FIRST_PERSON_RIGHT_HAND))) {
			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "wanderers_sword_big", "inventory"));
		}
		return value;
	}

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useBigWardenersClosed(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(ModItems.WANDERERS_SWORD) && renderMode == (ModelTransformationMode.THIRD_PERSON_LEFT_HAND))
		|| (stack.isOf(ModItems.WANDERERS_SWORD) && renderMode == (ModelTransformationMode.FIRST_PERSON_LEFT_HAND)) ) {
			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "wanderers_sword_closed", "inventory"));
		}
		return value;
	}

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useBigBook(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(Items.BOOK) && renderMode == (ModelTransformationMode.THIRD_PERSON_RIGHT_HAND))){
			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "book_big", "inventory"));
		}
		return value;
	}

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useClosedBook(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(Items.BOOK) && renderMode == (ModelTransformationMode.THIRD_PERSON_LEFT_HAND))){
			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "book_big_closed", "inventory"));
		}
		return value;
	}

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useBigBookWritable(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(Items.WRITABLE_BOOK) && renderMode == (ModelTransformationMode.THIRD_PERSON_RIGHT_HAND))) {
			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "w_book_big", "inventory"));
		}
		return value;
	}
	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useClosedBigBookWritable(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(Items.WRITABLE_BOOK) && renderMode == (ModelTransformationMode.THIRD_PERSON_LEFT_HAND))) {
			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "w_book_big_closed", "inventory"));
		}
		return value;
	}

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useBigBookWritten(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(Items.WRITTEN_BOOK) && renderMode == (ModelTransformationMode.THIRD_PERSON_RIGHT_HAND))) {
			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "w_book_big", "inventory"));
		}
		return value;
	}

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useClosedBigBookWritten(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(Items.WRITTEN_BOOK) && renderMode == (ModelTransformationMode.THIRD_PERSON_LEFT_HAND))) {
			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "w_book_big_closed", "inventory"));
		}
		return value;
	}

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useBigBookEnchanced(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(Items.ENCHANTED_BOOK) && renderMode == (ModelTransformationMode.THIRD_PERSON_RIGHT_HAND))) {
			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "e_book_big", "inventory"));
		}
		return value;
	}

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useCloseduseBigBookEnchanced(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(Items.ENCHANTED_BOOK) && renderMode == (ModelTransformationMode.THIRD_PERSON_LEFT_HAND))) {
			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "e_book_big_closed", "inventory"));
		}
		return value;
	}


	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useBigMace(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if ((stack.isOf(ModItems.MACE) && renderMode == (ModelTransformationMode.THIRD_PERSON_RIGHT_HAND))
			|| (stack.isOf(ModItems.MACE) && renderMode == (ModelTransformationMode.THIRD_PERSON_LEFT_HAND))
		|| (stack.isOf(ModItems.MACE) && renderMode == (ModelTransformationMode.FIRST_PERSON_RIGHT_HAND))
		|| (stack.isOf(ModItems.MACE) && renderMode == (ModelTransformationMode.FIRST_PERSON_LEFT_HAND))) {
			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "mace_big", "inventory"));
		}
		return value;
	}


	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useBigJevilScytheModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if (((stack.isOf(ModItems.JEVIL_SCYTHE) && renderMode == (ModelTransformationMode.THIRD_PERSON_RIGHT_HAND))
			|| (stack.isOf(ModItems.JEVIL_SCYTHE) && renderMode == (ModelTransformationMode.THIRD_PERSON_LEFT_HAND))
			|| (stack.isOf(ModItems.JEVIL_SCYTHE) && renderMode == (ModelTransformationMode.FIRST_PERSON_LEFT_HAND))
			|| (stack.isOf(ModItems.JEVIL_SCYTHE) && renderMode == (ModelTransformationMode.FIRST_PERSON_RIGHT_HAND)))){

			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "jevil_scythe_big", "inventory"));
		}
		return value;
	}

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useBigChaosMultiToolModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if (((stack.isOf(ModItems.CHAOS_MULTITOOL) && renderMode == (ModelTransformationMode.THIRD_PERSON_RIGHT_HAND))
			|| (stack.isOf(ModItems.CHAOS_MULTITOOL) && renderMode == (ModelTransformationMode.THIRD_PERSON_LEFT_HAND)))){

			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "chaos_multitool_big", "inventory"));
		}
		return value;
	}

	@ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
	public BakedModel useBigJusticeRevolverModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		if (((stack.isOf(ModItems.JUSTICE_REVOLVER) && renderMode == (ModelTransformationMode.THIRD_PERSON_RIGHT_HAND))
			|| (stack.isOf(ModItems.JUSTICE_REVOLVER) && renderMode == (ModelTransformationMode.THIRD_PERSON_LEFT_HAND))
			|| (stack.isOf(ModItems.JUSTICE_REVOLVER) && renderMode == (ModelTransformationMode.FIRST_PERSON_LEFT_HAND))
			|| (stack.isOf(ModItems.JUSTICE_REVOLVER) && renderMode == (ModelTransformationMode.FIRST_PERSON_RIGHT_HAND)))){

			return ((ItemRendererAccessor) this).customModels$getModels().getModelManager().getModel(new ModelIdentifier(UniqueScythe.MOD_ID, "justice_revolver_big", "inventory"));
		}
		return value;
	}
}
