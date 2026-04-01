package com.samsung.android.sum.core.controller;

import com.samsung.android.sum.core.message.Response;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Supplier {
    public final /* synthetic */ int d;

    public /* synthetic */ i(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return CompletableFuture.completedFuture(Response.of(-5));
            default:
                return CompletableFuture.completedFuture(Response.of(-5));
        }
    }
}
