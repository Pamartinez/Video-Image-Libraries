package androidx.picker.widget;

import android.view.View;
import android.view.animation.PathInterpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SeslCircularSeekBarRevealAnimation {
    private final PathInterpolator mAniInterpolator = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
    private boolean mIsRevealAnimation;
    private SeslCircularSeekBarView mView;

    public SeslCircularSeekBarRevealAnimation(View view) {
        if (view instanceof SeslCircularSeekBarView) {
            this.mView = (SeslCircularSeekBarView) view;
        }
    }

    public boolean isRevealAnimation() {
        return this.mIsRevealAnimation;
    }
}
