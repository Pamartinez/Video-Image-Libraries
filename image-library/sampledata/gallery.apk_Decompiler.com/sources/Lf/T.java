package lf;

import B2.o;
import ge.W0;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1254d;
import rf.C1255e;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1264n;
import rf.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class T extends C1264n {
    public static final T r;
    public static final C1148a s = new C1148a(18);
    public final C1255e e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4781h;

    /* renamed from: i  reason: collision with root package name */
    public List f4782i;

    /* renamed from: j  reason: collision with root package name */
    public Q f4783j;
    public int k;
    public Q l;
    public int m;
    public List n;

    /* renamed from: o  reason: collision with root package name */
    public List f4784o;

    /* renamed from: p  reason: collision with root package name */
    public byte f4785p;
    public int q;

    static {
        T t = new T();
        r = t;
        t.g = 6;
        t.f4781h = 0;
        List list = Collections.EMPTY_LIST;
        t.f4782i = list;
        Q q10 = Q.w;
        t.f4783j = q10;
        t.k = 0;
        t.l = q10;
        t.m = 0;
        t.n = list;
        t.f4784o = list;
    }

    public T(S s5) {
        super(s5);
        this.f4785p = -1;
        this.q = -1;
        this.e = s5.d;
    }

    public final int a() {
        int i2;
        int i7 = this.q;
        if (i7 != -1) {
            return i7;
        }
        if ((this.f & 1) == 1) {
            i2 = o.b(1, this.g);
        } else {
            i2 = 0;
        }
        if ((this.f & 2) == 2) {
            i2 += o.b(2, this.f4781h);
        }
        for (int i8 = 0; i8 < this.f4782i.size(); i8++) {
            i2 += o.d(3, (C1252b) this.f4782i.get(i8));
        }
        if ((this.f & 4) == 4) {
            i2 += o.d(4, this.f4783j);
        }
        if ((this.f & 8) == 8) {
            i2 += o.b(5, this.k);
        }
        if ((this.f & 16) == 16) {
            i2 += o.d(6, this.l);
        }
        if ((this.f & 32) == 32) {
            i2 += o.b(7, this.m);
        }
        for (int i10 = 0; i10 < this.n.size(); i10++) {
            i2 += o.d(8, (C1252b) this.n.get(i10));
        }
        int i11 = 0;
        for (int i12 = 0; i12 < this.f4784o.size(); i12++) {
            i11 += o.c(((Integer) this.f4784o.get(i12)).intValue());
        }
        int size = this.e.size() + h() + (this.f4784o.size() * 2) + i2 + i11;
        this.q = size;
        return size;
    }

    public final C1262l b() {
        return S.f();
    }

    public final C1262l c() {
        S f5 = S.f();
        f5.g(this);
        return f5;
    }

    public final void d(o oVar) {
        a();
        W0 w02 = new W0((C1264n) this);
        if ((this.f & 1) == 1) {
            oVar.m(1, this.g);
        }
        if ((this.f & 2) == 2) {
            oVar.m(2, this.f4781h);
        }
        for (int i2 = 0; i2 < this.f4782i.size(); i2++) {
            oVar.o(3, (C1252b) this.f4782i.get(i2));
        }
        if ((this.f & 4) == 4) {
            oVar.o(4, this.f4783j);
        }
        if ((this.f & 8) == 8) {
            oVar.m(5, this.k);
        }
        if ((this.f & 16) == 16) {
            oVar.o(6, this.l);
        }
        if ((this.f & 32) == 32) {
            oVar.m(7, this.m);
        }
        for (int i7 = 0; i7 < this.n.size(); i7++) {
            oVar.o(8, (C1252b) this.n.get(i7));
        }
        for (int i8 = 0; i8 < this.f4784o.size(); i8++) {
            oVar.m(31, ((Integer) this.f4784o.get(i8)).intValue());
        }
        w02.A0(200, oVar);
        oVar.r(this.e);
    }

    public final C1252b getDefaultInstanceForType() {
        return r;
    }

    public final boolean isInitialized() {
        byte b = this.f4785p;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.f & 2) == 2) {
            for (int i2 = 0; i2 < this.f4782i.size(); i2++) {
                if (!((W) this.f4782i.get(i2)).isInitialized()) {
                    this.f4785p = 0;
                    return false;
                }
            }
            if ((this.f & 4) == 4 && !this.f4783j.isInitialized()) {
                this.f4785p = 0;
                return false;
            } else if ((this.f & 16) != 16 || this.l.isInitialized()) {
                for (int i7 = 0; i7 < this.n.size(); i7++) {
                    if (!((C1154g) this.n.get(i7)).isInitialized()) {
                        this.f4785p = 0;
                        return false;
                    }
                }
                if (!g()) {
                    this.f4785p = 0;
                    return false;
                }
                this.f4785p = 1;
                return true;
            } else {
                this.f4785p = 0;
                return false;
            }
        } else {
            this.f4785p = 0;
            return false;
        }
    }

    public T() {
        this.f4785p = -1;
        this.q = -1;
        this.e = C1255e.d;
    }

    public T(C1256f fVar, C1258h hVar) {
        this.f4785p = -1;
        this.q = -1;
        this.g = 6;
        boolean z = false;
        this.f4781h = 0;
        List list = Collections.EMPTY_LIST;
        this.f4782i = list;
        Q q10 = Q.w;
        this.f4783j = q10;
        this.k = 0;
        this.l = q10;
        this.m = 0;
        this.n = list;
        this.f4784o = list;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        boolean z3 = false;
        while (!z) {
            try {
                int n3 = fVar.n();
                P p6 = null;
                switch (n3) {
                    case 0:
                        z = true;
                        break;
                    case 8:
                        this.f |= 1;
                        this.g = fVar.k();
                        break;
                    case 16:
                        this.f |= 2;
                        this.f4781h = fVar.k();
                        break;
                    case 26:
                        if (!(z3 & true)) {
                            this.f4782i = new ArrayList();
                            z3 |= true;
                        }
                        this.f4782i.add(fVar.g(W.q, hVar));
                        break;
                    case 34:
                        if ((this.f & 4) == 4) {
                            Q q11 = this.f4783j;
                            q11.getClass();
                            p6 = Q.p(q11);
                        }
                        Q q12 = (Q) fVar.g(Q.f4770x, hVar);
                        this.f4783j = q12;
                        if (p6 != null) {
                            p6.g(q12);
                            this.f4783j = p6.e();
                        }
                        this.f |= 4;
                        break;
                    case 40:
                        this.f |= 8;
                        this.k = fVar.k();
                        break;
                    case 50:
                        if ((this.f & 16) == 16) {
                            Q q13 = this.l;
                            q13.getClass();
                            p6 = Q.p(q13);
                        }
                        Q q14 = (Q) fVar.g(Q.f4770x, hVar);
                        this.l = q14;
                        if (p6 != null) {
                            p6.g(q14);
                            this.l = p6.e();
                        }
                        this.f |= 16;
                        break;
                    case 56:
                        this.f |= 32;
                        this.m = fVar.k();
                        break;
                    case 66:
                        if (!(z3 & true)) {
                            this.n = new ArrayList();
                            z3 |= true;
                        }
                        this.n.add(fVar.g(C1154g.k, hVar));
                        break;
                    case 248:
                        if (!(z3 & true)) {
                            this.f4784o = new ArrayList();
                            z3 |= true;
                        }
                        this.f4784o.add(Integer.valueOf(fVar.k()));
                        break;
                    case 250:
                        int d = fVar.d(fVar.k());
                        if (!(z3 & true) && fVar.b() > 0) {
                            this.f4784o = new ArrayList();
                            z3 |= true;
                        }
                        while (fVar.b() > 0) {
                            this.f4784o.add(Integer.valueOf(fVar.k()));
                        }
                        fVar.c(d);
                        break;
                    default:
                        if (m(fVar, j2, hVar, n3)) {
                            break;
                        }
                        z = true;
                        break;
                }
            } catch (u e7) {
                e7.d = this;
                throw e7;
            } catch (IOException e8) {
                u uVar = new u(e8.getMessage());
                uVar.d = this;
                throw uVar;
            } catch (Throwable th) {
                if (z3 & true) {
                    this.f4782i = Collections.unmodifiableList(this.f4782i);
                }
                if (z3 & true) {
                    this.n = Collections.unmodifiableList(this.n);
                }
                if (z3 & true) {
                    this.f4784o = Collections.unmodifiableList(this.f4784o);
                }
                try {
                    j2.i();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.e = dVar.f();
                    throw th2;
                }
                this.e = dVar.f();
                l();
                throw th;
            }
        }
        if (z3 & true) {
            this.f4782i = Collections.unmodifiableList(this.f4782i);
        }
        if (z3 & true) {
            this.n = Collections.unmodifiableList(this.n);
        }
        if (z3 & true) {
            this.f4784o = Collections.unmodifiableList(this.f4784o);
        }
        try {
            j2.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.e = dVar.f();
            throw th3;
        }
        this.e = dVar.f();
        l();
    }
}
