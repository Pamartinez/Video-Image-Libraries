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
public final class K extends C1267q {

    /* renamed from: h  reason: collision with root package name */
    public static final K f4757h;

    /* renamed from: i  reason: collision with root package name */
    public static final C1148a f4758i = new C1148a(13);
    public final C1255e d;
    public List e;
    public byte f;
    public int g;

    static {
        K k = new K();
        f4757h = k;
        k.e = Collections.EMPTY_LIST;
    }

    public K() {
        this.f = -1;
        this.g = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2 = this.g;
        if (i2 != -1) {
            return i2;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < this.e.size(); i8++) {
            i7 += o.d(1, (C1252b) this.e.get(i8));
        }
        int size = this.d.size() + i7;
        this.g = size;
        return size;
    }

    public final C1262l b() {
        C1160m mVar = new C1160m(1);
        mVar.g = Collections.EMPTY_LIST;
        return mVar;
    }

    public final C1262l c() {
        C1160m mVar = new C1160m(1);
        mVar.g = Collections.EMPTY_LIST;
        mVar.j(this);
        return mVar;
    }

    public final void d(o oVar) {
        a();
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            oVar.o(1, (C1252b) this.e.get(i2));
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        byte b = this.f;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            if (!((J) this.e.get(i2)).isInitialized()) {
                this.f = 0;
                return false;
            }
        }
        this.f = 1;
        return true;
    }

    public K(C1160m mVar) {
        this.f = -1;
        this.g = -1;
        this.d = mVar.d;
    }

    public K(C1256f fVar, C1258h hVar) {
        this.f = -1;
        this.g = -1;
        this.e = Collections.EMPTY_LIST;
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
                            this.e = new ArrayList();
                            z3 = true;
                        }
                        this.e.add(fVar.g(J.l, hVar));
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
                    this.e = Collections.unmodifiableList(this.e);
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
            this.e = Collections.unmodifiableList(this.e);
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
