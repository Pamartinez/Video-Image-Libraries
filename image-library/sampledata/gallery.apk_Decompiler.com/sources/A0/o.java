package A0;

import D0.e;
import E0.q;
import J0.b;
import J0.f;
import K0.a;
import android.graphics.Path;
import android.graphics.PointF;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import z0.s;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends e {

    /* renamed from: i  reason: collision with root package name */
    public final q f15i = new q();

    /* renamed from: j  reason: collision with root package name */
    public final Path f16j = new Path();
    public Path k;
    public Path l;
    public ArrayList m;

    public o(List list) {
        super(list);
    }

    public final Object g(a aVar, float f) {
        q qVar;
        boolean z;
        q qVar2;
        q qVar3;
        Path path;
        int i2;
        int i7;
        PointF pointF;
        ArrayList arrayList;
        PointF pointF2;
        boolean z3;
        q qVar4;
        q qVar5;
        q qVar6;
        PointF pointF3;
        PointF pointF4;
        boolean z7;
        a aVar2 = aVar;
        float f5 = f;
        q qVar7 = (q) aVar2.b;
        q qVar8 = (q) aVar2.f370c;
        if (qVar8 == null) {
            qVar = qVar7;
        } else {
            qVar = qVar8;
        }
        q qVar9 = this.f15i;
        ArrayList arrayList2 = qVar9.f147a;
        if (qVar9.b == null) {
            qVar9.b = new PointF();
        }
        boolean z9 = qVar7.f148c;
        ArrayList arrayList3 = qVar7.f147a;
        boolean z10 = true;
        if (z9 || qVar.f148c) {
            z = true;
        } else {
            z = false;
        }
        qVar9.f148c = z;
        int size = arrayList3.size();
        ArrayList arrayList4 = qVar.f147a;
        if (size != arrayList4.size()) {
            b.b("Curves must have the same number of control points. Shape 1: " + arrayList3.size() + "\tShape 2: " + arrayList4.size());
        }
        int min = Math.min(arrayList3.size(), arrayList4.size());
        if (arrayList2.size() < min) {
            for (int size2 = arrayList2.size(); size2 < min; size2++) {
                arrayList2.add(new C0.a());
            }
        } else if (arrayList2.size() > min) {
            for (int size3 = arrayList2.size() - 1; size3 >= min; size3--) {
                arrayList2.remove(arrayList2.size() - 1);
            }
        }
        PointF pointF5 = qVar7.b;
        PointF pointF6 = qVar.b;
        qVar9.a(f.e(pointF5.x, pointF6.x, f5), f.e(pointF5.y, pointF6.y, f5));
        int size4 = arrayList2.size() - 1;
        while (size4 >= 0) {
            C0.a aVar3 = (C0.a) arrayList3.get(size4);
            C0.a aVar4 = (C0.a) arrayList4.get(size4);
            PointF pointF7 = aVar3.f85a;
            PointF pointF8 = aVar3.b;
            PointF pointF9 = aVar3.f86c;
            boolean z11 = z10;
            PointF pointF10 = aVar4.f85a;
            PointF pointF11 = aVar4.b;
            PointF pointF12 = aVar4.f86c;
            ((C0.a) arrayList2.get(size4)).f85a.set(f.e(pointF7.x, pointF10.x, f5), f.e(pointF7.y, pointF10.y, f5));
            ((C0.a) arrayList2.get(size4)).b.set(f.e(pointF8.x, pointF11.x, f5), f.e(pointF8.y, pointF11.y, f5));
            ((C0.a) arrayList2.get(size4)).f86c.set(f.e(pointF9.x, pointF12.x, f5), f.e(pointF9.y, pointF12.y, f5));
            size4--;
            z10 = z11;
            arrayList3 = arrayList3;
            qVar9 = qVar9;
            arrayList4 = arrayList4;
        }
        q qVar10 = qVar9;
        boolean z12 = z10;
        ArrayList arrayList5 = this.m;
        if (arrayList5 != null) {
            int size5 = arrayList5.size() - 1;
            qVar2 = qVar10;
            while (true) {
                ArrayList arrayList6 = qVar2.f147a;
                if (size5 < 0) {
                    break;
                }
                s sVar = (s) this.m.get(size5);
                sVar.getClass();
                if (arrayList6.size() > 2) {
                    float floatValue = ((Float) sVar.b.f()).floatValue();
                    if (floatValue != 0.0f) {
                        boolean z13 = qVar2.f148c;
                        int size6 = arrayList6.size() - 1;
                        int i8 = 0;
                        while (size6 >= 0) {
                            C0.a aVar5 = (C0.a) arrayList6.get(size6);
                            C0.a aVar6 = (C0.a) arrayList6.get(s.c(size6 - 1, arrayList6.size()));
                            if (size6 != 0 || z13) {
                                pointF3 = aVar6.f86c;
                            } else {
                                pointF3 = qVar2.b;
                            }
                            if (size6 != 0 || z13) {
                                pointF4 = aVar6.b;
                            } else {
                                pointF4 = pointF3;
                            }
                            PointF pointF13 = aVar5.f85a;
                            int i10 = size5;
                            if (qVar2.f148c || !(size6 == 0 || size6 == arrayList6.size() - 1)) {
                                z7 = false;
                            } else {
                                z7 = z12;
                            }
                            if (!pointF4.equals(pointF3) || !pointF13.equals(pointF3) || z7) {
                                i8++;
                            } else {
                                i8 += 2;
                            }
                            size6--;
                            size5 = i10;
                        }
                        i2 = size5;
                        q qVar11 = sVar.f2196c;
                        if (qVar11 == null || qVar11.f147a.size() != i8) {
                            ArrayList arrayList7 = new ArrayList(i8);
                            for (int i11 = 0; i11 < i8; i11++) {
                                arrayList7.add(new C0.a());
                            }
                            i7 = 0;
                            sVar.f2196c = new q(new PointF(0.0f, 0.0f), false, arrayList7);
                        } else {
                            i7 = 0;
                        }
                        q qVar12 = sVar.f2196c;
                        qVar12.f148c = z13;
                        PointF pointF14 = qVar2.b;
                        qVar12.a(pointF14.x, pointF14.y);
                        ArrayList arrayList8 = qVar12.f147a;
                        boolean z14 = qVar2.f148c;
                        int i12 = i7;
                        int i13 = i12;
                        while (i12 < arrayList6.size()) {
                            C0.a aVar7 = (C0.a) arrayList6.get(i12);
                            C0.a aVar8 = (C0.a) arrayList6.get(s.c(i12 - 1, arrayList6.size()));
                            C0.a aVar9 = (C0.a) arrayList6.get(s.c(i12 - 2, arrayList6.size()));
                            if (i12 != 0 || z14) {
                                pointF = aVar8.f86c;
                            } else {
                                pointF = qVar2.b;
                            }
                            if (i12 != 0 || z14) {
                                arrayList = arrayList6;
                                pointF2 = aVar8.b;
                            } else {
                                arrayList = arrayList6;
                                pointF2 = pointF;
                            }
                            float f8 = floatValue;
                            PointF pointF15 = aVar7.f85a;
                            PointF pointF16 = aVar9.f86c;
                            boolean z15 = z14;
                            PointF pointF17 = aVar7.f86c;
                            if (qVar2.f148c || !(i12 == 0 || i12 == arrayList.size() - 1)) {
                                z3 = false;
                            } else {
                                z3 = z12;
                            }
                            if (!pointF2.equals(pointF) || !pointF15.equals(pointF) || z3) {
                                qVar6 = qVar7;
                                qVar5 = qVar8;
                                qVar4 = qVar2;
                                C0.a aVar10 = (C0.a) arrayList8.get(s.c(i13 - 1, arrayList8.size()));
                                PointF pointF18 = aVar8.b;
                                aVar10.b.set(pointF18.x, pointF18.y);
                                PointF pointF19 = aVar8.f86c;
                                aVar10.f86c.set(pointF19.x, pointF19.y);
                                PointF pointF20 = aVar7.f85a;
                                ((C0.a) arrayList8.get(i13)).f85a.set(pointF20.x, pointF20.y);
                                i13++;
                            } else {
                                float f10 = pointF.x;
                                float f11 = f10 - pointF16.x;
                                float f12 = pointF.y;
                                float f13 = f12 - pointF16.y;
                                float f14 = pointF17.x - f10;
                                float f15 = pointF17.y - f12;
                                q qVar13 = qVar2;
                                qVar6 = qVar7;
                                qVar5 = qVar8;
                                float min2 = Math.min(f8 / ((float) Math.hypot((double) f11, (double) f13)), 0.5f);
                                float min3 = Math.min(f8 / ((float) Math.hypot((double) f14, (double) f15)), 0.5f);
                                float f16 = pointF.x;
                                float a7 = C0212a.a(pointF16.x, f16, min2, f16);
                                float f17 = pointF.y;
                                float a10 = C0212a.a(pointF16.y, f17, min2, f17);
                                float a11 = C0212a.a(pointF17.x, f16, min3, f16);
                                float a12 = C0212a.a(pointF17.y, f17, min3, f17);
                                float f18 = a7 - ((a7 - f16) * 0.5519f);
                                float f19 = a10 - ((a10 - f17) * 0.5519f);
                                float f20 = a11 - ((a11 - f16) * 0.5519f);
                                float f21 = a12 - ((a12 - f17) * 0.5519f);
                                C0.a aVar11 = (C0.a) arrayList8.get(s.c(i13 - 1, arrayList8.size()));
                                C0.a aVar12 = (C0.a) arrayList8.get(i13);
                                qVar4 = qVar13;
                                aVar11.b.set(a7, a10);
                                aVar11.f86c.set(a7, a10);
                                if (i12 == 0) {
                                    qVar12.a(a7, a10);
                                }
                                aVar12.f85a.set(f18, f19);
                                aVar12.b.set(f20, f21);
                                aVar12.f86c.set(a11, a12);
                                ((C0.a) arrayList8.get(i13 + 1)).f85a.set(a11, a12);
                                i13 += 2;
                            }
                            i12++;
                            a aVar13 = aVar;
                            float f22 = f;
                            arrayList6 = arrayList;
                            floatValue = f8;
                            z14 = z15;
                            qVar7 = qVar6;
                            qVar8 = qVar5;
                            qVar2 = qVar4;
                        }
                        qVar2 = qVar12;
                        size5 = i2 - 1;
                        a aVar14 = aVar;
                        float f23 = f;
                        qVar7 = qVar7;
                        qVar8 = qVar8;
                    }
                }
                i2 = size5;
                size5 = i2 - 1;
                a aVar142 = aVar;
                float f232 = f;
                qVar7 = qVar7;
                qVar8 = qVar8;
            }
            q qVar14 = qVar2;
        } else {
            qVar2 = qVar10;
        }
        q qVar15 = qVar7;
        q qVar16 = qVar8;
        Path path2 = this.f16j;
        f.d(qVar2, path2);
        if (this.e == null) {
            return path2;
        }
        if (this.k == null) {
            this.k = new Path();
            this.l = new Path();
        }
        f.d(qVar15, this.k);
        if (qVar16 != null) {
            qVar3 = qVar16;
            f.d(qVar3, this.l);
        } else {
            qVar3 = qVar16;
        }
        a aVar15 = aVar;
        e eVar = this.e;
        float f24 = aVar15.g;
        float floatValue2 = aVar15.f371h.floatValue();
        q qVar17 = qVar3;
        Path path3 = this.k;
        if (qVar17 == null) {
            path = path3;
        } else {
            path = this.l;
        }
        return (Path) eVar.L(f24, floatValue2, path3, path, f, e(), this.d);
    }
}
