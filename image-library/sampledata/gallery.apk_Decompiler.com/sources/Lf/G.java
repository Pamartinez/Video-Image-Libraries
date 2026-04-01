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

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G extends C1264n {

    /* renamed from: x  reason: collision with root package name */
    public static final G f4747x;
    public static final C1148a y = new C1148a(12);
    public final C1255e e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4748h;

    /* renamed from: i  reason: collision with root package name */
    public int f4749i;

    /* renamed from: j  reason: collision with root package name */
    public Q f4750j;
    public int k;
    public List l;
    public Q m;
    public int n;

    /* renamed from: o  reason: collision with root package name */
    public List f4751o;

    /* renamed from: p  reason: collision with root package name */
    public List f4752p;
    public int q;
    public Z r;
    public int s;
    public int t;
    public List u;
    public byte v;
    public int w;

    static {
        G g3 = new G();
        f4747x = g3;
        g3.o();
    }

    public G(F f5) {
        super(f5);
        this.q = -1;
        this.v = -1;
        this.w = -1;
        this.e = f5.d;
    }

    public final int a() {
        int i2;
        int i7 = this.w;
        if (i7 != -1) {
            return i7;
        }
        if ((this.f & 2) == 2) {
            i2 = o.b(1, this.f4748h);
        } else {
            i2 = 0;
        }
        if ((this.f & 4) == 4) {
            i2 += o.b(2, this.f4749i);
        }
        if ((this.f & 8) == 8) {
            i2 += o.d(3, this.f4750j);
        }
        for (int i8 = 0; i8 < this.l.size(); i8++) {
            i2 += o.d(4, (C1252b) this.l.get(i8));
        }
        if ((this.f & 32) == 32) {
            i2 += o.d(5, this.m);
        }
        if ((this.f & 128) == 128) {
            i2 += o.d(6, this.r);
        }
        if ((this.f & 256) == 256) {
            i2 += o.b(7, this.s);
        }
        if ((this.f & 512) == 512) {
            i2 += o.b(8, this.t);
        }
        if ((this.f & 16) == 16) {
            i2 += o.b(9, this.k);
        }
        if ((this.f & 64) == 64) {
            i2 += o.b(10, this.n);
        }
        if ((this.f & 1) == 1) {
            i2 += o.b(11, this.g);
        }
        for (int i10 = 0; i10 < this.f4751o.size(); i10++) {
            i2 += o.d(12, (C1252b) this.f4751o.get(i10));
        }
        int i11 = 0;
        for (int i12 = 0; i12 < this.f4752p.size(); i12++) {
            i11 += o.c(((Integer) this.f4752p.get(i12)).intValue());
        }
        int i13 = i2 + i11;
        if (!this.f4752p.isEmpty()) {
            i13 = i13 + 1 + o.c(i11);
        }
        this.q = i11;
        int i14 = 0;
        for (int i15 = 0; i15 < this.u.size(); i15++) {
            i14 += o.c(((Integer) this.u.get(i15)).intValue());
        }
        int size = this.e.size() + h() + (this.u.size() * 2) + i13 + i14;
        this.w = size;
        return size;
    }

    public final C1262l b() {
        return F.f();
    }

    public final C1262l c() {
        F f5 = F.f();
        f5.g(this);
        return f5;
    }

    public final void d(o oVar) {
        a();
        W0 w02 = new W0((C1264n) this);
        if ((this.f & 2) == 2) {
            oVar.m(1, this.f4748h);
        }
        if ((this.f & 4) == 4) {
            oVar.m(2, this.f4749i);
        }
        if ((this.f & 8) == 8) {
            oVar.o(3, this.f4750j);
        }
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            oVar.o(4, (C1252b) this.l.get(i2));
        }
        if ((this.f & 32) == 32) {
            oVar.o(5, this.m);
        }
        if ((this.f & 128) == 128) {
            oVar.o(6, this.r);
        }
        if ((this.f & 256) == 256) {
            oVar.m(7, this.s);
        }
        if ((this.f & 512) == 512) {
            oVar.m(8, this.t);
        }
        if ((this.f & 16) == 16) {
            oVar.m(9, this.k);
        }
        if ((this.f & 64) == 64) {
            oVar.m(10, this.n);
        }
        if ((this.f & 1) == 1) {
            oVar.m(11, this.g);
        }
        for (int i7 = 0; i7 < this.f4751o.size(); i7++) {
            oVar.o(12, (C1252b) this.f4751o.get(i7));
        }
        if (this.f4752p.size() > 0) {
            oVar.v(106);
            oVar.v(this.q);
        }
        for (int i8 = 0; i8 < this.f4752p.size(); i8++) {
            oVar.n(((Integer) this.f4752p.get(i8)).intValue());
        }
        for (int i10 = 0; i10 < this.u.size(); i10++) {
            oVar.m(31, ((Integer) this.u.get(i10)).intValue());
        }
        w02.A0(ErrorCodeConvertor.CLOUD_UID_VALIDATION, oVar);
        oVar.r(this.e);
    }

    public final C1252b getDefaultInstanceForType() {
        return f4747x;
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
        } else if ((i2 & 8) != 8 || this.f4750j.isInitialized()) {
            for (int i7 = 0; i7 < this.l.size(); i7++) {
                if (!((W) this.l.get(i7)).isInitialized()) {
                    this.v = 0;
                    return false;
                }
            }
            if ((this.f & 32) != 32 || this.m.isInitialized()) {
                for (int i8 = 0; i8 < this.f4751o.size(); i8++) {
                    if (!((Q) this.f4751o.get(i8)).isInitialized()) {
                        this.v = 0;
                        return false;
                    }
                }
                if ((this.f & 128) == 128 && !this.r.isInitialized()) {
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
        this.g = 518;
        this.f4748h = 2054;
        this.f4749i = 0;
        Q q10 = Q.w;
        this.f4750j = q10;
        this.k = 0;
        List list = Collections.EMPTY_LIST;
        this.l = list;
        this.m = q10;
        this.n = 0;
        this.f4751o = list;
        this.f4752p = list;
        this.r = Z.f4800o;
        this.s = 0;
        this.t = 0;
        this.u = list;
    }

    public G() {
        this.q = -1;
        this.v = -1;
        this.w = -1;
        this.e = C1255e.d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: lf.P} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: lf.P} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: lf.Y} */
    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: type inference failed for: r10v8, types: [rf.m, lf.Y] */
    /* JADX WARNING: type inference failed for: r10v25 */
    /* JADX WARNING: type inference failed for: r10v26 */
    /* JADX WARNING: type inference failed for: r10v27 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public G(rf.C1256f r14, rf.C1258h r15) {
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
            r5 = 256(0x100, float:3.59E-43)
            r6 = 32
            r7 = 8192(0x2000, float:1.14794E-41)
            r8 = 512(0x200, float:7.175E-43)
            if (r3 != 0) goto L_0x024e
            int r9 = r14.n()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r10 = 0
            switch(r9) {
                case 0: goto L_0x0031;
                case 8: goto L_0x01eb;
                case 16: goto L_0x01dd;
                case 26: goto L_0x01b1;
                case 34: goto L_0x0197;
                case 42: goto L_0x016d;
                case 50: goto L_0x0137;
                case 56: goto L_0x012a;
                case 64: goto L_0x011d;
                case 72: goto L_0x010f;
                case 80: goto L_0x0101;
                case 88: goto L_0x00f4;
                case 98: goto L_0x00da;
                case 104: goto L_0x00be;
                case 106: goto L_0x008a;
                case 248: goto L_0x006f;
                case 250: goto L_0x003c;
                default: goto L_0x002b;
            }     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
        L_0x002b:
            boolean r5 = r13.m(r14, r2, r15, r9)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            if (r5 != 0) goto L_0x0019
        L_0x0031:
            r3 = r1
            goto L_0x0019
        L_0x0033:
            r14 = move-exception
            goto L_0x0208
        L_0x0036:
            r14 = move-exception
            goto L_0x01f9
        L_0x0039:
            r14 = move-exception
            goto L_0x0205
        L_0x003c:
            int r9 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r9 = r14.d(r9)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r10 = r4 & 8192(0x2000, float:1.14794E-41)
            if (r10 == r7) goto L_0x0057
            int r10 = r14.b()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            if (r10 <= 0) goto L_0x0057
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r10.<init>()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.u = r10     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r4 = r4 | 8192(0x2000, float:1.14794E-41)
        L_0x0057:
            int r10 = r14.b()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            if (r10 <= 0) goto L_0x006b
            java.util.List r10 = r13.u     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r11 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r10.add(r11)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0057
        L_0x006b:
            r14.c(r9)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x006f:
            r9 = r4 & 8192(0x2000, float:1.14794E-41)
            if (r9 == r7) goto L_0x007c
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9.<init>()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.u = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r4 = r4 | 8192(0x2000, float:1.14794E-41)
        L_0x007c:
            java.util.List r9 = r13.u     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r10 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9.add(r10)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x008a:
            int r9 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r9 = r14.d(r9)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r10 = r4 & 512(0x200, float:7.175E-43)
            if (r10 == r8) goto L_0x00a5
            int r10 = r14.b()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            if (r10 <= 0) goto L_0x00a5
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r10.<init>()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.f4752p = r10     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r4 = r4 | 512(0x200, float:7.175E-43)
        L_0x00a5:
            int r10 = r14.b()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            if (r10 <= 0) goto L_0x00b9
            java.util.List r10 = r13.f4752p     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r11 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r10.add(r11)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x00a5
        L_0x00b9:
            r14.c(r9)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x00be:
            r9 = r4 & 512(0x200, float:7.175E-43)
            if (r9 == r8) goto L_0x00cb
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9.<init>()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.f4752p = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r4 = r4 | 512(0x200, float:7.175E-43)
        L_0x00cb:
            java.util.List r9 = r13.f4752p     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r10 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9.add(r10)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x00da:
            r9 = r4 & 256(0x100, float:3.59E-43)
            if (r9 == r5) goto L_0x00e7
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9.<init>()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.f4751o = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r4 = r4 | 256(0x100, float:3.59E-43)
        L_0x00e7:
            java.util.List r9 = r13.f4751o     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            lf.a r10 = lf.Q.f4770x     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            rf.b r10 = r14.g(r10, r15)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9.add(r10)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x00f4:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9 = r9 | r1
            r13.f = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r9 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.g = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x0101:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9 = r9 | 64
            r13.f = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r9 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.n = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x010f:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9 = r9 | 16
            r13.f = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r9 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.k = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x011d:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9 = r9 | r8
            r13.f = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r9 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.t = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x012a:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9 = r9 | r5
            r13.f = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r9 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.s = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x0137:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r11 = 128(0x80, float:1.794E-43)
            r9 = r9 & r11
            if (r9 != r11) goto L_0x0151
            lf.Z r9 = r13.r     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9.getClass()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            lf.Y r10 = new lf.Y     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r10.<init>()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            lf.Q r12 = lf.Q.w     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r10.f4799j = r12     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r10.l = r12     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r10.f(r9)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
        L_0x0151:
            lf.a r9 = lf.Z.f4801p     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            rf.b r9 = r14.g(r9, r15)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            lf.Z r9 = (lf.Z) r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.r = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            if (r10 == 0) goto L_0x0166
            r10.f(r9)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            lf.Z r9 = r10.e()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.r = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
        L_0x0166:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9 = r9 | r11
            r13.f = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x016d:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9 = r9 & r6
            if (r9 != r6) goto L_0x017b
            lf.Q r9 = r13.m     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9.getClass()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            lf.P r10 = lf.Q.p(r9)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
        L_0x017b:
            lf.a r9 = lf.Q.f4770x     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            rf.b r9 = r14.g(r9, r15)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            lf.Q r9 = (lf.Q) r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.m = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            if (r10 == 0) goto L_0x0190
            r10.g(r9)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            lf.Q r9 = r10.e()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.m = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
        L_0x0190:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9 = r9 | r6
            r13.f = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x0197:
            r9 = r4 & 32
            if (r9 == r6) goto L_0x01a4
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9.<init>()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.l = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r4 = r4 | 32
        L_0x01a4:
            java.util.List r9 = r13.l     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            lf.a r10 = lf.W.q     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            rf.b r10 = r14.g(r10, r15)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9.add(r10)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x01b1:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r11 = 8
            r9 = r9 & r11
            if (r9 != r11) goto L_0x01c1
            lf.Q r9 = r13.f4750j     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9.getClass()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            lf.P r10 = lf.Q.p(r9)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
        L_0x01c1:
            lf.a r9 = lf.Q.f4770x     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            rf.b r9 = r14.g(r9, r15)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            lf.Q r9 = (lf.Q) r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.f4750j = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            if (r10 == 0) goto L_0x01d6
            r10.g(r9)     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            lf.Q r9 = r10.e()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.f4750j = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
        L_0x01d6:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9 = r9 | r11
            r13.f = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x01dd:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9 = r9 | 4
            r13.f = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r9 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.f4749i = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x01eb:
            int r9 = r13.f     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r9 = r9 | 2
            r13.f = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            int r9 = r14.k()     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            r13.f4748h = r9     // Catch:{ u -> 0x0039, IOException -> 0x0036 }
            goto L_0x0019
        L_0x01f9:
            rf.u r15 = new rf.u     // Catch:{ all -> 0x0033 }
            java.lang.String r14 = r14.getMessage()     // Catch:{ all -> 0x0033 }
            r15.<init>(r14)     // Catch:{ all -> 0x0033 }
            r15.d = r13     // Catch:{ all -> 0x0033 }
            throw r15     // Catch:{ all -> 0x0033 }
        L_0x0205:
            r14.d = r13     // Catch:{ all -> 0x0033 }
            throw r14     // Catch:{ all -> 0x0033 }
        L_0x0208:
            r15 = r4 & 32
            if (r15 != r6) goto L_0x0214
            java.util.List r15 = r13.l
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.l = r15
        L_0x0214:
            r15 = r4 & 256(0x100, float:3.59E-43)
            if (r15 != r5) goto L_0x0220
            java.util.List r15 = r13.f4751o
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.f4751o = r15
        L_0x0220:
            r15 = r4 & 512(0x200, float:7.175E-43)
            if (r15 != r8) goto L_0x022c
            java.util.List r15 = r13.f4752p
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.f4752p = r15
        L_0x022c:
            r15 = r4 & 8192(0x2000, float:1.14794E-41)
            if (r15 != r7) goto L_0x0238
            java.util.List r15 = r13.u
            java.util.List r15 = java.util.Collections.unmodifiableList(r15)
            r13.u = r15
        L_0x0238:
            r2.i()     // Catch:{ IOException -> 0x023b, all -> 0x0242 }
        L_0x023b:
            rf.e r15 = r0.f()
            r13.e = r15
            goto L_0x024a
        L_0x0242:
            r14 = move-exception
            rf.e r15 = r0.f()
            r13.e = r15
            throw r14
        L_0x024a:
            r13.l()
            throw r14
        L_0x024e:
            r14 = r4 & 32
            if (r14 != r6) goto L_0x025a
            java.util.List r14 = r13.l
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.l = r14
        L_0x025a:
            r14 = r4 & 256(0x100, float:3.59E-43)
            if (r14 != r5) goto L_0x0266
            java.util.List r14 = r13.f4751o
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.f4751o = r14
        L_0x0266:
            r14 = r4 & 512(0x200, float:7.175E-43)
            if (r14 != r8) goto L_0x0272
            java.util.List r14 = r13.f4752p
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.f4752p = r14
        L_0x0272:
            r14 = r4 & 8192(0x2000, float:1.14794E-41)
            if (r14 != r7) goto L_0x027e
            java.util.List r14 = r13.u
            java.util.List r14 = java.util.Collections.unmodifiableList(r14)
            r13.u = r14
        L_0x027e:
            r2.i()     // Catch:{ IOException -> 0x0281, all -> 0x0288 }
        L_0x0281:
            rf.e r14 = r0.f()
            r13.e = r14
            goto L_0x0290
        L_0x0288:
            r14 = move-exception
            rf.e r15 = r0.f()
            r13.e = r15
            throw r14
        L_0x0290:
            r13.l()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lf.G.<init>(rf.f, rf.h):void");
    }
}
