package com.samsung.android.sum.core.service;

import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.message.ResponseHolder;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Response e;
    public final /* synthetic */ Exception f;

    public /* synthetic */ b(Response response, Exception exc, int i2) {
        this.d = i2;
        this.e = response;
        this.f = exc;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                LocalServiceProxy.lambda$onError$5(this.e, this.f, (ResponseHolder) obj);
                return;
            default:
                RemoteServiceProxy.lambda$onError$5(this.e, this.f, (ResponseHolder) obj);
                return;
        }
    }
}
