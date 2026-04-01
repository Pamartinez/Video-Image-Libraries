package androidx.work.impl;

import androidx.work.BackoffPolicy;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Operation;
import androidx.work.OperationKt;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.Tracer;
import androidx.work.WorkInfo$State;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.utils.EnqueueUtilsKt;
import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import i.C0212a;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import n0.C0236c;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aK\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\n\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a#\u0010\u0016\u001a\u00020\u0015*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/work/impl/Processor;", "processor", "Landroidx/work/impl/WorkDatabase;", "workDatabase", "Landroidx/work/Configuration;", "configuration", "", "Landroidx/work/impl/Scheduler;", "schedulers", "Landroidx/work/impl/model/WorkSpec;", "newWorkSpec", "", "", "tags", "Landroidx/work/WorkManager$UpdateResult;", "updateWorkImpl", "(Landroidx/work/impl/Processor;Landroidx/work/impl/WorkDatabase;Landroidx/work/Configuration;Ljava/util/List;Landroidx/work/impl/model/WorkSpec;Ljava/util/Set;)Landroidx/work/WorkManager$UpdateResult;", "Landroidx/work/impl/WorkManagerImpl;", "name", "Landroidx/work/WorkRequest;", "workRequest", "Landroidx/work/Operation;", "enqueueUniquelyNamedPeriodic", "(Landroidx/work/impl/WorkManagerImpl;Ljava/lang/String;Landroidx/work/WorkRequest;)Landroidx/work/Operation;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WorkerUpdater {
    public static final Operation enqueueUniquelyNamedPeriodic(WorkManagerImpl workManagerImpl, String str, WorkRequest workRequest) {
        j.e(workManagerImpl, "<this>");
        j.e(str, "name");
        j.e(workRequest, "workRequest");
        Tracer tracer = workManagerImpl.getConfiguration().getTracer();
        String concat = "enqueueUniquePeriodic_".concat(str);
        SerialExecutor serialTaskExecutor = workManagerImpl.getWorkTaskExecutor().getSerialTaskExecutor();
        j.d(serialTaskExecutor, "workTaskExecutor.serialTaskExecutor");
        return OperationKt.launchOperation(tracer, concat, serialTaskExecutor, new WorkerUpdater$enqueueUniquelyNamedPeriodic$1(workManagerImpl, str, workRequest));
    }

    /* access modifiers changed from: private */
    public static final WorkManager.UpdateResult updateWorkImpl(Processor processor, WorkDatabase workDatabase, Configuration configuration, List<? extends Scheduler> list, WorkSpec workSpec, Set<String> set) {
        String str = workSpec.id;
        WorkSpec workSpec2 = workDatabase.workSpecDao().getWorkSpec(str);
        if (workSpec2 == null) {
            throw new IllegalArgumentException(C0212a.m("Worker with ", str, " doesn't exist"));
        } else if (workSpec2.state.isFinished()) {
            return WorkManager.UpdateResult.NOT_APPLIED;
        } else {
            if (!(workSpec2.isPeriodic() ^ workSpec.isPeriodic())) {
                boolean isEnqueued = processor.isEnqueued(str);
                if (!isEnqueued) {
                    for (Scheduler cancel : list) {
                        cancel.cancel(str);
                    }
                }
                WorkDatabase workDatabase2 = workDatabase;
                List<? extends Scheduler> list2 = list;
                workDatabase2.runInTransaction((Runnable) new C0236c(workDatabase2, workSpec2, workSpec, list2, str, set, isEnqueued));
                if (!isEnqueued) {
                    Schedulers.schedule(configuration, workDatabase2, list2);
                }
                if (isEnqueued) {
                    return WorkManager.UpdateResult.APPLIED_FOR_NEXT_RUN;
                }
                return WorkManager.UpdateResult.APPLIED_IMMEDIATELY;
            }
            WorkerUpdater$updateWorkImpl$type$1 workerUpdater$updateWorkImpl$type$1 = WorkerUpdater$updateWorkImpl$type$1.INSTANCE;
            StringBuilder sb2 = new StringBuilder("Can't update ");
            sb2.append((String) workerUpdater$updateWorkImpl$type$1.invoke(workSpec2));
            sb2.append(" Worker to ");
            throw new UnsupportedOperationException(C0212a.p(sb2, (String) workerUpdater$updateWorkImpl$type$1.invoke(workSpec), " Worker. Update operation must preserve worker's type."));
        }
    }

    /* access modifiers changed from: private */
    public static final void updateWorkImpl$lambda$2(WorkDatabase workDatabase, WorkSpec workSpec, WorkSpec workSpec2, List list, String str, Set set, boolean z) {
        WorkSpec workSpec3 = workSpec;
        String str2 = str;
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        WorkTagDao workTagDao = workDatabase.workTagDao();
        WorkInfo$State workInfo$State = workSpec3.state;
        int i2 = workSpec3.runAttemptCount;
        long j2 = workSpec3.lastEnqueueTime;
        long j3 = j2;
        int i7 = i2;
        WorkSpec workSpec4 = workSpec2;
        WorkSpec copy$default = WorkSpec.copy$default(workSpec4, (String) null, workInfo$State, (String) null, (String) null, (Data) null, (Data) null, 0, 0, 0, (Constraints) null, i7, (BackoffPolicy) null, 0, j3, 0, 0, false, (OutOfQuotaPolicy) null, workSpec3.getPeriodCount(), workSpec3.getGeneration() + 1, workSpec3.getNextScheduleTimeOverride(), workSpec3.getNextScheduleTimeOverrideGeneration(), 0, (String) null, 12835837, (Object) null);
        if (workSpec2.getNextScheduleTimeOverrideGeneration() == 1) {
            copy$default.setNextScheduleTimeOverride(workSpec2.getNextScheduleTimeOverride());
            copy$default.setNextScheduleTimeOverrideGeneration(copy$default.getNextScheduleTimeOverrideGeneration() + 1);
        }
        workSpecDao.updateWorkSpec(EnqueueUtilsKt.wrapWorkSpecIfNeeded(list, copy$default));
        workTagDao.deleteByWorkSpecId(str2);
        workTagDao.insertTags(str2, set);
        if (!z) {
            workSpecDao.markWorkSpecScheduled(str2, -1);
            workDatabase.workProgressDao().delete(str2);
        }
    }
}
