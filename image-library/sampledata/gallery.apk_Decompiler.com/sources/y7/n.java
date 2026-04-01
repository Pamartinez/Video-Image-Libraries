package y7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionVideoHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ TextExtractionVideoHandler e;

    public /* synthetic */ n(TextExtractionVideoHandler textExtractionVideoHandler, int i2) {
        this.d = i2;
        this.e = textExtractionVideoHandler;
    }

    public final void run() {
        int i2 = this.d;
        TextExtractionVideoHandler textExtractionVideoHandler = this.e;
        switch (i2) {
            case 0:
                textExtractionVideoHandler.lambda$resetToIdle$4();
                return;
            case 1:
                textExtractionVideoHandler.resetTextHelper();
                return;
            case 2:
                textExtractionVideoHandler.lambda$refreshLayout$2();
                return;
            default:
                textExtractionVideoHandler.lambda$resetAndDetect$3();
                return;
        }
    }
}
