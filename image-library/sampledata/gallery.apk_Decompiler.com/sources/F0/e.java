package F0;

import A0.i;
import A0.s;
import C0.f;
import D0.b;
import J0.g;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import x0.C0319A;
import x0.C0323a;
import x0.C0326d;
import x0.C0332j;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends c {
    public A0.e D;
    public final ArrayList E = new ArrayList();

    /* renamed from: F  reason: collision with root package name */
    public final RectF f192F = new RectF();

    /* renamed from: G  reason: collision with root package name */
    public final RectF f193G = new RectF();

    /* renamed from: H  reason: collision with root package name */
    public final Paint f194H = new Paint();

    /* renamed from: I  reason: collision with root package name */
    public float f195I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f196J = true;

    public e(w wVar, i iVar, List list, C0332j jVar) {
        super(wVar, iVar);
        c cVar;
        c cVar2;
        b bVar = iVar.s;
        if (bVar != null) {
            i C02 = bVar.p0();
            this.D = C02;
            f(C02);
            this.D.a(this);
        } else {
            this.D = null;
        }
        LongSparseArray longSparseArray = new LongSparseArray(jVar.f2060j.size());
        int size = list.size() - 1;
        c cVar3 = null;
        while (true) {
            if (size >= 0) {
                i iVar2 = (i) list.get(size);
                switch (b.f180a[iVar2.e.ordinal()]) {
                    case 1:
                        cVar2 = new k(wVar, iVar2, this, jVar);
                        break;
                    case 2:
                        cVar2 = new e(wVar, iVar2, (List) jVar.f2057c.get(iVar2.g), jVar);
                        break;
                    case 3:
                        cVar2 = new f(wVar, iVar2, 1);
                        break;
                    case 4:
                        cVar2 = new f(wVar, iVar2, 0);
                        break;
                    case 5:
                        cVar2 = new c(wVar, iVar2);
                        break;
                    case 6:
                        cVar2 = new o(wVar, iVar2);
                        break;
                    default:
                        J0.b.b("Unknown layer type " + iVar2.e);
                        cVar2 = null;
                        break;
                }
                if (cVar2 != null) {
                    longSparseArray.put(cVar2.f189p.d, cVar2);
                    if (cVar3 != null) {
                        cVar3.s = cVar2;
                        cVar3 = null;
                    } else {
                        this.E.add(0, cVar2);
                        int i2 = d.f191a[iVar2.u.ordinal()];
                        if (i2 == 1 || i2 == 2) {
                            cVar3 = cVar2;
                        }
                    }
                }
                size--;
            } else {
                for (int i7 = 0; i7 < longSparseArray.size(); i7++) {
                    c cVar4 = (c) longSparseArray.get(longSparseArray.keyAt(i7));
                    if (!(cVar4 == null || (cVar = (c) longSparseArray.get(cVar4.f189p.f)) == null)) {
                        cVar4.t = cVar;
                    }
                }
                return;
            }
        }
    }

    public final void d(D0.e eVar, Object obj) {
        super.d(eVar, obj);
        if (obj == C0319A.z) {
            s sVar = new s(eVar, (Object) null);
            this.D = sVar;
            sVar.a(this);
            f(this.D);
        }
    }

    public final void e(RectF rectF, Matrix matrix, boolean z) {
        super.e(rectF, matrix, z);
        ArrayList arrayList = this.E;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            RectF rectF2 = this.f192F;
            rectF2.set(0.0f, 0.0f, 0.0f, 0.0f);
            ((c) arrayList.get(size)).e(rectF2, this.n, true);
            rectF.union(rectF2);
        }
    }

    public final void i(Canvas canvas, Matrix matrix, int i2) {
        boolean z;
        boolean z3;
        C0323a aVar = C0326d.f2049a;
        i iVar = this.f189p;
        float f = iVar.f208o;
        float f5 = iVar.f209p;
        RectF rectF = this.f193G;
        rectF.set(0.0f, 0.0f, f, f5);
        matrix.mapRect(rectF);
        boolean z7 = this.f188o.y;
        ArrayList arrayList = this.E;
        if (!z7 || arrayList.size() <= 1 || i2 == 255) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            Paint paint = this.f194H;
            paint.setAlpha(i2);
            g.f(canvas, rectF, paint);
        } else {
            canvas.save();
        }
        if (z) {
            i2 = 255;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if ((this.f196J || !"__container".equals(iVar.f204c)) && !rectF.isEmpty()) {
                z3 = canvas.clipRect(rectF);
            } else {
                z3 = true;
            }
            if (z3) {
                ((c) arrayList.get(size)).g(canvas, matrix, i2);
            }
        }
        canvas.restore();
        C0323a aVar2 = C0326d.f2049a;
    }

    public final void o(f fVar, int i2, ArrayList arrayList, f fVar2) {
        int i7 = 0;
        while (true) {
            ArrayList arrayList2 = this.E;
            if (i7 < arrayList2.size()) {
                ((c) arrayList2.get(i7)).c(fVar, i2, arrayList, fVar2);
                i7++;
            } else {
                return;
            }
        }
    }

    public final void p(boolean z) {
        super.p(z);
        Iterator it = this.E.iterator();
        while (it.hasNext()) {
            ((c) it.next()).p(z);
        }
    }

    public final void q(float f) {
        C0323a aVar = C0326d.f2049a;
        this.f195I = f;
        super.q(f);
        A0.e eVar = this.D;
        i iVar = this.f189p;
        if (eVar != null) {
            f = ((((Float) this.D.f()).floatValue() * iVar.b.n) - iVar.b.l) / (this.f188o.d.c() + 0.01f);
        }
        if (this.D == null) {
            f -= iVar.n / iVar.b.c();
        }
        if (iVar.m != 0.0f && !"__container".equals(iVar.f204c)) {
            f /= iVar.m;
        }
        ArrayList arrayList = this.E;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((c) arrayList.get(size)).q(f);
        }
        C0323a aVar2 = C0326d.f2049a;
    }
}
