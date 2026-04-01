package of;

import B2.o;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lf.C1148a;
import rf.C1252b;
import rf.C1254d;
import rf.C1255e;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1267q;
import rf.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j extends C1267q {

    /* renamed from: j  reason: collision with root package name */
    public static final j f5005j;
    public static final C1148a k = new C1148a(27);
    public final C1255e d;
    public List e;
    public List f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public byte f5006h;

    /* renamed from: i  reason: collision with root package name */
    public int f5007i;

    static {
        j jVar = new j();
        f5005j = jVar;
        List list = Collections.EMPTY_LIST;
        jVar.e = list;
        jVar.f = list;
    }

    public j() {
        this.g = -1;
        this.f5006h = -1;
        this.f5007i = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2 = this.f5007i;
        if (i2 != -1) {
            return i2;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < this.e.size(); i8++) {
            i7 += o.d(1, (C1252b) this.e.get(i8));
        }
        int i10 = 0;
        for (int i11 = 0; i11 < this.f.size(); i11++) {
            i10 += o.c(((Integer) this.f.get(i11)).intValue());
        }
        int i12 = i7 + i10;
        if (!this.f.isEmpty()) {
            i12 = i12 + 1 + o.c(i10);
        }
        this.g = i10;
        int size = this.d.size() + i12;
        this.f5007i = size;
        return size;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [rf.l, of.f] */
    public final C1262l b() {
        ? lVar = new C1262l();
        List list = Collections.EMPTY_LIST;
        lVar.f = list;
        lVar.g = list;
        return lVar;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.l, of.f] */
    public final C1262l c() {
        ? lVar = new C1262l();
        List list = Collections.EMPTY_LIST;
        lVar.f = list;
        lVar.g = list;
        lVar.e(this);
        return lVar;
    }

    public final void d(o oVar) {
        a();
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            oVar.o(1, (C1252b) this.e.get(i2));
        }
        if (this.f.size() > 0) {
            oVar.v(42);
            oVar.v(this.g);
        }
        for (int i7 = 0; i7 < this.f.size(); i7++) {
            oVar.n(((Integer) this.f.get(i7)).intValue());
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        if (this.f5006h == 1) {
            return true;
        }
        this.f5006h = 1;
        return true;
    }

    public j(f fVar) {
        this.g = -1;
        this.f5006h = -1;
        this.f5007i = -1;
        this.d = fVar.d;
    }

    public j(C1256f fVar, C1258h hVar) {
        this.g = -1;
        this.f5006h = -1;
        this.f5007i = -1;
        List list = Collections.EMPTY_LIST;
        this.e = list;
        this.f = list;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        boolean z = false;
        boolean z3 = false;
        while (!z) {
            try {
                int n = fVar.n();
                if (n != 0) {
                    if (n == 10) {
                        if (!z3 || !true) {
                            this.e = new ArrayList();
                            z3 |= true;
                        }
                        this.e.add(fVar.g(i.q, hVar));
                    } else if (n == 40) {
                        if (!(z3 & true)) {
                            this.f = new ArrayList();
                            z3 |= true;
                        }
                        this.f.add(Integer.valueOf(fVar.k()));
                    } else if (n == 42) {
                        int d2 = fVar.d(fVar.k());
                        if (!(z3 & true) && fVar.b() > 0) {
                            this.f = new ArrayList();
                            z3 |= true;
                        }
                        while (fVar.b() > 0) {
                            this.f.add(Integer.valueOf(fVar.k()));
                        }
                        fVar.c(d2);
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
                if (z3 && true) {
                    this.e = Collections.unmodifiableList(this.e);
                }
                if (z3 & true) {
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
        if (z3 && true) {
            this.e = Collections.unmodifiableList(this.e);
        }
        if (z3 & true) {
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
