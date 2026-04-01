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

/* renamed from: lf.g  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1154g extends C1267q {

    /* renamed from: j  reason: collision with root package name */
    public static final C1154g f4829j;
    public static final C1148a k = new C1148a(0);
    public final C1255e d;
    public int e;
    public int f;
    public List g;

    /* renamed from: h  reason: collision with root package name */
    public byte f4830h;

    /* renamed from: i  reason: collision with root package name */
    public int f4831i;

    static {
        C1154g gVar = new C1154g();
        f4829j = gVar;
        gVar.f = 0;
        gVar.g = Collections.EMPTY_LIST;
    }

    public C1154g() {
        this.f4830h = -1;
        this.f4831i = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2;
        int i7 = this.f4831i;
        if (i7 != -1) {
            return i7;
        }
        if ((this.e & 1) == 1) {
            i2 = o.b(1, this.f);
        } else {
            i2 = 0;
        }
        for (int i8 = 0; i8 < this.g.size(); i8++) {
            i2 += o.d(2, (C1252b) this.g.get(i8));
        }
        int size = this.d.size() + i2;
        this.f4831i = size;
        return size;
    }

    public final C1262l b() {
        C1153f fVar = new C1153f(0);
        fVar.g = Collections.EMPTY_LIST;
        return fVar;
    }

    public final C1262l c() {
        C1153f fVar = new C1153f(0);
        fVar.g = Collections.EMPTY_LIST;
        fVar.j(this);
        return fVar;
    }

    public final void d(o oVar) {
        a();
        if ((this.e & 1) == 1) {
            oVar.m(1, this.f);
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            oVar.o(2, (C1252b) this.g.get(i2));
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        byte b = this.f4830h;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.e & 1) == 1) {
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                if (!((C1152e) this.g.get(i2)).isInitialized()) {
                    this.f4830h = 0;
                    return false;
                }
            }
            this.f4830h = 1;
            return true;
        }
        this.f4830h = 0;
        return false;
    }

    public C1154g(C1153f fVar) {
        this.f4830h = -1;
        this.f4831i = -1;
        this.d = fVar.d;
    }

    public C1154g(C1256f fVar, C1258h hVar) {
        this.f4830h = -1;
        this.f4831i = -1;
        boolean z = false;
        this.f = 0;
        this.g = Collections.EMPTY_LIST;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        boolean z3 = false;
        while (!z) {
            try {
                int n = fVar.n();
                if (n != 0) {
                    if (n == 8) {
                        this.e |= 1;
                        this.f = fVar.k();
                    } else if (n == 18) {
                        if (!(z3 & true)) {
                            this.g = new ArrayList();
                            z3 = true;
                        }
                        this.g.add(fVar.g(C1152e.k, hVar));
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
                if (z3 & true) {
                    this.g = Collections.unmodifiableList(this.g);
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
        if (z3 & true) {
            this.g = Collections.unmodifiableList(this.g);
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
