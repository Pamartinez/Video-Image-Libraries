package cg;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class k {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f4026a = AtomicReferenceFieldUpdater.newUpdater(k.class, Object.class, "_cur$volatile");
    private volatile /* synthetic */ Object _cur$volatile = new m(8, false);

    /* JADX WARNING: Removed duplicated region for block: B:7:0x001b A[LOOP:1: B:7:0x001b->B:10:0x0026, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(java.lang.Runnable r5) {
        /*
            r4 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = f4026a
            java.lang.Object r1 = r0.get(r4)
            cg.m r1 = (cg.m) r1
            int r2 = r1.a(r5)
            r3 = 1
            if (r2 == 0) goto L_0x0029
            if (r2 == r3) goto L_0x0017
            r0 = 2
            if (r2 == r0) goto L_0x0015
            goto L_0x0000
        L_0x0015:
            r4 = 0
            return r4
        L_0x0017:
            cg.m r2 = r1.c()
        L_0x001b:
            boolean r3 = r0.compareAndSet(r4, r1, r2)
            if (r3 == 0) goto L_0x0022
            goto L_0x0000
        L_0x0022:
            java.lang.Object r3 = r0.get(r4)
            if (r3 == r1) goto L_0x001b
            goto L_0x0000
        L_0x0029:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cg.k.a(java.lang.Runnable):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0013 A[LOOP:1: B:4:0x0013->B:7:0x001e, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
            r4 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = f4026a
            java.lang.Object r1 = r0.get(r4)
            cg.m r1 = (cg.m) r1
            boolean r2 = r1.b()
            if (r2 == 0) goto L_0x000f
            return
        L_0x000f:
            cg.m r2 = r1.c()
        L_0x0013:
            boolean r3 = r0.compareAndSet(r4, r1, r2)
            if (r3 == 0) goto L_0x001a
            goto L_0x0000
        L_0x001a:
            java.lang.Object r3 = r0.get(r4)
            if (r3 == r1) goto L_0x0013
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: cg.k.b():void");
    }

    public final int c() {
        m mVar = (m) f4026a.get(this);
        mVar.getClass();
        long j2 = m.f.get(mVar);
        return 1073741823 & (((int) ((j2 & 1152921503533105152L) >> 30)) - ((int) (1073741823 & j2)));
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0015 A[LOOP:1: B:4:0x0015->B:7:0x0020, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object d() {
        /*
            r4 = this;
        L_0x0000:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = f4026a
            java.lang.Object r1 = r0.get(r4)
            cg.m r1 = (cg.m) r1
            java.lang.Object r2 = r1.d()
            Qe.B r3 = cg.m.g
            if (r2 == r3) goto L_0x0011
            return r2
        L_0x0011:
            cg.m r2 = r1.c()
        L_0x0015:
            boolean r3 = r0.compareAndSet(r4, r1, r2)
            if (r3 == 0) goto L_0x001c
            goto L_0x0000
        L_0x001c:
            java.lang.Object r3 = r0.get(r4)
            if (r3 == r1) goto L_0x0015
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: cg.k.d():java.lang.Object");
    }
}
