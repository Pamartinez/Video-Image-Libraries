package lf;

import B2.o;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1255e;
import rf.C1262l;
import rf.C1267q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends C1267q {
    public static final r l;
    public static final C1148a m = new C1148a(6);
    public final C1255e d;
    public int e;
    public C1163p f;
    public List g;

    /* renamed from: h  reason: collision with root package name */
    public C1169w f4866h;

    /* renamed from: i  reason: collision with root package name */
    public C1164q f4867i;

    /* renamed from: j  reason: collision with root package name */
    public byte f4868j;
    public int k;

    static {
        r rVar = new r();
        l = rVar;
        rVar.f = C1163p.RETURNS_CONSTANT;
        rVar.g = Collections.EMPTY_LIST;
        rVar.f4866h = C1169w.f4876o;
        rVar.f4867i = C1164q.AT_MOST_ONCE;
    }

    public r() {
        this.f4868j = -1;
        this.k = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2;
        int i7 = this.k;
        if (i7 != -1) {
            return i7;
        }
        if ((this.e & 1) == 1) {
            i2 = o.a(1, this.f.a());
        } else {
            i2 = 0;
        }
        for (int i8 = 0; i8 < this.g.size(); i8++) {
            i2 += o.d(2, (C1252b) this.g.get(i8));
        }
        if ((this.e & 2) == 2) {
            i2 += o.d(3, this.f4866h);
        }
        if ((this.e & 4) == 4) {
            i2 += o.a(4, this.f4867i.a());
        }
        int size = this.d.size() + i2;
        this.k = size;
        return size;
    }

    public final C1262l b() {
        return C1162o.e();
    }

    public final C1262l c() {
        C1162o e7 = C1162o.e();
        e7.f(this);
        return e7;
    }

    public final void d(o oVar) {
        a();
        if ((this.e & 1) == 1) {
            oVar.l(1, this.f.a());
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            oVar.o(2, (C1252b) this.g.get(i2));
        }
        if ((this.e & 2) == 2) {
            oVar.o(3, this.f4866h);
        }
        if ((this.e & 4) == 4) {
            oVar.l(4, this.f4867i.a());
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        byte b = this.f4868j;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            if (!((C1169w) this.g.get(i2)).isInitialized()) {
                this.f4868j = 0;
                return false;
            }
        }
        if ((this.e & 2) != 2 || this.f4866h.isInitialized()) {
            this.f4868j = 1;
            return true;
        }
        this.f4868j = 0;
        return false;
    }

    public r(C1162o oVar) {
        this.f4868j = -1;
        this.k = -1;
        this.d = oVar.d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: lf.p} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: lf.u} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: lf.q} */
    /* JADX WARNING: type inference failed for: r8v0 */
    /* JADX WARNING: type inference failed for: r8v11 */
    /* JADX WARNING: type inference failed for: r8v12 */
    /* JADX WARNING: type inference failed for: r8v13 */
    /* JADX WARNING: type inference failed for: r8v14 */
    /* JADX WARNING: type inference failed for: r8v15 */
    /* JADX WARNING: type inference failed for: r8v16 */
    /* JADX WARNING: type inference failed for: r8v17 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public r(rf.C1256f r10, rf.C1258h r11) {
        /*
            r9 = this;
            r9.<init>()
            r0 = -1
            r9.f4868j = r0
            r9.k = r0
            lf.p r0 = lf.C1163p.RETURNS_CONSTANT
            r9.f = r0
            java.util.List r0 = java.util.Collections.EMPTY_LIST
            r9.g = r0
            lf.w r0 = lf.C1169w.f4876o
            r9.f4866h = r0
            lf.q r0 = lf.C1164q.AT_MOST_ONCE
            r9.f4867i = r0
            rf.d r0 = new rf.d
            r0.<init>()
            r1 = 1
            B2.o r2 = B2.o.j(r0, r1)
            r3 = 0
            r4 = r3
        L_0x0024:
            r5 = 2
            if (r3 != 0) goto L_0x010d
            int r6 = r10.n()     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            if (r6 == 0) goto L_0x0044
            r7 = 8
            r8 = 0
            if (r6 == r7) goto L_0x00b9
            r7 = 18
            if (r6 == r7) goto L_0x00a0
            r7 = 26
            if (r6 == r7) goto L_0x0074
            r7 = 32
            if (r6 == r7) goto L_0x004f
            boolean r5 = r10.q(r6, r2)     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            if (r5 != 0) goto L_0x0024
        L_0x0044:
            r3 = r1
            goto L_0x0024
        L_0x0046:
            r10 = move-exception
            goto L_0x00ee
        L_0x0049:
            r10 = move-exception
            goto L_0x00df
        L_0x004c:
            r10 = move-exception
            goto L_0x00eb
        L_0x004f:
            int r7 = r10.k()     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            if (r7 == 0) goto L_0x0060
            if (r7 == r1) goto L_0x005d
            if (r7 == r5) goto L_0x005a
            goto L_0x0062
        L_0x005a:
            lf.q r8 = lf.C1164q.AT_LEAST_ONCE     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            goto L_0x0062
        L_0x005d:
            lf.q r8 = lf.C1164q.EXACTLY_ONCE     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            goto L_0x0062
        L_0x0060:
            lf.q r8 = lf.C1164q.AT_MOST_ONCE     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
        L_0x0062:
            if (r8 != 0) goto L_0x006b
            r2.v(r6)     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r2.v(r7)     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            goto L_0x0024
        L_0x006b:
            int r6 = r9.e     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r6 = r6 | 4
            r9.e = r6     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r9.f4867i = r8     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            goto L_0x0024
        L_0x0074:
            int r6 = r9.e     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r6 = r6 & r5
            if (r6 != r5) goto L_0x0085
            lf.w r6 = r9.f4866h     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r6.getClass()     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            lf.u r8 = lf.C1167u.e()     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r8.f(r6)     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
        L_0x0085:
            lf.a r6 = lf.C1169w.f4877p     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            rf.b r6 = r10.g(r6, r11)     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            lf.w r6 = (lf.C1169w) r6     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r9.f4866h = r6     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            if (r8 == 0) goto L_0x009a
            r8.f(r6)     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            lf.w r6 = r8.d()     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r9.f4866h = r6     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
        L_0x009a:
            int r6 = r9.e     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r6 = r6 | r5
            r9.e = r6     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            goto L_0x0024
        L_0x00a0:
            r6 = r4 & 2
            if (r6 == r5) goto L_0x00ac
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r6.<init>()     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r9.g = r6     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r4 = r5
        L_0x00ac:
            java.util.List r6 = r9.g     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            lf.a r7 = lf.C1169w.f4877p     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            rf.b r7 = r10.g(r7, r11)     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r6.add(r7)     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            goto L_0x0024
        L_0x00b9:
            int r7 = r10.k()     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            if (r7 == 0) goto L_0x00ca
            if (r7 == r1) goto L_0x00c7
            if (r7 == r5) goto L_0x00c4
            goto L_0x00cc
        L_0x00c4:
            lf.p r8 = lf.C1163p.RETURNS_NOT_NULL     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            goto L_0x00cc
        L_0x00c7:
            lf.p r8 = lf.C1163p.CALLS     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            goto L_0x00cc
        L_0x00ca:
            lf.p r8 = lf.C1163p.RETURNS_CONSTANT     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
        L_0x00cc:
            if (r8 != 0) goto L_0x00d6
            r2.v(r6)     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r2.v(r7)     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            goto L_0x0024
        L_0x00d6:
            int r6 = r9.e     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r6 = r6 | r1
            r9.e = r6     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            r9.f = r8     // Catch:{ u -> 0x004c, IOException -> 0x0049 }
            goto L_0x0024
        L_0x00df:
            rf.u r11 = new rf.u     // Catch:{ all -> 0x0046 }
            java.lang.String r10 = r10.getMessage()     // Catch:{ all -> 0x0046 }
            r11.<init>(r10)     // Catch:{ all -> 0x0046 }
            r11.d = r9     // Catch:{ all -> 0x0046 }
            throw r11     // Catch:{ all -> 0x0046 }
        L_0x00eb:
            r10.d = r9     // Catch:{ all -> 0x0046 }
            throw r10     // Catch:{ all -> 0x0046 }
        L_0x00ee:
            r11 = r4 & 2
            if (r11 != r5) goto L_0x00fa
            java.util.List r11 = r9.g
            java.util.List r11 = java.util.Collections.unmodifiableList(r11)
            r9.g = r11
        L_0x00fa:
            r2.i()     // Catch:{ IOException -> 0x00fd, all -> 0x0104 }
        L_0x00fd:
            rf.e r11 = r0.f()
            r9.d = r11
            goto L_0x010c
        L_0x0104:
            r10 = move-exception
            rf.e r11 = r0.f()
            r9.d = r11
            throw r10
        L_0x010c:
            throw r10
        L_0x010d:
            r10 = r4 & 2
            if (r10 != r5) goto L_0x0119
            java.util.List r10 = r9.g
            java.util.List r10 = java.util.Collections.unmodifiableList(r10)
            r9.g = r10
        L_0x0119:
            r2.i()     // Catch:{ IOException -> 0x011c, all -> 0x0123 }
        L_0x011c:
            rf.e r10 = r0.f()
            r9.d = r10
            return
        L_0x0123:
            r10 = move-exception
            rf.e r11 = r0.f()
            r9.d = r11
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: lf.r.<init>(rf.f, rf.h):void");
    }
}
