package gd;

import com.samsung.android.sum.core.utils.FPSTracer;
import java.util.function.IntToLongFunction;

/* renamed from: gd.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0999a implements IntToLongFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FPSTracer f4379a;

    public /* synthetic */ C0999a(FPSTracer fPSTracer) {
        this.f4379a = fPSTracer;
    }

    public final long applyAsLong(int i2) {
        return this.f4379a.lambda$update$0(i2);
    }
}
