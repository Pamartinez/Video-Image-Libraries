package P2;

import S2.u;
import T2.b;
import T2.c;
import T2.e;
import U2.d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

/* renamed from: P2.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0054d extends I {
    public final TreeMap f = new TreeMap();
    public ArrayList g = null;

    public C0054d(C0056f fVar) {
        super("class_defs", fVar, 4);
    }

    public final Collection c() {
        ArrayList arrayList = this.g;
        if (arrayList != null) {
            return arrayList;
        }
        return this.f.values();
    }

    public final void k() {
        TreeMap treeMap = this.f;
        int size = treeMap.size();
        this.g = new ArrayList(size);
        int i2 = 0;
        for (c l : treeMap.keySet()) {
            i2 = l(l, i2, size - i2);
        }
    }

    public final int l(c cVar, int i2, int i7) {
        e eVar;
        C0053c cVar2 = (C0053c) this.f.get(cVar);
        if (cVar2 == null || cVar2.d >= 0) {
            return i2;
        }
        if (i7 >= 0) {
            int i8 = i7 - 1;
            u uVar = cVar2.g;
            if (uVar != null) {
                i2 = l(uVar.d, i2, i8);
            }
            H h5 = cVar2.f587h;
            if (h5 == null) {
                eVar = b.f;
            } else {
                eVar = h5.f580h;
            }
            int length = ((d) eVar).e.length;
            for (int i10 = 0; i10 < length; i10++) {
                i2 = l(eVar.getType(i10), i2, i8);
            }
            cVar2.g(i2);
            this.g.add(cVar2);
            return i2 + 1;
        }
        throw new RuntimeException("class circularity with " + cVar);
    }
}
