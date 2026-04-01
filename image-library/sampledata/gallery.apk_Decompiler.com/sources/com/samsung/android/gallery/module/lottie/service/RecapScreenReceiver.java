package com.samsung.android.gallery.module.lottie.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.work.WorkManager;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Log;
import java.util.UUID;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapScreenReceiver extends BroadcastReceiver {
    private static final CharSequence TAG = "RecapScreenReceiver";
    UUID mId;

    public void onReceive(Context context, Intent intent) {
        Log.i("RecapScreenReceiver", "onReceive : " + this.mId);
        if ("android.intent.action.SCREEN_ON".equals(intent.getAction()) && this.mId != null) {
            try {
                WorkManager.getInstance(context).cancelWorkById(this.mId);
                AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_NONE.toString(), AnalyticsEventId.EVENT_DEV_RECAP_VIDEO_CREATION_CANCEL.toString(), "scr");
            } catch (Exception unused) {
                CharSequence charSequence = TAG;
                Log.e(charSequence, "cancelWorkById fail : " + this.mId);
                WorkManager.getInstance(context).cancelAllWorkByTag("RecapWorker");
                AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_NONE.toString(), AnalyticsEventId.EVENT_DEV_RECAP_VIDEO_CREATION_CANCEL.toString(), "scrAll");
            }
        }
    }

    public void register(Context context, UUID uuid) {
        this.mId = uuid;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        AndroidCompat.registerSystemReceiver(context, this, intentFilter);
    }

    public synchronized void unregister(Context context) {
        if (this.mId != null) {
            AndroidCompat.unregisterReceiver(context, this);
            this.mId = null;
        }
    }
}
