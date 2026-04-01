package lf;

import B2.o;
import ge.W0;
import java.io.IOException;
import rf.C1252b;
import rf.C1254d;
import rf.C1255e;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1263m;
import rf.C1264n;
import rf.u;

/* renamed from: lf.t  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1166t extends C1264n {

    /* renamed from: j  reason: collision with root package name */
    public static final C1166t f4870j;
    public static final C1148a k = new C1148a(7);
    public final C1255e e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public byte f4871h;

    /* renamed from: i  reason: collision with root package name */
    public int f4872i;

    static {
        C1166t tVar = new C1166t();
        f4870j = tVar;
        tVar.g = 0;
    }

    public C1166t(C1165s sVar) {
        super(sVar);
        this.f4871h = -1;
        this.f4872i = -1;
        this.e = sVar.d;
    }

    public final int a() {
        int i2;
        int i7 = this.f4872i;
        if (i7 != -1) {
            return i7;
        }
        if ((this.f & 1) == 1) {
            i2 = o.b(1, this.g);
        } else {
            i2 = 0;
        }
        int size = this.e.size() + h() + i2;
        this.f4872i = size;
        return size;
    }

    public final C1262l b() {
        return new C1263m();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.m, rf.l, lf.s] */
    public final C1262l c() {
        ? mVar = new C1263m();
        mVar.e(this);
        return mVar;
    }

    public final void d(o oVar) {
        a();
        W0 w02 = new W0((C1264n) this);
        if ((this.f & 1) == 1) {
            oVar.m(1, this.g);
        }
        w02.A0(200, oVar);
        oVar.r(this.e);
    }

    public final C1252b getDefaultInstanceForType() {
        return f4870j;
    }

    public final boolean isInitialized() {
        byte b = this.f4871h;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if (!g()) {
            this.f4871h = 0;
            return false;
        }
        this.f4871h = 1;
        return true;
    }

    public C1166t() {
        this.f4871h = -1;
        this.f4872i = -1;
        this.e = C1255e.d;
    }

    public C1166t(C1256f fVar, C1258h hVar) {
        this.f4871h = -1;
        this.f4872i = -1;
        boolean z = false;
        this.g = 0;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        while (!z) {
            try {
                int n = fVar.n();
                if (n != 0) {
                    if (n == 8) {
                        this.f |= 1;
                        this.g = fVar.k();
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
