package lf;

import B2.o;
import com.samsung.android.gallery.support.utils.MapUtil;
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

/* renamed from: lf.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1151d extends C1267q {
    public static final C1151d s;
    public static final C1148a t = new C1148a(2);
    public final C1255e d;
    public int e;
    public C1150c f;
    public long g;

    /* renamed from: h  reason: collision with root package name */
    public float f4814h;

    /* renamed from: i  reason: collision with root package name */
    public double f4815i;

    /* renamed from: j  reason: collision with root package name */
    public int f4816j;
    public int k;
    public int l;
    public C1154g m;
    public List n;

    /* renamed from: o  reason: collision with root package name */
    public int f4817o;

    /* renamed from: p  reason: collision with root package name */
    public int f4818p;
    public byte q;
    public int r;

    static {
        C1151d dVar = new C1151d();
        s = dVar;
        dVar.g();
    }

    public C1151d() {
        this.q = -1;
        this.r = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2;
        int i7 = this.r;
        if (i7 != -1) {
            return i7;
        }
        if ((this.e & 1) == 1) {
            i2 = o.a(1, this.f.a());
        } else {
            i2 = 0;
        }
        if ((this.e & 2) == 2) {
            long j2 = this.g;
            i2 += o.g((j2 >> 63) ^ (j2 << 1)) + o.h(2);
        }
        if ((this.e & 4) == 4) {
            i2 += o.h(3) + 4;
        }
        if ((this.e & 8) == 8) {
            i2 += o.h(4) + 8;
        }
        if ((this.e & 16) == 16) {
            i2 += o.b(5, this.f4816j);
        }
        if ((this.e & 32) == 32) {
            i2 += o.b(6, this.k);
        }
        if ((this.e & 64) == 64) {
            i2 += o.b(7, this.l);
        }
        if ((this.e & 128) == 128) {
            i2 += o.d(8, this.m);
        }
        for (int i8 = 0; i8 < this.n.size(); i8++) {
            i2 += o.d(9, (C1252b) this.n.get(i8));
        }
        if ((this.e & 512) == 512) {
            i2 += o.b(10, this.f4818p);
        }
        if ((this.e & 256) == 256) {
            i2 += o.b(11, this.f4817o);
        }
        int size = this.d.size() + i2;
        this.r = size;
        return size;
    }

    public final C1262l b() {
        return C1149b.e();
    }

    public final C1262l c() {
        C1149b e7 = C1149b.e();
        e7.f(this);
        return e7;
    }

    public final void d(o oVar) {
        a();
        if ((this.e & 1) == 1) {
            oVar.l(1, this.f.a());
        }
        if ((this.e & 2) == 2) {
            long j2 = this.g;
            oVar.x(2, 0);
            oVar.w((j2 >> 63) ^ (j2 << 1));
        }
        if ((this.e & 4) == 4) {
            float f5 = this.f4814h;
            oVar.x(3, 5);
            oVar.t(Float.floatToRawIntBits(f5));
        }
        if ((this.e & 8) == 8) {
            double d2 = this.f4815i;
            oVar.x(4, 1);
            oVar.u(Double.doubleToRawLongBits(d2));
        }
        if ((this.e & 16) == 16) {
            oVar.m(5, this.f4816j);
        }
        if ((this.e & 32) == 32) {
            oVar.m(6, this.k);
        }
        if ((this.e & 64) == 64) {
            oVar.m(7, this.l);
        }
        if ((this.e & 128) == 128) {
            oVar.o(8, this.m);
        }
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            oVar.o(9, (C1252b) this.n.get(i2));
        }
        if ((this.e & 512) == 512) {
            oVar.m(10, this.f4818p);
        }
        if ((this.e & 256) == 256) {
            oVar.m(11, this.f4817o);
        }
        oVar.r(this.d);
    }

    public final void g() {
        this.f = C1150c.BYTE;
        this.g = 0;
        this.f4814h = 0.0f;
        this.f4815i = MapUtil.INVALID_LOCATION;
        this.f4816j = 0;
        this.k = 0;
        this.l = 0;
        this.m = C1154g.f4829j;
        this.n = Collections.EMPTY_LIST;
        this.f4817o = 0;
        this.f4818p = 0;
    }

    public final boolean isInitialized() {
        byte b = this.q;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.e & 128) != 128 || this.m.isInitialized()) {
            for (int i2 = 0; i2 < this.n.size(); i2++) {
                if (!((C1151d) this.n.get(i2)).isInitialized()) {
                    this.q = 0;
                    return false;
                }
            }
            this.q = 1;
            return true;
        }
        this.q = 0;
        return false;
    }

    public C1151d(C1149b bVar) {
        this.q = -1;
        this.r = -1;
        this.d = bVar.d;
    }

    public C1151d(C1256f fVar, C1258h hVar) {
        C1153f fVar2;
        this.q = -1;
        this.r = -1;
        g();
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        boolean z = false;
        boolean z3 = false;
        while (!z) {
            try {
                int n3 = fVar.n();
                switch (n3) {
                    case 0:
                        z = true;
                        break;
                    case 8:
                        int k10 = fVar.k();
                        C1150c b = C1150c.b(k10);
                        if (b != null) {
                            this.e |= 1;
                            this.f = b;
                            break;
                        } else {
                            j2.v(n3);
                            j2.v(k10);
                            break;
                        }
                    case 16:
                        this.e |= 2;
                        long l8 = fVar.l();
                        this.g = (-(l8 & 1)) ^ (l8 >>> 1);
                        break;
                    case 29:
                        this.e |= 4;
                        this.f4814h = Float.intBitsToFloat(fVar.i());
                        break;
                    case 33:
                        this.e |= 8;
                        this.f4815i = Double.longBitsToDouble(fVar.j());
                        break;
                    case 40:
                        this.e |= 16;
                        this.f4816j = fVar.k();
                        break;
                    case 48:
                        this.e |= 32;
                        this.k = fVar.k();
                        break;
                    case 56:
                        this.e |= 64;
                        this.l = fVar.k();
                        break;
                    case 66:
                        if ((this.e & 128) == 128) {
                            C1154g gVar = this.m;
                            gVar.getClass();
                            fVar2 = new C1153f(0);
                            fVar2.g = Collections.EMPTY_LIST;
                            fVar2.j(gVar);
                        } else {
                            fVar2 = null;
                        }
                        C1154g gVar2 = (C1154g) fVar.g(C1154g.k, hVar);
                        this.m = gVar2;
                        if (fVar2 != null) {
                            fVar2.j(gVar2);
                            this.m = fVar2.e();
                        }
                        this.e |= 128;
                        break;
                    case 74:
                        if (!(z3 & true)) {
                            this.n = new ArrayList();
                            z3 = true;
                        }
                        this.n.add(fVar.g(t, hVar));
                        break;
                    case 80:
                        this.e |= 512;
                        this.f4818p = fVar.k();
                        break;
                    case 88:
                        this.e |= 256;
                        this.f4817o = fVar.k();
                        break;
                    default:
                        if (fVar.q(n3, j2)) {
                            break;
                        }
                        z = true;
                        break;
                }
            } catch (u e7) {
                e7.d = this;
                throw e7;
            } catch (IOException e8) {
                u uVar = new u(e8.getMessage());
                uVar.d = this;
                throw uVar;
            } catch (Throwable th) {
                if (z3 & true) {
                    this.n = Collections.unmodifiableList(this.n);
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
            this.n = Collections.unmodifiableList(this.n);
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
