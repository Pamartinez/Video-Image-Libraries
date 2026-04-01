package lf;

import B2.o;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
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

/* renamed from: lf.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1159l extends C1264n {
    public static final C1159l l;
    public static final C1148a m = new C1148a(4);
    public final C1255e e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public List f4859h;

    /* renamed from: i  reason: collision with root package name */
    public List f4860i;

    /* renamed from: j  reason: collision with root package name */
    public byte f4861j;
    public int k;

    static {
        C1159l lVar = new C1159l();
        l = lVar;
        lVar.g = 6;
        List list = Collections.EMPTY_LIST;
        lVar.f4859h = list;
        lVar.f4860i = list;
    }

    public C1159l(C1158k kVar) {
        super(kVar);
        this.f4861j = -1;
        this.k = -1;
        this.e = kVar.d;
    }

    public final int a() {
        int i2;
        int i7 = this.k;
        if (i7 != -1) {
            return i7;
        }
        if ((this.f & 1) == 1) {
            i2 = o.b(1, this.g);
        } else {
            i2 = 0;
        }
        for (int i8 = 0; i8 < this.f4859h.size(); i8++) {
            i2 += o.d(2, (C1252b) this.f4859h.get(i8));
        }
        int i10 = 0;
        for (int i11 = 0; i11 < this.f4860i.size(); i11++) {
            i10 += o.c(((Integer) this.f4860i.get(i11)).intValue());
        }
        int size = this.e.size() + h() + (this.f4860i.size() * 2) + i2 + i10;
        this.k = size;
        return size;
    }

    public final C1262l b() {
        return C1158k.f();
    }

    public final C1262l c() {
        C1158k f5 = C1158k.f();
        f5.g(this);
        return f5;
    }

    public final void d(o oVar) {
        a();
        W0 w02 = new W0((C1264n) this);
        if ((this.f & 1) == 1) {
            oVar.m(1, this.g);
        }
        for (int i2 = 0; i2 < this.f4859h.size(); i2++) {
            oVar.o(2, (C1252b) this.f4859h.get(i2));
        }
        for (int i7 = 0; i7 < this.f4860i.size(); i7++) {
            oVar.m(31, ((Integer) this.f4860i.get(i7)).intValue());
        }
        w02.A0(ErrorCodeConvertor.CLOUD_UID_VALIDATION, oVar);
        oVar.r(this.e);
    }

    public final C1252b getDefaultInstanceForType() {
        return l;
    }

    public final boolean isInitialized() {
        byte b = this.f4861j;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        for (int i2 = 0; i2 < this.f4859h.size(); i2++) {
            if (!((Z) this.f4859h.get(i2)).isInitialized()) {
                this.f4861j = 0;
                return false;
            }
        }
        if (!g()) {
            this.f4861j = 0;
            return false;
        }
        this.f4861j = 1;
        return true;
    }

    public C1159l() {
        this.f4861j = -1;
        this.k = -1;
        this.e = C1255e.d;
    }

    public C1159l(C1256f fVar, C1258h hVar) {
        this.f4861j = -1;
        this.k = -1;
        this.g = 6;
        List list = Collections.EMPTY_LIST;
        this.f4859h = list;
        this.f4860i = list;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        boolean z = false;
        boolean z3 = false;
        while (!z) {
            try {
                int n = fVar.n();
                if (n != 0) {
                    if (n == 8) {
                        this.f |= 1;
                        this.g = fVar.k();
                    } else if (n == 18) {
                        if (!(z3 & true)) {
                            this.f4859h = new ArrayList();
                            z3 |= true;
                        }
                        this.f4859h.add(fVar.g(Z.f4801p, hVar));
                    } else if (n == 248) {
                        if (!(z3 & true)) {
                            this.f4860i = new ArrayList();
                            z3 |= true;
                        }
                        this.f4860i.add(Integer.valueOf(fVar.k()));
                    } else if (n == 250) {
                        int d = fVar.d(fVar.k());
                        if (!(z3 & true) && fVar.b() > 0) {
                            this.f4860i = new ArrayList();
                            z3 |= true;
                        }
                        while (fVar.b() > 0) {
                            this.f4860i.add(Integer.valueOf(fVar.k()));
                        }
                        fVar.c(d);
                    } else if (m(fVar, j2, hVar, n)) {
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
                    this.f4859h = Collections.unmodifiableList(this.f4859h);
                }
                if (z3 & true) {
                    this.f4860i = Collections.unmodifiableList(this.f4860i);
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
            this.f4859h = Collections.unmodifiableList(this.f4859h);
        }
        if (z3 & true) {
            this.f4860i = Collections.unmodifiableList(this.f4860i);
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
