package Vc;

import com.samsung.android.sdk.scs.ai.text.unit.UnitConverter;
import java.math.BigDecimal;
import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ UnitConverter f882a;
    public final /* synthetic */ BigDecimal b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f883c;
    public final /* synthetic */ String d;
    public final /* synthetic */ String e;

    public /* synthetic */ a(UnitConverter unitConverter, BigDecimal bigDecimal, String str, String str2, String str3) {
        this.f882a = unitConverter;
        this.b = bigDecimal;
        this.f883c = str;
        this.d = str2;
        this.e = str3;
    }

    public final Object call() {
        return this.f882a.lambda$getBundleFromInput$1(this.b, this.f883c, this.d, this.e);
    }
}
