package o6;

import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.OsdUiDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class v implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ OsdUiDelegate e;

    public /* synthetic */ v(OsdUiDelegate osdUiDelegate, int i2) {
        this.d = i2;
        this.e = osdUiDelegate;
    }

    public final void run() {
        int i2 = this.d;
        OsdUiDelegate osdUiDelegate = this.e;
        switch (i2) {
            case 0:
                osdUiDelegate.setSystemUiVisibleForce();
                return;
            default:
                osdUiDelegate.lambda$initializeUiState$1();
                return;
        }
    }
}
