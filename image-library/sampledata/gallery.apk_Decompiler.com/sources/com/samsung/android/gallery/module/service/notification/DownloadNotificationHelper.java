package com.samsung.android.gallery.module.service.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.service.message.DownloadMsgMgr;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadNotificationHelper extends BaseNotificationHelper {
    public DownloadNotificationHelper(Context context, String str, String str2) {
        super(context, str, str2);
    }

    private PendingIntent getContent(Context context, int i2, ArrayList<Uri> arrayList, int i7) {
        Intent putExtra = new Intent("com.samsung.android.gallery.app.service.CALL_ACTIVITY").setClassName("com.sec.android.gallery3d", this.mClassName).putExtra("notification_id", i2);
        if (arrayList != null) {
            putExtra.putExtra("notification_data", arrayList);
        }
        if (i7 != 0) {
            putExtra.putExtra("ALBUM_ID", i7);
        }
        return AndroidCompat.createServicePendingIntent(context, i2, putExtra, 134217728);
    }

    private PendingIntent getDelete(Context context, int i2) {
        return AndroidCompat.createServicePendingIntent(context, i2, new Intent("com.samsung.android.gallery.app.service.DELETE_SERVICE").setClassName("com.sec.android.gallery3d", this.mClassName).putExtra("notification_id", i2), 134217728);
    }

    private NotificationCompat.Builder getStopBuilderInternal(Context context, int i2, String str, String str2, ArrayList<Uri> arrayList, Bitmap bitmap, int i7) {
        NotificationCompat.Builder group = new NotificationCompat.Builder(context, this.mNotificationChannelId).setSmallIcon(R$drawable.stat_notify_gallery).setContentIntent(getContent(context, i2, arrayList, i7)).setContentTitle(str).setColor(context.getColor(R$color.quick_panel_notification_color)).setDeleteIntent(getDelete(context, i2)).setOngoing(false).setGroup(getGroupKey());
        if (!TextUtils.isEmpty(str2)) {
            group.setStyle(new NotificationCompat.BigTextStyle().bigText(str2));
        }
        if (bitmap != null && !bitmap.isRecycled() && bitmap.getWidth() > 0 && bitmap.getHeight() > 0) {
            group.setLargeIcon(bitmap);
        }
        return group;
    }

    public void dismiss(int i2) {
        if (this.mIsCreated) {
            this.mNotificationClient.cancelNotification(i2, this.mNotificationChannelId);
        }
    }

    public NotificationCompat.Builder getServiceResumeBuilder(Context context, int i2, String str) {
        return new NotificationCompat.Builder(context, this.mNotificationChannelId).setSmallIcon(R$drawable.stat_notify_gallery).setContentTitle("Continue download service").setContentIntent(getBody(context, i2)).setColor(context.getColor(R$color.quick_panel_notification_color)).setOngoing(false).addAction(R$drawable.mainmenu_icon_gallery, context.getString(R$string.button_continue), getResume(context, i2, this.mClassName, str));
    }

    public NotificationCompat.Builder getStartBuilder(Context context, int i2) {
        return new NotificationCompat.Builder(context, this.mNotificationChannelId).setSmallIcon(R$drawable.stat_notify_gallery).setContentTitle(DownloadMsgMgr.getDownloadStartNotificationMessage(context)).setContentIntent(getBody(context, i2)).setColor(context.getColor(R$color.quick_panel_notification_color)).setOngoing(true).setGroup(getGroupKey()).addAction(R$drawable.mainmenu_icon_gallery, context.getString(R$string.cancel), getStop(context, i2, this.mClassName));
    }

    public NotificationCompat.Builder getStopBuilder(Context context, int i2, String str, String str2, ArrayList<Uri> arrayList, Bitmap bitmap) {
        return getStopBuilderInternal(context, i2, str, str2, arrayList, bitmap, -1);
    }

    public void notify(int i2, Notification notification) {
        if (this.mIsCreated) {
            this.mNotificationClient.notifyNotification(i2, this.mNotificationChannelId, notification);
        }
    }

    public NotificationCompat.Builder getStopBuilder(Context context, int i2, String str, String str2, int i7) {
        return getStopBuilderInternal(context, i2, str, str2, (ArrayList<Uri>) null, (Bitmap) null, i7);
    }
}
