package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.work.Clock;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.StartStopTokens;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CommandHandler implements ExecutionListener {
    private static final String TAG = Logger.tagWithPrefix("CommandHandler");
    private final Clock mClock;
    private final Context mContext;
    private final Object mLock = new Object();
    private final Map<WorkGenerationalId, DelayMetCommandHandler> mPendingDelayMet = new HashMap();
    private final StartStopTokens mStartStopTokens;

    public CommandHandler(Context context, Clock clock, StartStopTokens startStopTokens) {
        this.mContext = context;
        this.mClock = clock;
        this.mStartStopTokens = startStopTokens;
    }

    public static Intent createConstraintsChangedIntent(Context context) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_CONSTRAINTS_CHANGED");
        return intent;
    }

    public static Intent createDelayMetIntent(Context context, WorkGenerationalId workGenerationalId) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_DELAY_MET");
        return writeWorkGenerationalId(intent, workGenerationalId);
    }

    public static Intent createExecutionCompletedIntent(Context context, WorkGenerationalId workGenerationalId, boolean z) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_EXECUTION_COMPLETED");
        intent.putExtra("KEY_NEEDS_RESCHEDULE", z);
        return writeWorkGenerationalId(intent, workGenerationalId);
    }

    public static Intent createScheduleWorkIntent(Context context, WorkGenerationalId workGenerationalId) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_SCHEDULE_WORK");
        return writeWorkGenerationalId(intent, workGenerationalId);
    }

    public static Intent createStopWorkIntent(Context context, WorkGenerationalId workGenerationalId) {
        Intent intent = new Intent(context, SystemAlarmService.class);
        intent.setAction("ACTION_STOP_WORK");
        return writeWorkGenerationalId(intent, workGenerationalId);
    }

    private void handleConstraintsChanged(Intent intent, int i2, SystemAlarmDispatcher systemAlarmDispatcher) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Handling constraints changed " + intent);
        new ConstraintsCommandHandler(this.mContext, this.mClock, i2, systemAlarmDispatcher).handleConstraintsChanged();
    }

    private void handleDelayMet(Intent intent, int i2, SystemAlarmDispatcher systemAlarmDispatcher) {
        synchronized (this.mLock) {
            try {
                WorkGenerationalId readWorkGenerationalId = readWorkGenerationalId(intent);
                Logger logger = Logger.get();
                String str = TAG;
                logger.debug(str, "Handing delay met for " + readWorkGenerationalId);
                if (!this.mPendingDelayMet.containsKey(readWorkGenerationalId)) {
                    DelayMetCommandHandler delayMetCommandHandler = new DelayMetCommandHandler(this.mContext, i2, systemAlarmDispatcher, this.mStartStopTokens.tokenFor(readWorkGenerationalId));
                    this.mPendingDelayMet.put(readWorkGenerationalId, delayMetCommandHandler);
                    delayMetCommandHandler.handleProcessWork();
                } else {
                    Logger logger2 = Logger.get();
                    logger2.debug(str, "WorkSpec " + readWorkGenerationalId + " is is already being handled for ACTION_DELAY_MET");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void handleExecutionCompleted(Intent intent, int i2) {
        WorkGenerationalId readWorkGenerationalId = readWorkGenerationalId(intent);
        boolean z = intent.getExtras().getBoolean("KEY_NEEDS_RESCHEDULE");
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Handling onExecutionCompleted " + intent + ArcCommonLog.TAG_COMMA + i2);
        onExecuted(readWorkGenerationalId, z);
    }

    private void handleReschedule(Intent intent, int i2, SystemAlarmDispatcher systemAlarmDispatcher) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Handling reschedule " + intent + ArcCommonLog.TAG_COMMA + i2);
        systemAlarmDispatcher.getWorkManager().rescheduleEligibleWork();
    }

    private void handleScheduleWorkIntent(Intent intent, int i2, SystemAlarmDispatcher systemAlarmDispatcher) {
        WorkGenerationalId readWorkGenerationalId = readWorkGenerationalId(intent);
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Handling schedule work for " + readWorkGenerationalId);
        WorkDatabase workDatabase = systemAlarmDispatcher.getWorkManager().getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            WorkSpec workSpec = workDatabase.workSpecDao().getWorkSpec(readWorkGenerationalId.getWorkSpecId());
            if (workSpec == null) {
                Logger logger2 = Logger.get();
                logger2.warning(str, "Skipping scheduling " + readWorkGenerationalId + " because it's no longer in the DB");
            } else if (workSpec.state.isFinished()) {
                Logger logger3 = Logger.get();
                logger3.warning(str, "Skipping scheduling " + readWorkGenerationalId + "because it is finished.");
                workDatabase.endTransaction();
            } else {
                long calculateNextRunTime = workSpec.calculateNextRunTime();
                if (!workSpec.hasConstraints()) {
                    Logger logger4 = Logger.get();
                    logger4.debug(str, "Setting up Alarms for " + readWorkGenerationalId + "at " + calculateNextRunTime);
                    Alarms.setAlarm(this.mContext, workDatabase, readWorkGenerationalId, calculateNextRunTime);
                } else {
                    Logger logger5 = Logger.get();
                    logger5.debug(str, "Opportunistically setting an alarm for " + readWorkGenerationalId + "at " + calculateNextRunTime);
                    Alarms.setAlarm(this.mContext, workDatabase, readWorkGenerationalId, calculateNextRunTime);
                    systemAlarmDispatcher.getTaskExecutor().getMainThreadExecutor().execute(new SystemAlarmDispatcher.AddRunnable(systemAlarmDispatcher, createConstraintsChangedIntent(this.mContext), i2));
                }
                workDatabase.setTransactionSuccessful();
                workDatabase.endTransaction();
            }
        } finally {
            workDatabase.endTransaction();
        }
    }

    private void handleStopWork(Intent intent, SystemAlarmDispatcher systemAlarmDispatcher) {
        ArrayList arrayList;
        Bundle extras = intent.getExtras();
        String string = extras.getString("KEY_WORKSPEC_ID");
        if (extras.containsKey("KEY_WORKSPEC_GENERATION")) {
            int i2 = extras.getInt("KEY_WORKSPEC_GENERATION");
            ArrayList arrayList2 = new ArrayList(1);
            StartStopToken remove = this.mStartStopTokens.remove(new WorkGenerationalId(string, i2));
            arrayList = arrayList2;
            if (remove != null) {
                arrayList2.add(remove);
                arrayList = arrayList2;
            }
        } else {
            arrayList = this.mStartStopTokens.remove(string);
        }
        for (StartStopToken next : arrayList) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Handing stopWork work for " + string);
            systemAlarmDispatcher.getWorkerLauncher().stopWork(next);
            Alarms.cancelAlarm(this.mContext, systemAlarmDispatcher.getWorkManager().getWorkDatabase(), next.getId());
            systemAlarmDispatcher.onExecuted(next.getId(), false);
        }
    }

    private static boolean hasKeys(Bundle bundle, String... strArr) {
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        for (String str : strArr) {
            if (bundle.get(str) == null) {
                return false;
            }
        }
        return true;
    }

    public static WorkGenerationalId readWorkGenerationalId(Intent intent) {
        return new WorkGenerationalId(intent.getStringExtra("KEY_WORKSPEC_ID"), intent.getIntExtra("KEY_WORKSPEC_GENERATION", 0));
    }

    private static Intent writeWorkGenerationalId(Intent intent, WorkGenerationalId workGenerationalId) {
        intent.putExtra("KEY_WORKSPEC_ID", workGenerationalId.getWorkSpecId());
        intent.putExtra("KEY_WORKSPEC_GENERATION", workGenerationalId.getGeneration());
        return intent;
    }

    public boolean hasPendingCommands() {
        boolean z;
        synchronized (this.mLock) {
            z = !this.mPendingDelayMet.isEmpty();
        }
        return z;
    }

    public void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        synchronized (this.mLock) {
            try {
                DelayMetCommandHandler remove = this.mPendingDelayMet.remove(workGenerationalId);
                this.mStartStopTokens.remove(workGenerationalId);
                if (remove != null) {
                    remove.onExecuted(z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void onHandleIntent(Intent intent, int i2, SystemAlarmDispatcher systemAlarmDispatcher) {
        String action = intent.getAction();
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action)) {
            handleConstraintsChanged(intent, i2, systemAlarmDispatcher);
        } else if ("ACTION_RESCHEDULE".equals(action)) {
            handleReschedule(intent, i2, systemAlarmDispatcher);
        } else if (!hasKeys(intent.getExtras(), "KEY_WORKSPEC_ID")) {
            Logger logger = Logger.get();
            String str = TAG;
            logger.error(str, "Invalid request for " + action + " , requires KEY_WORKSPEC_ID .");
        } else if ("ACTION_SCHEDULE_WORK".equals(action)) {
            handleScheduleWorkIntent(intent, i2, systemAlarmDispatcher);
        } else if ("ACTION_DELAY_MET".equals(action)) {
            handleDelayMet(intent, i2, systemAlarmDispatcher);
        } else if ("ACTION_STOP_WORK".equals(action)) {
            handleStopWork(intent, systemAlarmDispatcher);
        } else if ("ACTION_EXECUTION_COMPLETED".equals(action)) {
            handleExecutionCompleted(intent, i2);
        } else {
            Logger logger2 = Logger.get();
            String str2 = TAG;
            logger2.warning(str2, "Ignoring intent " + intent);
        }
    }
}
