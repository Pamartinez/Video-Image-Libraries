package com.samsung.scsp.framework.core.api;

import com.samsung.scsp.error.FaultBarrier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements FaultBarrier.ThrowableRunnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ AbstractApiControl e;

    public /* synthetic */ d(AbstractApiControl abstractApiControl, int i2) {
        this.d = i2;
        this.e = abstractApiControl;
    }

    public final void run() {
        int i2 = this.d;
        AbstractApiControl abstractApiControl = this.e;
        switch (i2) {
            case 0:
                abstractApiControl.lambda$new$2();
                return;
            default:
                abstractApiControl.lambda$new$3();
                return;
        }
    }
}
