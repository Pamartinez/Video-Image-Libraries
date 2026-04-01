package com.samsung.android.vexfwk.sdk.common;

import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfiguration;
import java.util.Iterator;
import java.util.Map;
import qe.C1227c;
import se.C1271c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends C1271c {
    public Object d;
    public Iterator e;
    public Map f;
    public VexFwkHelperConfiguration.Session g;

    /* renamed from: h  reason: collision with root package name */
    public /* synthetic */ Object f4160h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ VexFwkHelperConfigurationFuture f4161i;

    /* renamed from: j  reason: collision with root package name */
    public int f4162j;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public k(VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture, C1227c cVar) {
        super(cVar);
        this.f4161i = vexFwkHelperConfigurationFuture;
    }

    public final Object invokeSuspend(Object obj) {
        this.f4160h = obj;
        this.f4162j |= Integer.MIN_VALUE;
        return this.f4161i.buildConfigMap(this);
    }
}
