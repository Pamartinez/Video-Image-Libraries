package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.Logger;
import androidx.work.impl.WorkManagerImpl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RescheduleReceiver extends BroadcastReceiver {
    private static final String TAG = Logger.tagWithPrefix("RescheduleReceiver");

    public void onReceive(Context context, Intent intent) {
        Logger logger = Logger.get();
        String str = TAG;
        logger.debug(str, "Received intent " + intent);
        try {
            WorkManagerImpl.getInstance(context).setReschedulePendingResult(goAsync());
        } catch (IllegalStateException e) {
            Logger.get().error(TAG, "Cannot reschedule jobs. WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", e);
        }
    }
}
