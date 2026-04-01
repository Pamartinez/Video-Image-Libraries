package vd;

import com.samsung.android.vexfwk.param.VexFwkOcrResult;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;
import com.samsung.android.vexfwk.sdk.imagetranslator.VexFwkImageTranslator;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Supplier {
    public final /* synthetic */ Object d;
    public final /* synthetic */ VexFwkImageTranslator e;
    public final /* synthetic */ String f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ VexFwkOcrResult f5150h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ VexFwkOcrResultV2 f5151i;

    public /* synthetic */ e(Object obj, VexFwkImageTranslator vexFwkImageTranslator, String str, String str2, VexFwkOcrResult vexFwkOcrResult, VexFwkOcrResultV2 vexFwkOcrResultV2) {
        this.d = obj;
        this.e = vexFwkImageTranslator;
        this.f = str;
        this.g = str2;
        this.f5150h = vexFwkOcrResult;
        this.f5151i = vexFwkOcrResultV2;
    }

    public final Object get() {
        return VexFwkImageTranslator.translateImpl$lambda$16(this.d, this.e, this.f, this.g, this.f5150h, this.f5151i);
    }
}
