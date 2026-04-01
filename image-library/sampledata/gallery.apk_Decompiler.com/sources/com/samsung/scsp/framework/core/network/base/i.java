package com.samsung.scsp.framework.core.network.base;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.network.base.SSLContextFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements FaultBarrier.ThrowableSupplier {
    public final Object get() {
        return SSLContextFactory.LazyHolder.lambda$static$0();
    }
}
