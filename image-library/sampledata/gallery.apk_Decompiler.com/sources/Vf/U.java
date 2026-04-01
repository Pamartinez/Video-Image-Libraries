package Vf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class U implements Runnable, Comparable, O {
    private volatile Object _heap;
    public long d;
    public int e = -1;

    public U(long j2) {
        this.d = j2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: cg.v} */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0032, code lost:
        throw r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.Object r0 = r5._heap     // Catch:{ all -> 0x0011 }
            Qe.B r1 = Vf.D.b     // Catch:{ all -> 0x0011 }
            if (r0 != r1) goto L_0x0009
            monitor-exit(r5)
            return
        L_0x0009:
            boolean r2 = r0 instanceof Vf.V     // Catch:{ all -> 0x0011 }
            r3 = 0
            if (r2 == 0) goto L_0x0013
            Vf.V r0 = (Vf.V) r0     // Catch:{ all -> 0x0011 }
            goto L_0x0014
        L_0x0011:
            r0 = move-exception
            goto L_0x0031
        L_0x0013:
            r0 = r3
        L_0x0014:
            if (r0 == 0) goto L_0x002d
            monitor-enter(r0)     // Catch:{ all -> 0x0011 }
            java.lang.Object r2 = r5._heap     // Catch:{ all -> 0x002a }
            boolean r4 = r2 instanceof cg.v     // Catch:{ all -> 0x002a }
            if (r4 == 0) goto L_0x0020
            r3 = r2
            cg.v r3 = (cg.v) r3     // Catch:{ all -> 0x002a }
        L_0x0020:
            if (r3 != 0) goto L_0x0023
            goto L_0x0028
        L_0x0023:
            int r2 = r5.e     // Catch:{ all -> 0x002a }
            r0.b(r2)     // Catch:{ all -> 0x002a }
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            goto L_0x002d
        L_0x002a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0011 }
            throw r1     // Catch:{ all -> 0x0011 }
        L_0x002d:
            r5._heap = r1     // Catch:{ all -> 0x0011 }
            monitor-exit(r5)
            return
        L_0x0031:
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.U.a():void");
    }

    public final int c(long j2, V v, W w) {
        U u;
        boolean z;
        synchronized (this) {
            if (this._heap == D.b) {
                return 2;
            }
            synchronized (v) {
                try {
                    U[] uArr = v.f4035a;
                    if (uArr != null) {
                        u = uArr[0];
                    } else {
                        u = null;
                    }
                    if (W.f3849j.get(w) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        return 1;
                    }
                    if (u == null) {
                        v.f3846c = j2;
                    } else {
                        long j3 = u.d;
                        if (j3 - j2 < 0) {
                            j2 = j3;
                        }
                        if (j2 - v.f3846c > 0) {
                            v.f3846c = j2;
                        }
                    }
                    long j8 = this.d;
                    long j10 = v.f3846c;
                    if (j8 - j10 < 0) {
                        this.d = j10;
                    }
                    v.a(this);
                    return 0;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public final int compareTo(Object obj) {
        int i2 = ((this.d - ((U) obj).d) > 0 ? 1 : ((this.d - ((U) obj).d) == 0 ? 0 : -1));
        if (i2 > 0) {
            return 1;
        }
        if (i2 < 0) {
            return -1;
        }
        return 0;
    }

    public final void d(V v) {
        if (this._heap != D.b) {
            this._heap = v;
            return;
        }
        throw new IllegalArgumentException("Failed requirement.");
    }

    public String toString() {
        return "Delayed[nanos=" + this.d + ']';
    }
}
