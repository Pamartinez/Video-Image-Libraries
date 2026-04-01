package androidx.work.impl;

import Ae.c;
import Vf.A;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableWorker;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0000H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"LVf/A;", "Landroidx/work/ListenableWorker$Result;", "kotlin.jvm.PlatformType", "<anonymous>", "(LVf/A;)Landroidx/work/ListenableWorker$Result;"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.work.impl.WorkerWrapper$runWorker$result$1", f = "WorkerWrapper.kt", l = {300, 311}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkerWrapper$runWorker$result$1 extends i implements c {
    final /* synthetic */ ForegroundUpdater $foregroundUpdater;
    final /* synthetic */ ListenableWorker $worker;
    int label;
    final /* synthetic */ WorkerWrapper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WorkerWrapper$runWorker$result$1(WorkerWrapper workerWrapper, ListenableWorker listenableWorker, ForegroundUpdater foregroundUpdater, C1227c cVar) {
        super(2, cVar);
        this.this$0 = workerWrapper;
        this.$worker = listenableWorker;
        this.$foregroundUpdater = foregroundUpdater;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new WorkerWrapper$runWorker$result$1(this.this$0, this.$worker, this.$foregroundUpdater, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((WorkerWrapper$runWorker$result$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003d, code lost:
        if (androidx.work.impl.utils.WorkForegroundKt.workForeground(r4, r5, r6, r7, r8, r9) == r0) goto L_0x0077;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            re.a r0 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r10.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x001d
            if (r1 == r3) goto L_0x0018
            if (r1 != r2) goto L_0x0010
            L2.a.A(r11)
            return r11
        L_0x0010:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0018:
            L2.a.A(r11)
            r9 = r10
            goto L_0x0040
        L_0x001d:
            L2.a.A(r11)
            androidx.work.impl.WorkerWrapper r11 = r10.this$0
            android.content.Context r4 = r11.appContext
            androidx.work.impl.WorkerWrapper r11 = r10.this$0
            androidx.work.impl.model.WorkSpec r5 = r11.getWorkSpec()
            androidx.work.ListenableWorker r6 = r10.$worker
            androidx.work.ForegroundUpdater r7 = r10.$foregroundUpdater
            androidx.work.impl.WorkerWrapper r11 = r10.this$0
            androidx.work.impl.utils.taskexecutor.TaskExecutor r8 = r11.workTaskExecutor
            r10.label = r3
            r9 = r10
            java.lang.Object r10 = androidx.work.impl.utils.WorkForegroundKt.workForeground(r4, r5, r6, r7, r8, r9)
            if (r10 != r0) goto L_0x0040
            goto L_0x0077
        L_0x0040:
            java.lang.String r10 = androidx.work.impl.WorkerWrapperKt.TAG
            androidx.work.impl.WorkerWrapper r11 = r9.this$0
            androidx.work.Logger r1 = androidx.work.Logger.get()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Starting work for "
            r3.<init>(r4)
            androidx.work.impl.model.WorkSpec r11 = r11.getWorkSpec()
            java.lang.String r11 = r11.workerClassName
            r3.append(r11)
            java.lang.String r11 = r3.toString()
            r1.debug(r10, r11)
            androidx.work.ListenableWorker r10 = r9.$worker
            com.google.common.util.concurrent.ListenableFuture r10 = r10.startWork()
            java.lang.String r11 = "worker.startWork()"
            kotlin.jvm.internal.j.d(r10, r11)
            androidx.work.ListenableWorker r11 = r9.$worker
            r9.label = r2
            java.lang.Object r10 = androidx.work.impl.WorkerWrapperKt.awaitWithin(r10, r11, r9)
            if (r10 != r0) goto L_0x0078
        L_0x0077:
            return r0
        L_0x0078:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.WorkerWrapper$runWorker$result$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
