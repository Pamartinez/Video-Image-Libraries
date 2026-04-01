package com.samsung.android.sdk.bixby2.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.sdk.bixby2.LogUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ApplicationTriggerReceiver extends BroadcastReceiver {
    private static final String TAG = "ApplicationTriggerReceiver";

    public void onReceive(Context context, Intent intent) {
        LogUtil.i(TAG, "onReceived()");
        if (context != null) {
            context.unregisterReceiver(this);
            LogUtil.i(TAG, "ApplicationTriggerReceiver unRegistered");
        }
    }
}
