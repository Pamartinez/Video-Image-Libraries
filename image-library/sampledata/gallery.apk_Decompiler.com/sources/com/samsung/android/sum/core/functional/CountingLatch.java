package com.samsung.android.sum.core.functional;

import A.a;
import N2.j;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import i.C0212a;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CountingLatch {
    private static final long LOGGABLE_TIME_IN_MILLIS = 500;
    /* access modifiers changed from: private */
    public static final String TAG = Def.tagOf((Class<?>) CountingLatch.class);
    protected Condition cond;
    protected int count;
    protected int initValue;
    protected ReentrantLock lock;
    protected Map<Long, Supplier<Boolean>> predicates = new HashMap();

    public CountingLatch(int i2) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.cond = reentrantLock.newCondition();
        this.initValue = i2;
        this.count = i2;
    }

    public static CountingLatch downOf() {
        return new CountingDownLatch(0);
    }

    public static CountingLatch of() {
        return new CountingEqualLatch(0);
    }

    public static CountingLatch upOf() {
        return new CountingUpLatch(0);
    }

    public int await(int i2) {
        return await(i2, (String) null);
    }

    public abstract int await(int i2, long j2, String str);

    public abstract int await(int i2, String str);

    public int awaitL(Supplier<Boolean> supplier, int i2, String str) {
        String str2 = TAG;
        SLog.d(str2, "await...E[" + hashCode() + "]: until " + this.count + " -> " + i2);
        long currentTimeMillis = System.currentTimeMillis();
        while (this.predicates.containsValue(supplier) && !supplier.get().booleanValue()) {
            this.cond.await();
            if (str != null) {
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                if (currentTimeMillis2 >= LOGGABLE_TIME_IN_MILLIS) {
                    String str3 = TAG;
                    StringBuilder t = C0212a.t(str, "...(");
                    t.append(String.join(ArcCommonLog.TAG_COMMA, new CharSequence[]{"cnt = " + this.count, C0086a.i(i2, "thr = "), a.e(currentTimeMillis2, "wait = ", " ms")}));
                    t.append(")");
                    SLog.i(str3, t.toString());
                }
            }
        }
        String str4 = TAG;
        SLog.d(str4, "await...X[" + hashCode() + "]: " + this.count);
        return this.count;
    }

    /* JADX INFO: finally extract failed */
    public boolean compare(int i2, Runnable runnable) {
        this.lock.lock();
        try {
            if (this.count == i2) {
                runnable.run();
                this.lock.unlock();
                return true;
            }
            this.lock.unlock();
            return false;
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public int down() {
        return down(1);
    }

    public void finalize() {
        try {
            reset();
        } finally {
            super.finalize();
        }
    }

    public int getCount() {
        this.lock.lock();
        try {
            return this.count;
        } finally {
            this.lock.unlock();
        }
    }

    public void reset() {
        String str = TAG;
        SLog.d(str, "reset E[" + hashCode() + "]");
        this.lock.lock();
        try {
            this.count = this.initValue;
            this.predicates.clear();
            this.cond.signalAll();
            this.lock.unlock();
            SLog.d(str, "reset X[" + hashCode() + "]");
        } catch (Throwable th) {
            this.lock.unlock();
            String str2 = TAG;
            SLog.d(str2, "reset X[" + hashCode() + "]");
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public void resetIf(Function<Integer, Boolean> function) {
        String str = TAG;
        SLog.d(str, "resetIf E[" + hashCode() + "]");
        this.lock.lock();
        try {
            if (function.apply(Integer.valueOf(this.count)).booleanValue()) {
                SLog.v(str, "reset latch on cnt=" + this.count);
                reset();
            }
            this.lock.unlock();
            SLog.d(str, "resetIf X[" + hashCode() + "]");
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public int up() {
        return up(1);
    }

    public static CountingLatch downOf(int i2) {
        return new CountingDownLatch(i2);
    }

    public static CountingLatch upOf(int i2) {
        return new CountingUpLatch(i2);
    }

    public int await(int i2, long j2) {
        return await(i2, j2, (String) null);
    }

    public int down(int i2) {
        this.lock.lock();
        try {
            this.count -= i2;
            this.cond.signalAll();
            return this.count;
        } finally {
            this.lock.unlock();
        }
    }

    public int up(int i2) {
        this.lock.lock();
        try {
            this.count += i2;
            this.cond.signalAll();
            return this.count;
        } finally {
            this.lock.unlock();
        }
    }

    public int down(int i2, Consumer<Integer> consumer) {
        this.lock.lock();
        try {
            int i7 = this.count - i2;
            this.count = i7;
            consumer.accept(Integer.valueOf(i7));
            this.cond.signalAll();
            return this.count;
        } finally {
            this.lock.unlock();
        }
    }

    public int up(int i2, Consumer<Integer> consumer) {
        this.lock.lock();
        try {
            int i7 = this.count + i2;
            this.count = i7;
            consumer.accept(Integer.valueOf(i7));
            this.cond.signalAll();
            return this.count;
        } finally {
            this.lock.unlock();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CountingDownLatch extends CountingLatch {
        public CountingDownLatch(int i2) {
            super(i2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Boolean lambda$await$0(int i2) {
            boolean z;
            if (this.count <= i2) {
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Boolean lambda$await$1(int i2) {
            boolean z;
            if (this.count <= i2) {
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }

        public int await(int i2, String str) {
            int i7;
            this.lock.lock();
            long id = Thread.currentThread().getId();
            a aVar = new a(this, i2, 0);
            this.predicates.put(Long.valueOf(id), aVar);
            try {
                i7 = awaitL(aVar, i2, str);
            } catch (InterruptedException e) {
                String access$000 = CountingLatch.TAG;
                SLog.w(access$000, "exception occur[" + this.count + "/" + i2 + "]: " + e.getMessage());
                i7 = this.count;
            } catch (Throwable th) {
                this.predicates.remove(Long.valueOf(id));
                this.lock.unlock();
                throw th;
            }
            this.predicates.remove(Long.valueOf(id));
            this.lock.unlock();
            return i7;
        }

        public int await(int i2, long j2, String str) {
            int i7;
            this.lock.lock();
            long id = Thread.currentThread().getId();
            a aVar = new a(this, i2, 1);
            this.predicates.put(Long.valueOf(id), aVar);
            int i8 = i2;
            try {
                i7 = awaitL(aVar, i8, j2, str);
            } catch (InterruptedException e) {
                String access$000 = CountingLatch.TAG;
                SLog.w(access$000, "exception occur[" + this.count + "/" + i8 + "]: " + e.getMessage());
                i7 = this.count;
            } catch (TimeoutException e7) {
                throw new IllegalStateException(e7);
            } catch (Throwable th) {
                Throwable th2 = th;
                this.predicates.remove(Long.valueOf(id));
                this.lock.unlock();
                throw th2;
            }
            this.predicates.remove(Long.valueOf(id));
            this.lock.unlock();
            return i7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CountingEqualLatch extends CountingLatch {
        public CountingEqualLatch(int i2) {
            super(i2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Boolean lambda$await$0(int i2) {
            boolean z;
            if (this.count == i2) {
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Boolean lambda$await$1(int i2) {
            boolean z;
            if (this.count == i2) {
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }

        public int await(int i2, String str) {
            int i7;
            this.lock.lock();
            long id = Thread.currentThread().getId();
            b bVar = new b(this, i2, 0);
            this.predicates.put(Long.valueOf(id), bVar);
            try {
                i7 = awaitL(bVar, i2, str);
            } catch (InterruptedException e) {
                String access$000 = CountingLatch.TAG;
                SLog.w(access$000, "exception occur[" + this.count + "/" + i2 + "]: " + e.getMessage());
                i7 = this.count;
            } catch (Throwable th) {
                this.predicates.remove(Long.valueOf(id));
                this.lock.unlock();
                throw th;
            }
            this.predicates.remove(Long.valueOf(id));
            this.lock.unlock();
            return i7;
        }

        public int await(int i2, long j2, String str) {
            int i7;
            this.lock.lock();
            long id = Thread.currentThread().getId();
            b bVar = new b(this, i2, 1);
            this.predicates.put(Long.valueOf(id), bVar);
            int i8 = i2;
            try {
                i7 = awaitL(bVar, i8, j2, str);
            } catch (InterruptedException e) {
                String access$000 = CountingLatch.TAG;
                SLog.w(access$000, "exception occur[" + this.count + "/" + i8 + "]: " + e.getMessage());
                i7 = this.count;
            } catch (TimeoutException e7) {
                throw new IllegalStateException(e7);
            } catch (Throwable th) {
                Throwable th2 = th;
                this.predicates.remove(Long.valueOf(id));
                this.lock.unlock();
                throw th2;
            }
            this.predicates.remove(Long.valueOf(id));
            this.lock.unlock();
            return i7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CountingUpLatch extends CountingLatch {
        public CountingUpLatch(int i2) {
            super(i2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Boolean lambda$await$0(int i2) {
            boolean z;
            if (this.count >= i2) {
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ Boolean lambda$await$1(int i2) {
            boolean z;
            if (this.count >= i2) {
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }

        public int await(int i2, String str) {
            int i7;
            this.lock.lock();
            long id = Thread.currentThread().getId();
            c cVar = new c(this, i2, 0);
            this.predicates.put(Long.valueOf(id), cVar);
            try {
                i7 = awaitL(cVar, i2, str);
            } catch (InterruptedException e) {
                String access$000 = CountingLatch.TAG;
                SLog.w(access$000, "exception occur[" + this.count + "/" + i2 + "]: " + e.getMessage());
                i7 = this.count;
            } catch (Throwable th) {
                this.predicates.remove(Long.valueOf(id));
                this.lock.unlock();
                throw th;
            }
            this.predicates.remove(Long.valueOf(id));
            this.lock.unlock();
            return i7;
        }

        public int await(int i2, long j2, String str) {
            int i7;
            this.lock.lock();
            long id = Thread.currentThread().getId();
            c cVar = new c(this, i2, 1);
            this.predicates.put(Long.valueOf(id), cVar);
            int i8 = i2;
            try {
                i7 = awaitL(cVar, i8, j2, str);
            } catch (InterruptedException e) {
                String access$000 = CountingLatch.TAG;
                SLog.w(access$000, "exception occur[" + this.count + "/" + i8 + "]: " + e.getMessage());
                i7 = this.count;
            } catch (TimeoutException e7) {
                throw new IllegalStateException(e7);
            } catch (Throwable th) {
                Throwable th2 = th;
                this.predicates.remove(Long.valueOf(id));
                this.lock.unlock();
                throw th2;
            }
            this.predicates.remove(Long.valueOf(id));
            this.lock.unlock();
            return i7;
        }
    }

    public int awaitL(Supplier<Boolean> supplier, int i2, long j2, String str) {
        int i7 = i2;
        long j3 = j2;
        String str2 = str;
        String str3 = TAG;
        StringBuilder sb2 = new StringBuilder("await...E[");
        sb2.append(hashCode());
        sb2.append("]: until ");
        j.x(sb2, this.count, " -> ", i7, " for ");
        sb2.append(j3);
        sb2.append(" ms");
        SLog.d(str3, sb2.toString());
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = false;
        while (!z) {
            Supplier<Boolean> supplier2 = supplier;
            if (!this.predicates.containsValue(supplier2) || supplier2.get().booleanValue()) {
                break;
            }
            z = !this.cond.await(j3, TimeUnit.MILLISECONDS);
            if (str2 != null) {
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                if (currentTimeMillis2 >= LOGGABLE_TIME_IN_MILLIS) {
                    String str4 = TAG;
                    StringBuilder t = C0212a.t(str2, "...(");
                    t.append(String.join(ArcCommonLog.TAG_COMMA, new CharSequence[]{"cnt = " + this.count, C0086a.i(i7, "thr = "), "isTimedOut = " + z, C0212a.o(j.j(currentTimeMillis2, "wait/timeout = ", "/"), j3, " ms")}));
                    t.append(")");
                    SLog.i(str4, t.toString());
                    i7 = i2;
                    str2 = str;
                }
            }
            i7 = i2;
            str2 = str;
        }
        SLog.d(TAG, "await...X[" + hashCode() + "]: " + this.count + ", isTimedOut=" + z);
        if (!z) {
            return this.count;
        }
        throw new TimeoutException(a.e(j3, "Timeout expired for ", " ms"));
    }
}
