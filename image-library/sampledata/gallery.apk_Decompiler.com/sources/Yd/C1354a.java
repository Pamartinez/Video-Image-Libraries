package yd;

import Ae.b;
import com.samsung.android.vexfwk.metadata.VexFwkMetadataNative;
import com.samsung.android.vexfwk.param.VexFwkObjectRemoverMode;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionConfigRequest;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkImageObjectRemover;
import com.samsung.android.vexfwk.sdk.objeraser.VexFwkObjRemover;

/* renamed from: yd.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C1354a implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ VexFwkObjectRemoverMode e;

    public /* synthetic */ C1354a(VexFwkObjectRemoverMode vexFwkObjectRemoverMode, int i2) {
        this.d = i2;
        this.e = vexFwkObjectRemoverMode;
    }

    public final Object invoke(Object obj) {
        int i2 = this.d;
        VexFwkObjectRemoverMode vexFwkObjectRemoverMode = this.e;
        switch (i2) {
            case 0:
                return VexFwkImageObjectRemover.configure$lambda$2(vexFwkObjectRemoverMode, (VexFwkImageObjectRemover) obj);
            case 1:
                return VexFwkImageObjectRemover.configure$lambda$2$lambda$1(vexFwkObjectRemoverMode, (VexFwkSessionConfigRequest.Builder) obj);
            case 2:
                return VexFwkImageObjectRemover.configure$lambda$2$lambda$1$lambda$0(vexFwkObjectRemoverMode, (VexFwkMetadataNative) obj);
            case 3:
                return VexFwkObjRemover.configure$lambda$2$lambda$1$lambda$0(vexFwkObjectRemoverMode, (VexFwkMetadataNative) obj);
            case 4:
                return VexFwkObjRemover.configure$lambda$2(vexFwkObjectRemoverMode, (VexFwkObjRemover) obj);
            default:
                return VexFwkObjRemover.configure$lambda$2$lambda$1(vexFwkObjectRemoverMode, (VexFwkSessionConfigRequest.Builder) obj);
        }
    }
}
