package com.samsung.android.vexfwk.sdk.common;

import com.samsung.android.vexfwk.sdk.common.VexFwkHelperProcess;
import java.util.Collection;
import java.util.Iterator;
import qe.C1227c;
import se.C1271c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q extends C1271c {
    public VexFwkHelperProcess d;
    public Collection e;
    public Iterator f;
    public VexFwkHelperProcess.Request g;

    /* renamed from: h  reason: collision with root package name */
    public Collection f4173h;

    /* renamed from: i  reason: collision with root package name */
    public /* synthetic */ Object f4174i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ VexFwkHelperProcessFuture f4175j;
    public int k;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public q(VexFwkHelperProcessFuture vexFwkHelperProcessFuture, C1227c cVar) {
        super(cVar);
        this.f4175j = vexFwkHelperProcessFuture;
    }

    public final Object invokeSuspend(Object obj) {
        this.f4174i = obj;
        this.k |= Integer.MIN_VALUE;
        return this.f4175j.buildProcess(this);
    }
}
