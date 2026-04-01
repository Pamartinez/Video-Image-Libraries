package androidx.work.impl;

import Ae.f;
import Vf.A;
import Vf.C0886x;
import Vf.D;
import android.content.Context;
import androidx.work.Configuration;
import androidx.work.R$bool;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.greedy.GreedyScheduler;
import androidx.work.impl.constraints.trackers.BatteryNotLowTracker;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1195m;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0001\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2<\b\u0002\u0010\u0010\u001a6\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fj\u0002`\u000fH\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001aE\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0017\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0019\u0010\u001a*j\u0010\u001c\"2\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f22\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f¨\u0006\u001d"}, d2 = {"Landroid/content/Context;", "context", "Landroidx/work/Configuration;", "configuration", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "workTaskExecutor", "Landroidx/work/impl/WorkDatabase;", "workDatabase", "Landroidx/work/impl/constraints/trackers/Trackers;", "trackers", "Landroidx/work/impl/Processor;", "processor", "Lkotlin/Function6;", "", "Landroidx/work/impl/Scheduler;", "Landroidx/work/impl/SchedulersCreator;", "schedulersCreator", "Landroidx/work/impl/WorkManagerImpl;", "createWorkManager", "(Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/WorkDatabase;Landroidx/work/impl/constraints/trackers/Trackers;Landroidx/work/impl/Processor;LAe/f;)Landroidx/work/impl/WorkManagerImpl;", "WorkManagerImpl", "createSchedulers", "(Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/WorkDatabase;Landroidx/work/impl/constraints/trackers/Trackers;Landroidx/work/impl/Processor;)Ljava/util/List;", "taskExecutor", "LVf/A;", "createWorkManagerScope", "(Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)LVf/A;", "WorkManagerScope", "SchedulersCreator", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WorkManagerImplExtKt {
    /* access modifiers changed from: private */
    public static final List<Scheduler> createSchedulers(Context context, Configuration configuration, TaskExecutor taskExecutor, WorkDatabase workDatabase, Trackers trackers, Processor processor) {
        Scheduler createBestAvailableBackgroundScheduler = Schedulers.createBestAvailableBackgroundScheduler(context, workDatabase, configuration);
        j.d(createBestAvailableBackgroundScheduler, "createBestAvailableBackg…kDatabase, configuration)");
        return C1195m.q0(createBestAvailableBackgroundScheduler, new GreedyScheduler(context, configuration, trackers, processor, new WorkLauncherImpl(processor, taskExecutor), taskExecutor));
    }

    public static final WorkManagerImpl createWorkManager(Context context, Configuration configuration) {
        j.e(context, "context");
        j.e(configuration, "configuration");
        return createWorkManager$default(context, configuration, (TaskExecutor) null, (WorkDatabase) null, (Trackers) null, (Processor) null, (f) null, 124, (Object) null);
    }

    public static /* synthetic */ WorkManagerImpl createWorkManager$default(Context context, Configuration configuration, TaskExecutor taskExecutor, WorkDatabase workDatabase, Trackers trackers, Processor processor, f fVar, int i2, Object obj) {
        Trackers trackers2;
        Processor processor2;
        WorkManagerImplExtKt$WorkManagerImpl$1 workManagerImplExtKt$WorkManagerImpl$1;
        if ((i2 & 4) != 0) {
            taskExecutor = new WorkManagerTaskExecutor(configuration.getTaskExecutor());
        }
        TaskExecutor taskExecutor2 = taskExecutor;
        if ((i2 & 8) != 0) {
            WorkDatabase.Companion companion = WorkDatabase.Companion;
            Context applicationContext = context.getApplicationContext();
            j.d(applicationContext, "context.applicationContext");
            SerialExecutor serialTaskExecutor = taskExecutor2.getSerialTaskExecutor();
            j.d(serialTaskExecutor, "workTaskExecutor.serialTaskExecutor");
            workDatabase = companion.create(applicationContext, serialTaskExecutor, configuration.getClock(), context.getResources().getBoolean(R$bool.workmanager_test_configuration));
        }
        if ((i2 & 16) != 0) {
            Context applicationContext2 = context.getApplicationContext();
            j.d(applicationContext2, "context.applicationContext");
            trackers2 = new Trackers(applicationContext2, taskExecutor2, (ConstraintTracker) null, (BatteryNotLowTracker) null, (ConstraintTracker) null, (ConstraintTracker) null, 60, (e) null);
        } else {
            trackers2 = trackers;
        }
        if ((i2 & 32) != 0) {
            processor2 = new Processor(context.getApplicationContext(), configuration, taskExecutor2, workDatabase);
        } else {
            processor2 = processor;
        }
        if ((i2 & 64) != 0) {
            workManagerImplExtKt$WorkManagerImpl$1 = WorkManagerImplExtKt$WorkManagerImpl$1.INSTANCE;
        } else {
            workManagerImplExtKt$WorkManagerImpl$1 = fVar;
        }
        return createWorkManager(context, configuration, taskExecutor2, workDatabase, trackers2, processor2, workManagerImplExtKt$WorkManagerImpl$1);
    }

    public static final A createWorkManagerScope(TaskExecutor taskExecutor) {
        j.e(taskExecutor, "taskExecutor");
        C0886x taskCoroutineDispatcher = taskExecutor.getTaskCoroutineDispatcher();
        j.d(taskCoroutineDispatcher, "taskExecutor.taskCoroutineDispatcher");
        return D.a(taskCoroutineDispatcher);
    }

    public static final WorkManagerImpl createWorkManager(Context context, Configuration configuration, TaskExecutor taskExecutor, WorkDatabase workDatabase, Trackers trackers, Processor processor, f fVar) {
        j.e(context, "context");
        j.e(configuration, "configuration");
        j.e(taskExecutor, "workTaskExecutor");
        j.e(workDatabase, "workDatabase");
        j.e(trackers, "trackers");
        j.e(processor, "processor");
        j.e(fVar, "schedulersCreator");
        Configuration configuration2 = configuration;
        Context context2 = context;
        f fVar2 = fVar;
        Processor processor2 = processor;
        Trackers trackers2 = trackers;
        WorkDatabase workDatabase2 = workDatabase;
        TaskExecutor taskExecutor2 = taskExecutor;
        Configuration configuration3 = configuration2;
        return new WorkManagerImpl(context2.getApplicationContext(), configuration3, taskExecutor2, workDatabase2, (List) fVar2.invoke(context2, configuration3, taskExecutor2, workDatabase2, trackers2, processor2), processor2, trackers2);
    }
}
