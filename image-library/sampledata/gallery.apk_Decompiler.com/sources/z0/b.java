package z0;

import A0.a;
import A0.e;
import A0.f;
import A0.h;
import A0.i;
import A0.s;
import E0.y;
import F0.c;
import F0.l;
import J0.g;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.ArrayList;
import java.util.List;
import x0.C0319A;
import x0.C0323a;
import x0.C0326d;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class b implements a, k, e {

    /* renamed from: a  reason: collision with root package name */
    public final PathMeasure f2145a = new PathMeasure();
    public final Path b = new Path();

    /* renamed from: c  reason: collision with root package name */
    public final Path f2146c = new Path();
    public final RectF d = new RectF();
    public final w e;
    public final c f;
    public final ArrayList g = new ArrayList();

    /* renamed from: h  reason: collision with root package name */
    public final float[] f2147h;

    /* renamed from: i  reason: collision with root package name */
    public final l f2148i;

    /* renamed from: j  reason: collision with root package name */
    public final i f2149j;
    public final f k;
    public final ArrayList l;
    public final i m;
    public s n;

    /* renamed from: o  reason: collision with root package name */
    public e f2150o;

    /* renamed from: p  reason: collision with root package name */
    public float f2151p;
    public final h q;

    public b(w wVar, c cVar, Paint.Cap cap, Paint.Join join, float f5, D0.a aVar, D0.b bVar, ArrayList arrayList, D0.b bVar2) {
        l lVar = new l(1, 2);
        this.f2148i = lVar;
        this.f2151p = 0.0f;
        this.e = wVar;
        this.f = cVar;
        lVar.setStyle(Paint.Style.STROKE);
        lVar.setStrokeCap(cap);
        lVar.setStrokeJoin(join);
        lVar.setStrokeMiter(f5);
        this.k = (f) aVar.p0();
        this.f2149j = bVar.p0();
        if (bVar2 == null) {
            this.m = null;
        } else {
            this.m = bVar2.p0();
        }
        this.l = new ArrayList(arrayList.size());
        this.f2147h = new float[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            this.l.add(((D0.b) arrayList.get(i2)).p0());
        }
        cVar.f(this.k);
        cVar.f(this.f2149j);
        for (int i7 = 0; i7 < this.l.size(); i7++) {
            cVar.f((e) this.l.get(i7));
        }
        i iVar = this.m;
        if (iVar != null) {
            cVar.f(iVar);
        }
        this.k.a(this);
        this.f2149j.a(this);
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            ((e) this.l.get(i8)).a(this);
        }
        i iVar2 = this.m;
        if (iVar2 != null) {
            iVar2.a(this);
        }
        if (cVar.j() != null) {
            i C02 = ((D0.b) cVar.j().d).p0();
            this.f2150o = C02;
            C02.a(this);
            cVar.f(this.f2150o);
        }
        if (cVar.k() != null) {
            this.q = new h(this, cVar, cVar.k());
        }
    }

    public final void a() {
        this.e.invalidateSelf();
    }

    public final void b(List list, List list2) {
        ArrayList arrayList;
        ArrayList arrayList2 = (ArrayList) list;
        C0360a aVar = null;
        v vVar = null;
        for (int size = arrayList2.size() - 1; size >= 0; size--) {
            c cVar = (c) arrayList2.get(size);
            if (cVar instanceof v) {
                v vVar2 = (v) cVar;
                if (vVar2.f2200c == y.INDIVIDUALLY) {
                    vVar = vVar2;
                }
            }
        }
        if (vVar != null) {
            vVar.c(this);
        }
        int size2 = list2.size();
        while (true) {
            size2--;
            arrayList = this.g;
            if (size2 < 0) {
                break;
            }
            c cVar2 = (c) list2.get(size2);
            if (cVar2 instanceof v) {
                v vVar3 = (v) cVar2;
                if (vVar3.f2200c == y.INDIVIDUALLY) {
                    if (aVar != null) {
                        arrayList.add(aVar);
                    }
                    C0360a aVar2 = new C0360a(vVar3);
                    vVar3.c(this);
                    aVar = aVar2;
                }
            }
            if (cVar2 instanceof n) {
                if (aVar == null) {
                    aVar = new C0360a(vVar);
                }
                aVar.f2144a.add((n) cVar2);
            }
        }
        if (aVar != null) {
            arrayList.add(aVar);
        }
    }

    public final void c(C0.f fVar, int i2, ArrayList arrayList, C0.f fVar2) {
        J0.f.f(fVar, i2, arrayList, fVar2, this);
    }

    public void d(D0.e eVar, Object obj) {
        PointF pointF = C0319A.f2034a;
        if (obj == 4) {
            this.k.k(eVar);
        } else if (obj == C0319A.n) {
            this.f2149j.k(eVar);
        } else {
            ColorFilter colorFilter = C0319A.f2028F;
            c cVar = this.f;
            if (obj == colorFilter) {
                s sVar = this.n;
                if (sVar != null) {
                    cVar.n(sVar);
                }
                s sVar2 = new s(eVar, (Object) null);
                this.n = sVar2;
                sVar2.a(this);
                cVar.f(this.n);
            } else if (obj == C0319A.e) {
                e eVar2 = this.f2150o;
                if (eVar2 != null) {
                    eVar2.k(eVar);
                    return;
                }
                s sVar3 = new s(eVar, (Object) null);
                this.f2150o = sVar3;
                sVar3.a(this);
                cVar.f(this.f2150o);
            } else {
                h hVar = this.q;
                if (obj == 5 && hVar != null) {
                    hVar.f6c.k(eVar);
                } else if (obj == C0319A.B && hVar != null) {
                    hVar.c(eVar);
                } else if (obj == C0319A.f2027C && hVar != null) {
                    hVar.e.k(eVar);
                } else if (obj == C0319A.D && hVar != null) {
                    hVar.f.k(eVar);
                } else if (obj == C0319A.E && hVar != null) {
                    hVar.g.k(eVar);
                }
            }
        }
    }

    public final void e(RectF rectF, Matrix matrix, boolean z) {
        C0323a aVar = C0326d.f2049a;
        Path path = this.b;
        path.reset();
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.g;
            if (i2 < arrayList.size()) {
                C0360a aVar2 = (C0360a) arrayList.get(i2);
                for (int i7 = 0; i7 < aVar2.f2144a.size(); i7++) {
                    path.addPath(((n) aVar2.f2144a.get(i7)).getPath(), matrix);
                }
                i2++;
            } else {
                RectF rectF2 = this.d;
                path.computeBounds(rectF2, false);
                float l8 = this.f2149j.l() / 2.0f;
                rectF2.set(rectF2.left - l8, rectF2.top - l8, rectF2.right + l8, rectF2.bottom + l8);
                rectF.set(rectF2);
                rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
                C0323a aVar3 = C0326d.f2049a;
                return;
            }
        }
    }

    public void g(Canvas canvas, Matrix matrix, int i2) {
        float f5;
        float f8;
        float f10;
        float f11;
        float f12;
        BlurMaskFilter blurMaskFilter;
        float[] fArr;
        float f13;
        b bVar = this;
        Canvas canvas2 = canvas;
        Matrix matrix2 = matrix;
        C0323a aVar = C0326d.f2049a;
        float[] fArr2 = (float[]) g.d.get();
        boolean z = false;
        float f14 = 0.0f;
        fArr2[0] = 0.0f;
        boolean z3 = true;
        fArr2[1] = 0.0f;
        fArr2[2] = 37394.73f;
        fArr2[3] = 39575.234f;
        matrix2.mapPoints(fArr2);
        if (fArr2[0] != fArr2[2] && fArr2[1] != fArr2[3]) {
            float f15 = ((float) i2) / 255.0f;
            f fVar = bVar.k;
            float f16 = 100.0f;
            int l8 = (int) (((((float) fVar.l(fVar.b(), fVar.d())) * f15) / 100.0f) * 255.0f);
            PointF pointF = J0.f.f362a;
            int max = Math.max(0, Math.min(ScoverState.TYPE_NFC_SMART_COVER, l8));
            l lVar = bVar.f2148i;
            lVar.setAlpha(max);
            lVar.setStrokeWidth(g.d(matrix2) * bVar.f2149j.l());
            if (lVar.getStrokeWidth() > 0.0f) {
                ArrayList arrayList = bVar.l;
                float f17 = 1.0f;
                if (!arrayList.isEmpty()) {
                    float d2 = g.d(matrix2);
                    int i7 = 0;
                    while (true) {
                        int size = arrayList.size();
                        boolean z7 = z3;
                        fArr = bVar.f2147h;
                        if (i7 >= size) {
                            break;
                        }
                        float floatValue = ((Float) ((e) arrayList.get(i7)).f()).floatValue();
                        fArr[i7] = floatValue;
                        if (i7 % 2 == 0) {
                            if (floatValue < 1.0f) {
                                fArr[i7] = 1.0f;
                            }
                        } else if (floatValue < 0.1f) {
                            fArr[i7] = 0.1f;
                        }
                        fArr[i7] = fArr[i7] * d2;
                        i7++;
                        z3 = z7;
                    }
                    i iVar = bVar.m;
                    if (iVar == null) {
                        f13 = 0.0f;
                    } else {
                        f13 = ((Float) iVar.f()).floatValue() * d2;
                    }
                    lVar.setPathEffect(new DashPathEffect(fArr, f13));
                    C0323a aVar2 = C0326d.f2049a;
                }
                s sVar = bVar.n;
                if (sVar != null) {
                    lVar.setColorFilter((ColorFilter) sVar.f());
                }
                e eVar = bVar.f2150o;
                if (eVar != null) {
                    float floatValue2 = ((Float) eVar.f()).floatValue();
                    if (floatValue2 == 0.0f) {
                        lVar.setMaskFilter((MaskFilter) null);
                    } else if (floatValue2 != bVar.f2151p) {
                        c cVar = bVar.f;
                        if (cVar.f181A == floatValue2) {
                            blurMaskFilter = cVar.B;
                        } else {
                            BlurMaskFilter blurMaskFilter2 = new BlurMaskFilter(floatValue2 / 2.0f, BlurMaskFilter.Blur.NORMAL);
                            cVar.B = blurMaskFilter2;
                            cVar.f181A = floatValue2;
                            blurMaskFilter = blurMaskFilter2;
                        }
                        lVar.setMaskFilter(blurMaskFilter);
                    }
                    bVar.f2151p = floatValue2;
                }
                h hVar = bVar.q;
                if (hVar != null) {
                    hVar.b(lVar, matrix2, (int) (((f15 * ((float) l8)) / 255.0f) * 255.0f));
                }
                int i8 = 0;
                while (true) {
                    ArrayList arrayList2 = bVar.g;
                    if (i8 < arrayList2.size()) {
                        C0360a aVar3 = (C0360a) arrayList2.get(i8);
                        v vVar = aVar3.b;
                        ArrayList arrayList3 = aVar3.f2144a;
                        Path path = bVar.b;
                        if (vVar != null) {
                            C0323a aVar4 = C0326d.f2049a;
                            path.reset();
                            for (int size2 = arrayList3.size() - 1; size2 >= 0; size2--) {
                                path.addPath(((n) arrayList3.get(size2)).getPath(), matrix2);
                            }
                            float floatValue3 = ((Float) vVar.d.f()).floatValue() / f16;
                            float floatValue4 = ((Float) vVar.e.f()).floatValue() / f16;
                            float floatValue5 = ((Float) vVar.f.f()).floatValue() / 360.0f;
                            if (floatValue3 >= 0.01f || floatValue4 <= 0.99f) {
                                PathMeasure pathMeasure = bVar.f2145a;
                                pathMeasure.setPath(path, z);
                                float length = pathMeasure.getLength();
                                while (pathMeasure.nextContour()) {
                                    length += pathMeasure.getLength();
                                }
                                float f18 = floatValue5 * length;
                                float f19 = (floatValue3 * length) + f18;
                                float min = Math.min((floatValue4 * length) + f18, (f19 + length) - f17);
                                int size3 = arrayList3.size() - 1;
                                float f20 = f14;
                                while (size3 >= 0) {
                                    Path path2 = ((n) arrayList3.get(size3)).getPath();
                                    Path path3 = bVar.f2146c;
                                    path3.set(path2);
                                    path3.transform(matrix2);
                                    pathMeasure.setPath(path3, z);
                                    float length2 = pathMeasure.getLength();
                                    if (min > length) {
                                        float f21 = min - length;
                                        if (f21 < f20 + length2 && f20 < f21) {
                                            if (f19 > length) {
                                                f12 = (f19 - length) / length2;
                                            } else {
                                                f12 = 0.0f;
                                            }
                                            g.a(f12, Math.min(f21 / length2, f17), 0.0f, path3);
                                            canvas2.drawPath(path3, lVar);
                                            f8 = 0.0f;
                                            f20 += length2;
                                            size3--;
                                            bVar = this;
                                            f14 = f8;
                                            z = false;
                                            f17 = 1.0f;
                                        }
                                    }
                                    float f22 = f20 + length2;
                                    if (f22 >= f19 && f20 <= min) {
                                        if (f22 > min || f19 >= f20) {
                                            if (f19 < f20) {
                                                f10 = 0.0f;
                                            } else {
                                                f10 = (f19 - f20) / length2;
                                            }
                                            if (min > f22) {
                                                f11 = 1.0f;
                                            } else {
                                                f11 = (min - f20) / length2;
                                            }
                                            f8 = 0.0f;
                                            g.a(f10, f11, 0.0f, path3);
                                            canvas2.drawPath(path3, lVar);
                                            f20 += length2;
                                            size3--;
                                            bVar = this;
                                            f14 = f8;
                                            z = false;
                                            f17 = 1.0f;
                                        } else {
                                            canvas2.drawPath(path3, lVar);
                                        }
                                    }
                                    f8 = 0.0f;
                                    f20 += length2;
                                    size3--;
                                    bVar = this;
                                    f14 = f8;
                                    z = false;
                                    f17 = 1.0f;
                                }
                                f5 = f14;
                                C0323a aVar5 = C0326d.f2049a;
                            } else {
                                canvas2.drawPath(path, lVar);
                                C0323a aVar6 = C0326d.f2049a;
                                f5 = f14;
                            }
                        } else {
                            f5 = f14;
                            C0323a aVar7 = C0326d.f2049a;
                            path.reset();
                            for (int size4 = arrayList3.size() - 1; size4 >= 0; size4--) {
                                path.addPath(((n) arrayList3.get(size4)).getPath(), matrix2);
                            }
                            C0323a aVar8 = C0326d.f2049a;
                            canvas2.drawPath(path, lVar);
                        }
                        i8++;
                        bVar = this;
                        f14 = f5;
                        z = false;
                        f16 = 100.0f;
                        f17 = 1.0f;
                    } else {
                        C0323a aVar9 = C0326d.f2049a;
                        return;
                    }
                }
            }
        }
    }
}
