package androidx.work.impl.utils;

import androidx.work.Logger;
import androidx.work.RunnableScheduler;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WorkTimer {
    private static final String TAG = Logger.tagWithPrefix("WorkTimer");
    final Map<WorkGenerationalId, TimeLimitExceededListener> mListeners = new HashMap();
    final Object mLock = new Object();
    final RunnableScheduler mRunnableScheduler;
    final Map<WorkGenerationalId, WorkTimerRunnable> mTimerMap = new HashMap();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface TimeLimitExceededListener {
        void onTimeLimitExceeded(WorkGenerationalId workGenerationalId);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class WorkTimerRunnable implements Runnable {
        private final WorkGenerationalId mWorkGenerationalId;
        private final WorkTimer mWorkTimer;

        public WorkTimerRunnable(WorkTimer workTimer, WorkGenerationalId workGenerationalId) {
            this.mWorkTimer = workTimer;
            this.mWorkGenerationalId = workGenerationalId;
        }

        public void run() {
            synchronized (this.mWorkTimer.mLock) {
                try {
                    if (this.mWorkTimer.mTimerMap.remove(this.mWorkGenerationalId) != null) {
                        TimeLimitExceededListener remove = this.mWorkTimer.mListeners.remove(this.mWorkGenerationalId);
                        if (remove != null) {
                            remove.onTimeLimitExceeded(this.mWorkGenerationalId);
                        }
                    } else {
                        Logger logger = Logger.get();
                        WorkGenerationalId workGenerationalId = this.mWorkGenerationalId;
                        logger.debug("WrkTimerRunnable", "Timer with " + workGenerationalId + " is already marked as complete.");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public WorkTimer(RunnableScheduler runnableScheduler) {
        this.mRunnableScheduler = runnableScheduler;
    }

    public void startTimer(WorkGenerationalId workGenerationalId, long j2, TimeLimitExceededListener timeLimitExceededListener) {
        synchronized (this.mLock) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Starting timer for " + workGenerationalId);
            stopTimer(workGenerationalId);
            WorkTimerRunnable workTimerRunnable = new WorkTimerRunnable(this, workGenerationalId);
            this.mTimerMap.put(workGenerationalId, workTimerRunnable);
            this.mListeners.put(workGenerationalId, timeLimitExceededListener);
            this.mRunnableScheduler.scheduleWithDelay(j2, workTimerRunnable);
        }
    }

    public void stopTimer(WorkGenerationalId workGenerationalId) {
        synchronized (this.mLock) {
            try {
                if (this.mTimerMap.remove(workGenerationalId) != null) {
                    Logger logger = Logger.get();
                    String str = TAG;
                    logger.debug(str, "Stopping timer for " + workGenerationalId);
                    this.mListeners.remove(workGenerationalId);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
