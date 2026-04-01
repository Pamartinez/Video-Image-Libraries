package com.samsung.android.gallery.support.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NotificationClient {
    private NotificationChannel mChannel;
    private final NotificationManager mManager;

    public NotificationClient(Context context) {
        this.mManager = (NotificationManager) context.getSystemService("notification");
    }

    public void cancelNotification(int i2) {
        NotificationManager notificationManager = this.mManager;
        if (notificationManager != null) {
            notificationManager.cancel(i2);
        }
    }

    public void createNotificationChannel(String str, CharSequence charSequence) {
        if (this.mManager != null) {
            NotificationChannel notificationChannel = new NotificationChannel(str, charSequence, 2);
            this.mChannel = notificationChannel;
            this.mManager.createNotificationChannel(notificationChannel);
        }
    }

    public void deleteNotificationChannel(String str) {
        NotificationManager notificationManager = this.mManager;
        if (notificationManager != null) {
            notificationManager.deleteNotificationChannel(str);
        }
    }

    public void notifyNotification(int i2, Notification notification) {
        NotificationManager notificationManager = this.mManager;
        if (notificationManager != null) {
            notificationManager.notify(i2, notification);
        }
    }

    public void setChannelName(String str) {
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel != null) {
            notificationChannel.setName(str);
        }
    }

    public void cancelNotification(int i2, String str) {
        NotificationManager notificationManager = this.mManager;
        if (notificationManager != null) {
            notificationManager.cancel(str, i2);
        }
    }

    public void notifyNotification(int i2, String str, Notification notification) {
        NotificationManager notificationManager = this.mManager;
        if (notificationManager != null) {
            notificationManager.notify(str, i2, notification);
        }
    }
}
