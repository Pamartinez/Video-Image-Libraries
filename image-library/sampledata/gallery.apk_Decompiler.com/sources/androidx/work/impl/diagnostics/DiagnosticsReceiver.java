package androidx.work.impl.diagnostics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.Logger;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.impl.workers.DiagnosticsWorker;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DiagnosticsReceiver extends BroadcastReceiver {
    private static final String TAG = Logger.tagWithPrefix("DiagnosticsRcvr");

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            Logger.get().debug(TAG, "Requesting diagnostics");
            try {
                WorkManager.getInstance(context).enqueue((WorkRequest) OneTimeWorkRequest.from(DiagnosticsWorker.class));
            } catch (IllegalStateException e) {
                Logger.get().error(TAG, "WorkManager is not initialized", e);
            }
        }
    }
}
