package ge;

import B1.b;
import He.F;
import ee.a0;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: ge.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1018e extends V {

    /* renamed from: a  reason: collision with root package name */
    public final C1064x f4504a;
    public final AtomicInteger b = new AtomicInteger(-2147483647);

    /* renamed from: c  reason: collision with root package name */
    public volatile a0 f4505c;
    public a0 d;
    public a0 e;
    public final b f = new b(11, this);
    public final /* synthetic */ C1021f g;

    public C1018e(C1021f fVar, C1064x xVar, String str) {
        this.g = fVar;
        F.n(xVar, "delegate");
        this.f4504a = xVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        if (r0 == null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        super.d(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        if (r1 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        super.b(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void g(ge.C1018e r3) {
        /*
            monitor-enter(r3)
            java.util.concurrent.atomic.AtomicInteger r0 = r3.b     // Catch:{ all -> 0x000b }
            int r0 = r0.get()     // Catch:{ all -> 0x000b }
            if (r0 == 0) goto L_0x000d
            monitor-exit(r3)     // Catch:{ all -> 0x000b }
            return
        L_0x000b:
            r0 = move-exception
            goto L_0x0022
        L_0x000d:
            ee.a0 r0 = r3.d     // Catch:{ all -> 0x000b }
            ee.a0 r1 = r3.e     // Catch:{ all -> 0x000b }
            r2 = 0
            r3.d = r2     // Catch:{ all -> 0x000b }
            r3.e = r2     // Catch:{ all -> 0x000b }
            monitor-exit(r3)     // Catch:{ all -> 0x000b }
            if (r0 == 0) goto L_0x001c
            super.d(r0)
        L_0x001c:
            if (r1 == 0) goto L_0x0021
            super.b(r1)
        L_0x0021:
            return
        L_0x0022:
            monitor-exit(r3)     // Catch:{ all -> 0x000b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.C1018e.g(ge.e):void");
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [Yd.b, java.lang.Object] */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public final ge.r a(io.grpc.MethodDescriptor r2, ee.M r3, io.grpc.CallOptions r4, io.grpc.a[] r5) {
        /*
            r1 = this;
            io.grpc.CallCredentials r0 = r4.f4613c
            if (r0 != 0) goto L_0x0005
            r0 = 0
        L_0x0005:
            if (r0 == 0) goto L_0x00f6
            Yd.b r2 = new Yd.b
            B1.b r3 = r1.f
            r2.<init>()
            java.lang.Object r4 = new java.lang.Object
            r4.<init>()
            r2.d = r4
            ee.C0979l.b()
            r2.b = r3
            r2.f3915c = r5
            java.util.concurrent.atomic.AtomicInteger r3 = r1.b
            int r3 = r3.incrementAndGet()
            if (r3 <= 0) goto L_0x003d
            B1.b r2 = r1.f
            java.lang.Object r2 = r2.e
            ge.e r2 = (ge.C1018e) r2
            java.util.concurrent.atomic.AtomicInteger r3 = r2.b
            int r3 = r3.decrementAndGet()
            if (r3 != 0) goto L_0x0035
            g(r2)
        L_0x0035:
            ge.T r2 = new ge.T
            ee.a0 r1 = r1.f4505c
            r2.<init>(r1, r5)
            return r2
        L_0x003d:
            ge.Q0 r3 = new ge.Q0
            r4 = 2
            r3.<init>(r4)
            ge.f r1 = r1.g     // Catch:{ all -> 0x004c }
            ge.w0 r1 = r1.e     // Catch:{ all -> 0x004c }
            r0.a(r3, r1, r2)     // Catch:{ all -> 0x004c }
            goto L_0x00da
        L_0x004c:
            r1 = move-exception
            ee.a0 r3 = ee.a0.f4288j
            java.lang.String r4 = "Credentials should use fail() instead of throwing exceptions"
            ee.a0 r3 = r3.g(r4)
            ee.a0 r1 = r3.f(r1)
            boolean r3 = r1.e()
            r4 = 1
            r3 = r3 ^ r4
            java.lang.String r5 = "Cannot fail with OK status"
            He.F.i(r5, r3)
            boolean r3 = r2.f3914a
            r3 = r3 ^ r4
            java.lang.String r5 = "apply() or fail() already called"
            He.F.t(r3, r5)
            ge.T r3 = new ge.T
            ee.a0 r1 = ge.Z.e(r1)
            java.lang.Object r5 = r2.f3915c
            io.grpc.a[] r5 = (io.grpc.a[]) r5
            ge.s r0 = ge.C1054s.PROCESSED
            r3.<init>(r1, r0, r5)
            boolean r1 = r2.f3914a
            r1 = r1 ^ r4
            java.lang.String r5 = "already finalized"
            He.F.t(r1, r5)
            r2.f3914a = r4
            java.lang.Object r0 = r2.d
            monitor-enter(r0)
            java.lang.Object r1 = r2.e     // Catch:{ all -> 0x0093 }
            ge.r r1 = (ge.r) r1     // Catch:{ all -> 0x0093 }
            r5 = 0
            if (r1 != 0) goto L_0x0095
            r2.e = r3     // Catch:{ all -> 0x0093 }
            r1 = r4
            goto L_0x0096
        L_0x0093:
            r1 = move-exception
            goto L_0x00f4
        L_0x0095:
            r1 = r5
        L_0x0096:
            monitor-exit(r0)     // Catch:{ all -> 0x0093 }
            if (r1 == 0) goto L_0x00ad
            java.lang.Object r1 = r2.b
            B1.b r1 = (B1.b) r1
            java.lang.Object r1 = r1.e
            ge.e r1 = (ge.C1018e) r1
            java.util.concurrent.atomic.AtomicInteger r3 = r1.b
            int r3 = r3.decrementAndGet()
            if (r3 != 0) goto L_0x00da
            g(r1)
            goto L_0x00da
        L_0x00ad:
            java.lang.Object r1 = r2.f
            ge.L r1 = (ge.L) r1
            if (r1 == 0) goto L_0x00b4
            goto L_0x00b5
        L_0x00b4:
            r4 = r5
        L_0x00b5:
            java.lang.String r1 = "delayedStream is null"
            He.F.t(r4, r1)
            java.lang.Object r1 = r2.f
            ge.L r1 = (ge.L) r1
            ge.I r1 = r1.l(r3)
            if (r1 == 0) goto L_0x00c7
            r1.run()
        L_0x00c7:
            java.lang.Object r1 = r2.b
            B1.b r1 = (B1.b) r1
            java.lang.Object r1 = r1.e
            ge.e r1 = (ge.C1018e) r1
            java.util.concurrent.atomic.AtomicInteger r3 = r1.b
            int r3 = r3.decrementAndGet()
            if (r3 != 0) goto L_0x00da
            g(r1)
        L_0x00da:
            java.lang.Object r1 = r2.d
            monitor-enter(r1)
            java.lang.Object r3 = r2.e     // Catch:{ all -> 0x00ee }
            ge.r r3 = (ge.r) r3     // Catch:{ all -> 0x00ee }
            if (r3 != 0) goto L_0x00f0
            ge.L r3 = new ge.L     // Catch:{ all -> 0x00ee }
            r3.<init>()     // Catch:{ all -> 0x00ee }
            r2.f = r3     // Catch:{ all -> 0x00ee }
            r2.e = r3     // Catch:{ all -> 0x00ee }
            monitor-exit(r1)     // Catch:{ all -> 0x00ee }
            goto L_0x00f1
        L_0x00ee:
            r2 = move-exception
            goto L_0x00f2
        L_0x00f0:
            monitor-exit(r1)     // Catch:{ all -> 0x00ee }
        L_0x00f1:
            return r3
        L_0x00f2:
            monitor-exit(r1)     // Catch:{ all -> 0x00ee }
            throw r2
        L_0x00f4:
            monitor-exit(r0)     // Catch:{ all -> 0x0093 }
            throw r1
        L_0x00f6:
            java.util.concurrent.atomic.AtomicInteger r0 = r1.b
            int r0 = r0.get()
            if (r0 < 0) goto L_0x0106
            ge.T r2 = new ge.T
            ee.a0 r1 = r1.f4505c
            r2.<init>(r1, r5)
            return r2
        L_0x0106:
            ge.x r1 = r1.f4504a
            ge.r r1 = r1.a(r2, r3, r4, r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.C1018e.a(io.grpc.MethodDescriptor, ee.M, io.grpc.CallOptions, io.grpc.a[]):ge.r");
    }

    public final void b(a0 a0Var) {
        F.n(a0Var, "status");
        synchronized (this) {
            try {
                if (this.b.get() < 0) {
                    this.f4505c = a0Var;
                    this.b.addAndGet(Integer.MAX_VALUE);
                } else if (this.e != null) {
                    return;
                }
                if (this.b.get() != 0) {
                    this.e = a0Var;
                } else {
                    super.b(a0Var);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public final void d(a0 a0Var) {
        F.n(a0Var, "status");
        synchronized (this) {
            try {
                if (this.b.get() < 0) {
                    this.f4505c = a0Var;
                    this.b.addAndGet(Integer.MAX_VALUE);
                    if (this.b.get() != 0) {
                        this.d = a0Var;
                    } else {
                        super.d(a0Var);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final C1064x f() {
        return this.f4504a;
    }
}
