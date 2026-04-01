package com.samsung.scsp.framework.core.network.base;

import com.samsung.scsp.error.FaultBarrier;
import com.samsung.scsp.framework.core.network.HttpRequest;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements FaultBarrier.ThrowableRunnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ HttpRequest e;

    public /* synthetic */ b(HttpRequest httpRequest, int i2) {
        this.d = i2;
        this.e = httpRequest;
    }

    public final void run() {
        int i2 = this.d;
        HttpRequest httpRequest = this.e;
        switch (i2) {
            case 0:
                httpRequest.begin();
                return;
            case 1:
                httpRequest.end();
                return;
            default:
                httpRequest.error(60000004);
                return;
        }
    }
}
