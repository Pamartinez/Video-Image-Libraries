package com.samsung.android.gallery.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.Interpolator;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.RotateAnimator2;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViAnimator {
    public AnimatorSet animatorSet;

    public /* synthetic */ ViAnimator(int i2) {
        this();
    }

    private ViAnimator() {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        private final List<Animator> animationList = new ArrayList();
        private SimpleAnimatorListener animationListener;
        private final ViAnimator info = new ViAnimator(0);
        private final HashMap<Integer, PointF> transitionPointMap = new HashMap<>();

        public Builder addAnimator(View view, int i2, int i7, Animator animator, Interpolator interpolator) {
            if (i7 >= i2) {
                if (interpolator != null) {
                    animator.setInterpolator(interpolator);
                }
                animator.setStartDelay((long) i2);
                animator.setDuration((long) (i7 - i2));
                this.animationList.add(animator);
                return this;
            }
            throw new InternalError("startTime should be less than endTime");
        }

        public Builder addRotateAnimator(View view, int i2, int i7, float f, float f5, Interpolator interpolator) {
            addAnimator(view, i2, i7, new RotateAnimator2(view, f, f5), interpolator);
            return this;
        }

        public Builder addScaleAnimator(View view, int i2, int i7, float f, float f5, Interpolator interpolator) {
            ScaleAnimator scaleAnimator = new ScaleAnimator(view, f, f5);
            scaleAnimator.setInterpolator(interpolator);
            addAnimator(view, i2, i7, scaleAnimator, interpolator);
            return this;
        }

        public Builder addTranslateAnimator(View view, int i2, int i7, float f, float f5, Interpolator interpolator) {
            this.transitionPointMap.putIfAbsent(Integer.valueOf(view.hashCode()), new PointF(view.getX(), view.getY()));
            float f8 = f;
            TranslationAnimator translationAnimator = new TranslationAnimator(view);
            float f10 = 0.0f;
            PointF orDefault = this.transitionPointMap.getOrDefault(Integer.valueOf(view.hashCode()), new PointF(0.0f, 0.0f));
            float f11 = orDefault != null ? orDefault.x : 0.0f;
            if (orDefault != null) {
                f10 = orDefault.y;
            }
            float f12 = f8 + f11;
            float f13 = f5 + f10;
            translationAnimator.translateX(f11, f12);
            translationAnimator.translateY(f10, f13);
            this.transitionPointMap.put(Integer.valueOf(view.hashCode()), new PointF(f12, f13));
            addAnimator(view, i2, i7, translationAnimator, interpolator);
            return this;
        }

        public ViAnimator build() {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(this.animationList);
            SimpleAnimatorListener simpleAnimatorListener = this.animationListener;
            if (simpleAnimatorListener != null) {
                animatorSet.addListener(simpleAnimatorListener);
            }
            ViAnimator viAnimator = this.info;
            viAnimator.animatorSet = animatorSet;
            return viAnimator;
        }

        public Builder setListener(SimpleAnimatorListener simpleAnimatorListener) {
            this.animationListener = simpleAnimatorListener;
            return this;
        }

        public Builder addTranslateAnimator(View view, int i2, int i7, int i8, int i10, int i11, int i12, Interpolator interpolator) {
            addTranslateAnimator(view, i2, i7, (float) (i11 - i8), (float) (i12 - i10), interpolator);
            return this;
        }
    }
}
