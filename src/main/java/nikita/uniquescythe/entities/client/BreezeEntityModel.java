package nikita.uniquescythe.entities.client;


import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import nikita.uniquescythe.entities.animation.ModAnimations;
import nikita.uniquescythe.entities.custom.BreezeEntity;
import nikita.uniquescythe.entities.custom.WindChargeProjectileEntity;


public class BreezeEntityModel<T extends BreezeEntity> extends SinglePartEntityModel<T>{
	private final ModelPart breeze;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart eyes;
	private final ModelPart eyebrows;
	private final ModelPart limbs;
	private final ModelPart wind_ar;
	private final ModelPart w1;
	private final ModelPart w2;
	private final ModelPart w3;
	private final ModelPart w4;
	private final ModelPart w5;
	private final ModelPart w6;
	public BreezeEntityModel(ModelPart root) {
		this.breeze = root.getChild("breeze");//root one
		this.body = breeze.getChild("body");//breeze.body
		//body children
		this.head = breeze.getChild("head");//breeze.body.head
		//head children
		//
		this.eyes = head.getChild("eyes");//breeze.body.head.eyes
		this.eyebrows = breeze.getChild("eyebrows");//breeze.body.head.eyebrows
		//
		this.limbs = body.getChild("limbs");//breeze.body.limbs





		this.wind_ar = breeze.getChild("wind_ar");
		//wind_ar children
		this.w1 = wind_ar.getChild("w1");
		this.w2 = wind_ar.getChild("w2");
		this.w3 = wind_ar.getChild("w3");
		this.w4 = wind_ar.getChild("w4");
		this.w5 = wind_ar.getChild("w5");
		this.w6 = wind_ar.getChild("w6");



	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData breeze = modelPartData.addChild("breeze", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 21.0F, 0.0F));

		ModelPartData eyebrows = breeze.addChild("eyebrows", ModelPartBuilder.create().uv(4, 24).cuboid(-5.0F, -1.25F, -3.5F, 10.0F, 3.0F, 4.0F, new Dilation(0.0F))
			.uv(4, 24).cuboid(-5.0F, -1.25F, -3.5F, 10.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -21.75F, -1.5F));

		ModelPartData head = breeze.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.25F, -2.5F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -21.75F, -1.5F));

		ModelPartData eyes = head.addChild("eyes", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, -2.5F, 0.0F, 8.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.25F, -2.75F));

		ModelPartData body = breeze.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -21.75F, -1.5F));

		ModelPartData limbs = body.addChild("limbs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 10.75F, 1.5F));

		ModelPartData leg_r1 = limbs.addChild("leg_r1", ModelPartBuilder.create().uv(0, 17).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, -3.0F, 0.2182F, 0.0F, 0.0F));

		ModelPartData leg_r2 = limbs.addChild("leg_r2", ModelPartBuilder.create().uv(0, 17).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.2182F));

		ModelPartData leg_r3 = limbs.addChild("leg_r3", ModelPartBuilder.create().uv(0, 17).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -1.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

		ModelPartData leg_r4 = limbs.addChild("leg_r4", ModelPartBuilder.create().uv(0, 17).cuboid(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, 3.0F, -0.2182F, 0.0F, 0.0F));

		ModelPartData wind_ar = breeze.addChild("wind_ar", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData w1 = wind_ar.addChild("w1", ModelPartBuilder.create().uv(129, 138).cuboid(-2.0F, -3.25F, -2.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData w2 = wind_ar.addChild("w2", ModelPartBuilder.create().uv(113, 123).cuboid(-4.0F, -5.75F, -4.0F, 8.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData w3 = wind_ar.addChild("w3", ModelPartBuilder.create().uv(0, 129).cuboid(-6.0F, -9.25F, -6.0F, 12.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData w4 = wind_ar.addChild("w4", ModelPartBuilder.create().uv(53, 121).cuboid(-9.0F, -14.75F, -9.0F, 18.0F, 6.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData w5 = wind_ar.addChild("w5", ModelPartBuilder.create().uv(49, 69).cuboid(-12.0F, -19.25F, -12.0F, 24.0F, 7.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData w6 = wind_ar.addChild("w6", ModelPartBuilder.create().uv(53, 121).cuboid(-9.0F, -22.75F, -9.0F, 18.0F, 6.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 145, 145);
	}

	@Override
	public void setAngles(T breezeEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		//animations here
		this.animate(breezeEntity.idleState, ModAnimations.BREEZE_IDLE, ageInTicks, 1F);

		this.animate(breezeEntity.attackState, ModAnimations.BREEZE_ATTACK, ageInTicks, 1F);

		this.animate(breezeEntity.triggerState, ModAnimations.BREEZE_TRIGGER, ageInTicks, 1F);

		this.animate(breezeEntity.sprintState, ModAnimations.BREEZE_SPRINT, ageInTicks, 1F);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {


		breeze.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);

	}

	@Override
	public ModelPart getPart() {
		return breeze;
	}



	public ModelPart getBody() {
		return this.body;
	}

	public ModelPart getHead() {
		return this.head;
	}

	public ModelPart getWindBody() {
		return this.wind_ar;
	}

	public ModelPart getEyebrows() {
		return this.eyebrows;
	}
}
