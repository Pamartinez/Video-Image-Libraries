package y7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionImageHandler;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements TextExtractionTask.OnCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2758a;
    public final /* synthetic */ TextExtractionImageHandler b;

    public /* synthetic */ h(TextExtractionImageHandler textExtractionImageHandler, int i2) {
        this.f2758a = i2;
        this.b = textExtractionImageHandler;
    }

    public final void onComplete() {
        int i2 = this.f2758a;
        TextExtractionImageHandler textExtractionImageHandler = this.b;
        switch (i2) {
            case 0:
                textExtractionImageHandler.detectDone();
                return;
            default:
                textExtractionImageHandler.extractDone();
                return;
        }
    }
}
