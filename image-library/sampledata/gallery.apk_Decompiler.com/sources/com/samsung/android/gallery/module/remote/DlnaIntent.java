package com.samsung.android.gallery.module.remote;

import android.content.Intent;
import android.content.IntentFilter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DlnaIntent {
    public static IntentFilter buildIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.sec.android.screensharing.DLNA_CONNECTION_REQUEST");
        intentFilter.addAction("com.sec.android.screensharing.DLNA_DISCONNECTION_REQUEST");
        intentFilter.addAction("com.samsung.intent.action.DLNA_STATUS_CHANGED");
        return intentFilter;
    }

    public static String getDeviceAddress(Intent intent) {
        if ("android.intent.action.MAIN".equals(intent.getAction())) {
            return null;
        }
        if (SConnectUtil.isSConnectIntent(intent)) {
            return SConnectUtil.getDeviceId(intent);
        }
        if ("com.sec.android.screensharing.DLNA_CONNECTION_REQUEST".equals(intent.getAction())) {
            return intent.getStringExtra("uid");
        }
        return null;
    }

    public static int getPlayerType(Intent intent) {
        if (intent == null) {
            return -1;
        }
        return intent.getIntExtra("player_type", -1);
    }

    public static int getStatus(Intent intent) {
        if (intent == null) {
            return -1;
        }
        return intent.getIntExtra("status", -1);
    }

    public static String getUid(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.getStringExtra("uid");
    }

    public static boolean isResumeRequest(Intent intent) {
        if (intent == null || !intent.getBooleanExtra("resume_request", false)) {
            return false;
        }
        return true;
    }
}
