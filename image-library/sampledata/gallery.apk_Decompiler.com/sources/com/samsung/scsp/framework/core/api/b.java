package com.samsung.scsp.framework.core.api;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.api.AbstractApi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements FaultBarrier.ThrowableSupplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Class e;

    public /* synthetic */ b(Class cls, int i2) {
        this.d = i2;
        this.e = cls;
    }

    public final Object get() {
        int i2 = this.d;
        Class cls = this.e;
        switch (i2) {
            case 0:
                return AbstractApi.JobFactory.lambda$create$0(cls);
            default:
                return cls.newInstance();
        }
    }
}
