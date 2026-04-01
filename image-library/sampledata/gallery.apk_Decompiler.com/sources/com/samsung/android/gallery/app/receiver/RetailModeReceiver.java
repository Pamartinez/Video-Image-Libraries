package com.samsung.android.gallery.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.module.retailmode.RetailModeInstaller;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RetailModeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d("RetailModeReceiver", "received action [" + action + "]");
        if (!Features.isEnabled(Features.IS_RDU_MODE)) {
            Log.w("RetailModeReceiver", "unable to handle it, not in shop demo mode");
        } else if ("com.samsung.intent.action.START_RETAILMODE_PRESET".equals(action)) {
            Log.d("RetailModeReceiver", "retail mode preset start");
            RetailModeInstaller.getInstance().setup(context, intent);
        } else if ("com.samsung.sea.rm.DEMO_RESET_STARTED".equals(action)) {
            Log.d("RetailModeReceiver", "demo reset(clear)");
            RetailModeInstaller.getInstance().demoReset(context);
        }
    }
}
