package hb;

import android.animation.ValueAnimator;
import com.samsung.android.gallery.widget.animations.textexpand.TextExpandAnimation;

/* renamed from: hb.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0693a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ TextExpandAnimation e;

    public /* synthetic */ C0693a(TextExpandAnimation textExpandAnimation, int i2) {
        this.d = i2;
        this.e = textExpandAnimation;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.d;
        TextExpandAnimation textExpandAnimation = this.e;
        switch (i2) {
            case 0:
                textExpandAnimation.lambda$createItemAnimation$0(valueAnimator);
                return;
            default:
                textExpandAnimation.lambda$createBgAnimation$1(valueAnimator);
                return;
        }
    }
}
