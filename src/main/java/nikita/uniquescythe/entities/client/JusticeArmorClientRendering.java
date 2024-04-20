package nikita.uniquescythe.entities.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.rendering.entity.api.client.ArmorRenderingRegistry;

public class JusticeArmorClientRendering implements ArmorRenderingRegistry.TextureProvider, ArmorRenderingRegistry.ModelProvider{


	private static final Identifier ARMOR_TEXTURE_ID = UniqueScythe.id("textures/models/armor/JusticeArmor.png");



	private final JusticeArmorModel ARMOR_MODEL;

	public JusticeArmorClientRendering(EntityRendererFactory.Context context) {
		super();
		this.ARMOR_MODEL = new JusticeArmorModel(context.getPart(ModModelLayers.JUSTICE_ARMOR));
	}


	@Override
	public @NotNull BipedEntityModel<LivingEntity> getArmorModel(
		@NotNull BipedEntityModel<LivingEntity> model,
		@NotNull LivingEntity entity, @NotNull ItemStack stack, @NotNull EquipmentSlot slot
	)
	{

		if (slot == EquipmentSlot.HEAD) {

			return ARMOR_MODEL;
		}

		return model;
	};


	@Override
	public @NotNull Identifier getArmorTexture(@NotNull Identifier texture, @NotNull LivingEntity entity,
											   @NotNull ItemStack stack, @NotNull EquipmentSlot slot, boolean useSecondLayer, @Nullable String suffix)
	{
		if (slot == EquipmentSlot.HEAD) {
			// redirect texture
			return ARMOR_TEXTURE_ID;
		}

		return texture;
	}
}
