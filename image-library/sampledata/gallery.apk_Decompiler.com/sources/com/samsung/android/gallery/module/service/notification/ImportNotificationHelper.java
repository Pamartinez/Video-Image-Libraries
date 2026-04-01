package com.samsung.android.gallery.module.service.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.support.utils.AndroidCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImportNotificationHelper extends DefaultNotificationHelper {
    private final String mDeviceName;

    public ImportNotificationHelper(Context context, int i2, String str, String str2, String str3) {
        super(context, i2, str, str2);
        this.mDeviceName = str3;
    }

    public PendingIntent getStop(Context context, int i2, String str) {
        return AndroidCompat.createServicePendingIntent(context, i2, new Intent("com.samsung.android.gallery.app.service.STOP_SERVICE").setClassName("com.sec.android.gallery3d", str).putExtra("notification_id", i2).putExtra("mtp_device_name", this.mDeviceName), 134217728);
    }
}
