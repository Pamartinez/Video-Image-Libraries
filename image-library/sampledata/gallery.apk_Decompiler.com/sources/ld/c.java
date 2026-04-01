package ld;

import Ae.a;
import com.samsung.android.vexfwk.param.VexFwkOcrResultV2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ VexFwkOcrResultV2 e;

    public /* synthetic */ c(VexFwkOcrResultV2 vexFwkOcrResultV2, int i2) {
        this.d = i2;
        this.e = vexFwkOcrResultV2;
    }

    public final Object invoke() {
        int i2 = this.d;
        VexFwkOcrResultV2 vexFwkOcrResultV2 = this.e;
        switch (i2) {
            case 0:
                return VexFwkOcrResultV2.charInfoList_delegate$lambda$1(vexFwkOcrResultV2);
            case 1:
                return VexFwkOcrResultV2.wordInfoList_delegate$lambda$3(vexFwkOcrResultV2);
            default:
                return VexFwkOcrResultV2.lineInfoList_delegate$lambda$5(vexFwkOcrResultV2);
        }
    }
}
