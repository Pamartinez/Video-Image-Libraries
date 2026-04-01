package com.samsung.scsp.framework.core.ers;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ DomainVo d;

    public /* synthetic */ a(DomainVo domainVo) {
        this.d = domainVo;
    }

    public final Object get() {
        return ErsImpl.lambda$execute$3(this.d);
    }
}
