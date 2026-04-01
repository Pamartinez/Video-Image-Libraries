package O8;

import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ TextExtractionHelper e;

    public /* synthetic */ c(TextExtractionHelper textExtractionHelper, int i2) {
        this.d = i2;
        this.e = textExtractionHelper;
    }

    public final void run() {
        int i2 = this.d;
        TextExtractionHelper textExtractionHelper = this.e;
        switch (i2) {
            case 0:
                textExtractionHelper.lambda$getSuggestion$11();
                return;
            default:
                textExtractionHelper.requestImageTranslation();
                return;
        }
    }
}
