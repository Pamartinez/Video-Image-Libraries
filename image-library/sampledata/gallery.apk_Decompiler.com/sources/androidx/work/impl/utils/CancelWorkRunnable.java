package androidx.work.impl.utils;

import androidx.work.Operation;
import androidx.work.OperationKt;
import androidx.work.Tracer;
import androidx.work.WorkInfo$State;
import androidx.work.impl.Processor;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import java.util.ArrayList;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import n0.C0235b;
import ne.C1195m;
import ne.C1200r;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0007\u0010\b\u001a\u001f\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a\u001d\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001d\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001d\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/work/impl/WorkManagerImpl;", "workManagerImpl", "", "workSpecId", "Lme/x;", "cancel", "(Landroidx/work/impl/WorkManagerImpl;Ljava/lang/String;)V", "reschedulePendingWorkers", "(Landroidx/work/impl/WorkManagerImpl;)V", "Landroidx/work/impl/WorkDatabase;", "workDatabase", "iterativelyCancelWorkAndDependents", "(Landroidx/work/impl/WorkDatabase;Ljava/lang/String;)V", "Ljava/util/UUID;", "id", "Landroidx/work/Operation;", "forId", "(Ljava/util/UUID;Landroidx/work/impl/WorkManagerImpl;)Landroidx/work/Operation;", "tag", "forTag", "(Ljava/lang/String;Landroidx/work/impl/WorkManagerImpl;)Landroidx/work/Operation;", "name", "forNameInline", "(Ljava/lang/String;Landroidx/work/impl/WorkManagerImpl;)V", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CancelWorkRunnable {
    /* access modifiers changed from: private */
    public static final void cancel(WorkManagerImpl workManagerImpl, String str) {
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        j.d(workDatabase, "workManagerImpl.workDatabase");
        iterativelyCancelWorkAndDependents(workDatabase, str);
        Processor processor = workManagerImpl.getProcessor();
        j.d(processor, "workManagerImpl.processor");
        processor.stopAndCancelWork(str, 1);
        for (Scheduler cancel : workManagerImpl.getSchedulers()) {
            cancel.cancel(str);
        }
    }

    public static final Operation forId(UUID uuid, WorkManagerImpl workManagerImpl) {
        j.e(uuid, "id");
        j.e(workManagerImpl, "workManagerImpl");
        Tracer tracer = workManagerImpl.getConfiguration().getTracer();
        SerialExecutor serialTaskExecutor = workManagerImpl.getWorkTaskExecutor().getSerialTaskExecutor();
        j.d(serialTaskExecutor, "workManagerImpl.workTask…ecutor.serialTaskExecutor");
        return OperationKt.launchOperation(tracer, "CancelWorkById", serialTaskExecutor, new CancelWorkRunnable$forId$1(workManagerImpl, uuid));
    }

    public static final void forNameInline(String str, WorkManagerImpl workManagerImpl) {
        j.e(str, "name");
        j.e(workManagerImpl, "workManagerImpl");
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        j.d(workDatabase, "workManagerImpl.workDatabase");
        workDatabase.runInTransaction((Runnable) new C0235b(workDatabase, str, workManagerImpl, 22));
    }

    /* access modifiers changed from: private */
    public static final void forNameInline$lambda$0(WorkDatabase workDatabase, String str, WorkManagerImpl workManagerImpl) {
        for (String cancel : workDatabase.workSpecDao().getUnfinishedWorkWithName(str)) {
            cancel(workManagerImpl, cancel);
        }
    }

    public static final Operation forTag(String str, WorkManagerImpl workManagerImpl) {
        j.e(str, "tag");
        j.e(workManagerImpl, "workManagerImpl");
        Tracer tracer = workManagerImpl.getConfiguration().getTracer();
        String concat = "CancelWorkByTag_".concat(str);
        SerialExecutor serialTaskExecutor = workManagerImpl.getWorkTaskExecutor().getSerialTaskExecutor();
        j.d(serialTaskExecutor, "workManagerImpl.workTask…ecutor.serialTaskExecutor");
        return OperationKt.launchOperation(tracer, concat, serialTaskExecutor, new CancelWorkRunnable$forTag$1(workManagerImpl, str));
    }

    private static final void iterativelyCancelWorkAndDependents(WorkDatabase workDatabase, String str) {
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        DependencyDao dependencyDao = workDatabase.dependencyDao();
        ArrayList s0 = C1195m.s0(str);
        while (!s0.isEmpty()) {
            String str2 = (String) C1200r.C0(s0);
            WorkInfo$State state = workSpecDao.getState(str2);
            if (!(state == WorkInfo$State.SUCCEEDED || state == WorkInfo$State.FAILED)) {
                workSpecDao.setCancelledState(str2);
            }
            s0.addAll(dependencyDao.getDependentWorkIds(str2));
        }
    }

    /* access modifiers changed from: private */
    public static final void reschedulePendingWorkers(WorkManagerImpl workManagerImpl) {
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }
}
