package com.samsung.scsp.framework.core.api;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.api.AbstractApi;
import com.samsung.scsp.framework.core.network.HttpRequest;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements FaultBarrier.ThrowableSupplier {
    public final /* synthetic */ Class d;
    public final /* synthetic */ HttpRequest.Method e;
    public final /* synthetic */ String f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Class f4216h;

    public /* synthetic */ c(Class cls, HttpRequest.Method method, String str, String str2, Class cls2) {
        this.d = cls;
        this.e = method;
        this.f = str;
        this.g = str2;
        this.f4216h = cls2;
    }

    public final Object get() {
        return AbstractApi.JobFactory.lambda$create$1(this.d, this.e, this.f, this.g, this.f4216h);
    }
}
