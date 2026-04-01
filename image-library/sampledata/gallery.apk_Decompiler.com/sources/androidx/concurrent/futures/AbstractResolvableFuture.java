package androidx.concurrent.futures;

import N2.j;
import com.google.common.util.concurrent.ListenableFuture;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import i.C0212a;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbstractResolvableFuture<V> implements ListenableFuture {
    static final AtomicHelper ATOMIC_HELPER;
    static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE));
    private static final Object NULL = new Object();
    private static final Logger log;
    volatile Listener listeners;
    volatile Object value;
    volatile Waiter waiters;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        public abstract boolean casListeners(AbstractResolvableFuture<?> abstractResolvableFuture, Listener listener, Listener listener2);

        public abstract boolean casValue(AbstractResolvableFuture<?> abstractResolvableFuture, Object obj, Object obj2);

        public abstract boolean casWaiters(AbstractResolvableFuture<?> abstractResolvableFuture, Waiter waiter, Waiter waiter2);

        public abstract void putNext(Waiter waiter, Waiter waiter2);

        public abstract void putThread(Waiter waiter, Thread thread);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Cancellation {
        static final Cancellation CAUSELESS_CANCELLED;
        static final Cancellation CAUSELESS_INTERRUPTED;
        final Throwable cause;
        final boolean wasInterrupted;

        static {
            if (AbstractResolvableFuture.GENERATE_CANCELLATION_CAUSES) {
                CAUSELESS_CANCELLED = null;
                CAUSELESS_INTERRUPTED = null;
                return;
            }
            CAUSELESS_CANCELLED = new Cancellation(false, (Throwable) null);
            CAUSELESS_INTERRUPTED = new Cancellation(true, (Throwable) null);
        }

        public Cancellation(boolean z, Throwable th) {
            this.wasInterrupted = z;
            this.cause = th;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Failure {
        static final Failure FALLBACK_INSTANCE = new Failure(new Throwable("Failure occurred while trying to finish a future.") {
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable exception;

        public Failure(Throwable th) {
            this.exception = (Throwable) AbstractResolvableFuture.checkNotNull(th);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Listener {
        static final Listener TOMBSTONE = new Listener((Runnable) null, (Executor) null);
        final Executor executor;
        Listener next;
        final Runnable task;

        public Listener(Runnable runnable, Executor executor2) {
            this.task = runnable;
            this.executor = executor2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicReferenceFieldUpdater<AbstractResolvableFuture, Listener> listenersUpdater;
        final AtomicReferenceFieldUpdater<AbstractResolvableFuture, Object> valueUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Waiter> waiterNextUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Thread> waiterThreadUpdater;
        final AtomicReferenceFieldUpdater<AbstractResolvableFuture, Waiter> waitersUpdater;

        public SafeAtomicHelper(AtomicReferenceFieldUpdater<Waiter, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<Waiter, Waiter> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AbstractResolvableFuture, Waiter> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AbstractResolvableFuture, Listener> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AbstractResolvableFuture, Object> atomicReferenceFieldUpdater5) {
            super();
            this.waiterThreadUpdater = atomicReferenceFieldUpdater;
            this.waiterNextUpdater = atomicReferenceFieldUpdater2;
            this.waitersUpdater = atomicReferenceFieldUpdater3;
            this.listenersUpdater = atomicReferenceFieldUpdater4;
            this.valueUpdater = atomicReferenceFieldUpdater5;
        }

        public boolean casListeners(AbstractResolvableFuture<?> abstractResolvableFuture, Listener listener, Listener listener2) {
            AtomicReferenceFieldUpdater<AbstractResolvableFuture, Listener> atomicReferenceFieldUpdater = this.listenersUpdater;
            while (!atomicReferenceFieldUpdater.compareAndSet(abstractResolvableFuture, listener, listener2)) {
                if (atomicReferenceFieldUpdater.get(abstractResolvableFuture) != listener) {
                    return false;
                }
            }
            return true;
        }

        public boolean casValue(AbstractResolvableFuture<?> abstractResolvableFuture, Object obj, Object obj2) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = this.valueUpdater;
            while (!atomicReferenceFieldUpdater.compareAndSet(abstractResolvableFuture, obj, obj2)) {
                if (atomicReferenceFieldUpdater.get(abstractResolvableFuture) != obj) {
                    return false;
                }
            }
            return true;
        }

        public boolean casWaiters(AbstractResolvableFuture<?> abstractResolvableFuture, Waiter waiter, Waiter waiter2) {
            AtomicReferenceFieldUpdater<AbstractResolvableFuture, Waiter> atomicReferenceFieldUpdater = this.waitersUpdater;
            while (!atomicReferenceFieldUpdater.compareAndSet(abstractResolvableFuture, waiter, waiter2)) {
                if (atomicReferenceFieldUpdater.get(abstractResolvableFuture) != waiter) {
                    return false;
                }
            }
            return true;
        }

        public void putNext(Waiter waiter, Waiter waiter2) {
            this.waiterNextUpdater.lazySet(waiter, waiter2);
        }

        public void putThread(Waiter waiter, Thread thread) {
            this.waiterThreadUpdater.lazySet(waiter, thread);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SynchronizedHelper extends AtomicHelper {
        public SynchronizedHelper() {
            super();
        }

        public boolean casListeners(AbstractResolvableFuture<?> abstractResolvableFuture, Listener listener, Listener listener2) {
            synchronized (abstractResolvableFuture) {
                try {
                    if (abstractResolvableFuture.listeners != listener) {
                        return false;
                    }
                    abstractResolvableFuture.listeners = listener2;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public boolean casValue(AbstractResolvableFuture<?> abstractResolvableFuture, Object obj, Object obj2) {
            synchronized (abstractResolvableFuture) {
                try {
                    if (abstractResolvableFuture.value != obj) {
                        return false;
                    }
                    abstractResolvableFuture.value = obj2;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public boolean casWaiters(AbstractResolvableFuture<?> abstractResolvableFuture, Waiter waiter, Waiter waiter2) {
            synchronized (abstractResolvableFuture) {
                try {
                    if (abstractResolvableFuture.waiters != waiter) {
                        return false;
                    }
                    abstractResolvableFuture.waiters = waiter2;
                    return true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void putNext(Waiter waiter, Waiter waiter2) {
            waiter.next = waiter2;
        }

        public void putThread(Waiter waiter, Thread thread) {
            waiter.thread = thread;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Waiter {
        static final Waiter TOMBSTONE = new Waiter(false);
        volatile Waiter next;
        volatile Thread thread;

        public Waiter(boolean z) {
        }

        public void setNext(Waiter waiter) {
            AbstractResolvableFuture.ATOMIC_HELPER.putNext(this, waiter);
        }

        public void unpark() {
            Thread thread2 = this.thread;
            if (thread2 != null) {
                this.thread = null;
                LockSupport.unpark(thread2);
            }
        }

        public Waiter() {
            AbstractResolvableFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
        }
    }

    static {
        AtomicHelper atomicHelper;
        Class<Waiter> cls = Waiter.class;
        Class<AbstractResolvableFuture> cls2 = AbstractResolvableFuture.class;
        log = Logger.getLogger(cls2.getName());
        try {
            atomicHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(cls, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(cls, cls, "next"), AtomicReferenceFieldUpdater.newUpdater(cls2, cls, "waiters"), AtomicReferenceFieldUpdater.newUpdater(cls2, Listener.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(cls2, Object.class, "value"));
            th = null;
        } catch (Throwable th) {
            th = th;
            atomicHelper = new SynchronizedHelper();
        }
        ATOMIC_HELPER = atomicHelper;
        if (th != null) {
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
    }

    private void addDoneString(StringBuilder sb2) {
        try {
            Object uninterruptibly = getUninterruptibly(this);
            sb2.append("SUCCESS, result=[");
            sb2.append(userObjectToString(uninterruptibly));
            sb2.append("]");
        } catch (ExecutionException e) {
            sb2.append("FAILURE, cause=[");
            sb2.append(e.getCause());
            sb2.append("]");
        } catch (CancellationException unused) {
            sb2.append("CANCELLED");
        } catch (RuntimeException e7) {
            sb2.append("UNKNOWN, cause=[");
            sb2.append(e7.getClass());
            sb2.append(" thrown from get()]");
        }
    }

    private static CancellationException cancellationExceptionWithCause(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    public static <T> T checkNotNull(T t) {
        t.getClass();
        return t;
    }

    private Listener clearListeners(Listener listener) {
        Listener listener2;
        do {
            listener2 = this.listeners;
        } while (!ATOMIC_HELPER.casListeners(this, listener2, Listener.TOMBSTONE));
        while (true) {
            Listener listener3 = listener;
            listener = listener2;
            if (listener == null) {
                return listener3;
            }
            listener2 = listener.next;
            listener.next = listener3;
        }
    }

    public static void complete(AbstractResolvableFuture<?> abstractResolvableFuture) {
        abstractResolvableFuture.releaseWaiters();
        abstractResolvableFuture.afterDone();
        Listener clearListeners = abstractResolvableFuture.clearListeners((Listener) null);
        while (clearListeners != null) {
            Listener listener = clearListeners.next;
            executeListener(clearListeners.task, clearListeners.executor);
            clearListeners = listener;
        }
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = log;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
        }
    }

    private V getDoneValue(Object obj) {
        if (obj instanceof Cancellation) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((Cancellation) obj).cause);
        } else if (obj instanceof Failure) {
            throw new ExecutionException(((Failure) obj).exception);
        } else if (obj == NULL) {
            return null;
        } else {
            return obj;
        }
    }

    public static <V> V getUninterruptibly(Future<V> future) {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    private void releaseWaiters() {
        Waiter waiter;
        do {
            waiter = this.waiters;
        } while (!ATOMIC_HELPER.casWaiters(this, waiter, Waiter.TOMBSTONE));
        while (waiter != null) {
            waiter.unpark();
            waiter = waiter.next;
        }
    }

    private void removeWaiter(Waiter waiter) {
        waiter.thread = null;
        while (true) {
            Waiter waiter2 = this.waiters;
            if (waiter2 != Waiter.TOMBSTONE) {
                Waiter waiter3 = null;
                while (waiter2 != null) {
                    Waiter waiter4 = waiter2.next;
                    if (waiter2.thread != null) {
                        waiter3 = waiter2;
                    } else if (waiter3 != null) {
                        waiter3.next = waiter4;
                        if (waiter3.thread == null) {
                        }
                    } else if (!ATOMIC_HELPER.casWaiters(this, waiter2, waiter4)) {
                    }
                    waiter2 = waiter4;
                }
                return;
            }
            return;
        }
    }

    private String userObjectToString(Object obj) {
        if (obj == this) {
            return "this future";
        }
        return String.valueOf(obj);
    }

    public final void addListener(Runnable runnable, Executor executor) {
        checkNotNull(runnable);
        checkNotNull(executor);
        Listener listener = this.listeners;
        if (listener != Listener.TOMBSTONE) {
            Listener listener2 = new Listener(runnable, executor);
            do {
                listener2.next = listener;
                if (!ATOMIC_HELPER.casListeners(this, listener, listener2)) {
                    listener = this.listeners;
                } else {
                    return;
                }
            } while (listener != Listener.TOMBSTONE);
        }
        executeListener(runnable, executor);
    }

    public final boolean cancel(boolean z) {
        boolean z3;
        Cancellation cancellation;
        Object obj = this.value;
        if (obj == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            if (GENERATE_CANCELLATION_CAUSES) {
                cancellation = new Cancellation(z, new CancellationException("Future.cancel() was called."));
            } else if (z) {
                cancellation = Cancellation.CAUSELESS_INTERRUPTED;
            } else {
                cancellation = Cancellation.CAUSELESS_CANCELLED;
            }
            if (ATOMIC_HELPER.casValue(this, obj, cancellation)) {
                if (z) {
                    interruptTask();
                }
                complete(this);
                return true;
            }
        }
        return false;
    }

    public final V get(long j2, TimeUnit timeUnit) {
        long j3 = j2;
        TimeUnit timeUnit2 = timeUnit;
        long nanos = timeUnit2.toNanos(j3);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            if (obj != null) {
                return getDoneValue(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0;
            if (nanos >= 1000) {
                Waiter waiter = this.waiters;
                if (waiter != Waiter.TOMBSTONE) {
                    Waiter waiter2 = new Waiter();
                    do {
                        waiter2.setNext(waiter);
                        if (ATOMIC_HELPER.casWaiters(this, waiter, waiter2)) {
                            do {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.value;
                                    if (obj2 != null) {
                                        return getDoneValue(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    removeWaiter(waiter2);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            removeWaiter(waiter2);
                        } else {
                            waiter = this.waiters;
                        }
                    } while (waiter != Waiter.TOMBSTONE);
                }
                return getDoneValue(this.value);
            }
            while (nanos > 0) {
                Object obj3 = this.value;
                if (obj3 != null) {
                    return getDoneValue(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String abstractResolvableFuture = toString();
            String obj4 = timeUnit2.toString();
            Locale locale = Locale.ROOT;
            String lowerCase = obj4.toLowerCase(locale);
            StringBuilder j8 = j.j(j3, "Waited ", " ");
            j8.append(timeUnit2.toString().toLowerCase(locale));
            String sb2 = j8.toString();
            if (nanos + 1000 < 0) {
                String A10 = C0212a.A(sb2, " (plus ");
                long j10 = -nanos;
                long convert = timeUnit2.convert(j10, TimeUnit.NANOSECONDS);
                long nanos2 = j10 - timeUnit2.toNanos(convert);
                int i2 = (convert > 0 ? 1 : (convert == 0 ? 0 : -1));
                boolean z = i2 == 0 || nanos2 > 1000;
                if (i2 > 0) {
                    String str = A10 + convert + " " + lowerCase;
                    if (z) {
                        str = C0212a.A(str, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    }
                    A10 = C0212a.A(str, " ");
                }
                if (z) {
                    A10 = A10 + nanos2 + " nanoseconds ";
                }
                sb2 = C0212a.A(A10, "delay)");
            }
            if (isDone()) {
                throw new TimeoutException(C0212a.A(sb2, " but future completed as timeout expired"));
            }
            throw new TimeoutException(C0212a.B(sb2, " for ", abstractResolvableFuture));
        }
        throw new InterruptedException();
    }

    public final boolean isCancelled() {
        return this.value instanceof Cancellation;
    }

    public final boolean isDone() {
        if (this.value != null) {
            return true;
        }
        return false;
    }

    public String pendingToString() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    public boolean set(V v) {
        if (v == null) {
            v = NULL;
        }
        if (!ATOMIC_HELPER.casValue(this, (Object) null, v)) {
            return false;
        }
        complete(this);
        return true;
    }

    public boolean setException(Throwable th) {
        if (!ATOMIC_HELPER.casValue(this, (Object) null, new Failure((Throwable) checkNotNull(th)))) {
            return false;
        }
        complete(this);
        return true;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.toString());
        sb2.append("[status=");
        if (isCancelled()) {
            sb2.append("CANCELLED");
        } else if (isDone()) {
            addDoneString(sb2);
        } else {
            try {
                str = pendingToString();
            } catch (RuntimeException e) {
                str = "Exception thrown from implementation: " + e.getClass();
            }
            if (str != null && !str.isEmpty()) {
                j.z(sb2, "PENDING, info=[", str, "]");
            } else if (isDone()) {
                addDoneString(sb2);
            } else {
                sb2.append("PENDING");
            }
        }
        sb2.append("]");
        return sb2.toString();
    }

    public void afterDone() {
    }

    public void interruptTask() {
    }

    public final V get() {
        Object obj;
        boolean z;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if (obj2 != null) {
                return getDoneValue(obj2);
            }
            Waiter waiter = this.waiters;
            if (waiter != Waiter.TOMBSTONE) {
                Waiter waiter2 = new Waiter();
                do {
                    waiter2.setNext(waiter);
                    if (ATOMIC_HELPER.casWaiters(this, waiter, waiter2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                                if (obj != null) {
                                    z = true;
                                    continue;
                                } else {
                                    z = false;
                                    continue;
                                }
                            } else {
                                removeWaiter(waiter2);
                                throw new InterruptedException();
                            }
                        } while (!z);
                        return getDoneValue(obj);
                    }
                    waiter = this.waiters;
                } while (waiter != Waiter.TOMBSTONE);
            }
            return getDoneValue(this.value);
        }
        throw new InterruptedException();
    }
}
