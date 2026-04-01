package F0;

import A0.a;
import A0.i;
import A0.l;
import A0.r;
import C0.f;
import C0.g;
import E0.h;
import E0.j;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import androidx.core.graphics.BlendModeCompat;
import androidx.core.graphics.PaintCompat;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import x0.C0323a;
import x0.C0326d;
import x0.w;
import z0.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class c implements e, a, g {

    /* renamed from: A  reason: collision with root package name */
    public float f181A;
    public BlurMaskFilter B;

    /* renamed from: C  reason: collision with root package name */
    public l f182C;

    /* renamed from: a  reason: collision with root package name */
    public final Path f183a = new Path();
    public final Matrix b = new Matrix();

    /* renamed from: c  reason: collision with root package name */
    public final Matrix f184c = new Matrix();
    public final l d;
    public final l e;
    public final l f;
    public final l g;

    /* renamed from: h  reason: collision with root package name */
    public final l f185h;

    /* renamed from: i  reason: collision with root package name */
    public final RectF f186i;

    /* renamed from: j  reason: collision with root package name */
    public final RectF f187j;
    public final RectF k;
    public final RectF l;
    public final RectF m;
    public final Matrix n;

    /* renamed from: o  reason: collision with root package name */
    public final w f188o;

    /* renamed from: p  reason: collision with root package name */
    public final i f189p;
    public final l q;
    public final i r;
    public c s;
    public c t;
    public List u;
    public final ArrayList v;
    public final r w;

    /* renamed from: x  reason: collision with root package name */
    public boolean f190x;
    public boolean y;
    public l z;

    /* JADX WARNING: type inference failed for: r9v4, types: [A0.i, A0.e] */
    public c(w wVar, i iVar) {
        boolean z3 = true;
        this.d = new l(1, 2);
        PorterDuff.Mode mode = PorterDuff.Mode.DST_IN;
        this.e = new l(mode);
        PorterDuff.Mode mode2 = PorterDuff.Mode.DST_OUT;
        this.f = new l(mode2);
        l lVar = new l(1, 2);
        this.g = lVar;
        PorterDuff.Mode mode3 = PorterDuff.Mode.CLEAR;
        l lVar2 = new l();
        lVar2.setXfermode(new PorterDuffXfermode(mode3));
        this.f185h = lVar2;
        this.f186i = new RectF();
        this.f187j = new RectF();
        this.k = new RectF();
        this.l = new RectF();
        this.m = new RectF();
        this.n = new Matrix();
        this.v = new ArrayList();
        this.f190x = true;
        this.f181A = 0.0f;
        this.f188o = wVar;
        this.f189p = iVar;
        List list = iVar.f205h;
        if (iVar.u == h.INVERT) {
            lVar.setXfermode(new PorterDuffXfermode(mode2));
        } else {
            lVar.setXfermode(new PorterDuffXfermode(mode));
        }
        D0.g gVar = iVar.f206i;
        gVar.getClass();
        r rVar = new r(gVar);
        this.w = rVar;
        rVar.b(this);
        if (list != null && !list.isEmpty()) {
            l lVar3 = new l(list);
            this.q = lVar3;
            Iterator it = ((ArrayList) lVar3.e).iterator();
            while (it.hasNext()) {
                ((A0.e) it.next()).a(this);
            }
            Iterator it2 = ((ArrayList) this.q.f).iterator();
            while (it2.hasNext()) {
                A0.e eVar = (A0.e) it2.next();
                f(eVar);
                eVar.a(this);
            }
        }
        i iVar2 = this.f189p;
        if (!iVar2.t.isEmpty()) {
            ? eVar2 = new A0.e(iVar2.t);
            this.r = eVar2;
            eVar2.b = true;
            eVar2.a(new a(this));
            z3 = ((Float) this.r.f()).floatValue() != 1.0f ? false : z3;
            if (z3 != this.f190x) {
                this.f190x = z3;
                this.f188o.invalidateSelf();
            }
            f(this.r);
        } else if (true != this.f190x) {
            this.f190x = true;
            this.f188o.invalidateSelf();
        }
    }

    public final void a() {
        this.f188o.invalidateSelf();
    }

    public final void c(f fVar, int i2, ArrayList arrayList, f fVar2) {
        c cVar = this.s;
        i iVar = this.f189p;
        if (cVar != null) {
            String str = cVar.f189p.f204c;
            f fVar3 = new f(fVar2);
            fVar3.f97a.add(str);
            if (fVar.a(i2, this.s.f189p.f204c)) {
                c cVar2 = this.s;
                f fVar4 = new f(fVar3);
                fVar4.b = cVar2;
                arrayList.add(fVar4);
            }
            if (fVar.d(i2, iVar.f204c)) {
                this.s.o(fVar, fVar.b(i2, this.s.f189p.f204c) + i2, arrayList, fVar3);
            }
        }
        String str2 = iVar.f204c;
        String str3 = iVar.f204c;
        if (fVar.c(i2, str2)) {
            if (!"__container".equals(str3)) {
                f fVar5 = new f(fVar2);
                fVar5.f97a.add(str3);
                if (fVar.a(i2, str3)) {
                    f fVar6 = new f(fVar5);
                    fVar6.b = this;
                    arrayList.add(fVar6);
                }
                fVar2 = fVar5;
            }
            if (fVar.d(i2, str3)) {
                o(fVar, fVar.b(i2, str3) + i2, arrayList, fVar2);
            }
        }
    }

    public void d(D0.e eVar, Object obj) {
        this.w.c(eVar, obj);
    }

    public void e(RectF rectF, Matrix matrix, boolean z3) {
        this.f186i.set(0.0f, 0.0f, 0.0f, 0.0f);
        h();
        Matrix matrix2 = this.n;
        matrix2.set(matrix);
        if (z3) {
            List list = this.u;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    matrix2.preConcat(((c) this.u.get(size)).w.e());
                }
            } else {
                c cVar = this.t;
                if (cVar != null) {
                    matrix2.preConcat(cVar.w.e());
                }
            }
        }
        matrix2.preConcat(this.w.e());
    }

    public final void f(A0.e eVar) {
        if (eVar != null) {
            this.v.add(eVar);
        }
    }

    public final void g(Canvas canvas, Matrix matrix, int i2) {
        int i7;
        float f5;
        l lVar;
        BlendModeCompat blendModeCompat;
        Path path;
        l lVar2;
        float f8;
        int i8;
        Integer num;
        Canvas canvas2 = canvas;
        Matrix matrix2 = matrix;
        C0323a aVar = C0326d.f2049a;
        if (this.f190x) {
            i iVar = this.f189p;
            boolean z3 = iVar.v;
            h hVar = iVar.y;
            if (!z3) {
                h();
                Matrix matrix3 = this.b;
                matrix3.reset();
                matrix3.set(matrix2);
                int i10 = 1;
                for (int size = this.u.size() - 1; size >= 0; size--) {
                    matrix3.preConcat(((c) this.u.get(size)).w.e());
                }
                C0323a aVar2 = C0326d.f2049a;
                r rVar = this.w;
                A0.e eVar = rVar.f25j;
                if (eVar == null || (num = (Integer) eVar.f()) == null) {
                    i7 = 100;
                } else {
                    i7 = num.intValue();
                }
                int i11 = (int) ((((((float) i2) / 255.0f) * ((float) i7)) / 100.0f) * 255.0f);
                if (this.s == null && !l() && hVar == h.NORMAL) {
                    matrix3.preConcat(rVar.e());
                    i(canvas2, matrix3, i11);
                    m();
                    return;
                }
                RectF rectF = this.f186i;
                int i12 = 0;
                e(rectF, matrix3, false);
                if (!(this.s == null || iVar.u == h.INVERT)) {
                    RectF rectF2 = this.l;
                    rectF2.set(0.0f, 0.0f, 0.0f, 0.0f);
                    this.s.e(rectF2, matrix2, true);
                    if (!rectF.intersect(rectF2)) {
                        rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
                    }
                }
                matrix3.preConcat(rVar.e());
                RectF rectF3 = this.k;
                rectF3.set(0.0f, 0.0f, 0.0f, 0.0f);
                boolean l8 = l();
                l lVar3 = this.q;
                Path path2 = this.f183a;
                if (l8) {
                    int size2 = ((List) lVar3.g).size();
                    while (true) {
                        if (i12 < size2) {
                            j jVar = (j) ((List) lVar3.g).get(i12);
                            Path path3 = (Path) ((A0.e) ((ArrayList) lVar3.e).get(i12)).f();
                            if (path3 != null) {
                                path2.set(path3);
                                path2.transform(matrix3);
                                int i13 = b.b[jVar.f136a.ordinal()];
                                if (i13 == i10 || i13 == 2 || ((i13 == 3 || i13 == 4) && jVar.d)) {
                                    break;
                                }
                                RectF rectF4 = this.m;
                                path2.computeBounds(rectF4, false);
                                if (i12 == 0) {
                                    rectF3.set(rectF4);
                                } else {
                                    i8 = size2;
                                    rectF3.set(Math.min(rectF3.left, rectF4.left), Math.min(rectF3.top, rectF4.top), Math.max(rectF3.right, rectF4.right), Math.max(rectF3.bottom, rectF4.bottom));
                                    i12++;
                                    size2 = i8;
                                    i10 = 1;
                                }
                            }
                            i8 = size2;
                            i12++;
                            size2 = i8;
                            i10 = 1;
                        } else if (!rectF.intersect(rectF3)) {
                            f5 = 0.0f;
                            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
                        }
                    }
                }
                f5 = 0.0f;
                RectF rectF5 = this.f187j;
                rectF5.set(f5, f5, (float) canvas2.getWidth(), (float) canvas2.getHeight());
                Matrix matrix4 = this.f184c;
                canvas2.getMatrix(matrix4);
                if (!matrix4.isIdentity()) {
                    matrix4.invert(matrix4);
                    matrix4.mapRect(rectF5);
                }
                if (!rectF.intersect(rectF5)) {
                    rectF.set(f5, f5, f5, f5);
                }
                C0323a aVar3 = C0326d.f2049a;
                float f10 = 1.0f;
                if (rectF.width() >= 1.0f && rectF.height() >= 1.0f) {
                    l lVar4 = this.d;
                    lVar4.setAlpha(ScoverState.TYPE_NFC_SMART_COVER);
                    hVar.getClass();
                    switch (E0.g.f135a[hVar.ordinal()]) {
                        case 2:
                            blendModeCompat = BlendModeCompat.MODULATE;
                            break;
                        case 3:
                            blendModeCompat = BlendModeCompat.SCREEN;
                            break;
                        case 4:
                            blendModeCompat = BlendModeCompat.OVERLAY;
                            break;
                        case 5:
                            blendModeCompat = BlendModeCompat.DARKEN;
                            break;
                        case 6:
                            blendModeCompat = BlendModeCompat.LIGHTEN;
                            break;
                        case 7:
                            blendModeCompat = BlendModeCompat.PLUS;
                            break;
                        default:
                            blendModeCompat = null;
                            break;
                    }
                    PaintCompat.setBlendMode(lVar4, blendModeCompat);
                    J0.g.f(canvas2, rectF, lVar4);
                    if (hVar != h.MULTIPLY) {
                        path = path2;
                        lVar2 = lVar3;
                        canvas2.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.f185h);
                        canvas2 = canvas;
                    } else {
                        lVar2 = lVar3;
                        path = path2;
                        if (this.f182C == null) {
                            l lVar5 = new l();
                            this.f182C = lVar5;
                            lVar5.setColor(-1);
                        }
                        canvas2 = canvas;
                        canvas2.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.f182C);
                    }
                    i(canvas2, matrix3, i11);
                    if (l()) {
                        l lVar6 = this.e;
                        canvas2.saveLayer(rectF, lVar6);
                        int i14 = 0;
                        while (true) {
                            List list = (List) lVar2.g;
                            ArrayList arrayList = (ArrayList) lVar2.e;
                            if (i14 < list.size()) {
                                j jVar2 = (j) list.get(i14);
                                A0.e eVar2 = (A0.e) arrayList.get(i14);
                                float f11 = f10;
                                A0.e eVar3 = (A0.e) ((ArrayList) lVar2.f).get(i14);
                                int[] iArr = b.b;
                                E0.i iVar2 = jVar2.f136a;
                                boolean z7 = jVar2.d;
                                int i15 = iArr[iVar2.ordinal()];
                                int i16 = i14;
                                if (i15 != 1) {
                                    l lVar7 = this.f;
                                    if (i15 == 2) {
                                        if (i16 == 0) {
                                            lVar4.setColor(-16777216);
                                            lVar4.setAlpha(ScoverState.TYPE_NFC_SMART_COVER);
                                            canvas2.drawRect(rectF, lVar4);
                                        }
                                        if (z7) {
                                            J0.g.f(canvas2, rectF, lVar7);
                                            canvas2.drawRect(rectF, lVar4);
                                            lVar7.setAlpha((int) (((float) ((Integer) eVar3.f()).intValue()) * 2.55f));
                                            path.set((Path) eVar2.f());
                                            path.transform(matrix3);
                                            canvas2.drawPath(path, lVar7);
                                            canvas2.restore();
                                        } else {
                                            path.set((Path) eVar2.f());
                                            path.transform(matrix3);
                                            canvas2.drawPath(path, lVar7);
                                        }
                                    } else if (i15 != 3) {
                                        if (i15 == 4) {
                                            if (z7) {
                                                J0.g.f(canvas2, rectF, lVar4);
                                                canvas2.drawRect(rectF, lVar4);
                                                path.set((Path) eVar2.f());
                                                path.transform(matrix3);
                                                lVar4.setAlpha((int) (((float) ((Integer) eVar3.f()).intValue()) * 2.55f));
                                                canvas2.drawPath(path, lVar7);
                                                canvas2.restore();
                                            } else {
                                                path.set((Path) eVar2.f());
                                                path.transform(matrix3);
                                                lVar4.setAlpha((int) (((float) ((Integer) eVar3.f()).intValue()) * 2.55f));
                                                canvas2.drawPath(path, lVar4);
                                            }
                                        }
                                    } else if (z7) {
                                        J0.g.f(canvas2, rectF, lVar6);
                                        canvas2.drawRect(rectF, lVar4);
                                        lVar7.setAlpha((int) (((float) ((Integer) eVar3.f()).intValue()) * 2.55f));
                                        path.set((Path) eVar2.f());
                                        path.transform(matrix3);
                                        canvas2.drawPath(path, lVar7);
                                        canvas2.restore();
                                    } else {
                                        J0.g.f(canvas2, rectF, lVar6);
                                        path.set((Path) eVar2.f());
                                        path.transform(matrix3);
                                        lVar4.setAlpha((int) (((float) ((Integer) eVar3.f()).intValue()) * 2.55f));
                                        canvas2.drawPath(path, lVar4);
                                        canvas2.restore();
                                    }
                                } else if (!arrayList.isEmpty()) {
                                    int i17 = 0;
                                    while (true) {
                                        if (i17 >= list.size()) {
                                            lVar4.setAlpha(ScoverState.TYPE_NFC_SMART_COVER);
                                            canvas2.drawRect(rectF, lVar4);
                                        } else if (((j) list.get(i17)).f136a == E0.i.MASK_MODE_NONE) {
                                            i17++;
                                        }
                                    }
                                    f10 = f11;
                                    i14 = i16 + 1;
                                }
                                f10 = f11;
                                i14 = i16 + 1;
                            } else {
                                f8 = f10;
                                C0323a aVar4 = C0326d.f2049a;
                                canvas2.restore();
                            }
                        }
                    } else {
                        f8 = 1.0f;
                    }
                    if (this.s != null) {
                        canvas2.saveLayer(rectF, this.g);
                        canvas2.drawRect(rectF.left - f8, rectF.top - f8, rectF.right + f8, rectF.bottom + f8, this.f185h);
                        this.s.g(canvas2, matrix2, i11);
                        canvas2.restore();
                    }
                    canvas2.restore();
                }
                if (this.y && (lVar = this.z) != null) {
                    lVar.setStyle(Paint.Style.STROKE);
                    this.z.setColor(-251901);
                    this.z.setStrokeWidth(4.0f);
                    canvas2.drawRect(rectF, this.z);
                    this.z.setStyle(Paint.Style.FILL);
                    this.z.setColor(1357638635);
                    canvas2.drawRect(rectF, this.z);
                }
                m();
            }
        }
    }

    public final void h() {
        if (this.u == null) {
            if (this.t == null) {
                this.u = Collections.EMPTY_LIST;
                return;
            }
            this.u = new ArrayList();
            for (c cVar = this.t; cVar != null; cVar = cVar.t) {
                this.u.add(cVar);
            }
        }
    }

    public abstract void i(Canvas canvas, Matrix matrix, int i2);

    public G0.e j() {
        return this.f189p.w;
    }

    public B0.a k() {
        return this.f189p.f210x;
    }

    public final boolean l() {
        l lVar = this.q;
        if (lVar == null || ((ArrayList) lVar.e).isEmpty()) {
            return false;
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: J0.e} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: J0.e} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: J0.e} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void m() {
        /*
            r4 = this;
            x0.w r0 = r4.f188o
            x0.j r0 = r0.d
            x0.E r0 = r0.f2056a
            F0.i r4 = r4.f189p
            java.lang.String r4 = r4.f204c
            java.util.HashMap r1 = r0.f2046c
            boolean r2 = r0.f2045a
            if (r2 != 0) goto L_0x0011
            goto L_0x004a
        L_0x0011:
            java.lang.Object r2 = r1.get(r4)
            J0.e r2 = (J0.e) r2
            if (r2 != 0) goto L_0x0021
            J0.e r2 = new J0.e
            r2.<init>()
            r1.put(r4, r2)
        L_0x0021:
            int r1 = r2.f361a
            int r1 = r1 + 1
            r2.f361a = r1
            r3 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r3) goto L_0x0030
            int r1 = r1 / 2
            r2.f361a = r1
        L_0x0030:
            java.lang.String r1 = "__container"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x004a
            androidx.collection.ArraySet r4 = r0.b
            java.util.Iterator r4 = r4.iterator()
            boolean r0 = r4.hasNext()
            if (r0 != 0) goto L_0x0045
            goto L_0x004a
        L_0x0045:
            java.lang.ClassCastException r4 = i.C0212a.h(r4)
            throw r4
        L_0x004a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.c.m():void");
    }

    public final void n(A0.e eVar) {
        this.v.remove(eVar);
    }

    public void p(boolean z3) {
        if (z3 && this.z == null) {
            this.z = new l();
        }
        this.y = z3;
    }

    public void q(float f5) {
        C0323a aVar = C0326d.f2049a;
        r rVar = this.w;
        A0.e eVar = rVar.f25j;
        if (eVar != null) {
            eVar.j(f5);
        }
        A0.e eVar2 = rVar.m;
        if (eVar2 != null) {
            eVar2.j(f5);
        }
        A0.e eVar3 = rVar.n;
        if (eVar3 != null) {
            eVar3.j(f5);
        }
        A0.e eVar4 = rVar.f;
        if (eVar4 != null) {
            eVar4.j(f5);
        }
        A0.e eVar5 = rVar.g;
        if (eVar5 != null) {
            eVar5.j(f5);
        }
        A0.e eVar6 = rVar.f23h;
        if (eVar6 != null) {
            eVar6.j(f5);
        }
        A0.e eVar7 = rVar.f24i;
        if (eVar7 != null) {
            eVar7.j(f5);
        }
        i iVar = rVar.k;
        if (iVar != null) {
            iVar.j(f5);
        }
        i iVar2 = rVar.l;
        if (iVar2 != null) {
            iVar2.j(f5);
        }
        int i2 = 0;
        l lVar = this.q;
        if (lVar != null) {
            ArrayList arrayList = (ArrayList) lVar.e;
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                ((A0.e) arrayList.get(i7)).j(f5);
            }
            C0323a aVar2 = C0326d.f2049a;
        }
        i iVar3 = this.r;
        if (iVar3 != null) {
            iVar3.j(f5);
        }
        c cVar = this.s;
        if (cVar != null) {
            cVar.q(f5);
        }
        while (true) {
            ArrayList arrayList2 = this.v;
            if (i2 < arrayList2.size()) {
                ((A0.e) arrayList2.get(i2)).j(f5);
                i2++;
            } else {
                C0323a aVar3 = C0326d.f2049a;
                return;
            }
        }
    }

    public final void b(List list, List list2) {
    }

    public void o(f fVar, int i2, ArrayList arrayList, f fVar2) {
    }
}
