package zd;

import com.samsung.android.vexfwk.sdk.ocr.VexFwkImageOcr;
import java.util.function.Supplier;

/* renamed from: zd.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C1362a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ VexFwkImageOcr f;

    public /* synthetic */ C1362a(Object obj, VexFwkImageOcr vexFwkImageOcr, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = vexFwkImageOcr;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return VexFwkImageOcr.hasTextImpl$lambda$8(this.e, this.f);
            default:
                return VexFwkImageOcr.recognizeImpl$lambda$14(this.e, this.f);
        }
    }
}
