package androidx.work.impl.utils;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.ApplicationExitInfo;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.os.UserManagerCompat;
import androidx.core.util.Consumer;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.WorkInfo$State;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkDatabasePathHelper;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import com.adobe.internal.xmp.options.PropertyOptions;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ForceStopRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("ForceStopRunnable");
    private static final long TEN_YEARS = TimeUnit.DAYS.toMillis(3650);
    private final Context mContext;
    private final PreferenceUtils mPreferenceUtils;
    private int mRetryCount = 0;
    private final WorkManagerImpl mWorkManager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BroadcastReceiver extends android.content.BroadcastReceiver {
        private static final String TAG = Logger.tagWithPrefix("ForceStopRunnable$Rcvr");

        public void onReceive(Context context, Intent intent) {
            if (intent != null && "ACTION_FORCE_STOP_RESCHEDULE".equals(intent.getAction())) {
                Logger.get().verbose(TAG, "Rescheduling alarm that keeps track of force-stops.");
                ForceStopRunnable.setAlarm(context);
            }
        }
    }

    public ForceStopRunnable(Context context, WorkManagerImpl workManagerImpl) {
        this.mContext = context.getApplicationContext();
        this.mWorkManager = workManagerImpl;
        this.mPreferenceUtils = workManagerImpl.getPreferenceUtils();
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return intent;
    }

    private static PendingIntent getPendingIntent(Context context, int i2) {
        return PendingIntent.getBroadcast(context, -1, getIntent(context), i2);
    }

    public static void setAlarm(Context context) {
        int i2;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (Build.VERSION.SDK_INT >= 31) {
            i2 = 167772160;
        } else {
            i2 = 134217728;
        }
        PendingIntent pendingIntent = getPendingIntent(context, i2);
        long currentTimeMillis = System.currentTimeMillis() + TEN_YEARS;
        if (alarmManager != null) {
            alarmManager.setExact(0, currentTimeMillis, pendingIntent);
        }
    }

    public boolean cleanUp() {
        boolean z;
        boolean reconcileJobs = SystemJobScheduler.reconcileJobs(this.mContext, this.mWorkManager.getWorkDatabase());
        WorkDatabase workDatabase = this.mWorkManager.getWorkDatabase();
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        WorkProgressDao workProgressDao = workDatabase.workProgressDao();
        workDatabase.beginTransaction();
        try {
            List<WorkSpec> runningWork = workSpecDao.getRunningWork();
            if (runningWork == null || runningWork.isEmpty()) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                for (WorkSpec next : runningWork) {
                    workSpecDao.setState(WorkInfo$State.ENQUEUED, next.id);
                    workSpecDao.setStopReason(next.id, -512);
                    workSpecDao.markWorkSpecScheduled(next.id, -1);
                }
            }
            workProgressDao.deleteAll();
            workDatabase.setTransactionSuccessful();
            workDatabase.endTransaction();
            if (z || reconcileJobs) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            workDatabase.endTransaction();
            throw th;
        }
    }

    public void forceStopRunnable() {
        boolean cleanUp = cleanUp();
        if (shouldRescheduleWorkers()) {
            Logger.get().debug(TAG, "Rescheduling Workers.");
            this.mWorkManager.rescheduleEligibleWork();
            this.mWorkManager.getPreferenceUtils().setNeedsReschedule(false);
        } else if (isForceStopped()) {
            Logger.get().debug(TAG, "Application was force-stopped, rescheduling.");
            this.mWorkManager.rescheduleEligibleWork();
            this.mPreferenceUtils.setLastForceStopEventMillis(this.mWorkManager.getConfiguration().getClock().currentTimeMillis());
        } else if (cleanUp) {
            Logger.get().debug(TAG, "Found unfinished work, scheduling it.");
            Schedulers.schedule(this.mWorkManager.getConfiguration(), this.mWorkManager.getWorkDatabase(), this.mWorkManager.getSchedulers());
        }
    }

    public boolean isForceStopped() {
        int i2;
        try {
            if (Build.VERSION.SDK_INT >= 31) {
                i2 = 570425344;
            } else {
                i2 = PropertyOptions.DELETE_EXISTING;
            }
            PendingIntent pendingIntent = getPendingIntent(this.mContext, i2);
            if (pendingIntent != null) {
                pendingIntent.cancel();
            }
            List historicalProcessExitReasons = ((ActivityManager) this.mContext.getSystemService("activity")).getHistoricalProcessExitReasons((String) null, 0, 0);
            if (historicalProcessExitReasons != null && !historicalProcessExitReasons.isEmpty()) {
                long lastForceStopEventMillis = this.mPreferenceUtils.getLastForceStopEventMillis();
                for (int i7 = 0; i7 < historicalProcessExitReasons.size(); i7++) {
                    ApplicationExitInfo applicationExitInfo = (ApplicationExitInfo) historicalProcessExitReasons.get(i7);
                    if (applicationExitInfo.getReason() == 10 && applicationExitInfo.getTimestamp() >= lastForceStopEventMillis) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IllegalArgumentException | SecurityException e) {
            Logger.get().warning(TAG, "Ignoring exception", e);
            return true;
        }
    }

    public boolean multiProcessChecks() {
        Configuration configuration = this.mWorkManager.getConfiguration();
        if (TextUtils.isEmpty(configuration.getDefaultProcessName())) {
            Logger.get().debug(TAG, "The default process name was not specified.");
            return true;
        }
        boolean isDefaultProcess = ProcessUtils.isDefaultProcess(this.mContext, configuration);
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Is default app process = " + isDefaultProcess);
        return isDefaultProcess;
    }

    public void run() {
        String str;
        try {
            if (multiProcessChecks()) {
                while (true) {
                    WorkDatabasePathHelper.migrateDatabase(this.mContext);
                    Logger.get().debug(TAG, "Performing cleanup operations.");
                    forceStopRunnable();
                    break;
                }
            }
        } catch (SQLiteException e) {
            Logger.get().error(TAG, "Unexpected SQLite exception during migrations");
            IllegalStateException illegalStateException = new IllegalStateException("Unexpected SQLite exception during migrations", e);
            Consumer<Throwable> initializationExceptionHandler = this.mWorkManager.getConfiguration().getInitializationExceptionHandler();
            if (initializationExceptionHandler != null) {
                initializationExceptionHandler.accept(illegalStateException);
            } else {
                throw illegalStateException;
            }
        } catch (SQLiteAccessPermException | SQLiteCantOpenDatabaseException | SQLiteConstraintException | SQLiteDatabaseCorruptException | SQLiteDatabaseLockedException | SQLiteDiskIOException | SQLiteFullException | SQLiteTableLockedException e7) {
            int i2 = this.mRetryCount + 1;
            this.mRetryCount = i2;
            if (i2 >= 3) {
                if (UserManagerCompat.isUserUnlocked(this.mContext)) {
                    str = "The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.";
                } else {
                    str = "WorkManager can't be accessed from direct boot, because credential encrypted storage isn't accessible.\nDon't access or initialise WorkManager from directAware components. See https://developer.android.com/training/articles/direct-boot";
                }
                Logger logger = Logger.get();
                String str2 = TAG;
                logger.error(str2, str, e7);
                IllegalStateException illegalStateException2 = new IllegalStateException(str, e7);
                Consumer<Throwable> initializationExceptionHandler2 = this.mWorkManager.getConfiguration().getInitializationExceptionHandler();
                if (initializationExceptionHandler2 != null) {
                    Logger.get().debug(str2, "Routing exception to the specified exception handler", illegalStateException2);
                    initializationExceptionHandler2.accept(illegalStateException2);
                } else {
                    throw illegalStateException2;
                }
            } else {
                Logger logger2 = Logger.get();
                String str3 = TAG;
                logger2.debug(str3, "Retrying after " + (((long) i2) * 300), e7);
                sleep(((long) this.mRetryCount) * 300);
            }
        } catch (Throwable th) {
            this.mWorkManager.onForceStopRunnableCompleted();
            throw th;
        }
        this.mWorkManager.onForceStopRunnableCompleted();
    }

    public boolean shouldRescheduleWorkers() {
        return this.mWorkManager.getPreferenceUtils().getNeedsReschedule();
    }

    public void sleep(long j2) {
        try {
            Thread.sleep(j2);
        } catch (InterruptedException unused) {
        }
    }
}
