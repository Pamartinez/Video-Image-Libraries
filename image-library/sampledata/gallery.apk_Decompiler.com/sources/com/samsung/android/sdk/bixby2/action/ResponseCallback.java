package com.samsung.android.sdk.bixby2.action;

import android.app.PendingIntent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ResponseCallback {
    void onComplete(String str);

    void onComplete(String str, PendingIntent pendingIntent);
}
