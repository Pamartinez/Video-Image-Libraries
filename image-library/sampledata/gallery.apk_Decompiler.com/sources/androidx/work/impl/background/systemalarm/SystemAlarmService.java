package androidx.work.impl.background.systemalarm;

import android.content.Intent;
import androidx.lifecycle.LifecycleService;
import androidx.work.Logger;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.utils.WakeLocks;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SystemAlarmService extends LifecycleService implements SystemAlarmDispatcher.CommandsCompletedListener {
    private static final String TAG = Logger.tagWithPrefix("SystemAlarmService");
    private SystemAlarmDispatcher mDispatcher;
    private boolean mIsShutdown;

    private void initializeDispatcher() {
        SystemAlarmDispatcher systemAlarmDispatcher = new SystemAlarmDispatcher(this);
        this.mDispatcher = systemAlarmDispatcher;
        systemAlarmDispatcher.setCompletedListener(this);
    }

    public void onAllCommandsCompleted() {
        this.mIsShutdown = true;
        Logger.get().debug(TAG, "All commands completed in dispatcher");
        WakeLocks.checkWakeLocks();
        stopSelf();
    }

    public void onCreate() {
        super.onCreate();
        initializeDispatcher();
        this.mIsShutdown = false;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mIsShutdown = true;
        this.mDispatcher.onDestroy();
    }

    public int onStartCommand(Intent intent, int i2, int i7) {
        super.onStartCommand(intent, i2, i7);
        if (this.mIsShutdown) {
            Logger.get().info(TAG, "Re-initializing SystemAlarmDispatcher after a request to shut-down.");
            this.mDispatcher.onDestroy();
            initializeDispatcher();
            this.mIsShutdown = false;
        }
        if (intent == null) {
            return 3;
        }
        this.mDispatcher.add(intent, i7);
        return 3;
    }
}
