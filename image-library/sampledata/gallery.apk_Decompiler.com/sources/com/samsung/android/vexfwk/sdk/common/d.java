package com.samsung.android.vexfwk.sdk.common;

import com.samsung.android.vexfwk.session.VexFwkUsecase;
import qe.C1227c;
import se.C1271c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends C1271c {
    public int d;
    public /* synthetic */ Object e;
    public final /* synthetic */ VexFwkHelperBase f;
    public int g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(VexFwkHelperBase vexFwkHelperBase, C1227c cVar) {
        super(cVar);
        this.f = vexFwkHelperBase;
    }

    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        this.g |= Integer.MIN_VALUE;
        return this.f.getSurface((VexFwkUsecase) null, 0, this);
    }
}
