package H0;

import C0.a;
import D0.e;
import E0.q;
import I0.b;
import I0.c;
import J0.f;
import android.graphics.PointF;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class z implements F {
    public static final z d = new Object();
    public static final e e = e.S("c", "v", "i", "o");

    public final Object g(c cVar, float f) {
        if (cVar.n() == b.BEGIN_ARRAY) {
            cVar.a();
        }
        cVar.c();
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        ArrayList arrayList3 = null;
        boolean z = false;
        while (cVar.h()) {
            int q = cVar.q(e);
            if (q == 0) {
                z = cVar.i();
            } else if (q == 1) {
                arrayList = o.c(cVar, f);
            } else if (q == 2) {
                arrayList2 = o.c(cVar, f);
            } else if (q != 3) {
                cVar.r();
                cVar.s();
            } else {
                arrayList3 = o.c(cVar, f);
            }
        }
        cVar.g();
        if (cVar.n() == b.END_ARRAY) {
            cVar.f();
        }
        if (arrayList == null || arrayList2 == null || arrayList3 == null) {
            throw new IllegalArgumentException("Shape data was missing information.");
        } else if (arrayList.isEmpty()) {
            return new q(new PointF(), false, Collections.EMPTY_LIST);
        } else {
            int size = arrayList.size();
            PointF pointF = (PointF) arrayList.get(0);
            ArrayList arrayList4 = new ArrayList(size);
            for (int i2 = 1; i2 < size; i2++) {
                PointF pointF2 = (PointF) arrayList.get(i2);
                int i7 = i2 - 1;
                arrayList4.add(new a(f.a((PointF) arrayList.get(i7), (PointF) arrayList3.get(i7)), f.a(pointF2, (PointF) arrayList2.get(i2)), pointF2));
            }
            if (z) {
                PointF pointF3 = (PointF) arrayList.get(0);
                int i8 = size - 1;
                arrayList4.add(new a(f.a((PointF) arrayList.get(i8), (PointF) arrayList3.get(i8)), f.a(pointF3, (PointF) arrayList2.get(0)), pointF3));
            }
            return new q(pointF, z, arrayList4);
        }
    }
}
