package androidx.work.impl.utils;

import Ae.c;
import Vf.A;
import android.content.Context;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableWorker;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0000H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"LVf/A;", "Ljava/lang/Void;", "kotlin.jvm.PlatformType", "<anonymous>", "(LVf/A;)Ljava/lang/Void;"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.work.impl.utils.WorkForegroundKt$workForeground$2", f = "WorkForeground.kt", l = {42, 50}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkForegroundKt$workForeground$2 extends i implements c {
    final /* synthetic */ Context $context;
    final /* synthetic */ ForegroundUpdater $foregroundUpdater;
    final /* synthetic */ WorkSpec $spec;
    final /* synthetic */ ListenableWorker $worker;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WorkForegroundKt$workForeground$2(ListenableWorker listenableWorker, WorkSpec workSpec, ForegroundUpdater foregroundUpdater, Context context, C1227c cVar) {
        super(2, cVar);
        this.$worker = listenableWorker;
        this.$spec = workSpec;
        this.$foregroundUpdater = foregroundUpdater;
        this.$context = context;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new WorkForegroundKt$workForeground$2(this.$worker, this.$spec, this.$foregroundUpdater, this.$context, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((WorkForegroundKt$workForeground$2) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0033, code lost:
        if (r8 == r0) goto L_0x0072;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            re.a r0 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x001c
            if (r1 == r3) goto L_0x0018
            if (r1 != r2) goto L_0x0010
            L2.a.A(r8)
            return r8
        L_0x0010:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0018:
            L2.a.A(r8)
            goto L_0x0036
        L_0x001c:
            L2.a.A(r8)
            androidx.work.ListenableWorker r8 = r7.$worker
            com.google.common.util.concurrent.ListenableFuture r8 = r8.getForegroundInfoAsync()
            java.lang.String r1 = "worker.getForegroundInfoAsync()"
            kotlin.jvm.internal.j.d(r8, r1)
            androidx.work.ListenableWorker r1 = r7.$worker
            r7.label = r3
            java.lang.Object r8 = androidx.work.impl.WorkerWrapperKt.awaitWithin(r8, r1, r7)
            if (r8 != r0) goto L_0x0036
            goto L_0x0072
        L_0x0036:
            androidx.work.ForegroundInfo r8 = (androidx.work.ForegroundInfo) r8
            if (r8 == 0) goto L_0x0074
            java.lang.String r1 = androidx.work.impl.utils.WorkForegroundKt.TAG
            androidx.work.impl.model.WorkSpec r3 = r7.$spec
            androidx.work.Logger r4 = androidx.work.Logger.get()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Updating notification for "
            r5.<init>(r6)
            java.lang.String r3 = r3.workerClassName
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r4.debug(r1, r3)
            androidx.work.ForegroundUpdater r1 = r7.$foregroundUpdater
            android.content.Context r3 = r7.$context
            androidx.work.ListenableWorker r4 = r7.$worker
            java.util.UUID r4 = r4.getId()
            com.google.common.util.concurrent.ListenableFuture r8 = r1.setForegroundAsync(r3, r4, r8)
            java.lang.String r1 = "foregroundUpdater.setFor…orker.id, foregroundInfo)"
            kotlin.jvm.internal.j.d(r8, r1)
            r7.label = r2
            java.lang.Object r7 = androidx.concurrent.futures.ListenableFutureKt.await(r8, r7)
            if (r7 != r0) goto L_0x0073
        L_0x0072:
            return r0
        L_0x0073:
            return r7
        L_0x0074:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Worker was marked important ("
            r8.<init>(r0)
            androidx.work.impl.model.WorkSpec r7 = r7.$spec
            java.lang.String r7 = r7.workerClassName
            java.lang.String r0 = ") but did not provide ForegroundInfo"
            java.lang.String r7 = i.C0212a.p(r8, r7, r0)
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            r8.<init>(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.WorkForegroundKt$workForeground$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
