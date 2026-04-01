package k7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.NaviUpDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ NaviUpDelegate e;

    public /* synthetic */ p(NaviUpDelegate naviUpDelegate, int i2) {
        this.d = i2;
        this.e = naviUpDelegate;
    }

    public final void run() {
        int i2 = this.d;
        NaviUpDelegate naviUpDelegate = this.e;
        switch (i2) {
            case 0:
                naviUpDelegate.launchTimelineView();
                return;
            default:
                naviUpDelegate.lambda$launchTimelineView$1();
                return;
        }
    }
}
