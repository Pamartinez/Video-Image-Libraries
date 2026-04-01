package S2;

import T2.a;
import T2.b;
import T2.c;
import U2.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q extends p {
    public final a f;
    public a g = null;

    public q(u uVar, r rVar) {
        super(uVar, rVar);
        this.f = a.c(rVar.e.d);
    }

    public final int d(a aVar) {
        p pVar = (p) aVar;
        int c5 = this.d.compareTo(pVar.d);
        if (c5 == 0) {
            c5 = this.e.d.compareTo(pVar.e.d);
        }
        if (c5 != 0) {
            return c5;
        }
        return this.f.compareTo(((q) aVar).f);
    }

    public final String e() {
        return "method";
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [U2.d, Nf.f, T2.b] */
    public final int f(boolean z) {
        a aVar = this.f;
        if (!z) {
            if (this.g == null) {
                c cVar = this.d.d;
                aVar.getClass();
                String str = "(" + cVar.d + aVar.d.substring(1);
                Object[] objArr = aVar.f.e;
                int length = objArr.length;
                ? dVar = new d(length + 1);
                dVar.e(0, cVar);
                int i2 = 0;
                while (i2 < length) {
                    int i7 = i2 + 1;
                    dVar.e(i7, objArr[i2]);
                    i2 = i7;
                }
                dVar.d = false;
                this.g = a.d(new a(str, aVar.e, dVar));
            }
            aVar = this.g;
        }
        b bVar = aVar.f;
        int length2 = bVar.e.length;
        int i8 = 0;
        for (int i10 = 0; i10 < length2; i10++) {
            i8 += ((c) bVar.d(i10)).e();
        }
        return i8;
    }

    public final c getType() {
        return this.f.e;
    }
}
