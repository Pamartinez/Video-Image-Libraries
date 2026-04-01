package androidx.work.impl.background.systemalarm;

import Vf.C0867e0;
import Vf.C0886x;
import android.content.Context;
import android.os.PowerManager;
import androidx.work.Logger;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.constraints.ConstraintsState;
import androidx.work.impl.constraints.OnConstraintsStateChangedListener;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.constraints.WorkConstraintsTrackerKt;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.WorkTimer;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import p0.C0252a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DelayMetCommandHandler implements OnConstraintsStateChangedListener, WorkTimer.TimeLimitExceededListener {
    private static final String TAG = Logger.tagWithPrefix("DelayMetCommandHandler");
    private final Context mContext;
    private final C0886x mCoroutineDispatcher;
    private int mCurrentState = 0;
    private final SystemAlarmDispatcher mDispatcher;
    private boolean mHasConstraints = false;
    private volatile C0867e0 mJob;
    private final Object mLock = new Object();
    private final Executor mMainThreadExecutor;
    private final Executor mSerialExecutor;
    private final int mStartId;
    private final StartStopToken mToken;
    private PowerManager.WakeLock mWakeLock;
    private final WorkConstraintsTracker mWorkConstraintsTracker;
    private final WorkGenerationalId mWorkGenerationalId;

    public DelayMetCommandHandler(Context context, int i2, SystemAlarmDispatcher systemAlarmDispatcher, StartStopToken startStopToken) {
        this.mContext = context;
        this.mStartId = i2;
        this.mDispatcher = systemAlarmDispatcher;
        this.mWorkGenerationalId = startStopToken.getId();
        this.mToken = startStopToken;
        Trackers trackers = systemAlarmDispatcher.getWorkManager().getTrackers();
        this.mSerialExecutor = systemAlarmDispatcher.getTaskExecutor().getSerialTaskExecutor();
        this.mMainThreadExecutor = systemAlarmDispatcher.getTaskExecutor().getMainThreadExecutor();
        this.mCoroutineDispatcher = systemAlarmDispatcher.getTaskExecutor().getTaskCoroutineDispatcher();
        this.mWorkConstraintsTracker = new WorkConstraintsTracker(trackers);
    }

    private void cleanUp() {
        synchronized (this.mLock) {
            try {
                if (this.mJob != null) {
                    this.mJob.a((CancellationException) null);
                }
                this.mDispatcher.getWorkTimer().stopTimer(this.mWorkGenerationalId);
                PowerManager.WakeLock wakeLock = this.mWakeLock;
                if (wakeLock != null && wakeLock.isHeld()) {
                    Logger logger = Logger.get();
                    String str = TAG;
                    logger.debug(str, "Releasing wakelock " + this.mWakeLock + "for WorkSpec " + this.mWorkGenerationalId);
                    this.mWakeLock.release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public void startWork() {
        if (this.mCurrentState == 0) {
            this.mCurrentState = 1;
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "onAllConstraintsMet for " + this.mWorkGenerationalId);
            if (this.mDispatcher.getProcessor().startWork(this.mToken)) {
                this.mDispatcher.getWorkTimer().startTimer(this.mWorkGenerationalId, 600000, this);
            } else {
                cleanUp();
            }
        } else {
            Logger logger2 = Logger.get();
            String str2 = TAG;
            logger2.debug(str2, "Already started work for " + this.mWorkGenerationalId);
        }
    }

    /* access modifiers changed from: private */
    public void stopWork() {
        String workSpecId = this.mWorkGenerationalId.getWorkSpecId();
        if (this.mCurrentState < 2) {
            this.mCurrentState = 2;
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Stopping work for WorkSpec " + workSpecId);
            this.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, CommandHandler.createStopWorkIntent(this.mContext, this.mWorkGenerationalId), this.mStartId));
            if (this.mDispatcher.getProcessor().isEnqueued(this.mWorkGenerationalId.getWorkSpecId())) {
                Logger logger2 = Logger.get();
                logger2.debug(str, "WorkSpec " + workSpecId + " needs to be rescheduled");
                this.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, CommandHandler.createScheduleWorkIntent(this.mContext, this.mWorkGenerationalId), this.mStartId));
                return;
            }
            Logger logger3 = Logger.get();
            logger3.debug(str, "Processor does not have WorkSpec " + workSpecId + ". No need to reschedule");
            return;
        }
        Logger logger4 = Logger.get();
        String str2 = TAG;
        logger4.debug(str2, "Already stopped work for " + workSpecId);
    }

    public void handleProcessWork() {
        String workSpecId = this.mWorkGenerationalId.getWorkSpecId();
        Context context = this.mContext;
        StringBuilder t = C0212a.t(workSpecId, " (");
        t.append(this.mStartId);
        t.append(")");
        this.mWakeLock = WakeLocks.newWakeLock(context, t.toString());
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Acquiring wakelock " + this.mWakeLock + "for WorkSpec " + workSpecId);
        this.mWakeLock.acquire();
        WorkSpec workSpec = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getWorkSpec(workSpecId);
        if (workSpec == null) {
            this.mSerialExecutor.execute(new C0252a(this, 0));
            return;
        }
        boolean hasConstraints = workSpec.hasConstraints();
        this.mHasConstraints = hasConstraints;
        if (!hasConstraints) {
            Logger logger2 = Logger.get();
            logger2.debug(str, "No constraints for " + workSpecId);
            this.mSerialExecutor.execute(new C0252a(this, 1));
            return;
        }
        this.mJob = WorkConstraintsTrackerKt.listen(this.mWorkConstraintsTracker, workSpec, this.mCoroutineDispatcher, this);
    }

    public void onConstraintsStateChanged(WorkSpec workSpec, ConstraintsState constraintsState) {
        if (constraintsState instanceof ConstraintsState.ConstraintsMet) {
            this.mSerialExecutor.execute(new C0252a(this, 1));
        } else {
            this.mSerialExecutor.execute(new C0252a(this, 0));
        }
    }

    public void onExecuted(boolean z) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "onExecuted " + this.mWorkGenerationalId + ArcCommonLog.TAG_COMMA + z);
        cleanUp();
        if (z) {
            this.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, CommandHandler.createScheduleWorkIntent(this.mContext, this.mWorkGenerationalId), this.mStartId));
        }
        if (this.mHasConstraints) {
            this.mMainThreadExecutor.execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, CommandHandler.createConstraintsChangedIntent(this.mContext), this.mStartId));
        }
    }

    public void onTimeLimitExceeded(WorkGenerationalId workGenerationalId) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Exceeded time limits on execution for " + workGenerationalId);
        this.mSerialExecutor.execute(new C0252a(this, 0));
    }
}
