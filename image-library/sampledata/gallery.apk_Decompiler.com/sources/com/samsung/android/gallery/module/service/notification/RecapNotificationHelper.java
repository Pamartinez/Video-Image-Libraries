package com.samsung.android.gallery.module.service.notification;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.R$string;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapNotificationHelper extends BaseNotificationHelper {
    int mNotificationId = RecapNotificationHelper.class.hashCode();

    public RecapNotificationHelper(Context context, String str, String str2) {
        super(context, str, str2);
    }

    public int getId() {
        return this.mNotificationId;
    }

    public NotificationCompat.Builder getStartBuilder(Context context, String str) {
        String string = context.getString(R$string.creating_ps, new Object[]{str});
        return new NotificationCompat.Builder(context, this.mNotificationChannelId).setSmallIcon(R$drawable.stat_notify_gallery).setContentTitle(string).setTicker(string).setContentText(context.getString(R$string.recap_notification_body)).setContentIntent(getBody(context, this.mNotificationId)).setColor(context.getColor(R$color.quick_panel_notification_color)).setOngoing(true);
    }
}
