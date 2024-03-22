package nikita.uniquescythe.entities.animation;

import net.minecraft.client.render.animation.Animation;//the same
import net.minecraft.client.render.animation.Animator;//animation helper
import net.minecraft.client.render.animation.PartAnimation;//transformation
import net.minecraft.client.render.animation.AnimationKeyframe;//keyframe

import static java.lang.Float.NaN;

public class ModAnimations {


	//wind charge entity animation
	public static final Animation WIND_CHARGE = Animation.Builder.withLength(6f).looping()
		.addPartAnimation("charge",
			new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
				new AnimationKeyframe(0f, Animator.rotate(0f, 0f, 0f),
					PartAnimation.Interpolations.LINEAR),
				new AnimationKeyframe(6f, Animator.rotate(0f, -1080.0F, 0f),
					PartAnimation.Interpolations.LINEAR)))
		.addPartAnimation("wind_inner",
			new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
				new AnimationKeyframe(0f, Animator.rotate(0f, 0f, 0f),
					PartAnimation.Interpolations.LINEAR),
				new AnimationKeyframe(3f, Animator.rotate(0f, -360f, 0f),
					PartAnimation.Interpolations.LINEAR),
				new AnimationKeyframe(6f, Animator.rotate(0f, -720, 0f),
					PartAnimation.Interpolations.LINEAR)))
		.addPartAnimation("wind_outer",
			new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
				new AnimationKeyframe(0f, Animator.rotate(0f, 0f, 0f),
					PartAnimation.Interpolations.LINEAR),
				new AnimationKeyframe(6f, Animator.rotate(0f, -360f, 0f),
					PartAnimation.Interpolations.LINEAR))).build();



	public static final Animation BREEZE_IDLE = Animation.Builder.withLength(1.5F).looping()
		.addPartAnimation("head", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(5.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F,  Animator.rotate(5.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("head", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F, Animator.translate(0.0F, 1.25F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("limbs", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("limbs", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F, Animator.translate(0.0F, 0.5F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("limbs", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F, Animator.rotate(1.25F, 1.2F, 1.25F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("wind_ar", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("wind_ar", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F, Animator.rotate(1.0F, 1.1F, 1.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.SPLINE)
		))
		.addPartAnimation("w1", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w1", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, -3.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w3", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, 720.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w3", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, -1.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w5", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, 720.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w5", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(0.9F, 1.0F, 0.9F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w6", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w6", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(0.9F, 1.0F, 0.9F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w2", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, -2.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyebrows", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F, Animator.translate(0.0F, 0.5F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyes", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F, Animator.translate(0.0F, 0.25F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.build();

	public static final Animation BREEZE_TRIGGER = Animation.Builder.withLength(1.5F).looping()
		.addPartAnimation("head", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(5.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.2083F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.5833F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.9167F,  Animator.rotate(5.0F, -360.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F,  Animator.rotate(5.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("head", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.2083F, Animator.translate(0.0F, 3.25F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.5833F, Animator.translate(0.0F, 4.25F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.9167F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("limbs", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("limbs", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F, Animator.translate(0.0F, 0.5F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("limbs", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F, Animator.rotate(1.25F, 1.2F, 1.25F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("wind_ar", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("wind_ar", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F, Animator.rotate(1.0F, 1.1F, 1.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.SPLINE)
		))
		.addPartAnimation("w1", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w1", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, -3.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w3", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, 720.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w3", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, -1.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w5", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, 720.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w5", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(0.9F, 1.0F, 0.9F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w6", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(0.9F, 1.0F, 0.9F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w2", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, -2.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyebrows", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.2083F,  Animator.rotate(-17.5F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.5833F,  Animator.rotate(-17.5F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.9167F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F,  Animator.rotate(10.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyebrows", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.2083F, Animator.translate(0.0F, 0.5F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.5833F, Animator.translate(0.0F, 0.5F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.9167F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.25F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyes", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.2083F, Animator.translate(0.0F, 0.5F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.5833F, Animator.translate(0.0F, 0.5F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.9167F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyes", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.2083F, Animator.rotate(0.9F, 1.325F, 1.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.5833F, Animator.rotate(0.9F, 1.325F, 1.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.9167F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.SPLINE)
		))
		.build();

	public static final Animation BREEZE_ATTACK = Animation.Builder.withLength(1.5F)
		.addPartAnimation("head", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(5.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.625F,  Animator.rotate(-10.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.7917F,  Animator.rotate(10.0F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F,  Animator.rotate(5.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("head", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.625F, Animator.translate(0.0F, 2.25F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.7917F, Animator.translate(0.0F, 0.0F, -2.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("limbs", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("limbs", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F, Animator.translate(0.0F, 0.5F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("limbs", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.625F, Animator.rotate(1.4083F, 1.1667F, 1.4083F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.9167F, Animator.rotate(1.0F, 1.2F, 1.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("wind_ar", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.5F,  Animator.rotate(0.0F, -120.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(0.625F,  Animator.rotate(0.0F, -155.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F,  Animator.rotate(0.0F, -180.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("wind_ar", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.625F, Animator.rotate(1.0F, 1.1F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.75F, Animator.rotate(1.1F, 0.9938F, 1.1F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.SPLINE)
		))
		.addPartAnimation("w1", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w1", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, -3.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w3", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, 720.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w3", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, -1.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w5", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, 720.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w5", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(0.9F, 1.0F, 0.9F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w6", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(0.9F, 1.0F, 0.9F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w2", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, -2.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyebrows", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(12.5F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.625F,  Animator.rotate(-2.5F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.7917F,  Animator.rotate(12.5F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F,  Animator.rotate(12.5F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyebrows", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.25F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.625F, Animator.translate(0.0F, 1.75F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.7917F, Animator.translate(0.0F, 0.25F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.25F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyes", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.625F, Animator.translate(0.0F, 0.5F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.7917F, Animator.translate(0.0F, -0.25F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.build();

	public static final Animation BREEZE_SPRINT = Animation.Builder.withLength(1.5F).looping()
		.addPartAnimation("head", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(5.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.1667F,  Animator.rotate(32.5F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.0F,  Animator.rotate(32.5F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F,  Animator.rotate(5.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("head", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.1667F, Animator.translate(0.0F, 0.42F, -3.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.0F, Animator.translate(0.0F, 0.42F, -3.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("limbs", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("limbs", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.1667F, Animator.translate(0.0F, 0.5F, -1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.0F, Animator.translate(0.0F, 0.5F, -1.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("limbs", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("wind_ar", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("wind_ar", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.2083F, Animator.translate(0.0F, 0.0F, -2.75F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.25F, Animator.translate(0.0F, 0.0F, -2.75F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("wind_ar", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.2083F, Animator.rotate(1.05F, 0.8511F, 1.05F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.25F, Animator.rotate(1.05F, 0.8511F, 1.05F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w1", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, -360.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w1", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, -3.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w3", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, 720.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w3", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, -1.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w5", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, 720.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w5", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(0.9F, 1.0F, 0.9F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w6", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(0.9F, 1.0F, 0.9F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("w2", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, -2.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyebrows", new PartAnimation(PartAnimation.AnimationTargets.ROTATE,
			new AnimationKeyframe(0.0F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.1667F,  Animator.rotate(-7.5F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.0F,  Animator.rotate(-7.5F, 0.0F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F,  Animator.rotate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyebrows", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.1667F, Animator.translate(0.0F, 1.25F, 0.25F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.0F, Animator.translate(0.0F, 1.25F, 0.25F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyebrows", new PartAnimation(PartAnimation.AnimationTargets.SCALE,
			new AnimationKeyframe(0.0F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.1667F, Animator.rotate(1.0F, 1.225F, 1.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.0F, Animator.rotate(1.0F, 1.225F, 1.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.rotate(1.0F, 1.0F, 1.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("body", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.2083F, Animator.translate(0.0F, -3.75F, -3.25F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.0F, Animator.translate(0.0F, -3.75F, -3.25F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.addPartAnimation("eyes", new PartAnimation(PartAnimation.AnimationTargets.TRANSLATE,
			new AnimationKeyframe(0.0F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(0.1667F, Animator.translate(0.0F, 0.75F, 0.0F), PartAnimation.Interpolations.LINEAR),
			new AnimationKeyframe(1.0F, Animator.translate(0.0F, 0.75F, 0.0F), PartAnimation.Interpolations.SPLINE),
			new AnimationKeyframe(1.5F, Animator.translate(0.0F, 0.0F, 0.0F), PartAnimation.Interpolations.LINEAR)
		))
		.build();

}
