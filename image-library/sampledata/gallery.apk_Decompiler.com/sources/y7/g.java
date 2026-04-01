package y7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionImageHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ TextExtractionImageHandler e;

    public /* synthetic */ g(TextExtractionImageHandler textExtractionImageHandler, int i2) {
        this.d = i2;
        this.e = textExtractionImageHandler;
    }

    public final void run() {
        int i2 = this.d;
        TextExtractionImageHandler textExtractionImageHandler = this.e;
        switch (i2) {
            case 0:
                textExtractionImageHandler.lambda$requestDetect$7();
                return;
            case 1:
                textExtractionImageHandler.lambda$bitmapInvalidateDetect$4();
                return;
            case 2:
                textExtractionImageHandler.lambda$onGIFAnimationMode$6();
                return;
            case 3:
                textExtractionImageHandler.lambda$detectDone$5();
                return;
            case 4:
                textExtractionImageHandler.bitmapLoadAndDetect();
                return;
            default:
                textExtractionImageHandler.bitmapInvalidateDetect();
                return;
        }
    }
}
