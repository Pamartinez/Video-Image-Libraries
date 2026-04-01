package com.samsung.android.sum.core.service;

import com.samsung.android.sum.core.message.Response;
import com.samsung.android.sum.core.message.ResponseHolder;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Response e;

    public /* synthetic */ c(Response response, int i2) {
        this.d = i2;
        this.e = response;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Response response = this.e;
        ResponseHolder responseHolder = (ResponseHolder) obj;
        switch (i2) {
            case 0:
                LocalServiceProxy.lambda$onWarn$4(response, responseHolder);
                return;
            default:
                RemoteServiceProxy.lambda$onWarn$4(response, responseHolder);
                return;
        }
    }
}
