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
		ModelPartData wind_charge = modelPartData.addChild("wind_charge", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0F, 0.0F));

		ModelPartData charge = wind_charge.addChild("charge", ModelPartBuilder.create().uv(51, 43).cuboid(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, 0.0F));

		ModelPartData wind_inner = wind_charge.addChild("wind_inner", ModelPartBuilder.create().uv(104, 97).cuboid(-3.0F, -2.0F, -3.0F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData wind_outer = wind_charge.addChild("wind_outer", ModelPartBuilder.create().uv(96, 118).cuboid(-4.0F, -1.4F, -4.0F, 8.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.4F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(T windChargeProjectileEntity , float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.animate(windChargeProjectileEntity.idleState, ModAnimations.WIND_CHARGE, ageInTicks, 3.2F);
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
