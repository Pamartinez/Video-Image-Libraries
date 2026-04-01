package cg;

import Ae.c;
import He.F;
import Qe.B;
import Vf.C0884v;
import Vf.x0;
import Wf.b;
import c0.C0086a;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.j;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final B f4016a = new B("CLOSED", 2);
    public static final B b = new B("UNDEFINED", 2);

    /* renamed from: c  reason: collision with root package name */
    public static final B f4017c = new B("REUSABLE_CLAIMED", 2);
    public static final B d = new B("NO_THREAD_ELEMENTS", 2);
    public static final C0884v e = new C0884v(4);
    public static final C0884v f = new C0884v(5);
    public static final C0884v g = new C0884v(6);

    public static final void a(int i2) {
        if (i2 < 1) {
            throw new IllegalArgumentException(C0086a.i(i2, "Expected positive parallelism level, but got ").toString());
        }
    }

    public static final Object b(s sVar, long j2, c cVar) {
        while (true) {
            if (sVar.f4032c >= j2 && !sVar.d()) {
                return sVar;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b.f4018a;
            Object obj = atomicReferenceFieldUpdater.get(sVar);
            B b5 = f4016a;
            if (obj == b5) {
                return b5;
            }
            s sVar2 = (s) ((b) obj);
            if (sVar2 == null) {
                sVar2 = (s) cVar.invoke(Long.valueOf(sVar.f4032c + 1), sVar);
                while (true) {
                    if (!atomicReferenceFieldUpdater.compareAndSet(sVar, (Object) null, sVar2)) {
                        if (atomicReferenceFieldUpdater.get(sVar) != null) {
                            break;
                        }
                    } else if (sVar.d()) {
                        sVar.e();
                    }
                }
            }
            sVar = sVar2;
        }
    }

    public static final s c(Object obj) {
        if (obj != f4016a) {
            return (s) obj;
        }
        throw new IllegalStateException("Does not contain segment");
    }

    public static final void d(Throwable th, C1232h hVar) {
        Throwable th2;
        for (b bVar : d.f4019a) {
            try {
                bVar.getClass();
            } catch (Throwable th3) {
                if (th == th3) {
                    th2 = th;
                } else {
                    th2 = new RuntimeException("Exception while trying to handle coroutine exception", th3);
                    F.e(th2, th);
                }
                Thread currentThread = Thread.currentThread();
                currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th2);
            }
        }
        try {
            F.e(th, new e(hVar));
        } catch (Throwable unused) {
        }
        Thread currentThread2 = Thread.currentThread();
        currentThread2.getUncaughtExceptionHandler().uncaughtException(currentThread2, th);
    }

    public static final boolean e(Object obj) {
        if (obj == f4016a) {
            return true;
        }
        return false;
    }

    public static final Object f(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(obj2);
        return arrayList;
    }

    public static final void g(C1232h hVar, Object obj) {
        if (obj != d) {
            if (obj instanceof w) {
                w wVar = (w) obj;
                x0[] x0VarArr = wVar.b;
                int length = x0VarArr.length - 1;
                if (length >= 0) {
                    x0 x0Var = x0VarArr[length];
                    j.b((Object) null);
                    Object obj2 = wVar.f4036a[length];
                    throw null;
                }
                return;
            }
            Object fold = hVar.fold((Object) null, f);
            j.c(fold, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
            A.a.t(fold);
            throw null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008a, code lost:
        if (r5.Y() != false) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a0, code lost:
        if (r5.Y() != false) goto L_0x00a2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void h(java.lang.Object r9, qe.C1227c r10) {
        /*
            boolean r0 = r10 instanceof cg.f
            if (r0 == 0) goto L_0x00b0
            cg.f r10 = (cg.f) r10
            Vf.x r0 = r10.g
            qe.c r1 = r10.f4020h
            java.lang.Throwable r2 = me.k.a(r9)
            if (r2 != 0) goto L_0x0012
            r3 = r9
            goto L_0x0018
        L_0x0012:
            Vf.u r3 = new Vf.u
            r4 = 0
            r3.<init>(r2, r4)
        L_0x0018:
            qe.h r2 = r1.getContext()
            boolean r2 = r0.j(r2)
            r4 = 1
            if (r2 == 0) goto L_0x002f
            r10.f4021i = r3
            r10.f = r4
            qe.h r9 = r1.getContext()
            r0.i(r9, r10)
            return
        L_0x002f:
            Vf.X r0 = Vf.y0.a()
            long r5 = r0.d
            r7 = 4294967296(0x100000000, double:2.121995791E-314)
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 < 0) goto L_0x0046
            r10.f4021i = r3
            r10.f = r4
            r0.m(r10)
            goto L_0x00aa
        L_0x0046:
            r0.p(r4)
            qe.h r2 = r1.getContext()     // Catch:{ all -> 0x006b }
            Vf.y r3 = Vf.C0887y.e     // Catch:{ all -> 0x006b }
            qe.f r2 = r2.get(r3)     // Catch:{ all -> 0x006b }
            Vf.e0 r2 = (Vf.C0867e0) r2     // Catch:{ all -> 0x006b }
            if (r2 == 0) goto L_0x006d
            boolean r3 = r2.isActive()     // Catch:{ all -> 0x006b }
            if (r3 != 0) goto L_0x006d
            Vf.n0 r2 = (Vf.n0) r2     // Catch:{ all -> 0x006b }
            java.util.concurrent.CancellationException r9 = r2.v()     // Catch:{ all -> 0x006b }
            me.j r9 = L2.a.l(r9)     // Catch:{ all -> 0x006b }
            r10.resumeWith(r9)     // Catch:{ all -> 0x006b }
            goto L_0x008f
        L_0x006b:
            r9 = move-exception
            goto L_0x00a6
        L_0x006d:
            java.lang.Object r2 = r10.f4022j     // Catch:{ all -> 0x006b }
            qe.h r3 = r1.getContext()     // Catch:{ all -> 0x006b }
            java.lang.Object r2 = l(r3, r2)     // Catch:{ all -> 0x006b }
            Qe.B r5 = d     // Catch:{ all -> 0x006b }
            if (r2 == r5) goto L_0x0080
            Vf.C0 r5 = Vf.D.v(r1, r3, r2)     // Catch:{ all -> 0x006b }
            goto L_0x0081
        L_0x0080:
            r5 = 0
        L_0x0081:
            r1.resumeWith(r9)     // Catch:{ all -> 0x0099 }
            if (r5 == 0) goto L_0x008c
            boolean r9 = r5.Y()     // Catch:{ all -> 0x006b }
            if (r9 == 0) goto L_0x008f
        L_0x008c:
            g(r3, r2)     // Catch:{ all -> 0x006b }
        L_0x008f:
            boolean r9 = r0.r()     // Catch:{ all -> 0x006b }
            if (r9 != 0) goto L_0x008f
        L_0x0095:
            r0.l(r4)
            goto L_0x00aa
        L_0x0099:
            r9 = move-exception
            if (r5 == 0) goto L_0x00a2
            boolean r1 = r5.Y()     // Catch:{ all -> 0x006b }
            if (r1 == 0) goto L_0x00a5
        L_0x00a2:
            g(r3, r2)     // Catch:{ all -> 0x006b }
        L_0x00a5:
            throw r9     // Catch:{ all -> 0x006b }
        L_0x00a6:
            r10.h(r9)     // Catch:{ all -> 0x00ab }
            goto L_0x0095
        L_0x00aa:
            return
        L_0x00ab:
            r9 = move-exception
            r0.l(r4)
            throw r9
        L_0x00b0:
            r10.resumeWith(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cg.a.h(java.lang.Object, qe.c):void");
    }

    public static final long i(String str, long j2, long j3, long j8) {
        String str2;
        Long l;
        boolean z;
        int i2;
        int i7;
        String str3 = str;
        long j10 = j3;
        long j11 = j8;
        int i8 = u.f4034a;
        try {
            str2 = System.getProperty(str3);
        } catch (SecurityException unused) {
            str2 = null;
        }
        if (str2 == null) {
            return j2;
        }
        j.e(str2, "<this>");
        int length = str2.length();
        if (length != 0) {
            int i10 = 0;
            char charAt = str2.charAt(0);
            long j12 = -9223372036854775807L;
            if (j.f(charAt, 48) < 0) {
                z = true;
                if (length != 1) {
                    if (charAt == '+') {
                        z = false;
                        i10 = 1;
                    } else if (charAt == '-') {
                        j12 = Long.MIN_VALUE;
                        i10 = 1;
                    }
                }
            } else {
                z = false;
            }
            long j13 = 0;
            long j14 = -256204778801521550L;
            while (true) {
                if (i10 < length) {
                    int digit = Character.digit(str2.charAt(i10), 10);
                    if (digit < 0) {
                        break;
                    }
                    if (j13 < j14) {
                        if (j14 != -256204778801521550L) {
                            break;
                        }
                        i7 = length;
                        i2 = i10;
                        j14 = j12 / ((long) 10);
                        if (j13 < j14) {
                            break;
                        }
                    } else {
                        i7 = length;
                        i2 = i10;
                    }
                    long j15 = j13 * ((long) 10);
                    long j16 = (long) digit;
                    if (j15 < j12 + j16) {
                        break;
                    }
                    j13 = j15 - j16;
                    i10 = i2 + 1;
                    length = i7;
                } else if (z) {
                    l = Long.valueOf(j13);
                } else {
                    l = Long.valueOf(-j13);
                }
            }
        }
        l = null;
        if (l != null) {
            long longValue = l.longValue();
            if (j10 <= longValue && longValue <= j11) {
                return longValue;
            }
            throw new IllegalStateException(("System property '" + str3 + "' should be in range " + j10 + ".." + j11 + ", but is '" + longValue + '\'').toString());
        }
        throw new IllegalStateException(("System property '" + str3 + "' has unrecognized value '" + str2 + '\'').toString());
    }

    public static int j(int i2, int i7, String str) {
        int i8;
        if ((i7 & 8) != 0) {
            i8 = Integer.MAX_VALUE;
        } else {
            i8 = 2097150;
        }
        return (int) i(str, (long) i2, (long) 1, (long) i8);
    }

    public static final Object k(C1232h hVar) {
        Object fold = hVar.fold(0, e);
        j.b(fold);
        return fold;
    }

    public static final Object l(C1232h hVar, Object obj) {
        if (obj == null) {
            obj = k(hVar);
        }
        if (obj == 0) {
            return d;
        }
        if (obj instanceof Integer) {
            return hVar.fold(new w(((Number) obj).intValue(), hVar), g);
        }
        A.a.t(obj);
        throw null;
    }
}
