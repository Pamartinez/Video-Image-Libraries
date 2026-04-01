package P2;

import L2.a;
import P0.b;
import S2.c;
import S2.t;
import S2.u;
import T2.e;
import U2.d;

/* renamed from: P2.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0053c extends o {
    public final u e;
    public final int f;
    public final u g;

    /* renamed from: h  reason: collision with root package name */
    public H f587h;

    /* renamed from: i  reason: collision with root package name */
    public final t f588i;

    /* renamed from: j  reason: collision with root package name */
    public final C0052b f589j;
    public C0057g k;
    public final C0051a l;

    /* JADX WARNING: type inference failed for: r1v3, types: [P2.A, P2.a] */
    public C0053c(u uVar, int i2, u uVar2, e eVar, t tVar) {
        H h5;
        if (uVar == null) {
            throw new NullPointerException("thisClass == null");
        } else if (eVar != null) {
            this.e = uVar;
            this.f = i2;
            this.g = uVar2;
            if (((d) eVar).e.length == 0) {
                h5 = null;
            } else {
                h5 = new H(eVar);
            }
            this.f587h = h5;
            this.f588i = tVar;
            this.f589j = new C0052b(uVar);
            this.k = null;
            this.l = new A(4, -1);
        } else {
            throw new NullPointerException("interfaces == null");
        }
    }

    public final void a(C0056f fVar) {
        C c5 = fVar.f;
        z zVar = fVar.l;
        z zVar2 = fVar.b;
        C c6 = fVar.e;
        c5.p(this.e);
        C0052b bVar = this.f589j;
        if (!bVar.isEmpty()) {
            fVar.k.k(bVar);
            c o2 = bVar.o();
            if (o2 != null) {
                this.k = (C0057g) zVar.l(new C0057g(o2));
            }
        }
        u uVar = this.g;
        if (uVar != null) {
            c5.p(uVar);
        }
        H h5 = this.f587h;
        if (h5 != null) {
            this.f587h = (H) zVar2.l(h5);
        }
        t tVar = this.f588i;
        if (tVar != null) {
            c6.o(tVar);
        }
        this.l.getClass();
    }

    public final q b() {
        return q.TYPE_CLASS_DEF_ITEM;
    }

    public final int c() {
        return 32;
    }

    public final void d(C0056f fVar, b bVar) {
        int i2;
        int i7;
        int i8;
        int i10;
        String str;
        C0056f fVar2 = fVar;
        b bVar2 = bVar;
        boolean d = bVar2.d();
        C c5 = fVar2.f;
        u uVar = this.e;
        int m = c5.m(uVar);
        int i11 = -1;
        u uVar2 = this.g;
        if (uVar2 == null) {
            i2 = -1;
        } else {
            i2 = c5.m(uVar2);
        }
        H h5 = this.f587h;
        if (h5 == null) {
            i7 = 0;
        } else {
            i7 = h5.f();
        }
        this.l.getClass();
        t tVar = this.f588i;
        if (tVar != null) {
            i11 = fVar2.e.l(tVar);
        }
        C0052b bVar3 = this.f589j;
        if (bVar3.isEmpty()) {
            i8 = 0;
        } else {
            i8 = bVar3.f();
        }
        C0057g gVar = this.k;
        if (gVar == null) {
            i10 = 0;
        } else {
            i10 = gVar.f();
        }
        int i12 = this.f;
        if (d) {
            bVar2.b(0, f() + ' ' + uVar.d.a());
            bVar2.b(4, "  class_idx:           ".concat(a.E(m)));
            StringBuilder sb2 = new StringBuilder("  access_flags:        ");
            sb2.append(a.s(i12, 30257, 1));
            bVar2.b(4, sb2.toString());
            StringBuilder sb3 = new StringBuilder("  superclass_idx:      ");
            sb3.append(a.E(i2));
            sb3.append(" // ");
            String str2 = "<none>";
            if (uVar2 == null) {
                str = str2;
            } else {
                str = uVar2.d.a();
            }
            sb3.append(str);
            bVar2.b(4, sb3.toString());
            bVar2.b(4, "  interfaces_off:      ".concat(a.E(i7)));
            if (i7 != 0) {
                e eVar = this.f587h.f580h;
                int length = ((d) eVar).e.length;
                for (int i13 = 0; i13 < length; i13++) {
                    bVar2.b(0, "    " + eVar.getType(i13).a());
                }
            }
            StringBuilder sb4 = new StringBuilder("  source_file_idx:     ");
            sb4.append(a.E(i11));
            sb4.append(" // ");
            if (tVar != null) {
                str2 = tVar.a();
            }
            sb4.append(str2);
            bVar2.b(4, sb4.toString());
            bVar2.b(4, "  annotations_off:     ".concat(a.E(0)));
            bVar2.b(4, "  class_data_off:      ".concat(a.E(i8)));
            bVar2.b(4, "  static_values_off:   ".concat(a.E(i10)));
        }
        bVar2.l(m);
        bVar2.l(i12);
        bVar2.l(i2);
        bVar2.l(i7);
        bVar2.l(i11);
        bVar2.l(0);
        bVar2.l(i8);
        bVar2.l(i10);
    }
}
