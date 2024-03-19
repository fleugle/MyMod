package nikita.uniquescythe.entities.client;


import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import nikita.uniquescythe.entities.animation.ModAnimations;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;


public class WindChargeProjectileEntityModel<T extends WindChargeProjectileEntity> extends SinglePartEntityModel<T> {
	private final ModelPart wind_charge;
	private final ModelPart charge;
	private final ModelPart wind_inner;
	private final ModelPart wind_outer;
	public WindChargeProjectileEntityModel(ModelPart root) {
		this.wind_charge = root.getChild("wind_charge");
		this.charge = wind_charge.getChild("charge");
		this.wind_inner = wind_charge.getChild("wind_inner");
		this.wind_outer = wind_charge.getChild("wind_outer");

	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData wind_charge = modelPartData.addChild("wind_charge", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData charge = wind_charge.addChild("charge", ModelPartBuilder.create().uv(21, 18).cuboid(-2.0F, -25.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData wind_inner = wind_charge.addChild("wind_inner", ModelPartBuilder.create().uv(0, 11).cuboid(-3.0F, -25.0F, -3.0F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData wind_outer = wind_charge.addChild("wind_outer", ModelPartBuilder.create().uv(0, 54).cuboid(-4.0F, -24.0F, -4.0F, 8.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(WindChargeProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.animate(entity.idleState, ModAnimations.WIND_CHARGE, 100,1f);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		wind_charge.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return wind_charge;
	}
}
