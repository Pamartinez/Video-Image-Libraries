package S2;

import T2.c;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends l {
    public static final d e = new l(0);
    public static final d f = new l(1);

    public final String a() {
        if (this.d == 0) {
            return SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE;
        }
        return SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE;
    }

    public final String e() {
        return "boolean";
    }

    public final c getType() {
        return c.f829j;
    }

    public final String toString() {
        if (this.d == 0) {
            return "boolean{false}";
        }
        return "boolean{true}";
    }
}
