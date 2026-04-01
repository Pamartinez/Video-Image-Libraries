package com.samsung.android.sum.core.channel;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ ReceiveChannelRouter e;

    public /* synthetic */ i(ReceiveChannelRouter receiveChannelRouter, int i2) {
        this.d = i2;
        this.e = receiveChannelRouter;
    }

    public final Object get() {
        int i2 = this.d;
        ReceiveChannelRouter receiveChannelRouter = this.e;
        switch (i2) {
            case 0:
                return receiveChannelRouter.receiveAny();
            default:
                return receiveChannelRouter.receiveAll();
        }
    }
}
