package androidx.arch.core.executor;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultTaskExecutor extends TaskExecutor {
    private final ExecutorService mDiskIO = Executors.newFixedThreadPool(4, new ThreadFactory() {
        private final AtomicInteger mThreadId = new AtomicInteger(0);

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("arch_disk_io_" + this.mThreadId.getAndIncrement());
            return thread;
        }
    });
    private final Object mLock = new Object();
    private volatile Handler mMainHandler;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api28Impl {
        public static Handler createAsync(Looper looper) {
            return Handler.createAsync(looper);
        }
    }

    private static Handler createAsync(Looper looper) {
        return Api28Impl.createAsync(looper);
    }

    public void executeOnDiskIO(Runnable runnable) {
        this.mDiskIO.execute(runnable);
    }

    public boolean isMainThread() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return true;
        }
        return false;
    }

    public void postToMainThread(Runnable runnable) {
        if (this.mMainHandler == null) {
            synchronized (this.mLock) {
                try {
                    if (this.mMainHandler == null) {
                        this.mMainHandler = createAsync(Looper.getMainLooper());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        this.mMainHandler.post(runnable);
    }
}
