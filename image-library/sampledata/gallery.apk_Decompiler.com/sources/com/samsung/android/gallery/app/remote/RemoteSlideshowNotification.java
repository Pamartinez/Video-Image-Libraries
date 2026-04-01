package com.samsung.android.gallery.app.remote;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.NotificationClient;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RemoteSlideshowNotification {
    static final int OPERATION_ID = 1887361735;
    private NotificationCompat.Builder mBuilder;
    private NotificationClient mClient;
    private String mDisplayName;

    private RemoteViews createCustomRemoteViews(Context context, boolean z) {
        PendingIntent pendingIntent;
        int i2;
        int i7;
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.quick_panel_smartview_slideshow_noti);
        String str = this.mDisplayName;
        if (str != null) {
            remoteViews.setTextViewText(R.id.quick_panel_slideshow_device_name, str);
        }
        if (z) {
            pendingIntent = getPauseIntent(context);
        } else {
            pendingIntent = getResumeIntent(context);
        }
        if (z) {
            i2 = R.drawable.sound_pause;
        } else {
            i2 = R.drawable.sound_play;
        }
        if (z) {
            i7 = R.string.pause;
        } else {
            i7 = R.string.play;
        }
        remoteViews.setOnClickPendingIntent(R.id.remote_play_button, pendingIntent);
        remoteViews.setContentDescription(R.id.remote_play_button, context.getString(i7));
        remoteViews.setImageViewResource(R.id.remote_play_button, i2);
        remoteViews.setOnClickPendingIntent(R.id.remote_cancel_button, getStopIntent(context));
        return remoteViews;
    }

    private NotificationCompat.Builder createNotificationBuilder(Context context, boolean z) {
        return new NotificationCompat.Builder(context, "SlideshowService").setOngoing(true).setDeleteIntent(getStopIntent(context)).setTicker(context.getText(R.string.app_name)).setSmallIcon(R.drawable.stat_notify_gallery).setColor(context.getColor(R.color.quick_panel_notification_color)).setContentTitle(context.getText(R.string.app_name)).setStyle(new NotificationCompat.DecoratedCustomViewStyle()).setCustomBigContentView(createCustomRemoteViews(context, z));
    }

    private PendingIntent getPauseIntent(Context context) {
        return AndroidCompat.createServicePendingIntent(context, OPERATION_ID, new Intent(context, SlideshowServiceOnRemoteV2.class).setAction("com.samsung.android.gallery.app.service.SLIDESHOW_PAUSE_SERVICE"), 134217728);
    }

    private PendingIntent getResumeIntent(Context context) {
        return AndroidCompat.createServicePendingIntent(context, OPERATION_ID, new Intent(context, SlideshowServiceOnRemoteV2.class).setAction("com.samsung.android.gallery.app.service.SLIDESHOW_RESUME_SERVICE"), 134217728);
    }

    private PendingIntent getStopIntent(Context context) {
        return AndroidCompat.createServicePendingIntent(context, OPERATION_ID, new Intent(context, SlideshowServiceOnRemoteV2.class).setAction("com.samsung.android.gallery.app.service.SLIDESHOW_STOP_SERVICE"), 134217728);
    }

    public void cancel() {
        NotificationClient notificationClient = this.mClient;
        if (notificationClient != null) {
            notificationClient.cancelNotification(OPERATION_ID);
        }
    }

    public Notification create(Context context) {
        NotificationClient notificationClient = new NotificationClient(context);
        this.mClient = notificationClient;
        notificationClient.createNotificationChannel("SlideshowService", "SlideshowService");
        NotificationCompat.Builder createNotificationBuilder = createNotificationBuilder(context, false);
        this.mBuilder = createNotificationBuilder;
        return createNotificationBuilder.build();
    }

    public void pause(Context context) {
        if (this.mClient != null) {
            this.mBuilder = createNotificationBuilder(context, false);
            show();
        }
    }

    public void resume(Context context) {
        if (this.mClient != null) {
            this.mBuilder = createNotificationBuilder(context, true);
            show();
        }
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public void show() {
        NotificationCompat.Builder builder;
        NotificationClient notificationClient = this.mClient;
        if (notificationClient != null && (builder = this.mBuilder) != null) {
            notificationClient.notifyNotification(OPERATION_ID, builder.build());
        }
    }
}
