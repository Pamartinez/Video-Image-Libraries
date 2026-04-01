package Kb;

import android.animation.ValueAnimator;
import com.samsung.android.gallery.widget.listview.scroller.FastScrollerV2ThumbAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ FastScrollerV2ThumbAnimator e;
    public final /* synthetic */ Runnable f;

    public /* synthetic */ d(FastScrollerV2ThumbAnimator fastScrollerV2ThumbAnimator, Runnable runnable, int i2) {
        this.d = i2;
        this.e = fastScrollerV2ThumbAnimator;
        this.f = runnable;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.d) {
            case 0:
                this.e.lambda$new$0(this.f, valueAnimator);
                return;
            default:
                this.e.lambda$new$1(this.f, valueAnimator);
                return;
        }
    }
}
