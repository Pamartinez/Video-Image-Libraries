package androidx.work.impl;

import kotlin.Metadata;
import qe.C1227c;
import se.C1271c;
import se.C1273e;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@C1273e(c = "androidx.work.impl.WorkerWrapper", f = "WorkerWrapper.kt", l = {299}, m = "runWorker")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkerWrapper$runWorker$1 extends C1271c {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WorkerWrapper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WorkerWrapper$runWorker$1(WorkerWrapper workerWrapper, C1227c cVar) {
        super(cVar);
        this.this$0 = workerWrapper;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.runWorker(this);
    }
}
