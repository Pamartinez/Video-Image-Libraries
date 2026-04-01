package com.samsung.scsp.common;

import com.samsung.scsp.error.FaultBarrier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements FaultBarrier.ThrowableRunnable, FaultBarrier.ThrowableSupplier {
    public final /* synthetic */ PushVo d;

    public /* synthetic */ b(PushVo pushVo) {
        this.d = pushVo;
    }

    public Object get() {
        return PushVoFactory.lambda$create$0(this.d);
    }

    public void run() {
        DeviceHealthCheckPushExecutorImpl.lambda$accept$1(this.d);
    }
}
