package com.samsung.android.gallery.module.service.notification;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultNotificationHelper extends BaseNotificationHelper {
    private NotificationCompat.Builder mBuilder = null;
    private boolean mIsShowing = false;
    final int mNotificationId;
    private long mNotifyTime = -1;

    public DefaultNotificationHelper(Context context, int i2, String str, String str2) {
        super(context, str, str2);
        this.mNotificationId = i2;
    }

    public void destroy() {
        dismiss();
        this.mNotificationClient.deleteNotificationChannel(this.mNotificationChannelId);
    }

    public void dismiss() {
        this.mIsShowing = false;
        if (this.mIsCreated) {
            this.mNotificationClient.cancelNotification(this.mNotificationId, this.mNotificationChannelId);
        }
        this.mIsCreated = false;
    }

    public NotificationCompat.Builder getBuilder(Context context) {
        if (this.mBuilder == null) {
            this.mBuilder = new NotificationCompat.Builder(context, this.mNotificationChannelId).setSmallIcon(R$drawable.stat_notify_gallery).setContentIntent(getBody(context, this.mNotificationId)).setColor(context.getColor(R$color.quick_panel_notification_color)).setOngoing(true).setGroup(getGroupKey()).addAction(R$drawable.mainmenu_icon_gallery, context.getString(R$string.cancel), getStop(context, this.mNotificationId, this.mClassName));
        }
        return this.mBuilder;
    }

    public void show(Context context) {
        if (this.mIsCreated) {
            NotificationCompat.Builder builder = getBuilder(context);
            builder.setContentTitle(context.getString(R$string.preparing)).setProgress(100, 0, false);
            this.mNotificationClient.notifyNotification(this.mNotificationId, this.mNotificationChannelId, builder.build());
            this.mIsShowing = true;
        }
    }

    public void update(Context context, int i2, String str, String str2) {
        if (this.mIsCreated && this.mIsShowing && isUpdatable(this.mNotifyTime, i2)) {
            this.mNotifyTime = System.currentTimeMillis();
            NotificationCompat.Builder builder = getBuilder(context);
            builder.setContentTitle(str).setStyle(getBigText(str2)).setProgress(100, i2, false);
            this.mNotificationClient.notifyNotification(this.mNotificationId, this.mNotificationChannelId, builder.build());
        }
    }

    public void updateChannelName(String str) {
        this.mNotificationClient.setChannelName(str);
    }
}
