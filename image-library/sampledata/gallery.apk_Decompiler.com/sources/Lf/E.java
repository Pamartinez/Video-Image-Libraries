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
public final class E extends C1264n {
    public static final E m;
    public static final C1148a n = new C1148a(11);
    public final C1255e e;
    public int f;
    public L g;

    /* renamed from: h  reason: collision with root package name */
    public K f4739h;

    /* renamed from: i  reason: collision with root package name */
    public C f4740i;

    /* renamed from: j  reason: collision with root package name */
    public List f4741j;
    public byte k;
    public int l;

    static {
        E e7 = new E();
        m = e7;
        e7.g = L.f4759h;
        e7.f4739h = K.f4757h;
        e7.f4740i = C.n;
        e7.f4741j = Collections.EMPTY_LIST;
    }

    public E(D d) {
        super(d);
        this.k = -1;
        this.l = -1;
        this.e = d.d;
    }

    public final int a() {
        int i2;
        int i7 = this.l;
        if (i7 != -1) {
            return i7;
        }
        if ((this.f & 1) == 1) {
            i2 = o.d(1, this.g);
        } else {
            i2 = 0;
        }
        if ((this.f & 2) == 2) {
            i2 += o.d(2, this.f4739h);
        }
        if ((this.f & 4) == 4) {
            i2 += o.d(3, this.f4740i);
        }
        for (int i8 = 0; i8 < this.f4741j.size(); i8++) {
            i2 += o.d(4, (C1252b) this.f4741j.get(i8));
        }
        int size = this.e.size() + h() + i2;
        this.l = size;
        return size;
    }

    public final C1262l b() {
        return D.f();
    }

    public final C1262l c() {
        D f5 = D.f();
        f5.g(this);
        return f5;
    }

    public final void d(o oVar) {
        a();
        W0 w02 = new W0((C1264n) this);
        if ((this.f & 1) == 1) {
            oVar.o(1, this.g);
        }
        if ((this.f & 2) == 2) {
            oVar.o(2, this.f4739h);
        }
        if ((this.f & 4) == 4) {
            oVar.o(3, this.f4740i);
        }
        for (int i2 = 0; i2 < this.f4741j.size(); i2++) {
            oVar.o(4, (C1252b) this.f4741j.get(i2));
        }
        w02.A0(200, oVar);
        oVar.r(this.e);
    }

    public final C1252b getDefaultInstanceForType() {
        return m;
    }

    public final boolean isInitialized() {
        byte b = this.k;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.f & 2) == 2 && !this.f4739h.isInitialized()) {
            this.k = 0;
            return false;
        } else if ((this.f & 4) != 4 || this.f4740i.isInitialized()) {
            for (int i2 = 0; i2 < this.f4741j.size(); i2++) {
                if (!((C1157j) this.f4741j.get(i2)).isInitialized()) {
                    this.k = 0;
                    return false;
                }
            }
            if (!g()) {
                this.k = 0;
                return false;
            }
            this.k = 1;
            return true;
        } else {
            this.k = 0;
            return false;
        }
    }

    public E() {
        this.k = -1;
        this.l = -1;
        this.e = C1255e.d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: lf.m} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: lf.m} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: lf.B} */
    /* JADX WARNING: type inference failed for: r8v0 */
    /* JADX WARNING: type inference failed for: r8v7 */
    /* JADX WARNING: type inference failed for: r8v8 */
    /* JADX WARNING: type inference failed for: r8v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public E(rf.C1256f r11, rf.C1258h r12) {
        /*
            r10 = this;
            r10.<init>()
            r0 = -1
            r10.k = r0
            r10.l = r0
            lf.L r0 = lf.L.f4759h
            r10.g = r0
            lf.K r0 = lf.K.f4757h
            r10.f4739h = r0
            lf.C r0 = lf.C.n
            r10.f4740i = r0
            java.util.List r0 = java.util.Collections.EMPTY_LIST
            r10.f4741j = r0
            rf.d r0 = new rf.d
            r0.<init>()
            r1 = 1
            B2.o r2 = B2.o.j(r0, r1)
            r3 = 0
            r4 = r3
        L_0x0024:
            r5 = 8
            if (r3 != 0) goto L_0x012d
            int r6 = r11.n()     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            if (r6 == 0) goto L_0x0045
            r7 = 10
            r8 = 0
            if (r6 == r7) goto L_0x00c9
            r7 = 18
            if (r6 == r7) goto L_0x0095
            r7 = 26
            if (r6 == r7) goto L_0x0068
            r7 = 34
            if (r6 == r7) goto L_0x0050
            boolean r5 = r10.m(r11, r2, r12, r6)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            if (r5 != 0) goto L_0x0024
        L_0x0045:
            r3 = r1
            goto L_0x0024
        L_0x0047:
            r11 = move-exception
            goto L_0x010b
        L_0x004a:
            r11 = move-exception
            goto L_0x00fc
        L_0x004d:
            r11 = move-exception
            goto L_0x0108
        L_0x0050:
            r6 = r4 & 8
            if (r6 == r5) goto L_0x005c
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r6.<init>()     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r10.f4741j = r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r4 = r5
        L_0x005c:
            java.util.List r6 = r10.f4741j     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            lf.a r7 = lf.C1157j.f4841N     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            rf.b r7 = r11.g(r7, r12)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r6.add(r7)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            goto L_0x0024
        L_0x0068:
            int r6 = r10.f     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r7 = 4
            r6 = r6 & r7
            if (r6 != r7) goto L_0x007a
            lf.C r6 = r10.f4740i     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r6.getClass()     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            lf.B r8 = lf.B.f()     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r8.g(r6)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
        L_0x007a:
            lf.a r6 = lf.C.f4732o     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            rf.b r6 = r11.g(r6, r12)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            lf.C r6 = (lf.C) r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r10.f4740i = r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            if (r8 == 0) goto L_0x008f
            r8.g(r6)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            lf.C r6 = r8.e()     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r10.f4740i = r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
        L_0x008f:
            int r6 = r10.f     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r6 = r6 | r7
            r10.f = r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            goto L_0x0024
        L_0x0095:
            int r6 = r10.f     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r7 = 2
            r6 = r6 & r7
            if (r6 != r7) goto L_0x00ad
            lf.K r6 = r10.f4739h     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r6.getClass()     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            lf.m r8 = new lf.m     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r9 = 1
            r8.<init>(r9)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            java.util.List r9 = java.util.Collections.EMPTY_LIST     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r8.g = r9     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r8.j(r6)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
        L_0x00ad:
            lf.a r6 = lf.K.f4758i     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            rf.b r6 = r11.g(r6, r12)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            lf.K r6 = (lf.K) r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r10.f4739h = r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            if (r8 == 0) goto L_0x00c2
            r8.j(r6)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            lf.K r6 = r8.e()     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r10.f4739h = r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
        L_0x00c2:
            int r6 = r10.f     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r6 = r6 | r7
            r10.f = r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            goto L_0x0024
        L_0x00c9:
            int r6 = r10.f     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r6 = r6 & r1
            if (r6 != r1) goto L_0x00e0
            lf.L r6 = r10.g     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r6.getClass()     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            lf.m r8 = new lf.m     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r7 = 3
            r8.<init>(r7)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            rf.L r7 = rf.v.e     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r8.g = r7     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r8.k(r6)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
        L_0x00e0:
            lf.a r6 = lf.L.f4760i     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            rf.b r6 = r11.g(r6, r12)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            lf.L r6 = (lf.L) r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r10.g = r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            if (r8 == 0) goto L_0x00f5
            r8.k(r6)     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            lf.L r6 = r8.f()     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r10.g = r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
        L_0x00f5:
            int r6 = r10.f     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            r6 = r6 | r1
            r10.f = r6     // Catch:{ u -> 0x004d, IOException -> 0x004a }
            goto L_0x0024
        L_0x00fc:
            rf.u r12 = new rf.u     // Catch:{ all -> 0x0047 }
            java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x0047 }
            r12.<init>(r11)     // Catch:{ all -> 0x0047 }
            r12.d = r10     // Catch:{ all -> 0x0047 }
            throw r12     // Catch:{ all -> 0x0047 }
        L_0x0108:
            r11.d = r10     // Catch:{ all -> 0x0047 }
            throw r11     // Catch:{ all -> 0x0047 }
        L_0x010b:
            r12 = r4 & 8
            if (r12 != r5) goto L_0x0117
            java.util.List r12 = r10.f4741j
            java.util.List r12 = java.util.Collections.unmodifiableList(r12)
            r10.f4741j = r12
        L_0x0117:
            r2.i()     // Catch:{ IOException -> 0x011a, all -> 0x0121 }
        L_0x011a:
            rf.e r12 = r0.f()
            r10.e = r12
            goto L_0x0129
        L_0x0121:
            r11 = move-exception
            rf.e r12 = r0.f()
            r10.e = r12
            throw r11
        L_0x0129:
            r10.l()
            throw r11
        L_0x012d:
            r11 = r4 & 8
            if (r11 != r5) goto L_0x0139
            java.util.List r11 = r10.f4741j
            java.util.List r11 = java.util.Collections.unmodifiableList(r11)
            r10.f4741j = r11
        L_0x0139:
            r2.i()     // Catch:{ IOException -> 0x013c, all -> 0x0143 }
        L_0x013c:
            rf.e r11 = r0.f()
            r10.e = r11
            goto L_0x014b
        L_0x0143:
            r11 = move-exception
            rf.e r12 = r0.f()
            r10.e = r12
            throw r11
        L_0x014b:
            r10.l()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lf.E.<init>(rf.f, rf.h):void");
    }
}
