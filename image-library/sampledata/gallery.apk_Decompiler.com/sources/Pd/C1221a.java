package pd;

import Ae.b;
import android.view.Surface;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.frcrunner.VexFwkFrcRunner;

/* renamed from: pd.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C1221a implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Surface g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f5021h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ int f5022i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ float f5023j;

    public /* synthetic */ C1221a(int i2, int i7, Surface surface, int i8, int i10, float f5, int i11) {
        this.d = i11;
        this.e = i2;
        this.f = i7;
        this.g = surface;
        this.f5021h = i8;
        this.f5022i = i10;
        this.f5023j = f5;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return VexFwkFrcRunner.configure$lambda$3(this.e, this.f, this.g, this.f5021h, this.f5022i, this.f5023j, (VexFwkFrcRunner) obj);
            default:
                return VexFwkFrcRunner.configure$lambda$3$lambda$2(this.e, this.f, this.g, this.f5021h, this.f5022i, this.f5023j, (VexFwkSessionConfigRequest.Builder) obj);
        }
    }
}
