package androidx.work.impl.foreground;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import androidx.lifecycle.LifecycleService;
import androidx.work.Logger;
import androidx.work.impl.foreground.SystemForegroundDispatcher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SystemForegroundService extends LifecycleService implements SystemForegroundDispatcher.Callback {
    /* access modifiers changed from: private */
    public static final String TAG = Logger.tagWithPrefix("SystemFgService");
    private static SystemForegroundService sForegroundService = null;
    SystemForegroundDispatcher mDispatcher;
    private boolean mIsShutdown;
    NotificationManager mNotificationManager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api29Impl {
        public static void startForeground(Service service, int i2, Notification notification, int i7) {
            service.startForeground(i2, notification, i7);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api31Impl {
        public static void startForeground(Service service, int i2, Notification notification, int i7) {
            try {
                service.startForeground(i2, notification, i7);
            } catch (ForegroundServiceStartNotAllowedException e) {
                Logger.get().warning(SystemForegroundService.TAG, "Unable to start foreground service", e);
            } catch (SecurityException e7) {
                Logger.get().warning(SystemForegroundService.TAG, "Unable to start foreground service", e7);
            }
        }
    }

    private void initializeDispatcher() {
        this.mNotificationManager = (NotificationManager) getApplicationContext().getSystemService("notification");
        SystemForegroundDispatcher systemForegroundDispatcher = new SystemForegroundDispatcher(getApplicationContext());
        this.mDispatcher = systemForegroundDispatcher;
        systemForegroundDispatcher.setCallback(this);
    }

    public void cancelNotification(int i2) {
        this.mNotificationManager.cancel(i2);
    }

    public void notify(int i2, Notification notification) {
        this.mNotificationManager.notify(i2, notification);
    }

    public void onCreate() {
        super.onCreate();
        sForegroundService = this;
        initializeDispatcher();
    }

    public void onDestroy() {
        super.onDestroy();
        this.mDispatcher.onDestroy();
    }

    public int onStartCommand(Intent intent, int i2, int i7) {
        super.onStartCommand(intent, i2, i7);
        if (this.mIsShutdown) {
            Logger.get().info(TAG, "Re-initializing SystemForegroundService after a request to shut-down.");
            this.mDispatcher.onDestroy();
            initializeDispatcher();
            this.mIsShutdown = false;
        }
        if (intent == null) {
            return 3;
        }
        this.mDispatcher.onStartCommand(intent);
        return 3;
    }

    public void onTimeout(int i2) {
        if (Build.VERSION.SDK_INT < 35) {
            this.mDispatcher.onTimeout(i2, 2048);
        }
    }

    public void startForeground(int i2, int i7, Notification notification) {
        if (Build.VERSION.SDK_INT >= 31) {
            Api31Impl.startForeground(this, i2, notification, i7);
        } else {
            Api29Impl.startForeground(this, i2, notification, i7);
        }
    }

    public void stop() {
        this.mIsShutdown = true;
        Logger.get().debug(TAG, "Shutting down.");
        stopForeground(true);
        sForegroundService = null;
        stopSelf();
    }

    public void onTimeout(int i2, int i7) {
        this.mDispatcher.onTimeout(i2, i7);
    }
}
