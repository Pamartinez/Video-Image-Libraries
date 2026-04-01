package com.samsung.android.gallery.app.service;

import android.content.Intent;
import com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil;
import com.samsung.android.gallery.module.remotegallery.RemoteServer;
import com.samsung.android.gallery.module.remotegallery.RemoteWebServer;
import com.samsung.android.gallery.module.service.abstraction.BaseService;
import com.samsung.android.gallery.module.service.notification.RemoteServerNotificationHelper;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoteServerService extends BaseService {
    private RemoteServerNotificationHelper mNotificationHelper;

    private void initNotificationHelper() {
        RemoteServerNotificationHelper remoteServerNotificationHelper = new RemoteServerNotificationHelper(this, this.TAG, "com.samsung.android.gallery.app.service.RemoteServerService");
        this.mNotificationHelper = remoteServerNotificationHelper;
        remoteServerNotificationHelper.create("Remote Gallery Server Running.");
        showNotification();
    }

    private void onInterruptService() {
        if (RemoteServer.getInstance().isRun()) {
            RemoteServer.getInstance().stop();
        }
        if (RemoteWebServer.getInstance().isRun()) {
            RemoteWebServer.getInstance().stop();
        }
        RemoteGalleryUtil.removeWakeup();
        RemoteServerNotificationHelper remoteServerNotificationHelper = this.mNotificationHelper;
        if (remoteServerNotificationHelper != null) {
            remoteServerNotificationHelper.dismiss();
        }
        stopForeground(true);
    }

    private void onStartService(Intent intent) {
        initNotificationHelper();
        startForeground(this.mNotificationHelper.getSummaryId(), this.mNotificationHelper.getSummaryNotification());
    }

    private void showNotification() {
        this.mNotificationHelper.notify(this.mNotificationHelper.getStartBuilder(getApplicationContext()).build());
    }

    public int onStartCommand(Intent intent, int i2, int i7) {
        if (intent != null) {
            String action = intent.getAction();
            String str = this.TAG;
            Log.d(str, "service receives [" + action + "]");
            if (action == null) {
                return 2;
            }
            if (action.equals("com.samsung.android.gallery.app.service.STOP_SERVICE")) {
                onInterruptService();
                return 2;
            } else if (!action.equals("com.samsung.android.gallery.app.service.START_SERVICE")) {
                return 2;
            } else {
                onStartService(intent);
                return 2;
            }
        } else {
            Log.w(this.TAG, "unable to operate startCommand");
            return 2;
        }
    }
}
