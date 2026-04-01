package y7;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ TextExtractionHandler e;

    public /* synthetic */ c(TextExtractionHandler textExtractionHandler, int i2) {
        this.d = i2;
        this.e = textExtractionHandler;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        TextExtractionHandler textExtractionHandler = this.e;
        switch (i2) {
            case 0:
                textExtractionHandler.lambda$updateDocumentScanButtonVisibility$16(view);
                return;
            default:
                textExtractionHandler.onButtonClick(view);
                return;
        }
    }
}
