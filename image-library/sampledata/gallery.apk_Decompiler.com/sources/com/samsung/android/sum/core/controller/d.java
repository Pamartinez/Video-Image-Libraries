package com.samsung.android.sum.core.controller;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.service.ServiceProxy;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4070a;
    public final /* synthetic */ Object b;

    public /* synthetic */ d(int i2, Object obj) {
        this.f4070a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4070a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((MediaFilterController) obj2).lambda$request$2((MediaBuffer) obj);
            case 1:
                return ((MediaFilterController) obj2).lambda$run$1((MediaBuffer) obj);
            default:
                return ((ServiceProxy) obj).request((Request) obj2);
        }
    }
}
