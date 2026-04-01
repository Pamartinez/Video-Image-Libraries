package com.samsung.android.vexfwk.sdk.common;

import java.util.Collection;
import qe.C1227c;
import se.C1271c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends C1271c {
    public Object d;
    public Object e;
    public Object f;
    public Collection g;

    /* renamed from: h  reason: collision with root package name */
    public Object f4163h;

    /* renamed from: i  reason: collision with root package name */
    public Object f4164i;

    /* renamed from: j  reason: collision with root package name */
    public Object f4165j;
    public Collection k;
    public /* synthetic */ Object l;
    public final /* synthetic */ VexFwkHelperConfigurationFuture m;
    public int n;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public l(VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture, C1227c cVar) {
        super(cVar);
        this.m = vexFwkHelperConfigurationFuture;
    }

    public final Object invokeSuspend(Object obj) {
        this.l = obj;
        this.n |= Integer.MIN_VALUE;
        return this.m.createSessionConfig((h) null, this);
    }
}
