package Vf;

import Qe.B;
import cg.m;
import cg.v;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class W extends X implements I {

    /* renamed from: h  reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f3847h;

    /* renamed from: i  reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f3848i;

    /* renamed from: j  reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f3849j;
    private volatile /* synthetic */ Object _delayed$volatile;
    private volatile /* synthetic */ int _isCompleted$volatile = 0;
    private volatile /* synthetic */ Object _queue$volatile;

    static {
        Class<W> cls = W.class;
        Class<Object> cls2 = Object.class;
        f3847h = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_queue$volatile");
        f3848i = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_delayed$volatile");
        f3849j = AtomicIntegerFieldUpdater.newUpdater(cls, "_isCompleted$volatile");
    }

    public O f(long j2, A0 a0, C1232h hVar) {
        return F.f3841a.f(j2, a0, hVar);
    }

    public final void h(long j2, C0875l lVar) {
        long j3 = 0;
        if (j2 > 0) {
            if (j2 >= 9223372036854L) {
                j3 = Long.MAX_VALUE;
            } else {
                j3 = 1000000 * j2;
            }
        }
        if (j3 < 4611686018427387903L) {
            long nanoTime = System.nanoTime();
            S s = new S(this, j3 + nanoTime, lVar);
            x(nanoTime, s);
            lVar.u(new C0871h(2, s));
        }
    }

    public final void i(C1232h hVar, Runnable runnable) {
        t(runnable);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        if (r0 == false) goto L_0x0054;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isEmpty() {
        /*
            r7 = this;
            ne.i r0 = r7.f
            r1 = 1
            if (r0 == 0) goto L_0x000a
            boolean r0 = r0.isEmpty()
            goto L_0x000b
        L_0x000a:
            r0 = r1
        L_0x000b:
            r2 = 0
            if (r0 != 0) goto L_0x000f
            goto L_0x0054
        L_0x000f:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = f3848i
            java.lang.Object r0 = r0.get(r7)
            Vf.V r0 = (Vf.V) r0
            if (r0 == 0) goto L_0x0027
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r3 = cg.v.b
            int r0 = r3.get(r0)
            if (r0 != 0) goto L_0x0023
            r0 = r1
            goto L_0x0024
        L_0x0023:
            r0 = r2
        L_0x0024:
            if (r0 != 0) goto L_0x0027
            goto L_0x0054
        L_0x0027:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = f3847h
            java.lang.Object r7 = r0.get(r7)
            if (r7 != 0) goto L_0x0030
            goto L_0x0053
        L_0x0030:
            boolean r0 = r7 instanceof cg.m
            if (r0 == 0) goto L_0x004f
            cg.m r7 = (cg.m) r7
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = cg.m.f
            long r3 = r0.get(r7)
            r5 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r5 = r5 & r3
            int r7 = (int) r5
            r5 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r3 = r3 & r5
            r0 = 30
            long r3 = r3 >> r0
            int r0 = (int) r3
            if (r7 != r0) goto L_0x004e
            return r1
        L_0x004e:
            return r2
        L_0x004f:
            Qe.B r0 = Vf.D.f3837c
            if (r7 != r0) goto L_0x0054
        L_0x0053:
            return r1
        L_0x0054:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.W.isEmpty():boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0030 A[LOOP:1: B:12:0x0030->B:15:0x003b, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long q() {
        /*
            r10 = this;
            Qe.B r0 = Vf.D.f3837c
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = f3847h
            boolean r2 = r10.r()
            r3 = 0
            if (r2 == 0) goto L_0x000e
            goto L_0x00b4
        L_0x000e:
            r10.v()
        L_0x0011:
            java.lang.Object r2 = r1.get(r10)
            r5 = 0
            if (r2 != 0) goto L_0x001a
        L_0x0018:
            r7 = r5
            goto L_0x004a
        L_0x001a:
            boolean r6 = r2 instanceof cg.m
            if (r6 == 0) goto L_0x003e
            r6 = r2
            cg.m r6 = (cg.m) r6
            java.lang.Object r7 = r6.d()
            Qe.B r8 = cg.m.g
            if (r7 == r8) goto L_0x002c
            java.lang.Runnable r7 = (java.lang.Runnable) r7
            goto L_0x004a
        L_0x002c:
            cg.m r6 = r6.c()
        L_0x0030:
            boolean r5 = r1.compareAndSet(r10, r2, r6)
            if (r5 == 0) goto L_0x0037
            goto L_0x0011
        L_0x0037:
            java.lang.Object r5 = r1.get(r10)
            if (r5 == r2) goto L_0x0030
            goto L_0x0011
        L_0x003e:
            if (r2 != r0) goto L_0x0041
            goto L_0x0018
        L_0x0041:
            boolean r6 = r1.compareAndSet(r10, r2, r5)
            if (r6 == 0) goto L_0x00b9
            r7 = r2
            java.lang.Runnable r7 = (java.lang.Runnable) r7
        L_0x004a:
            if (r7 == 0) goto L_0x0050
            r7.run()
            return r3
        L_0x0050:
            ne.i r2 = r10.f
            r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            if (r2 != 0) goto L_0x005b
        L_0x0059:
            r8 = r6
            goto L_0x0063
        L_0x005b:
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0062
            goto L_0x0059
        L_0x0062:
            r8 = r3
        L_0x0063:
            int r2 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r2 != 0) goto L_0x0068
            goto L_0x00b4
        L_0x0068:
            java.lang.Object r1 = r1.get(r10)
            if (r1 == 0) goto L_0x0090
            boolean r2 = r1 instanceof cg.m
            if (r2 == 0) goto L_0x008d
            cg.m r1 = (cg.m) r1
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = cg.m.f
            long r0 = r0.get(r1)
            r8 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r8 = r8 & r0
            int r2 = (int) r8
            r8 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r0 = r0 & r8
            r8 = 30
            long r0 = r0 >> r8
            int r0 = (int) r0
            if (r2 != r0) goto L_0x008c
            goto L_0x0090
        L_0x008c:
            return r3
        L_0x008d:
            if (r1 != r0) goto L_0x00b4
            goto L_0x00b8
        L_0x0090:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = f3848i
            java.lang.Object r10 = r0.get(r10)
            Vf.V r10 = (Vf.V) r10
            if (r10 == 0) goto L_0x00b8
            monitor-enter(r10)
            Vf.U[] r0 = r10.f4035a     // Catch:{ all -> 0x00a3 }
            if (r0 == 0) goto L_0x00a5
            r1 = 0
            r5 = r0[r1]     // Catch:{ all -> 0x00a3 }
            goto L_0x00a5
        L_0x00a3:
            r0 = move-exception
            goto L_0x00b6
        L_0x00a5:
            monitor-exit(r10)
            if (r5 != 0) goto L_0x00a9
            goto L_0x00b8
        L_0x00a9:
            long r0 = r5.d
            long r5 = java.lang.System.nanoTime()
            long r0 = r0 - r5
            int r10 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r10 >= 0) goto L_0x00b5
        L_0x00b4:
            return r3
        L_0x00b5:
            return r0
        L_0x00b6:
            monitor-exit(r10)
            throw r0
        L_0x00b8:
            return r6
        L_0x00b9:
            java.lang.Object r6 = r1.get(r10)
            if (r6 == r2) goto L_0x0041
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.W.q():long");
    }

    public void shutdown() {
        U u;
        y0.f3876a.set((Object) null);
        f3849j.set(this, 1);
        B b = D.f3837c;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3847h;
        loop0:
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj != null) {
                if (!(obj instanceof m)) {
                    if (obj != b) {
                        m mVar = new m(8, true);
                        mVar.a((Runnable) obj);
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, mVar)) {
                            if (atomicReferenceFieldUpdater.get(this) != obj) {
                            }
                        }
                        break loop0;
                    }
                    break;
                }
                ((m) obj).b();
                break;
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(this, (Object) null, b)) {
                if (atomicReferenceFieldUpdater.get(this) != null) {
                }
            }
            break loop0;
        }
        do {
        } while (q() <= 0);
        long nanoTime = System.nanoTime();
        while (true) {
            V v = (V) f3848i.get(this);
            if (v != null) {
                synchronized (v) {
                    if (v.b.get(v) > 0) {
                        u = v.b(0);
                    } else {
                        u = null;
                    }
                }
                if (u != null) {
                    s(nanoTime, u);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public void t(Runnable runnable) {
        v();
        if (w(runnable)) {
            Thread n = n();
            if (Thread.currentThread() != n) {
                LockSupport.unpark(n);
                return;
            }
            return;
        }
        E.k.t(runnable);
    }

    public final void v() {
        U u;
        U u3;
        boolean z;
        V v = (V) f3848i.get(this);
        if (v != null && v.b.get(v) != 0) {
            long nanoTime = System.nanoTime();
            do {
                synchronized (v) {
                    try {
                        U[] uArr = v.f4035a;
                        u = null;
                        if (uArr != null) {
                            u3 = uArr[0];
                        } else {
                            u3 = null;
                        }
                        if (u3 == null) {
                            continue;
                        } else {
                            if (nanoTime - u3.d >= 0) {
                                z = w(u3);
                            } else {
                                z = false;
                            }
                            if (z) {
                                u = v.b(0);
                            }
                            continue;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } while (u != null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0062, code lost:
        return true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039 A[LOOP:2: B:17:0x0039->B:20:0x0044, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean w(java.lang.Runnable r7) {
        /*
            r6 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = f3847h
            java.lang.Object r1 = r0.get(r6)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = f3849j
            int r2 = r2.get(r6)
            r3 = 0
            if (r2 == 0) goto L_0x0010
            return r3
        L_0x0010:
            r2 = 1
            if (r1 != 0) goto L_0x0022
        L_0x0013:
            r1 = 0
            boolean r1 = r0.compareAndSet(r6, r1, r7)
            if (r1 == 0) goto L_0x001b
            goto L_0x0062
        L_0x001b:
            java.lang.Object r1 = r0.get(r6)
            if (r1 == 0) goto L_0x0013
            goto L_0x0000
        L_0x0022:
            boolean r4 = r1 instanceof cg.m
            if (r4 == 0) goto L_0x0047
            r4 = r1
            cg.m r4 = (cg.m) r4
            int r5 = r4.a(r7)
            if (r5 == 0) goto L_0x0062
            if (r5 == r2) goto L_0x0035
            r0 = 2
            if (r5 == r0) goto L_0x004b
            goto L_0x0000
        L_0x0035:
            cg.m r3 = r4.c()
        L_0x0039:
            boolean r2 = r0.compareAndSet(r6, r1, r3)
            if (r2 == 0) goto L_0x0040
            goto L_0x0000
        L_0x0040:
            java.lang.Object r2 = r0.get(r6)
            if (r2 == r1) goto L_0x0039
            goto L_0x0000
        L_0x0047:
            Qe.B r4 = Vf.D.f3837c
            if (r1 != r4) goto L_0x004c
        L_0x004b:
            return r3
        L_0x004c:
            cg.m r3 = new cg.m
            r4 = 8
            r3.<init>(r4, r2)
            r4 = r1
            java.lang.Runnable r4 = (java.lang.Runnable) r4
            r3.a(r4)
            r3.a(r7)
        L_0x005c:
            boolean r4 = r0.compareAndSet(r6, r1, r3)
            if (r4 == 0) goto L_0x0063
        L_0x0062:
            return r2
        L_0x0063:
            java.lang.Object r4 = r0.get(r6)
            if (r4 == r1) goto L_0x005c
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.W.w(java.lang.Runnable):boolean");
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [Vf.V, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x001d A[LOOP:0: B:6:0x001d->B:9:0x0028, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void x(long r6, Vf.U r8) {
        /*
            r5 = this;
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = f3848i
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = f3849j
            int r1 = r1.get(r5)
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x000e
            r1 = r3
            goto L_0x0037
        L_0x000e:
            java.lang.Object r1 = r0.get(r5)
            Vf.V r1 = (Vf.V) r1
            if (r1 != 0) goto L_0x0033
            Vf.V r4 = new Vf.V
            r4.<init>()
            r4.f3846c = r6
        L_0x001d:
            boolean r1 = r0.compareAndSet(r5, r2, r4)
            if (r1 == 0) goto L_0x0024
            goto L_0x002a
        L_0x0024:
            java.lang.Object r1 = r0.get(r5)
            if (r1 == 0) goto L_0x001d
        L_0x002a:
            java.lang.Object r1 = r0.get(r5)
            kotlin.jvm.internal.j.b(r1)
            Vf.V r1 = (Vf.V) r1
        L_0x0033:
            int r1 = r8.c(r6, r1, r5)
        L_0x0037:
            if (r1 == 0) goto L_0x004b
            if (r1 == r3) goto L_0x0047
            r5 = 2
            if (r1 != r5) goto L_0x003f
            goto L_0x0071
        L_0x003f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "unexpected result"
            r5.<init>(r6)
            throw r5
        L_0x0047:
            r5.s(r6, r8)
            return
        L_0x004b:
            java.lang.Object r6 = r0.get(r5)
            Vf.V r6 = (Vf.V) r6
            if (r6 == 0) goto L_0x0062
            monitor-enter(r6)
            Vf.U[] r7 = r6.f4035a     // Catch:{ all -> 0x005c }
            if (r7 == 0) goto L_0x005e
            r0 = 0
            r2 = r7[r0]     // Catch:{ all -> 0x005c }
            goto L_0x005e
        L_0x005c:
            r5 = move-exception
            goto L_0x0060
        L_0x005e:
            monitor-exit(r6)
            goto L_0x0062
        L_0x0060:
            monitor-exit(r6)
            throw r5
        L_0x0062:
            if (r2 != r8) goto L_0x0071
            java.lang.Thread r5 = r5.n()
            java.lang.Thread r6 = java.lang.Thread.currentThread()
            if (r6 == r5) goto L_0x0071
            java.util.concurrent.locks.LockSupport.unpark(r5)
        L_0x0071:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.W.x(long, Vf.U):void");
    }
}
