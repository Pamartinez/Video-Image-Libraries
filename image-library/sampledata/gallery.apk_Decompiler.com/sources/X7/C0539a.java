package x7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureHandler;

/* renamed from: x7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0539a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ObjectCaptureHandler e;

    public /* synthetic */ C0539a(ObjectCaptureHandler objectCaptureHandler, int i2) {
        this.d = i2;
        this.e = objectCaptureHandler;
    }

    public final void run() {
        int i2 = this.d;
        ObjectCaptureHandler objectCaptureHandler = this.e;
        switch (i2) {
            case 0:
                objectCaptureHandler.lambda$disableObjectCaptureView$2();
                return;
            case 1:
                objectCaptureHandler.lambda$onCapture$3();
                return;
            case 2:
                objectCaptureHandler.publishObjectCaptureResetConsumer();
                return;
            default:
                objectCaptureHandler.enableObjectCaptureView();
                return;
        }
    }
}
