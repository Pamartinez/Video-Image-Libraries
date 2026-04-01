package P2;

import L2.a;
import P0.b;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class J extends A {

    /* renamed from: h  reason: collision with root package name */
    public final q f581h;

    /* renamed from: i  reason: collision with root package name */
    public final ArrayList f582i;

    public J(q qVar, ArrayList arrayList) {
        super(l(arrayList), l(arrayList) + (((A) arrayList.get(0)).c() * arrayList.size()));
        if (qVar != null) {
            this.f582i = arrayList;
            this.f581h = qVar;
            return;
        }
        throw new NullPointerException("itemType == null");
    }

    public static int l(ArrayList arrayList) {
        try {
            return Math.max(4, ((A) arrayList.get(0)).d);
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalArgumentException("items.size() == 0");
        } catch (NullPointerException unused2) {
            throw new NullPointerException("items == null");
        }
    }

    public final void a(C0056f fVar) {
        Iterator it = this.f582i.iterator();
        while (it.hasNext()) {
            ((A) it.next()).a(fVar);
        }
    }

    public final q b() {
        return this.f581h;
    }

    public final void i(z zVar, int i2) {
        int i7 = i2 + this.d;
        Iterator it = this.f582i.iterator();
        boolean z = true;
        int i8 = -1;
        int i10 = -1;
        while (it.hasNext()) {
            A a7 = (A) it.next();
            int c5 = a7.c();
            int i11 = a7.d;
            if (z) {
                z = false;
                i8 = c5;
                i10 = i11;
            } else if (c5 != i8) {
                throw new UnsupportedOperationException("item size mismatch");
            } else if (i11 != i10) {
                throw new UnsupportedOperationException("item alignment mismatch");
            }
            i7 = a7.h(zVar, i7) + c5;
        }
    }

    public final void k(C0056f fVar, b bVar) {
        ArrayList arrayList = this.f582i;
        int size = arrayList.size();
        if (bVar.d()) {
            bVar.b(0, g() + " " + b().a());
            bVar.b(4, "  size: ".concat(a.E(size)));
        }
        bVar.l(size);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((A) it.next()).d(fVar, bVar);
        }
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(J.class.getName());
        stringBuffer.append(this.f582i);
        return stringBuffer.toString();
    }
}
