package androidx.work.impl.background.systemjob;

import Ae.b;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.WorkDatabase;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u001a'\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0007\u0010\b\"\u0014\u0010\t\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n\"\u0018\u0010\u000e\u001a\u00020\u000b*\u00020\u00008AX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u001d\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f*\u00020\u000b8G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Landroid/content/Context;", "context", "Landroidx/work/impl/WorkDatabase;", "workDatabase", "Landroidx/work/Configuration;", "configuration", "", "createErrorMessage", "(Landroid/content/Context;Landroidx/work/impl/WorkDatabase;Landroidx/work/Configuration;)Ljava/lang/String;", "TAG", "Ljava/lang/String;", "Landroid/app/job/JobScheduler;", "getWmJobScheduler", "(Landroid/content/Context;)Landroid/app/job/JobScheduler;", "wmJobScheduler", "", "Landroid/app/job/JobInfo;", "getSafePendingJobs", "(Landroid/app/job/JobScheduler;)Ljava/util/List;", "safePendingJobs", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class JobSchedulerExtKt {
    private static final String TAG;

    static {
        String tagWithPrefix = Logger.tagWithPrefix("SystemJobScheduler");
        j.d(tagWithPrefix, "tagWithPrefix(\"SystemJobScheduler\")");
        TAG = tagWithPrefix;
    }

    public static final String createErrorMessage(Context context, WorkDatabase workDatabase, Configuration configuration) {
        int i2;
        int i7;
        String str;
        j.e(context, "context");
        j.e(workDatabase, "workDatabase");
        j.e(configuration, "configuration");
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 31) {
            i2 = 150;
        } else {
            i2 = 100;
        }
        int size = workDatabase.workSpecDao().getScheduledWork().size();
        String str2 = "<faulty JobScheduler failed to getPendingJobs>";
        if (i8 >= 34) {
            JobScheduler wmJobScheduler = getWmJobScheduler(context);
            List<JobInfo> safePendingJobs = getSafePendingJobs(wmJobScheduler);
            if (safePendingJobs != null) {
                List<JobInfo> pendingJobs = SystemJobScheduler.getPendingJobs(context, wmJobScheduler);
                int i10 = 0;
                if (pendingJobs != null) {
                    i7 = safePendingJobs.size() - pendingJobs.size();
                } else {
                    i7 = 0;
                }
                String str3 = null;
                if (i7 == 0) {
                    str = null;
                } else {
                    str = i7 + " of which are not owned by WorkManager";
                }
                Object systemService = context.getSystemService("jobscheduler");
                j.c(systemService, "null cannot be cast to non-null type android.app.job.JobScheduler");
                List<JobInfo> pendingJobs2 = SystemJobScheduler.getPendingJobs(context, (JobScheduler) systemService);
                if (pendingJobs2 != null) {
                    i10 = pendingJobs2.size();
                }
                if (i10 != 0) {
                    str3 = i10 + " from WorkManager in the default namespace";
                }
                str2 = C1194l.R0(C1192j.l0(new String[]{safePendingJobs.size() + " jobs in \"androidx.work.systemjobscheduler\" namespace", str, str3}), ",\n", (String) null, (String) null, (b) null, 62);
            }
        } else {
            List<JobInfo> pendingJobs3 = SystemJobScheduler.getPendingJobs(context, getWmJobScheduler(context));
            if (pendingJobs3 != null) {
                str2 = pendingJobs3.size() + " jobs from WorkManager";
            }
        }
        return "JobScheduler " + i2 + " job limit exceeded.\nIn JobScheduler there are " + str2 + ".\nThere are " + size + " jobs tracked by WorkManager's database;\nthe Configuration limit is " + configuration.getMaxSchedulerLimit() + '.';
    }

    public static final List<JobInfo> getSafePendingJobs(JobScheduler jobScheduler) {
        j.e(jobScheduler, "<this>");
        try {
            return JobScheduler21.INSTANCE.getAllPendingJobs(jobScheduler);
        } catch (Throwable th) {
            Logger.get().error(TAG, "getAllPendingJobs() is not reliable on this device.", th);
            return null;
        }
    }

    public static final JobScheduler getWmJobScheduler(Context context) {
        j.e(context, "<this>");
        Object systemService = context.getSystemService("jobscheduler");
        j.c(systemService, "null cannot be cast to non-null type android.app.job.JobScheduler");
        JobScheduler jobScheduler = (JobScheduler) systemService;
        if (Build.VERSION.SDK_INT >= 34) {
            return JobScheduler34.INSTANCE.forNamespace(jobScheduler);
        }
        return jobScheduler;
    }
}
