package androidx.work.impl;

import Ae.c;
import L2.a;
import Vf.A;
import Vf.C0881s;
import Vf.D;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.impl.WorkerWrapper;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "", "<anonymous>", "(LVf/A;)Z"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.work.impl.WorkerWrapper$launch$1", f = "WorkerWrapper.kt", l = {98}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkerWrapper$launch$1 extends i implements c {
    int label;
    final /* synthetic */ WorkerWrapper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WorkerWrapper$launch$1(WorkerWrapper workerWrapper, C1227c cVar) {
        super(2, cVar);
        this.this$0 = workerWrapper;
    }

    /* access modifiers changed from: private */
    public static final Boolean invokeSuspend$lambda$1(WorkerWrapper.Resolution resolution, WorkerWrapper workerWrapper) {
        boolean z;
        if (resolution instanceof WorkerWrapper.Resolution.Finished) {
            z = workerWrapper.onWorkFinished(((WorkerWrapper.Resolution.Finished) resolution).getResult());
        } else if (resolution instanceof WorkerWrapper.Resolution.Failed) {
            workerWrapper.setFailed(((WorkerWrapper.Resolution.Failed) resolution).getResult());
            z = false;
        } else if (resolution instanceof WorkerWrapper.Resolution.ResetWorkerStatus) {
            z = workerWrapper.resetWorkerStatus(((WorkerWrapper.Resolution.ResetWorkerStatus) resolution).getReason());
        } else {
            throw new RuntimeException();
        }
        return Boolean.valueOf(z);
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new WorkerWrapper$launch$1(this.this$0, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((WorkerWrapper$launch$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        WorkerWrapper.Resolution resolution;
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            C0881s access$getWorkerJob$p = this.this$0.workerJob;
            WorkerWrapper$launch$1$resolution$1 workerWrapper$launch$1$resolution$1 = new WorkerWrapper$launch$1$resolution$1(this.this$0, (C1227c) null);
            this.label = 1;
            obj = D.w(access$getWorkerJob$p, workerWrapper$launch$1$resolution$1, this);
            if (obj == aVar) {
                return aVar;
            }
        } else if (i2 == 1) {
            try {
                a.A(obj);
            } catch (WorkerStoppedException e) {
                resolution = new WorkerWrapper.Resolution.ResetWorkerStatus(e.getReason());
            } catch (CancellationException unused) {
                resolution = new WorkerWrapper.Resolution.Failed((ListenableWorker.Result) null, 1, (e) null);
            } catch (Throwable th) {
                Logger.get().error(WorkerWrapperKt.TAG, "Unexpected error in WorkerWrapper", th);
                resolution = new WorkerWrapper.Resolution.Failed((ListenableWorker.Result) null, 1, (e) null);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        resolution = (WorkerWrapper.Resolution) obj;
        Object runInTransaction = this.this$0.workDatabase.runInTransaction(new a(resolution, this.this$0));
        j.d(runInTransaction, "workDatabase.runInTransa…          }\n            )");
        return runInTransaction;
    }
}
