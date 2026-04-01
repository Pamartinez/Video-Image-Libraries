package lf;

import B2.o;
import rf.C1255e;
import rf.C1262l;
import rf.C1267q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class O extends C1267q {
    public static final O k;
    public static final C1148a l = new C1148a(17);
    public final C1255e d;
    public int e;
    public N f;
    public Q g;

    /* renamed from: h  reason: collision with root package name */
    public int f4762h;

    /* renamed from: i  reason: collision with root package name */
    public byte f4763i;

    /* renamed from: j  reason: collision with root package name */
    public int f4764j;

    static {
        O o2 = new O();
        k = o2;
        o2.f = N.INV;
        o2.g = Q.w;
        o2.f4762h = 0;
    }

    public O() {
        this.f4763i = -1;
        this.f4764j = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2;
        int i7 = this.f4764j;
        if (i7 != -1) {
            return i7;
        }
        if ((this.e & 1) == 1) {
            i2 = o.a(1, this.f.a());
        } else {
            i2 = 0;
        }
        if ((this.e & 2) == 2) {
            i2 += o.d(2, this.g);
        }
        if ((this.e & 4) == 4) {
            i2 += o.b(3, this.f4762h);
        }
        int size = this.d.size() + i2;
        this.f4764j = size;
        return size;
    }

    public final C1262l b() {
        return M.e();
    }

    public final C1262l c() {
        M e7 = M.e();
        e7.f(this);
        return e7;
    }

    public final void d(o oVar) {
        a();
        if ((this.e & 1) == 1) {
            oVar.l(1, this.f.a());
        }
        if ((this.e & 2) == 2) {
            oVar.o(2, this.g);
        }
        if ((this.e & 4) == 4) {
            oVar.m(3, this.f4762h);
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        byte b = this.f4763i;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.e & 2) != 2 || this.g.isInitialized()) {
            this.f4763i = 1;
            return true;
        }
        this.f4763i = 0;
        return false;
    }

    public O(M m) {
        this.f4763i = -1;
        this.f4764j = -1;
        this.d = m.d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: lf.N} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: lf.P} */
    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r6v8 */
    /* JADX WARNING: type inference failed for: r6v9 */
    /* JADX WARNING: type inference failed for: r6v10 */
    /* JADX WARNING: type inference failed for: r6v11 */
    /* JADX WARNING: type inference failed for: r6v12 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public O(rf.C1256f r9, rf.C1258h r10) {
        /*
            r8 = this;
            r8.<init>()
            r0 = -1
            r8.f4763i = r0
            r8.f4764j = r0
            lf.N r0 = lf.N.INV
            r8.f = r0
            lf.Q r0 = lf.Q.w
            r8.g = r0
            r0 = 0
            r8.f4762h = r0
            rf.d r1 = new rf.d
            r1.<init>()
            r2 = 1
            B2.o r3 = B2.o.j(r1, r2)
        L_0x001d:
            if (r0 != 0) goto L_0x00c6
            int r4 = r9.n()     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            if (r4 == 0) goto L_0x0039
            r5 = 8
            r6 = 0
            r7 = 2
            if (r4 == r5) goto L_0x0079
            r5 = 18
            if (r4 == r5) goto L_0x0050
            r5 = 24
            if (r4 == r5) goto L_0x0043
            boolean r4 = r9.q(r4, r3)     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            if (r4 != 0) goto L_0x001d
        L_0x0039:
            r0 = r2
            goto L_0x001d
        L_0x003b:
            r9 = move-exception
            goto L_0x00b3
        L_0x003e:
            r9 = move-exception
            goto L_0x00a4
        L_0x0040:
            r9 = move-exception
            goto L_0x00b0
        L_0x0043:
            int r4 = r8.e     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            r4 = r4 | 4
            r8.e = r4     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            int r4 = r9.k()     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            r8.f4762h = r4     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            goto L_0x001d
        L_0x0050:
            int r4 = r8.e     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            r4 = r4 & r7
            if (r4 != r7) goto L_0x005e
            lf.Q r4 = r8.g     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            r4.getClass()     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            lf.P r6 = lf.Q.p(r4)     // Catch:{ u -> 0x0040, IOException -> 0x003e }
        L_0x005e:
            lf.a r4 = lf.Q.f4770x     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            rf.b r4 = r9.g(r4, r10)     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            lf.Q r4 = (lf.Q) r4     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            r8.g = r4     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            if (r6 == 0) goto L_0x0073
            r6.g(r4)     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            lf.Q r4 = r6.e()     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            r8.g = r4     // Catch:{ u -> 0x0040, IOException -> 0x003e }
        L_0x0073:
            int r4 = r8.e     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            r4 = r4 | r7
            r8.e = r4     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            goto L_0x001d
        L_0x0079:
            int r5 = r9.k()     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            if (r5 == 0) goto L_0x0090
            if (r5 == r2) goto L_0x008d
            if (r5 == r7) goto L_0x008a
            r7 = 3
            if (r5 == r7) goto L_0x0087
            goto L_0x0092
        L_0x0087:
            lf.N r6 = lf.N.STAR     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            goto L_0x0092
        L_0x008a:
            lf.N r6 = lf.N.INV     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            goto L_0x0092
        L_0x008d:
            lf.N r6 = lf.N.OUT     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            goto L_0x0092
        L_0x0090:
            lf.N r6 = lf.N.IN     // Catch:{ u -> 0x0040, IOException -> 0x003e }
        L_0x0092:
            if (r6 != 0) goto L_0x009b
            r3.v(r4)     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            r3.v(r5)     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            goto L_0x001d
        L_0x009b:
            int r4 = r8.e     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            r4 = r4 | r2
            r8.e = r4     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            r8.f = r6     // Catch:{ u -> 0x0040, IOException -> 0x003e }
            goto L_0x001d
        L_0x00a4:
            rf.u r10 = new rf.u     // Catch:{ all -> 0x003b }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x003b }
            r10.<init>(r9)     // Catch:{ all -> 0x003b }
            r10.d = r8     // Catch:{ all -> 0x003b }
            throw r10     // Catch:{ all -> 0x003b }
        L_0x00b0:
            r9.d = r8     // Catch:{ all -> 0x003b }
            throw r9     // Catch:{ all -> 0x003b }
        L_0x00b3:
            r3.i()     // Catch:{ IOException -> 0x00b6, all -> 0x00bd }
        L_0x00b6:
            rf.e r10 = r1.f()
            r8.d = r10
            goto L_0x00c5
        L_0x00bd:
            r9 = move-exception
            rf.e r10 = r1.f()
            r8.d = r10
            throw r9
        L_0x00c5:
            throw r9
        L_0x00c6:
            r3.i()     // Catch:{ IOException -> 0x00c9, all -> 0x00d0 }
        L_0x00c9:
            rf.e r9 = r1.f()
            r8.d = r9
            return
        L_0x00d0:
            r9 = move-exception
            rf.e r10 = r1.f()
            r8.d = r10
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: lf.O.<init>(rf.f, rf.h):void");
    }
}
