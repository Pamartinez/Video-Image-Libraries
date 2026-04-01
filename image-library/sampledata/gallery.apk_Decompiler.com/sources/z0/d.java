package z0;

import A0.a;
import A0.r;
import C0.f;
import C0.g;
import D0.e;
import F0.c;
import F0.l;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements e, n, a, g {

    /* renamed from: a  reason: collision with root package name */
    public final l f2152a;
    public final RectF b;

    /* renamed from: c  reason: collision with root package name */
    public final Matrix f2153c;
    public final Path d;
    public final RectF e;
    public final String f;
    public final boolean g;

    /* renamed from: h  reason: collision with root package name */
    public final ArrayList f2154h;

    /* renamed from: i  reason: collision with root package name */
    public final w f2155i;

    /* renamed from: j  reason: collision with root package name */
    public ArrayList f2156j;
    public final r k;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public d(x0.w r8, F0.c r9, E0.s r10, x0.C0332j r11) {
        /*
            r7 = this;
            java.lang.String r3 = r10.f151a
            boolean r4 = r10.f152c
            java.util.List r10 = r10.b
            java.util.ArrayList r5 = new java.util.ArrayList
            int r0 = r10.size()
            r5.<init>(r0)
            r0 = 0
            r1 = r0
        L_0x0011:
            int r2 = r10.size()
            if (r1 >= r2) goto L_0x0029
            java.lang.Object r2 = r10.get(r1)
            E0.b r2 = (E0.b) r2
            z0.c r2 = r2.a(r8, r11, r9)
            if (r2 == 0) goto L_0x0026
            r5.add(r2)
        L_0x0026:
            int r1 = r1 + 1
            goto L_0x0011
        L_0x0029:
            int r11 = r10.size()
            if (r0 >= r11) goto L_0x0043
            java.lang.Object r11 = r10.get(r0)
            E0.b r11 = (E0.b) r11
            boolean r1 = r11 instanceof D0.g
            if (r1 == 0) goto L_0x0040
            D0.g r11 = (D0.g) r11
        L_0x003b:
            r0 = r7
            r1 = r8
            r2 = r9
            r6 = r11
            goto L_0x0045
        L_0x0040:
            int r0 = r0 + 1
            goto L_0x0029
        L_0x0043:
            r11 = 0
            goto L_0x003b
        L_0x0045:
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.d.<init>(x0.w, F0.c, E0.s, x0.j):void");
    }

    public final void a() {
        this.f2155i.invalidateSelf();
    }

    public final void b(List list, List list2) {
        int size = list.size();
        ArrayList arrayList = this.f2154h;
        ArrayList arrayList2 = new ArrayList(arrayList.size() + size);
        arrayList2.addAll(list);
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            c cVar = (c) arrayList.get(size2);
            cVar.b(arrayList2, arrayList.subList(0, size2));
            arrayList2.add(cVar);
        }
    }

    public final void c(f fVar, int i2, ArrayList arrayList, f fVar2) {
        String str = this.f;
        if (fVar.c(i2, str) || "__container".equals(str)) {
            if (!"__container".equals(str)) {
                f fVar3 = new f(fVar2);
                fVar3.f97a.add(str);
                if (fVar.a(i2, str)) {
                    f fVar4 = new f(fVar3);
                    fVar4.b = this;
                    arrayList.add(fVar4);
                }
                fVar2 = fVar3;
            }
            if (fVar.d(i2, str)) {
                int b5 = fVar.b(i2, str) + i2;
                int i7 = 0;
                while (true) {
                    ArrayList arrayList2 = this.f2154h;
                    if (i7 < arrayList2.size()) {
                        c cVar = (c) arrayList2.get(i7);
                        if (cVar instanceof g) {
                            ((g) cVar).c(fVar, b5, arrayList, fVar2);
                        }
                        i7++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public final void d(e eVar, Object obj) {
        r rVar = this.k;
        if (rVar != null) {
            rVar.c(eVar, obj);
        }
    }

    public final void e(RectF rectF, Matrix matrix, boolean z) {
        Matrix matrix2 = this.f2153c;
        matrix2.set(matrix);
        r rVar = this.k;
        if (rVar != null) {
            matrix2.preConcat(rVar.e());
        }
        RectF rectF2 = this.e;
        rectF2.set(0.0f, 0.0f, 0.0f, 0.0f);
        ArrayList arrayList = this.f2154h;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            c cVar = (c) arrayList.get(size);
            if (cVar instanceof e) {
                ((e) cVar).e(rectF2, matrix2, z);
                rectF.union(rectF2);
            }
        }
    }

    public final List f() {
        if (this.f2156j == null) {
            this.f2156j = new ArrayList();
            int i2 = 0;
            while (true) {
                ArrayList arrayList = this.f2154h;
                if (i2 >= arrayList.size()) {
                    break;
                }
                c cVar = (c) arrayList.get(i2);
                if (cVar instanceof n) {
                    this.f2156j.add((n) cVar);
                }
                i2++;
            }
        }
        return this.f2156j;
    }

    public final void g(Canvas canvas, Matrix matrix, int i2) {
        int i7;
        if (!this.g) {
            Matrix matrix2 = this.f2153c;
            matrix2.set(matrix);
            r rVar = this.k;
            if (rVar != null) {
                matrix2.preConcat(rVar.e());
                A0.e eVar = rVar.f25j;
                if (eVar == null) {
                    i7 = 100;
                } else {
                    i7 = ((Integer) eVar.f()).intValue();
                }
                i2 = (int) ((((((float) i7) / 100.0f) * ((float) i2)) / 255.0f) * 255.0f);
            }
            boolean z = this.f2155i.y;
            boolean z3 = false;
            ArrayList arrayList = this.f2154h;
            if (z) {
                int i8 = 0;
                int i10 = 0;
                while (true) {
                    if (i8 >= arrayList.size()) {
                        break;
                    } else if (!(arrayList.get(i8) instanceof e) || (i10 = i10 + 1) < 2) {
                        i8++;
                    } else if (i2 != 255) {
                        z3 = true;
                    }
                }
            }
            if (z3) {
                RectF rectF = this.b;
                rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
                e(rectF, matrix2, true);
                l lVar = this.f2152a;
                lVar.setAlpha(i2);
                J0.g.f(canvas, rectF, lVar);
            }
            if (z3) {
                i2 = 255;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                Object obj = arrayList.get(size);
                if (obj instanceof e) {
                    ((e) obj).g(canvas, matrix2, i2);
                }
            }
            if (z3) {
                canvas.restore();
            }
        }
    }

    public final String getName() {
        throw null;
    }

    public final Path getPath() {
        Matrix matrix = this.f2153c;
        matrix.reset();
        r rVar = this.k;
        if (rVar != null) {
            matrix.set(rVar.e());
        }
        Path path = this.d;
        path.reset();
        if (!this.g) {
            ArrayList arrayList = this.f2154h;
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                c cVar = (c) arrayList.get(size);
                if (cVar instanceof n) {
                    path.addPath(((n) cVar).getPath(), matrix);
                }
            }
        }
        return path;
    }

    public d(w wVar, c cVar, String str, boolean z, ArrayList arrayList, D0.g gVar) {
        this.f2152a = new l();
        this.b = new RectF();
        this.f2153c = new Matrix();
        this.d = new Path();
        this.e = new RectF();
        this.f = str;
        this.f2155i = wVar;
        this.g = z;
        this.f2154h = arrayList;
        if (gVar != null) {
            r rVar = new r(gVar);
            this.k = rVar;
            rVar.a(cVar);
            rVar.b(this);
        }
        ArrayList arrayList2 = new ArrayList();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            c cVar2 = (c) arrayList.get(size);
            if (cVar2 instanceof j) {
                arrayList2.add((j) cVar2);
            }
        }
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            ((j) arrayList2.get(size2)).f(arrayList.listIterator(arrayList.size()));
        }
    }
}
