package x7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureProcessingHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ ObjectCaptureProcessingHandler e;

    public /* synthetic */ i(ObjectCaptureProcessingHandler objectCaptureProcessingHandler, int i2) {
        this.d = i2;
        this.e = objectCaptureProcessingHandler;
    }

    public final void run() {
        int i2 = this.d;
        ObjectCaptureProcessingHandler objectCaptureProcessingHandler = this.e;
        switch (i2) {
            case 0:
                objectCaptureProcessingHandler.lambda$updateLoadingView$5();
                return;
            default:
                objectCaptureProcessingHandler.lambda$onProgressEnd$3();
                return;
        }
    }
}
