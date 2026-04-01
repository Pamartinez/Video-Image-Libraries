package com.samsung.scsp.common;

import com.samsung.scsp.common.JournalFactory;
import com.samsung.scsp.error.FaultBarrier;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements FaultBarrier.ThrowableRunnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ e(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((JournalFactory.JournalBase) this.e).lambda$apply$0((Consumer) this.f);
                return;
            default:
                ((PushConsumer) this.e).lambda$executeSyncPushExecutor$6((PushVo) this.f);
                return;
        }
    }
}
