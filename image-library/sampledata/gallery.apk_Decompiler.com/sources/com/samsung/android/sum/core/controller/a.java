package com.samsung.android.sum.core.controller;

import com.samsung.android.sum.core.message.Message;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaFilterController e;
    public final /* synthetic */ Message f;

    public /* synthetic */ a(MediaFilterController mediaFilterController, Message message, int i2) {
        this.d = i2;
        this.e = mediaFilterController;
        this.f = message;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return this.e.lambda$sendMessage$5(this.f);
            default:
                return this.e.lambda$onMessageReceived$11(this.f);
        }
    }
}
