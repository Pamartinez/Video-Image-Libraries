package com.samsung.scsp.common;

import com.samsung.scsp.common.ContextFactory;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;

    public /* synthetic */ a(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return ContextFactory.ContextHolder.lambda$new$0();
            case 1:
                return ContextFactory.ContextHolder.lambda$new$1();
            default:
                return PushConsumer.lambda$new$0();
        }
    }
}
