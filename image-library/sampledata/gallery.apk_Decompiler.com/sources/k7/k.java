package k7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.JumpToTimelineDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ JumpToTimelineDelegate e;

    public /* synthetic */ k(JumpToTimelineDelegate jumpToTimelineDelegate, int i2) {
        this.d = i2;
        this.e = jumpToTimelineDelegate;
    }

    public final void run() {
        int i2 = this.d;
        JumpToTimelineDelegate jumpToTimelineDelegate = this.e;
        switch (i2) {
            case 0:
                jumpToTimelineDelegate.onTimeout();
                return;
            case 1:
                jumpToTimelineDelegate.lambda$jumpAndShrink$3();
                return;
            default:
                jumpToTimelineDelegate.lambda$handleJump$2();
                return;
        }
    }
}
