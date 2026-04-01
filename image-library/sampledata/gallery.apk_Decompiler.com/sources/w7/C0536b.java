package w7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture.CaptureHandler;

/* renamed from: w7.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0536b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ CaptureHandler e;

    public /* synthetic */ C0536b(CaptureHandler captureHandler, int i2) {
        this.d = i2;
        this.e = captureHandler;
    }

    public final void run() {
        int i2 = this.d;
        CaptureHandler captureHandler = this.e;
        switch (i2) {
            case 0:
                captureHandler.lambda$invalidate$11();
                return;
            default:
                captureHandler.lambda$hideButton$13();
                return;
        }
    }
}
