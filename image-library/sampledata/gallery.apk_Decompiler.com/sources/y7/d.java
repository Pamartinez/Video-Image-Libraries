package y7;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionHandler;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ TextExtractionHandler e;

    public /* synthetic */ d(TextExtractionHandler textExtractionHandler, int i2) {
        this.d = i2;
        this.e = textExtractionHandler;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        TextExtractionHandler textExtractionHandler = this.e;
        switch (i2) {
            case 0:
                textExtractionHandler.onTapToDocumentScan((MediaItem) obj);
                return;
            case 1:
                textExtractionHandler.onTapTranslate((Boolean) obj);
                return;
            case 2:
                textExtractionHandler.lambda$detectDone$7((TextExtractionHelper) obj);
                return;
            default:
                textExtractionHandler.lambda$setState$15((EventContext) obj);
                return;
        }
    }
}
