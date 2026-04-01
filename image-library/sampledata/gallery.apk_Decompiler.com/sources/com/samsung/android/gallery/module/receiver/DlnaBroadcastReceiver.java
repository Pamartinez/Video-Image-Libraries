package com.samsung.android.gallery.module.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.samsung.android.gallery.module.remote.DlnaIntent;
import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DlnaBroadcastReceiver extends AbsBroadcastReceiver {
    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onReceive$0(Intent intent) {
        String deviceAddress = DlnaIntent.getDeviceAddress(intent);
        Blackboard applicationInstance = Blackboard.getApplicationInstance();
        if (deviceAddress == null) {
            deviceAddress = "";
        }
        applicationInstance.publish("event/dlnaConnectReqStateChanged", deviceAddress);
    }

    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            Log.d(this.TAG, "onReceive failed intent is null.");
            return;
        }
        String action = intent.getAction();
        boolean isResumeRequest = DlnaIntent.isResumeRequest(intent);
        int playerType = DlnaIntent.getPlayerType(intent);
        int status = DlnaIntent.getStatus(intent);
        Log.d(this.TAG, "onReceive", action, Boolean.valueOf(isResumeRequest), Integer.valueOf(status));
        if ("com.sec.android.screensharing.DLNA_CONNECTION_REQUEST".equals(action)) {
            if (playerType != 1) {
                String str = this.TAG;
                Log.rme(str, "onReceive failed. wrong playerType " + playerType);
            } else if (DlnaIntent.getUid(intent) == null) {
                Log.rme(this.TAG, "onReceive failed. device id is null.");
            } else {
                ThreadUtil.postOnBgThreadDelayed(new c(intent), 500);
            }
        } else if ("com.sec.android.screensharing.DLNA_DISCONNECTION_REQUEST".equals(action)) {
            Blackboard.getApplicationInstance().publish("event/dlnaConnectReqStateChanged", (Object) null);
        } else if ("com.samsung.intent.action.DLNA_STATUS_CHANGED".equals(action)) {
            Blackboard.getApplicationInstance().publish("event/dlnaStateChanged", Integer.valueOf(status));
            if (status == 1) {
                RemoteDisplayState.getInstance().onDlnaConnected();
            } else if (status == 0) {
                RemoteDisplayState.getInstance().onDlnaDisconnected();
            }
        }
    }

    public void registerReceiver(Context context) {
        AndroidCompat.registerReceiver(context, this, DlnaIntent.buildIntentFilter(), "android.permission.CONFIGURE_WIFI_DISPLAY", (Handler) null);
    }
}
