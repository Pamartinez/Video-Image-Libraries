package com.samsung.android.sdk.scs.ai.downloader;

import android.content.Context;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.feature.FeatureStatusCache;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ModelDownloaderSequentialExecutor extends ThreadPoolExecutor implements AutoCloseable {
    private static final String TAG = "ModelDownloaderSequentialExecutor";
    private final Context mContext;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ModelDownloaderSequentialExecutor(Context context) {
        super(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingDeque());
        allowCoreThreadTimeOut(true);
        this.mContext = context.getApplicationContext();
    }

    public void beforeExecute(Thread thread, Runnable runnable) {
        super.beforeExecute(thread, runnable);
        if (runnable instanceof TaskRunnable) {
            String featureName = ((TaskRunnable) runnable).getFeatureName();
            if (FeatureStatusCache.getStatus(featureName) == -1000) {
                int checkFeature = Feature.checkFeature(this.mContext, featureName);
                String str = TAG;
                Log.d(str, "beforeExecute: check feature: " + featureName + " status: " + checkFeature);
            }
        }
    }

    public final /* synthetic */ void close() {
        boolean isTerminated;
        if (this != ForkJoinPool.commonPool() && !(isTerminated = isTerminated())) {
            shutdown();
            boolean z = false;
            while (!isTerminated) {
                try {
                    isTerminated = awaitTermination(1, TimeUnit.DAYS);
                } catch (InterruptedException unused) {
                    if (!z) {
                        shutdownNow();
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void finalize() {
        super.finalize();
        Log.d(TAG, "finalize");
    }
}
