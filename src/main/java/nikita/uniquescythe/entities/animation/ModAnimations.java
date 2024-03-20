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





}
