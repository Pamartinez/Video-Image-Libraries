package androidx.work.impl.background.systemjob;

import android.app.job.JobScheduler;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Landroidx/work/impl/background/systemjob/JobScheduler34;", "", "()V", "forNamespace", "Landroid/app/job/JobScheduler;", "jobScheduler", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class JobScheduler34 {
    public static final JobScheduler34 INSTANCE = new JobScheduler34();

    private JobScheduler34() {
    }

    public final JobScheduler forNamespace(JobScheduler jobScheduler) {
        j.e(jobScheduler, "jobScheduler");
        JobScheduler b = jobScheduler.forNamespace("androidx.work.systemjobscheduler");
        j.d(b, "jobScheduler.forNamespace(WORKMANAGER_NAMESPACE)");
        return b;
    }
}
