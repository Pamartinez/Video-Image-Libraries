package z0;

import A0.a;
import A0.e;
import A0.f;
import A0.h;
import A0.i;
import A0.s;
import D0.b;
import E0.r;
import F0.c;
import F0.l;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Path;
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
public final class g implements e, a, k {

    /* renamed from: a  reason: collision with root package name */
    public final Path f2160a;
    public final l b = new l(1, 2);

    /* renamed from: c  reason: collision with root package name */
    public final c f2161c;
    public final String d;
    public final boolean e;
    public final ArrayList f = new ArrayList();
    public final f g;

    /* renamed from: h  reason: collision with root package name */
    public final f f2162h;

    /* renamed from: i  reason: collision with root package name */
    public s f2163i;

    /* renamed from: j  reason: collision with root package name */
    public final w f2164j;
    public e k;
    public float l;
    public final h m;

    public g(w wVar, c cVar, r rVar) {
        Path path = new Path();
        this.f2160a = path;
        this.f2161c = cVar;
        String str = rVar.f150c;
        D0.a aVar = rVar.e;
        D0.a aVar2 = rVar.d;
        this.d = str;
        this.e = rVar.f;
        this.f2164j = wVar;
        if (cVar.j() != null) {
            i C02 = ((b) cVar.j().d).p0();
            this.k = C02;
            C02.a(this);
            cVar.f(this.k);
        }
        if (cVar.k() != null) {
            this.m = new h(this, cVar, cVar.k());
        }
        if (aVar2 != null) {
            path.setFillType(rVar.b);
            e p02 = aVar2.p0();
            this.g = (f) p02;
            p02.a(this);
            cVar.f(p02);
            e p03 = aVar.p0();
            this.f2162h = (f) p03;
            p03.a(this);
            cVar.f(p03);
            return;
        }
        this.g = null;
        this.f2162h = null;
    }

    public final void a() {
        this.f2164j.invalidateSelf();
    }

    public final void b(List list, List list2) {
        for (int i2 = 0; i2 < list2.size(); i2++) {
            c cVar = (c) list2.get(i2);
            if (cVar instanceof n) {
                this.f.add((n) cVar);
            }
        }
    }

    public final void c(C0.f fVar, int i2, ArrayList arrayList, C0.f fVar2) {
        J0.f.f(fVar, i2, arrayList, fVar2, this);
    }

    public final void d(D0.e eVar, Object obj) {
        PointF pointF = C0319A.f2034a;
        if (obj == 1) {
            this.g.k(eVar);
        } else if (obj == 4) {
            this.f2162h.k(eVar);
        } else {
            ColorFilter colorFilter = C0319A.f2028F;
            c cVar = this.f2161c;
            if (obj == colorFilter) {
                s sVar = this.f2163i;
                if (sVar != null) {
                    cVar.n(sVar);
                }
                s sVar2 = new s(eVar, (Object) null);
                this.f2163i = sVar2;
                sVar2.a(this);
                cVar.f(this.f2163i);
            } else if (obj == C0319A.e) {
                e eVar2 = this.k;
                if (eVar2 != null) {
                    eVar2.k(eVar);
                    return;
                }
                s sVar3 = new s(eVar, (Object) null);
                this.k = sVar3;
                sVar3.a(this);
                cVar.f(this.k);
            } else {
                h hVar = this.m;
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
        Path path = this.f2160a;
        path.reset();
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.f;
            if (i2 < arrayList.size()) {
                path.addPath(((n) arrayList.get(i2)).getPath(), matrix);
                i2++;
            } else {
                path.computeBounds(rectF, false);
                rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
                return;
            }
        }
    }

    public final void g(Canvas canvas, Matrix matrix, int i2) {
        BlurMaskFilter blurMaskFilter;
        if (!this.e) {
            C0323a aVar = C0326d.f2049a;
            f fVar = this.g;
            int l8 = fVar.l(fVar.b(), fVar.d());
            float f5 = ((float) i2) / 255.0f;
            int intValue = (int) (((((float) ((Integer) this.f2162h.f()).intValue()) * f5) / 100.0f) * 255.0f);
            PointF pointF = J0.f.f362a;
            int i7 = 0;
            int max = (l8 & 16777215) | (Math.max(0, Math.min(ScoverState.TYPE_NFC_SMART_COVER, intValue)) << 24);
            l lVar = this.b;
            lVar.setColor(max);
            s sVar = this.f2163i;
            if (sVar != null) {
                lVar.setColorFilter((ColorFilter) sVar.f());
            }
            e eVar = this.k;
            if (eVar != null) {
                float floatValue = ((Float) eVar.f()).floatValue();
                if (floatValue == 0.0f) {
                    lVar.setMaskFilter((MaskFilter) null);
                } else if (floatValue != this.l) {
                    c cVar = this.f2161c;
                    if (cVar.f181A == floatValue) {
                        blurMaskFilter = cVar.B;
                    } else {
                        BlurMaskFilter blurMaskFilter2 = new BlurMaskFilter(floatValue / 2.0f, BlurMaskFilter.Blur.NORMAL);
                        cVar.B = blurMaskFilter2;
                        cVar.f181A = floatValue;
                        blurMaskFilter = blurMaskFilter2;
                    }
                    lVar.setMaskFilter(blurMaskFilter);
                }
                this.l = floatValue;
            }
            h hVar = this.m;
            if (hVar != null) {
                D1.g gVar = J0.g.f363a;
                hVar.b(lVar, matrix, (int) (((f5 * ((float) intValue)) / 255.0f) * 255.0f));
            }
            Path path = this.f2160a;
            path.reset();
            while (true) {
                ArrayList arrayList = this.f;
                if (i7 < arrayList.size()) {
                    path.addPath(((n) arrayList.get(i7)).getPath(), matrix);
                    i7++;
                } else {
                    canvas.drawPath(path, lVar);
                    C0323a aVar2 = C0326d.f2049a;
                    return;
                }
            }
        }
    }

    public final String getName() {
        return this.d;
    }
}
