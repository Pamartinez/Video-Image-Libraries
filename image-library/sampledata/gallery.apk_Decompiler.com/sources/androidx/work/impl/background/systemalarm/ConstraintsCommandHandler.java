package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import androidx.work.Clock;
import androidx.work.Logger;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ConstraintsCommandHandler {
    private static final String TAG = Logger.tagWithPrefix("ConstraintsCmdHandler");
    private final Clock mClock;
    private final Context mContext;
    private final SystemAlarmDispatcher mDispatcher;
    private final int mStartId;
    private final WorkConstraintsTracker mWorkConstraintsTracker;

    public ConstraintsCommandHandler(Context context, Clock clock, int i2, SystemAlarmDispatcher systemAlarmDispatcher) {
        this.mContext = context;
        this.mClock = clock;
        this.mStartId = i2;
        this.mDispatcher = systemAlarmDispatcher;
        this.mWorkConstraintsTracker = new WorkConstraintsTracker(systemAlarmDispatcher.getWorkManager().getTrackers());
    }

    public void handleConstraintsChanged() {
        List<WorkSpec> scheduledWork = this.mDispatcher.getWorkManager().getWorkDatabase().workSpecDao().getScheduledWork();
        ConstraintProxy.updateAll(this.mContext, scheduledWork);
        ArrayList arrayList = new ArrayList(scheduledWork.size());
        long currentTimeMillis = this.mClock.currentTimeMillis();
        for (WorkSpec next : scheduledWork) {
            if (currentTimeMillis >= next.calculateNextRunTime() && (!next.hasConstraints() || this.mWorkConstraintsTracker.areAllConstraintsMet(next))) {
                arrayList.add(next);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            WorkSpec workSpec = (WorkSpec) it.next();
            String str = workSpec.id;
            Intent createDelayMetIntent = CommandHandler.createDelayMetIntent(this.mContext, WorkSpecKt.generationalId(workSpec));
            Logger logger = Logger.get();
            String str2 = TAG;
            logger.debug(str2, "Creating a delay_met command for workSpec with id (" + str + ")");
            this.mDispatcher.getTaskExecutor().getMainThreadExecutor().execute(new SystemAlarmDispatcher.AddRunnable(this.mDispatcher, createDelayMetIntent, this.mStartId));
        }
    }
}
