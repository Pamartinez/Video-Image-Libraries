package androidx.work.impl;

import Ae.a;
import androidx.work.ExistingWorkPolicy;
import androidx.work.WorkRequest;
import androidx.work.impl.utils.EnqueueRunnable;
import kotlin.Metadata;
import kotlin.jvm.internal.k;
import o1.C0246a;

@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lme/x;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkerUpdater$enqueueUniquelyNamedPeriodic$1$enqueueNew$1 extends k implements a {
    final /* synthetic */ String $name;
    final /* synthetic */ WorkManagerImpl $this_enqueueUniquelyNamedPeriodic;
    final /* synthetic */ WorkRequest $workRequest;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WorkerUpdater$enqueueUniquelyNamedPeriodic$1$enqueueNew$1(WorkRequest workRequest, WorkManagerImpl workManagerImpl, String str) {
        super(0);
        this.$workRequest = workRequest;
        this.$this_enqueueUniquelyNamedPeriodic = workManagerImpl;
        this.$name = str;
    }

    public final void invoke() {
        EnqueueRunnable.enqueue(new WorkContinuationImpl(this.$this_enqueueUniquelyNamedPeriodic, this.$name, ExistingWorkPolicy.KEEP, C0246a.e0(this.$workRequest)));
    }
}
