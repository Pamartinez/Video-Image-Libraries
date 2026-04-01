package z0;

import A0.a;
import A0.e;
import A0.i;
import C0.f;
import D0.c;
import E0.m;
import E0.n;
import E0.y;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.samsung.android.gallery.support.utils.MapUtil;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import x0.C0319A;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p implements n, a, k {

    /* renamed from: a  reason: collision with root package name */
    public final Path f2178a = new Path();
    public final Path b = new Path();

    /* renamed from: c  reason: collision with root package name */
    public final PathMeasure f2179c = new PathMeasure();
    public final float[] d = new float[2];
    public final String e;
    public final w f;
    public final m g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f2180h;

    /* renamed from: i  reason: collision with root package name */
    public final boolean f2181i;

    /* renamed from: j  reason: collision with root package name */
    public final i f2182j;
    public final e k;
    public final i l;
    public final i m;
    public final i n;

    /* renamed from: o  reason: collision with root package name */
    public final i f2183o;

    /* renamed from: p  reason: collision with root package name */
    public final i f2184p;
    public final c q = new c();
    public boolean r;

    public p(w wVar, F0.c cVar, n nVar) {
        this.f = wVar;
        this.e = nVar.f139a;
        m mVar = nVar.b;
        this.g = mVar;
        this.f2180h = nVar.f143j;
        this.f2181i = nVar.k;
        i C02 = nVar.f140c.p0();
        this.f2182j = C02;
        e p02 = nVar.d.p0();
        this.k = p02;
        i C03 = nVar.e.p0();
        this.l = C03;
        i C04 = nVar.g.p0();
        this.n = C04;
        i C05 = nVar.f142i.p0();
        this.f2184p = C05;
        m mVar2 = m.STAR;
        if (mVar == mVar2) {
            this.m = nVar.f.p0();
            this.f2183o = nVar.f141h.p0();
        } else {
            this.m = null;
            this.f2183o = null;
        }
        cVar.f(C02);
        cVar.f(p02);
        cVar.f(C03);
        cVar.f(C04);
        cVar.f(C05);
        if (mVar == mVar2) {
            cVar.f(this.m);
            cVar.f(this.f2183o);
        }
        C02.a(this);
        p02.a(this);
        C03.a(this);
        C04.a(this);
        C05.a(this);
        if (mVar == mVar2) {
            this.m.a(this);
            this.f2183o.a(this);
        }
    }

    public final void a() {
        this.r = false;
        this.f.invalidateSelf();
    }

    public final void b(List list, List list2) {
        int i2 = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) list;
            if (i2 < arrayList.size()) {
                c cVar = (c) arrayList.get(i2);
                if (cVar instanceof v) {
                    v vVar = (v) cVar;
                    if (vVar.f2200c == y.SIMULTANEOUSLY) {
                        this.q.d.add(vVar);
                        vVar.c(this);
                    }
                }
                i2++;
            } else {
                return;
            }
        }
    }

    public final void c(f fVar, int i2, ArrayList arrayList, f fVar2) {
        J0.f.f(fVar, i2, arrayList, fVar2, this);
    }

    public final void d(D0.e eVar, Object obj) {
        i iVar;
        i iVar2;
        if (obj == C0319A.r) {
            this.f2182j.k(eVar);
        } else if (obj == C0319A.s) {
            this.l.k(eVar);
        } else if (obj == C0319A.f2037i) {
            this.k.k(eVar);
        } else if (obj == C0319A.t && (iVar2 = this.m) != null) {
            iVar2.k(eVar);
        } else if (obj == C0319A.u) {
            this.n.k(eVar);
        } else if (obj == C0319A.v && (iVar = this.f2183o) != null) {
            iVar.k(eVar);
        } else if (obj == C0319A.w) {
            this.f2184p.k(eVar);
        }
    }

    public final String getName() {
        return this.e;
    }

    public final Path getPath() {
        boolean z;
        float f5;
        float f8;
        float f10;
        float f11;
        double d2;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19;
        float f20;
        float f21;
        double d3;
        int i2;
        boolean z3 = this.r;
        Path path = this.f2178a;
        if (z3) {
            return path;
        }
        path.reset();
        if (this.f2180h) {
            this.r = true;
            return path;
        }
        int i7 = o.f2177a[this.g.ordinal()];
        e eVar = this.k;
        i iVar = this.n;
        i iVar2 = this.f2184p;
        double d5 = MapUtil.INVALID_LOCATION;
        i iVar3 = this.l;
        i iVar4 = this.f2182j;
        if (i7 == 1) {
            z = true;
            e eVar2 = eVar;
            float floatValue = ((Float) iVar4.f()).floatValue();
            if (iVar3 != null) {
                d5 = (double) ((Float) iVar3.f()).floatValue();
            }
            double radians = Math.toRadians(d5 - 90.0d);
            double d6 = (double) floatValue;
            float f22 = (float) (6.283185307179586d / d6);
            if (this.f2181i) {
                f22 *= -1.0f;
            }
            float f23 = f22;
            float f24 = f23 / 2.0f;
            float f25 = floatValue - ((float) ((int) floatValue));
            int i8 = (f25 > 0.0f ? 1 : (f25 == 0.0f ? 0 : -1));
            if (i8 != 0) {
                f5 = 2.0f;
                radians += (double) ((1.0f - f25) * f24);
            } else {
                f5 = 2.0f;
            }
            float floatValue2 = ((Float) iVar.f()).floatValue();
            float floatValue3 = ((Float) this.m.f()).floatValue();
            i iVar5 = this.f2183o;
            if (iVar5 != null) {
                f8 = ((Float) iVar5.f()).floatValue() / 100.0f;
            } else {
                f8 = 0.0f;
            }
            if (iVar2 != null) {
                f10 = ((Float) iVar2.f()).floatValue() / 100.0f;
            } else {
                f10 = 0.0f;
            }
            if (i8 != 0) {
                float a7 = C0212a.a(floatValue2, floatValue3, f25, floatValue3);
                f11 = a7;
                double d7 = (double) a7;
                float cos = (float) (Math.cos(radians) * d7);
                float sin = (float) (Math.sin(radians) * d7);
                path.moveTo(cos, sin);
                d2 = radians + ((double) ((f23 * f25) / f5));
                f13 = cos;
                f12 = sin;
            } else {
                double d9 = (double) floatValue2;
                float cos2 = (float) (Math.cos(radians) * d9);
                float sin2 = (float) (Math.sin(radians) * d9);
                path.moveTo(cos2, sin2);
                d2 = radians + ((double) f24);
                f13 = cos2;
                f12 = sin2;
                f11 = 0.0f;
            }
            double ceil = Math.ceil(d6) * 2.0d;
            double d10 = d2;
            int i10 = 0;
            boolean z7 = false;
            while (true) {
                double d11 = (double) i10;
                if (d11 >= ceil) {
                    break;
                }
                if (z7) {
                    f14 = floatValue2;
                } else {
                    f14 = floatValue3;
                }
                int i11 = (f11 > 0.0f ? 1 : (f11 == 0.0f ? 0 : -1));
                if (i11 == 0 || d11 != ceil - 2.0d) {
                    f15 = f24;
                } else {
                    f15 = (f23 * f25) / f5;
                }
                if (i11 != 0 && d11 == ceil - 1.0d) {
                    f14 = f11;
                }
                double d12 = (double) f14;
                double d13 = d11;
                float cos3 = (float) (Math.cos(d10) * d12);
                float sin3 = (float) (Math.sin(d10) * d12);
                if (f8 == 0.0f && f10 == 0.0f) {
                    path.lineTo(cos3, sin3);
                    f16 = f25;
                    f17 = cos3;
                } else {
                    f16 = f25;
                    Path path2 = path;
                    double atan2 = (double) ((float) (Math.atan2((double) f12, (double) f13) - 1.5707963267948966d));
                    float cos4 = (float) Math.cos(atan2);
                    float sin4 = (float) Math.sin(atan2);
                    float f26 = f13;
                    float f27 = f12;
                    double atan22 = (double) ((float) (Math.atan2((double) sin3, (double) cos3) - 1.5707963267948966d));
                    float cos5 = (float) Math.cos(atan22);
                    float sin5 = (float) Math.sin(atan22);
                    if (z7) {
                        f18 = f8;
                    } else {
                        f18 = f10;
                    }
                    if (z7) {
                        f19 = f10;
                    } else {
                        f19 = f8;
                    }
                    if (z7) {
                        f20 = floatValue3;
                    } else {
                        f20 = floatValue2;
                    }
                    if (z7) {
                        f21 = floatValue2;
                    } else {
                        f21 = floatValue3;
                    }
                    float f28 = f20 * f18 * 0.47829f;
                    float f29 = cos4 * f28;
                    float f30 = f28 * sin4;
                    float f31 = f21 * f19 * 0.47829f;
                    float f32 = cos5 * f31;
                    float f33 = f31 * sin5;
                    if (i8 != 0) {
                        if (i10 == 0) {
                            f29 *= f16;
                            f30 *= f16;
                        } else if (d13 == ceil - 1.0d) {
                            f32 *= f16;
                            f33 *= f16;
                        }
                    }
                    f17 = cos3;
                    path = path2;
                    path.cubicTo(f26 - f29, f27 - f30, f32 + cos3, sin3 + f33, f17, sin3);
                }
                d10 += (double) f15;
                z7 = !z7;
                i10++;
                f13 = f17;
                f12 = sin3;
                f25 = f16;
                f5 = 2.0f;
            }
            PointF pointF = (PointF) eVar2.f();
            path.offset(pointF.x, pointF.y);
            path.close();
        } else if (i7 != 2) {
            z = true;
        } else {
            int floor = (int) Math.floor((double) ((Float) iVar4.f()).floatValue());
            if (iVar3 != null) {
                d5 = (double) ((Float) iVar3.f()).floatValue();
            }
            double radians2 = Math.toRadians(d5 - 90.0d);
            double d14 = (double) floor;
            float floatValue4 = ((Float) iVar2.f()).floatValue() / 100.0f;
            float floatValue5 = ((Float) iVar.f()).floatValue();
            double d15 = (double) floatValue5;
            z = true;
            e eVar3 = eVar;
            float cos6 = (float) (Math.cos(radians2) * d15);
            float sin6 = (float) (Math.sin(radians2) * d15);
            path.moveTo(cos6, sin6);
            double d16 = (double) ((float) (6.283185307179586d / d14));
            double ceil2 = Math.ceil(d14);
            double d17 = radians2 + d16;
            int i12 = 0;
            while (true) {
                double d18 = (double) i12;
                if (d18 >= ceil2) {
                    break;
                }
                double d19 = ceil2;
                float cos7 = (float) (Math.cos(d17) * d15);
                float sin7 = (float) (Math.sin(d17) * d15);
                if (floatValue4 != 0.0f) {
                    i2 = i12;
                    Path path3 = path;
                    d3 = d15;
                    double atan23 = (double) ((float) (Math.atan2((double) sin6, (double) cos6) - 1.5707963267948966d));
                    float sin8 = (float) Math.sin(atan23);
                    float cos8 = (float) Math.cos(atan23);
                    float f34 = sin8;
                    double atan24 = (double) ((float) (Math.atan2((double) sin7, (double) cos7) - 1.5707963267948966d));
                    float f35 = floatValue5 * floatValue4 * 0.25f;
                    float f36 = f35 * cos8;
                    float f37 = f35 * f34;
                    float cos9 = ((float) Math.cos(atan24)) * f35;
                    float sin9 = f35 * ((float) Math.sin(atan24));
                    if (d18 == d19 - 1.0d) {
                        Path path4 = this.b;
                        path4.reset();
                        path4.moveTo(cos6, sin6);
                        float f38 = cos6 - f36;
                        float f39 = sin6 - f37;
                        float f40 = cos7 + cos9;
                        float f41 = sin7 + sin9;
                        float f42 = cos7;
                        float f43 = sin7;
                        path4.cubicTo(f38, f39, f40, f41, f42, f43);
                        PathMeasure pathMeasure = this.f2179c;
                        pathMeasure.setPath(path4, false);
                        float[] fArr = this.d;
                        pathMeasure.getPosTan(pathMeasure.getLength() * 0.9999f, fArr, (float[]) null);
                        path = path3;
                        path.cubicTo(f38, f39, f40, f41, fArr[0], fArr[1]);
                        cos6 = f42;
                        sin6 = f43;
                    } else {
                        float f44 = cos7;
                        float f45 = sin7;
                        float f46 = f45 + sin9;
                        path = path3;
                        float f47 = f44;
                        sin6 = f45;
                        path.cubicTo(cos6 - f36, sin6 - f37, f44 + cos9, f46, f47, sin6);
                        cos6 = f47;
                    }
                } else {
                    i2 = i12;
                    d3 = d15;
                    cos6 = cos7;
                    sin6 = sin7;
                    if (d18 == d19 - 1.0d) {
                        i12 = i2 + 1;
                        ceil2 = d19;
                        d15 = d3;
                    } else {
                        path.lineTo(cos6, sin6);
                    }
                }
                d17 += d16;
                i12 = i2 + 1;
                ceil2 = d19;
                d15 = d3;
            }
            PointF pointF2 = (PointF) eVar3.f();
            path.offset(pointF2.x, pointF2.y);
            path.close();
        }
        path.close();
        this.q.c(path);
        this.r = z;
        return path;
    }
}
