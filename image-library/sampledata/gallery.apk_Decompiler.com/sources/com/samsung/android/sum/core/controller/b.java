package com.samsung.android.sum.core.controller;

import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.Response;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                Response.of((Message) ((Supplier) obj).get()).post();
                return;
            default:
                MediaFilterController.lambda$sendMessage$6((Message) obj);
                return;
        }
    }
}
