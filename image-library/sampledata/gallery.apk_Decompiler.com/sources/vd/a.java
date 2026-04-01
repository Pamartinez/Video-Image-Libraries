package vd;

import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ String e;
    public final /* synthetic */ VexFwkImageTranslator f;

    public /* synthetic */ a(String str, VexFwkImageTranslator vexFwkImageTranslator, int i2) {
        this.d = i2;
        this.e = str;
        this.f = vexFwkImageTranslator;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return VexFwkImageTranslator.getAvailableSourceLanguageTo$lambda$47(this.e, this.f);
            default:
                return VexFwkImageTranslator.getAvailableTargetLanguageFrom$lambda$38(this.e, this.f);
        }
    }
}
