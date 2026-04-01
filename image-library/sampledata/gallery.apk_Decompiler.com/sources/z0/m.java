package z0;

import A0.r;
import E0.l;
import android.graphics.Matrix;
import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m implements n, j {

    /* renamed from: a  reason: collision with root package name */
    public final Path f2175a = new Path();
    public final Path b = new Path();

    /* renamed from: c  reason: collision with root package name */
    public final Path f2176c = new Path();
    public final ArrayList d = new ArrayList();
    public final l e;

    public m(l lVar) {
        this.e = lVar;
    }

    public final void a(Path.Op op) {
        Path path = this.b;
        path.reset();
        Path path2 = this.f2175a;
        path2.reset();
        ArrayList arrayList = this.d;
        for (int size = arrayList.size() - 1; size >= 1; size--) {
            n nVar = (n) arrayList.get(size);
            if (nVar instanceof d) {
                d dVar = (d) nVar;
                ArrayList arrayList2 = (ArrayList) dVar.f();
                for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
                    Path path3 = ((n) arrayList2.get(size2)).getPath();
                    Matrix matrix = dVar.f2153c;
                    r rVar = dVar.k;
                    if (rVar != null) {
                        matrix = rVar.e();
                    } else {
                        matrix.reset();
                    }
                    path3.transform(matrix);
                    path.addPath(path3);
                }
            } else {
                path.addPath(nVar.getPath());
            }
        }
        int i2 = 0;
        n nVar2 = (n) arrayList.get(0);
        if (nVar2 instanceof d) {
            d dVar2 = (d) nVar2;
            List f = dVar2.f();
            while (true) {
                ArrayList arrayList3 = (ArrayList) f;
                if (i2 >= arrayList3.size()) {
                    break;
                }
                Path path4 = ((n) arrayList3.get(i2)).getPath();
                Matrix matrix2 = dVar2.f2153c;
                r rVar2 = dVar2.k;
                if (rVar2 != null) {
                    matrix2 = rVar2.e();
                } else {
                    matrix2.reset();
                }
                path4.transform(matrix2);
                path2.addPath(path4);
                i2++;
            }
        } else {
            path2.set(nVar2.getPath());
        }
        this.f2176c.op(path2, path, op);
    }

    public final void b(List list, List list2) {
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.d;
            if (i2 < arrayList.size()) {
                ((n) arrayList.get(i2)).b(list, list2);
                i2++;
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:3:0x000a, LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void f(java.util.ListIterator r3) {
        /*
            r2 = this;
        L_0x0000:
            boolean r0 = r3.hasPrevious()
            if (r0 == 0) goto L_0x000d
            java.lang.Object r0 = r3.previous()
            if (r0 == r2) goto L_0x000d
            goto L_0x0000
        L_0x000d:
            boolean r0 = r3.hasPrevious()
            if (r0 == 0) goto L_0x0028
            java.lang.Object r0 = r3.previous()
            z0.c r0 = (z0.c) r0
            boolean r1 = r0 instanceof z0.n
            if (r1 == 0) goto L_0x000d
            java.util.ArrayList r1 = r2.d
            z0.n r0 = (z0.n) r0
            r1.add(r0)
            r3.remove()
            goto L_0x000d
        L_0x0028:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: z0.m.f(java.util.ListIterator):void");
    }

    public final Path getPath() {
        Path path = this.f2176c;
        path.reset();
        l lVar = this.e;
        if (!lVar.b) {
            int i2 = l.f2174a[lVar.f138a.ordinal()];
            if (i2 == 1) {
                int i7 = 0;
                while (true) {
                    ArrayList arrayList = this.d;
                    if (i7 >= arrayList.size()) {
                        break;
                    }
                    path.addPath(((n) arrayList.get(i7)).getPath());
                    i7++;
                }
            } else if (i2 == 2) {
                a(Path.Op.UNION);
                return path;
            } else if (i2 == 3) {
                a(Path.Op.REVERSE_DIFFERENCE);
                return path;
            } else if (i2 == 4) {
                a(Path.Op.INTERSECT);
                return path;
            } else if (i2 == 5) {
                a(Path.Op.XOR);
                return path;
            }
        }
        return path;
    }
}
