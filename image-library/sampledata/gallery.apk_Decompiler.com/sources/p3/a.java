package p3;

import Ae.b;
import com.samsung.android.app.sdk.deepsky.textextraction.result.OcrData;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;
    public final /* synthetic */ String f;

    public /* synthetic */ a(String str, String str2, int i2) {
        this.d = i2;
        this.e = str;
        this.f = str2;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return OcrData.toBlockStringList$lambda$5$lambda$4(this.e, this.f, (OcrData.LineInfo) obj);
            default:
                return OcrData.joinToStringWithSeparator$lambda$9$lambda$8(this.e, this.f, (OcrData.LineInfo) obj);
        }
    }
}
