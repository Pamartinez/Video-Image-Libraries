package lf;

import Dd.C0730a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1267q;
import rf.u;
import rf.y;

/* renamed from: lf.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1153f extends C1262l implements y {
    public final /* synthetic */ int e;
    public int f;
    public Object g;

    /* renamed from: h  reason: collision with root package name */
    public int f4828h;

    public /* synthetic */ C1153f(int i2) {
        this.e = i2;
    }

    public static C1153f g() {
        C1153f fVar = new C1153f(1);
        fVar.g = Collections.EMPTY_LIST;
        fVar.f4828h = -1;
        return fVar;
    }

    public final C1252b a() {
        switch (this.e) {
            case 0:
                C1154g e7 = e();
                if (e7.isInitialized()) {
                    return e7;
                }
                throw new C0730a();
            case 1:
                X f5 = f();
                if (f5.isInitialized()) {
                    return f5;
                }
                throw new C0730a();
            default:
                C1152e d = d();
                if (d.isInitialized()) {
                    return d;
                }
                throw new C0730a();
        }
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        C1154g gVar;
        C1154g gVar2;
        X x9;
        X x10;
        C1152e eVar;
        C1152e eVar2;
        switch (this.e) {
            case 0:
                gVar = null;
                try {
                    j((C1154g) C1154g.k.a(fVar, hVar));
                    return this;
                } catch (u e7) {
                    gVar2 = (C1154g) e7.d;
                    throw e7;
                } catch (Throwable th) {
                    th = th;
                    gVar = gVar2;
                    break;
                }
            case 1:
                x9 = null;
                try {
                    X.k.getClass();
                    k(new X(fVar, hVar));
                    return this;
                } catch (u e8) {
                    x10 = (X) e8.d;
                    throw e8;
                } catch (Throwable th2) {
                    th = th2;
                    x9 = x10;
                    break;
                }
            default:
                eVar = null;
                try {
                    C1152e.k.getClass();
                    h(new C1152e(fVar, hVar));
                    return this;
                } catch (u e9) {
                    eVar2 = (C1152e) e9.d;
                    throw e9;
                } catch (Throwable th3) {
                    th = th3;
                    eVar = eVar2;
                    break;
                }
        }
        if (gVar != null) {
            j(gVar);
        }
        throw th;
        if (x9 != null) {
            k(x9);
        }
        throw th;
        if (eVar != null) {
            h(eVar);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        switch (this.e) {
            case 0:
                j((C1154g) qVar);
                return this;
            case 1:
                k((X) qVar);
                return this;
            default:
                h((C1152e) qVar);
                return this;
        }
    }

    public final Object clone() {
        switch (this.e) {
            case 0:
                C1153f fVar = new C1153f(0);
                fVar.g = Collections.EMPTY_LIST;
                fVar.j(e());
                return fVar;
            case 1:
                C1153f g3 = g();
                g3.k(f());
                return g3;
            default:
                C1153f fVar2 = new C1153f(2);
                fVar2.g = C1151d.s;
                fVar2.h(d());
                return fVar2;
        }
    }

    public C1152e d() {
        C1152e eVar = new C1152e(this);
        int i2 = this.f;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        eVar.f = this.f4828h;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        eVar.g = (C1151d) this.g;
        eVar.e = i7;
        return eVar;
    }

    public C1154g e() {
        C1154g gVar = new C1154g(this);
        int i2 = this.f;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        gVar.f = this.f4828h;
        if ((i2 & 2) == 2) {
            this.g = Collections.unmodifiableList((List) this.g);
            this.f &= -3;
        }
        gVar.g = (List) this.g;
        gVar.e = i7;
        return gVar;
    }

    public X f() {
        X x9 = new X(this);
        int i2 = this.f;
        int i7 = 1;
        if ((i2 & 1) == 1) {
            this.g = Collections.unmodifiableList((List) this.g);
            this.f &= -2;
        }
        x9.f = (List) this.g;
        if ((i2 & 2) != 2) {
            i7 = 0;
        }
        x9.g = this.f4828h;
        x9.e = i7;
        return x9;
    }

    public void h(C1152e eVar) {
        C1151d dVar;
        if (eVar != C1152e.f4823j) {
            int i2 = eVar.e;
            if ((i2 & 1) == 1) {
                int i7 = eVar.f;
                this.f = 1 | this.f;
                this.f4828h = i7;
            }
            if ((i2 & 2) == 2) {
                C1151d dVar2 = eVar.g;
                if ((this.f & 2) != 2 || (dVar = (C1151d) this.g) == C1151d.s) {
                    this.g = dVar2;
                } else {
                    C1149b e7 = C1149b.e();
                    e7.f(dVar);
                    e7.f(dVar2);
                    this.g = e7.d();
                }
                this.f |= 2;
            }
            this.d = this.d.p(eVar.d);
        }
    }

    public void j(C1154g gVar) {
        if (gVar != C1154g.f4829j) {
            if ((gVar.e & 1) == 1) {
                int i2 = gVar.f;
                this.f = 1 | this.f;
                this.f4828h = i2;
            }
            if (!gVar.g.isEmpty()) {
                if (((List) this.g).isEmpty()) {
                    this.g = gVar.g;
                    this.f &= -3;
                } else {
                    if ((this.f & 2) != 2) {
                        this.g = new ArrayList((List) this.g);
                        this.f |= 2;
                    }
                    ((List) this.g).addAll(gVar.g);
                }
            }
            this.d = this.d.p(gVar.d);
        }
    }

    public void k(X x9) {
        if (x9 != X.f4794j) {
            if (!x9.f.isEmpty()) {
                if (((List) this.g).isEmpty()) {
                    this.g = x9.f;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) != 1) {
                        this.g = new ArrayList((List) this.g);
                        this.f |= 1;
                    }
                    ((List) this.g).addAll(x9.f);
                }
            }
            if ((x9.e & 1) == 1) {
                int i2 = x9.g;
                this.f |= 2;
                this.f4828h = i2;
            }
            this.d = this.d.p(x9.d);
        }
    }
}
