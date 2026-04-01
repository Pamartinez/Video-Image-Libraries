package com.samsung.android.sum.core.service;

import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.ResponseHolder;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ResponseHolder e;

    public /* synthetic */ a(ResponseHolder responseHolder, int i2) {
        this.d = i2;
        this.e = responseHolder;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ResponseHolder responseHolder = this.e;
        Message message = (Message) obj;
        switch (i2) {
            case 0:
                LocalServiceProxy.lambda$request$1(responseHolder, message);
                return;
            default:
                RemoteServiceProxy.lambda$requestThenAwait$2(responseHolder, message);
                return;
        }
    }
}
