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
public final class Q extends C1264n {
    public static final Q w;

    /* renamed from: x  reason: collision with root package name */
    public static final C1148a f4770x = new C1148a(16);
    public final C1255e e;
    public int f;
    public List g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f4771h;

    /* renamed from: i  reason: collision with root package name */
    public int f4772i;

    /* renamed from: j  reason: collision with root package name */
    public Q f4773j;
    public int k;
    public int l;
    public int m;
    public int n;

    /* renamed from: o  reason: collision with root package name */
    public int f4774o;

    /* renamed from: p  reason: collision with root package name */
    public Q f4775p;
    public int q;
    public Q r;
    public int s;
    public int t;
    public byte u;
    public int v;

    static {
        Q q10 = new Q();
        w = q10;
        q10.o();
    }

    public Q(P p6) {
        super(p6);
        this.u = -1;
        this.v = -1;
        this.e = p6.d;
    }

    public static P p(Q q10) {
        P f5 = P.f();
        f5.g(q10);
        return f5;
    }

    public final int a() {
        int i2;
        int i7 = this.v;
        if (i7 != -1) {
            return i7;
        }
        if ((this.f & 4096) == 4096) {
            i2 = o.b(1, this.t);
        } else {
            i2 = 0;
        }
        for (int i8 = 0; i8 < this.g.size(); i8++) {
            i2 += o.d(2, (C1252b) this.g.get(i8));
        }
        if ((this.f & 1) == 1) {
            i2 += o.h(3) + 1;
        }
        if ((this.f & 2) == 2) {
            i2 += o.b(4, this.f4772i);
        }
        if ((this.f & 4) == 4) {
            i2 += o.d(5, this.f4773j);
        }
        if ((this.f & 16) == 16) {
            i2 += o.b(6, this.l);
        }
        if ((this.f & 32) == 32) {
            i2 += o.b(7, this.m);
        }
        if ((this.f & 8) == 8) {
            i2 += o.b(8, this.k);
        }
        if ((this.f & 64) == 64) {
            i2 += o.b(9, this.n);
        }
        if ((this.f & 256) == 256) {
            i2 += o.d(10, this.f4775p);
        }
        if ((this.f & 512) == 512) {
            i2 += o.b(11, this.q);
        }
        if ((this.f & 128) == 128) {
            i2 += o.b(12, this.f4774o);
        }
        if ((this.f & 1024) == 1024) {
            i2 += o.d(13, this.r);
        }
        if ((this.f & 2048) == 2048) {
            i2 += o.b(14, this.s);
        }
        int size = this.e.size() + h() + i2;
        this.v = size;
        return size;
    }

    public final C1262l b() {
        return P.f();
    }

    public final void d(o oVar) {
        a();
        W0 w02 = new W0((C1264n) this);
        if ((this.f & 4096) == 4096) {
            oVar.m(1, this.t);
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            oVar.o(2, (C1252b) this.g.get(i2));
        }
        if ((this.f & 1) == 1) {
            boolean z = this.f4771h;
            oVar.x(3, 0);
            oVar.q(z ? 1 : 0);
        }
        if ((this.f & 2) == 2) {
            oVar.m(4, this.f4772i);
        }
        if ((this.f & 4) == 4) {
            oVar.o(5, this.f4773j);
        }
        if ((this.f & 16) == 16) {
            oVar.m(6, this.l);
        }
        if ((this.f & 32) == 32) {
            oVar.m(7, this.m);
        }
        if ((this.f & 8) == 8) {
            oVar.m(8, this.k);
        }
        if ((this.f & 64) == 64) {
            oVar.m(9, this.n);
        }
        if ((this.f & 256) == 256) {
            oVar.o(10, this.f4775p);
        }
        if ((this.f & 512) == 512) {
            oVar.m(11, this.q);
        }
        if ((this.f & 128) == 128) {
            oVar.m(12, this.f4774o);
        }
        if ((this.f & 1024) == 1024) {
            oVar.o(13, this.r);
        }
        if ((this.f & 2048) == 2048) {
            oVar.m(14, this.s);
        }
        w02.A0(200, oVar);
        oVar.r(this.e);
    }

    public final C1252b getDefaultInstanceForType() {
        return w;
    }

    public final boolean isInitialized() {
        byte b = this.u;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            if (!((O) this.g.get(i2)).isInitialized()) {
                this.u = 0;
                return false;
            }
        }
        if ((this.f & 4) == 4 && !this.f4773j.isInitialized()) {
            this.u = 0;
            return false;
        } else if ((this.f & 256) == 256 && !this.f4775p.isInitialized()) {
            this.u = 0;
            return false;
        } else if ((this.f & 1024) == 1024 && !this.r.isInitialized()) {
            this.u = 0;
            return false;
        } else if (!g()) {
            this.u = 0;
            return false;
        } else {
            this.u = 1;
            return true;
        }
    }

    public final void o() {
        this.g = Collections.EMPTY_LIST;
        this.f4771h = false;
        this.f4772i = 0;
        Q q10 = w;
        this.f4773j = q10;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.f4774o = 0;
        this.f4775p = q10;
        this.q = 0;
        this.r = q10;
        this.s = 0;
        this.t = 0;
    }

    /* renamed from: q */
    public final P c() {
        return p(this);
    }

    public Q() {
        this.u = -1;
        this.v = -1;
        this.e = C1255e.d;
    }

    public Q(C1256f fVar, C1258h hVar) {
        this.u = -1;
        this.v = -1;
        o();
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        boolean z = false;
        boolean z3 = false;
        while (!z) {
            try {
                int n3 = fVar.n();
                C1148a aVar = f4770x;
                P p6 = null;
                switch (n3) {
                    case 0:
                        break;
                    case 8:
                        this.f |= 4096;
                        this.t = fVar.k();
                        continue;
                    case 18:
                        if (!z3) {
                            this.g = new ArrayList();
                            z3 = true;
                        }
                        this.g.add(fVar.g(O.l, hVar));
                        continue;
                    case 24:
                        this.f |= 1;
                        this.f4771h = fVar.l() != 0;
                        continue;
                    case 32:
                        this.f |= 2;
                        this.f4772i = fVar.k();
                        continue;
                    case 42:
                        if ((this.f & 4) == 4) {
                            Q q10 = this.f4773j;
                            q10.getClass();
                            p6 = p(q10);
                        }
                        Q q11 = (Q) fVar.g(aVar, hVar);
                        this.f4773j = q11;
                        if (p6 != null) {
                            p6.g(q11);
                            this.f4773j = p6.e();
                        }
                        this.f |= 4;
                        continue;
                    case 48:
                        this.f |= 16;
                        this.l = fVar.k();
                        continue;
                    case 56:
                        this.f |= 32;
                        this.m = fVar.k();
                        continue;
                    case 64:
                        this.f |= 8;
                        this.k = fVar.k();
                        continue;
                    case 72:
                        this.f |= 64;
                        this.n = fVar.k();
                        continue;
                    case 82:
                        if ((this.f & 256) == 256) {
                            Q q12 = this.f4775p;
                            q12.getClass();
                            p6 = p(q12);
                        }
                        Q q13 = (Q) fVar.g(aVar, hVar);
                        this.f4775p = q13;
                        if (p6 != null) {
                            p6.g(q13);
                            this.f4775p = p6.e();
                        }
                        this.f |= 256;
                        continue;
                    case 88:
                        this.f |= 512;
                        this.q = fVar.k();
                        continue;
                    case 96:
                        this.f |= 128;
                        this.f4774o = fVar.k();
                        continue;
                    case 106:
                        if ((this.f & 1024) == 1024) {
                            Q q14 = this.r;
                            q14.getClass();
                            p6 = p(q14);
                        }
                        Q q15 = (Q) fVar.g(aVar, hVar);
                        this.r = q15;
                        if (p6 != null) {
                            p6.g(q15);
                            this.r = p6.e();
                        }
                        this.f |= 1024;
                        continue;
                    case 112:
                        this.f |= 2048;
                        this.s = fVar.k();
                        continue;
                    default:
                        if (m(fVar, j2, hVar, n3)) {
                            continue;
                        }
                        break;
                }
                z = true;
            } catch (u e7) {
                e7.d = this;
                throw e7;
            } catch (IOException e8) {
                u uVar = new u(e8.getMessage());
                uVar.d = this;
                throw uVar;
            } catch (Throwable th) {
                if (z3) {
                    this.g = Collections.unmodifiableList(this.g);
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
        if (z3) {
            this.g = Collections.unmodifiableList(this.g);
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
