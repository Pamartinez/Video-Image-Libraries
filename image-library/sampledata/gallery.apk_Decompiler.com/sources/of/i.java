package of;

import B2.o;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lf.C1148a;
import rf.C1254d;
import rf.C1255e;
import rf.C1256f;
import rf.C1262l;
import rf.C1267q;
import rf.u;
import rf.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends C1267q {

    /* renamed from: p  reason: collision with root package name */
    public static final i f5000p;
    public static final C1148a q = new C1148a(28);
    public final C1255e d;
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public Object f5001h;

    /* renamed from: i  reason: collision with root package name */
    public h f5002i;

    /* renamed from: j  reason: collision with root package name */
    public List f5003j;
    public int k;
    public List l;
    public int m;
    public byte n;

    /* renamed from: o  reason: collision with root package name */
    public int f5004o;

    static {
        i iVar = new i();
        f5000p = iVar;
        iVar.f = 1;
        iVar.g = 0;
        iVar.f5001h = "";
        iVar.f5002i = h.NONE;
        List list = Collections.EMPTY_LIST;
        iVar.f5003j = list;
        iVar.l = list;
    }

    public i() {
        this.k = -1;
        this.m = -1;
        this.n = -1;
        this.f5004o = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2;
        C1255e eVar;
        int i7 = this.f5004o;
        if (i7 != -1) {
            return i7;
        }
        if ((this.e & 1) == 1) {
            i2 = o.b(1, this.f);
        } else {
            i2 = 0;
        }
        if ((this.e & 2) == 2) {
            i2 += o.b(2, this.g);
        }
        if ((this.e & 8) == 8) {
            i2 += o.a(3, this.f5002i.a());
        }
        int i8 = 0;
        for (int i10 = 0; i10 < this.f5003j.size(); i10++) {
            i8 += o.c(((Integer) this.f5003j.get(i10)).intValue());
        }
        int i11 = i2 + i8;
        if (!this.f5003j.isEmpty()) {
            i11 = i11 + 1 + o.c(i8);
        }
        this.k = i8;
        int i12 = 0;
        for (int i13 = 0; i13 < this.l.size(); i13++) {
            i12 += o.c(((Integer) this.l.get(i13)).intValue());
        }
        int i14 = i11 + i12;
        if (!this.l.isEmpty()) {
            i14 = i14 + 1 + o.c(i12);
        }
        this.m = i12;
        if ((this.e & 4) == 4) {
            Object obj = this.f5001h;
            if (obj instanceof String) {
                try {
                    eVar = new x(((String) obj).getBytes("UTF-8"));
                    this.f5001h = eVar;
                } catch (UnsupportedEncodingException e7) {
                    throw new RuntimeException("UTF-8 not supported?", e7);
                }
            } else {
                eVar = (C1255e) obj;
            }
            i14 += eVar.size() + o.f(eVar.size()) + o.h(6);
        }
        int size = this.d.size() + i14;
        this.f5004o = size;
        return size;
    }

    public final C1262l b() {
        return g.e();
    }

    public final C1262l c() {
        g e7 = g.e();
        e7.f(this);
        return e7;
    }

    public final void d(o oVar) {
        C1255e eVar;
        a();
        if ((this.e & 1) == 1) {
            oVar.m(1, this.f);
        }
        if ((this.e & 2) == 2) {
            oVar.m(2, this.g);
        }
        if ((this.e & 8) == 8) {
            oVar.l(3, this.f5002i.a());
        }
        if (this.f5003j.size() > 0) {
            oVar.v(34);
            oVar.v(this.k);
        }
        for (int i2 = 0; i2 < this.f5003j.size(); i2++) {
            oVar.n(((Integer) this.f5003j.get(i2)).intValue());
        }
        if (this.l.size() > 0) {
            oVar.v(42);
            oVar.v(this.m);
        }
        for (int i7 = 0; i7 < this.l.size(); i7++) {
            oVar.n(((Integer) this.l.get(i7)).intValue());
        }
        if ((this.e & 4) == 4) {
            Object obj = this.f5001h;
            if (obj instanceof String) {
                try {
                    eVar = new x(((String) obj).getBytes("UTF-8"));
                    this.f5001h = eVar;
                } catch (UnsupportedEncodingException e7) {
                    throw new RuntimeException("UTF-8 not supported?", e7);
                }
            } else {
                eVar = (C1255e) obj;
            }
            oVar.x(6, 2);
            oVar.v(eVar.size());
            oVar.r(eVar);
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        if (this.n == 1) {
            return true;
        }
        this.n = 1;
        return true;
    }

    public i(g gVar) {
        this.k = -1;
        this.m = -1;
        this.n = -1;
        this.f5004o = -1;
        this.d = gVar.d;
    }

    public i(C1256f fVar) {
        h hVar;
        this.k = -1;
        this.m = -1;
        this.n = -1;
        this.f5004o = -1;
        this.f = 1;
        boolean z = false;
        this.g = 0;
        this.f5001h = "";
        this.f5002i = h.NONE;
        List list = Collections.EMPTY_LIST;
        this.f5003j = list;
        this.l = list;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        boolean z3 = false;
        while (!z) {
            try {
                int n3 = fVar.n();
                if (n3 != 0) {
                    if (n3 == 8) {
                        this.e |= 1;
                        this.f = fVar.k();
                    } else if (n3 == 16) {
                        this.e |= 2;
                        this.g = fVar.k();
                    } else if (n3 == 24) {
                        int k10 = fVar.k();
                        if (k10 == 0) {
                            hVar = h.NONE;
                        } else if (k10 == 1) {
                            hVar = h.INTERNAL_TO_CLASS_ID;
                        } else if (k10 != 2) {
                            hVar = null;
                        } else {
                            hVar = h.DESC_TO_CLASS_ID;
                        }
                        if (hVar == null) {
                            j2.v(n3);
                            j2.v(k10);
                        } else {
                            this.e |= 8;
                            this.f5002i = hVar;
                        }
                    } else if (n3 == 32) {
                        if (!(z3 & true)) {
                            this.f5003j = new ArrayList();
                            z3 |= true;
                        }
                        this.f5003j.add(Integer.valueOf(fVar.k()));
                    } else if (n3 == 34) {
                        int d2 = fVar.d(fVar.k());
                        if (!(z3 & true) && fVar.b() > 0) {
                            this.f5003j = new ArrayList();
                            z3 |= true;
                        }
                        while (fVar.b() > 0) {
                            this.f5003j.add(Integer.valueOf(fVar.k()));
                        }
                        fVar.c(d2);
                    } else if (n3 == 40) {
                        if (!(z3 & true)) {
                            this.l = new ArrayList();
                            z3 |= true;
                        }
                        this.l.add(Integer.valueOf(fVar.k()));
                    } else if (n3 == 42) {
                        int d3 = fVar.d(fVar.k());
                        if (!(z3 & true) && fVar.b() > 0) {
                            this.l = new ArrayList();
                            z3 |= true;
                        }
                        while (fVar.b() > 0) {
                            this.l.add(Integer.valueOf(fVar.k()));
                        }
                        fVar.c(d3);
                    } else if (n3 == 50) {
                        x e7 = fVar.e();
                        this.e |= 4;
                        this.f5001h = e7;
                    } else if (fVar.q(n3, j2)) {
                    }
                }
                z = true;
            } catch (u e8) {
                e8.d = this;
                throw e8;
            } catch (IOException e9) {
                u uVar = new u(e9.getMessage());
                uVar.d = this;
                throw uVar;
            } catch (Throwable th) {
                if (z3 & true) {
                    this.f5003j = Collections.unmodifiableList(this.f5003j);
                }
                if (z3 & true) {
                    this.l = Collections.unmodifiableList(this.l);
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
            this.f5003j = Collections.unmodifiableList(this.f5003j);
        }
        if (z3 & true) {
            this.l = Collections.unmodifiableList(this.l);
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
