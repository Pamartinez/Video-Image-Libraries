package Kb;

import android.animation.ValueAnimator;
import com.samsung.android.gallery.widget.listview.scroller.FastScroller;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ FastScroller e;

    public /* synthetic */ c(FastScroller fastScroller, int i2) {
        this.d = i2;
        this.e = fastScroller;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.d;
        FastScroller fastScroller = this.e;
        switch (i2) {
            case 0:
                fastScroller.lambda$new$3(valueAnimator);
                return;
            default:
                fastScroller.lambda$new$4(valueAnimator);
                return;
        }
    }
}
