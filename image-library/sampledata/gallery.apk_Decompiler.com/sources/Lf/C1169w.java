package lf;

import B2.o;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1255e;
import rf.C1262l;
import rf.C1267q;

/* renamed from: lf.w  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1169w extends C1267q {

    /* renamed from: o  reason: collision with root package name */
    public static final C1169w f4876o;

    /* renamed from: p  reason: collision with root package name */
    public static final C1148a f4877p = new C1148a(8);
    public final C1255e d;
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public C1168v f4878h;

    /* renamed from: i  reason: collision with root package name */
    public Q f4879i;

    /* renamed from: j  reason: collision with root package name */
    public int f4880j;
    public List k;
    public List l;
    public byte m;
    public int n;

    static {
        C1169w wVar = new C1169w();
        f4876o = wVar;
        wVar.f = 0;
        wVar.g = 0;
        wVar.f4878h = C1168v.TRUE;
        wVar.f4879i = Q.w;
        wVar.f4880j = 0;
        List list = Collections.EMPTY_LIST;
        wVar.k = list;
        wVar.l = list;
    }

    public C1169w() {
        this.m = -1;
        this.n = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2;
        int i7 = this.n;
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
            i2 += o.a(3, this.f4878h.a());
        }
        if ((this.e & 8) == 8) {
            i2 += o.d(4, this.f4879i);
        }
        if ((this.e & 16) == 16) {
            i2 += o.b(5, this.f4880j);
        }
        for (int i8 = 0; i8 < this.k.size(); i8++) {
            i2 += o.d(6, (C1252b) this.k.get(i8));
        }
        for (int i10 = 0; i10 < this.l.size(); i10++) {
            i2 += o.d(7, (C1252b) this.l.get(i10));
        }
        int size = this.d.size() + i2;
        this.n = size;
        return size;
    }

    public final C1262l b() {
        return C1167u.e();
    }

    public final C1262l c() {
        C1167u e7 = C1167u.e();
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
            oVar.l(3, this.f4878h.a());
        }
        if ((this.e & 8) == 8) {
            oVar.o(4, this.f4879i);
        }
        if ((this.e & 16) == 16) {
            oVar.m(5, this.f4880j);
        }
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            oVar.o(6, (C1252b) this.k.get(i2));
        }
        for (int i7 = 0; i7 < this.l.size(); i7++) {
            oVar.o(7, (C1252b) this.l.get(i7));
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        byte b = this.m;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.e & 8) != 8 || this.f4879i.isInitialized()) {
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                if (!((C1169w) this.k.get(i2)).isInitialized()) {
                    this.m = 0;
                    return false;
                }
            }
            for (int i7 = 0; i7 < this.l.size(); i7++) {
                if (!((C1169w) this.l.get(i7)).isInitialized()) {
                    this.m = 0;
                    return false;
                }
            }
            this.m = 1;
            return true;
        }
        this.m = 0;
        return false;
    }

    public C1169w(C1167u uVar) {
        this.m = -1;
        this.n = -1;
        this.d = uVar.d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: lf.v} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: lf.P} */
    /* JADX WARNING: type inference failed for: r12v0 */
    /* JADX WARNING: type inference failed for: r12v7 */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C1169w(rf.C1256f r14, rf.C1258h r15) {
        /*
            r13 = this;
            r13.<init>()
            r0 = -1
            r13.m = r0
            r13.n = r0
            r0 = 0
            r13.f = r0
            r13.g = r0
            lf.v r1 = lf.C1168v.TRUE
            r13.f4878h = r1
            lf.Q r1 = lf.Q.w
            r13.f4879i = r1
            r13.f4880j = r0
            java.util.List r1 = java.util.Collections.EMPTY_LIST
            r13.k = r1
            r13.l = r1
            rf.d r1 = new rf.d
            r1.<init>()
            r2 = 1
            B2.o r3 = B2.o.j(r1, r2)
            r4 = r0
        L_0x0028:
            r5 = 32
            r6 = 64
            if (r0 != 0) goto L_0x0144
            int r7 = r14.n()     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            if (r7 == 0) goto L_0x005a
            r8 = 8
            if (r7 == r8) goto L_0x00fd
            r9 = 2
            r10 = 16
            if (r7 == r10) goto L_0x00f0
            r11 = 24
            r12 = 0
            if (r7 == r11) goto L_0x00c9
            r9 = 34
            if (r7 == r9) goto L_0x009f
            r8 = 40
            if (r7 == r8) goto L_0x0093
            r8 = 50
            lf.a r9 = f4877p
            if (r7 == r8) goto L_0x007c
            r8 = 58
            if (r7 == r8) goto L_0x0065
            boolean r5 = r14.q(r7, r3)     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            if (r5 != 0) goto L_0x0028
        L_0x005a:
            r0 = r2
            goto L_0x0028
        L_0x005c:
            r14 = move-exception
            goto L_0x0119
        L_0x005f:
            r14 = move-exception
            goto L_0x010a
        L_0x0062:
            r14 = move-exception
            goto L_0x0116
        L_0x0065:
            r7 = r4 & 64
            if (r7 == r6) goto L_0x0072
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r7.<init>()     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r13.l = r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r4 = r4 | 64
        L_0x0072:
            java.util.List r7 = r13.l     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            rf.b r8 = r14.g(r9, r15)     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r7.add(r8)     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            goto L_0x0028
        L_0x007c:
            r7 = r4 & 32
            if (r7 == r5) goto L_0x0089
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r7.<init>()     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r13.k = r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r4 = r4 | 32
        L_0x0089:
            java.util.List r7 = r13.k     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            rf.b r8 = r14.g(r9, r15)     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r7.add(r8)     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            goto L_0x0028
        L_0x0093:
            int r7 = r13.e     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r7 = r7 | r10
            r13.e = r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            int r7 = r14.k()     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r13.f4880j = r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            goto L_0x0028
        L_0x009f:
            int r7 = r13.e     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r7 = r7 & r8
            if (r7 != r8) goto L_0x00ad
            lf.Q r7 = r13.f4879i     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r7.getClass()     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            lf.P r12 = lf.Q.p(r7)     // Catch:{ u -> 0x0062, IOException -> 0x005f }
        L_0x00ad:
            lf.a r7 = lf.Q.f4770x     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            rf.b r7 = r14.g(r7, r15)     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            lf.Q r7 = (lf.Q) r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r13.f4879i = r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            if (r12 == 0) goto L_0x00c2
            r12.g(r7)     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            lf.Q r7 = r12.e()     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r13.f4879i = r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
        L_0x00c2:
            int r7 = r13.e     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r7 = r7 | r8
            r13.e = r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            goto L_0x0028
        L_0x00c9:
            int r8 = r14.k()     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            if (r8 == 0) goto L_0x00da
            if (r8 == r2) goto L_0x00d7
            if (r8 == r9) goto L_0x00d4
            goto L_0x00dc
        L_0x00d4:
            lf.v r12 = lf.C1168v.NULL     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            goto L_0x00dc
        L_0x00d7:
            lf.v r12 = lf.C1168v.FALSE     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            goto L_0x00dc
        L_0x00da:
            lf.v r12 = lf.C1168v.TRUE     // Catch:{ u -> 0x0062, IOException -> 0x005f }
        L_0x00dc:
            if (r12 != 0) goto L_0x00e6
            r3.v(r7)     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r3.v(r8)     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            goto L_0x0028
        L_0x00e6:
            int r7 = r13.e     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r7 = r7 | 4
            r13.e = r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r13.f4878h = r12     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            goto L_0x0028
        L_0x00f0:
            int r7 = r13.e     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r7 = r7 | r9
            r13.e = r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            int r7 = r14.k()     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r13.g = r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            goto L_0x0028
        L_0x00fd:
            int r7 = r13.e     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r7 = r7 | r2
            r13.e = r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            int r7 = r14.k()     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            r13.f = r7     // Catch:{ u -> 0x0062, IOException -> 0x005f }
            goto L_0x0028
        L_0x010a:
            rf.u r15 = new rf.u     // Catch:{ all -> 0x005c }
            java.lang.String r14 = r14.getMessage()     // Catch:{ all -> 0x005c }
            r15.<init>(r14)     // Catch:{ all -> 0x005c }
            r15.d = r13     // Catch:{ all -> 0x005c }
            throw r15     // Catch:{ all -> 0x005c }
        L_0x0116:
            r14.d = r13     // Catch:{ all -> 0x005c }
            throw r14     // Catch:{ all -> 0x005c }
        L_0x0119:
            r15 = r4 & 32
            if (r15 != r5) goto L_0x0125
            java.util.List r15 = r13.k
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.k = r15
        L_0x0125:
            r15 = r4 & 64
            if (r15 != r6) goto L_0x0131
            java.util.List r15 = r13.l
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.l = r15
        L_0x0131:
            r3.i()     // Catch:{ IOException -> 0x0134, all -> 0x013b }
        L_0x0134:
            rf.e r15 = r1.f()
            r13.d = r15
            goto L_0x0143
        L_0x013b:
            r14 = move-exception
            rf.e r15 = r1.f()
            r13.d = r15
            throw r14
        L_0x0143:
            throw r14
        L_0x0144:
            r14 = r4 & 32
            if (r14 != r5) goto L_0x0150
            java.util.List r14 = r13.k
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.k = r14
        L_0x0150:
            r14 = r4 & 64
            if (r14 != r6) goto L_0x015c
            java.util.List r14 = r13.l
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.l = r14
        L_0x015c:
            r3.i()     // Catch:{ IOException -> 0x015f, all -> 0x0166 }
        L_0x015f:
            rf.e r14 = r1.f()
            r13.d = r14
            return
        L_0x0166:
            r14 = move-exception
            rf.e r15 = r1.f()
            r13.d = r15
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: lf.C1169w.<init>(rf.f, rf.h):void");
    }
}
