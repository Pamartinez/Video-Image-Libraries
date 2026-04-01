package lf;

import B2.o;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import ge.W0;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1255e;
import rf.C1262l;
import rf.C1264n;

/* renamed from: lf.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1157j extends C1264n {

    /* renamed from: M  reason: collision with root package name */
    public static final C1157j f4840M;

    /* renamed from: N  reason: collision with root package name */
    public static final C1148a f4841N = new C1148a(3);

    /* renamed from: A  reason: collision with root package name */
    public Q f4842A;
    public int B;

    /* renamed from: C  reason: collision with root package name */
    public List f4843C;
    public int D;
    public List E;

    /* renamed from: F  reason: collision with root package name */
    public List f4844F;

    /* renamed from: G  reason: collision with root package name */
    public int f4845G;

    /* renamed from: H  reason: collision with root package name */
    public X f4846H;

    /* renamed from: I  reason: collision with root package name */
    public List f4847I;

    /* renamed from: J  reason: collision with root package name */
    public e0 f4848J;

    /* renamed from: K  reason: collision with root package name */
    public byte f4849K;
    public int L;
    public final C1255e e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4850h;

    /* renamed from: i  reason: collision with root package name */
    public int f4851i;

    /* renamed from: j  reason: collision with root package name */
    public List f4852j;
    public List k;
    public List l;
    public int m;
    public List n;

    /* renamed from: o  reason: collision with root package name */
    public int f4853o;

    /* renamed from: p  reason: collision with root package name */
    public List f4854p;
    public List q;
    public int r;
    public List s;
    public List t;
    public List u;
    public List v;
    public List w;

    /* renamed from: x  reason: collision with root package name */
    public List f4855x;
    public int y;
    public int z;

    static {
        C1157j jVar = new C1157j();
        f4840M = jVar;
        jVar.o();
    }

    public C1157j(C1155h hVar) {
        super(hVar);
        this.m = -1;
        this.f4853o = -1;
        this.r = -1;
        this.y = -1;
        this.D = -1;
        this.f4845G = -1;
        this.f4849K = -1;
        this.L = -1;
        this.e = hVar.d;
    }

    public final int a() {
        int i2;
        int i7 = this.L;
        if (i7 != -1) {
            return i7;
        }
        if ((this.f & 1) == 1) {
            i2 = o.b(1, this.g);
        } else {
            i2 = 0;
        }
        int i8 = 0;
        for (int i10 = 0; i10 < this.l.size(); i10++) {
            i8 += o.c(((Integer) this.l.get(i10)).intValue());
        }
        int i11 = i2 + i8;
        if (!this.l.isEmpty()) {
            i11 = i11 + 1 + o.c(i8);
        }
        this.m = i8;
        if ((this.f & 2) == 2) {
            i11 += o.b(3, this.f4850h);
        }
        if ((this.f & 4) == 4) {
            i11 += o.b(4, this.f4851i);
        }
        for (int i12 = 0; i12 < this.f4852j.size(); i12++) {
            i11 += o.d(5, (C1252b) this.f4852j.get(i12));
        }
        for (int i13 = 0; i13 < this.k.size(); i13++) {
            i11 += o.d(6, (C1252b) this.k.get(i13));
        }
        int i14 = 0;
        for (int i15 = 0; i15 < this.n.size(); i15++) {
            i14 += o.c(((Integer) this.n.get(i15)).intValue());
        }
        int i16 = i11 + i14;
        if (!this.n.isEmpty()) {
            i16 = i16 + 1 + o.c(i14);
        }
        this.f4853o = i14;
        for (int i17 = 0; i17 < this.s.size(); i17++) {
            i16 += o.d(8, (C1252b) this.s.get(i17));
        }
        for (int i18 = 0; i18 < this.t.size(); i18++) {
            i16 += o.d(9, (C1252b) this.t.get(i18));
        }
        for (int i19 = 0; i19 < this.u.size(); i19++) {
            i16 += o.d(10, (C1252b) this.u.get(i19));
        }
        for (int i20 = 0; i20 < this.v.size(); i20++) {
            i16 += o.d(11, (C1252b) this.v.get(i20));
        }
        for (int i21 = 0; i21 < this.w.size(); i21++) {
            i16 += o.d(13, (C1252b) this.w.get(i21));
        }
        int i22 = 0;
        for (int i23 = 0; i23 < this.f4855x.size(); i23++) {
            i22 += o.c(((Integer) this.f4855x.get(i23)).intValue());
        }
        int i24 = i16 + i22;
        if (!this.f4855x.isEmpty()) {
            i24 = i24 + 2 + o.c(i22);
        }
        this.y = i22;
        if ((this.f & 8) == 8) {
            i24 += o.b(17, this.z);
        }
        if ((this.f & 16) == 16) {
            i24 += o.d(18, this.f4842A);
        }
        if ((this.f & 32) == 32) {
            i24 += o.b(19, this.B);
        }
        for (int i25 = 0; i25 < this.f4854p.size(); i25++) {
            i24 += o.d(20, (C1252b) this.f4854p.get(i25));
        }
        int i26 = 0;
        for (int i27 = 0; i27 < this.q.size(); i27++) {
            i26 += o.c(((Integer) this.q.get(i27)).intValue());
        }
        int i28 = i24 + i26;
        if (!this.q.isEmpty()) {
            i28 = i28 + 2 + o.c(i26);
        }
        this.r = i26;
        int i29 = 0;
        for (int i30 = 0; i30 < this.f4843C.size(); i30++) {
            i29 += o.c(((Integer) this.f4843C.get(i30)).intValue());
        }
        int i31 = i28 + i29;
        if (!this.f4843C.isEmpty()) {
            i31 = i31 + 2 + o.c(i29);
        }
        this.D = i29;
        for (int i32 = 0; i32 < this.E.size(); i32++) {
            i31 += o.d(23, (C1252b) this.E.get(i32));
        }
        int i33 = 0;
        for (int i34 = 0; i34 < this.f4844F.size(); i34++) {
            i33 += o.c(((Integer) this.f4844F.get(i34)).intValue());
        }
        int i35 = i31 + i33;
        if (!this.f4844F.isEmpty()) {
            i35 = i35 + 2 + o.c(i33);
        }
        this.f4845G = i33;
        if ((this.f & 64) == 64) {
            i35 += o.d(30, this.f4846H);
        }
        int i36 = 0;
        for (int i37 = 0; i37 < this.f4847I.size(); i37++) {
            i36 += o.c(((Integer) this.f4847I.get(i37)).intValue());
        }
        int size = (this.f4847I.size() * 2) + i35 + i36;
        if ((this.f & 128) == 128) {
            size += o.d(32, this.f4848J);
        }
        int size2 = this.e.size() + h() + size;
        this.L = size2;
        return size2;
    }

    public final C1262l b() {
        return C1155h.f();
    }

    public final C1262l c() {
        C1155h f5 = C1155h.f();
        f5.g(this);
        return f5;
    }

    public final void d(o oVar) {
        a();
        W0 w02 = new W0((C1264n) this);
        if ((this.f & 1) == 1) {
            oVar.m(1, this.g);
        }
        if (this.l.size() > 0) {
            oVar.v(18);
            oVar.v(this.m);
        }
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            oVar.n(((Integer) this.l.get(i2)).intValue());
        }
        if ((this.f & 2) == 2) {
            oVar.m(3, this.f4850h);
        }
        if ((this.f & 4) == 4) {
            oVar.m(4, this.f4851i);
        }
        for (int i7 = 0; i7 < this.f4852j.size(); i7++) {
            oVar.o(5, (C1252b) this.f4852j.get(i7));
        }
        for (int i8 = 0; i8 < this.k.size(); i8++) {
            oVar.o(6, (C1252b) this.k.get(i8));
        }
        if (this.n.size() > 0) {
            oVar.v(58);
            oVar.v(this.f4853o);
        }
        for (int i10 = 0; i10 < this.n.size(); i10++) {
            oVar.n(((Integer) this.n.get(i10)).intValue());
        }
        for (int i11 = 0; i11 < this.s.size(); i11++) {
            oVar.o(8, (C1252b) this.s.get(i11));
        }
        for (int i12 = 0; i12 < this.t.size(); i12++) {
            oVar.o(9, (C1252b) this.t.get(i12));
        }
        for (int i13 = 0; i13 < this.u.size(); i13++) {
            oVar.o(10, (C1252b) this.u.get(i13));
        }
        for (int i14 = 0; i14 < this.v.size(); i14++) {
            oVar.o(11, (C1252b) this.v.get(i14));
        }
        for (int i15 = 0; i15 < this.w.size(); i15++) {
            oVar.o(13, (C1252b) this.w.get(i15));
        }
        if (this.f4855x.size() > 0) {
            oVar.v(130);
            oVar.v(this.y);
        }
        for (int i16 = 0; i16 < this.f4855x.size(); i16++) {
            oVar.n(((Integer) this.f4855x.get(i16)).intValue());
        }
        if ((this.f & 8) == 8) {
            oVar.m(17, this.z);
        }
        if ((this.f & 16) == 16) {
            oVar.o(18, this.f4842A);
        }
        if ((this.f & 32) == 32) {
            oVar.m(19, this.B);
        }
        for (int i17 = 0; i17 < this.f4854p.size(); i17++) {
            oVar.o(20, (C1252b) this.f4854p.get(i17));
        }
        if (this.q.size() > 0) {
            oVar.v(MOCRLang.MALAYALAM);
            oVar.v(this.r);
        }
        for (int i18 = 0; i18 < this.q.size(); i18++) {
            oVar.n(((Integer) this.q.get(i18)).intValue());
        }
        if (this.f4843C.size() > 0) {
            oVar.v(178);
            oVar.v(this.D);
        }
        for (int i19 = 0; i19 < this.f4843C.size(); i19++) {
            oVar.n(((Integer) this.f4843C.get(i19)).intValue());
        }
        for (int i20 = 0; i20 < this.E.size(); i20++) {
            oVar.o(23, (C1252b) this.E.get(i20));
        }
        if (this.f4844F.size() > 0) {
            oVar.v(194);
            oVar.v(this.f4845G);
        }
        for (int i21 = 0; i21 < this.f4844F.size(); i21++) {
            oVar.n(((Integer) this.f4844F.get(i21)).intValue());
        }
        if ((this.f & 64) == 64) {
            oVar.o(30, this.f4846H);
        }
        for (int i22 = 0; i22 < this.f4847I.size(); i22++) {
            oVar.m(31, ((Integer) this.f4847I.get(i22)).intValue());
        }
        if ((this.f & 128) == 128) {
            oVar.o(32, this.f4848J);
        }
        w02.A0(ErrorCodeConvertor.CLOUD_UID_VALIDATION, oVar);
        oVar.r(this.e);
    }

    public final C1252b getDefaultInstanceForType() {
        return f4840M;
    }

    public final boolean isInitialized() {
        byte b = this.f4849K;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.f & 2) == 2) {
            for (int i2 = 0; i2 < this.f4852j.size(); i2++) {
                if (!((W) this.f4852j.get(i2)).isInitialized()) {
                    this.f4849K = 0;
                    return false;
                }
            }
            for (int i7 = 0; i7 < this.k.size(); i7++) {
                if (!((Q) this.k.get(i7)).isInitialized()) {
                    this.f4849K = 0;
                    return false;
                }
            }
            for (int i8 = 0; i8 < this.f4854p.size(); i8++) {
                if (!((Q) this.f4854p.get(i8)).isInitialized()) {
                    this.f4849K = 0;
                    return false;
                }
            }
            for (int i10 = 0; i10 < this.s.size(); i10++) {
                if (!((C1159l) this.s.get(i10)).isInitialized()) {
                    this.f4849K = 0;
                    return false;
                }
            }
            for (int i11 = 0; i11 < this.t.size(); i11++) {
                if (!((C1171y) this.t.get(i11)).isInitialized()) {
                    this.f4849K = 0;
                    return false;
                }
            }
            for (int i12 = 0; i12 < this.u.size(); i12++) {
                if (!((G) this.u.get(i12)).isInitialized()) {
                    this.f4849K = 0;
                    return false;
                }
            }
            for (int i13 = 0; i13 < this.v.size(); i13++) {
                if (!((T) this.v.get(i13)).isInitialized()) {
                    this.f4849K = 0;
                    return false;
                }
            }
            for (int i14 = 0; i14 < this.w.size(); i14++) {
                if (!((C1166t) this.w.get(i14)).isInitialized()) {
                    this.f4849K = 0;
                    return false;
                }
            }
            if ((this.f & 16) != 16 || this.f4842A.isInitialized()) {
                for (int i15 = 0; i15 < this.E.size(); i15++) {
                    if (!((Q) this.E.get(i15)).isInitialized()) {
                        this.f4849K = 0;
                        return false;
                    }
                }
                if ((this.f & 64) == 64 && !this.f4846H.isInitialized()) {
                    this.f4849K = 0;
                    return false;
                } else if (!g()) {
                    this.f4849K = 0;
                    return false;
                } else {
                    this.f4849K = 1;
                    return true;
                }
            } else {
                this.f4849K = 0;
                return false;
            }
        } else {
            this.f4849K = 0;
            return false;
        }
    }

    public final void o() {
        this.g = 6;
        this.f4850h = 0;
        this.f4851i = 0;
        List list = Collections.EMPTY_LIST;
        this.f4852j = list;
        this.k = list;
        this.l = list;
        this.n = list;
        this.f4854p = list;
        this.q = list;
        this.s = list;
        this.t = list;
        this.u = list;
        this.v = list;
        this.w = list;
        this.f4855x = list;
        this.z = 0;
        this.f4842A = Q.w;
        this.B = 0;
        this.f4843C = list;
        this.E = list;
        this.f4844F = list;
        this.f4846H = X.f4794j;
        this.f4847I = list;
        this.f4848J = e0.f4826h;
    }

    public C1157j() {
        this.m = -1;
        this.f4853o = -1;
        this.r = -1;
        this.y = -1;
        this.D = -1;
        this.f4845G = -1;
        this.f4849K = -1;
        this.L = -1;
        this.e = C1255e.d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v0, resolved type: lf.P} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v1, resolved type: lf.P} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v3, resolved type: lf.P} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v84, resolved type: lf.f} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v6, resolved type: lf.P} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v28, resolved type: lf.m} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v8, resolved type: lf.P} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v9, resolved type: lf.P} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v10, resolved type: lf.P} */
    /* JADX WARNING: type inference failed for: r19v4, types: [lf.f] */
    /* JADX WARNING: type inference failed for: r19v7, types: [lf.m] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x048c  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x049a  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x04a8  */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x04b4  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x04c2  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x04d0  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x04de  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x04ec  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x04fa  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x0508  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x0516  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x052e  */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x053a  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x0546  */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x0552  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C1157j(rf.C1256f r22, rf.C1258h r23) {
        /*
            r21 = this;
            r1 = r21
            r0 = r22
            r2 = r23
            r1.<init>()
            r3 = -1
            r1.m = r3
            r1.f4853o = r3
            r1.r = r3
            r1.y = r3
            r1.D = r3
            r1.f4845G = r3
            r1.f4849K = r3
            r1.L = r3
            r1.o()
            rf.d r3 = rf.C1255e.v()
            r4 = 1
            B2.o r5 = B2.o.j(r3, r4)
            r6 = 0
            r7 = r6
        L_0x0028:
            r13 = 524288(0x80000, float:7.34684E-40)
            r16 = r4
            r17 = 8
            r14 = 256(0x100, float:3.59E-43)
            r8 = 262144(0x40000, float:3.67342E-40)
            r9 = 1048576(0x100000, float:1.469368E-39)
            r10 = 4194304(0x400000, float:5.877472E-39)
            r11 = 128(0x80, float:1.794E-43)
            r18 = 32
            r12 = 64
            if (r6 != 0) goto L_0x0570
            int r15 = r0.n()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r19 = 0
            switch(r15) {
                case 0: goto L_0x004d;
                case 8: goto L_0x0467;
                case 16: goto L_0x044a;
                case 18: goto L_0x0415;
                case 24: goto L_0x0408;
                case 32: goto L_0x03fb;
                case 42: goto L_0x03df;
                case 50: goto L_0x03c3;
                case 56: goto L_0x03a7;
                case 58: goto L_0x0373;
                case 66: goto L_0x0357;
                case 74: goto L_0x033b;
                case 82: goto L_0x031f;
                case 90: goto L_0x0303;
                case 106: goto L_0x02e7;
                case 128: goto L_0x02c9;
                case 130: goto L_0x0293;
                case 136: goto L_0x0285;
                case 146: goto L_0x0254;
                case 152: goto L_0x0244;
                case 162: goto L_0x0226;
                case 168: goto L_0x0208;
                case 170: goto L_0x01d2;
                case 176: goto L_0x01b5;
                case 178: goto L_0x0180;
                case 186: goto L_0x0165;
                case 192: goto L_0x0148;
                case 194: goto L_0x0113;
                case 242: goto L_0x00e8;
                case 248: goto L_0x00cb;
                case 250: goto L_0x0094;
                case 258: goto L_0x005a;
                default: goto L_0x0047;
            }     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
        L_0x0047:
            boolean r4 = r1.m(r0, r5, r2, r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r4 != 0) goto L_0x0473
        L_0x004d:
            r6 = r16
            goto L_0x0473
        L_0x0051:
            r0 = move-exception
            goto L_0x0486
        L_0x0054:
            r0 = move-exception
            goto L_0x0477
        L_0x0057:
            r0 = move-exception
            goto L_0x0483
        L_0x005a:
            int r15 = r1.f     // Catch:{ u -> 0x008f, IOException -> 0x008a, all -> 0x0085 }
            r15 = r15 & r11
            if (r15 != r11) goto L_0x0065
            lf.e0 r15 = r1.f4848J     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.m r19 = r15.g()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
        L_0x0065:
            r15 = r19
            r20 = 16
            lf.a r4 = lf.e0.f4827i     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            rf.b r4 = r0.g(r4, r2)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.e0 r4 = (lf.e0) r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4848J = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r15 == 0) goto L_0x007e
            r15.l(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.e0 r4 = r15.g()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4848J = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
        L_0x007e:
            int r4 = r1.f     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4 = r4 | r11
            r1.f = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0085:
            r0 = move-exception
            r20 = 16
            goto L_0x0486
        L_0x008a:
            r0 = move-exception
            r20 = 16
            goto L_0x0477
        L_0x008f:
            r0 = move-exception
            r20 = 16
            goto L_0x0483
        L_0x0094:
            r20 = 16
            int r4 = r0.k()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r4 = r0.d(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r15 = r7 & r10
            if (r15 == r10) goto L_0x00b0
            int r15 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r15 <= 0) goto L_0x00b0
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r15.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4847I = r15     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | r10
        L_0x00b0:
            int r15 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r15 <= 0) goto L_0x00c6
            java.util.List r15 = r1.f4847I     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r19 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r19)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r15.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11 = 128(0x80, float:1.794E-43)
            goto L_0x00b0
        L_0x00c6:
            r0.c(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x00cb:
            r20 = 16
            r4 = r7 & r10
            if (r4 == r10) goto L_0x00d9
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4847I = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | r10
        L_0x00d9:
            java.util.List r4 = r1.f4847I     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r11 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x00e8:
            r20 = 16
            int r4 = r1.f     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4 = r4 & r12
            if (r4 != r12) goto L_0x00f5
            lf.X r4 = r1.f4846H     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.f r19 = r4.h()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
        L_0x00f5:
            r4 = r19
            lf.a r11 = lf.X.k     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            rf.b r11 = r0.g(r11, r2)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.X r11 = (lf.X) r11     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4846H = r11     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r4 == 0) goto L_0x010c
            r4.k(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.X r4 = r4.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4846H = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
        L_0x010c:
            int r4 = r1.f     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4 = r4 | r12
            r1.f = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0113:
            r20 = 16
            int r4 = r0.k()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r4 = r0.d(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11 = r7 & r9
            if (r11 == r9) goto L_0x012f
            int r11 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r11 <= 0) goto L_0x012f
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4844F = r11     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | r9
        L_0x012f:
            int r11 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r11 <= 0) goto L_0x0143
            java.util.List r11 = r1.f4844F     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r15 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11.add(r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x012f
        L_0x0143:
            r0.c(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0148:
            r20 = 16
            r4 = r7 & r9
            if (r4 == r9) goto L_0x0156
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4844F = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | r9
        L_0x0156:
            java.util.List r4 = r1.f4844F     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r11 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0165:
            r20 = 16
            r4 = r7 & r13
            if (r4 == r13) goto L_0x0173
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.E = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | r13
        L_0x0173:
            java.util.List r4 = r1.E     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.a r11 = lf.Q.f4770x     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            rf.b r11 = r0.g(r11, r2)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0180:
            r20 = 16
            int r4 = r0.k()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r4 = r0.d(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11 = r7 & r8
            if (r11 == r8) goto L_0x019c
            int r11 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r11 <= 0) goto L_0x019c
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4843C = r11     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | r8
        L_0x019c:
            int r11 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r11 <= 0) goto L_0x01b0
            java.util.List r11 = r1.f4843C     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r15 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11.add(r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x019c
        L_0x01b0:
            r0.c(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x01b5:
            r20 = 16
            r4 = r7 & r8
            if (r4 == r8) goto L_0x01c3
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4843C = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | r8
        L_0x01c3:
            java.util.List r4 = r1.f4843C     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r11 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x01d2:
            r20 = 16
            int r4 = r0.k()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r4 = r0.d(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11 = r7 & 256(0x100, float:3.59E-43)
            if (r11 == r14) goto L_0x01ef
            int r11 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r11 <= 0) goto L_0x01ef
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.q = r11     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 256(0x100, float:3.59E-43)
        L_0x01ef:
            int r11 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r11 <= 0) goto L_0x0203
            java.util.List r11 = r1.q     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r15 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11.add(r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x01ef
        L_0x0203:
            r0.c(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0208:
            r20 = 16
            r4 = r7 & 256(0x100, float:3.59E-43)
            if (r4 == r14) goto L_0x0217
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.q = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 256(0x100, float:3.59E-43)
        L_0x0217:
            java.util.List r4 = r1.q     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r11 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0226:
            r20 = 16
            r4 = r7 & 128(0x80, float:1.794E-43)
            r11 = 128(0x80, float:1.794E-43)
            if (r4 == r11) goto L_0x0237
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4854p = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 128(0x80, float:1.794E-43)
        L_0x0237:
            java.util.List r4 = r1.f4854p     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.a r11 = lf.Q.f4770x     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            rf.b r11 = r0.g(r11, r2)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0244:
            r20 = 16
            int r4 = r1.f     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4 = r4 | 32
            r1.f = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r4 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.B = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0254:
            r20 = 16
            int r4 = r1.f     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4 = r4 & 16
            r11 = r20
            if (r4 != r11) goto L_0x0264
            lf.Q r4 = r1.f4842A     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.P r19 = r4.c()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
        L_0x0264:
            r4 = r19
            lf.a r11 = lf.Q.f4770x     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            rf.b r11 = r0.g(r11, r2)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.Q r11 = (lf.Q) r11     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4842A = r11     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r4 == 0) goto L_0x027b
            r4.g(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.Q r4 = r4.e()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4842A = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
        L_0x027b:
            int r4 = r1.f     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r20 = 16
            r4 = r4 | 16
            r1.f = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0285:
            int r4 = r1.f     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4 = r4 | 8
            r1.f = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r4 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.z = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0293:
            int r4 = r0.k()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r4 = r0.d(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11 = r7 & 16384(0x4000, float:2.2959E-41)
            r15 = 16384(0x4000, float:2.2959E-41)
            if (r11 == r15) goto L_0x02b0
            int r11 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r11 <= 0) goto L_0x02b0
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4855x = r11     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 16384(0x4000, float:2.2959E-41)
        L_0x02b0:
            int r11 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r11 <= 0) goto L_0x02c4
            java.util.List r11 = r1.f4855x     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r15 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11.add(r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x02b0
        L_0x02c4:
            r0.c(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x02c9:
            r4 = r7 & 16384(0x4000, float:2.2959E-41)
            r15 = 16384(0x4000, float:2.2959E-41)
            if (r4 == r15) goto L_0x02d8
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4855x = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 16384(0x4000, float:2.2959E-41)
        L_0x02d8:
            java.util.List r4 = r1.f4855x     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r11 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x02e7:
            r4 = r7 & 8192(0x2000, float:1.14794E-41)
            r11 = 8192(0x2000, float:1.14794E-41)
            if (r4 == r11) goto L_0x02f6
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.w = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 8192(0x2000, float:1.14794E-41)
        L_0x02f6:
            java.util.List r4 = r1.w     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.a r11 = lf.C1166t.k     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            rf.b r11 = r0.g(r11, r2)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0303:
            r4 = r7 & 4096(0x1000, float:5.74E-42)
            r11 = 4096(0x1000, float:5.74E-42)
            if (r4 == r11) goto L_0x0312
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.v = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 4096(0x1000, float:5.74E-42)
        L_0x0312:
            java.util.List r4 = r1.v     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.a r11 = lf.T.s     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            rf.b r11 = r0.g(r11, r2)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x031f:
            r4 = r7 & 2048(0x800, float:2.87E-42)
            r11 = 2048(0x800, float:2.87E-42)
            if (r4 == r11) goto L_0x032e
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.u = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 2048(0x800, float:2.87E-42)
        L_0x032e:
            java.util.List r4 = r1.u     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.a r11 = lf.G.y     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            rf.b r11 = r0.g(r11, r2)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x033b:
            r4 = r7 & 1024(0x400, float:1.435E-42)
            r11 = 1024(0x400, float:1.435E-42)
            if (r4 == r11) goto L_0x034a
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.t = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 1024(0x400, float:1.435E-42)
        L_0x034a:
            java.util.List r4 = r1.t     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.a r11 = lf.C1171y.y     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            rf.b r11 = r0.g(r11, r2)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0357:
            r4 = r7 & 512(0x200, float:7.175E-43)
            r11 = 512(0x200, float:7.175E-43)
            if (r4 == r11) goto L_0x0366
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.s = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 512(0x200, float:7.175E-43)
        L_0x0366:
            java.util.List r4 = r1.s     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.a r11 = lf.C1159l.m     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            rf.b r11 = r0.g(r11, r2)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0373:
            int r4 = r0.k()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r4 = r0.d(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11 = r7 & 64
            if (r11 == r12) goto L_0x038e
            int r11 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r11 <= 0) goto L_0x038e
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.n = r11     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 64
        L_0x038e:
            int r11 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r11 <= 0) goto L_0x03a2
            java.util.List r11 = r1.n     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r15 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11.add(r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x038e
        L_0x03a2:
            r0.c(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x03a7:
            r4 = r7 & 64
            if (r4 == r12) goto L_0x03b4
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.n = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 64
        L_0x03b4:
            java.util.List r4 = r1.n     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r11 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x03c3:
            r4 = r7 & 16
            r11 = 16
            if (r4 == r11) goto L_0x03d2
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.k = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 16
        L_0x03d2:
            java.util.List r4 = r1.k     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.a r11 = lf.Q.f4770x     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            rf.b r11 = r0.g(r11, r2)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x03df:
            r4 = r7 & 8
            r11 = r17
            if (r4 == r11) goto L_0x03ee
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4852j = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 8
        L_0x03ee:
            java.util.List r4 = r1.f4852j     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            lf.a r11 = lf.W.q     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            rf.b r11 = r0.g(r11, r2)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x03fb:
            int r4 = r1.f     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4 = r4 | 4
            r1.f = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r4 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4851i = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0408:
            int r4 = r1.f     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4 = r4 | 2
            r1.f = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r4 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.f4850h = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0415:
            int r4 = r0.k()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r4 = r0.d(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11 = r7 & 32
            r15 = r18
            if (r11 == r15) goto L_0x0432
            int r11 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r11 <= 0) goto L_0x0432
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.l = r11     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 32
        L_0x0432:
            int r11 = r0.b()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            if (r11 <= 0) goto L_0x0446
            java.util.List r11 = r1.l     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r15 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r11.add(r15)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0432
        L_0x0446:
            r0.c(r4)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x044a:
            r4 = r7 & 32
            r15 = 32
            if (r4 == r15) goto L_0x0459
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.<init>()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.l = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r7 = r7 | 32
        L_0x0459:
            java.util.List r4 = r1.l     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r11 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4.add(r11)     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            goto L_0x0473
        L_0x0467:
            int r4 = r1.f     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r4 = r4 | 1
            r1.f = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            int r4 = r0.f()     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
            r1.g = r4     // Catch:{ u -> 0x0057, IOException -> 0x0054 }
        L_0x0473:
            r4 = r16
            goto L_0x0028
        L_0x0477:
            rf.u r2 = new rf.u     // Catch:{ all -> 0x0051 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0051 }
            r2.<init>(r0)     // Catch:{ all -> 0x0051 }
            r2.d = r1     // Catch:{ all -> 0x0051 }
            throw r2     // Catch:{ all -> 0x0051 }
        L_0x0483:
            r0.d = r1     // Catch:{ all -> 0x0051 }
            throw r0     // Catch:{ all -> 0x0051 }
        L_0x0486:
            r2 = r7 & 32
            r15 = 32
            if (r2 != r15) goto L_0x0494
            java.util.List r2 = r1.l
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.l = r2
        L_0x0494:
            r2 = r7 & 8
            r11 = 8
            if (r2 != r11) goto L_0x04a2
            java.util.List r2 = r1.f4852j
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.f4852j = r2
        L_0x04a2:
            r2 = r7 & 16
            r11 = 16
            if (r2 != r11) goto L_0x04b0
            java.util.List r2 = r1.k
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.k = r2
        L_0x04b0:
            r2 = r7 & 64
            if (r2 != r12) goto L_0x04bc
            java.util.List r2 = r1.n
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.n = r2
        L_0x04bc:
            r2 = r7 & 512(0x200, float:7.175E-43)
            r11 = 512(0x200, float:7.175E-43)
            if (r2 != r11) goto L_0x04ca
            java.util.List r2 = r1.s
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.s = r2
        L_0x04ca:
            r2 = r7 & 1024(0x400, float:1.435E-42)
            r11 = 1024(0x400, float:1.435E-42)
            if (r2 != r11) goto L_0x04d8
            java.util.List r2 = r1.t
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.t = r2
        L_0x04d8:
            r2 = r7 & 2048(0x800, float:2.87E-42)
            r11 = 2048(0x800, float:2.87E-42)
            if (r2 != r11) goto L_0x04e6
            java.util.List r2 = r1.u
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.u = r2
        L_0x04e6:
            r2 = r7 & 4096(0x1000, float:5.74E-42)
            r11 = 4096(0x1000, float:5.74E-42)
            if (r2 != r11) goto L_0x04f4
            java.util.List r2 = r1.v
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.v = r2
        L_0x04f4:
            r2 = r7 & 8192(0x2000, float:1.14794E-41)
            r11 = 8192(0x2000, float:1.14794E-41)
            if (r2 != r11) goto L_0x0502
            java.util.List r2 = r1.w
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.w = r2
        L_0x0502:
            r2 = r7 & 16384(0x4000, float:2.2959E-41)
            r15 = 16384(0x4000, float:2.2959E-41)
            if (r2 != r15) goto L_0x0510
            java.util.List r2 = r1.f4855x
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.f4855x = r2
        L_0x0510:
            r2 = r7 & 128(0x80, float:1.794E-43)
            r11 = 128(0x80, float:1.794E-43)
            if (r2 != r11) goto L_0x051e
            java.util.List r2 = r1.f4854p
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.f4854p = r2
        L_0x051e:
            r2 = r7 & 256(0x100, float:3.59E-43)
            if (r2 != r14) goto L_0x052a
            java.util.List r2 = r1.q
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.q = r2
        L_0x052a:
            r2 = r7 & r8
            if (r2 != r8) goto L_0x0536
            java.util.List r2 = r1.f4843C
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.f4843C = r2
        L_0x0536:
            r2 = r7 & r13
            if (r2 != r13) goto L_0x0542
            java.util.List r2 = r1.E
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.E = r2
        L_0x0542:
            r2 = r7 & r9
            if (r2 != r9) goto L_0x054e
            java.util.List r2 = r1.f4844F
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.f4844F = r2
        L_0x054e:
            r2 = r7 & r10
            if (r2 != r10) goto L_0x055a
            java.util.List r2 = r1.f4847I
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)
            r1.f4847I = r2
        L_0x055a:
            r5.i()     // Catch:{ IOException -> 0x055d, all -> 0x0564 }
        L_0x055d:
            rf.e r2 = r3.f()
            r1.e = r2
            goto L_0x056c
        L_0x0564:
            r0 = move-exception
            rf.e r2 = r3.f()
            r1.e = r2
            throw r0
        L_0x056c:
            r1.l()
            throw r0
        L_0x0570:
            r0 = r7 & 32
            r15 = 32
            if (r0 != r15) goto L_0x057e
            java.util.List r0 = r1.l
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.l = r0
        L_0x057e:
            r0 = r7 & 8
            r11 = 8
            if (r0 != r11) goto L_0x058c
            java.util.List r0 = r1.f4852j
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.f4852j = r0
        L_0x058c:
            r0 = r7 & 16
            r11 = 16
            if (r0 != r11) goto L_0x059a
            java.util.List r0 = r1.k
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.k = r0
        L_0x059a:
            r0 = r7 & 64
            if (r0 != r12) goto L_0x05a6
            java.util.List r0 = r1.n
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.n = r0
        L_0x05a6:
            r0 = r7 & 512(0x200, float:7.175E-43)
            r11 = 512(0x200, float:7.175E-43)
            if (r0 != r11) goto L_0x05b4
            java.util.List r0 = r1.s
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.s = r0
        L_0x05b4:
            r0 = r7 & 1024(0x400, float:1.435E-42)
            r11 = 1024(0x400, float:1.435E-42)
            if (r0 != r11) goto L_0x05c2
            java.util.List r0 = r1.t
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.t = r0
        L_0x05c2:
            r0 = r7 & 2048(0x800, float:2.87E-42)
            r11 = 2048(0x800, float:2.87E-42)
            if (r0 != r11) goto L_0x05d0
            java.util.List r0 = r1.u
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.u = r0
        L_0x05d0:
            r0 = r7 & 4096(0x1000, float:5.74E-42)
            r11 = 4096(0x1000, float:5.74E-42)
            if (r0 != r11) goto L_0x05de
            java.util.List r0 = r1.v
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.v = r0
        L_0x05de:
            r0 = r7 & 8192(0x2000, float:1.14794E-41)
            r11 = 8192(0x2000, float:1.14794E-41)
            if (r0 != r11) goto L_0x05ec
            java.util.List r0 = r1.w
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.w = r0
        L_0x05ec:
            r0 = r7 & 16384(0x4000, float:2.2959E-41)
            r15 = 16384(0x4000, float:2.2959E-41)
            if (r0 != r15) goto L_0x05fa
            java.util.List r0 = r1.f4855x
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.f4855x = r0
        L_0x05fa:
            r0 = r7 & 128(0x80, float:1.794E-43)
            r11 = 128(0x80, float:1.794E-43)
            if (r0 != r11) goto L_0x0608
            java.util.List r0 = r1.f4854p
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.f4854p = r0
        L_0x0608:
            r0 = r7 & 256(0x100, float:3.59E-43)
            if (r0 != r14) goto L_0x0614
            java.util.List r0 = r1.q
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.q = r0
        L_0x0614:
            r0 = r7 & r8
            if (r0 != r8) goto L_0x0620
            java.util.List r0 = r1.f4843C
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.f4843C = r0
        L_0x0620:
            r0 = r7 & r13
            if (r0 != r13) goto L_0x062c
            java.util.List r0 = r1.E
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.E = r0
        L_0x062c:
            r0 = r7 & r9
            if (r0 != r9) goto L_0x0638
            java.util.List r0 = r1.f4844F
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.f4844F = r0
        L_0x0638:
            r0 = r7 & r10
            if (r0 != r10) goto L_0x0644
            java.util.List r0 = r1.f4847I
            java.util.List r0 = java.util.Collections.unmodifiableList(r0)
            r1.f4847I = r0
        L_0x0644:
            r5.i()     // Catch:{ IOException -> 0x0647, all -> 0x064e }
        L_0x0647:
            rf.e r0 = r3.f()
            r1.e = r0
            goto L_0x0656
        L_0x064e:
            r0 = move-exception
            rf.e r2 = r3.f()
            r1.e = r2
            throw r0
        L_0x0656:
            r1.l()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lf.C1157j.<init>(rf.f, rf.h):void");
    }
}
