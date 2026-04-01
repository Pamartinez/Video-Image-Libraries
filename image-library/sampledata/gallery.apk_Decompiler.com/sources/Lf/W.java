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
public final class W extends C1264n {

    /* renamed from: p  reason: collision with root package name */
    public static final W f4789p;
    public static final C1148a q = new C1148a(19);
    public final C1255e e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4790h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f4791i;

    /* renamed from: j  reason: collision with root package name */
    public V f4792j;
    public List k;
    public List l;
    public int m;
    public byte n;

    /* renamed from: o  reason: collision with root package name */
    public int f4793o;

    static {
        W w = new W();
        f4789p = w;
        w.g = 0;
        w.f4790h = 0;
        w.f4791i = false;
        w.f4792j = V.INV;
        List list = Collections.EMPTY_LIST;
        w.k = list;
        w.l = list;
    }

    public W(U u) {
        super(u);
        this.m = -1;
        this.n = -1;
        this.f4793o = -1;
        this.e = u.d;
    }

    public final int a() {
        int i2;
        int i7 = this.f4793o;
        if (i7 != -1) {
            return i7;
        }
        if ((this.f & 1) == 1) {
            i2 = o.b(1, this.g);
        } else {
            i2 = 0;
        }
        if ((this.f & 2) == 2) {
            i2 += o.b(2, this.f4790h);
        }
        if ((this.f & 4) == 4) {
            i2 += o.h(3) + 1;
        }
        if ((this.f & 8) == 8) {
            i2 += o.a(4, this.f4792j.a());
        }
        for (int i8 = 0; i8 < this.k.size(); i8++) {
            i2 += o.d(5, (C1252b) this.k.get(i8));
        }
        int i10 = 0;
        for (int i11 = 0; i11 < this.l.size(); i11++) {
            i10 += o.c(((Integer) this.l.get(i11)).intValue());
        }
        int i12 = i2 + i10;
        if (!this.l.isEmpty()) {
            i12 = i12 + 1 + o.c(i10);
        }
        this.m = i10;
        int size = this.e.size() + h() + i12;
        this.f4793o = size;
        return size;
    }

    public final C1262l b() {
        return U.f();
    }

    public final C1262l c() {
        U f5 = U.f();
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
            oVar.m(2, this.f4790h);
        }
        if ((this.f & 4) == 4) {
            boolean z = this.f4791i;
            oVar.x(3, 0);
            oVar.q(z ? 1 : 0);
        }
        if ((this.f & 8) == 8) {
            oVar.l(4, this.f4792j.a());
        }
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            oVar.o(5, (C1252b) this.k.get(i2));
        }
        if (this.l.size() > 0) {
            oVar.v(50);
            oVar.v(this.m);
        }
        for (int i7 = 0; i7 < this.l.size(); i7++) {
            oVar.n(((Integer) this.l.get(i7)).intValue());
        }
        w02.A0(1000, oVar);
        oVar.r(this.e);
    }

    public final C1252b getDefaultInstanceForType() {
        return f4789p;
    }

    public final boolean isInitialized() {
        byte b = this.n;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        int i2 = this.f;
        if ((i2 & 1) != 1) {
            this.n = 0;
            return false;
        } else if ((i2 & 2) == 2) {
            for (int i7 = 0; i7 < this.k.size(); i7++) {
                if (!((Q) this.k.get(i7)).isInitialized()) {
                    this.n = 0;
                    return false;
                }
            }
            if (!g()) {
                this.n = 0;
                return false;
            }
            this.n = 1;
            return true;
        } else {
            this.n = 0;
            return false;
        }
    }

    public W() {
        this.m = -1;
        this.n = -1;
        this.f4793o = -1;
        this.e = C1255e.d;
    }

    public W(C1256f fVar, C1258h hVar) {
        V v;
        this.m = -1;
        this.n = -1;
        this.f4793o = -1;
        this.g = 0;
        this.f4790h = 0;
        this.f4791i = false;
        this.f4792j = V.INV;
        List list = Collections.EMPTY_LIST;
        this.k = list;
        this.l = list;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        boolean z = false;
        boolean z3 = false;
        while (!z) {
            try {
                int n3 = fVar.n();
                if (n3 != 0) {
                    if (n3 == 8) {
                        this.f |= 1;
                        this.g = fVar.k();
                    } else if (n3 == 16) {
                        this.f |= 2;
                        this.f4790h = fVar.k();
                    } else if (n3 == 24) {
                        this.f |= 4;
                        this.f4791i = fVar.l() != 0;
                    } else if (n3 == 32) {
                        int k10 = fVar.k();
                        if (k10 == 0) {
                            v = V.IN;
                        } else if (k10 == 1) {
                            v = V.OUT;
                        } else if (k10 != 2) {
                            v = null;
                        } else {
                            v = V.INV;
                        }
                        if (v == null) {
                            j2.v(n3);
                            j2.v(k10);
                        } else {
                            this.f |= 8;
                            this.f4792j = v;
                        }
                    } else if (n3 == 42) {
                        if (!(z3 & true)) {
                            this.k = new ArrayList();
                            z3 |= true;
                        }
                        this.k.add(fVar.g(Q.f4770x, hVar));
                    } else if (n3 == 48) {
                        if (!(z3 & true)) {
                            this.l = new ArrayList();
                            z3 |= true;
                        }
                        this.l.add(Integer.valueOf(fVar.k()));
                    } else if (n3 == 50) {
                        int d = fVar.d(fVar.k());
                        if (!(z3 & true) && fVar.b() > 0) {
                            this.l = new ArrayList();
                            z3 |= true;
                        }
                        while (fVar.b() > 0) {
                            this.l.add(Integer.valueOf(fVar.k()));
                        }
                        fVar.c(d);
                    } else if (m(fVar, j2, hVar, n3)) {
                    }
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
                if (z3 & true) {
                    this.k = Collections.unmodifiableList(this.k);
                }
                if (z3 & true) {
                    this.l = Collections.unmodifiableList(this.l);
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
            this.k = Collections.unmodifiableList(this.k);
        }
        if (z3 & true) {
            this.l = Collections.unmodifiableList(this.l);
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
