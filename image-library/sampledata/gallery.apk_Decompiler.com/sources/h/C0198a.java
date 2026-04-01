package h;

import android.animation.ValueAnimator;
import androidx.appcompat.widget.SeslSwitchBar;

/* renamed from: h.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0198a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SeslSwitchBar e;

    public /* synthetic */ C0198a(SeslSwitchBar seslSwitchBar, int i2) {
        this.d = i2;
        this.e = seslSwitchBar;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.d;
        SeslSwitchBar seslSwitchBar = this.e;
        switch (i2) {
            case 0:
                seslSwitchBar.lambda$initBackgroundColorAnimator$0(valueAnimator);
                return;
            default:
                seslSwitchBar.lambda$initBackgroundColorAnimator$1(valueAnimator);
                return;
        }
    }
}
