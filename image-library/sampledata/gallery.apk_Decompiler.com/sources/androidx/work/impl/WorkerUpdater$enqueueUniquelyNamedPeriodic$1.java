package androidx.work.impl;

import Ae.a;
import androidx.work.BackoffPolicy;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import ne.C1194l;

@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lme/x;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkerUpdater$enqueueUniquelyNamedPeriodic$1 extends k implements a {
    final /* synthetic */ String $name;
    final /* synthetic */ WorkManagerImpl $this_enqueueUniquelyNamedPeriodic;
    final /* synthetic */ WorkRequest $workRequest;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WorkerUpdater$enqueueUniquelyNamedPeriodic$1(WorkManagerImpl workManagerImpl, String str, WorkRequest workRequest) {
        super(0);
        this.$this_enqueueUniquelyNamedPeriodic = workManagerImpl;
        this.$name = str;
        this.$workRequest = workRequest;
    }

    public final void invoke() {
        WorkerUpdater$enqueueUniquelyNamedPeriodic$1$enqueueNew$1 workerUpdater$enqueueUniquelyNamedPeriodic$1$enqueueNew$1 = new WorkerUpdater$enqueueUniquelyNamedPeriodic$1$enqueueNew$1(this.$workRequest, this.$this_enqueueUniquelyNamedPeriodic, this.$name);
        WorkSpecDao workSpecDao = this.$this_enqueueUniquelyNamedPeriodic.getWorkDatabase().workSpecDao();
        List<WorkSpec.IdAndState> workSpecIdAndStatesForName = workSpecDao.getWorkSpecIdAndStatesForName(this.$name);
        if (workSpecIdAndStatesForName.size() <= 1) {
            WorkSpec.IdAndState idAndState = (WorkSpec.IdAndState) C1194l.N0(workSpecIdAndStatesForName);
            if (idAndState == null) {
                workerUpdater$enqueueUniquelyNamedPeriodic$1$enqueueNew$1.invoke();
                return;
            }
            WorkSpec workSpec = workSpecDao.getWorkSpec(idAndState.id);
            if (workSpec == null) {
                StringBuilder sb2 = new StringBuilder("WorkSpec with ");
                sb2.append(idAndState.id);
                sb2.append(", that matches a name \"");
                throw new IllegalStateException(C0212a.p(sb2, this.$name, "\", wasn't found"));
            } else if (!workSpec.isPeriodic()) {
                throw new UnsupportedOperationException("Can't update OneTimeWorker to Periodic Worker. Update operation must preserve worker's type.");
            } else if (idAndState.state == WorkInfo$State.CANCELLED) {
                workSpecDao.delete(idAndState.id);
                workerUpdater$enqueueUniquelyNamedPeriodic$1$enqueueNew$1.invoke();
            } else {
                WorkSpec copy$default = WorkSpec.copy$default(this.$workRequest.getWorkSpec(), idAndState.id, (WorkInfo$State) null, (String) null, (String) null, (Data) null, (Data) null, 0, 0, 0, (Constraints) null, 0, (BackoffPolicy) null, 0, 0, 0, 0, false, (OutOfQuotaPolicy) null, 0, 0, 0, 0, 0, (String) null, 16777214, (Object) null);
                Processor processor = this.$this_enqueueUniquelyNamedPeriodic.getProcessor();
                j.d(processor, "processor");
                WorkDatabase workDatabase = this.$this_enqueueUniquelyNamedPeriodic.getWorkDatabase();
                j.d(workDatabase, "workDatabase");
                Configuration configuration = this.$this_enqueueUniquelyNamedPeriodic.getConfiguration();
                j.d(configuration, "configuration");
                List<Scheduler> schedulers = this.$this_enqueueUniquelyNamedPeriodic.getSchedulers();
                j.d(schedulers, "schedulers");
                WorkManager.UpdateResult unused = WorkerUpdater.updateWorkImpl(processor, workDatabase, configuration, schedulers, copy$default, this.$workRequest.getTags());
            }
        } else {
            throw new UnsupportedOperationException("Can't apply UPDATE policy to the chains of work.");
        }
    }
}
