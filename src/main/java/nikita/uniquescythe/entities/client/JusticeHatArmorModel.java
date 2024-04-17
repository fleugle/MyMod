package nikita.uniquescythe.entities.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class JusticeHatArmorModel extends BipedEntityModel<LivingEntity> {

	public JusticeHatArmorModel(ModelPart root) {
		super(root);
	}

	public JusticeHatArmorModel(ModelPart root, Function<Identifier, RenderLayer> renderLayerFactory) {
		super(root, renderLayerFactory);
	}


}
