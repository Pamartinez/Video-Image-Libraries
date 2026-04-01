package com.samsung.scsp.common;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.identity.PushInfoList;
import com.samsung.scsp.framework.core.identity.ScspCorePreferences;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements FaultBarrier.ThrowableSupplier {
    public final /* synthetic */ int d;

    public /* synthetic */ h(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return new PushInfoList(ScspCorePreferences.get().pushInfos.get()).getPushInfoList().get(0).getToken();
            default:
                return new PushInfoList(ScspCorePreferences.get().pushInfos.get()).getPushInfoList().get(0).getToken();
        }
    }
}
