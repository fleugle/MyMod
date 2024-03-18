package nikita.uniquescythe.entities.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;

public class ModModelLayers {
	public static final EntityModelLayer WIND_CHARGE =
		new EntityModelLayer(new Identifier(UniqueScythe.MOD_ID,"wind_charge_entity"), "main");
}
