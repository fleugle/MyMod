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
import mod.azure.azurelib.core.object.PlayState;
import mod.azure.azurelib.renderer.GeoArmorRenderer;
import mod.azure.azurelib.util.AzureLibUtil;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import nikita.uniquescythe.geo.renderers.JusticeArmorRenderer;
import nikita.uniquescythe.items.ModItems;
import org.jetbrains.annotations.NotNull;


import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class JusticeArmorItem extends ArmorItem implements GeoItem {
	private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);
	private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

	public JusticeArmorItem(ArmorMaterial armorMaterial, ArmorItem.ArmorSlot type, Settings properties) {
		super(armorMaterial, type, properties);
	}

	@Override
	public void createRenderer(Consumer<Object> consumer) {
		consumer.accept(new RenderProvider() {
			private GeoArmorRenderer<?> renderer;

			@Override
			public @NotNull BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
				if (this.renderer == null)
					this.renderer = new JusticeArmorRenderer();

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
		controllers.add(new AnimationController<>(this, 20, state -> {
			// Apply our generic idle animation.
			// Whether it plays or not is decided down below.
			state.getController().setAnimation(DefaultAnimations.IDLE);

			// Let's gather some data from the state to use below
			// This is the entity that is currently wearing/holding the item
			Entity entity = state.getData(DataTickets.ENTITY);

			// We'll just have ArmorStands always animate, so we can return here
			if (entity instanceof ArmorStandEntity)
				return PlayState.CONTINUE;

			// For this example, we only want the animation to play if the entity is wearing all pieces of the armor
			// Let's collect the armor pieces the entity is currently wearing
			Set<Item> wornArmor = new ObjectOpenHashSet<>();

			for (ItemStack stack : entity.getArmorItems()) {
				// We can stop immediately if any of the slots are empty
				if (stack.isEmpty())
					return PlayState.STOP;

				wornArmor.add(stack.getItem());
			}

			// Check each of the pieces match our set
			boolean isFullSet = wornArmor.containsAll(ObjectArrayList.of(
				ModItems.JUSTICE_HAT,
				ModItems.COWBOYS_CLOAK));

			// Play the animation if the full set is being worn, otherwise stop
			return isFullSet ? PlayState.CONTINUE : PlayState.STOP;
		}));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
