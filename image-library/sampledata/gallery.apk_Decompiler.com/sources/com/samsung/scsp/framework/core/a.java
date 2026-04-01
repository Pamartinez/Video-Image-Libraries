package com.samsung.scsp.framework.core;

import com.samsung.scsp.common.Holder;
import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.network.Network;
import com.samsung.scsp.framework.core.network.NetworkFunction;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements FaultBarrier.ThrowableRunnable {
    public final /* synthetic */ Holder d;
    public final /* synthetic */ NetworkFunction e;
    public final /* synthetic */ Predicate f;

    public /* synthetic */ a(Holder holder, NetworkFunction networkFunction, Predicate predicate) {
        this.d = holder;
        this.e = networkFunction;
        this.f = predicate;
    }

    public final void run() {
        this.d.hold((Network) this.e.apply(this.f));
    }
}
