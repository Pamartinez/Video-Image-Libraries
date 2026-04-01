package com.google.common.util.concurrent;

import sun.misc.Unsafe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends C0122a {

    /* renamed from: a  reason: collision with root package name */
    public static final Unsafe f1568a;
    public static final long b;

    /* renamed from: c  reason: collision with root package name */
    public static final long f1569c;
    public static final long d;
    public static final long e;
    public static final long f;

    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.Object, java.security.PrivilegedExceptionAction] */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0068, code lost:
        throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        r1 = (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new java.lang.Object());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
    static {
        /*
            java.lang.Class<com.google.common.util.concurrent.p> r0 = com.google.common.util.concurrent.p.class
            sun.misc.Unsafe r1 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0007 }
            goto L_0x0012
        L_0x0007:
            com.google.common.util.concurrent.n r1 = new com.google.common.util.concurrent.n     // Catch:{ PrivilegedActionException -> 0x005c }
            r1.<init>()     // Catch:{ PrivilegedActionException -> 0x005c }
            java.lang.Object r1 = java.security.AccessController.doPrivileged(r1)     // Catch:{ PrivilegedActionException -> 0x005c }
            sun.misc.Unsafe r1 = (sun.misc.Unsafe) r1     // Catch:{ PrivilegedActionException -> 0x005c }
        L_0x0012:
            java.lang.Class<com.google.common.util.concurrent.q> r2 = com.google.common.util.concurrent.q.class
            java.lang.String r3 = "waiters"
            java.lang.reflect.Field r3 = r2.getDeclaredField(r3)     // Catch:{ NoSuchFieldException -> 0x0055 }
            long r3 = r1.objectFieldOffset(r3)     // Catch:{ NoSuchFieldException -> 0x0055 }
            f1569c = r3     // Catch:{ NoSuchFieldException -> 0x0055 }
            java.lang.String r3 = "listeners"
            java.lang.reflect.Field r3 = r2.getDeclaredField(r3)     // Catch:{ NoSuchFieldException -> 0x0055 }
            long r3 = r1.objectFieldOffset(r3)     // Catch:{ NoSuchFieldException -> 0x0055 }
            b = r3     // Catch:{ NoSuchFieldException -> 0x0055 }
            java.lang.String r3 = "value"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch:{ NoSuchFieldException -> 0x0055 }
            long r2 = r1.objectFieldOffset(r2)     // Catch:{ NoSuchFieldException -> 0x0055 }
            d = r2     // Catch:{ NoSuchFieldException -> 0x0055 }
            java.lang.String r2 = "a"
            java.lang.reflect.Field r2 = r0.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0055 }
            long r2 = r1.objectFieldOffset(r2)     // Catch:{ NoSuchFieldException -> 0x0055 }
            e = r2     // Catch:{ NoSuchFieldException -> 0x0055 }
            java.lang.String r2 = "b"
            java.lang.reflect.Field r0 = r0.getDeclaredField(r2)     // Catch:{ NoSuchFieldException -> 0x0055 }
            long r2 = r1.objectFieldOffset(r0)     // Catch:{ NoSuchFieldException -> 0x0055 }
            f = r2     // Catch:{ NoSuchFieldException -> 0x0055 }
            f1568a = r1     // Catch:{ NoSuchFieldException -> 0x0055 }
            return
        L_0x0055:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x005c:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Could not initialize intrinsics"
            java.lang.Throwable r0 = r0.getCause()
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.o.<clinit>():void");
    }

    public final boolean a(q qVar, C0126e eVar, C0126e eVar2) {
        return l.a(f1568a, qVar, b, eVar, eVar2);
    }

    public final boolean b(q qVar, Object obj, Object obj2) {
        return m.a(f1568a, qVar, d, obj, obj2);
    }

    public final boolean c(q qVar, p pVar, p pVar2) {
        return k.a(f1568a, qVar, f1569c, pVar, pVar2);
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final com.google.common.util.concurrent.C0126e d(com.google.common.util.concurrent.q r3) {
        /*
            r2 = this;
        L_0x0000:
            com.google.common.util.concurrent.e r0 = r3.listeners
            com.google.common.util.concurrent.e r1 = com.google.common.util.concurrent.C0126e.d
            if (r1 != r0) goto L_0x0009
            goto L_0x000f
        L_0x0009:
            boolean r1 = r2.a(r3, r0, r1)
            if (r1 == 0) goto L_0x0000
        L_0x000f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.o.d(com.google.common.util.concurrent.q):com.google.common.util.concurrent.e");
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final com.google.common.util.concurrent.p e(com.google.common.util.concurrent.q r3) {
        /*
            r2 = this;
        L_0x0000:
            com.google.common.util.concurrent.p r0 = r3.waiters
            com.google.common.util.concurrent.p r1 = com.google.common.util.concurrent.p.f1570c
            if (r1 != r0) goto L_0x0009
            goto L_0x000f
        L_0x0009:
            boolean r1 = r2.c(r3, r0, r1)
            if (r1 == 0) goto L_0x0000
        L_0x000f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.o.e(com.google.common.util.concurrent.q):com.google.common.util.concurrent.p");
    }

    public final void f(p pVar, p pVar2) {
        f1568a.putObject(pVar, f, pVar2);
    }

    public final void g(p pVar, Thread thread) {
        f1568a.putObject(pVar, e, thread);
    }
}
