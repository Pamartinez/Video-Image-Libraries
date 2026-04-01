package androidx.work.impl.utils;

import A.a;
import androidx.work.BackoffPolicy;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import androidx.work.WorkRequest;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkSpec;
import c0.C0086a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1195m;
import ne.C1200r;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u001a'\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u0017\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0007¢\u0006\u0004\b\u000b\u0010\f\u001a%\u0010\u0010\u001a\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\n\u001a\u00020\tH\u0000¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/work/impl/WorkDatabase;", "workDatabase", "Landroidx/work/Configuration;", "configuration", "Landroidx/work/impl/WorkContinuationImpl;", "continuation", "Lme/x;", "checkContentUriTriggerWorkerLimits", "(Landroidx/work/impl/WorkDatabase;Landroidx/work/Configuration;Landroidx/work/impl/WorkContinuationImpl;)V", "Landroidx/work/impl/model/WorkSpec;", "workSpec", "tryDelegateRemoteListenableWorker", "(Landroidx/work/impl/model/WorkSpec;)Landroidx/work/impl/model/WorkSpec;", "", "Landroidx/work/impl/Scheduler;", "schedulers", "wrapWorkSpecIfNeeded", "(Ljava/util/List;Landroidx/work/impl/model/WorkSpec;)Landroidx/work/impl/model/WorkSpec;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class EnqueueUtilsKt {
    public static final void checkContentUriTriggerWorkerLimits(WorkDatabase workDatabase, Configuration configuration, WorkContinuationImpl workContinuationImpl) {
        int i2;
        j.e(workDatabase, "workDatabase");
        j.e(configuration, "configuration");
        j.e(workContinuationImpl, "continuation");
        ArrayList s0 = C1195m.s0(workContinuationImpl);
        int i7 = 0;
        while (!s0.isEmpty()) {
            WorkContinuationImpl workContinuationImpl2 = (WorkContinuationImpl) C1200r.C0(s0);
            List<? extends WorkRequest> work = workContinuationImpl2.getWork();
            j.d(work, "current.work");
            Iterable<WorkRequest> iterable = work;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                i2 = 0;
                for (WorkRequest workSpec : iterable) {
                    if (workSpec.getWorkSpec().constraints.hasContentUriTriggers() && (i2 = i2 + 1) < 0) {
                        C1195m.u0();
                        throw null;
                    }
                }
            } else {
                i2 = 0;
            }
            i7 += i2;
            List<WorkContinuationImpl> parents = workContinuationImpl2.getParents();
            if (parents != null) {
                s0.addAll(parents);
            }
        }
        if (i7 != 0) {
            int countNonFinishedContentUriTriggerWorkers = workDatabase.workSpecDao().countNonFinishedContentUriTriggerWorkers();
            int contentUriTriggerWorkersLimit = configuration.getContentUriTriggerWorkersLimit();
            if (countNonFinishedContentUriTriggerWorkers + i7 > contentUriTriggerWorkersLimit) {
                throw new IllegalArgumentException(C0086a.l(a.h(contentUriTriggerWorkersLimit, countNonFinishedContentUriTriggerWorkers, "Too many workers with contentUriTriggers are enqueued:\ncontentUriTrigger workers limit: ", ";\nalready enqueued count: ", ";\ncurrent enqueue operation count: "), i7, ".\nTo address this issue you can: \n1. enqueue less workers or batch some of workers with content uri triggers together;\n2. increase limit via Configuration.Builder.setContentUriTriggerWorkersLimit;\nPlease beware that workers with content uri triggers immediately occupy slots in JobScheduler so no updates to content uris are missed."));
            }
        }
    }

    public static final WorkSpec tryDelegateRemoteListenableWorker(WorkSpec workSpec) {
        WorkSpec workSpec2 = workSpec;
        j.e(workSpec2, "workSpec");
        Class<String> cls = String.class;
        boolean hasKeyWithValueOfType = workSpec2.input.hasKeyWithValueOfType("androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME", cls);
        boolean hasKeyWithValueOfType2 = workSpec2.input.hasKeyWithValueOfType("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_PACKAGE_NAME", cls);
        boolean hasKeyWithValueOfType3 = workSpec2.input.hasKeyWithValueOfType("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_CLASS_NAME", cls);
        if (hasKeyWithValueOfType || !hasKeyWithValueOfType2 || !hasKeyWithValueOfType3) {
            return workSpec;
        }
        return WorkSpec.copy$default(workSpec2, (String) null, (WorkInfo$State) null, "androidx.work.multiprocess.RemoteListenableDelegatingWorker", (String) null, new Data.Builder().putAll(workSpec2.input).putString("androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME", workSpec2.workerClassName).build(), (Data) null, 0, 0, 0, (Constraints) null, 0, (BackoffPolicy) null, 0, 0, 0, 0, false, (OutOfQuotaPolicy) null, 0, 0, 0, 0, 0, (String) null, 16777195, (Object) null);
    }

    public static final WorkSpec wrapWorkSpecIfNeeded(List<? extends Scheduler> list, WorkSpec workSpec) {
        j.e(list, "schedulers");
        j.e(workSpec, "workSpec");
        return tryDelegateRemoteListenableWorker(workSpec);
    }
}
