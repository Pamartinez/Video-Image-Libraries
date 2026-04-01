package com.samsung.android.vexfwk.sdk.common;

import com.samsung.android.vexfwk.session.VexFwkUsecase;
import fg.a;
import qe.C1227c;
import se.C1271c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends C1271c {
    public Object d;
    public Object e;
    public a f;
    public /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ VexFwkHelperBase f4148h;

    /* renamed from: i  reason: collision with root package name */
    public int f4149i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(VexFwkHelperBase vexFwkHelperBase, C1227c cVar) {
        super(cVar);
        this.f4148h = vexFwkHelperBase;
    }

    public final Object invokeSuspend(Object obj) {
        this.g = obj;
        this.f4149i |= Integer.MIN_VALUE;
        return this.f4148h.getSession((VexFwkUsecase) null, this);
    }
}
