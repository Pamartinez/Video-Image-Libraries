package com.samsung.android.gallery.support.utils;

import B8.g;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleThreadPool implements Executor {
    protected static volatile SimpleThreadPool sInstance;
    private final ExecutorService mExecutorService = Executors.newCachedThreadPool(new SimpleThreadFactory());
    private final ThreadWatchDog mWatchDog = new ThreadWatchDog("latch");

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SimpleThreadFactory implements ThreadFactory {
        private static int sCount;

        public Thread newThread(Runnable runnable) {
            StringBuilder sb2 = new StringBuilder("latch-pool-");
            int i2 = sCount;
            sCount = i2 + 1;
            sb2.append(i2);
            Thread thread = new Thread(runnable, sb2.toString());
            thread.setPriority(10);
            return thread;
        }
    }

    private SimpleThreadPool() {
    }

    public static SimpleThreadPool getInstance() {
        if (sInstance == null) {
            synchronized (SimpleThreadPool.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new SimpleThreadPool();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$attachWatchdog$0(boolean z, Runnable runnable) {
        int hashCode = hashCode();
        this.mWatchDog.set(hashCode);
        if (z) {
            ThreadUtil.setHighPriority();
            runnable.run();
            ThreadUtil.setDefaultPriority();
        } else {
            runnable.run();
        }
        this.mWatchDog.reset(hashCode);
    }

    public Runnable attachWatchdog(Runnable runnable, boolean z) {
        return new g((Object) this, z, (Object) runnable, 11);
    }

    public void execute(Runnable runnable) {
        this.mExecutorService.execute(attachWatchdog(runnable, false));
    }

    public void executeOnPriorThread(Runnable runnable) {
        this.mExecutorService.execute(attachWatchdog(runnable, true));
    }

    public Executor getPoolExecutor() {
        return this.mExecutorService;
    }

    public Future<?> submit(Runnable runnable) {
        return this.mExecutorService.submit(attachWatchdog(runnable, false));
    }
}
