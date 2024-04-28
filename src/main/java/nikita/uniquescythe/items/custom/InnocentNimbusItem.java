package nikita.uniquescythe.items.custom;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import mod.azure.azurelib.animatable.GeoItem;
import mod.azure.azurelib.animatable.client.RenderProvider;
import mod.azure.azurelib.constant.DataTickets;
import mod.azure.azurelib.constant.DefaultAnimations;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.animation.RawAnimation;
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.renderer.GeoArmorRenderer;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nikita.uniquescythe.geo.renderers.InnocentNimbusRenderer;
import nikita.uniquescythe.geo.renderers.JusticeArmorRenderer;
import nikita.uniquescythe.items.ModItems;
import nikita.uniquescythe.utility.ModArmorMaterials;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.include.com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Set;
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
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 25,99, false, false, false));
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 25,99, false, false, false));


			}
		}

		super.inventoryTick(stack, world, entity, slot, selected);
	}



	private boolean hasFullSuitOfArmorOn(PlayerEntity player) {

		ItemStack helmet = player.getInventory().getArmorStack(3);

		return !helmet.isEmpty();
	}



	//azurelib part

	@Override
	public void createRenderer(Consumer<Object> consumer) {
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
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
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
