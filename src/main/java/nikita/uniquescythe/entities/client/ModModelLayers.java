package nikita.uniquescythe.entities.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import nikita.uniquescythe.UniqueScythe;

public class ModModelLayers {
	//wind charge
	public static final EntityModelLayer WIND_CHARGE =
		new EntityModelLayer(new Identifier(UniqueScythe.MOD_ID,"wind_charge_entity"), "main");



	//breeze
	public static final EntityModelLayer BREEZE =
		new EntityModelLayer(new Identifier(UniqueScythe.MOD_ID,"breeze"), "main");

	//breeze
	public static final EntityModelLayer BREEZE_WIND =
		new EntityModelLayer(new Identifier(UniqueScythe.MOD_ID,"breeze_wind"), "outer");

	public static final EntityModelLayer BULLET =
		new EntityModelLayer(new Identifier(UniqueScythe.MOD_ID,"bullet"),"main");
}
