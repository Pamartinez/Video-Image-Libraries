package w3;

import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Ae.a {
    public final /* synthetic */ int d;
    public final /* synthetic */ ImageTranslator e;

    public /* synthetic */ a(ImageTranslator imageTranslator, int i2) {
        this.d = i2;
        this.e = imageTranslator;
    }

    public final Object invoke() {
        int i2 = this.d;
        ImageTranslator imageTranslator = this.e;
        switch (i2) {
            case 0:
                return ImageTranslator.ocrData_delegate$lambda$0(imageTranslator);
            case 1:
                return ImageTranslator.originalBitmap_delegate$lambda$1(imageTranslator);
            case 2:
                return ImageTranslator.renderedBitmap_delegate$lambda$2(imageTranslator);
            default:
                return ImageTranslator.lastTranslatedText_delegate$lambda$3(imageTranslator);
        }
    }
}
