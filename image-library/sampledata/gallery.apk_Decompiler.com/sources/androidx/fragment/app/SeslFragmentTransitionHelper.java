package androidx.fragment.app;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.Resources;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import androidx.fragment.R$animator;
import i.C0212a;
import java.util.EnumMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslFragmentTransitionHelper {
    private static final PathInterpolator DEPTH_IN_INTERPOLATION = new PathInterpolator(0.22f, 0.5f, 0.0f, 1.0f);
    private static final PathInterpolator DEPTH_OUT_INTERPOLATION = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final EnumMap<AnimationType, AnimatorStrategy> STRATEGIES;
    private final Context mContext;
    private final int mScreenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private View mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum AnimationType {
        CLOSE_EXIT(R$animator.sesl_fragment_close_exit),
        CLOSE_ENTER(R$animator.sesl_fragment_close_enter),
        OPEN_ENTER(R$animator.sesl_fragment_open_enter),
        OPEN_EXIT(R$animator.sesl_fragment_open_exit);
        
        private static final SparseArray<AnimationType> LOOKUP = null;
        final int resId;

        static {
            int i2;
            LOOKUP = new SparseArray<>();
            for (AnimationType animationType : values()) {
                LOOKUP.put(animationType.resId, animationType);
            }
        }

        private AnimationType(int i2) {
            this.resId = i2;
        }

        public static AnimationType fromResId(int i2) {
            return LOOKUP.get(i2);
        }
    }

    @FunctionalInterface
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AnimatorStrategy {
        AnimatorSet build(SeslFragmentTransitionHelper seslFragmentTransitionHelper, boolean z, boolean z3, TransitionGeometry transitionGeometry);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TransitionGeometry {
        private final int leftMargin;
        private final int rightMargin;
        private final int width;

        public TransitionGeometry(int i2, int[] iArr) {
            this.width = i2;
            this.leftMargin = iArr[0];
            this.rightMargin = iArr[1];
        }

        public int getLeftMargin() {
            return this.leftMargin;
        }

        public int getRightMargin() {
            return this.rightMargin;
        }

        public int getWidth() {
            return this.width;
        }
    }

    static {
        EnumMap<AnimationType, AnimatorStrategy> enumMap = new EnumMap<>(AnimationType.class);
        STRATEGIES = enumMap;
        enumMap.put(AnimationType.CLOSE_EXIT, new j(0));
        enumMap.put(AnimationType.CLOSE_ENTER, new j(1));
        enumMap.put(AnimationType.OPEN_ENTER, new j(2));
        enumMap.put(AnimationType.OPEN_EXIT, new j(3));
    }

    public SeslFragmentTransitionHelper(View view) {
        this.mView = view;
        this.mContext = view.getContext();
    }

    /* access modifiers changed from: private */
    public AnimatorSet animatorSetOf(ObjectAnimator... objectAnimatorArr) {
        AnimatorSet animatorSet = new AnimatorSet();
        if (!(objectAnimatorArr == null || objectAnimatorArr.length == 0)) {
            if (objectAnimatorArr.length == 1) {
                animatorSet.play(objectAnimatorArr[0]);
                return animatorSet;
            }
            animatorSet.playTogether(objectAnimatorArr);
        }
        return animatorSet;
    }

    private ObjectAnimator buildAlphaAnimator(int i2, float f, float f5) {
        return createAnimator(LINEAR_INTERPOLATOR, i2, "alpha", Keyframe.ofFloat(0.0f, f), Keyframe.ofFloat(1.0f, f5));
    }

    private ObjectAnimator buildTranslateXAnimator(Interpolator interpolator, int i2, float f, float f5) {
        return createAnimator(interpolator, i2, "x", Keyframe.ofFloat(0.0f, f), Keyframe.ofFloat(1.0f, f5));
    }

    private static float clamp(float f, float f5, float f8) {
        return Math.max(f, Math.min(f5, f8));
    }

    private ObjectAnimator createAnimator(Interpolator interpolator, int i2, String str, Keyframe... keyframeArr) {
        PropertyValuesHolder ofKeyframe = PropertyValuesHolder.ofKeyframe(str, keyframeArr);
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setInterpolator(interpolator);
        objectAnimator.setValues(new PropertyValuesHolder[]{ofKeyframe});
        objectAnimator.setDuration((long) i2);
        return objectAnimator;
    }

    private int getEffectiveWidth() {
        if (this.mView.getWidth() > 0) {
            return this.mView.getWidth();
        }
        return this.mScreenWidth;
    }

    private int[] getHorizontalMargins() {
        int i2;
        int i7;
        ViewGroup.LayoutParams layoutParams = this.mView.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            i7 = marginLayoutParams.leftMargin;
            i2 = marginLayoutParams.rightMargin;
        } else {
            i7 = 0;
            i2 = 0;
        }
        return new int[]{i7, i2};
    }

    private Interpolator getInterpolator(boolean z) {
        if (z) {
            return DEPTH_OUT_INTERPOLATION;
        }
        return DEPTH_IN_INTERPOLATION;
    }

    private TransitionGeometry getTransitionGeometry() {
        return new TransitionGeometry(getEffectiveWidth(), getHorizontalMargins());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ AnimatorSet lambda$static$0(SeslFragmentTransitionHelper seslFragmentTransitionHelper, boolean z, boolean z3, TransitionGeometry transitionGeometry) {
        Interpolator interpolator;
        if (z) {
            interpolator = seslFragmentTransitionHelper.getInterpolator(true);
        } else {
            interpolator = LINEAR_INTERPOLATOR;
        }
        return seslFragmentTransitionHelper.animatorSetOf(seslFragmentTransitionHelper.buildTranslateXAnimator(interpolator, 400, seslFragmentTransitionHelper.mView.getTranslationX() + ((float) transitionGeometry.getLeftMargin()), (float) transitionGeometry.getWidth()));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ AnimatorSet lambda$static$1(SeslFragmentTransitionHelper seslFragmentTransitionHelper, boolean z, boolean z3, TransitionGeometry transitionGeometry) {
        float f;
        Interpolator interpolator;
        if (z3) {
            f = seslFragmentTransitionHelper.mView.getTranslationX() + ((float) transitionGeometry.getLeftMargin());
        } else {
            f = ((float) (transitionGeometry.getWidth() + transitionGeometry.getLeftMargin() + transitionGeometry.getRightMargin())) * -0.33f;
        }
        if (z) {
            interpolator = seslFragmentTransitionHelper.getInterpolator(true);
        } else {
            interpolator = LINEAR_INTERPOLATOR;
        }
        ObjectAnimator buildTranslateXAnimator = seslFragmentTransitionHelper.buildTranslateXAnimator(interpolator, 400, f, (float) transitionGeometry.getLeftMargin());
        if (!z || z3) {
            return seslFragmentTransitionHelper.animatorSetOf(buildTranslateXAnimator);
        }
        return seslFragmentTransitionHelper.animatorSetOf(buildTranslateXAnimator, seslFragmentTransitionHelper.buildAlphaAnimator(150, 0.0f, 1.0f));
    }

    private static float lerp(float f, float f5, float f8) {
        return C0212a.a(f5, f, f8, f);
    }

    public float getProgress(float f) {
        float clamp = clamp(0.0f, 1.0f, f);
        if (clamp <= 0.5f || clamp > 1.0f) {
            return clamp;
        }
        return lerp(0.5f, 0.6f, (clamp - 0.5f) / 0.5f);
    }

    public void initTransition() {
        View view = this.mView;
        if (view != null) {
            view.setTranslationX(0.0f);
        }
    }

    public void update(View view) {
        if (this.mView != view) {
            this.mView = view;
        }
    }

    public AnimatorSet createAnimator(int i2, boolean z, boolean z3) {
        AnimatorStrategy animatorStrategy;
        AnimationType fromResId = AnimationType.fromResId(i2);
        if (fromResId == null || (animatorStrategy = STRATEGIES.get(fromResId)) == null) {
            return null;
        }
        return animatorStrategy.build(this, z, z3, getTransitionGeometry());
    }
}
