package androidx.work.impl;

import Ae.b;
import androidx.work.ListenableWorker;
import kotlin.Metadata;
import kotlin.jvm.internal.k;
import me.x;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lme/x;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkerWrapper$runWorker$2 extends k implements b {
    final /* synthetic */ boolean $isTracingEnabled;
    final /* synthetic */ String $traceTag;
    final /* synthetic */ ListenableWorker $worker;
    final /* synthetic */ WorkerWrapper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WorkerWrapper$runWorker$2(ListenableWorker listenableWorker, boolean z, String str, WorkerWrapper workerWrapper) {
        super(1);
        this.$worker = listenableWorker;
        this.$isTracingEnabled = z;
        this.$traceTag = str;
        this.this$0 = workerWrapper;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return x.f4917a;
    }

    public final void invoke(Throwable th) {
        if (th instanceof WorkerStoppedException) {
            this.$worker.stop(((WorkerStoppedException) th).getReason());
        }
        if (this.$isTracingEnabled && this.$traceTag != null) {
            this.this$0.configuration.getTracer().endAsyncSection(this.$traceTag, this.this$0.getWorkSpec().hashCode());
        }
    }
}
