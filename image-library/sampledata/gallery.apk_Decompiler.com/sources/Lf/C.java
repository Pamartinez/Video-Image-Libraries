package lf;

import B2.o;
import ge.W0;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1255e;
import rf.C1262l;
import rf.C1264n;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C extends C1264n {
    public static final C n;

    /* renamed from: o  reason: collision with root package name */
    public static final C1148a f4732o = new C1148a(10);
    public final C1255e e;
    public int f;
    public List g;

    /* renamed from: h  reason: collision with root package name */
    public List f4733h;

    /* renamed from: i  reason: collision with root package name */
    public List f4734i;

    /* renamed from: j  reason: collision with root package name */
    public X f4735j;
    public e0 k;
    public byte l;
    public int m;

    static {
        C c5 = new C();
        n = c5;
        List list = Collections.EMPTY_LIST;
        c5.g = list;
        c5.f4733h = list;
        c5.f4734i = list;
        c5.f4735j = X.f4794j;
        c5.k = e0.f4826h;
    }

    public C(B b) {
        super(b);
        this.l = -1;
        this.m = -1;
        this.e = b.d;
    }

    public final int a() {
        int i2 = this.m;
        if (i2 != -1) {
            return i2;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < this.g.size(); i8++) {
            i7 += o.d(3, (C1252b) this.g.get(i8));
        }
        for (int i10 = 0; i10 < this.f4733h.size(); i10++) {
            i7 += o.d(4, (C1252b) this.f4733h.get(i10));
        }
        for (int i11 = 0; i11 < this.f4734i.size(); i11++) {
            i7 += o.d(5, (C1252b) this.f4734i.get(i11));
        }
        if ((this.f & 1) == 1) {
            i7 += o.d(30, this.f4735j);
        }
        if ((this.f & 2) == 2) {
            i7 += o.d(32, this.k);
        }
        int size = this.e.size() + h() + i7;
        this.m = size;
        return size;
    }

    public final C1262l b() {
        return B.f();
    }

    public final C1262l c() {
        B f5 = B.f();
        f5.g(this);
        return f5;
    }

    public final void d(o oVar) {
        a();
        W0 w02 = new W0((C1264n) this);
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            oVar.o(3, (C1252b) this.g.get(i2));
        }
        for (int i7 = 0; i7 < this.f4733h.size(); i7++) {
            oVar.o(4, (C1252b) this.f4733h.get(i7));
        }
        for (int i8 = 0; i8 < this.f4734i.size(); i8++) {
            oVar.o(5, (C1252b) this.f4734i.get(i8));
        }
        if ((this.f & 1) == 1) {
            oVar.o(30, this.f4735j);
        }
        if ((this.f & 2) == 2) {
            oVar.o(32, this.k);
        }
        w02.A0(200, oVar);
        oVar.r(this.e);
    }

    public final C1252b getDefaultInstanceForType() {
        return n;
    }

    public final boolean isInitialized() {
        byte b = this.l;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            if (!((C1171y) this.g.get(i2)).isInitialized()) {
                this.l = 0;
                return false;
            }
        }
        for (int i7 = 0; i7 < this.f4733h.size(); i7++) {
            if (!((G) this.f4733h.get(i7)).isInitialized()) {
                this.l = 0;
                return false;
            }
        }
        for (int i8 = 0; i8 < this.f4734i.size(); i8++) {
            if (!((T) this.f4734i.get(i8)).isInitialized()) {
                this.l = 0;
                return false;
            }
        }
        if ((this.f & 1) == 1 && !this.f4735j.isInitialized()) {
            this.l = 0;
            return false;
        } else if (!g()) {
            this.l = 0;
            return false;
        } else {
            this.l = 1;
            return true;
        }
    }

    public C() {
        this.l = -1;
        this.m = -1;
        this.e = C1255e.d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: lf.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: lf.m} */
    /* JADX WARNING: type inference failed for: r9v0 */
    /* JADX WARNING: type inference failed for: r9v5 */
    /* JADX WARNING: type inference failed for: r9v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C(rf.C1256f r11, rf.C1258h r12) {
        /*
            r10 = this;
            r10.<init>()
            r0 = -1
            r10.l = r0
            r10.m = r0
            java.util.List r0 = java.util.Collections.EMPTY_LIST
            r10.g = r0
            r10.f4733h = r0
            r10.f4734i = r0
            lf.X r0 = lf.X.f4794j
            r10.f4735j = r0
            lf.e0 r0 = lf.e0.f4826h
            r10.k = r0
            rf.d r0 = new rf.d
            r0.<init>()
            r1 = 1
            B2.o r2 = B2.o.j(r0, r1)
            r3 = 0
            r4 = r3
        L_0x0024:
            r5 = 4
            r6 = 2
            if (r3 != 0) goto L_0x0147
            int r7 = r11.n()     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            if (r7 == 0) goto L_0x0049
            r8 = 26
            if (r7 == r8) goto L_0x00e4
            r8 = 34
            if (r7 == r8) goto L_0x00ca
            r8 = 42
            if (r7 == r8) goto L_0x00b0
            r8 = 242(0xf2, float:3.39E-43)
            r9 = 0
            if (r7 == r8) goto L_0x0086
            r8 = 258(0x102, float:3.62E-43)
            if (r7 == r8) goto L_0x0054
            boolean r5 = r10.m(r11, r2, r12, r7)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            if (r5 != 0) goto L_0x0024
        L_0x0049:
            r3 = r1
            goto L_0x0024
        L_0x004b:
            r11 = move-exception
            goto L_0x010d
        L_0x004e:
            r11 = move-exception
            goto L_0x00fe
        L_0x0051:
            r11 = move-exception
            goto L_0x010a
        L_0x0054:
            int r7 = r10.f     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r7 = r7 & r6
            if (r7 != r6) goto L_0x006b
            lf.e0 r7 = r10.k     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r7.getClass()     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            lf.m r9 = new lf.m     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r8 = 2
            r9.<init>(r8)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            java.util.List r8 = java.util.Collections.EMPTY_LIST     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r9.g = r8     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r9.l(r7)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
        L_0x006b:
            lf.a r7 = lf.e0.f4827i     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            rf.b r7 = r11.g(r7, r12)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            lf.e0 r7 = (lf.e0) r7     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r10.k = r7     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            if (r9 == 0) goto L_0x0080
            r9.l(r7)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            lf.e0 r7 = r9.g()     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r10.k = r7     // Catch:{ u -> 0x0051, IOException -> 0x004e }
        L_0x0080:
            int r7 = r10.f     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r7 = r7 | r6
            r10.f = r7     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            goto L_0x0024
        L_0x0086:
            int r7 = r10.f     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r7 = r7 & r1
            if (r7 != r1) goto L_0x0094
            lf.X r7 = r10.f4735j     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r7.getClass()     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            lf.f r9 = lf.X.g(r7)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
        L_0x0094:
            lf.a r7 = lf.X.k     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            rf.b r7 = r11.g(r7, r12)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            lf.X r7 = (lf.X) r7     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r10.f4735j = r7     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            if (r9 == 0) goto L_0x00a9
            r9.k(r7)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            lf.X r7 = r9.f()     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r10.f4735j = r7     // Catch:{ u -> 0x0051, IOException -> 0x004e }
        L_0x00a9:
            int r7 = r10.f     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r7 = r7 | r1
            r10.f = r7     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            goto L_0x0024
        L_0x00b0:
            r7 = r4 & 4
            if (r7 == r5) goto L_0x00bd
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r7.<init>()     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r10.f4734i = r7     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r4 = r4 | 4
        L_0x00bd:
            java.util.List r7 = r10.f4734i     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            lf.a r8 = lf.T.s     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            rf.b r8 = r11.g(r8, r12)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r7.add(r8)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            goto L_0x0024
        L_0x00ca:
            r7 = r4 & 2
            if (r7 == r6) goto L_0x00d7
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r7.<init>()     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r10.f4733h = r7     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r4 = r4 | 2
        L_0x00d7:
            java.util.List r7 = r10.f4733h     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            lf.a r8 = lf.G.y     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            rf.b r8 = r11.g(r8, r12)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r7.add(r8)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            goto L_0x0024
        L_0x00e4:
            r7 = r4 & 1
            if (r7 == r1) goto L_0x00f1
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r7.<init>()     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r10.g = r7     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r4 = r4 | 1
        L_0x00f1:
            java.util.List r7 = r10.g     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            lf.a r8 = lf.C1171y.y     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            rf.b r8 = r11.g(r8, r12)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            r7.add(r8)     // Catch:{ u -> 0x0051, IOException -> 0x004e }
            goto L_0x0024
        L_0x00fe:
            rf.u r12 = new rf.u     // Catch:{ all -> 0x004b }
            java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x004b }
            r12.<init>(r11)     // Catch:{ all -> 0x004b }
            r12.d = r10     // Catch:{ all -> 0x004b }
            throw r12     // Catch:{ all -> 0x004b }
        L_0x010a:
            r11.d = r10     // Catch:{ all -> 0x004b }
            throw r11     // Catch:{ all -> 0x004b }
        L_0x010d:
            r12 = r4 & 1
            if (r12 != r1) goto L_0x0119
            java.util.List r12 = r10.g
            java.util.List r12 = java.util.Collections.unmodifiableList(r12)
            r10.g = r12
        L_0x0119:
            r12 = r4 & 2
            if (r12 != r6) goto L_0x0125
            java.util.List r12 = r10.f4733h
            java.util.List r12 = java.util.Collections.unmodifiableList(r12)
            r10.f4733h = r12
        L_0x0125:
            r12 = r4 & 4
            if (r12 != r5) goto L_0x0131
            java.util.List r12 = r10.f4734i
            java.util.List r12 = java.util.Collections.unmodifiableList(r12)
            r10.f4734i = r12
        L_0x0131:
            r2.i()     // Catch:{ IOException -> 0x0134, all -> 0x013b }
        L_0x0134:
            rf.e r12 = r0.f()
            r10.e = r12
            goto L_0x0143
        L_0x013b:
            r11 = move-exception
            rf.e r12 = r0.f()
            r10.e = r12
            throw r11
        L_0x0143:
            r10.l()
            throw r11
        L_0x0147:
            r11 = r4 & 1
            if (r11 != r1) goto L_0x0153
            java.util.List r11 = r10.g
            java.util.List r11 = java.util.Collections.unmodifiableList(r11)
            r10.g = r11
        L_0x0153:
            r11 = r4 & 2
            if (r11 != r6) goto L_0x015f
            java.util.List r11 = r10.f4733h
            java.util.List r11 = java.util.Collections.unmodifiableList(r11)
            r10.f4733h = r11
        L_0x015f:
            r11 = r4 & 4
            if (r11 != r5) goto L_0x016b
            java.util.List r11 = r10.f4734i
            java.util.List r11 = java.util.Collections.unmodifiableList(r11)
            r10.f4734i = r11
        L_0x016b:
            r2.i()     // Catch:{ IOException -> 0x016e, all -> 0x0175 }
        L_0x016e:
            rf.e r11 = r0.f()
            r10.e = r11
            goto L_0x017d
        L_0x0175:
            r11 = move-exception
            rf.e r12 = r0.f()
            r10.e = r12
            throw r11
        L_0x017d:
            r10.l()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lf.C.<init>(rf.f, rf.h):void");
    }
}
