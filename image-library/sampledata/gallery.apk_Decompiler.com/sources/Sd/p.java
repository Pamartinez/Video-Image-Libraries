package Sd;

import android.content.Context;
import android.util.Log;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.samsung.scloud.gallery.SCGalleryLogWorker;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class p {

    /* renamed from: a  reason: collision with root package name */
    public static volatile boolean f3714a = false;

    public static void a(Context context) {
        try {
            if (f3714a) {
                Log.d("[SCG-SDK][0.0.0019][SCGalleryLogScheduler]", "start: Worker already scheduled, skipping");
                return;
            }
            WorkManager.getInstance(context).enqueueUniquePeriodicWork("SCGalleryLogWorker", ExistingPeriodicWorkPolicy.KEEP, (PeriodicWorkRequest) new PeriodicWorkRequest.Builder(SCGalleryLogWorker.class, 24, TimeUnit.HOURS).build());
            f3714a = true;
            Log.i("[SCG-SDK][0.0.0019][SCGalleryLogScheduler]", "start: Worker scheduled successfully (interval: 24 hours)");
        } catch (Exception e) {
            Log.e("[SCG-SDK][0.0.0019][SCGalleryLogScheduler]", "start: exception occurred", e);
        }
    }
}
