package androidx.work.impl;

import android.os.Handler;
import android.os.Looper;
import androidx.core.os.HandlerCompat;
import androidx.work.RunnableScheduler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultRunnableScheduler implements RunnableScheduler {
    private final Handler mHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    public void cancel(Runnable runnable) {
        this.mHandler.removeCallbacks(runnable);
    }

    public void scheduleWithDelay(long j2, Runnable runnable) {
        this.mHandler.postDelayed(runnable, j2);
    }
}
