package x7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureImageHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ObjectCaptureImageHandler e;

    public /* synthetic */ g(ObjectCaptureImageHandler objectCaptureImageHandler, int i2) {
        this.d = i2;
        this.e = objectCaptureImageHandler;
    }

    public final void run() {
        int i2 = this.d;
        ObjectCaptureImageHandler objectCaptureImageHandler = this.e;
        switch (i2) {
            case 0:
                objectCaptureImageHandler.lambda$onGIFAnimationMode$3();
                return;
            default:
                objectCaptureImageHandler.disableObjectCaptureView(new Object[0]);
                return;
        }
    }
}
