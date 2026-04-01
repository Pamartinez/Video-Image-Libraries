package y7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionVideoHandler;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements TextExtractionTask.OnCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2763a;
    public final /* synthetic */ TextExtractionVideoHandler b;

    public /* synthetic */ o(TextExtractionVideoHandler textExtractionVideoHandler, int i2) {
        this.f2763a = i2;
        this.b = textExtractionVideoHandler;
    }

    public final void onComplete() {
        int i2 = this.f2763a;
        TextExtractionVideoHandler textExtractionVideoHandler = this.b;
        switch (i2) {
            case 0:
                textExtractionVideoHandler.detectDone();
                return;
            default:
                textExtractionVideoHandler.extractDone();
                return;
        }
    }
}
