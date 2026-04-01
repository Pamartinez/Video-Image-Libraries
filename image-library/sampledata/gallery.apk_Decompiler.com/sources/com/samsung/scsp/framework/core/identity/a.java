package com.samsung.scsp.framework.core.identity;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Object get() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return DeviceInfoManager.lambda$updateCache$0((DeviceInfo) obj);
            default:
                return ((PushInfoList) obj).toJsonArray().toString();
        }
    }
}
