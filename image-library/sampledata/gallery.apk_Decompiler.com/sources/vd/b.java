package vd;

import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ VexFwkImageTranslator e;

    public /* synthetic */ b(VexFwkImageTranslator vexFwkImageTranslator, int i2) {
        this.d = i2;
        this.e = vexFwkImageTranslator;
    }

    public final Object get() {
        int i2 = this.d;
        VexFwkImageTranslator vexFwkImageTranslator = this.e;
        switch (i2) {
            case 0:
                return VexFwkImageTranslator.showInstallPopupAsync$lambda$26(vexFwkImageTranslator);
            default:
                return VexFwkImageTranslator.getAvailableLanguages$lambda$55(vexFwkImageTranslator);
        }
    }
}
