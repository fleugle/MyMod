package nikita.uniquescythe.entities.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class JusticeArmorModel extends EntityModel<LivingEntity> {
	private final ModelPart helmet;
	private final ModelPart chestplate;
	private final ModelPart rarm;
	private final ModelPart larm;
	private final ModelPart rleg;
	private final ModelPart lleg;
	public JusticeArmorModel(ModelPart root) {
        this.helmet = root.getChild("helmet");
		this.chestplate = root.getChild("chestplate");
		this.rarm = root.getChild("rarm");
		this.larm = root.getChild("larm");
		this.rleg = root.getChild("rleg");
		this.lleg = root.getChild("lleg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData helmet = modelPartData.addChild("helmet", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, 0.8125F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F))
		.uv(0, 15).cuboid(-4.5F, -3.6875F, -4.5F, 9.0F, 4.5F, 9.0F, new Dilation(0.0F))
		.uv(56, 1).cuboid(-5.0F, -3.1875F, -5.0F, 10.0F, 4.0F, 10.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-7.0F, 0.8125F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.0625F, 0.0F, -0.1309F, -0.0002F, 0.0854F));

		ModelPartData chestplate = modelPartData.addChild("chestplate", ModelPartBuilder.create().uv(36, 15).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F))
		.uv(0, 55).cuboid(-3.0F, -0.5F, -3.0F, 6.0F, 3.0F, 6.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData rarm = modelPartData.addChild("rarm", ModelPartBuilder.create().uv(1, 29).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

		ModelPartData larm = modelPartData.addChild("larm", ModelPartBuilder.create().uv(17, 29).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

		ModelPartData rleg = modelPartData.addChild("rleg", ModelPartBuilder.create(), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

		ModelPartData lleg = modelPartData.addChild("lleg", ModelPartBuilder.create(), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 64);
	}


	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

	}

	@Override
	public void setAngles(LivingEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}
}
