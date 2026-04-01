package lf;

import B2.o;
import rf.C1255e;
import rf.C1262l;
import rf.C1267q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d0 extends C1267q {
    public static final d0 n;

    /* renamed from: o  reason: collision with root package name */
    public static final C1148a f4819o = new C1148a(22);
    public final C1255e d;
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public b0 f4820h;

    /* renamed from: i  reason: collision with root package name */
    public int f4821i;

    /* renamed from: j  reason: collision with root package name */
    public int f4822j;
    public c0 k;
    public byte l;
    public int m;

    static {
        d0 d0Var = new d0();
        n = d0Var;
        d0Var.f = 0;
        d0Var.g = 0;
        d0Var.f4820h = b0.ERROR;
        d0Var.f4821i = 0;
        d0Var.f4822j = 0;
        d0Var.k = c0.LANGUAGE_VERSION;
    }

    public d0() {
        this.l = -1;
        this.m = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2;
        int i7 = this.m;
        if (i7 != -1) {
            return i7;
        }
        if ((this.e & 1) == 1) {
            i2 = o.b(1, this.f);
        } else {
            i2 = 0;
        }
        if ((this.e & 2) == 2) {
            i2 += o.b(2, this.g);
        }
        if ((this.e & 4) == 4) {
            i2 += o.a(3, this.f4820h.a());
        }
        if ((this.e & 8) == 8) {
            i2 += o.b(4, this.f4821i);
        }
        if ((this.e & 16) == 16) {
            i2 += o.b(5, this.f4822j);
        }
        if ((this.e & 32) == 32) {
            i2 += o.a(6, this.k.a());
        }
        int size = this.d.size() + i2;
        this.m = size;
        return size;
    }

    public final C1262l b() {
        return a0.e();
    }

    public final C1262l c() {
        a0 e7 = a0.e();
        e7.f(this);
        return e7;
    }

    public final void d(o oVar) {
        a();
        if ((this.e & 1) == 1) {
            oVar.m(1, this.f);
        }
        if ((this.e & 2) == 2) {
            oVar.m(2, this.g);
        }
        if ((this.e & 4) == 4) {
            oVar.l(3, this.f4820h.a());
        }
        if ((this.e & 8) == 8) {
            oVar.m(4, this.f4821i);
        }
        if ((this.e & 16) == 16) {
            oVar.m(5, this.f4822j);
        }
        if ((this.e & 32) == 32) {
            oVar.l(6, this.k.a());
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        if (this.l == 1) {
            return true;
        }
        this.l = 1;
        return true;
    }

    public d0(a0 a0Var) {
        this.l = -1;
        this.m = -1;
        this.d = a0Var.d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: lf.b0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: lf.c0} */
    /* JADX WARNING: type inference failed for: r9v0 */
    /* JADX WARNING: type inference failed for: r9v9 */
    /* JADX WARNING: type inference failed for: r9v10 */
    /* JADX WARNING: type inference failed for: r9v11 */
    /* JADX WARNING: type inference failed for: r9v12 */
    /* JADX WARNING: type inference failed for: r9v13 */
    /* JADX WARNING: type inference failed for: r9v14 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public d0(rf.C1256f r11) {
        /*
            r10 = this;
            r10.<init>()
            r0 = -1
            r10.l = r0
            r10.m = r0
            r0 = 0
            r10.f = r0
            r10.g = r0
            lf.b0 r1 = lf.b0.ERROR
            r10.f4820h = r1
            r10.f4821i = r0
            r10.f4822j = r0
            lf.c0 r1 = lf.c0.LANGUAGE_VERSION
            r10.k = r1
            rf.d r1 = new rf.d
            r1.<init>()
            r2 = 1
            B2.o r3 = B2.o.j(r1, r2)
        L_0x0023:
            if (r0 != 0) goto L_0x00f5
            int r4 = r11.n()     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            if (r4 == 0) goto L_0x004b
            r5 = 8
            if (r4 == r5) goto L_0x00c6
            r6 = 2
            r7 = 16
            if (r4 == r7) goto L_0x00b9
            r8 = 24
            r9 = 0
            if (r4 == r8) goto L_0x0092
            r8 = 32
            if (r4 == r8) goto L_0x0086
            r5 = 40
            if (r4 == r5) goto L_0x007a
            r5 = 48
            if (r4 == r5) goto L_0x0056
            boolean r4 = r11.q(r4, r3)     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            if (r4 != 0) goto L_0x0023
        L_0x004b:
            r0 = r2
            goto L_0x0023
        L_0x004d:
            r11 = move-exception
            goto L_0x00e2
        L_0x0050:
            r11 = move-exception
            goto L_0x00d3
        L_0x0053:
            r11 = move-exception
            goto L_0x00df
        L_0x0056:
            int r5 = r11.k()     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            if (r5 == 0) goto L_0x0067
            if (r5 == r2) goto L_0x0064
            if (r5 == r6) goto L_0x0061
            goto L_0x0069
        L_0x0061:
            lf.c0 r9 = lf.c0.API_VERSION     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            goto L_0x0069
        L_0x0064:
            lf.c0 r9 = lf.c0.COMPILER_VERSION     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            goto L_0x0069
        L_0x0067:
            lf.c0 r9 = lf.c0.LANGUAGE_VERSION     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
        L_0x0069:
            if (r9 != 0) goto L_0x0072
            r3.v(r4)     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r3.v(r5)     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            goto L_0x0023
        L_0x0072:
            int r4 = r10.e     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r4 = r4 | r8
            r10.e = r4     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r10.k = r9     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            goto L_0x0023
        L_0x007a:
            int r4 = r10.e     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r4 = r4 | r7
            r10.e = r4     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            int r4 = r11.k()     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r10.f4822j = r4     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            goto L_0x0023
        L_0x0086:
            int r4 = r10.e     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r4 = r4 | r5
            r10.e = r4     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            int r4 = r11.k()     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r10.f4821i = r4     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            goto L_0x0023
        L_0x0092:
            int r5 = r11.k()     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            if (r5 == 0) goto L_0x00a3
            if (r5 == r2) goto L_0x00a0
            if (r5 == r6) goto L_0x009d
            goto L_0x00a5
        L_0x009d:
            lf.b0 r9 = lf.b0.HIDDEN     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            goto L_0x00a5
        L_0x00a0:
            lf.b0 r9 = lf.b0.ERROR     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            goto L_0x00a5
        L_0x00a3:
            lf.b0 r9 = lf.b0.WARNING     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
        L_0x00a5:
            if (r9 != 0) goto L_0x00af
            r3.v(r4)     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r3.v(r5)     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            goto L_0x0023
        L_0x00af:
            int r4 = r10.e     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r4 = r4 | 4
            r10.e = r4     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r10.f4820h = r9     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            goto L_0x0023
        L_0x00b9:
            int r4 = r10.e     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r4 = r4 | r6
            r10.e = r4     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            int r4 = r11.k()     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r10.g = r4     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            goto L_0x0023
        L_0x00c6:
            int r4 = r10.e     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r4 = r4 | r2
            r10.e = r4     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            int r4 = r11.k()     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            r10.f = r4     // Catch:{ u -> 0x0053, IOException -> 0x0050 }
            goto L_0x0023
        L_0x00d3:
            rf.u r0 = new rf.u     // Catch:{ all -> 0x004d }
            java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x004d }
            r0.<init>(r11)     // Catch:{ all -> 0x004d }
            r0.d = r10     // Catch:{ all -> 0x004d }
            throw r0     // Catch:{ all -> 0x004d }
        L_0x00df:
            r11.d = r10     // Catch:{ all -> 0x004d }
            throw r11     // Catch:{ all -> 0x004d }
        L_0x00e2:
            r3.i()     // Catch:{ IOException -> 0x00e5, all -> 0x00ec }
        L_0x00e5:
            rf.e r0 = r1.f()
            r10.d = r0
            goto L_0x00f4
        L_0x00ec:
            r11 = move-exception
            rf.e r0 = r1.f()
            r10.d = r0
            throw r11
        L_0x00f4:
            throw r11
        L_0x00f5:
            r3.i()     // Catch:{ IOException -> 0x00f8, all -> 0x00ff }
        L_0x00f8:
            rf.e r11 = r1.f()
            r10.d = r11
            return
        L_0x00ff:
            r11 = move-exception
            rf.e r0 = r1.f()
            r10.d = r0
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: lf.d0.<init>(rf.f):void");
    }
}
