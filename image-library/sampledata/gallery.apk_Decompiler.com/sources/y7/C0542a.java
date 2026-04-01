package y7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionHandler;

/* renamed from: y7.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0542a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ TextExtractionHandler e;

    public /* synthetic */ C0542a(TextExtractionHandler textExtractionHandler, int i2) {
        this.d = i2;
        this.e = textExtractionHandler;
    }

    public final void run() {
        int i2 = this.d;
        TextExtractionHandler textExtractionHandler = this.e;
        switch (i2) {
            case 0:
                textExtractionHandler.lambda$onTableModeChanged$3();
                return;
            case 1:
                textExtractionHandler.lambda$clearTextExtractionViewVisibilityChangeFlag$4();
                return;
            case 2:
                textExtractionHandler.lambda$removeAllCapsules$9();
                return;
            case 3:
                textExtractionHandler.lambda$extractDone$8();
                return;
            case 4:
                textExtractionHandler.lambda$clearTextSelection$5();
                return;
            case 5:
                textExtractionHandler.lambda$setState$13();
                return;
            default:
                textExtractionHandler.lambda$updateCapsuleMargin$17();
                return;
        }
    }
}
