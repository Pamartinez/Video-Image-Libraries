package androidx.work.impl.foreground;

import Vf.C0867e0;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.ConstraintsState;
import androidx.work.impl.constraints.OnConstraintsStateChangedListener;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.constraints.WorkConstraintsTrackerKt;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CancellationException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SystemForegroundDispatcher implements OnConstraintsStateChangedListener, ExecutionListener {
    static final String TAG = Logger.tagWithPrefix("SystemFgDispatcher");
    private Callback mCallback;
    final WorkConstraintsTracker mConstraintsTracker;
    private Context mContext;
    WorkGenerationalId mCurrentForegroundId;
    final Map<WorkGenerationalId, ForegroundInfo> mForegroundInfoById;
    final Object mLock = new Object();
    /* access modifiers changed from: private */
    public final TaskExecutor mTaskExecutor;
    final Map<WorkGenerationalId, C0867e0> mTrackedWorkSpecs;
    /* access modifiers changed from: private */
    public WorkManagerImpl mWorkManagerImpl;
    final Map<WorkGenerationalId, WorkSpec> mWorkSpecById;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Callback {
        void cancelNotification(int i2);

        void notify(int i2, Notification notification);

        void startForeground(int i2, int i7, Notification notification);

        void stop();
    }

    public SystemForegroundDispatcher(Context context) {
        this.mContext = context;
        WorkManagerImpl instance = WorkManagerImpl.getInstance(context);
        this.mWorkManagerImpl = instance;
        this.mTaskExecutor = instance.getWorkTaskExecutor();
        this.mCurrentForegroundId = null;
        this.mForegroundInfoById = new LinkedHashMap();
        this.mTrackedWorkSpecs = new HashMap();
        this.mWorkSpecById = new HashMap();
        this.mConstraintsTracker = new WorkConstraintsTracker(this.mWorkManagerImpl.getTrackers());
        this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
    }

    public static Intent createNotifyIntent(Context context, WorkGenerationalId workGenerationalId, ForegroundInfo foregroundInfo) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_NOTIFY");
        intent.putExtra("KEY_NOTIFICATION_ID", foregroundInfo.getNotificationId());
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", foregroundInfo.getForegroundServiceType());
        intent.putExtra("KEY_NOTIFICATION", foregroundInfo.getNotification());
        intent.putExtra("KEY_WORKSPEC_ID", workGenerationalId.getWorkSpecId());
        intent.putExtra("KEY_GENERATION", workGenerationalId.getGeneration());
        return intent;
    }

    public static Intent createStartForegroundIntent(Context context, WorkGenerationalId workGenerationalId, ForegroundInfo foregroundInfo) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_START_FOREGROUND");
        intent.putExtra("KEY_WORKSPEC_ID", workGenerationalId.getWorkSpecId());
        intent.putExtra("KEY_GENERATION", workGenerationalId.getGeneration());
        intent.putExtra("KEY_NOTIFICATION_ID", foregroundInfo.getNotificationId());
        intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", foregroundInfo.getForegroundServiceType());
        intent.putExtra("KEY_NOTIFICATION", foregroundInfo.getNotification());
        return intent;
    }

    public static Intent createStopForegroundIntent(Context context) {
        Intent intent = new Intent(context, SystemForegroundService.class);
        intent.setAction("ACTION_STOP_FOREGROUND");
        return intent;
    }

    private void handleCancelWork(Intent intent) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.info(str, "Stopping foreground work for " + intent);
        String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        if (stringExtra != null && !TextUtils.isEmpty(stringExtra)) {
            this.mWorkManagerImpl.cancelWorkById(UUID.fromString(stringExtra));
        }
    }

    private void handleNotify(Intent intent) {
        if (this.mCallback != null) {
            int i2 = 0;
            int intExtra = intent.getIntExtra("KEY_NOTIFICATION_ID", 0);
            int intExtra2 = intent.getIntExtra("KEY_FOREGROUND_SERVICE_TYPE", 0);
            String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
            WorkGenerationalId workGenerationalId = new WorkGenerationalId(stringExtra, intent.getIntExtra("KEY_GENERATION", 0));
            Notification notification = (Notification) intent.getParcelableExtra("KEY_NOTIFICATION");
            Logger logger = Logger.get();
            String str = TAG;
            logger.debug(str, "Notifying with (id:" + intExtra + ", workSpecId: " + stringExtra + ", notificationType :" + intExtra2 + ")");
            if (notification != null) {
                ForegroundInfo foregroundInfo = new ForegroundInfo(intExtra, notification, intExtra2);
                this.mForegroundInfoById.put(workGenerationalId, foregroundInfo);
                ForegroundInfo foregroundInfo2 = this.mForegroundInfoById.get(this.mCurrentForegroundId);
                if (foregroundInfo2 == null) {
                    this.mCurrentForegroundId = workGenerationalId;
                } else {
                    this.mCallback.notify(intExtra, notification);
                    for (Map.Entry<WorkGenerationalId, ForegroundInfo> value : this.mForegroundInfoById.entrySet()) {
                        i2 |= ((ForegroundInfo) value.getValue()).getForegroundServiceType();
                    }
                    foregroundInfo = new ForegroundInfo(foregroundInfo2.getNotificationId(), foregroundInfo2.getNotification(), i2);
                }
                this.mCallback.startForeground(foregroundInfo.getNotificationId(), foregroundInfo.getForegroundServiceType(), foregroundInfo.getNotification());
                return;
            }
            throw new IllegalArgumentException("Notification passed in the intent was null.");
        }
        throw new IllegalStateException("handleNotify was called on the destroyed dispatcher");
    }

    private void handleStartForeground(Intent intent) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.info(str, "Started foreground service " + intent);
        final String stringExtra = intent.getStringExtra("KEY_WORKSPEC_ID");
        this.mTaskExecutor.executeOnTaskThread(new Runnable() {
            public void run() {
                WorkSpec runningWorkSpec = SystemForegroundDispatcher.this.mWorkManagerImpl.getProcessor().getRunningWorkSpec(stringExtra);
                if (runningWorkSpec != null && runningWorkSpec.hasConstraints()) {
                    synchronized (SystemForegroundDispatcher.this.mLock) {
                        SystemForegroundDispatcher.this.mWorkSpecById.put(WorkSpecKt.generationalId(runningWorkSpec), runningWorkSpec);
                        SystemForegroundDispatcher systemForegroundDispatcher = SystemForegroundDispatcher.this;
                        SystemForegroundDispatcher.this.mTrackedWorkSpecs.put(WorkSpecKt.generationalId(runningWorkSpec), WorkConstraintsTrackerKt.listen(systemForegroundDispatcher.mConstraintsTracker, runningWorkSpec, systemForegroundDispatcher.mTaskExecutor.getTaskCoroutineDispatcher(), SystemForegroundDispatcher.this));
                    }
                }
            }
        });
    }

    public void handleStop(Intent intent) {
        Logger.get().info(TAG, "Stopping foreground service");
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.stop();
        }
    }

    public void onConstraintsStateChanged(WorkSpec workSpec, ConstraintsState constraintsState) {
        if (constraintsState instanceof ConstraintsState.ConstraintsNotMet) {
            String str = workSpec.id;
            Logger logger = Logger.get();
            String str2 = TAG;
            logger.debug(str2, "Constraints unmet for WorkSpec " + str);
            this.mWorkManagerImpl.stopForegroundWork(WorkSpecKt.generationalId(workSpec), ((ConstraintsState.ConstraintsNotMet) constraintsState).getReason());
        }
    }

    public void onDestroy() {
        this.mCallback = null;
        synchronized (this.mLock) {
            try {
                for (C0867e0 a7 : this.mTrackedWorkSpecs.values()) {
                    a7.a((CancellationException) null);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.mWorkManagerImpl.getProcessor().removeExecutionListener(this);
    }

    public void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        C0867e0 e0Var;
        Map.Entry entry;
        synchronized (this.mLock) {
            try {
                if (this.mWorkSpecById.remove(workGenerationalId) != null) {
                    e0Var = this.mTrackedWorkSpecs.remove(workGenerationalId);
                } else {
                    e0Var = null;
                }
                if (e0Var != null) {
                    e0Var.a((CancellationException) null);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        ForegroundInfo remove = this.mForegroundInfoById.remove(workGenerationalId);
        if (workGenerationalId.equals(this.mCurrentForegroundId)) {
            if (this.mForegroundInfoById.size() > 0) {
                Iterator it = this.mForegroundInfoById.entrySet().iterator();
                Object next = it.next();
                while (true) {
                    entry = (Map.Entry) next;
                    if (!it.hasNext()) {
                        break;
                    }
                    next = it.next();
                }
                this.mCurrentForegroundId = (WorkGenerationalId) entry.getKey();
                if (this.mCallback != null) {
                    ForegroundInfo foregroundInfo = (ForegroundInfo) entry.getValue();
                    this.mCallback.startForeground(foregroundInfo.getNotificationId(), foregroundInfo.getForegroundServiceType(), foregroundInfo.getNotification());
                    this.mCallback.cancelNotification(foregroundInfo.getNotificationId());
                }
            } else {
                this.mCurrentForegroundId = null;
            }
        }
        Callback callback = this.mCallback;
        if (remove != null && callback != null) {
            Logger.get().debug(TAG, "Removing Notification (id: " + remove.getNotificationId() + ", workSpecId: " + workGenerationalId + ", notificationType: " + remove.getForegroundServiceType());
            callback.cancelNotification(remove.getNotificationId());
        }
    }

    public void onStartCommand(Intent intent) {
        String action = intent.getAction();
        if ("ACTION_START_FOREGROUND".equals(action)) {
            handleStartForeground(intent);
            handleNotify(intent);
        } else if ("ACTION_NOTIFY".equals(action)) {
            handleNotify(intent);
        } else if ("ACTION_CANCEL_WORK".equals(action)) {
            handleCancelWork(intent);
        } else if ("ACTION_STOP_FOREGROUND".equals(action)) {
            handleStop(intent);
        }
    }

    public void onTimeout(int i2, int i7) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.info(str, "Foreground service timed out, FGS type: " + i7);
        for (Map.Entry next : this.mForegroundInfoById.entrySet()) {
            if (((ForegroundInfo) next.getValue()).getForegroundServiceType() == i7) {
                this.mWorkManagerImpl.stopForegroundWork((WorkGenerationalId) next.getKey(), -128);
            }
        }
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.stop();
        }
    }

    public void setCallback(Callback callback) {
        if (this.mCallback != null) {
            Logger.get().error(TAG, "A callback already exists.");
        } else {
            this.mCallback = callback;
        }
    }
}
