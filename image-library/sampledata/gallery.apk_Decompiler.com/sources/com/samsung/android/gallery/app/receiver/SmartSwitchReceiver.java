package com.samsung.android.gallery.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.module.smartswitch.SmartSwitchState;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SmartSwitchReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.i("SmartSwitchReceiver", "onReceive: " + intent.getAction());
        if ("com.samsung.android.intent.action.RESTORING_START".equalsIgnoreCase(intent.getAction())) {
            SmartSwitchState.save(SmartSwitchState.RESTORING);
            Blackboard.postEventGlobal(EventMessage.obtain(1104));
        } else if ("com.samsung.android.intent.action.RESTORING_COMPLETE".equalsIgnoreCase(intent.getAction())) {
            SmartSwitchState.save(SmartSwitchState.RESTORE_COMPLETED);
            Blackboard.postEventGlobal(EventMessage.obtain(1105));
        }
    }
}
