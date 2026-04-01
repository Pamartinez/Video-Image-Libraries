package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.core.util.Consumer;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoKt;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.IdGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SystemJobScheduler implements Scheduler {
    private static final String TAG = Logger.tagWithPrefix("SystemJobScheduler");
    private final Configuration mConfiguration;
    private final Context mContext;
    private final JobScheduler mJobScheduler;
    private final SystemJobInfoConverter mSystemJobInfoConverter;
    private final WorkDatabase mWorkDatabase;

    public SystemJobScheduler(Context context, WorkDatabase workDatabase, Configuration configuration) {
        this(context, workDatabase, configuration, JobSchedulerExtKt.getWmJobScheduler(context), new SystemJobInfoConverter(context, configuration.getClock(), configuration.isMarkingJobsAsImportantWhileForeground()));
    }

    public static void cancelAllInAllNamespaces(Context context) {
        if (Build.VERSION.SDK_INT >= 34) {
            JobSchedulerExtKt.getWmJobScheduler(context).cancelAll();
        }
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        List<JobInfo> pendingJobs = getPendingJobs(context, jobScheduler);
        if (pendingJobs != null && !pendingJobs.isEmpty()) {
            for (JobInfo id : pendingJobs) {
                cancelJobById(jobScheduler, id.getId());
            }
        }
    }

    private static void cancelJobById(JobScheduler jobScheduler, int i2) {
        try {
            jobScheduler.cancel(i2);
        } catch (Throwable th) {
            Logger.get().error(TAG, String.format(Locale.getDefault(), "Exception while trying to cancel job (%d)", new Object[]{Integer.valueOf(i2)}), th);
        }
    }

    private static List<Integer> getPendingJobIds(Context context, JobScheduler jobScheduler, String str) {
        List<JobInfo> pendingJobs = getPendingJobs(context, jobScheduler);
        if (pendingJobs == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(2);
        for (JobInfo next : pendingJobs) {
            WorkGenerationalId workGenerationalIdFromJobInfo = getWorkGenerationalIdFromJobInfo(next);
            if (workGenerationalIdFromJobInfo != null && str.equals(workGenerationalIdFromJobInfo.getWorkSpecId())) {
                arrayList.add(Integer.valueOf(next.getId()));
            }
        }
        return arrayList;
    }

    public static List<JobInfo> getPendingJobs(Context context, JobScheduler jobScheduler) {
        List<JobInfo> safePendingJobs = JobSchedulerExtKt.getSafePendingJobs(jobScheduler);
        if (safePendingJobs == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(safePendingJobs.size());
        ComponentName componentName = new ComponentName(context, SystemJobService.class);
        for (JobInfo next : safePendingJobs) {
            if (componentName.equals(next.getService())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private static WorkGenerationalId getWorkGenerationalIdFromJobInfo(JobInfo jobInfo) {
        PersistableBundle extras = jobInfo.getExtras();
        if (extras == null) {
            return null;
        }
        try {
            if (!extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return null;
            }
            return new WorkGenerationalId(extras.getString("EXTRA_WORK_SPEC_ID"), extras.getInt("EXTRA_WORK_SPEC_GENERATION", 0));
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static boolean reconcileJobs(Context context, WorkDatabase workDatabase) {
        int i2;
        JobScheduler wmJobScheduler = JobSchedulerExtKt.getWmJobScheduler(context);
        List<JobInfo> pendingJobs = getPendingJobs(context, wmJobScheduler);
        List<String> workSpecIds = workDatabase.systemIdInfoDao().getWorkSpecIds();
        boolean z = false;
        if (pendingJobs != null) {
            i2 = pendingJobs.size();
        } else {
            i2 = 0;
        }
        HashSet hashSet = new HashSet(i2);
        if (pendingJobs != null && !pendingJobs.isEmpty()) {
            for (JobInfo next : pendingJobs) {
                WorkGenerationalId workGenerationalIdFromJobInfo = getWorkGenerationalIdFromJobInfo(next);
                if (workGenerationalIdFromJobInfo != null) {
                    hashSet.add(workGenerationalIdFromJobInfo.getWorkSpecId());
                } else {
                    cancelJobById(wmJobScheduler, next.getId());
                }
            }
        }
        Iterator<String> it = workSpecIds.iterator();
        while (true) {
            if (it.hasNext()) {
                if (!hashSet.contains(it.next())) {
                    Logger.get().debug(TAG, "Reconciling jobs");
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (!z) {
            return z;
        }
        workDatabase.beginTransaction();
        try {
            WorkSpecDao workSpecDao = workDatabase.workSpecDao();
            for (String markWorkSpecScheduled : workSpecIds) {
                workSpecDao.markWorkSpecScheduled(markWorkSpecScheduled, -1);
            }
            workDatabase.setTransactionSuccessful();
            workDatabase.endTransaction();
            return z;
        } catch (Throwable th) {
            workDatabase.endTransaction();
            throw th;
        }
    }

    public void cancel(String str) {
        List<Integer> pendingJobIds = getPendingJobIds(this.mContext, this.mJobScheduler, str);
        if (pendingJobIds != null && !pendingJobIds.isEmpty()) {
            for (Integer intValue : pendingJobIds) {
                cancelJobById(this.mJobScheduler, intValue.intValue());
            }
            this.mWorkDatabase.systemIdInfoDao().removeSystemIdInfo(str);
        }
    }

    public boolean hasLimitedSchedulingSlots() {
        return true;
    }

    public void schedule(WorkSpec... workSpecArr) {
        int i2;
        IdGenerator idGenerator = new IdGenerator(this.mWorkDatabase);
        int length = workSpecArr.length;
        int i7 = 0;
        while (i7 < length) {
            WorkSpec workSpec = workSpecArr[i7];
            this.mWorkDatabase.beginTransaction();
            try {
                WorkSpec workSpec2 = this.mWorkDatabase.workSpecDao().getWorkSpec(workSpec.id);
                if (workSpec2 == null) {
                    Logger logger = Logger.get();
                    String str = TAG;
                    logger.warning(str, "Skipping scheduling " + workSpec.id + " because it's no longer in the DB");
                    this.mWorkDatabase.setTransactionSuccessful();
                } else if (workSpec2.state != WorkInfo$State.ENQUEUED) {
                    Logger logger2 = Logger.get();
                    String str2 = TAG;
                    logger2.warning(str2, "Skipping scheduling " + workSpec.id + " because it is no longer enqueued");
                    this.mWorkDatabase.setTransactionSuccessful();
                } else {
                    WorkGenerationalId generationalId = WorkSpecKt.generationalId(workSpec);
                    SystemIdInfo systemIdInfo = this.mWorkDatabase.systemIdInfoDao().getSystemIdInfo(generationalId);
                    if (systemIdInfo != null) {
                        i2 = systemIdInfo.systemId;
                    } else {
                        i2 = idGenerator.nextJobSchedulerIdWithRange(this.mConfiguration.getMinJobSchedulerId(), this.mConfiguration.getMaxJobSchedulerId());
                    }
                    if (systemIdInfo == null) {
                        this.mWorkDatabase.systemIdInfoDao().insertSystemIdInfo(SystemIdInfoKt.systemIdInfo(generationalId, i2));
                    }
                    scheduleInternal(workSpec, i2);
                    this.mWorkDatabase.setTransactionSuccessful();
                }
                i7++;
            } finally {
                this.mWorkDatabase.endTransaction();
            }
        }
    }

    public void scheduleInternal(WorkSpec workSpec, int i2) {
        JobInfo convert = this.mSystemJobInfoConverter.convert(workSpec, i2);
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Scheduling work ID " + workSpec.id + "Job ID " + i2);
        try {
            if (this.mJobScheduler.schedule(convert) == 0) {
                Logger logger2 = Logger.get();
                logger2.warning(str, "Unable to schedule work ID " + workSpec.id);
                if (workSpec.expedited && workSpec.outOfQuotaPolicy == OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST) {
                    workSpec.expedited = false;
                    String str2 = workSpec.id;
                    Logger.get().debug(str, "Scheduling a non-expedited job (work ID " + str2 + ")");
                    scheduleInternal(workSpec, i2);
                }
            }
        } catch (IllegalStateException e) {
            String createErrorMessage = JobSchedulerExtKt.createErrorMessage(this.mContext, this.mWorkDatabase, this.mConfiguration);
            Logger.get().error(TAG, createErrorMessage);
            IllegalStateException illegalStateException = new IllegalStateException(createErrorMessage, e);
            Consumer<Throwable> schedulingExceptionHandler = this.mConfiguration.getSchedulingExceptionHandler();
            if (schedulingExceptionHandler != null) {
                schedulingExceptionHandler.accept(illegalStateException);
                return;
            }
            throw illegalStateException;
        } catch (Throwable th) {
            Logger logger3 = Logger.get();
            String str3 = TAG;
            logger3.error(str3, "Unable to schedule " + workSpec, th);
        }
    }

    public SystemJobScheduler(Context context, WorkDatabase workDatabase, Configuration configuration, JobScheduler jobScheduler, SystemJobInfoConverter systemJobInfoConverter) {
        this.mContext = context;
        this.mJobScheduler = jobScheduler;
        this.mSystemJobInfoConverter = systemJobInfoConverter;
        this.mWorkDatabase = workDatabase;
        this.mConfiguration = configuration;
    }
}
