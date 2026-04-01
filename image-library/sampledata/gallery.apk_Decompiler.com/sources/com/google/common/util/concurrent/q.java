package com.google.common.util.concurrent;

import He.F;
import K2.a;
import N2.j;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.utils.Log;
import i.C0212a;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class q extends a implements ListenableFuture {
    /* access modifiers changed from: private */
    public static final C0122a ATOMIC_HELPER;
    static final boolean GENERATE_CANCELLATION_CAUSES;
    private static final Object NULL = new Object();
    private static final long SPIN_THRESHOLD_NANOS = 1000;
    static final x log;
    /* access modifiers changed from: private */
    public volatile C0126e listeners;
    /* access modifiers changed from: private */
    public volatile Object value;
    /* access modifiers changed from: private */
    public volatile p waiters;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.google.common.util.concurrent.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: com.google.common.util.concurrent.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: com.google.common.util.concurrent.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: com.google.common.util.concurrent.f} */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            java.lang.Class<com.google.common.util.concurrent.p> r1 = com.google.common.util.concurrent.p.class
            java.lang.String r0 = "guava.concurrent.generate_cancellation_cause"
            java.lang.String r2 = "false"
            java.lang.String r0 = java.lang.System.getProperty(r0, r2)     // Catch:{ SecurityException -> 0x000f }
            boolean r0 = java.lang.Boolean.parseBoolean(r0)     // Catch:{ SecurityException -> 0x000f }
            goto L_0x0010
        L_0x000f:
            r0 = 0
        L_0x0010:
            GENERATE_CANCELLATION_CAUSES = r0
            com.google.common.util.concurrent.x r0 = new com.google.common.util.concurrent.x
            java.lang.Class<com.google.common.util.concurrent.q> r2 = com.google.common.util.concurrent.q.class
            r0.<init>(r2)
            log = r0
            r3 = 0
            com.google.common.util.concurrent.o r0 = new com.google.common.util.concurrent.o     // Catch:{ Error | Exception -> 0x0023 }
            r0.<init>()     // Catch:{ Error | Exception -> 0x0023 }
            r4 = r3
            goto L_0x0059
        L_0x0023:
            r0 = move-exception
            r4 = r0
            com.google.common.util.concurrent.f r5 = new com.google.common.util.concurrent.f     // Catch:{ Error | Exception -> 0x0052 }
            java.lang.Class<java.lang.Thread> r0 = java.lang.Thread.class
            java.lang.String r6 = "a"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r6 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r1, r0, r6)     // Catch:{ Error | Exception -> 0x0052 }
            java.lang.String r0 = "b"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r7 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r1, r1, r0)     // Catch:{ Error | Exception -> 0x0052 }
            java.lang.String r0 = "waiters"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r8 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r2, r1, r0)     // Catch:{ Error | Exception -> 0x0052 }
            java.lang.Class<com.google.common.util.concurrent.e> r0 = com.google.common.util.concurrent.C0126e.class
            java.lang.String r1 = "listeners"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r9 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r2, r0, r1)     // Catch:{ Error | Exception -> 0x0052 }
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            java.lang.String r1 = "value"
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r10 = java.util.concurrent.atomic.AtomicReferenceFieldUpdater.newUpdater(r2, r0, r1)     // Catch:{ Error | Exception -> 0x0052 }
            r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ Error | Exception -> 0x0052 }
            r0 = r5
            goto L_0x0059
        L_0x0052:
            r0 = move-exception
            r3 = r0
            com.google.common.util.concurrent.h r0 = new com.google.common.util.concurrent.h
            r0.<init>()
        L_0x0059:
            ATOMIC_HELPER = r0
            if (r3 == 0) goto L_0x0073
            com.google.common.util.concurrent.x r0 = log
            java.util.logging.Logger r1 = r0.a()
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r5 = "UnsafeAtomicHelper is broken!"
            r1.log(r2, r5, r4)
            java.util.logging.Logger r0 = r0.a()
            java.lang.String r1 = "SafeAtomicHelper is broken!"
            r0.log(r2, r1, r3)
        L_0x0073:
            java.lang.Object r0 = new java.lang.Object
            r0.<init>()
            NULL = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.q.<clinit>():void");
    }

    public static void c(q qVar, boolean z) {
        C0126e eVar = null;
        while (true) {
            qVar.getClass();
            for (p e = ATOMIC_HELPER.e(qVar); e != null; e = e.b) {
                Thread thread = e.f1571a;
                if (thread != null) {
                    e.f1571a = null;
                    LockSupport.unpark(thread);
                }
            }
            if (z) {
                qVar.interruptTask();
                z = false;
            }
            qVar.afterDone();
            C0126e eVar2 = eVar;
            C0126e d = ATOMIC_HELPER.d(qVar);
            C0126e eVar3 = eVar2;
            while (d != null) {
                C0126e eVar4 = d.f1565c;
                d.f1565c = eVar3;
                eVar3 = d;
                d = eVar4;
            }
            while (true) {
                if (eVar3 != null) {
                    eVar = eVar3.f1565c;
                    Runnable runnable = eVar3.f1564a;
                    Objects.requireNonNull(runnable);
                    if (runnable instanceof C0128g) {
                        C0128g gVar = (C0128g) runnable;
                        qVar = gVar.d;
                        if (qVar.value == gVar) {
                            if (ATOMIC_HELPER.b(qVar, gVar, f(gVar.e))) {
                            }
                        } else {
                            continue;
                        }
                    } else {
                        Executor executor = eVar3.b;
                        Objects.requireNonNull(executor);
                        d(runnable, executor);
                    }
                    eVar3 = eVar;
                } else {
                    return;
                }
            }
        }
    }

    public static void d(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e) {
            Logger a7 = log.a();
            Level level = Level.SEVERE;
            a7.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
        }
    }

    public static Object e(Object obj) {
        if (obj instanceof C0123b) {
            Throwable th = ((C0123b) obj).b;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof C0125d) {
            throw new ExecutionException(((C0125d) obj).f1563a);
        } else if (obj == NULL) {
            return null;
        } else {
            return obj;
        }
    }

    public static Object f(ListenableFuture listenableFuture) {
        Object obj;
        Throwable tryInternalFastPathGetFailure;
        if (listenableFuture instanceof i) {
            Object obj2 = ((q) listenableFuture).value;
            if (obj2 instanceof C0123b) {
                C0123b bVar = (C0123b) obj2;
                if (bVar.f1562a) {
                    obj2 = bVar.b != null ? new C0123b(false, bVar.b) : C0123b.d;
                }
            }
            Objects.requireNonNull(obj2);
            return obj2;
        } else if ((listenableFuture instanceof a) && (tryInternalFastPathGetFailure = ((a) listenableFuture).tryInternalFastPathGetFailure()) != null) {
            return new C0125d(tryInternalFastPathGetFailure);
        } else {
            boolean isCancelled = listenableFuture.isCancelled();
            if ((!GENERATE_CANCELLATION_CAUSES) && isCancelled) {
                C0123b bVar2 = C0123b.d;
                Objects.requireNonNull(bVar2);
                return bVar2;
            }
            boolean z = false;
            while (true) {
                try {
                    obj = listenableFuture.get();
                    break;
                } catch (InterruptedException unused) {
                    z = true;
                } catch (ExecutionException e) {
                    if (!isCancelled) {
                        return new C0125d(e.getCause());
                    }
                    return new C0123b(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + listenableFuture, e));
                } catch (CancellationException e7) {
                    if (isCancelled) {
                        return new C0123b(false, e7);
                    }
                    return new C0125d(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + listenableFuture, e7));
                } catch (Error | Exception e8) {
                    return new C0125d(e8);
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
            if (isCancelled) {
                return new C0123b(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + listenableFuture));
            } else if (obj == null) {
                return NULL;
            } else {
                return obj;
            }
        }
    }

    public final void a(StringBuilder sb2) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (ExecutionException e) {
                sb2.append("FAILURE, cause=[");
                sb2.append(e.getCause());
                sb2.append("]");
                return;
            } catch (CancellationException unused2) {
                sb2.append("CANCELLED");
                return;
            } catch (Exception e7) {
                sb2.append("UNKNOWN, cause=[");
                sb2.append(e7.getClass());
                sb2.append(" thrown from get()]");
                return;
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
        sb2.append("SUCCESS, result=[");
        b(obj, sb2);
        sb2.append("]");
    }

    public void addListener(Runnable runnable, Executor executor) {
        C0126e eVar;
        C0126e eVar2 = C0126e.d;
        F.n(runnable, "Runnable was null.");
        F.n(executor, "Executor was null.");
        if (isDone() || (eVar = this.listeners) == eVar2) {
            d(runnable, executor);
        }
        C0126e eVar3 = new C0126e(runnable, executor);
        do {
            eVar3.f1565c = eVar;
            if (!ATOMIC_HELPER.a(this, eVar, eVar3)) {
                eVar = this.listeners;
            } else {
                return;
            }
        } while (eVar != eVar2);
        d(runnable, executor);
    }

    public final void b(Object obj, StringBuilder sb2) {
        if (obj == null) {
            sb2.append("null");
        } else if (obj == this) {
            sb2.append("this future");
        } else {
            sb2.append(obj.getClass().getName());
            sb2.append(Log.TAG_SEPARATOR);
            sb2.append(Integer.toHexString(System.identityHashCode(obj)));
        }
    }

    public boolean cancel(boolean z) {
        boolean z3;
        C0123b bVar;
        boolean z7;
        Object obj = this.value;
        if (obj == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z3 && !(obj instanceof C0128g)) {
            return false;
        }
        if (GENERATE_CANCELLATION_CAUSES) {
            bVar = new C0123b(z, new CancellationException("Future.cancel() was called."));
        } else {
            if (z) {
                bVar = C0123b.f1561c;
            } else {
                bVar = C0123b.d;
            }
            Objects.requireNonNull(bVar);
        }
        boolean z9 = false;
        while (true) {
            if (ATOMIC_HELPER.b(this, obj, bVar)) {
                c(this, z);
                if (!(obj instanceof C0128g)) {
                    break;
                }
                ListenableFuture listenableFuture = ((C0128g) obj).e;
                if (!(listenableFuture instanceof i)) {
                    listenableFuture.cancel(z);
                    break;
                }
                this = (q) listenableFuture;
                obj = this.value;
                if (obj == null) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                if (!z7 && !(obj instanceof C0128g)) {
                    break;
                }
                z9 = true;
            } else {
                obj = this.value;
                if (!(obj instanceof C0128g)) {
                    return z9;
                }
            }
        }
        return true;
    }

    public final void g(p pVar) {
        pVar.f1571a = null;
        while (true) {
            p pVar2 = this.waiters;
            if (pVar2 != p.f1570c) {
                p pVar3 = null;
                while (pVar2 != null) {
                    p pVar4 = pVar2.b;
                    if (pVar2.f1571a != null) {
                        pVar3 = pVar2;
                    } else if (pVar3 != null) {
                        pVar3.b = pVar4;
                        if (pVar3.f1571a == null) {
                        }
                    } else if (!ATOMIC_HELPER.c(this, pVar2, pVar4)) {
                    }
                    pVar2 = pVar4;
                }
                return;
            }
            return;
        }
    }

    public Object get(long j2, TimeUnit timeUnit) {
        long j3;
        boolean z;
        long j8 = j2;
        TimeUnit timeUnit2 = timeUnit;
        p pVar = p.f1570c;
        long nanos = timeUnit2.toNanos(j8);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            if ((obj != null) && (!(obj instanceof C0128g))) {
                return e(obj);
            }
            long j10 = 0;
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0;
            if (nanos >= 1000) {
                p pVar2 = this.waiters;
                if (pVar2 != pVar) {
                    p pVar3 = new p();
                    z = true;
                    while (true) {
                        ATOMIC_HELPER.f(pVar3, pVar2);
                        if (ATOMIC_HELPER.c(this, pVar2, pVar3)) {
                            j3 = j10;
                            do {
                                LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.value;
                                    if ((obj2 != null) && (!(obj2 instanceof C0128g))) {
                                        return e(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    g(pVar3);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            g(pVar3);
                        } else {
                            long j11 = j10;
                            pVar2 = this.waiters;
                            if (pVar2 == pVar) {
                                break;
                            }
                            j10 = j11;
                        }
                    }
                }
                Object obj3 = this.value;
                Objects.requireNonNull(obj3);
                return e(obj3);
            }
            z = true;
            j3 = 0;
            while (nanos > j3) {
                Object obj4 = this.value;
                if ((obj4 != null ? z : false) && (!(obj4 instanceof C0128g))) {
                    return e(obj4);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String qVar = toString();
            String obj5 = timeUnit2.toString();
            Locale locale = Locale.ROOT;
            String lowerCase = obj5.toLowerCase(locale);
            StringBuilder j12 = j.j(j8, "Waited ", " ");
            j12.append(timeUnit2.toString().toLowerCase(locale));
            String sb2 = j12.toString();
            if (nanos + 1000 < j3) {
                String A10 = C0212a.A(sb2, " (plus ");
                long j13 = -nanos;
                long convert = timeUnit2.convert(j13, TimeUnit.NANOSECONDS);
                long nanos2 = j13 - timeUnit2.toNanos(convert);
                int i2 = (convert > j3 ? 1 : (convert == j3 ? 0 : -1));
                boolean z3 = (i2 == 0 || nanos2 > 1000) ? z : false;
                if (i2 > 0) {
                    String str = A10 + convert + " " + lowerCase;
                    if (z3) {
                        str = C0212a.A(str, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    }
                    A10 = C0212a.A(str, " ");
                }
                if (z3) {
                    A10 = A10 + nanos2 + " nanoseconds ";
                }
                sb2 = C0212a.A(A10, "delay)");
            }
            if (isDone()) {
                throw new TimeoutException(C0212a.A(sb2, " but future completed as timeout expired"));
            }
            throw new TimeoutException(C0212a.B(sb2, " for ", qVar));
        }
        throw new InterruptedException();
    }

    public boolean isCancelled() {
        return this.value instanceof C0123b;
    }

    public boolean isDone() {
        boolean z;
        Object obj = this.value;
        if (obj != null) {
            z = true;
        } else {
            z = false;
        }
        return (!(obj instanceof C0128g)) & z;
    }

    public final void maybePropagateCancellationTo(Future<?> future) {
        boolean z;
        if (future != null) {
            z = true;
        } else {
            z = false;
        }
        if (z && isCancelled()) {
            future.cancel(wasInterrupted());
        }
    }

    public String pendingToString() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    public boolean set(Object obj) {
        if (obj == null) {
            obj = NULL;
        }
        if (!ATOMIC_HELPER.b(this, (Object) null, obj)) {
            return false;
        }
        c(this, false);
        return true;
    }

    public boolean setException(Throwable th) {
        th.getClass();
        if (!ATOMIC_HELPER.b(this, (Object) null, new C0125d(th))) {
            return false;
        }
        c(this, false);
        return true;
    }

    public boolean setFuture(ListenableFuture listenableFuture) {
        C0125d dVar;
        listenableFuture.getClass();
        Object obj = this.value;
        if (obj == null) {
            if (listenableFuture.isDone()) {
                if (ATOMIC_HELPER.b(this, (Object) null, f(listenableFuture))) {
                    c(this, false);
                    return true;
                }
                return false;
            }
            C0128g gVar = new C0128g(this, listenableFuture);
            if (ATOMIC_HELPER.b(this, (Object) null, gVar)) {
                try {
                    listenableFuture.addListener(gVar, r.INSTANCE);
                    return true;
                } catch (Throwable th) {
                    try {
                        dVar = new C0125d(th);
                    } catch (Error | Exception unused) {
                        dVar = C0125d.b;
                    }
                    ATOMIC_HELPER.b(this, gVar, dVar);
                    return true;
                }
            } else {
                obj = this.value;
            }
        }
        if (obj instanceof C0123b) {
            listenableFuture.cancel(((C0123b) obj).f1562a);
        }
        return false;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb2.append(getClass().getSimpleName());
        } else {
            sb2.append(getClass().getName());
        }
        sb2.append('@');
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append("[status=");
        if (isCancelled()) {
            sb2.append("CANCELLED");
        } else if (isDone()) {
            a(sb2);
        } else {
            int length = sb2.length();
            sb2.append("PENDING");
            Object obj = this.value;
            if (obj instanceof C0128g) {
                sb2.append(", setFuture=[");
                ListenableFuture listenableFuture = ((C0128g) obj).e;
                if (listenableFuture == this) {
                    try {
                        sb2.append("this future");
                    } catch (Exception | StackOverflowError e) {
                        sb2.append("Exception thrown from implementation: ");
                        sb2.append(e.getClass());
                    }
                } else {
                    sb2.append(listenableFuture);
                }
                sb2.append("]");
            } else {
                try {
                    str = pendingToString();
                    if (Gd.a.h0(str)) {
                        str = null;
                    }
                } catch (Exception | StackOverflowError e7) {
                    str = "Exception thrown from implementation: " + e7.getClass();
                }
                if (str != null) {
                    j.z(sb2, ", info=[", str, "]");
                }
            }
            if (isDone()) {
                sb2.delete(length, sb2.length());
                a(sb2);
            }
        }
        sb2.append("]");
        return sb2.toString();
    }

    public final Throwable tryInternalFastPathGetFailure() {
        if (!(this instanceof i)) {
            return null;
        }
        Object obj = this.value;
        if (obj instanceof C0125d) {
            return ((C0125d) obj).f1563a;
        }
        return null;
    }

    public final boolean wasInterrupted() {
        Object obj = this.value;
        if (!(obj instanceof C0123b) || !((C0123b) obj).f1562a) {
            return false;
        }
        return true;
    }

    public void afterDone() {
    }

    public void interruptTask() {
    }

    public Object get() {
        Object obj;
        p pVar = p.f1570c;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) && (!(obj2 instanceof C0128g))) {
                return e(obj2);
            }
            p pVar2 = this.waiters;
            if (pVar2 != pVar) {
                p pVar3 = new p();
                do {
                    ATOMIC_HELPER.f(pVar3, pVar2);
                    if (ATOMIC_HELPER.c(this, pVar2, pVar3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                g(pVar3);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof C0128g))));
                        return e(obj);
                    }
                    pVar2 = this.waiters;
                } while (pVar2 != pVar);
            }
            Object obj3 = this.value;
            Objects.requireNonNull(obj3);
            return e(obj3);
        }
        throw new InterruptedException();
    }
}
