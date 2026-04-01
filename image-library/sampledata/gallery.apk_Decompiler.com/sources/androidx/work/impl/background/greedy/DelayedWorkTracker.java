package androidx.work.impl.background.greedy;

import androidx.work.Clock;
import androidx.work.Logger;
import androidx.work.RunnableScheduler;
import androidx.work.impl.Scheduler;
import androidx.work.impl.model.WorkSpec;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DelayedWorkTracker {
    static final String TAG = Logger.tagWithPrefix("DelayedWorkTracker");
    private final Clock mClock;
    final Scheduler mImmediateScheduler;
    private final RunnableScheduler mRunnableScheduler;
    private final Map<String, Runnable> mRunnables = new HashMap();

    public DelayedWorkTracker(Scheduler scheduler, RunnableScheduler runnableScheduler, Clock clock) {
        this.mImmediateScheduler = scheduler;
        this.mRunnableScheduler = runnableScheduler;
        this.mClock = clock;
    }

    public void schedule(final WorkSpec workSpec, long j2) {
        Runnable remove = this.mRunnables.remove(workSpec.id);
        if (remove != null) {
            this.mRunnableScheduler.cancel(remove);
        }
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                Logger logger = Logger.get();
                String str = DelayedWorkTracker.TAG;
                logger.debug(str, "Scheduling work " + workSpec.id);
                DelayedWorkTracker.this.mImmediateScheduler.schedule(workSpec);
            }
        };
        this.mRunnables.put(workSpec.id, r0);
        this.mRunnableScheduler.scheduleWithDelay(j2 - this.mClock.currentTimeMillis(), r0);
    }

    public void unschedule(String str) {
        Runnable remove = this.mRunnables.remove(str);
        if (remove != null) {
            this.mRunnableScheduler.cancel(remove);
        }
    }
}
