package o7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.input.ViewerTouchPadDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ViewerTouchPadDelegate e;

    public /* synthetic */ h(ViewerTouchPadDelegate viewerTouchPadDelegate, int i2) {
        this.d = i2;
        this.e = viewerTouchPadDelegate;
    }

    public final void run() {
        int i2 = this.d;
        ViewerTouchPadDelegate viewerTouchPadDelegate = this.e;
        switch (i2) {
            case 0:
                viewerTouchPadDelegate.lambda$updateLayout$2();
                return;
            default:
                viewerTouchPadDelegate.showWithAnim();
                return;
        }
    }
}
