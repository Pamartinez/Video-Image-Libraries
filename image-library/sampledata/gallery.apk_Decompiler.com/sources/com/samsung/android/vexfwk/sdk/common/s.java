package com.samsung.android.vexfwk.sdk.common;

import Ae.c;
import java.util.Iterator;
import qe.C1227c;
import se.C1271c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s extends C1271c {
    public VexFwkHelperProcessFuture d;
    public c e;
    public Iterator f;
    public /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ VexFwkHelperProcessFuture f4177h;

    /* renamed from: i  reason: collision with root package name */
    public int f4178i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public s(VexFwkHelperProcessFuture vexFwkHelperProcessFuture, C1227c cVar) {
        super(cVar);
        this.f4177h = vexFwkHelperProcessFuture;
    }

    public final Object invokeSuspend(Object obj) {
        this.g = obj;
        this.f4178i |= Integer.MIN_VALUE;
        return this.f4177h.execute(this);
    }
}
