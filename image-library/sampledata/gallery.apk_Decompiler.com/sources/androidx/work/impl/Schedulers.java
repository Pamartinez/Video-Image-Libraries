package androidx.work.impl;

import android.content.Context;
import androidx.work.Clock;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.PackageManagerHelper;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import n0.C0234a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Schedulers {
    private static final String TAG = Logger.tagWithPrefix("Schedulers");

    public static Scheduler createBestAvailableBackgroundScheduler(Context context, WorkDatabase workDatabase, Configuration configuration) {
        SystemJobScheduler systemJobScheduler = new SystemJobScheduler(context, workDatabase, configuration);
        PackageManagerHelper.setComponentEnabled(context, SystemJobService.class, true);
        Logger.get().debug(TAG, "Created SystemJobScheduler and enabled SystemJobService");
        return systemJobScheduler;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$registerRescheduling$0(List list, WorkGenerationalId workGenerationalId, Configuration configuration, WorkDatabase workDatabase) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((Scheduler) it.next()).cancel(workGenerationalId.getWorkSpecId());
        }
        schedule(configuration, workDatabase, list);
    }

    private static void markScheduled(WorkSpecDao workSpecDao, Clock clock, List<WorkSpec> list) {
        if (list.size() > 0) {
            long currentTimeMillis = clock.currentTimeMillis();
            for (WorkSpec workSpec : list) {
                workSpecDao.markWorkSpecScheduled(workSpec.id, currentTimeMillis);
            }
        }
    }

    public static void registerRescheduling(List<Scheduler> list, Processor processor, Executor executor, WorkDatabase workDatabase, Configuration configuration) {
        processor.addExecutionListener(new C0234a(executor, list, configuration, workDatabase));
    }

    public static void schedule(Configuration configuration, WorkDatabase workDatabase, List<Scheduler> list) {
        if (list != null && list.size() != 0) {
            WorkSpecDao workSpecDao = workDatabase.workSpecDao();
            workDatabase.beginTransaction();
            try {
                List<WorkSpec> eligibleWorkForSchedulingWithContentUris = workSpecDao.getEligibleWorkForSchedulingWithContentUris();
                markScheduled(workSpecDao, configuration.getClock(), eligibleWorkForSchedulingWithContentUris);
                List<WorkSpec> eligibleWorkForScheduling = workSpecDao.getEligibleWorkForScheduling(configuration.getMaxSchedulerLimit());
                markScheduled(workSpecDao, configuration.getClock(), eligibleWorkForScheduling);
                if (eligibleWorkForSchedulingWithContentUris != null) {
                    eligibleWorkForScheduling.addAll(eligibleWorkForSchedulingWithContentUris);
                }
                List<WorkSpec> allEligibleWorkSpecsForScheduling = workSpecDao.getAllEligibleWorkSpecsForScheduling(200);
                workDatabase.setTransactionSuccessful();
                workDatabase.endTransaction();
                if (eligibleWorkForScheduling.size() > 0) {
                    WorkSpec[] workSpecArr = (WorkSpec[]) eligibleWorkForScheduling.toArray(new WorkSpec[eligibleWorkForScheduling.size()]);
                    for (Scheduler next : list) {
                        if (next.hasLimitedSchedulingSlots()) {
                            next.schedule(workSpecArr);
                        }
                    }
                }
                if (allEligibleWorkSpecsForScheduling.size() > 0) {
                    WorkSpec[] workSpecArr2 = (WorkSpec[]) allEligibleWorkSpecsForScheduling.toArray(new WorkSpec[allEligibleWorkSpecsForScheduling.size()]);
                    for (Scheduler next2 : list) {
                        if (!next2.hasLimitedSchedulingSlots()) {
                            next2.schedule(workSpecArr2);
                        }
                    }
                }
            } catch (Throwable th) {
                workDatabase.endTransaction();
                throw th;
            }
        }
    }
}
