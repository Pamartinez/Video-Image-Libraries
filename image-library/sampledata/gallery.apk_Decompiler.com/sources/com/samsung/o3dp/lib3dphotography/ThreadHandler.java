package com.samsung.o3dp.lib3dphotography;

import android.text.TextUtils;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThreadHandler {
    private static final String TAG = "ThreadHandler";
    private static final String WORKER_THREAD_NAME = "ThreadHandler-WorkerThread";
    private ExecutorService mWorkerExecutor;
    private final Object mWorkerThreadLock;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyInitializationHolder {
        /* access modifiers changed from: private */
        public static final ThreadHandler INSTANCE = new ThreadHandler(0);

        private LazyInitializationHolder() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class NamedThreadFactory implements ThreadFactory {
        private final String mName;
        private final AtomicInteger mThreadNumber = new AtomicInteger(1);

        public NamedThreadFactory(String str) {
            this.mName = TextUtils.isEmpty(str) ? Thread.currentThread().getName() : str;
        }

        public Thread newThread(Runnable runnable) {
            String str = this.mName + "-" + this.mThreadNumber.getAndIncrement();
            LogUtil.d(ThreadHandler.TAG, "newThread# mName " + str);
            return new Thread(runnable, str);
        }
    }

    public /* synthetic */ ThreadHandler(int i2) {
        this();
    }

    public static synchronized ThreadHandler getInstance() {
        ThreadHandler a7;
        synchronized (ThreadHandler.class) {
            a7 = LazyInitializationHolder.INSTANCE;
        }
        return a7;
    }

    public synchronized void release() {
        releaseWorkerThread();
    }

    public void releaseWorkerThread() {
        synchronized (this.mWorkerThreadLock) {
            try {
                ExecutorService executorService = this.mWorkerExecutor;
                if (executorService != null) {
                    executorService.shutdown();
                    this.mWorkerExecutor = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [java.lang.Object, java.lang.Runnable] */
    public void runOnWorkerThread(Runnable runnable) {
        synchronized (this.mWorkerThreadLock) {
            try {
                if (this.mWorkerExecutor == null) {
                    ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(new NamedThreadFactory(WORKER_THREAD_NAME));
                    this.mWorkerExecutor = newSingleThreadExecutor;
                    newSingleThreadExecutor.execute(new Object());
                }
                this.mWorkerExecutor.execute(runnable);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private ThreadHandler() {
        this.mWorkerThreadLock = new Object();
    }
}
