package com.samsung.android.gallery.module.service.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.NotificationClient;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseNotificationHelper {
    protected final String mClassName;
    protected boolean mIsCreated = false;
    protected final String mNotificationChannelId;
    protected NotificationClient mNotificationClient;
    private final NotificationCompat.BigTextStyle mStyle = new NotificationCompat.BigTextStyle();
    private final NotificationCompat.Builder mSummaryBuilder;

    public BaseNotificationHelper(Context context, String str, String str2) {
        this.mNotificationChannelId = str;
        this.mNotificationClient = new NotificationClient(context.getApplicationContext());
        this.mClassName = str2;
        this.mSummaryBuilder = new NotificationCompat.Builder(context, str).setSmallIcon(R$drawable.stat_notify_gallery).setColor(context.getColor(R$color.quick_panel_notification_color)).setGroup(getGroupKey()).setGroupSummary(true);
    }

    public void create(String str) {
        this.mNotificationClient.createNotificationChannel(this.mNotificationChannelId, str);
        this.mIsCreated = true;
    }

    public NotificationCompat.BigTextStyle getBigText(String str) {
        return this.mStyle.bigText(str);
    }

    public PendingIntent getBody(Context context, int i2) {
        return AndroidCompat.createActivityPendingIntent(context, i2, new Intent("android.intent.action.MAIN").setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.GalleryActivity").addCategory("android.intent.category.LAUNCHER"), 134217728);
    }

    public String getGroupKey() {
        return "gallery_group_key" + this.mNotificationChannelId;
    }

    public PendingIntent getResume(Context context, int i2, String str, String str2) {
        Intent putExtra = new Intent("com.samsung.android.gallery.app.service.START_SERVICE").setClassName("com.sec.android.gallery3d", str).putExtra("notification_id", i2).putExtra("is_download_service_resume", true).putExtra("blackboard_name", str2);
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.GalleryActivity");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addFlags(805306368);
        intent.putExtra("pending-timeout-service", putExtra);
        return AndroidCompat.createActivityPendingIntent(context, i2, intent, 134217728);
    }

    public PendingIntent getStop(Context context, int i2, String str) {
        return AndroidCompat.createServicePendingIntent(context, i2, new Intent("com.samsung.android.gallery.app.service.STOP_SERVICE").setClassName("com.sec.android.gallery3d", str).putExtra("notification_id", i2), 134217728);
    }

    public final int getSummaryId() {
        return this.mNotificationChannelId.hashCode();
    }

    public Notification getSummaryNotification() {
        return this.mSummaryBuilder.build();
    }

    public boolean isUpdatable(long j2, int i2) {
        if ((System.currentTimeMillis() - j2 <= 800 || i2 > 100) && i2 != 100) {
            return false;
        }
        return true;
    }
}
