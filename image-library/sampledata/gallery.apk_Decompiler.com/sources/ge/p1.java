package ge;

import io.grpc.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p1 extends a {
    public final v1 e;
    public long f;
    public final /* synthetic */ C1057t0 g;

    public p1(C1057t0 t0Var, v1 v1Var) {
        this.g = t0Var;
        this.e = v1Var;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0062, code lost:
        if (r8 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0064, code lost:
        r8.run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void f(long r9) {
        /*
            r8 = this;
            ge.t0 r0 = r8.g
            ge.r1 r0 = r0.r
            ge.v1 r0 = r0.f
            if (r0 == 0) goto L_0x0009
            goto L_0x0067
        L_0x0009:
            ge.t0 r0 = r8.g
            java.lang.Object r0 = r0.l
            monitor-enter(r0)
            ge.t0 r1 = r8.g     // Catch:{ all -> 0x002c }
            ge.r1 r1 = r1.r     // Catch:{ all -> 0x002c }
            ge.v1 r1 = r1.f     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0068
            ge.v1 r1 = r8.e     // Catch:{ all -> 0x002c }
            boolean r2 = r1.b     // Catch:{ all -> 0x002c }
            if (r2 == 0) goto L_0x001d
            goto L_0x0068
        L_0x001d:
            long r2 = r8.f     // Catch:{ all -> 0x002c }
            long r2 = r2 + r9
            r8.f = r2     // Catch:{ all -> 0x002c }
            ge.t0 r9 = r8.g     // Catch:{ all -> 0x002c }
            long r4 = r9.w     // Catch:{ all -> 0x002c }
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 > 0) goto L_0x002e
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r8 = move-exception
            goto L_0x006a
        L_0x002e:
            long r6 = r9.n     // Catch:{ all -> 0x002c }
            int r10 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            r6 = 1
            if (r10 <= 0) goto L_0x0038
            r1.f4568c = r6     // Catch:{ all -> 0x002c }
            goto L_0x0053
        L_0x0038:
            B1.b r9 = r9.m     // Catch:{ all -> 0x002c }
            long r2 = r2 - r4
            java.lang.Object r9 = r9.e     // Catch:{ all -> 0x002c }
            java.util.concurrent.atomic.AtomicLong r9 = (java.util.concurrent.atomic.AtomicLong) r9     // Catch:{ all -> 0x002c }
            long r9 = r9.addAndGet(r2)     // Catch:{ all -> 0x002c }
            ge.t0 r1 = r8.g     // Catch:{ all -> 0x002c }
            long r2 = r8.f     // Catch:{ all -> 0x002c }
            r1.w = r2     // Catch:{ all -> 0x002c }
            long r1 = r1.f4558o     // Catch:{ all -> 0x002c }
            int r9 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r9 <= 0) goto L_0x0053
            ge.v1 r9 = r8.e     // Catch:{ all -> 0x002c }
            r9.f4568c = r6     // Catch:{ all -> 0x002c }
        L_0x0053:
            ge.v1 r9 = r8.e     // Catch:{ all -> 0x002c }
            boolean r10 = r9.f4568c     // Catch:{ all -> 0x002c }
            if (r10 == 0) goto L_0x0060
            ge.t0 r8 = r8.g     // Catch:{ all -> 0x002c }
            ge.i1 r8 = r8.c(r9)     // Catch:{ all -> 0x002c }
            goto L_0x0061
        L_0x0060:
            r8 = 0
        L_0x0061:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            if (r8 == 0) goto L_0x0067
            r8.run()
        L_0x0067:
            return
        L_0x0068:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x006a:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.p1.f(long):void");
    }
}
