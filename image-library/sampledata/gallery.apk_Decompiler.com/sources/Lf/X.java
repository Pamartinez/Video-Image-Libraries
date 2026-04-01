package lf;

import B2.o;
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
import rf.C1267q;
import rf.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class X extends C1267q {

    /* renamed from: j  reason: collision with root package name */
    public static final X f4794j;
    public static final C1148a k = new C1148a(20);
    public final C1255e d;
    public int e;
    public List f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public byte f4795h;

    /* renamed from: i  reason: collision with root package name */
    public int f4796i;

    static {
        X x9 = new X();
        f4794j = x9;
        x9.f = Collections.EMPTY_LIST;
        x9.g = -1;
    }

    public X() {
        this.f4795h = -1;
        this.f4796i = -1;
        this.d = C1255e.d;
    }

    public static C1153f g(X x9) {
        C1153f g3 = C1153f.g();
        g3.k(x9);
        return g3;
    }

    public final int a() {
        int i2 = this.f4796i;
        if (i2 != -1) {
            return i2;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < this.f.size(); i8++) {
            i7 += o.d(1, (C1252b) this.f.get(i8));
        }
        if ((this.e & 1) == 1) {
            i7 += o.b(2, this.g);
        }
        int size = this.d.size() + i7;
        this.f4796i = size;
        return size;
    }

    public final C1262l b() {
        return C1153f.g();
    }

    public final C1262l c() {
        return g(this);
    }

    public final void d(o oVar) {
        a();
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            oVar.o(1, (C1252b) this.f.get(i2));
        }
        if ((this.e & 1) == 1) {
            oVar.m(2, this.g);
        }
        oVar.r(this.d);
    }

    public final C1153f h() {
        return g(this);
    }

    public final boolean isInitialized() {
        byte b = this.f4795h;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            if (!((Q) this.f.get(i2)).isInitialized()) {
                this.f4795h = 0;
                return false;
            }
        }
        this.f4795h = 1;
        return true;
    }

    public X(C1153f fVar) {
        this.f4795h = -1;
        this.f4796i = -1;
        this.d = fVar.d;
    }

    public X(C1256f fVar, C1258h hVar) {
        this.f4795h = -1;
        this.f4796i = -1;
        this.f = Collections.EMPTY_LIST;
        this.g = -1;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        boolean z = false;
        boolean z3 = false;
        while (!z) {
            try {
                int n = fVar.n();
                if (n != 0) {
                    if (n == 10) {
                        if (!z3) {
                            this.f = new ArrayList();
                            z3 = true;
                        }
                        this.f.add(fVar.g(Q.f4770x, hVar));
                    } else if (n == 16) {
                        this.e |= 1;
                        this.g = fVar.k();
                    } else if (fVar.q(n, j2)) {
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
                if (z3) {
                    this.f = Collections.unmodifiableList(this.f);
                }
                try {
                    j2.i();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.d = dVar.f();
                    throw th2;
                }
                this.d = dVar.f();
                throw th;
            }
        }
        if (z3) {
            this.f = Collections.unmodifiableList(this.f);
        }
        try {
            j2.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.d = dVar.f();
            throw th3;
        }
        this.d = dVar.f();
    }
}
