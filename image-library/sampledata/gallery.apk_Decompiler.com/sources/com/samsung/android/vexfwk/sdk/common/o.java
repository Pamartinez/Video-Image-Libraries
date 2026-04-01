package com.samsung.android.vexfwk.sdk.common;

import java.util.Collection;
import java.util.Iterator;
import qe.C1227c;
import se.C1271c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends C1271c {
    public VexFwkHelperConfigurationFuture d;
    public Collection e;
    public Iterator f;
    public /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ VexFwkHelperConfigurationFuture f4169h;

    /* renamed from: i  reason: collision with root package name */
    public int f4170i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public o(VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture, C1227c cVar) {
        super(cVar);
        this.f4169h = vexFwkHelperConfigurationFuture;
    }

    public final Object invokeSuspend(Object obj) {
        this.g = obj;
        this.f4170i |= Integer.MIN_VALUE;
        return this.f4169h.getAllSessions(this);
    }
}
