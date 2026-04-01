package com.samsung.android.gallery.module.idleworker;

import A4.S;
import J3.a;
import N2.j;
import W4.b;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import c0.C0086a;
import com.samsung.android.gallery.module.abstraction.IdleWorkerJob;
import com.samsung.android.gallery.module.album.SaveAlbumCoverTask;
import com.samsung.android.gallery.module.idleworker.jobs.AlbumStatusLogJob;
import com.samsung.android.gallery.module.idleworker.jobs.CacheTrimJob;
import com.samsung.android.gallery.module.idleworker.jobs.CleanUpMdeJob;
import com.samsung.android.gallery.module.idleworker.jobs.CountLogJob;
import com.samsung.android.gallery.module.idleworker.jobs.DeleteCacheJob;
import com.samsung.android.gallery.module.idleworker.jobs.DeleteExpiredTempFileJob;
import com.samsung.android.gallery.module.idleworker.jobs.DumpHiddenFolderJob;
import com.samsung.android.gallery.module.idleworker.jobs.GroupShotUpdateJob;
import com.samsung.android.gallery.module.idleworker.jobs.StatusLogJob;
import com.samsung.android.gallery.module.trash.helper.TrashEmptyAbnormalTask;
import com.samsung.android.gallery.module.trash.helper.TrashEmptyExpiredTask;
import com.samsung.android.gallery.module.trash.helper.TrashMpRecoverTask;
import com.samsung.android.gallery.module.trash.helper.TrashRollbackTask;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.ServiceManager;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.functional.g;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import n5.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IdleWorker extends JobService {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IdleChargedJobs {
        static final ArrayList<Supplier<IdleWorkerJob>> constructors = new ArrayList<Supplier<IdleWorkerJob>>() {
            {
                add(new a(0));
                add(new a(1));
                add(new a(2));
                if (!PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
                    add(new a(3));
                }
                SdkConfig.GED ged = SdkConfig.GED.V;
                if (SdkConfig.atLeast(ged) && !GalleryPreference.getInstanceDebug().loadBoolean("TrashMpRecoverTask_job_finished", false)) {
                    add(new a(4));
                }
                if (SdkConfig.atLeast(ged)) {
                    add(new a(5));
                }
            }

            /* access modifiers changed from: private */
            public static /* synthetic */ IdleWorkerJob lambda$new$0() {
                return new DumpHiddenFolderJob(4, IdleWorkerJob.Type.CHARGED_IDLE);
            }

            /* access modifiers changed from: private */
            public static /* synthetic */ IdleWorkerJob lambda$new$1() {
                return new AlbumStatusLogJob(4, IdleWorkerJob.Type.CHARGED_IDLE);
            }

            /* access modifiers changed from: private */
            public static /* synthetic */ IdleWorkerJob lambda$new$2() {
                return new GroupShotUpdateJob(4, IdleWorkerJob.Type.CHARGED_IDLE);
            }

            /* access modifiers changed from: private */
            public static /* synthetic */ IdleWorkerJob lambda$new$3() {
                return new TrashEmptyAbnormalTask.TrashEmptyAbnormalJob(4, IdleWorkerJob.Type.CHARGED_IDLE);
            }

            /* access modifiers changed from: private */
            public static /* synthetic */ IdleWorkerJob lambda$new$4() {
                return new TrashMpRecoverTask.TrashMpRecoverJob(4, IdleWorkerJob.Type.CHARGED_IDLE);
            }

            /* access modifiers changed from: private */
            public static /* synthetic */ IdleWorkerJob lambda$new$5() {
                return new TrashRollbackTask.TrashRollbackJob(4, IdleWorkerJob.Type.CHARGED_IDLE);
            }
        };

        public static List<IdleWorkerJob> build() {
            return (List) constructors.stream().map(new e(11)).collect(Collectors.toList());
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IdleJobs {
        static final ArrayList<Supplier<IdleWorkerJob>> constructors = new ArrayList<Supplier<IdleWorkerJob>>() {
            {
                if (PreferenceFeatures.OneUi41.SUPPORT_PERMANENT_ALBUM_COVER && GalleryPreference.getInstance().loadBoolean(PreferenceName.SAVE_ALBUM_COVER, true)) {
                    add(new a(6));
                }
                if (!PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
                    add(new a(7));
                }
                add(new a(8));
                add(new a(9));
            }

            /* access modifiers changed from: private */
            public static /* synthetic */ IdleWorkerJob lambda$new$0() {
                return new SaveAlbumCoverTask.SaveAlbumCoverJob(0, IdleWorkerJob.Type.IDLE);
            }

            /* access modifiers changed from: private */
            public static /* synthetic */ IdleWorkerJob lambda$new$1() {
                return new TrashEmptyExpiredTask.TrashEmptyExpiredJob(0, IdleWorkerJob.Type.IDLE);
            }

            /* access modifiers changed from: private */
            public static /* synthetic */ IdleWorkerJob lambda$new$2() {
                return new DeleteExpiredTempFileJob(0, IdleWorkerJob.Type.IDLE);
            }

            /* access modifiers changed from: private */
            public static /* synthetic */ IdleWorkerJob lambda$new$3() {
                return new DeleteCacheJob(0, IdleWorkerJob.Type.IDLE);
            }
        };

        public static List<IdleWorkerJob> build() {
            return (List) constructors.stream().map(new e(11)).collect(Collectors.toList());
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class IdleWorkerThread {
        static final ThreadPool instance = ThreadPool.createPrivateInstance("idle-worker");
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class UniqueJobs {
        static final List<IdleWorkerJob> list = new ArrayList<IdleWorkerJob>() {
            {
                int i2 = 1000;
                if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
                    add(new CountLogJob(1000, IdleWorkerJob.Type.CHARGED_IDLE));
                    i2 = 1001;
                }
                IdleWorkerJob.Type type = IdleWorkerJob.Type.CHARGED_IDLE;
                add(new StatusLogJob(i2, type));
                add(new CleanUpMdeJob(i2 + 1, type));
                add(new CacheTrimJob(i2 + 2, type));
            }
        };

        public static boolean contains(int i2) {
            if (i2 < 1000 || i2 >= 1999) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$valueOf$1(int i2, IdleWorkerJob idleWorkerJob) {
            if (idleWorkerJob.getJobId() == i2) {
                return true;
            }
            return false;
        }

        public static IdleWorkerJob valueOf(int i2) {
            return list.stream().filter(new b(i2)).findFirst().orElse((Object) null);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: execJob */
    public void lambda$onStartJob$2(Context context, JobParameters jobParameters, IdleWorkerJob idleWorkerJob, AtomicInteger atomicInteger) {
        IdleWorkerThread.instance.submit(new a(this, idleWorkerJob, context, atomicInteger, jobParameters, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$execJob$3(IdleWorkerJob idleWorkerJob, Context context, AtomicInteger atomicInteger, JobParameters jobParameters, ThreadPool.JobContext jobContext) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Thread.currentThread().setName(idleWorkerJob.getClass().getSimpleName());
            idleWorkerJob.run(context);
            Log.d("IdleWorker", "run#" + idleWorkerJob + Logger.vt(currentTimeMillis));
        } catch (Error | Exception e) {
            Log.e("IdleWorker", "run#" + idleWorkerJob + " failed. e=" + e.getMessage());
            StringBuilder sb2 = new StringBuilder("IDLE WORK FAIL : ");
            sb2.append(idleWorkerJob);
            new InternalException(j.i(e, sb2)).post();
        }
        if (System.currentTimeMillis() - currentTimeMillis > 30000) {
            new InternalException("IDLE WORK SLOW V2 : " + idleWorkerJob).post(10000);
        }
        if (atomicInteger.decrementAndGet() == 0) {
            if (!idleWorkerJob.isChargingRequired() || !idleWorkerJob.wantsReschedule()) {
                z = false;
            } else {
                z = true;
            }
            jobFinished(jobParameters, z);
            Log.d("IdleWorker", "jobFinished#" + idleWorkerJob);
        }
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onStartJob$1(long j2, IdleWorkerJob idleWorkerJob) {
        return !idleWorkerJob.executable(j2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$startIdle$0(Context context, JobScheduler jobScheduler, ArrayList arrayList, IdleWorkerJob idleWorkerJob) {
        if (scheduleJob(context, jobScheduler, idleWorkerJob.getJobId(), idleWorkerJob.isChargingRequired())) {
            arrayList.add(idleWorkerJob.toString());
        }
    }

    private static boolean scheduleJob(Context context, JobScheduler jobScheduler, int i2, boolean z) {
        try {
            if (jobScheduler.schedule(new JobInfo.Builder(i2, new ComponentName(context, IdleWorker.class)).setRequiresDeviceIdle(true).setRequiresCharging(z).build()) != 0) {
                return true;
            }
            Log.e("IdleWorker", "schedule job failed : " + i2);
            return false;
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("schedule job failed e="), "IdleWorker");
            return false;
        }
    }

    public static void startIdle(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        ArrayList arrayList = new ArrayList();
        if (jobScheduler != null) {
            if (scheduleJob(context, jobScheduler, 0, false)) {
                arrayList.add("IdleJobs:0");
            }
            if (scheduleJob(context, jobScheduler, 4, true)) {
                arrayList.add("IdleJobs(C):4");
            }
            UniqueJobs.list.forEach(new g(context, jobScheduler, arrayList, 9));
            Log.d("IdleWorker", "schedule [" + String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList) + "]");
        }
    }

    public List<IdleWorkerJob> buildJobs(int i2) {
        if (i2 == 0) {
            return IdleJobs.build();
        }
        if (i2 == 4) {
            return IdleChargedJobs.build();
        }
        Log.e("IdleWorker", "wrong job id=" + i2);
        return new ArrayList();
    }

    public void onCreate() {
        Log.i("IdleWorker", "onCreate");
        ServiceManager.getInstance().add(this);
    }

    public void onDestroy() {
        Log.i("IdleWorker", "onDestroy");
        ServiceManager.getInstance().remove(this);
    }

    public boolean onStartJob(JobParameters jobParameters) {
        int jobId = jobParameters.getJobId();
        Context applicationContext = getApplicationContext();
        long currentTimeMillis = System.currentTimeMillis();
        if (UniqueJobs.contains(jobId)) {
            IdleWorkerJob valueOf = UniqueJobs.valueOf(jobId);
            if (valueOf == null || !valueOf.executable(currentTimeMillis)) {
                Log.i("IdleWorker", "job already executed : " + jobId);
                return false;
            }
            Log.d("IdleWorker", "onStartJob#" + jobId + "=" + valueOf);
            lambda$onStartJob$2(applicationContext, jobParameters, valueOf, new AtomicInteger(1));
            return true;
        }
        List<IdleWorkerJob> buildJobs = buildJobs(jobId);
        buildJobs.removeIf(new b(currentTimeMillis, 8));
        if (buildJobs.size() == 0) {
            Log.i("IdleWorker", "jobs already executed : " + jobId);
            return false;
        }
        StringBuilder o2 = C0086a.o(jobId, "onStartJob#", "=[");
        o2.append((String) buildJobs.stream().map(new e(10)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
        o2.append("]");
        Log.d("IdleWorker", o2.toString());
        buildJobs.forEach(new S(this, applicationContext, jobParameters, new AtomicInteger(buildJobs.size()), 17));
        return true;
    }

    public boolean onStopJob(JobParameters jobParameters) {
        Log.d("IdleWorker", "onStopJob");
        return true;
    }
}
