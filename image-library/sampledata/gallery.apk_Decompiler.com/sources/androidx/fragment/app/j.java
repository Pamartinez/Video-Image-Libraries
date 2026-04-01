package androidx.fragment.app;

import android.animation.AnimatorSet;
import androidx.fragment.app.SeslFragmentTransitionHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements SeslFragmentTransitionHelper.AnimatorStrategy {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f992a;

    public /* synthetic */ j(int i2) {
        this.f992a = i2;
    }

    public final AnimatorSet build(SeslFragmentTransitionHelper seslFragmentTransitionHelper, boolean z, boolean z3, SeslFragmentTransitionHelper.TransitionGeometry transitionGeometry) {
        switch (this.f992a) {
            case 0:
                return SeslFragmentTransitionHelper.lambda$static$0(seslFragmentTransitionHelper, z, z3, transitionGeometry);
            case 1:
                return SeslFragmentTransitionHelper.lambda$static$1(seslFragmentTransitionHelper, z, z3, transitionGeometry);
            case 2:
                return seslFragmentTransitionHelper.animatorSetOf(seslFragmentTransitionHelper.buildTranslateXAnimator(seslFragmentTransitionHelper.getInterpolator(false), 450, (float) transitionGeometry.getWidth(), (float) transitionGeometry.getLeftMargin()));
            default:
                return seslFragmentTransitionHelper.animatorSetOf(seslFragmentTransitionHelper.buildTranslateXAnimator(seslFragmentTransitionHelper.getInterpolator(false), 450, (float) transitionGeometry.getLeftMargin(), ((float) transitionGeometry.getWidth()) * -0.33f), seslFragmentTransitionHelper.buildAlphaAnimator(150, 1.0f, 0.0f));
        }
    }
}
