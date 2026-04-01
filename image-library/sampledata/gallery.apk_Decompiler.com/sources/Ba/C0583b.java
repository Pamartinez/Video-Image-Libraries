package ba;

import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.function.Consumer;

/* renamed from: ba.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0583b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ UriBuilder e;

    public /* synthetic */ C0583b(UriBuilder uriBuilder, int i2) {
        this.d = i2;
        this.e = uriBuilder;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        UriBuilder uriBuilder = this.e;
        switch (i2) {
            case 0:
                uriBuilder.appendArg("people_only_them", (String) SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
                return;
            default:
                uriBuilder.appendArg((String) obj, true);
                return;
        }
    }
}
