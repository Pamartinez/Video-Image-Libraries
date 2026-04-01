package cg;

import Qe.B;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m {
    public static final /* synthetic */ AtomicReferenceFieldUpdater e;
    public static final /* synthetic */ AtomicLongFieldUpdater f;
    public static final B g = new B("REMOVE_FROZEN", 2);
    private volatile /* synthetic */ Object _next$volatile;
    private volatile /* synthetic */ long _state$volatile;

    /* renamed from: a  reason: collision with root package name */
    public final int f4028a;
    public final boolean b;

    /* renamed from: c  reason: collision with root package name */
    public final int f4029c;
    public final /* synthetic */ AtomicReferenceArray d;

    static {
        Class<m> cls = m.class;
        e = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "_next$volatile");
        f = AtomicLongFieldUpdater.newUpdater(cls, "_state$volatile");
    }

    public m(int i2, boolean z) {
        this.f4028a = i2;
        this.b = z;
        int i7 = i2 - 1;
        this.f4029c = i7;
        this.d = new AtomicReferenceArray(i2);
        if (i7 > 1073741823) {
            throw new IllegalStateException("Check failed.");
        } else if ((i2 & i7) != 0) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final int a(Object obj) {
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f;
            long j2 = atomicLongFieldUpdater.get(this);
            if ((3458764513820540928L & j2) == 0) {
                int i2 = (int) (1073741823 & j2);
                int i7 = (int) ((1152921503533105152L & j2) >> 30);
                int i8 = this.f4029c;
                if (((i7 + 2) & i8) == (i2 & i8)) {
                    return 1;
                }
                boolean z = this.b;
                AtomicReferenceArray atomicReferenceArray = this.d;
                if (z || atomicReferenceArray.get(i7 & i8) == null) {
                    m mVar = this;
                    if (f.compareAndSet(mVar, j2, (-1152921503533105153L & j2) | (((long) ((i7 + 1) & 1073741823)) << 30))) {
                        atomicReferenceArray.set(i7 & i8, obj);
                        m mVar2 = mVar;
                        while ((atomicLongFieldUpdater.get(mVar2) & 1152921504606846976L) != 0) {
                            mVar2 = mVar2.c();
                            AtomicReferenceArray atomicReferenceArray2 = mVar2.d;
                            int i10 = mVar2.f4029c & i7;
                            Object obj2 = atomicReferenceArray2.get(i10);
                            if (!(obj2 instanceof l) || ((l) obj2).f4027a != i7) {
                                mVar2 = null;
                                continue;
                            } else {
                                atomicReferenceArray2.set(i10, obj);
                                continue;
                            }
                            if (mVar2 == null) {
                                return 0;
                            }
                        }
                        return 0;
                    }
                    this = mVar;
                } else {
                    int i11 = this.f4028a;
                    if (i11 < 1024 || ((i7 - i2) & 1073741823) > (i11 >> 1)) {
                        return 1;
                    }
                }
            } else if ((2305843009213693952L & j2) != 0) {
                return 2;
            } else {
                return 1;
            }
        }
    }

    public final boolean b() {
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f;
            long j2 = atomicLongFieldUpdater.get(this);
            if ((j2 & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j2) != 0) {
                return false;
            }
            m mVar = this;
            if (atomicLongFieldUpdater.compareAndSet(mVar, j2, 2305843009213693952L | j2)) {
                return true;
            }
            this = mVar;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0068 A[LOOP:3: B:17:0x0068->B:20:0x0074, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final cg.m c() {
        /*
            r10 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = f
            long r2 = r0.get(r10)
            r4 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r6 = r2 & r4
            r8 = 0
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 == 0) goto L_0x0012
            r1 = r10
            goto L_0x001b
        L_0x0012:
            long r4 = r4 | r2
            r1 = r10
            boolean r10 = r0.compareAndSet(r1, r2, r4)
            if (r10 == 0) goto L_0x0077
            r2 = r4
        L_0x001b:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r10 = e
            java.lang.Object r4 = r10.get(r1)
            cg.m r4 = (cg.m) r4
            if (r4 == 0) goto L_0x0026
            return r4
        L_0x0026:
            cg.m r4 = new cg.m
            int r5 = r1.f4028a
            int r5 = r5 * 2
            boolean r6 = r1.b
            r4.<init>(r5, r6)
            r5 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r5 = r5 & r2
            int r5 = (int) r5
            r6 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r6 = r6 & r2
            r8 = 30
            long r6 = r6 >> r8
            int r6 = (int) r6
        L_0x0040:
            int r7 = r1.f4029c
            r8 = r5 & r7
            r7 = r7 & r6
            if (r8 == r7) goto L_0x005f
            java.util.concurrent.atomic.AtomicReferenceArray r7 = r1.d
            java.lang.Object r7 = r7.get(r8)
            if (r7 != 0) goto L_0x0054
            cg.l r7 = new cg.l
            r7.<init>(r5)
        L_0x0054:
            int r8 = r4.f4029c
            r8 = r8 & r5
            java.util.concurrent.atomic.AtomicReferenceArray r9 = r4.d
            r9.set(r8, r7)
            int r5 = r5 + 1
            goto L_0x0040
        L_0x005f:
            r5 = -1152921504606846977(0xefffffffffffffff, double:-3.1050361846014175E231)
            long r5 = r5 & r2
            r0.set(r4, r5)
        L_0x0068:
            r5 = 0
            boolean r5 = r10.compareAndSet(r1, r5, r4)
            if (r5 == 0) goto L_0x0070
            goto L_0x001b
        L_0x0070:
            java.lang.Object r5 = r10.get(r1)
            if (r5 == 0) goto L_0x0068
            goto L_0x001b
        L_0x0077:
            r10 = r1
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: cg.m.c():cg.m");
    }

    public final Object d() {
        m mVar = this;
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = f;
            long j2 = atomicLongFieldUpdater.get(mVar);
            if ((j2 & 1152921504606846976L) != 0) {
                return g;
            }
            int i2 = (int) (j2 & 1073741823);
            int i7 = mVar.f4029c;
            int i8 = i2 & i7;
            if ((((int) ((1152921503533105152L & j2) >> 30)) & i7) == i8) {
                break;
            }
            AtomicReferenceArray atomicReferenceArray = mVar.d;
            Object obj = atomicReferenceArray.get(i8);
            boolean z = mVar.b;
            if (obj == null) {
                if (z) {
                    break;
                }
            } else if (obj instanceof l) {
                break;
            } else {
                long j3 = (long) ((i2 + 1) & 1073741823);
                Object obj2 = obj;
                boolean z3 = z;
                if (f.compareAndSet(mVar, j2, (j2 & -1073741824) | j3)) {
                    atomicReferenceArray.set(i8, (Object) null);
                    return obj2;
                }
                mVar = this;
                if (z3) {
                    while (true) {
                        long j8 = atomicLongFieldUpdater.get(mVar);
                        int i10 = (int) (j8 & 1073741823);
                        if ((j8 & 1152921504606846976L) != 0) {
                            mVar = mVar.c();
                        } else {
                            m mVar2 = mVar;
                            m mVar3 = mVar2;
                            if (f.compareAndSet(mVar2, j8, (j8 & -1073741824) | j3)) {
                                mVar3.d.set(i10 & mVar3.f4029c, (Object) null);
                                mVar = null;
                            } else {
                                mVar = mVar3;
                            }
                        }
                        if (mVar == null) {
                            return obj2;
                        }
                    }
                }
            }
        }
        return null;
    }
}
