package com.samsung.scsp.media.nde;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ NDEApiContext e;

    public /* synthetic */ a(NDEApiContext nDEApiContext, int i2) {
        this.d = i2;
        this.e = nDEApiContext;
    }

    public final Object get() {
        int i2 = this.d;
        NDEApiContext nDEApiContext = this.e;
        switch (i2) {
            case 0:
                return NDEApiHelper.lambda$onPrepareExecuteUpload$0(nDEApiContext);
            default:
                return NDEApiHelper.lambda$onPrepareExecuteUpdate$1(nDEApiContext);
        }
    }
}
