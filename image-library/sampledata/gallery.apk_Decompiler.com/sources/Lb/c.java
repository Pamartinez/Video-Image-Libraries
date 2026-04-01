package Lb;

import com.samsung.android.gallery.widget.livemotion.LiveMotionViewPager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LiveMotionViewPager e;

    public /* synthetic */ c(LiveMotionViewPager liveMotionViewPager, int i2) {
        this.d = i2;
        this.e = liveMotionViewPager;
    }

    public final void run() {
        int i2 = this.d;
        LiveMotionViewPager liveMotionViewPager = this.e;
        switch (i2) {
            case 0:
                liveMotionViewPager.lambda$isTouchUpOrCancelInternal$8();
                return;
            default:
                liveMotionViewPager.lambda$onCompleteRestoreZoom$7();
                return;
        }
    }
}
