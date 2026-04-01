package androidx.work.impl;

import Ae.c;
import L2.a;
import Vf.A;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "<anonymous>", "(LVf/A;)Landroidx/work/impl/WorkerWrapper$Resolution;"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.work.impl.WorkerWrapper$launch$1$resolution$1", f = "WorkerWrapper.kt", l = {98}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkerWrapper$launch$1$resolution$1 extends i implements c {
    int label;
    final /* synthetic */ WorkerWrapper this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WorkerWrapper$launch$1$resolution$1(WorkerWrapper workerWrapper, C1227c cVar) {
        super(2, cVar);
        this.this$0 = workerWrapper;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new WorkerWrapper$launch$1$resolution$1(this.this$0, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((WorkerWrapper$launch$1$resolution$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            WorkerWrapper workerWrapper = this.this$0;
            this.label = 1;
            Object access$runWorker = workerWrapper.runWorker(this);
            if (access$runWorker == aVar) {
                return aVar;
            }
            return access$runWorker;
        } else if (i2 == 1) {
            a.A(obj);
            return obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
