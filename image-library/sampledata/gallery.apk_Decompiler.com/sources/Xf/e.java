package Xf;

import Ae.b;
import Ae.d;
import Qe.B;
import Vf.C0873j;
import Vf.C0875l;
import Vf.E0;
import Zf.g;
import cg.a;
import cg.s;
import i.C0212a;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.y;
import me.x;
import qe.C1227c;
import re.C1245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class e implements i {
    public static final /* synthetic */ AtomicLongFieldUpdater e;
    public static final /* synthetic */ AtomicLongFieldUpdater f;
    public static final /* synthetic */ AtomicLongFieldUpdater g;

    /* renamed from: h  reason: collision with root package name */
    public static final /* synthetic */ AtomicLongFieldUpdater f3899h;

    /* renamed from: i  reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f3900i;

    /* renamed from: j  reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f3901j;
    public static final /* synthetic */ AtomicReferenceFieldUpdater k;
    public static final /* synthetic */ AtomicReferenceFieldUpdater l;
    public static final /* synthetic */ AtomicReferenceFieldUpdater m;
    private volatile /* synthetic */ Object _closeCause$volatile;
    private volatile /* synthetic */ long bufferEnd$volatile;
    private volatile /* synthetic */ Object bufferEndSegment$volatile;
    private volatile /* synthetic */ Object closeHandler$volatile;
    private volatile /* synthetic */ long completedExpandBuffersAndPauseFlag$volatile;
    public final int d;
    private volatile /* synthetic */ Object receiveSegment$volatile;
    private volatile /* synthetic */ long receivers$volatile;
    private volatile /* synthetic */ Object sendSegment$volatile;
    private volatile /* synthetic */ long sendersAndCloseStatus$volatile;

    static {
        Class<e> cls = e.class;
        e = AtomicLongFieldUpdater.newUpdater(cls, "sendersAndCloseStatus$volatile");
        f = AtomicLongFieldUpdater.newUpdater(cls, "receivers$volatile");
        g = AtomicLongFieldUpdater.newUpdater(cls, "bufferEnd$volatile");
        f3899h = AtomicLongFieldUpdater.newUpdater(cls, "completedExpandBuffersAndPauseFlag$volatile");
        Class<Object> cls2 = Object.class;
        f3900i = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "sendSegment$volatile");
        f3901j = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "receiveSegment$volatile");
        k = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "bufferEndSegment$volatile");
        l = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_closeCause$volatile");
        m = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "closeHandler$volatile");
    }

    public e(int i2) {
        long j2;
        this.d = i2;
        if (i2 >= 0) {
            m mVar = g.f3902a;
            if (i2 == 0) {
                j2 = 0;
            } else if (i2 != Integer.MAX_VALUE) {
                j2 = (long) i2;
            } else {
                j2 = Long.MAX_VALUE;
            }
            this.bufferEnd$volatile = j2;
            this.completedExpandBuffersAndPauseFlag$volatile = g.get(this);
            m mVar2 = new m(0, (m) null, this, 3);
            this.sendSegment$volatile = mVar2;
            this.receiveSegment$volatile = mVar2;
            if (w()) {
                mVar2 = g.f3902a;
                j.c(mVar2, "null cannot be cast to non-null type kotlinx.coroutines.channels.ChannelSegment<E of kotlinx.coroutines.channels.BufferedChannel>");
            }
            this.bufferEndSegment$volatile = mVar2;
            this._closeCause$volatile = g.s;
            return;
        }
        throw new IllegalArgumentException(C0212a.j(i2, "Invalid channel capacity: ", ", should be >=0").toString());
    }

    public static boolean D(Object obj) {
        if (obj instanceof C0873j) {
            return g.a((C0873j) obj, x.f4917a, (d) null);
        }
        throw new IllegalStateException(("Unexpected waiter: " + obj).toString());
    }

    public static final m b(e eVar, long j2, m mVar) {
        Object b;
        e eVar2;
        m mVar2 = g.f3902a;
        f fVar = f.d;
        loop0:
        while (true) {
            b = a.b(mVar, j2, fVar);
            if (a.e(b)) {
                break;
            }
            s c5 = a.c(b);
            while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3900i;
                s sVar = (s) atomicReferenceFieldUpdater.get(eVar);
                if (sVar.f4032c >= c5.f4032c) {
                    break loop0;
                } else if (c5.j()) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(eVar, sVar, c5)) {
                        if (atomicReferenceFieldUpdater.get(eVar) != sVar) {
                            if (c5.f()) {
                                c5.e();
                            }
                        }
                    }
                    if (sVar.f()) {
                        sVar.e();
                    }
                }
            }
        }
        boolean e7 = a.e(b);
        AtomicLongFieldUpdater atomicLongFieldUpdater = f;
        if (e7) {
            eVar.u();
            if (mVar.f4032c * ((long) g.b) < atomicLongFieldUpdater.get(eVar)) {
                mVar.b();
                return null;
            }
        } else {
            m mVar3 = (m) a.c(b);
            long j3 = mVar3.f4032c;
            if (j3 <= j2) {
                return mVar3;
            }
            long j8 = ((long) g.b) * j3;
            while (true) {
                long j10 = e.get(eVar);
                long j11 = 1152921504606846975L & j10;
                if (j11 >= j8) {
                    eVar2 = eVar;
                    break;
                }
                eVar2 = eVar;
                if (e.compareAndSet(eVar2, j10, (((long) ((int) (j10 >> 60))) << 60) + j11)) {
                    break;
                }
                eVar = eVar2;
            }
            if (j3 * ((long) g.b) < atomicLongFieldUpdater.get(eVar2)) {
                mVar3.b();
            }
        }
        return null;
    }

    public static final void c(e eVar, Object obj, C0875l lVar) {
        lVar.resumeWith(L2.a.l(eVar.q()));
    }

    public static final int d(e eVar, m mVar, int i2, Object obj, long j2, Object obj2, boolean z) {
        mVar.n(i2, obj);
        if (z) {
            return eVar.F(mVar, i2, obj, j2, obj2, z);
        }
        Object l8 = mVar.l(i2);
        if (l8 == null) {
            if (eVar.g(j2)) {
                if (mVar.k(i2, (Object) null, g.d)) {
                    return 1;
                }
            } else if (obj2 == null) {
                return 3;
            } else {
                if (mVar.k(i2, (Object) null, obj2)) {
                    return 2;
                }
            }
        } else if (l8 instanceof E0) {
            mVar.n(i2, (Object) null);
            if (eVar.C(l8, obj)) {
                mVar.o(i2, g.f3905i);
                return 0;
            }
            B b = g.k;
            if (mVar.f.getAndSet((i2 * 2) + 1, b) == b) {
                return 5;
            }
            mVar.m(i2, true);
            return 5;
        }
        return eVar.F(mVar, i2, obj, j2, obj2, z);
    }

    public static void s(e eVar) {
        AtomicLongFieldUpdater atomicLongFieldUpdater = f3899h;
        if ((atomicLongFieldUpdater.addAndGet(eVar, 1) & 4611686018427387904L) != 0) {
            do {
            } while ((atomicLongFieldUpdater.get(eVar) & 4611686018427387904L) != 0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object z(Xf.e r13, se.C1271c r14) {
        /*
            boolean r0 = r14 instanceof Xf.c
            if (r0 == 0) goto L_0x0014
            r0 = r14
            Xf.c r0 = (Xf.c) r0
            int r1 = r0.f
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0014
            int r1 = r1 - r2
            r0.f = r1
        L_0x0012:
            r6 = r0
            goto L_0x001a
        L_0x0014:
            Xf.c r0 = new Xf.c
            r0.<init>(r13, r14)
            goto L_0x0012
        L_0x001a:
            java.lang.Object r14 = r6.d
            re.a r0 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r6.f
            r2 = 1
            if (r1 == 0) goto L_0x0035
            if (r1 != r2) goto L_0x002d
            L2.a.A(r14)
            Xf.l r14 = (Xf.l) r14
            java.lang.Object r13 = r14.f3911a
            return r13
        L_0x002d:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0035:
            L2.a.A(r14)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r14 = f3901j
            java.lang.Object r14 = r14.get(r13)
            Xf.m r14 = (Xf.m) r14
        L_0x0040:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = e
            long r3 = r1.get(r13)
            boolean r1 = r13.t(r3, r2)
            if (r1 == 0) goto L_0x0056
            java.lang.Throwable r13 = r13.p()
            Xf.j r14 = new Xf.j
            r14.<init>(r13)
            return r14
        L_0x0056:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = f
            long r4 = r1.getAndIncrement(r13)
            int r1 = Xf.g.b
            long r7 = (long) r1
            long r9 = r4 / r7
            long r7 = r4 % r7
            int r3 = (int) r7
            long r7 = r14.f4032c
            int r1 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r1 == 0) goto L_0x0073
            Xf.m r1 = r13.o(r9, r14)
            if (r1 != 0) goto L_0x0071
            goto L_0x0040
        L_0x0071:
            r8 = r1
            goto L_0x0074
        L_0x0073:
            r8 = r14
        L_0x0074:
            r12 = 0
            r7 = r13
            r9 = r3
            r10 = r4
            java.lang.Object r13 = r7.E(r8, r9, r10, r12)
            r1 = r7
            Qe.B r14 = Xf.g.m
            if (r13 == r14) goto L_0x00a6
            Qe.B r14 = Xf.g.f3907o
            if (r13 != r14) goto L_0x0093
            long r13 = r1.r()
            int r13 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r13 >= 0) goto L_0x0090
            r8.b()
        L_0x0090:
            r13 = r1
            r14 = r8
            goto L_0x0040
        L_0x0093:
            Qe.B r14 = Xf.g.n
            if (r13 != r14) goto L_0x00a2
            r6.f = r2
            r2 = r8
            java.lang.Object r13 = r1.A(r2, r3, r4, r6)
            if (r13 != r0) goto L_0x00a1
            return r0
        L_0x00a1:
            return r13
        L_0x00a2:
            r8.b()
            return r13
        L_0x00a6:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "unexpected"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: Xf.e.z(Xf.e, se.c):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object A(Xf.m r11, int r12, long r13, se.C1271c r15) {
        /*
            r10 = this;
            boolean r0 = r15 instanceof Xf.d
            if (r0 == 0) goto L_0x0013
            r0 = r15
            Xf.d r0 = (Xf.d) r0
            int r1 = r0.f
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.f = r1
            goto L_0x0018
        L_0x0013:
            Xf.d r0 = new Xf.d
            r0.<init>(r10, r15)
        L_0x0018:
            java.lang.Object r15 = r0.d
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            int r2 = r0.f
            r3 = 1
            if (r2 == 0) goto L_0x0030
            if (r2 != r3) goto L_0x0028
            L2.a.A(r15)
            goto L_0x00f6
        L_0x0028:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0030:
            L2.a.A(r15)
            r0.f = r3
            qe.c r15 = L1.d.m(r0)
            Vf.l r15 = Vf.D.k(r15)
            Xf.s r9 = new Xf.s     // Catch:{ all -> 0x0053 }
            r9.<init>(r15)     // Catch:{ all -> 0x0053 }
            r4 = r10
            r5 = r11
            r6 = r12
            r7 = r13
            java.lang.Object r10 = r4.E(r5, r6, r7, r9)     // Catch:{ all -> 0x0053 }
            Qe.B r11 = Xf.g.m     // Catch:{ all -> 0x0053 }
            if (r10 != r11) goto L_0x0057
            r9.a(r5, r6)     // Catch:{ all -> 0x0053 }
            goto L_0x00ed
        L_0x0053:
            r0 = move-exception
            r10 = r0
            goto L_0x00fb
        L_0x0057:
            Qe.B r11 = Xf.g.f3907o     // Catch:{ all -> 0x0053 }
            r12 = 0
            if (r10 != r11) goto L_0x00e4
            long r10 = r4.r()     // Catch:{ all -> 0x0053 }
            int r10 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x0067
            r5.b()     // Catch:{ all -> 0x0053 }
        L_0x0067:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r10 = f3901j     // Catch:{ all -> 0x0053 }
            java.lang.Object r10 = r10.get(r4)     // Catch:{ all -> 0x0053 }
            Xf.m r10 = (Xf.m) r10     // Catch:{ all -> 0x0053 }
        L_0x006f:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r11 = e     // Catch:{ all -> 0x0053 }
            long r13 = r11.get(r4)     // Catch:{ all -> 0x0053 }
            boolean r11 = r4.t(r13, r3)     // Catch:{ all -> 0x0053 }
            if (r11 == 0) goto L_0x008e
            java.lang.Throwable r10 = r4.p()     // Catch:{ all -> 0x0053 }
            Xf.j r11 = new Xf.j     // Catch:{ all -> 0x0053 }
            r11.<init>(r10)     // Catch:{ all -> 0x0053 }
            Xf.l r10 = new Xf.l     // Catch:{ all -> 0x0053 }
            r10.<init>(r11)     // Catch:{ all -> 0x0053 }
            r15.resumeWith(r10)     // Catch:{ all -> 0x0053 }
            goto L_0x00ed
        L_0x008e:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r11 = f     // Catch:{ all -> 0x0053 }
            long r7 = r11.getAndIncrement(r4)     // Catch:{ all -> 0x0053 }
            int r11 = Xf.g.b     // Catch:{ all -> 0x0053 }
            long r13 = (long) r11     // Catch:{ all -> 0x0053 }
            long r5 = r7 / r13
            long r13 = r7 % r13
            int r11 = (int) r13     // Catch:{ all -> 0x0053 }
            long r13 = r10.f4032c     // Catch:{ all -> 0x0053 }
            int r13 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r13 == 0) goto L_0x00ac
            Xf.m r13 = r4.o(r5, r10)     // Catch:{ all -> 0x0053 }
            if (r13 != 0) goto L_0x00a9
            goto L_0x006f
        L_0x00a9:
            r5 = r13
        L_0x00aa:
            r6 = r11
            goto L_0x00ae
        L_0x00ac:
            r5 = r10
            goto L_0x00aa
        L_0x00ae:
            java.lang.Object r10 = r4.E(r5, r6, r7, r9)     // Catch:{ all -> 0x0053 }
            r13 = r5
            Qe.B r11 = Xf.g.m     // Catch:{ all -> 0x0053 }
            if (r10 != r11) goto L_0x00bb
            r9.a(r13, r6)     // Catch:{ all -> 0x0053 }
            goto L_0x00ed
        L_0x00bb:
            Qe.B r11 = Xf.g.f3907o     // Catch:{ all -> 0x0053 }
            if (r10 != r11) goto L_0x00cc
            long r10 = r4.r()     // Catch:{ all -> 0x0053 }
            int r10 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x00ca
            r13.b()     // Catch:{ all -> 0x0053 }
        L_0x00ca:
            r10 = r13
            goto L_0x006f
        L_0x00cc:
            Qe.B r11 = Xf.g.n     // Catch:{ all -> 0x0053 }
            if (r10 == r11) goto L_0x00dc
            r13.b()     // Catch:{ all -> 0x0053 }
            Xf.l r11 = new Xf.l     // Catch:{ all -> 0x0053 }
            r11.<init>(r10)     // Catch:{ all -> 0x0053 }
        L_0x00d8:
            r15.z(r11, r12)     // Catch:{ all -> 0x0053 }
            goto L_0x00ed
        L_0x00dc:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0053 }
            java.lang.String r11 = "unexpected"
            r10.<init>(r11)     // Catch:{ all -> 0x0053 }
            throw r10     // Catch:{ all -> 0x0053 }
        L_0x00e4:
            r5.b()     // Catch:{ all -> 0x0053 }
            Xf.l r11 = new Xf.l     // Catch:{ all -> 0x0053 }
            r11.<init>(r10)     // Catch:{ all -> 0x0053 }
            goto L_0x00d8
        L_0x00ed:
            java.lang.Object r15 = r15.q()
            re.a r10 = re.C1245a.COROUTINE_SUSPENDED
            if (r15 != r1) goto L_0x00f6
            return r1
        L_0x00f6:
            Xf.l r15 = (Xf.l) r15
            java.lang.Object r10 = r15.f3911a
            return r10
        L_0x00fb:
            r15.y()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: Xf.e.A(Xf.m, int, long, se.c):java.lang.Object");
    }

    public final void B(E0 e02, boolean z) {
        Throwable th;
        if (e02 instanceof C0873j) {
            C1227c cVar = (C1227c) e02;
            if (z) {
                th = p();
                if (th == null) {
                    th = new NoSuchElementException("Channel was closed");
                }
            } else {
                th = q();
            }
            cVar.resumeWith(L2.a.l(th));
        } else if (e02 instanceof s) {
            ((s) e02).d.resumeWith(new l(new j(p())));
        } else if (e02 instanceof b) {
            b bVar = (b) e02;
            C0875l lVar = bVar.e;
            j.b(lVar);
            bVar.e = null;
            bVar.d = g.l;
            Throwable p6 = bVar.f.p();
            if (p6 == null) {
                lVar.resumeWith(Boolean.FALSE);
            } else {
                lVar.resumeWith(L2.a.l(p6));
            }
        } else {
            throw new IllegalStateException(("Unexpected waiter: " + e02).toString());
        }
    }

    public final boolean C(Object obj, Object obj2) {
        if (obj instanceof s) {
            return g.a(((s) obj).d, new l(obj2), (d) null);
        }
        if (obj instanceof b) {
            b bVar = (b) obj;
            C0875l lVar = bVar.e;
            j.b(lVar);
            bVar.e = null;
            bVar.d = obj2;
            return g.a(lVar, Boolean.TRUE, (d) null);
        } else if (obj instanceof C0873j) {
            return g.a((C0873j) obj, obj2, (d) null);
        } else {
            throw new IllegalStateException(("Unexpected receiver type: " + obj).toString());
        }
    }

    public final Object E(m mVar, int i2, long j2, Object obj) {
        Object l8 = mVar.l(i2);
        AtomicReferenceArray atomicReferenceArray = mVar.f;
        AtomicLongFieldUpdater atomicLongFieldUpdater = e;
        if (l8 == null) {
            if (j2 >= (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                if (obj == null) {
                    return g.n;
                }
                if (mVar.k(i2, l8, obj)) {
                    n();
                    return g.m;
                }
            }
        } else if (l8 == g.d && mVar.k(i2, l8, g.f3905i)) {
            n();
            Object obj2 = atomicReferenceArray.get(i2 * 2);
            mVar.n(i2, (Object) null);
            return obj2;
        }
        while (true) {
            Object l10 = mVar.l(i2);
            if (l10 == null || l10 == g.e) {
                if (j2 < (atomicLongFieldUpdater.get(this) & 1152921504606846975L)) {
                    if (mVar.k(i2, l10, g.f3904h)) {
                        n();
                        return g.f3907o;
                    }
                } else if (obj == null) {
                    return g.n;
                } else {
                    if (mVar.k(i2, l10, obj)) {
                        n();
                        return g.m;
                    }
                }
            } else if (l10 != g.d) {
                B b = g.f3906j;
                if (l10 == b) {
                    return g.f3907o;
                }
                if (l10 == g.f3904h) {
                    return g.f3907o;
                }
                if (l10 == g.l) {
                    n();
                    return g.f3907o;
                } else if (l10 != g.g && mVar.k(i2, l10, g.f)) {
                    boolean z = l10 instanceof v;
                    if (z) {
                        l10 = ((v) l10).f3912a;
                    }
                    if (D(l10)) {
                        mVar.o(i2, g.f3905i);
                        n();
                        Object obj3 = atomicReferenceArray.get(i2 * 2);
                        mVar.n(i2, (Object) null);
                        return obj3;
                    }
                    mVar.o(i2, b);
                    mVar.i();
                    if (z) {
                        n();
                    }
                    return g.f3907o;
                }
            } else if (mVar.k(i2, l10, g.f3905i)) {
                n();
                Object obj4 = atomicReferenceArray.get(i2 * 2);
                mVar.n(i2, (Object) null);
                return obj4;
            }
        }
    }

    public final int F(m mVar, int i2, Object obj, long j2, Object obj2, boolean z) {
        while (true) {
            Object l8 = mVar.l(i2);
            if (l8 == null) {
                if (!g(j2) || z) {
                    if (z) {
                        if (mVar.k(i2, (Object) null, g.f3906j)) {
                            mVar.i();
                            return 4;
                        }
                    } else if (obj2 == null) {
                        return 3;
                    } else {
                        if (mVar.k(i2, (Object) null, obj2)) {
                            return 2;
                        }
                    }
                } else if (mVar.k(i2, (Object) null, g.d)) {
                    break;
                }
            } else if (l8 != g.e) {
                B b = g.k;
                if (l8 == b) {
                    mVar.n(i2, (Object) null);
                    return 5;
                } else if (l8 == g.f3904h) {
                    mVar.n(i2, (Object) null);
                    return 5;
                } else if (l8 == g.l) {
                    mVar.n(i2, (Object) null);
                    u();
                    return 4;
                } else {
                    mVar.n(i2, (Object) null);
                    if (l8 instanceof v) {
                        l8 = ((v) l8).f3912a;
                    }
                    if (C(l8, obj)) {
                        mVar.o(i2, g.f3905i);
                        return 0;
                    }
                    if (mVar.f.getAndSet((i2 * 2) + 1, b) != b) {
                        mVar.m(i2, true);
                    }
                    return 5;
                }
            } else if (mVar.k(i2, l8, g.d)) {
                break;
            }
        }
        return 1;
    }

    public final void G(long j2) {
        AtomicLongFieldUpdater atomicLongFieldUpdater;
        e eVar;
        boolean z;
        e eVar2 = this;
        if (!eVar2.w()) {
            while (true) {
                atomicLongFieldUpdater = g;
                if (atomicLongFieldUpdater.get(eVar2) > j2) {
                    break;
                }
                eVar2 = this;
            }
            int i2 = g.f3903c;
            int i7 = 0;
            while (true) {
                AtomicLongFieldUpdater atomicLongFieldUpdater2 = f3899h;
                if (i7 < i2) {
                    long j3 = atomicLongFieldUpdater.get(eVar2);
                    if (j3 != (4611686018427387903L & atomicLongFieldUpdater2.get(eVar2)) || j3 != atomicLongFieldUpdater.get(eVar2)) {
                        i7++;
                    } else {
                        return;
                    }
                } else {
                    AtomicLongFieldUpdater atomicLongFieldUpdater3 = atomicLongFieldUpdater2;
                    while (true) {
                        long j8 = atomicLongFieldUpdater3.get(eVar);
                        if (atomicLongFieldUpdater3.compareAndSet(eVar, j8, (j8 & 4611686018427387903L) + 4611686018427387904L)) {
                            break;
                        }
                        eVar2 = this;
                    }
                    while (true) {
                        long j10 = atomicLongFieldUpdater.get(eVar);
                        long j11 = atomicLongFieldUpdater3.get(eVar);
                        long j12 = j11 & 4611686018427387903L;
                        if ((j11 & 4611686018427387904L) != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (j10 == j12 && j10 == atomicLongFieldUpdater.get(eVar)) {
                            break;
                        } else if (!z) {
                            eVar = this;
                            atomicLongFieldUpdater3.compareAndSet(eVar, j11, 4611686018427387904L + j12);
                        } else {
                            eVar = this;
                        }
                    }
                    while (true) {
                        long j13 = atomicLongFieldUpdater3.get(eVar);
                        if (!atomicLongFieldUpdater3.compareAndSet(eVar, j13, j13 & 4611686018427387903L)) {
                            eVar = this;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    public final void a(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new CancellationException("Channel was cancelled");
        }
        k(cancellationException, true);
    }

    public Object e(Object obj) {
        boolean z;
        E0 e02;
        AtomicLongFieldUpdater atomicLongFieldUpdater = e;
        long j2 = atomicLongFieldUpdater.get(this);
        boolean z3 = false;
        long j3 = 1152921504606846975L;
        if (t(j2, false)) {
            z = false;
        } else {
            z = !g(j2 & 1152921504606846975L);
        }
        k kVar = l.b;
        if (z) {
            return kVar;
        }
        B b = g.f3906j;
        m mVar = (m) f3900i.get(this);
        while (true) {
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j8 = andIncrement & j3;
            boolean t = t(andIncrement, z3);
            int i2 = g.b;
            long j10 = (long) i2;
            long j11 = j8 / j10;
            int i7 = (int) (j8 % j10);
            if (mVar.f4032c != j11) {
                m b5 = b(this, j11, mVar);
                if (b5 != null) {
                    mVar = b5;
                } else if (t) {
                    return new j(q());
                } else {
                    z3 = false;
                    j3 = 1152921504606846975L;
                }
            }
            int d2 = d(this, mVar, i7, obj, j8, b, t);
            x xVar = x.f4917a;
            if (d2 == 0) {
                mVar.b();
                return xVar;
            } else if (d2 == 1) {
                return xVar;
            } else {
                if (d2 != 2) {
                    if (d2 == 3) {
                        throw new IllegalStateException("unexpected");
                    } else if (d2 != 4) {
                        if (d2 == 5) {
                            mVar.b();
                        }
                        z3 = false;
                        j3 = 1152921504606846975L;
                    } else {
                        if (j8 < f.get(this)) {
                            mVar.b();
                        }
                        return new j(q());
                    }
                } else if (t) {
                    mVar.i();
                    return new j(q());
                } else {
                    if (b instanceof E0) {
                        e02 = (E0) b;
                    } else {
                        e02 = null;
                    }
                    if (e02 != null) {
                        e02.a(mVar, i7 + i2);
                    }
                    mVar.i();
                    return kVar;
                }
            }
        }
    }

    public final Object f(g gVar) {
        return z(this, gVar);
    }

    public final boolean g(long j2) {
        if (j2 < g.get(this) || j2 < f.get(this) + ((long) this.d)) {
            return true;
        }
        return false;
    }

    public final Object h() {
        m mVar;
        E0 e02;
        AtomicLongFieldUpdater atomicLongFieldUpdater = f;
        long j2 = atomicLongFieldUpdater.get(this);
        AtomicLongFieldUpdater atomicLongFieldUpdater2 = e;
        long j3 = atomicLongFieldUpdater2.get(this);
        if (t(j3, true)) {
            return new j(p());
        }
        int i2 = (j2 > (j3 & 1152921504606846975L) ? 1 : (j2 == (j3 & 1152921504606846975L) ? 0 : -1));
        k kVar = l.b;
        if (i2 >= 0) {
            return kVar;
        }
        B b = g.k;
        m mVar2 = (m) f3901j.get(this);
        while (!this.t(atomicLongFieldUpdater2.get(this), true)) {
            long andIncrement = atomicLongFieldUpdater.getAndIncrement(this);
            long j8 = (long) g.b;
            long j10 = andIncrement / j8;
            int i7 = (int) (andIncrement % j8);
            if (mVar2.f4032c != j10) {
                m o2 = this.o(j10, mVar2);
                if (o2 == null) {
                    continue;
                } else {
                    mVar = o2;
                }
            } else {
                mVar = mVar2;
            }
            e eVar = this;
            Object E = eVar.E(mVar, i7, andIncrement, b);
            mVar2 = mVar;
            if (E == g.m) {
                if (b instanceof E0) {
                    e02 = (E0) b;
                } else {
                    e02 = null;
                }
                if (e02 != null) {
                    e02.a(mVar2, i7);
                }
                eVar.G(andIncrement);
                mVar2.i();
                return kVar;
            } else if (E == g.f3907o) {
                if (andIncrement < eVar.r()) {
                    mVar2.b();
                }
                this = eVar;
            } else if (E != g.n) {
                mVar2.b();
                return E;
            } else {
                throw new IllegalStateException("unexpected");
            }
        }
        return new j(this.p());
    }

    /* JADX WARNING: Removed duplicated region for block: B:82:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x014f A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object i(java.lang.Object r23, qe.C1227c r24) {
        /*
            r22 = this;
            r0 = r22
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r8 = f3900i
            java.lang.Object r1 = r8.get(r0)
            Xf.m r1 = (Xf.m) r1
        L_0x000a:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r9 = e
            long r2 = r9.getAndIncrement(r0)
            r10 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            long r4 = r2 & r10
            r12 = 0
            boolean r7 = r0.t(r2, r12)
            int r13 = Xf.g.b
            long r2 = (long) r13
            long r14 = r4 / r2
            long r2 = r4 % r2
            int r2 = (int) r2
            r16 = r10
            long r10 = r1.f4032c
            int r3 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            me.x r10 = me.x.f4917a
            if (r3 == 0) goto L_0x0040
            Xf.m r3 = b(r0, r14, r1)
            if (r3 != 0) goto L_0x003f
            if (r7 == 0) goto L_0x000a
            java.lang.Object r0 = r22.y(r23, r24)
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            if (r0 != r1) goto L_0x0164
            return r0
        L_0x003f:
            r1 = r3
        L_0x0040:
            r6 = 0
            r3 = r23
            int r6 = d(r0, r1, r2, r3, r4, r6, r7)
            if (r6 == 0) goto L_0x0165
            r11 = 1
            if (r6 == r11) goto L_0x0164
            r14 = 2
            if (r6 == r14) goto L_0x0154
            java.util.concurrent.atomic.AtomicLongFieldUpdater r15 = f
            r3 = 5
            r7 = 4
            r12 = 3
            if (r6 == r12) goto L_0x0073
            if (r6 == r7) goto L_0x005f
            if (r6 == r3) goto L_0x005b
            goto L_0x000a
        L_0x005b:
            r1.b()
            goto L_0x000a
        L_0x005f:
            long r2 = r15.get(r0)
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x006a
            r1.b()
        L_0x006a:
            java.lang.Object r0 = r22.y(r23, r24)
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            if (r0 != r1) goto L_0x0164
            return r0
        L_0x0073:
            qe.c r6 = L1.d.m(r24)
            Vf.l r6 = Vf.D.k(r6)
            r18 = r7
            r7 = 0
            r3 = r23
            r12 = r18
            int r7 = d(r0, r1, r2, r3, r4, r6, r7)     // Catch:{ all -> 0x00c7 }
            if (r7 == 0) goto L_0x013f
            if (r7 == r11) goto L_0x011e
            if (r7 == r14) goto L_0x013a
            if (r7 == r12) goto L_0x012d
            java.lang.String r13 = "unexpected"
            r2 = 5
            if (r7 != r2) goto L_0x0126
            r1.b()     // Catch:{ all -> 0x00c7 }
            java.lang.Object r1 = r8.get(r0)     // Catch:{ all -> 0x00c7 }
            Xf.m r1 = (Xf.m) r1     // Catch:{ all -> 0x00c7 }
        L_0x009c:
            long r4 = r9.getAndIncrement(r0)     // Catch:{ all -> 0x00c7 }
            long r7 = r4 & r16
            r2 = 0
            boolean r4 = r0.t(r4, r2)     // Catch:{ all -> 0x00c7 }
            int r5 = Xf.g.b     // Catch:{ all -> 0x00c7 }
            r24 = r13
            long r12 = (long) r5     // Catch:{ all -> 0x00c7 }
            r19 = r15
            long r14 = r7 / r12
            long r12 = r7 % r12
            int r12 = (int) r12     // Catch:{ all -> 0x00c7 }
            r13 = r12
            long r11 = r1.f4032c     // Catch:{ all -> 0x00c7 }
            int r11 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r11 == 0) goto L_0x00d3
            Xf.m r11 = b(r0, r14, r1)     // Catch:{ all -> 0x00c7 }
            if (r11 != 0) goto L_0x00d2
            if (r4 == 0) goto L_0x00ca
        L_0x00c2:
            c(r0, r3, r6)     // Catch:{ all -> 0x00c7 }
            goto L_0x0143
        L_0x00c7:
            r0 = move-exception
            goto L_0x0150
        L_0x00ca:
            r13 = r24
            r15 = r19
            r11 = 1
            r12 = 4
            r14 = 2
            goto L_0x009c
        L_0x00d2:
            r1 = r11
        L_0x00d3:
            r11 = r2
            r2 = r13
            r20 = r7
            r7 = r4
            r8 = r5
            r4 = r20
            int r12 = d(r0, r1, r2, r3, r4, r6, r7)     // Catch:{ all -> 0x00c7 }
            r13 = r2
            if (r12 == 0) goto L_0x0122
            r2 = 1
            if (r12 == r2) goto L_0x011e
            r14 = 2
            if (r12 == r14) goto L_0x0112
            r15 = 3
            if (r12 == r15) goto L_0x010a
            r7 = 4
            if (r12 == r7) goto L_0x00fc
            r8 = 5
            if (r12 == r8) goto L_0x00f2
            goto L_0x00f5
        L_0x00f2:
            r1.b()     // Catch:{ all -> 0x00c7 }
        L_0x00f5:
            r13 = r24
            r11 = r2
            r12 = r7
            r15 = r19
            goto L_0x009c
        L_0x00fc:
            r2 = r19
            long r7 = r2.get(r0)     // Catch:{ all -> 0x00c7 }
            int r2 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r2 >= 0) goto L_0x00c2
            r1.b()     // Catch:{ all -> 0x00c7 }
            goto L_0x00c2
        L_0x010a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00c7 }
            r1 = r24
            r0.<init>(r1)     // Catch:{ all -> 0x00c7 }
            throw r0     // Catch:{ all -> 0x00c7 }
        L_0x0112:
            if (r7 == 0) goto L_0x0118
            r1.i()     // Catch:{ all -> 0x00c7 }
            goto L_0x00c2
        L_0x0118:
            int r12 = r13 + r8
            r6.a(r1, r12)     // Catch:{ all -> 0x00c7 }
            goto L_0x0143
        L_0x011e:
            r6.resumeWith(r10)     // Catch:{ all -> 0x00c7 }
            goto L_0x0143
        L_0x0122:
            r1.b()     // Catch:{ all -> 0x00c7 }
            goto L_0x011e
        L_0x0126:
            r1 = r13
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00c7 }
            r0.<init>(r1)     // Catch:{ all -> 0x00c7 }
            throw r0     // Catch:{ all -> 0x00c7 }
        L_0x012d:
            r2 = r15
            long r7 = r2.get(r0)     // Catch:{ all -> 0x00c7 }
            int r2 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r2 >= 0) goto L_0x00c2
            r1.b()     // Catch:{ all -> 0x00c7 }
            goto L_0x00c2
        L_0x013a:
            int r2 = r2 + r13
            r6.a(r1, r2)     // Catch:{ all -> 0x00c7 }
            goto L_0x0143
        L_0x013f:
            r1.b()     // Catch:{ all -> 0x00c7 }
            goto L_0x011e
        L_0x0143:
            java.lang.Object r0 = r6.q()
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            if (r0 != r1) goto L_0x014c
            goto L_0x014d
        L_0x014c:
            r0 = r10
        L_0x014d:
            if (r0 != r1) goto L_0x0164
            return r0
        L_0x0150:
            r6.y()
            throw r0
        L_0x0154:
            r3 = r23
            if (r7 == 0) goto L_0x0164
            r1.i()
            java.lang.Object r0 = r22.y(r23, r24)
            re.a r1 = re.C1245a.COROUTINE_SUSPENDED
            if (r0 != r1) goto L_0x0164
            return r0
        L_0x0164:
            return r10
        L_0x0165:
            r1.b()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: Xf.e.i(java.lang.Object, qe.c):java.lang.Object");
    }

    public final b iterator() {
        return new b(this);
    }

    public final boolean j(Throwable th) {
        return k(th, false);
    }

    public final boolean k(Throwable th, boolean z) {
        e eVar;
        boolean z3;
        Object obj;
        B b;
        long j2;
        long j3;
        long j8;
        long j10;
        AtomicLongFieldUpdater atomicLongFieldUpdater = e;
        if (z) {
            while (true) {
                long j11 = atomicLongFieldUpdater.get(this);
                if (((int) (j11 >> 60)) != 0) {
                    break;
                }
                m mVar = g.f3902a;
                eVar = this;
                if (atomicLongFieldUpdater.compareAndSet(eVar, j11, (j11 & 1152921504606846975L) + (((long) 1) << 60))) {
                    break;
                }
                this = eVar;
            }
        }
        eVar = this;
        B b5 = g.s;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = l;
            if (!atomicReferenceFieldUpdater.compareAndSet(eVar, b5, th)) {
                if (atomicReferenceFieldUpdater.get(eVar) != b5) {
                    z3 = false;
                    break;
                }
            } else {
                z3 = true;
                break;
            }
        }
        if (z) {
            do {
                j10 = atomicLongFieldUpdater.get(eVar);
            } while (!atomicLongFieldUpdater.compareAndSet(eVar, j10, (((long) 3) << 60) + (j10 & 1152921504606846975L)));
        } else {
            do {
                j2 = atomicLongFieldUpdater.get(eVar);
                int i2 = (int) (j2 >> 60);
                if (i2 == 0) {
                    j3 = j2 & 1152921504606846975L;
                    j8 = (long) 2;
                } else if (i2 != 1) {
                    break;
                } else {
                    j3 = j2 & 1152921504606846975L;
                    j8 = (long) 3;
                }
            } while (!atomicLongFieldUpdater.compareAndSet(eVar, j2, (j8 << 60) + j3));
        }
        eVar.u();
        if (z3) {
            loop4:
            while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = m;
                obj = atomicReferenceFieldUpdater2.get(eVar);
                if (obj == null) {
                    b = g.q;
                } else {
                    b = g.r;
                }
                while (true) {
                    if (atomicReferenceFieldUpdater2.compareAndSet(eVar, obj, b)) {
                        break loop4;
                    } else if (atomicReferenceFieldUpdater2.get(eVar) != obj) {
                    }
                }
            }
            if (obj != null) {
                y.c(1, obj);
                ((b) obj).invoke(eVar.p());
                return z3;
            }
        }
        return z3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006d, code lost:
        r8 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x008d, code lost:
        r1 = (Xf.m) ((cg.b) cg.b.b.get(r1));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Xf.m l(long r13) {
        /*
            r12 = this;
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = k
            java.lang.Object r0 = r0.get(r12)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = f3900i
            java.lang.Object r1 = r1.get(r12)
            Xf.m r1 = (Xf.m) r1
            long r2 = r1.f4032c
            r4 = r0
            Xf.m r4 = (Xf.m) r4
            long r4 = r4.f4032c
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x001a
            r0 = r1
        L_0x001a:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = f3901j
            java.lang.Object r1 = r1.get(r12)
            Xf.m r1 = (Xf.m) r1
            long r2 = r1.f4032c
            r4 = r0
            Xf.m r4 = (Xf.m) r4
            long r4 = r4.f4032c
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x002e
            r0 = r1
        L_0x002e:
            cg.b r0 = (cg.b) r0
        L_0x0030:
            r0.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = cg.b.f4018a
            java.lang.Object r2 = r1.get(r0)
            Qe.B r3 = cg.a.f4016a
            r4 = 0
            if (r2 != r3) goto L_0x003f
            goto L_0x0049
        L_0x003f:
            cg.b r2 = (cg.b) r2
            if (r2 != 0) goto L_0x012f
        L_0x0043:
            boolean r2 = r1.compareAndSet(r0, r4, r3)
            if (r2 == 0) goto L_0x0127
        L_0x0049:
            Xf.m r0 = (Xf.m) r0
            boolean r1 = r12.v()
            r2 = 1
            r3 = -1
            if (r1 == 0) goto L_0x00a1
            r1 = r0
        L_0x0054:
            int r5 = Xf.g.b
            int r5 = r5 - r2
        L_0x0057:
            r6 = -1
            if (r3 >= r5) goto L_0x008d
            long r8 = r1.f4032c
            int r10 = Xf.g.b
            long r10 = (long) r10
            long r8 = r8 * r10
            long r10 = (long) r5
            long r8 = r8 + r10
            java.util.concurrent.atomic.AtomicLongFieldUpdater r10 = f
            long r10 = r10.get(r12)
            int r10 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x006f
        L_0x006d:
            r8 = r6
            goto L_0x009a
        L_0x006f:
            java.lang.Object r10 = r1.l(r5)
            if (r10 == 0) goto L_0x007f
            Qe.B r11 = Xf.g.e
            if (r10 != r11) goto L_0x007a
            goto L_0x007f
        L_0x007a:
            Qe.B r11 = Xf.g.d
            if (r10 != r11) goto L_0x008a
            goto L_0x009a
        L_0x007f:
            Qe.B r11 = Xf.g.l
            boolean r10 = r1.k(r5, r10, r11)
            if (r10 == 0) goto L_0x006f
            r1.i()
        L_0x008a:
            int r5 = r5 + -1
            goto L_0x0057
        L_0x008d:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = cg.b.b
            java.lang.Object r1 = r5.get(r1)
            cg.b r1 = (cg.b) r1
            Xf.m r1 = (Xf.m) r1
            if (r1 != 0) goto L_0x0054
            goto L_0x006d
        L_0x009a:
            int r1 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r1 == 0) goto L_0x00a1
            r12.m(r8)
        L_0x00a1:
            r1 = r0
        L_0x00a2:
            if (r1 == 0) goto L_0x0105
            int r5 = Xf.g.b
            int r5 = r5 - r2
        L_0x00a7:
            if (r3 >= r5) goto L_0x00fa
            long r6 = r1.f4032c
            int r8 = Xf.g.b
            long r8 = (long) r8
            long r6 = r6 * r8
            long r8 = (long) r5
            long r6 = r6 + r8
            int r6 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r6 < 0) goto L_0x0105
        L_0x00b5:
            java.lang.Object r6 = r1.l(r5)
            if (r6 == 0) goto L_0x00ec
            Qe.B r7 = Xf.g.e
            if (r6 != r7) goto L_0x00c0
            goto L_0x00ec
        L_0x00c0:
            boolean r7 = r6 instanceof Xf.v
            if (r7 == 0) goto L_0x00d8
            Qe.B r7 = Xf.g.l
            boolean r7 = r1.k(r5, r6, r7)
            if (r7 == 0) goto L_0x00b5
            Xf.v r6 = (Xf.v) r6
            Vf.E0 r6 = r6.f3912a
            java.lang.Object r4 = cg.a.f(r4, r6)
            r1.m(r5, r2)
            goto L_0x00f7
        L_0x00d8:
            boolean r7 = r6 instanceof Vf.E0
            if (r7 == 0) goto L_0x00f7
            Qe.B r7 = Xf.g.l
            boolean r7 = r1.k(r5, r6, r7)
            if (r7 == 0) goto L_0x00b5
            java.lang.Object r4 = cg.a.f(r4, r6)
            r1.m(r5, r2)
            goto L_0x00f7
        L_0x00ec:
            Qe.B r7 = Xf.g.l
            boolean r6 = r1.k(r5, r6, r7)
            if (r6 == 0) goto L_0x00b5
            r1.i()
        L_0x00f7:
            int r5 = r5 + -1
            goto L_0x00a7
        L_0x00fa:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = cg.b.b
            java.lang.Object r1 = r5.get(r1)
            cg.b r1 = (cg.b) r1
            Xf.m r1 = (Xf.m) r1
            goto L_0x00a2
        L_0x0105:
            if (r4 == 0) goto L_0x0126
            boolean r13 = r4 instanceof java.util.ArrayList
            if (r13 != 0) goto L_0x0111
            Vf.E0 r4 = (Vf.E0) r4
            r12.B(r4, r2)
            return r0
        L_0x0111:
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            int r13 = r4.size()
            int r13 = r13 - r2
        L_0x0118:
            if (r3 >= r13) goto L_0x0126
            java.lang.Object r14 = r4.get(r13)
            Vf.E0 r14 = (Vf.E0) r14
            r12.B(r14, r2)
            int r13 = r13 + -1
            goto L_0x0118
        L_0x0126:
            return r0
        L_0x0127:
            java.lang.Object r2 = r1.get(r0)
            if (r2 == 0) goto L_0x0043
            goto L_0x0030
        L_0x012f:
            r0 = r2
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: Xf.e.l(long):Xf.m");
    }

    public final void m(long j2) {
        m mVar = (m) f3901j.get(this);
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f;
            long j3 = atomicLongFieldUpdater.get(r9);
            if (j2 >= Math.max(((long) r9.d) + j3, g.get(r9))) {
                e eVar = r9;
                if (atomicLongFieldUpdater.compareAndSet(eVar, j3, 1 + j3)) {
                    long j8 = (long) g.b;
                    long j10 = j3 / j8;
                    int i2 = (int) (j3 % j8);
                    if (mVar.f4032c != j10) {
                        m o2 = eVar.o(j10, mVar);
                        if (o2 != null) {
                            mVar = o2;
                        }
                    }
                    long j11 = j3;
                    m mVar2 = mVar;
                    if (eVar.E(mVar2, i2, j11, (Object) null) != g.f3907o) {
                        mVar2.b();
                    } else if (j11 < eVar.r()) {
                        mVar2.b();
                    }
                    r9 = eVar;
                    mVar = mVar2;
                }
                r9 = eVar;
            } else {
                return;
            }
        }
    }

    public final void n() {
        Object b;
        if (!w()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = k;
            m mVar = (m) atomicReferenceFieldUpdater.get(this);
            loop0:
            while (true) {
                long andIncrement = g.getAndIncrement(this);
                long j2 = andIncrement / ((long) g.b);
                if (r() <= andIncrement) {
                    if (mVar.f4032c < j2 && mVar.c() != null) {
                        x(j2, mVar);
                    }
                    s(this);
                    return;
                }
                if (mVar.f4032c != j2) {
                    f fVar = f.d;
                    while (true) {
                        b = a.b(mVar, j2, fVar);
                        if (a.e(b)) {
                            break;
                        }
                        s c5 = a.c(b);
                        while (true) {
                            s sVar = (s) atomicReferenceFieldUpdater.get(this);
                            if (sVar.f4032c >= c5.f4032c) {
                                break;
                            } else if (c5.j()) {
                                while (!atomicReferenceFieldUpdater.compareAndSet(this, sVar, c5)) {
                                    if (atomicReferenceFieldUpdater.get(this) != sVar) {
                                        if (c5.f()) {
                                            c5.e();
                                        }
                                    }
                                }
                                if (sVar.f()) {
                                    sVar.e();
                                }
                            }
                        }
                    }
                    m mVar2 = null;
                    if (a.e(b)) {
                        u();
                        x(j2, mVar);
                        s(this);
                    } else {
                        m mVar3 = (m) a.c(b);
                        long j3 = mVar3.f4032c;
                        if (j3 > j2) {
                            long j8 = j3 * ((long) g.b);
                            if (g.compareAndSet(this, 1 + andIncrement, j8)) {
                                AtomicLongFieldUpdater atomicLongFieldUpdater = f3899h;
                                if ((atomicLongFieldUpdater.addAndGet(this, j8 - andIncrement) & 4611686018427387904L) != 0) {
                                    do {
                                    } while ((atomicLongFieldUpdater.get(this) & 4611686018427387904L) != 0);
                                }
                            } else {
                                s(this);
                            }
                        } else {
                            mVar2 = mVar3;
                        }
                    }
                    if (mVar2 == null) {
                        continue;
                    } else {
                        mVar = mVar2;
                    }
                }
                int i2 = (int) (andIncrement % ((long) g.b));
                Object l8 = mVar.l(i2);
                boolean z = l8 instanceof E0;
                AtomicLongFieldUpdater atomicLongFieldUpdater2 = f;
                if (!z || andIncrement < atomicLongFieldUpdater2.get(this) || !mVar.k(i2, l8, g.g)) {
                    while (true) {
                        Object l10 = mVar.l(i2);
                        if (!(l10 instanceof E0)) {
                            if (l10 != g.f3906j) {
                                if (l10 != null) {
                                    if (l10 == g.d || l10 == g.f3904h || l10 == g.f3905i || l10 == g.k || l10 == g.l) {
                                        break loop0;
                                    } else if (l10 != g.f) {
                                        throw new IllegalStateException(("Unexpected cell state: " + l10).toString());
                                    }
                                } else if (mVar.k(i2, l10, g.e)) {
                                    break loop0;
                                }
                            } else {
                                break;
                            }
                        } else if (andIncrement < atomicLongFieldUpdater2.get(this)) {
                            if (mVar.k(i2, l10, new v((E0) l10))) {
                                break loop0;
                            }
                        } else if (mVar.k(i2, l10, g.g)) {
                            if (D(l10)) {
                                mVar.o(i2, g.d);
                                break;
                            } else {
                                mVar.o(i2, g.f3906j);
                                mVar.i();
                            }
                        }
                    }
                } else if (D(l8)) {
                    mVar.o(i2, g.d);
                    break;
                } else {
                    mVar.o(i2, g.f3906j);
                    mVar.i();
                }
                s(this);
            }
            s(this);
        }
    }

    public final m o(long j2, m mVar) {
        Object b;
        e eVar;
        m mVar2 = g.f3902a;
        f fVar = f.d;
        loop0:
        while (true) {
            b = a.b(mVar, j2, fVar);
            if (a.e(b)) {
                break;
            }
            s c5 = a.c(b);
            while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3901j;
                s sVar = (s) atomicReferenceFieldUpdater.get(this);
                if (sVar.f4032c >= c5.f4032c) {
                    break loop0;
                } else if (c5.j()) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, sVar, c5)) {
                        if (atomicReferenceFieldUpdater.get(this) != sVar) {
                            if (c5.f()) {
                                c5.e();
                            }
                        }
                    }
                    if (sVar.f()) {
                        sVar.e();
                    }
                }
            }
        }
        if (a.e(b)) {
            u();
            if (mVar.f4032c * ((long) g.b) < r()) {
                mVar.b();
                return null;
            }
        } else {
            m mVar3 = (m) a.c(b);
            long j3 = mVar3.f4032c;
            if (!w() && j2 <= g.get(this) / ((long) g.b)) {
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = k;
                    s sVar2 = (s) atomicReferenceFieldUpdater2.get(this);
                    if (sVar2.f4032c >= j3 || !mVar3.j()) {
                        break;
                    }
                    while (!atomicReferenceFieldUpdater2.compareAndSet(this, sVar2, mVar3)) {
                        if (atomicReferenceFieldUpdater2.get(this) != sVar2) {
                            if (mVar3.f()) {
                                mVar3.e();
                            }
                        }
                    }
                    if (sVar2.f()) {
                        sVar2.e();
                    }
                }
            }
            if (j3 <= j2) {
                return mVar3;
            }
            long j8 = j3 * ((long) g.b);
            while (true) {
                long j10 = f.get(this);
                if (j10 >= j8) {
                    eVar = this;
                    break;
                }
                eVar = this;
                if (f.compareAndSet(eVar, j10, j8)) {
                    break;
                }
                this = eVar;
            }
            if (j3 * ((long) g.b) < eVar.r()) {
                mVar3.b();
            }
        }
        return null;
    }

    public final Throwable p() {
        return (Throwable) l.get(this);
    }

    public final Throwable q() {
        Throwable p6 = p();
        if (p6 == null) {
            return new IllegalStateException("Channel was closed");
        }
        return p6;
    }

    public final long r() {
        return e.get(this) & 1152921504606846975L;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a2, code lost:
        r0 = (Xf.m) ((cg.b) cg.b.b.get(r0));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean t(long r14, boolean r16) {
        /*
            r13 = this;
            r0 = 60
            long r0 = r14 >> r0
            int r0 = (int) r0
            r1 = 0
            if (r0 == 0) goto L_0x016f
            r2 = 1
            if (r0 == r2) goto L_0x016f
            r3 = 2
            java.util.concurrent.atomic.AtomicLongFieldUpdater r4 = f
            r5 = 1152921504606846975(0xfffffffffffffff, double:1.2882297539194265E-231)
            if (r0 == r3) goto L_0x00e0
            r3 = 3
            if (r0 != r3) goto L_0x00d0
            long r5 = r5 & r14
            Xf.m r0 = r13.l(r5)
            r3 = 0
            r5 = r3
        L_0x001f:
            int r6 = Xf.g.b
            int r6 = r6 - r2
        L_0x0022:
            r7 = -1
            if (r7 >= r6) goto L_0x00a2
            long r8 = r0.f4032c
            int r10 = Xf.g.b
            long r10 = (long) r10
            long r8 = r8 * r10
            long r10 = (long) r6
            long r8 = r8 + r10
        L_0x002d:
            java.lang.Object r10 = r0.l(r6)
            Qe.B r11 = Xf.g.f3905i
            if (r10 == r11) goto L_0x00ae
            Qe.B r11 = Xf.g.d
            if (r10 != r11) goto L_0x0050
            long r11 = r4.get(r13)
            int r11 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r11 < 0) goto L_0x00ae
            Qe.B r11 = Xf.g.l
            boolean r10 = r0.k(r6, r10, r11)
            if (r10 == 0) goto L_0x002d
            r0.n(r6, r3)
            r0.i()
            goto L_0x009f
        L_0x0050:
            Qe.B r11 = Xf.g.e
            if (r10 == r11) goto L_0x0094
            if (r10 != 0) goto L_0x0057
            goto L_0x0094
        L_0x0057:
            boolean r11 = r10 instanceof Vf.E0
            if (r11 != 0) goto L_0x006c
            boolean r11 = r10 instanceof Xf.v
            if (r11 == 0) goto L_0x0060
            goto L_0x006c
        L_0x0060:
            Qe.B r11 = Xf.g.g
            if (r10 == r11) goto L_0x00ae
            Qe.B r12 = Xf.g.f
            if (r10 != r12) goto L_0x0069
            goto L_0x00ae
        L_0x0069:
            if (r10 == r11) goto L_0x002d
            goto L_0x009f
        L_0x006c:
            long r11 = r4.get(r13)
            int r11 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r11 < 0) goto L_0x00ae
            boolean r11 = r10 instanceof Xf.v
            if (r11 == 0) goto L_0x007e
            r11 = r10
            Xf.v r11 = (Xf.v) r11
            Vf.E0 r11 = r11.f3912a
            goto L_0x0081
        L_0x007e:
            r11 = r10
            Vf.E0 r11 = (Vf.E0) r11
        L_0x0081:
            Qe.B r12 = Xf.g.l
            boolean r10 = r0.k(r6, r10, r12)
            if (r10 == 0) goto L_0x002d
            java.lang.Object r5 = cg.a.f(r5, r11)
            r0.n(r6, r3)
            r0.i()
            goto L_0x009f
        L_0x0094:
            Qe.B r11 = Xf.g.l
            boolean r10 = r0.k(r6, r10, r11)
            if (r10 == 0) goto L_0x002d
            r0.i()
        L_0x009f:
            int r6 = r6 + -1
            goto L_0x0022
        L_0x00a2:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r6 = cg.b.b
            java.lang.Object r0 = r6.get(r0)
            cg.b r0 = (cg.b) r0
            Xf.m r0 = (Xf.m) r0
            if (r0 != 0) goto L_0x001f
        L_0x00ae:
            if (r5 == 0) goto L_0x016e
            boolean r0 = r5 instanceof java.util.ArrayList
            if (r0 != 0) goto L_0x00bb
            Vf.E0 r5 = (Vf.E0) r5
            r13.B(r5, r1)
            goto L_0x016e
        L_0x00bb:
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            int r0 = r5.size()
            int r0 = r0 - r2
        L_0x00c2:
            if (r7 >= r0) goto L_0x016e
            java.lang.Object r3 = r5.get(r0)
            Vf.E0 r3 = (Vf.E0) r3
            r13.B(r3, r1)
            int r0 = r0 + -1
            goto L_0x00c2
        L_0x00d0:
            java.lang.String r13 = "unexpected close status: "
            java.lang.String r13 = c0.C0086a.i(r0, r13)
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r13 = r13.toString()
            r0.<init>(r13)
            throw r0
        L_0x00e0:
            long r5 = r5 & r14
            r13.l(r5)
            if (r16 == 0) goto L_0x016e
        L_0x00e6:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = f3901j
            java.lang.Object r3 = r0.get(r13)
            Xf.m r3 = (Xf.m) r3
            long r7 = r4.get(r13)
            long r5 = r13.r()
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 > 0) goto L_0x00fc
            goto L_0x016e
        L_0x00fc:
            int r5 = Xf.g.b
            long r5 = (long) r5
            long r9 = r7 / r5
            long r11 = r3.f4032c
            int r11 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x011a
            Xf.m r3 = r13.o(r9, r3)
            if (r3 != 0) goto L_0x011a
            java.lang.Object r0 = r0.get(r13)
            Xf.m r0 = (Xf.m) r0
            long r5 = r0.f4032c
            int r0 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r0 >= 0) goto L_0x00e6
            goto L_0x016e
        L_0x011a:
            r3.b()
            long r5 = r7 % r5
            int r0 = (int) r5
        L_0x0120:
            java.lang.Object r5 = r3.l(r0)
            if (r5 == 0) goto L_0x0157
            Qe.B r6 = Xf.g.e
            if (r5 != r6) goto L_0x012b
            goto L_0x0157
        L_0x012b:
            Qe.B r0 = Xf.g.d
            if (r5 != r0) goto L_0x0130
            goto L_0x016f
        L_0x0130:
            Qe.B r0 = Xf.g.f3906j
            if (r5 != r0) goto L_0x0135
            goto L_0x0162
        L_0x0135:
            Qe.B r0 = Xf.g.l
            if (r5 != r0) goto L_0x013a
            goto L_0x0162
        L_0x013a:
            Qe.B r0 = Xf.g.f3905i
            if (r5 != r0) goto L_0x013f
            goto L_0x0162
        L_0x013f:
            Qe.B r0 = Xf.g.f3904h
            if (r5 != r0) goto L_0x0144
            goto L_0x0162
        L_0x0144:
            Qe.B r0 = Xf.g.g
            if (r5 != r0) goto L_0x0149
            goto L_0x016f
        L_0x0149:
            Qe.B r0 = Xf.g.f
            if (r5 != r0) goto L_0x014e
            goto L_0x0162
        L_0x014e:
            long r5 = r4.get(r13)
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 != 0) goto L_0x0162
            goto L_0x016f
        L_0x0157:
            Qe.B r6 = Xf.g.f3904h
            boolean r5 = r3.k(r0, r5, r6)
            if (r5 == 0) goto L_0x0120
            r13.n()
        L_0x0162:
            r5 = 1
            long r9 = r7 + r5
            java.util.concurrent.atomic.AtomicLongFieldUpdater r5 = f
            r6 = r13
            r5.compareAndSet(r6, r7, r9)
            goto L_0x00e6
        L_0x016e:
            return r2
        L_0x016f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: Xf.e.t(long, boolean):boolean");
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [cg.b] */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0196, code lost:
        r3 = r3.c();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x019d, code lost:
        if (r3 != null) goto L_0x01bd;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String toString() {
        /*
            r16 = this;
            r0 = r16
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = e
            long r2 = r2.get(r0)
            r4 = 60
            long r2 = r2 >> r4
            int r2 = (int) r2
            r3 = 3
            r4 = 2
            if (r2 == r4) goto L_0x001e
            if (r2 == r3) goto L_0x0018
            goto L_0x0023
        L_0x0018:
            java.lang.String r2 = "cancelled,"
            r1.append(r2)
            goto L_0x0023
        L_0x001e:
            java.lang.String r2 = "closed,"
            r1.append(r2)
        L_0x0023:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "capacity="
            r2.<init>(r5)
            int r5 = r0.d
            r2.append(r5)
            r5 = 44
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r1.append(r2)
            java.lang.String r2 = "data=["
            r1.append(r2)
            Xf.m[] r2 = new Xf.m[r3]
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = f3901j
            java.lang.Object r3 = r3.get(r0)
            r6 = 0
            r2[r6] = r3
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = f3900i
            java.lang.Object r3 = r3.get(r0)
            r7 = 1
            r2[r7] = r3
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = k
            java.lang.Object r3 = r3.get(r0)
            r2[r4] = r3
            java.util.List r2 = ne.C1195m.q0(r2)
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r2.iterator()
        L_0x006b:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0080
            java.lang.Object r4 = r2.next()
            r8 = r4
            Xf.m r8 = (Xf.m) r8
            Xf.m r9 = Xf.g.f3902a
            if (r8 == r9) goto L_0x006b
            r3.add(r4)
            goto L_0x006b
        L_0x0080:
            java.util.Iterator r2 = r3.iterator()
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x01c0
            java.lang.Object r3 = r2.next()
            boolean r4 = r2.hasNext()
            if (r4 != 0) goto L_0x0095
            goto L_0x00af
        L_0x0095:
            r4 = r3
            Xf.m r4 = (Xf.m) r4
            long r8 = r4.f4032c
        L_0x009a:
            java.lang.Object r4 = r2.next()
            r10 = r4
            Xf.m r10 = (Xf.m) r10
            long r10 = r10.f4032c
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 <= 0) goto L_0x00a9
            r3 = r4
            r8 = r10
        L_0x00a9:
            boolean r4 = r2.hasNext()
            if (r4 != 0) goto L_0x009a
        L_0x00af:
            Xf.m r3 = (Xf.m) r3
            java.util.concurrent.atomic.AtomicLongFieldUpdater r2 = f
            long r10 = r2.get(r0)
            long r12 = r0.r()
        L_0x00bb:
            int r0 = Xf.g.b
            r2 = r6
        L_0x00be:
            if (r2 >= r0) goto L_0x0196
            long r8 = r3.f4032c
            int r4 = Xf.g.b
            long r14 = (long) r4
            long r8 = r8 * r14
            long r14 = (long) r2
            long r8 = r8 + r14
            int r4 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r4 < 0) goto L_0x00d0
            int r14 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r14 >= 0) goto L_0x019f
        L_0x00d0:
            java.lang.Object r14 = r3.l(r2)
            java.util.concurrent.atomic.AtomicReferenceArray r15 = r3.f
            int r6 = r2 * 2
            java.lang.Object r6 = r15.get(r6)
            boolean r15 = r14 instanceof Vf.C0873j
            if (r15 == 0) goto L_0x00f6
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 >= 0) goto L_0x00ea
            if (r4 < 0) goto L_0x00ea
            java.lang.String r4 = "receive"
            goto L_0x0160
        L_0x00ea:
            if (r4 >= 0) goto L_0x00f2
            if (r8 < 0) goto L_0x00f2
            java.lang.String r4 = "send"
            goto L_0x0160
        L_0x00f2:
            java.lang.String r4 = "cont"
            goto L_0x0160
        L_0x00f6:
            boolean r4 = r14 instanceof Xf.s
            if (r4 == 0) goto L_0x00fd
            java.lang.String r4 = "receiveCatching"
            goto L_0x0160
        L_0x00fd:
            boolean r4 = r14 instanceof Xf.v
            if (r4 == 0) goto L_0x0115
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r8 = "EB("
            r4.<init>(r8)
            r4.append(r14)
            r8 = 41
            r4.append(r8)
            java.lang.String r4 = r4.toString()
            goto L_0x0160
        L_0x0115:
            Qe.B r4 = Xf.g.f
            boolean r4 = kotlin.jvm.internal.j.a(r14, r4)
            if (r4 != 0) goto L_0x015e
            Qe.B r4 = Xf.g.g
            boolean r4 = kotlin.jvm.internal.j.a(r14, r4)
            if (r4 == 0) goto L_0x0126
            goto L_0x015e
        L_0x0126:
            if (r14 == 0) goto L_0x0191
            Qe.B r4 = Xf.g.e
            boolean r4 = r14.equals(r4)
            if (r4 != 0) goto L_0x0191
            Qe.B r4 = Xf.g.f3905i
            boolean r4 = r14.equals(r4)
            if (r4 != 0) goto L_0x0191
            Qe.B r4 = Xf.g.f3904h
            boolean r4 = r14.equals(r4)
            if (r4 != 0) goto L_0x0191
            Qe.B r4 = Xf.g.k
            boolean r4 = r14.equals(r4)
            if (r4 != 0) goto L_0x0191
            Qe.B r4 = Xf.g.f3906j
            boolean r4 = r14.equals(r4)
            if (r4 != 0) goto L_0x0191
            Qe.B r4 = Xf.g.l
            boolean r4 = r14.equals(r4)
            if (r4 == 0) goto L_0x0159
            goto L_0x0191
        L_0x0159:
            java.lang.String r4 = r14.toString()
            goto L_0x0160
        L_0x015e:
            java.lang.String r4 = "resuming_sender"
        L_0x0160:
            if (r6 == 0) goto L_0x017f
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "("
            r8.<init>(r9)
            r8.append(r4)
            r8.append(r5)
            r8.append(r6)
            java.lang.String r4 = "),"
            r8.append(r4)
            java.lang.String r4 = r8.toString()
            r1.append(r4)
            goto L_0x0191
        L_0x017f:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r4)
            r6.append(r5)
            java.lang.String r4 = r6.toString()
            r1.append(r4)
        L_0x0191:
            int r2 = r2 + 1
            r6 = 0
            goto L_0x00be
        L_0x0196:
            cg.b r0 = r3.c()
            r3 = r0
            Xf.m r3 = (Xf.m) r3
            if (r3 != 0) goto L_0x01bd
        L_0x019f:
            char r0 = Tf.n.D0(r1)
            if (r0 != r5) goto L_0x01b3
            int r0 = r1.length()
            int r0 = r0 - r7
            java.lang.StringBuilder r0 = r1.deleteCharAt(r0)
            java.lang.String r2 = "deleteCharAt(...)"
            kotlin.jvm.internal.j.d(r0, r2)
        L_0x01b3:
            java.lang.String r0 = "]"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            return r0
        L_0x01bd:
            r6 = 0
            goto L_0x00bb
        L_0x01c0:
            java.util.NoSuchElementException r0 = new java.util.NoSuchElementException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Xf.e.toString():java.lang.String");
    }

    public final boolean u() {
        return t(e.get(this), false);
    }

    public boolean v() {
        return false;
    }

    public final boolean w() {
        long j2 = g.get(this);
        if (j2 == 0 || j2 == Long.MAX_VALUE) {
            return true;
        }
        return false;
    }

    public final void x(long j2, m mVar) {
        m mVar2;
        m mVar3;
        while (mVar.f4032c < j2 && (mVar3 = (m) mVar.c()) != null) {
            mVar = mVar3;
        }
        while (true) {
            if (!mVar.d() || (mVar2 = (m) mVar.c()) == null) {
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = k;
                    s sVar = (s) atomicReferenceFieldUpdater.get(this);
                    if (sVar.f4032c < mVar.f4032c) {
                        if (!mVar.j()) {
                            continue;
                            break;
                        }
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, sVar, mVar)) {
                            if (atomicReferenceFieldUpdater.get(this) != sVar) {
                                if (mVar.f()) {
                                    mVar.e();
                                }
                            }
                        }
                        if (sVar.f()) {
                            sVar.e();
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            mVar = mVar2;
        }
    }

    public final Object y(Object obj, C1227c cVar) {
        C0875l lVar = new C0875l(1, L1.d.m(cVar));
        lVar.r();
        lVar.resumeWith(L2.a.l(q()));
        Object q = lVar.q();
        if (q == C1245a.COROUTINE_SUSPENDED) {
            return q;
        }
        return x.f4917a;
    }
}
