package com.samsung.android.gallery.support.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PendingIntentCompat {
    public static PendingIntent getActivity(Context context, int i2, Intent intent, int i7) {
        if (Build.VERSION.SDK_INT >= 31) {
            return PendingIntent.getActivity(context, i2, intent, i7 | 33554432);
        }
        return PendingIntent.getActivity(context, i2, intent, i7);
    }

    public static PendingIntent getBroadcast(Context context, int i2, Intent intent, int i7) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 34) {
            return PendingIntent.getBroadcast(context, i2, intent, i7 | 67108864);
        }
        if (i8 >= 31) {
            return PendingIntent.getBroadcast(context, i2, intent, i7 | 33554432);
        }
        return PendingIntent.getBroadcast(context, i2, intent, i7);
    }

    public static PendingIntent getService(Context context, int i2, Intent intent, int i7) {
        if (Build.VERSION.SDK_INT >= 31) {
            return PendingIntent.getService(context, i2, intent, i7 | 33554432);
        }
        return PendingIntent.getService(context, i2, intent, i7);
    }
}
