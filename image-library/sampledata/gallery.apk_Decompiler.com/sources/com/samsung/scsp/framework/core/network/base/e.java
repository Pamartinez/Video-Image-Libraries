package com.samsung.scsp.framework.core.network.base;

import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ NetworkImpl d;
    public final /* synthetic */ Predicate e;

    public /* synthetic */ e(NetworkImpl networkImpl, Predicate predicate) {
        this.d = networkImpl;
        this.e = predicate;
    }

    public final void run() {
        this.d.lambda$close$6(this.e);
    }
}
