package com.samsung.scsp.framework.core.api;

import com.samsung.scsp.error.FaultBarrier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements FaultBarrier.ThrowableSupplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ e(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Object get() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return SCHashMap.lambda$getAsInteger$1(obj);
            case 1:
                return SCHashMap.lambda$getAsLong$0(obj);
            default:
                return SCHashMap.lambda$getAsBoolean$2(obj);
        }
    }
}
