package com.samsung.scsp.framework.core.network.base;

import com.samsung.scsp.framework.core.network.visitor.PayloadWriterVisitor;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Supplier {
    public final /* synthetic */ PayloadWriterVisitor.Payload d;

    public /* synthetic */ h(PayloadWriterVisitor.Payload payload) {
        this.d = payload;
    }

    public final Object get() {
        return PayloadWriterVisitorImpl.lambda$visit$0(this.d);
    }
}
