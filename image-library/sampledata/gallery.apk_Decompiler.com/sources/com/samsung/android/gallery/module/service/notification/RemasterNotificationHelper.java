package com.samsung.android.gallery.module.service.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.AndroidCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterNotificationHelper extends BaseNotificationHelper {
    private NotificationCompat.Builder mBuilder = null;
    final int mNotificationId;

    public RemasterNotificationHelper(Context context, int i2, String str, String str2) {
        super(context, str, str2);
        this.mNotificationId = i2;
    }

    private NotificationCompat.Builder getBuilder(Context context) {
        if (this.mBuilder == null) {
            this.mBuilder = new NotificationCompat.Builder(context, this.mNotificationChannelId).setSmallIcon(R$drawable.stat_notify_gallery).setContentIntent(getBody(context, this.mNotificationId)).setColor(context.getColor(R$color.quick_panel_notification_color)).setOngoing(true).setGroup(getGroupKey()).addAction(R$drawable.mainmenu_icon_gallery, context.getString(R$string.cancel), getStop(context, this.mNotificationId, this.mClassName));
        }
        return this.mBuilder;
    }

    private PendingIntent getContent(Context context) {
        return AndroidCompat.createServicePendingIntent(context, this.mNotificationId, new Intent("com.samsung.android.gallery.app.service.CALL_ACTIVITY").setClassName("com.sec.android.gallery3d", this.mClassName).putExtra("notification_id", this.mNotificationId), 134217728);
    }

    private PendingIntent getDelete(Context context) {
        return AndroidCompat.createServicePendingIntent(context, this.mNotificationId, new Intent("com.samsung.android.gallery.app.service.DELETE_SERVICE").setClassName("com.sec.android.gallery3d", this.mClassName).putExtra("notification_id", this.mNotificationId), 134217728);
    }

    public void dismiss() {
        if (this.mIsCreated) {
            this.mNotificationClient.cancelNotification(this.mNotificationId, this.mNotificationChannelId);
        }
    }

    public void show(Context context, String str) {
        if (this.mIsCreated) {
            NotificationCompat.Builder builder = getBuilder(context);
            builder.setContentTitle(str);
            this.mNotificationClient.notifyNotification(this.mNotificationId, this.mNotificationChannelId, builder.build());
        }
    }

    public void showStopNotification(Context context, String str) {
        if (this.mIsCreated) {
            NotificationCompat.Builder builder = getBuilder(context);
            builder.setContentTitle(str);
            builder.setStyle((NotificationCompat.Style) null);
            builder.setContentIntent(getContent(context));
            builder.setDeleteIntent(getDelete(context));
            builder.setOngoing(false);
            builder.mActions.clear();
            this.mNotificationClient.notifyNotification(this.mNotificationId, this.mNotificationChannelId, builder.build());
        }
    }
}
