package Ba;

import android.animation.ValueAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ValueAnimator e;

    public /* synthetic */ p(ValueAnimator valueAnimator, int i2) {
        this.d = i2;
        this.e = valueAnimator;
    }

    public final void run() {
        int i2 = this.d;
        ValueAnimator valueAnimator = this.e;
        switch (i2) {
            case 0:
                valueAnimator.start();
                return;
            default:
                valueAnimator.removeAllUpdateListeners();
                return;
        }
    }
}
