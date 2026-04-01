package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Fade extends Visibility {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FadeAnimatorListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private boolean mLayerTypeChanged = false;
        private final View mView;

        public FadeAnimatorListener(View view) {
            this.mView = view;
        }

        public void onAnimationCancel(Animator animator) {
            ViewUtils.setTransitionAlpha(this.mView, 1.0f);
        }

        public void onAnimationEnd(Animator animator) {
            onAnimationEnd(animator, false);
        }

        public void onAnimationStart(Animator animator) {
            if (this.mView.hasOverlappingRendering() && this.mView.getLayerType() == 0) {
                this.mLayerTypeChanged = true;
                this.mView.setLayerType(2, (Paint) null);
            }
        }

        public void onTransitionPause(Transition transition) {
            float f;
            if (this.mView.getVisibility() == 0) {
                f = ViewUtils.getTransitionAlpha(this.mView);
            } else {
                f = 0.0f;
            }
            this.mView.setTag(R$id.transition_pause_alpha, Float.valueOf(f));
        }

        public void onTransitionResume(Transition transition) {
            this.mView.setTag(R$id.transition_pause_alpha, (Object) null);
        }

        public void onTransitionStart(Transition transition) {
        }

        public void onAnimationEnd(Animator animator, boolean z) {
            if (this.mLayerTypeChanged) {
                this.mView.setLayerType(0, (Paint) null);
            }
            if (!z) {
                ViewUtils.setTransitionAlpha(this.mView, 1.0f);
                ViewUtils.clearNonTransitionAlpha(this.mView);
            }
        }

        public void onTransitionStart(Transition transition, boolean z) {
        }

        public void onTransitionCancel(Transition transition) {
        }

        public void onTransitionEnd(Transition transition) {
        }
    }

    public Fade(int i2) {
        setMode(i2);
    }

    private Animator createAnimation(View view, float f, float f5) {
        if (f == f5) {
            return null;
        }
        ViewUtils.setTransitionAlpha(view, f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, ViewUtils.TRANSITION_ALPHA, new float[]{f5});
        FadeAnimatorListener fadeAnimatorListener = new FadeAnimatorListener(view);
        ofFloat.addListener(fadeAnimatorListener);
        getRootTransition().addListener(fadeAnimatorListener);
        return ofFloat;
    }

    private static float getStartAlpha(TransitionValues transitionValues, float f) {
        Float f5;
        if (transitionValues == null || (f5 = (Float) transitionValues.values.get("android:fade:transitionAlpha")) == null) {
            return f;
        }
        return f5.floatValue();
    }

    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        Float f = (Float) transitionValues.view.getTag(R$id.transition_pause_alpha);
        if (f == null) {
            if (transitionValues.view.getVisibility() == 0) {
                f = Float.valueOf(ViewUtils.getTransitionAlpha(transitionValues.view));
            } else {
                f = Float.valueOf(0.0f);
            }
        }
        transitionValues.values.put("android:fade:transitionAlpha", f);
    }

    public boolean isSeekingSupported() {
        return true;
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewUtils.saveNonTransitionAlpha(view);
        return createAnimation(view, getStartAlpha(transitionValues, 0.0f), 1.0f);
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewUtils.saveNonTransitionAlpha(view);
        Animator createAnimation = createAnimation(view, getStartAlpha(transitionValues, 1.0f), 0.0f);
        if (createAnimation == null) {
            ViewUtils.setTransitionAlpha(view, getStartAlpha(transitionValues2, 1.0f));
        }
        return createAnimation;
    }

    public Fade() {
    }
}
