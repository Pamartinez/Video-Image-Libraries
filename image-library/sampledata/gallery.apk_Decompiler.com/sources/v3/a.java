package v3;

import Ae.b;
import com.samsung.android.app.sdk.deepsky.textextraction.textclassification.TextClassificationHelper;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements b {
    public final /* synthetic */ TextClassificationHelper d;
    public final /* synthetic */ String e;
    public final /* synthetic */ String f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ boolean f1982h;

    public /* synthetic */ a(TextClassificationHelper textClassificationHelper, String str, String str2, String str3, boolean z) {
        this.d = textClassificationHelper;
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.f1982h = z;
    }

    public final Object invoke(Object obj) {
        return TextClassificationHelper.classifyTextWithTimeout$lambda$0(this.d, this.e, this.f, this.g, this.f1982h, (x) obj);
    }
}
