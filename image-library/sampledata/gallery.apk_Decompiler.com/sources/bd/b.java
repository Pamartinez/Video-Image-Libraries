package bd;

import android.animation.ValueAnimator;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.AnimationManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnimationManager e;
    public final /* synthetic */ float f;

    public /* synthetic */ b(AnimationManager animationManager, float f5, int i2) {
        this.d = i2;
        this.e = animationManager;
        this.f = f5;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.d) {
            case 0:
                AnimationManager.hideByLuminance$lambda$22(this.e, this.f, valueAnimator);
                return;
            case 1:
                AnimationManager.createSizeAnimation$lambda$8(this.e, this.f, valueAnimator);
                return;
            default:
                AnimationManager.createLuminanceAnimation$lambda$10(this.e, this.f, valueAnimator);
                return;
        }
    }
}
