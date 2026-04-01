package com.samsung.android.sdk.scs.base.connection;

import android.content.Context;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.feature.FeatureStatusCache;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ProviderExecutor extends ThreadPoolExecutor implements AutoCloseable {
    private static final String TAG = "ScsApi@ProviderExecutor";
    private final Context context;
    private final AtomicInteger taskCount = new AtomicInteger(0);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProviderExecutor(Context context2, int i2, int i7, long j2, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        super(i2, i7, j2, timeUnit, blockingQueue);
        allowCoreThreadTimeOut(true);
        this.context = context2;
        Log.d(TAG, "ProviderExecutor constructor()");
    }

    public void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        this.taskCount.getAndDecrement();
        Log.d(TAG, "afterExecute(). mTaskCount: " + this.taskCount);
    }

    public void beforeExecute(Thread thread, Runnable runnable) {
        super.beforeExecute(thread, runnable);
        Log.objectInfo(TAG, "task", this, runnable);
        if (runnable instanceof TaskRunnable) {
            String featureName = ((TaskRunnable) runnable).getFeatureName();
            int status = FeatureStatusCache.getStatus(featureName);
            if (status == -1000 || status == 2000) {
                int checkFeature = Feature.checkFeature(this.context, featureName);
                Log.d(TAG, "beforeExecute(). First check for " + featureName + ". status: " + checkFeature);
            }
        } else {
            Log.e(TAG, "Unexpected runnable!!!!");
        }
        this.taskCount.getAndIncrement();
        Log.d(TAG, "beforeExecute(). mTaskCount: " + this.taskCount);
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
}
