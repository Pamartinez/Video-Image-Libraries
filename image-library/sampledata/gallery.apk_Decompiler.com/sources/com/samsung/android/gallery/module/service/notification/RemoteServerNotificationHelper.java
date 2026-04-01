package com.samsung.android.gallery.module.service.notification;

import android.app.Notification;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.remotegallery.RemoteWebServer;
import com.samsung.android.gallery.support.utils.Utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoteServerNotificationHelper extends BaseNotificationHelper {
    int mNotificationId = RemoteServerNotificationHelper.class.hashCode();

    public RemoteServerNotificationHelper(Context context, String str, String str2) {
        super(context, str, str2);
    }

    public void dismiss() {
        if (this.mIsCreated) {
            this.mNotificationClient.cancelNotification(this.mNotificationId, this.mNotificationChannelId);
        }
    }

    public NotificationCompat.Builder getStartBuilder(Context context) {
        String wifiAddress = Utils.getWifiAddress();
        NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(context, this.mNotificationChannelId).setSmallIcon(R$drawable.stat_notify_gallery);
        return smallIcon.setContentTitle("Remote Gallery Server : " + wifiAddress).setContentIntent(getBody(context, this.mNotificationId)).setLargeIcon(RemoteWebServer.getQr(wifiAddress)).setColor(context.getColor(R$color.quick_panel_notification_color)).setOngoing(true).setGroup(getGroupKey()).addAction(R$drawable.mainmenu_icon_gallery, context.getString(R$string.stop), getStop(context, this.mNotificationId, this.mClassName));
    }

    public void notify(Notification notification) {
        if (this.mIsCreated) {
            this.mNotificationClient.notifyNotification(this.mNotificationId, this.mNotificationChannelId, notification);
        }
    }
}
