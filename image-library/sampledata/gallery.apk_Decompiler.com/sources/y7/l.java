package y7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionMotionPhotoHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ TextExtractionMotionPhotoHandler e;

    public /* synthetic */ l(TextExtractionMotionPhotoHandler textExtractionMotionPhotoHandler, int i2) {
        this.d = i2;
        this.e = textExtractionMotionPhotoHandler;
    }

    public final void run() {
        int i2 = this.d;
        TextExtractionMotionPhotoHandler textExtractionMotionPhotoHandler = this.e;
        switch (i2) {
            case 0:
                textExtractionMotionPhotoHandler.lambda$requestDetect$5();
                return;
            case 1:
                textExtractionMotionPhotoHandler.lambda$resetToIdle$8();
                return;
            case 2:
                textExtractionMotionPhotoHandler.resetTextHelper();
                return;
            default:
                textExtractionMotionPhotoHandler.lambda$refreshCaptureView$4();
                return;
        }
    }
}
