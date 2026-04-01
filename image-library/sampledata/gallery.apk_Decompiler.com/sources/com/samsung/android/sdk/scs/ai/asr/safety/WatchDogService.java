package com.samsung.android.sdk.scs.ai.asr.safety;

import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class WatchDogService {
    private static final long CHECK_INTERVAL;
    private static final String TAG = "WatchDogService";
    static final ScheduledThreadPoolExecutor TIMER;
    private static final long WATCHDOG_MAX_ALIVE_TIME = TimeUnit.HOURS.toMillis(6);
    private static final AtomicBoolean mIsObserving = new AtomicBoolean(false);
    private static final AtomicLong sThreadCnt = new AtomicLong(0);
    static final Map<Thread, WatchDog> watchDogHolders = new ConcurrentHashMap();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RepeatableTask {
        private static final AtomicLong mCounting = new AtomicLong(0);
        private final Runnable mExec;
        private final ScheduledExecutorService mExecutor;
        private final long mPeriodInMs;
        private final Runnable mPost;
        private final Runnable mPre;
        private final BooleanSupplier mUntil;

        public /* synthetic */ RepeatableTask(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, d dVar, d dVar2, long j2, e eVar) {
            this(scheduledThreadPoolExecutor, (Runnable) null, dVar, dVar2, j2, eVar);
        }

        private synchronized void close() {
            try {
                Runnable runnable = this.mPost;
                if (runnable != null) {
                    runnable.run();
                }
                Log.i(WatchDogService.TAG, "dec- " + mCounting.decrementAndGet());
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }

        /* access modifiers changed from: private */
        public void exec() {
            Runnable runnable = this.mPre;
            if (runnable != null) {
                runnable.run();
            }
            iteration();
        }

        private boolean hasNext() {
            return this.mUntil.getAsBoolean();
        }

        /* access modifiers changed from: private */
        public void iteration() {
            try {
                this.mExec.run();
                if (hasNext()) {
                    this.mExecutor.schedule(new f(this, 1), this.mPeriodInMs, TimeUnit.MILLISECONDS);
                } else {
                    close();
                }
            } catch (Exception e) {
                Log.e(WatchDogService.TAG, "error on iteration. so finish iteration.", e);
                close();
            }
        }

        private RepeatableTask(ScheduledExecutorService scheduledExecutorService, Runnable runnable, Runnable runnable2, Runnable runnable3, long j2, BooleanSupplier booleanSupplier) {
            this.mExecutor = scheduledExecutorService;
            this.mPre = runnable;
            this.mExec = runnable2;
            this.mPost = runnable3;
            this.mPeriodInMs = j2;
            this.mUntil = booleanSupplier;
            Log.i(WatchDogService.TAG, "inc+ " + mCounting.incrementAndGet() + " with interval: " + j2);
        }
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [java.lang.Object, java.util.concurrent.ThreadFactory] */
    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        CHECK_INTERVAL = timeUnit.toMillis(1);
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2, new Object());
        TIMER = scheduledThreadPoolExecutor;
        scheduledThreadPoolExecutor.setKeepAliveTime(30, timeUnit);
        scheduledThreadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    private static void checkState() {
        Map<Thread, WatchDog> map = watchDogHolders;
        if (!map.isEmpty()) {
            Log.i(TAG, "checkState " + map.size());
            for (Map.Entry next : map.entrySet()) {
                try {
                    Log.i(TAG, next.getKey() + " : " + next.getValue());
                } catch (Exception unused) {
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static void checkWatchDog() {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<Map.Entry<Thread, WatchDog>> it = watchDogHolders.entrySet().iterator();
        while (it.hasNext()) {
            try {
                WatchDog watchDog = (WatchDog) it.next().getValue();
                if (watchDog != null) {
                    if (currentTimeMillis - watchDog.getCreateTime() > WATCHDOG_MAX_ALIVE_TIME) {
                        it.remove();
                        Log.e(TAG, "remove watchdog with expired " + watchDog);
                    } else {
                        watchDog.executeWatchDog();
                    }
                }
            } catch (Exception e) {
                if (WatchDog.isDevelop) {
                    Log.e(TAG, "error to handle watch dog", e);
                }
            }
        }
    }

    public static WatchDog create(boolean z) {
        return watchDogHolders.computeIfAbsent(Thread.currentThread(), new i(z));
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.util.function.Predicate, java.lang.Object] */
    /* access modifiers changed from: private */
    public static boolean isWatching() {
        return watchDogHolders.values().stream().anyMatch(new Object());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ WatchDog lambda$create$0(boolean z, Thread thread) {
        return new WatchDogImpl(z, thread);
    }

    /* access modifiers changed from: private */
    public static void onStopSchedule() {
        Log.i(TAG, "onStopSchedule " + watchDogHolders.size());
        mIsObserving.set(false);
    }

    public static void remove(Thread thread) {
        WatchDog remove;
        if (!(thread == null || (remove = watchDogHolders.remove(thread)) == null)) {
            Log.i(TAG, "Remove watchdog on " + thread + " = " + remove);
        }
        checkState();
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [java.lang.Object, com.samsung.android.sdk.scs.ai.asr.safety.e] */
    public static void triggerSchedule() {
        if (mIsObserving.compareAndSet(false, true)) {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = TIMER;
            scheduledThreadPoolExecutor.execute(new f(new RepeatableTask(scheduledThreadPoolExecutor, new d(0), new d(1), CHECK_INTERVAL, new Object()), 0));
        }
    }

    public static Thread watchdogThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.setName("WatchDog#" + sThreadCnt.getAndIncrement());
        return thread;
    }
}
