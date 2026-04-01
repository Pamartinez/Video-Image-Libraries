package bd;

import android.animation.ValueAnimator;
import com.samsung.android.sesl.visualeffect.lighteffects.guidinglight.AnimationManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AnimationManager e;
    public final /* synthetic */ float f;
    public final /* synthetic */ float g;

    public /* synthetic */ e(AnimationManager animationManager, float f5, float f8, int i2) {
        this.d = i2;
        this.e = animationManager;
        this.f = f5;
        this.g = f8;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.d) {
            case 0:
                AnimationManager.createNowBarAnimation$lambda$13(this.e, this.f, this.g, valueAnimator);
                return;
            case 1:
                AnimationManager.createNowBarAnimation$lambda$16(this.e, this.f, this.g, valueAnimator);
                return;
            case 2:
                AnimationManager.createNowBarAnimation$lambda$19(this.e, this.f, this.g, valueAnimator);
                return;
            case 3:
                AnimationManager.createSizeAnimation$lambda$2(this.e, this.f, this.g, valueAnimator);
                return;
            default:
                AnimationManager.createSizeAnimation$lambda$5(this.e, this.f, this.g, valueAnimator);
                return;
        }
    }
}
