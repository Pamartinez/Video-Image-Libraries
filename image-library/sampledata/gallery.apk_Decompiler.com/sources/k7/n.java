package k7;

import com.samsung.android.gallery.app.ui.viewer2.container.delegate.MirroringDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MirroringDelegate e;

    public /* synthetic */ n(MirroringDelegate mirroringDelegate, int i2) {
        this.d = i2;
        this.e = mirroringDelegate;
    }

    public final void run() {
        int i2 = this.d;
        MirroringDelegate mirroringDelegate = this.e;
        switch (i2) {
            case 0:
                mirroringDelegate.lambda$updateEnablePreviewPlay$3();
                return;
            default:
                mirroringDelegate.lambda$showPresentation$1();
                return;
        }
    }
}
