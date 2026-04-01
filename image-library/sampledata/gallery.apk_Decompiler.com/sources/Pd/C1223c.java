package pd;

import Ae.b;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkFrcStatus;
import com.samsung.android.vexfwk.sdk.frcrunner.VexFwkFrcRunner;

/* renamed from: pd.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C1223c implements b {
    public final /* synthetic */ VexFwkFrcStatus d;
    public final /* synthetic */ int e;
    public final /* synthetic */ float f;

    public /* synthetic */ C1223c(VexFwkFrcStatus vexFwkFrcStatus, int i2, float f5) {
        this.d = vexFwkFrcStatus;
        this.e = i2;
        this.f = f5;
    }

    public final Object invoke(Object obj) {
        return VexFwkFrcRunner.run$lambda$5(this.d, this.e, this.f, (VexFwkMetadataNative) obj);
    }
}
