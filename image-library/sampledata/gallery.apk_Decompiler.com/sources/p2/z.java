package P2;

import P0.b;
import U2.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class z extends D {

    /* renamed from: j  reason: collision with root package name */
    public static final w f605j = new w(0);
    public final ArrayList f = new ArrayList(100);
    public final HashMap g = new HashMap(100);

    /* renamed from: h  reason: collision with root package name */
    public final y f606h;

    /* renamed from: i  reason: collision with root package name */
    public int f607i;

    public z(String str, C0056f fVar, int i2, y yVar) {
        super(str, fVar, i2);
        this.f606h = yVar;
        this.f607i = -1;
    }

    public final int a(p pVar) {
        return ((A) pVar).f();
    }

    public final Collection c() {
        return this.f;
    }

    public final void e() {
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.f;
            int size = arrayList.size();
            if (i2 < size) {
                while (i2 < size) {
                    ((A) arrayList.get(i2)).a(this.b);
                    i2++;
                }
            } else {
                return;
            }
        }
    }

    public final int h() {
        f();
        return this.f607i;
    }

    public final void j(b bVar) {
        boolean d = bVar.d();
        Iterator it = this.f.iterator();
        boolean z = true;
        int i2 = 0;
        while (it.hasNext()) {
            A a7 = (A) it.next();
            if (d) {
                if (z) {
                    z = false;
                } else {
                    bVar.b(0, "\n");
                }
            }
            int i7 = a7.d - 1;
            int i8 = (~i7) & (i2 + i7);
            if (i2 != i8) {
                bVar.p(i8 - i2);
                i2 = i8;
            }
            a7.d(this.b, bVar);
            i2 += a7.c();
        }
        if (i2 != this.f607i) {
            throw new RuntimeException("output size mismatch");
        }
    }

    public final void k(A a7) {
        g();
        try {
            if (a7.d <= this.f578c) {
                this.f.add(a7);
                return;
            }
            throw new IllegalArgumentException("incompatible item alignment");
        } catch (NullPointerException unused) {
            throw new NullPointerException("item == null");
        }
    }

    public final A l(A a7) {
        g();
        HashMap hashMap = this.g;
        A a10 = (A) hashMap.get(a7);
        if (a10 != null) {
            return a10;
        }
        k(a7);
        hashMap.put(a7, a7);
        return a7;
    }

    public final void m() {
        f();
        int i2 = x.f604a[this.f606h.ordinal()];
        ArrayList arrayList = this.f;
        if (i2 == 1) {
            Collections.sort(arrayList);
        } else if (i2 == 2) {
            Collections.sort(arrayList, f605j);
        }
        int size = arrayList.size();
        int i7 = 0;
        int i8 = 0;
        while (i7 < size) {
            A a7 = (A) arrayList.get(i7);
            try {
                int h5 = a7.h(this, i8);
                if (h5 >= i8) {
                    i8 = a7.c() + h5;
                    i7++;
                } else {
                    throw new RuntimeException("bogus place() result for " + a7);
                }
            } catch (RuntimeException e) {
                throw c.a(e, "...while placing " + a7);
            }
        }
        this.f607i = i8;
    }
}
