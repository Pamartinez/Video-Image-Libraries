package lf;

import B2.o;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import ge.W0;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1255e;
import rf.C1262l;
import rf.C1264n;

/* renamed from: lf.y  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1171y extends C1264n {

    /* renamed from: x  reason: collision with root package name */
    public static final C1171y f4886x;
    public static final C1148a y = new C1148a(9);
    public final C1255e e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4887h;

    /* renamed from: i  reason: collision with root package name */
    public int f4888i;

    /* renamed from: j  reason: collision with root package name */
    public Q f4889j;
    public int k;
    public List l;
    public Q m;
    public int n;

    /* renamed from: o  reason: collision with root package name */
    public List f4890o;

    /* renamed from: p  reason: collision with root package name */
    public List f4891p;
    public int q;
    public List r;
    public X s;
    public List t;
    public C1161n u;
    public byte v;
    public int w;

    static {
        C1171y yVar = new C1171y();
        f4886x = yVar;
        yVar.o();
    }

    public C1171y(C1170x xVar) {
        super(xVar);
        this.q = -1;
        this.v = -1;
        this.w = -1;
        this.e = xVar.d;
    }

    public final int a() {
        int i2;
        int i7 = this.w;
        if (i7 != -1) {
            return i7;
        }
        if ((this.f & 2) == 2) {
            i2 = o.b(1, this.f4887h);
        } else {
            i2 = 0;
        }
        if ((this.f & 4) == 4) {
            i2 += o.b(2, this.f4888i);
        }
        if ((this.f & 8) == 8) {
            i2 += o.d(3, this.f4889j);
        }
        for (int i8 = 0; i8 < this.l.size(); i8++) {
            i2 += o.d(4, (C1252b) this.l.get(i8));
        }
        if ((this.f & 32) == 32) {
            i2 += o.d(5, this.m);
        }
        for (int i10 = 0; i10 < this.r.size(); i10++) {
            i2 += o.d(6, (C1252b) this.r.get(i10));
        }
        if ((this.f & 16) == 16) {
            i2 += o.b(7, this.k);
        }
        if ((this.f & 64) == 64) {
            i2 += o.b(8, this.n);
        }
        if ((this.f & 1) == 1) {
            i2 += o.b(9, this.g);
        }
        for (int i11 = 0; i11 < this.f4890o.size(); i11++) {
            i2 += o.d(10, (C1252b) this.f4890o.get(i11));
        }
        int i12 = 0;
        for (int i13 = 0; i13 < this.f4891p.size(); i13++) {
            i12 += o.c(((Integer) this.f4891p.get(i13)).intValue());
        }
        int i14 = i2 + i12;
        if (!this.f4891p.isEmpty()) {
            i14 = i14 + 1 + o.c(i12);
        }
        this.q = i12;
        if ((this.f & 128) == 128) {
            i14 += o.d(30, this.s);
        }
        int i15 = 0;
        for (int i16 = 0; i16 < this.t.size(); i16++) {
            i15 += o.c(((Integer) this.t.get(i16)).intValue());
        }
        int size = (this.t.size() * 2) + i14 + i15;
        if ((this.f & 256) == 256) {
            size += o.d(32, this.u);
        }
        int size2 = this.e.size() + h() + size;
        this.w = size2;
        return size2;
    }

    public final C1262l b() {
        return C1170x.f();
    }

    public final C1262l c() {
        C1170x f5 = C1170x.f();
        f5.g(this);
        return f5;
    }

    public final void d(o oVar) {
        a();
        W0 w02 = new W0((C1264n) this);
        if ((this.f & 2) == 2) {
            oVar.m(1, this.f4887h);
        }
        if ((this.f & 4) == 4) {
            oVar.m(2, this.f4888i);
        }
        if ((this.f & 8) == 8) {
            oVar.o(3, this.f4889j);
        }
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            oVar.o(4, (C1252b) this.l.get(i2));
        }
        if ((this.f & 32) == 32) {
            oVar.o(5, this.m);
        }
        for (int i7 = 0; i7 < this.r.size(); i7++) {
            oVar.o(6, (C1252b) this.r.get(i7));
        }
        if ((this.f & 16) == 16) {
            oVar.m(7, this.k);
        }
        if ((this.f & 64) == 64) {
            oVar.m(8, this.n);
        }
        if ((this.f & 1) == 1) {
            oVar.m(9, this.g);
        }
        for (int i8 = 0; i8 < this.f4890o.size(); i8++) {
            oVar.o(10, (C1252b) this.f4890o.get(i8));
        }
        if (this.f4891p.size() > 0) {
            oVar.v(90);
            oVar.v(this.q);
        }
        for (int i10 = 0; i10 < this.f4891p.size(); i10++) {
            oVar.n(((Integer) this.f4891p.get(i10)).intValue());
        }
        if ((this.f & 128) == 128) {
            oVar.o(30, this.s);
        }
        for (int i11 = 0; i11 < this.t.size(); i11++) {
            oVar.m(31, ((Integer) this.t.get(i11)).intValue());
        }
        if ((this.f & 256) == 256) {
            oVar.o(32, this.u);
        }
        w02.A0(ErrorCodeConvertor.CLOUD_UID_VALIDATION, oVar);
        oVar.r(this.e);
    }

    public final C1252b getDefaultInstanceForType() {
        return f4886x;
    }

    public final boolean isInitialized() {
        byte b = this.v;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        int i2 = this.f;
        if ((i2 & 4) != 4) {
            this.v = 0;
            return false;
        } else if ((i2 & 8) != 8 || this.f4889j.isInitialized()) {
            for (int i7 = 0; i7 < this.l.size(); i7++) {
                if (!((W) this.l.get(i7)).isInitialized()) {
                    this.v = 0;
                    return false;
                }
            }
            if ((this.f & 32) != 32 || this.m.isInitialized()) {
                for (int i8 = 0; i8 < this.f4890o.size(); i8++) {
                    if (!((Q) this.f4890o.get(i8)).isInitialized()) {
                        this.v = 0;
                        return false;
                    }
                }
                for (int i10 = 0; i10 < this.r.size(); i10++) {
                    if (!((Z) this.r.get(i10)).isInitialized()) {
                        this.v = 0;
                        return false;
                    }
                }
                if ((this.f & 128) == 128 && !this.s.isInitialized()) {
                    this.v = 0;
                    return false;
                } else if ((this.f & 256) == 256 && !this.u.isInitialized()) {
                    this.v = 0;
                    return false;
                } else if (!g()) {
                    this.v = 0;
                    return false;
                } else {
                    this.v = 1;
                    return true;
                }
            } else {
                this.v = 0;
                return false;
            }
        } else {
            this.v = 0;
            return false;
        }
    }

    public final void o() {
        this.g = 6;
        this.f4887h = 6;
        this.f4888i = 0;
        Q q10 = Q.w;
        this.f4889j = q10;
        this.k = 0;
        List list = Collections.EMPTY_LIST;
        this.l = list;
        this.m = q10;
        this.n = 0;
        this.f4890o = list;
        this.f4891p = list;
        this.r = list;
        this.s = X.f4794j;
        this.t = list;
        this.u = C1161n.f4862h;
    }

    public C1171y() {
        this.q = -1;
        this.v = -1;
        this.w = -1;
        this.e = C1255e.d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: lf.P} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: lf.P} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v18, resolved type: lf.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v27, resolved type: lf.m} */
    /* JADX WARNING: type inference failed for: r11v0 */
    /* JADX WARNING: type inference failed for: r11v29 */
    /* JADX WARNING: type inference failed for: r11v30 */
    /* JADX WARNING: type inference failed for: r11v31 */
    /* JADX WARNING: type inference failed for: r11v32 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C1171y(rf.C1256f r14, rf.C1258h r15) {
        /*
            r13 = this;
            r13.<init>()
            r0 = -1
            r13.q = r0
            r13.v = r0
            r13.w = r0
            r13.o()
            rf.d r0 = new rf.d
            r0.<init>()
            r1 = 1
            B2.o r2 = B2.o.j(r0, r1)
            r3 = 0
            r4 = r3
        L_0x0019:
            r5 = 1024(0x400, float:1.435E-42)
            r6 = 32
            r7 = 512(0x200, float:7.175E-43)
            r8 = 4096(0x1000, float:5.74E-42)
            r9 = 256(0x100, float:3.59E-43)
            if (r3 != 0) goto L_0x0286
            int r10 = r14.n()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r11 = 0
            switch(r10) {
                case 0: goto L_0x0033;
                case 8: goto L_0x0217;
                case 16: goto L_0x0209;
                case 26: goto L_0x01dd;
                case 34: goto L_0x01c3;
                case 42: goto L_0x0199;
                case 50: goto L_0x017f;
                case 56: goto L_0x0171;
                case 64: goto L_0x0163;
                case 72: goto L_0x0156;
                case 82: goto L_0x013c;
                case 88: goto L_0x0120;
                case 90: goto L_0x00ec;
                case 242: goto L_0x00c0;
                case 248: goto L_0x00a4;
                case 250: goto L_0x0070;
                case 258: goto L_0x003e;
                default: goto L_0x002d;
            }     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
        L_0x002d:
            boolean r5 = r13.m(r14, r2, r15, r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            if (r5 != 0) goto L_0x0019
        L_0x0033:
            r3 = r1
            goto L_0x0019
        L_0x0035:
            r14 = move-exception
            goto L_0x0234
        L_0x0038:
            r14 = move-exception
            goto L_0x0225
        L_0x003b:
            r14 = move-exception
            goto L_0x0231
        L_0x003e:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10 = r10 & r9
            if (r10 != r9) goto L_0x0055
            lf.n r10 = r13.u     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.getClass()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.m r11 = new lf.m     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r12 = 0
            r11.<init>(r12)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            java.util.List r12 = java.util.Collections.EMPTY_LIST     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r11.g = r12     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r11.h(r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
        L_0x0055:
            lf.a r10 = lf.C1161n.f4863i     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            rf.b r10 = r14.g(r10, r15)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.n r10 = (lf.C1161n) r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.u = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            if (r11 == 0) goto L_0x006a
            r11.h(r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.n r10 = r11.d()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.u = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
        L_0x006a:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10 = r10 | r9
            r13.f = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x0070:
            int r10 = r14.k()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            int r10 = r14.d(r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r11 = r4 & 4096(0x1000, float:5.74E-42)
            if (r11 == r8) goto L_0x008b
            int r11 = r14.b()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            if (r11 <= 0) goto L_0x008b
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r11.<init>()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.t = r11     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r4 = r4 | 4096(0x1000, float:5.74E-42)
        L_0x008b:
            int r11 = r14.b()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            if (r11 <= 0) goto L_0x009f
            java.util.List r11 = r13.t     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            int r12 = r14.k()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r11.add(r12)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x008b
        L_0x009f:
            r14.c(r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x00a4:
            r10 = r4 & 4096(0x1000, float:5.74E-42)
            if (r10 == r8) goto L_0x00b1
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.<init>()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.t = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r4 = r4 | 4096(0x1000, float:5.74E-42)
        L_0x00b1:
            java.util.List r10 = r13.t     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            int r11 = r14.k()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.add(r11)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x00c0:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r12 = 128(0x80, float:1.794E-43)
            r10 = r10 & r12
            if (r10 != r12) goto L_0x00d0
            lf.X r10 = r13.s     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.getClass()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.f r11 = lf.X.g(r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
        L_0x00d0:
            lf.a r10 = lf.X.k     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            rf.b r10 = r14.g(r10, r15)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.X r10 = (lf.X) r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.s = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            if (r11 == 0) goto L_0x00e5
            r11.k(r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.X r10 = r11.f()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.s = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
        L_0x00e5:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10 = r10 | r12
            r13.f = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x00ec:
            int r10 = r14.k()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            int r10 = r14.d(r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r11 = r4 & 512(0x200, float:7.175E-43)
            if (r11 == r7) goto L_0x0107
            int r11 = r14.b()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            if (r11 <= 0) goto L_0x0107
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r11.<init>()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.f4891p = r11     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r4 = r4 | 512(0x200, float:7.175E-43)
        L_0x0107:
            int r11 = r14.b()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            if (r11 <= 0) goto L_0x011b
            java.util.List r11 = r13.f4891p     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            int r12 = r14.k()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r11.add(r12)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0107
        L_0x011b:
            r14.c(r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x0120:
            r10 = r4 & 512(0x200, float:7.175E-43)
            if (r10 == r7) goto L_0x012d
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.<init>()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.f4891p = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r4 = r4 | 512(0x200, float:7.175E-43)
        L_0x012d:
            java.util.List r10 = r13.f4891p     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            int r11 = r14.k()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.add(r11)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x013c:
            r10 = r4 & 256(0x100, float:3.59E-43)
            if (r10 == r9) goto L_0x0149
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.<init>()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.f4890o = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r4 = r4 | 256(0x100, float:3.59E-43)
        L_0x0149:
            java.util.List r10 = r13.f4890o     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.a r11 = lf.Q.f4770x     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            rf.b r11 = r14.g(r11, r15)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.add(r11)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x0156:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10 = r10 | r1
            r13.f = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            int r10 = r14.k()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.g = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x0163:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10 = r10 | 64
            r13.f = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            int r10 = r14.k()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.n = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x0171:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10 = r10 | 16
            r13.f = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            int r10 = r14.k()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.k = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x017f:
            r10 = r4 & 1024(0x400, float:1.435E-42)
            if (r10 == r5) goto L_0x018c
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.<init>()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.r = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r4 = r4 | 1024(0x400, float:1.435E-42)
        L_0x018c:
            java.util.List r10 = r13.r     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.a r11 = lf.Z.f4801p     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            rf.b r11 = r14.g(r11, r15)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.add(r11)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x0199:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10 = r10 & r6
            if (r10 != r6) goto L_0x01a7
            lf.Q r10 = r13.m     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.getClass()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.P r11 = lf.Q.p(r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
        L_0x01a7:
            lf.a r10 = lf.Q.f4770x     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            rf.b r10 = r14.g(r10, r15)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.Q r10 = (lf.Q) r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.m = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            if (r11 == 0) goto L_0x01bc
            r11.g(r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.Q r10 = r11.e()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.m = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
        L_0x01bc:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10 = r10 | r6
            r13.f = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x01c3:
            r10 = r4 & 32
            if (r10 == r6) goto L_0x01d0
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.<init>()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.l = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r4 = r4 | 32
        L_0x01d0:
            java.util.List r10 = r13.l     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.a r11 = lf.W.q     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            rf.b r11 = r14.g(r11, r15)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.add(r11)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x01dd:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r12 = 8
            r10 = r10 & r12
            if (r10 != r12) goto L_0x01ed
            lf.Q r10 = r13.f4889j     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10.getClass()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.P r11 = lf.Q.p(r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
        L_0x01ed:
            lf.a r10 = lf.Q.f4770x     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            rf.b r10 = r14.g(r10, r15)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.Q r10 = (lf.Q) r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.f4889j = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            if (r11 == 0) goto L_0x0202
            r11.g(r10)     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            lf.Q r10 = r11.e()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.f4889j = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
        L_0x0202:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10 = r10 | r12
            r13.f = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x0209:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10 = r10 | 4
            r13.f = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            int r10 = r14.k()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.f4888i = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x0217:
            int r10 = r13.f     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r10 = r10 | 2
            r13.f = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            int r10 = r14.k()     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            r13.f4887h = r10     // Catch:{ u -> 0x003b, IOException -> 0x0038 }
            goto L_0x0019
        L_0x0225:
            rf.u r15 = new rf.u     // Catch:{ all -> 0x0035 }
            java.lang.String r14 = r14.getMessage()     // Catch:{ all -> 0x0035 }
            r15.<init>(r14)     // Catch:{ all -> 0x0035 }
            r15.d = r13     // Catch:{ all -> 0x0035 }
            throw r15     // Catch:{ all -> 0x0035 }
        L_0x0231:
            r14.d = r13     // Catch:{ all -> 0x0035 }
            throw r14     // Catch:{ all -> 0x0035 }
        L_0x0234:
            r15 = r4 & 32
            if (r15 != r6) goto L_0x0240
            java.util.List r15 = r13.l
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.l = r15
        L_0x0240:
            r15 = r4 & 1024(0x400, float:1.435E-42)
            if (r15 != r5) goto L_0x024c
            java.util.List r15 = r13.r
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.r = r15
        L_0x024c:
            r15 = r4 & 256(0x100, float:3.59E-43)
            if (r15 != r9) goto L_0x0258
            java.util.List r15 = r13.f4890o
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.f4890o = r15
        L_0x0258:
            r15 = r4 & 512(0x200, float:7.175E-43)
            if (r15 != r7) goto L_0x0264
            java.util.List r15 = r13.f4891p
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.f4891p = r15
        L_0x0264:
            r15 = r4 & 4096(0x1000, float:5.74E-42)
            if (r15 != r8) goto L_0x0270
            java.util.List r15 = r13.t
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.t = r15
        L_0x0270:
            r2.i()     // Catch:{ IOException -> 0x0273, all -> 0x027a }
        L_0x0273:
            rf.e r15 = r0.f()
            r13.e = r15
            goto L_0x0282
        L_0x027a:
            r14 = move-exception
            rf.e r15 = r0.f()
            r13.e = r15
            throw r14
        L_0x0282:
            r13.l()
            throw r14
        L_0x0286:
            r14 = r4 & 32
            if (r14 != r6) goto L_0x0292
            java.util.List r14 = r13.l
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.l = r14
        L_0x0292:
            r14 = r4 & 1024(0x400, float:1.435E-42)
            if (r14 != r5) goto L_0x029e
            java.util.List r14 = r13.r
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.r = r14
        L_0x029e:
            r14 = r4 & 256(0x100, float:3.59E-43)
            if (r14 != r9) goto L_0x02aa
            java.util.List r14 = r13.f4890o
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.f4890o = r14
        L_0x02aa:
            r14 = r4 & 512(0x200, float:7.175E-43)
            if (r14 != r7) goto L_0x02b6
            java.util.List r14 = r13.f4891p
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.f4891p = r14
        L_0x02b6:
            r14 = r4 & 4096(0x1000, float:5.74E-42)
            if (r14 != r8) goto L_0x02c2
            java.util.List r14 = r13.t
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.t = r14
        L_0x02c2:
            r2.i()     // Catch:{ IOException -> 0x02c5, all -> 0x02cc }
        L_0x02c5:
            rf.e r14 = r0.f()
            r13.e = r14
            goto L_0x02d4
        L_0x02cc:
            r14 = move-exception
            rf.e r15 = r0.f()
            r13.e = r15
            throw r14
        L_0x02d4:
            r13.l()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lf.C1171y.<init>(rf.f, rf.h):void");
    }
}
