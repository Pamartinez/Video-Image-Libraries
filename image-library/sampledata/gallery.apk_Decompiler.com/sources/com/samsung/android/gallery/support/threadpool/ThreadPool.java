package com.samsung.android.gallery.support.threadpool;

import Ka.b;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadExceptionHandler;
import com.samsung.android.gallery.support.utils.ThreadWatchDog;
import i.C0212a;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThreadPool {
    /* access modifiers changed from: private */
    public static final HashMap<String, ThreadExceptionHandler> sExceptionHandlerMap;
    private static final ThreadPool sInstance = new ThreadPool("");
    private final int mCorePoolSize;
    private final ThreadPoolExecutor mExecutor;
    /* access modifiers changed from: private */
    public final ResourceCounter mResourceCounter;
    /* access modifiers changed from: private */
    public ThreadWatchDog mWatchDog;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Job<T> {
        T run(JobContext jobContext);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface JobContext {
        boolean isCancelled();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ResourceCounter {
        int value;

        public ResourceCounter(int i2) {
            this.value = i2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class Worker<T> implements Runnable, Future<T>, JobContext {
        private volatile boolean mIsCancelled;
        private boolean mIsDone;
        /* access modifiers changed from: private */
        public final Job<T> mJob;
        private FutureListener<T> mListener;
        private int mMode;
        private T mResult;
        private StackTraceElement[] mStackTraceElements;
        private ResourceCounter mWaitOnResource;

        public Worker(Job<T> job, FutureListener<T> futureListener) {
            this.mJob = job;
            this.mListener = futureListener;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(3:29|30|31) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0010, code lost:
            r0 = com.samsung.android.gallery.support.threadpool.ThreadPool.d(r4.this$0, r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
            r2 = r5.value;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0019, code lost:
            if (r2 <= 0) goto L_0x002a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x001b, code lost:
            r5.value = r2 - 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x001f, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0020, code lost:
            monitor-enter(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
            r4.mWaitOnResource = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0023, code lost:
            monitor-exit(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0024, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
            r5.wait();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x002d */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean acquireResource(com.samsung.android.gallery.support.threadpool.ThreadPool.ResourceCounter r5) {
            /*
                r4 = this;
            L_0x0000:
                monitor-enter(r4)
                boolean r0 = r4.mIsCancelled     // Catch:{ all -> 0x000b }
                r1 = 0
                if (r0 == 0) goto L_0x000d
                r4.mWaitOnResource = r1     // Catch:{ all -> 0x000b }
                monitor-exit(r4)     // Catch:{ all -> 0x000b }
                r4 = 0
                return r4
            L_0x000b:
                r5 = move-exception
                goto L_0x0031
            L_0x000d:
                r4.mWaitOnResource = r5     // Catch:{ all -> 0x000b }
                monitor-exit(r4)     // Catch:{ all -> 0x000b }
                com.samsung.android.gallery.support.threadpool.ThreadPool r0 = com.samsung.android.gallery.support.threadpool.ThreadPool.this
                com.samsung.android.gallery.support.threadpool.ThreadPool$ResourceCounter r0 = r0.getLockWithValidation(r5)
                monitor-enter(r0)
                int r2 = r5.value     // Catch:{ all -> 0x0028 }
                if (r2 <= 0) goto L_0x002a
                r3 = 1
                int r2 = r2 - r3
                r5.value = r2     // Catch:{ all -> 0x0028 }
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                monitor-enter(r4)
                r4.mWaitOnResource = r1     // Catch:{ all -> 0x0025 }
                monitor-exit(r4)     // Catch:{ all -> 0x0025 }
                return r3
            L_0x0025:
                r5 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x0025 }
                throw r5
            L_0x0028:
                r4 = move-exception
                goto L_0x002f
            L_0x002a:
                r5.wait()     // Catch:{ InterruptedException -> 0x002d }
            L_0x002d:
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                goto L_0x0000
            L_0x002f:
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                throw r4
            L_0x0031:
                monitor-exit(r4)     // Catch:{ all -> 0x000b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.threadpool.ThreadPool.Worker.acquireResource(com.samsung.android.gallery.support.threadpool.ThreadPool$ResourceCounter):boolean");
        }

        private ResourceCounter getResourceCounter(int i2) {
            if (i2 == 0) {
                return null;
            }
            return ThreadPool.this.mResourceCounter;
        }

        private void releaseResource(ResourceCounter resourceCounter) {
            synchronized (ThreadPool.this.getLockWithValidation(resourceCounter)) {
                resourceCounter.value++;
                resourceCounter.notifyAll();
            }
        }

        /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
            java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
            	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
            	at java.util.ArrayList.get(ArrayList.java:435)
            	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
            	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
            	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
            	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
            */
        public synchronized void cancel() {
            /*
                r1 = this;
                monitor-enter(r1)
                boolean r0 = r1.mIsCancelled     // Catch:{ all -> 0x0019 }
                if (r0 == 0) goto L_0x0007
                monitor-exit(r1)
                return
            L_0x0007:
                r0 = 1
                r1.mIsCancelled = r0     // Catch:{ all -> 0x0019 }
                com.samsung.android.gallery.support.threadpool.ThreadPool$ResourceCounter r0 = r1.mWaitOnResource     // Catch:{ all -> 0x0019 }
                if (r0 == 0) goto L_0x001b
                monitor-enter(r1)     // Catch:{ all -> 0x0019 }
                com.samsung.android.gallery.support.threadpool.ThreadPool$ResourceCounter r0 = r1.mWaitOnResource     // Catch:{ all -> 0x0016 }
                r0.notifyAll()     // Catch:{ all -> 0x0016 }
                monitor-exit(r1)     // Catch:{ all -> 0x0016 }
                goto L_0x001b
            L_0x0016:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0016 }
                throw r0     // Catch:{ all -> 0x0019 }
            L_0x0019:
                r0 = move-exception
                goto L_0x001d
            L_0x001b:
                monitor-exit(r1)
                return
            L_0x001d:
                monitor-exit(r1)     // Catch:{ all -> 0x0019 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.threadpool.ThreadPool.Worker.cancel():void");
        }

        public synchronized T get() {
            while (!this.mIsDone) {
                try {
                    wait();
                } catch (Exception unused) {
                    Log.w("ThreadPool", "ignore exception ");
                }
            }
            return this.mResult;
        }

        public synchronized boolean isCancelled() {
            return this.mIsCancelled;
        }

        public void run() {
            T t;
            if (ThreadPool.sExceptionHandlerMap != null) {
                String name = Thread.currentThread().getName();
                ThreadExceptionHandler threadExceptionHandler = (ThreadExceptionHandler) ThreadPool.sExceptionHandlerMap.get(name);
                if (threadExceptionHandler == null) {
                    threadExceptionHandler = new ThreadExceptionHandler();
                    ThreadPool.sExceptionHandlerMap.put(name, threadExceptionHandler);
                    Thread.currentThread().setUncaughtExceptionHandler(threadExceptionHandler);
                }
                threadExceptionHandler.saveCallTimeStack(this.mStackTraceElements);
            }
            if (setMode(1)) {
                t = this.mJob.run(this);
            } else {
                t = null;
            }
            if (ThreadPool.this.mWatchDog != null) {
                ThreadPool.this.mWatchDog.reset(this.mJob.hashCode());
            }
            synchronized (this) {
                setMode(0);
                this.mResult = t;
                this.mIsDone = true;
                notifyAll();
            }
            FutureListener<T> futureListener = this.mListener;
            if (futureListener != null) {
                futureListener.onFutureDone(this);
            }
        }

        public boolean setMode(int i2) {
            ResourceCounter resourceCounter = getResourceCounter(this.mMode);
            if (resourceCounter != null) {
                releaseResource(resourceCounter);
            }
            this.mMode = 0;
            ResourceCounter resourceCounter2 = getResourceCounter(i2);
            if (resourceCounter2 == null) {
                return true;
            }
            if (!acquireResource(resourceCounter2)) {
                return false;
            }
            this.mMode = i2;
            return true;
        }
    }

    static {
        HashMap<String, ThreadExceptionHandler> hashMap;
        if (Logger.isAllowDebug()) {
            hashMap = new HashMap<>();
        } else {
            hashMap = null;
        }
        sExceptionHandlerMap = hashMap;
    }

    private ThreadPool(String str) {
        this(str, 3, 3);
    }

    public static ThreadPool createPrivateInstance(String str) {
        return new ThreadPool(C0212a.l("-", str), 3, 3);
    }

    public static ThreadPool getInstance() {
        return sInstance;
    }

    /* access modifiers changed from: private */
    public ResourceCounter getLockWithValidation(ResourceCounter resourceCounter) {
        ResourceCounter resourceCounter2 = this.mResourceCounter;
        if (resourceCounter == resourceCounter2) {
            return resourceCounter2;
        }
        throw new AssertionError("fail to get lock ");
    }

    public int getQueuedCount() {
        return this.mExecutor.getQueue().size();
    }

    public boolean hasIdle() {
        if (this.mResourceCounter.value > 0) {
            return true;
        }
        return false;
    }

    public <T> boolean removeInQueue(Future<T> future) {
        ThreadPoolExecutor threadPoolExecutor = this.mExecutor;
        BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
        Worker worker = (Worker) future;
        if (queue.isEmpty() || !queue.contains(worker)) {
            return false;
        }
        threadPoolExecutor.remove(worker);
        Log.w("ThreadPool", "removeInQueue: QueueCount" + getQueuedCount() + " w.mJob " + worker.mJob);
        return true;
    }

    public void setExpireCallback(Consumer<StackTraceElement[]> consumer) {
        ThreadWatchDog threadWatchDog = this.mWatchDog;
        if (threadWatchDog != null) {
            threadWatchDog.setExpireCallback(consumer);
        }
    }

    public void shutDown() {
        ThreadPoolExecutor threadPoolExecutor = this.mExecutor;
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
        }
    }

    public <T> Future<T> submit(Job<T> job, FutureListener<T> futureListener) {
        Worker worker = new Worker(job, futureListener);
        try {
            ThreadWatchDog threadWatchDog = this.mWatchDog;
            if (threadWatchDog != null) {
                threadWatchDog.set(job.hashCode());
            }
            this.mExecutor.execute(worker);
            return worker;
        } catch (RejectedExecutionException e) {
            Log.e((CharSequence) "ThreadPool", "submit failed. maybe shutdown", (Throwable) e);
            SimpleThreadPool.getInstance().execute(worker);
            return worker;
        }
    }

    public void submitUrgent(Runnable runnable) {
        submitUrgent(new b(runnable, 1), (FutureListener) null);
    }

    public ThreadPool(String str, int i2, int i7) {
        int i8 = i2;
        this.mExecutor = new ThreadPoolExecutor(i8, i7, 10, TimeUnit.SECONDS, new LinkedBlockingQueue(), new PriorityThreadFactory(C0212a.l("thread-pool", str), 10));
        this.mCorePoolSize = i8;
        this.mResourceCounter = new ResourceCounter(i8);
        if (TextUtils.isEmpty(str)) {
            this.mWatchDog = new ThreadWatchDog("thread-pool");
        }
    }

    public <T> Future<T> submitUrgent(Job<T> job, FutureListener<T> futureListener) {
        ThreadPoolExecutor threadPoolExecutor = this.mExecutor;
        BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
        int size = queue.size();
        Future<T> submit = submit(job, futureListener);
        for (int i2 = 0; i2 < size; i2++) {
            Runnable poll = queue.poll();
            if (poll != null) {
                threadPoolExecutor.remove(poll);
                threadPoolExecutor.execute(poll);
            }
        }
        return submit;
    }

    public <T> Future<T> submit(Job<T> job) {
        return submit(job, (FutureListener) null);
    }

    public static ThreadPool createPrivateInstance(String str, int i2) {
        return new ThreadPool(C0212a.l("-", str), i2, i2);
    }
}
