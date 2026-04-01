package com.samsung.android.vexfwk.sdk.common;

import qe.C1227c;
import se.C1271c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends C1271c {
    public VexFwkHelperConfigurationFuture d;
    public /* synthetic */ Object e;
    public final /* synthetic */ VexFwkHelperConfigurationFuture f;
    public int g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public m(VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture, C1227c cVar) {
        super(cVar);
        this.f = vexFwkHelperConfigurationFuture;
    }

    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        this.g |= Integer.MIN_VALUE;
        return this.f.execute(this);
    }
}
