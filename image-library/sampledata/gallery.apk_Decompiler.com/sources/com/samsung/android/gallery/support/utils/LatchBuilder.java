package com.samsung.android.gallery.support.utils;

import A.a;
import c0.C0086a;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LatchBuilder {
    private final String CALLER;
    private final String WORKER_TAG;
    private Supplier<Boolean> mCancelSignal;
    private Runnable mCurrent;
    private Consumer<Boolean> mPostArgExecutor;
    private Runnable mPostExecutor;
    private final boolean mSilent;
    private long mTimeout;
    private final ArrayList<Runnable> mWorkers;

    public LatchBuilder(String str) {
        this(str, false);
    }

    public static /* synthetic */ void b(LatchBuilder latchBuilder, Runnable runnable, CountDownLatch countDownLatch, String str, long j2) {
        CountDownLatch countDownLatch2 = countDownLatch;
        latchBuilder.lambda$start$1(str, runnable, countDownLatch2, j2);
    }

    public static void executeLatch(long j2, Runnable runnable) {
        new LatchBuilder(a.f("Latch#", j2)).addWorker(runnable).setTimeout(j2).start();
    }

    public static void executeSilentLatch(String str, long j2, Runnable runnable) {
        new LatchBuilder(str, true).addWorker(runnable).setTimeout(j2).start();
    }

    private String getDebugLog(String str, String str2, long j2) {
        long j3;
        String str3;
        if (j2 > 0) {
            j3 = System.currentTimeMillis() - j2;
        } else {
            j3 = -1;
        }
        StringBuilder sb2 = new StringBuilder();
        C0086a.z(sb2, this.CALLER, " Latch[", str, "] ");
        sb2.append(str2);
        if (j3 > 0) {
            str3 = a.f(" +", j3);
        } else {
            str3 = "";
        }
        sb2.append(str3);
        return sb2.toString();
    }

    private boolean isActive() {
        Supplier<Boolean> supplier = this.mCancelSignal;
        if (supplier == null || !supplier.get().booleanValue()) {
            return true;
        }
        return false;
    }

    private /* synthetic */ void lambda$start$1(String str, Runnable runnable, CountDownLatch countDownLatch, long j2) {
        Thread.currentThread().setName(str);
        lambda$start$2(runnable, countDownLatch, str, j2);
    }

    public static <T> T loadLazy(String str, long j2, Supplier<T> supplier) {
        AtomicReference atomicReference = new AtomicReference((Object) null);
        new LatchBuilder(str, true).addWorker(new C0685x(0, atomicReference, supplier)).setTimeout(j2).start();
        return atomicReference.get();
    }

    /* access modifiers changed from: private */
    /* renamed from: runWorker */
    public void lambda$start$2(Runnable runnable, CountDownLatch countDownLatch, String str, long j2) {
        if (isActive()) {
            runnable.run();
        }
        countDownLatch.countDown();
    }

    public LatchBuilder addWorker(Runnable runnable) {
        if (runnable != null) {
            this.mWorkers.add(runnable);
        }
        return this;
    }

    public LatchBuilder setCancelSignal(Supplier<Boolean> supplier) {
        this.mCancelSignal = supplier;
        return this;
    }

    public LatchBuilder setCurrent(Runnable runnable) {
        this.mCurrent = runnable;
        return this;
    }

    public LatchBuilder setPostExecutor(Runnable runnable) {
        this.mPostExecutor = runnable;
        return this;
    }

    public LatchBuilder setTimeout(long j2) {
        this.mTimeout = j2;
        return this;
    }

    public void start() {
        start(SimpleThreadPool.getInstance().getPoolExecutor());
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        StringBuilder sb2 = new StringBuilder("LatchBuilder{");
        if (this.mCurrent != null) {
            str = "C";
        } else {
            str = "c";
        }
        sb2.append(str);
        if (this.mPostExecutor != null) {
            str2 = "P";
        } else {
            str2 = "p";
        }
        sb2.append(str2);
        if (this.mPostArgExecutor != null) {
            str3 = "A";
        } else {
            str3 = "a";
        }
        sb2.append(str3);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        C0086a.A(sb2, this.mWorkers, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0212a.o(sb2, this.mTimeout, "ms}");
    }

    public LatchBuilder(String str, boolean z) {
        this.mWorkers = new ArrayList<>();
        this.mTimeout = 60000;
        this.CALLER = str;
        this.WORKER_TAG = "latch-@" + Integer.toHexString(hashCode()) + "-";
        this.mSilent = z;
    }

    public LatchBuilder setPostExecutor(Consumer<Boolean> consumer) {
        this.mPostArgExecutor = consumer;
        return this;
    }

    public void start(Executor executor) {
        LatchBuilder latchBuilder;
        long currentTimeMillis = System.currentTimeMillis();
        String name = Thread.currentThread().getName();
        if (!this.mSilent) {
            Log.d("LatchBuilder", getDebugLog(name, "start " + this, 0));
        }
        CountDownLatch countDownLatch = new CountDownLatch(this.mWorkers.size());
        Iterator<Runnable> it = this.mWorkers.iterator();
        boolean z = false;
        int i2 = 0;
        while (it.hasNext()) {
            Runnable next = it.next();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.WORKER_TAG);
            int i7 = i2 + 1;
            sb2.append(i2);
            String sb3 = sb2.toString();
            if (executor != null) {
                CountDownLatch countDownLatch2 = countDownLatch;
                Runnable runnable = next;
                String str = sb3;
                CountDownLatch countDownLatch3 = countDownLatch2;
                latchBuilder = this;
                C0684w wVar = new C0684w(latchBuilder, str, runnable, countDownLatch3, currentTimeMillis);
                countDownLatch = countDownLatch3;
                executor.execute(wVar);
            } else {
                latchBuilder = this;
                Thread thread = new Thread(new C0684w(latchBuilder, next, countDownLatch, sb3, currentTimeMillis), sb3);
                thread.setPriority(10);
                thread.start();
            }
            this = latchBuilder;
            i2 = i7;
        }
        LatchBuilder latchBuilder2 = this;
        if (latchBuilder2.mCurrent != null && latchBuilder2.isActive()) {
            latchBuilder2.mCurrent.run();
        }
        try {
            if (!countDownLatch.await(latchBuilder2.mTimeout, TimeUnit.MILLISECONDS)) {
                z = true;
                Log.e("LatchBuilder", latchBuilder2.getDebugLog(name, "timeout", currentTimeMillis));
            } else if (!latchBuilder2.mSilent) {
                Log.d("LatchBuilder", latchBuilder2.getDebugLog(name, "completed", currentTimeMillis));
            }
        } catch (InterruptedException e) {
            InterruptedException interruptedException = e;
            Log.e("LatchBuilder", latchBuilder2.getDebugLog(name, "interrupted e=" + interruptedException.getMessage(), currentTimeMillis));
        }
        Runnable runnable2 = latchBuilder2.mPostExecutor;
        if (runnable2 != null) {
            runnable2.run();
            return;
        }
        Consumer<Boolean> consumer = latchBuilder2.mPostArgExecutor;
        if (consumer != null) {
            consumer.accept(Boolean.valueOf(z));
        }
    }
}
