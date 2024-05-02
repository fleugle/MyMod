package nikita.uniquescythe.items.custom;

import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.client.RenderProvider;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.renderer.GeoArmorRenderer;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nikita.uniquescythe.geo.renderers.InnocentNimbusRenderer;
import nikita.uniquescythe.utility.ModArmorMaterials;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InnocentNimbusItem extends ArmorItem implements GeoItem {

	private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
	private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);






	public InnocentNimbusItem(ArmorMaterial armorMaterial, ArmorSlot type, Settings properties) {
		super(armorMaterial, type, properties);
	}



	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(!world.isClient()) {
			if(entity instanceof PlayerEntity player && hasFullSuitOfArmorOn(player)) {
				if(hasCorrectArmorOn(ModArmorMaterials.INNOCENCE, player)) {
					addStatusEffectForMaterial(player, ModArmorMaterials.INNOCENCE, new StatusEffectInstance(StatusEffects.RESISTANCE,
						25, 99, false, false, false));
					addStatusEffectForMaterial(player, ModArmorMaterials.INNOCENCE, new StatusEffectInstance(StatusEffects.REGENERATION,
						25, 99, false, false, false));
					addStatusEffectForMaterial(player, ModArmorMaterials.INNOCENCE, new StatusEffectInstance(StatusEffects.WEAKNESS,
						25, 99, false, false, false));
					addStatusEffectForMaterial(player, ModArmorMaterials.INNOCENCE, new StatusEffectInstance(StatusEffects.SATURATION,
						25, 99, false, false, false));

					//i think would be better to change to a custom effect, that will apply all above at once, but display as 1

				}
			}
		}

		super.inventoryTick(stack, world, entity, slot, selected);
	}



	private void addStatusEffectForMaterial(@NotNull PlayerEntity player, ArmorMaterial ArmorMaterial, @NotNull StatusEffectInstance StatusEffect) {

		if(hasCorrectArmorOn(ArmorMaterial, player)) {
			player.addStatusEffect(new StatusEffectInstance(StatusEffect));
		}
	}

	private boolean hasFullSuitOfArmorOn(@NotNull PlayerEntity player) {

		ItemStack helmet = player.getInventory().getArmorStack(3);

		return !helmet.isEmpty();
	}

	private boolean hasCorrectArmorOn(ArmorMaterial material, @NotNull PlayerEntity player) {



		ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

		return helmet.getMaterial() == material;
	}















	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	//azurelib part

	@Override
	public void createRenderer(@NotNull Consumer<Object> consumer) {
		consumer.accept(new RenderProvider() {
			private GeoArmorRenderer<?> renderer;

			@Override
			public @NotNull BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
				if (this.renderer == null)
					this.renderer = new InnocentNimbusRenderer();

				// This prepares our GeoArmorRenderer for the current render frame.
				// These parameters may be null however, so we don't do anything further with them
				this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

				return this.renderer;
			}
		});
	}

	@Override
	public Supplier<Object> getRenderProvider() {
		return this.renderProvider;
	}

	// Let's add our animation controller
	@Override
	public void registerControllers(AnimatableManager.@NotNull ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "controllerName", 0, event ->
		{
			return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
		}));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
