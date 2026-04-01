package com.samsung.android.gallery.module.service.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.samsung.android.gallery.support.utils.AndroidCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaCaptureNotificationHelper extends DefaultNotificationHelper {
    public MediaCaptureNotificationHelper(Context context, int i2, String str, String str2) {
        super(context, i2, str, str2);
    }

    private PendingIntent getContent(Context context) {
        return AndroidCompat.createServicePendingIntent(context, this.mNotificationId, new Intent("com.samsung.android.gallery.app.service.CALL_ACTIVITY").setClassName("com.sec.android.gallery3d", this.mClassName).putExtra("notification_id", this.mNotificationId), 134217728);
    }

    private PendingIntent getDelete(Context context) {
        return AndroidCompat.createServicePendingIntent(context, this.mNotificationId, new Intent("com.samsung.android.gallery.app.service.DELETE_SERVICE").setClassName("com.sec.android.gallery3d", this.mClassName).putExtra("notification_id", this.mNotificationId), 134217728);
    }

    public void showStopNotification(Context context, String str, String str2) {
        if (this.mIsCreated) {
            NotificationCompat.Builder builder = getBuilder(context);
            builder.setContentTitle(str);
            builder.setContentText(str2);
            builder.setStyle((NotificationCompat.Style) null);
            builder.setContentIntent(getContent(context));
            builder.setDeleteIntent(getDelete(context));
            builder.setProgress(0, 0, false);
            builder.setOngoing(false);
            builder.mActions.clear();
            this.mNotificationClient.notifyNotification(this.mNotificationId, this.mNotificationChannelId, builder.build());
        }
    }
}
