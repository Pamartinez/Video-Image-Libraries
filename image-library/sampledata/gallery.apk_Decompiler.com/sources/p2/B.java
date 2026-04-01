package P2;

import S2.t;
import T2.a;
import T2.b;
import T2.c;
import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B extends o {
    public final a e;
    public final t f;
    public H g;

    public B(a aVar) {
        H h5;
        if (aVar != null) {
            b bVar = aVar.f;
            Object[] objArr = bVar.e;
            this.e = aVar;
            int length = objArr.length;
            StringBuilder sb2 = new StringBuilder(length + 1);
            char charAt = aVar.e.d.charAt(0);
            sb2.append(charAt == '[' ? 'L' : charAt);
            for (int i2 = 0; i2 < length; i2++) {
                char charAt2 = ((c) bVar.d(i2)).d.charAt(0);
                if (charAt2 == '[') {
                    charAt2 = 'L';
                }
                sb2.append(charAt2);
            }
            this.f = new t(sb2.toString());
            if (objArr.length == 0) {
                h5 = null;
            } else {
                h5 = new H(bVar);
            }
            this.g = h5;
            return;
        }
        throw new NullPointerException("prototype == null");
    }

    public final void a(C0056f fVar) {
        C c5 = fVar.e;
        C c6 = fVar.f;
        z zVar = fVar.b;
        c6.q(this.e.e);
        c5.o(this.f);
        H h5 = this.g;
        if (h5 != null) {
            this.g = (H) zVar.l(h5);
        }
    }

    public final q b() {
        return q.TYPE_PROTO_ID_ITEM;
    }

    public final int c() {
        return 12;
    }

    public final void d(C0056f fVar, P0.b bVar) {
        int i2;
        C c5 = fVar.e;
        t tVar = this.f;
        int l = c5.l(tVar);
        C c6 = fVar.f;
        a aVar = this.e;
        c cVar = aVar.e;
        c cVar2 = aVar.e;
        int n = c6.n(cVar);
        H h5 = this.g;
        if (h5 == null) {
            i2 = 0;
        } else {
            i2 = h5.f();
        }
        if (bVar.d()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(cVar2.a());
            sb2.append(" proto(");
            b bVar2 = aVar.f;
            int length = bVar2.e.length;
            for (int i7 = 0; i7 < length; i7++) {
                if (i7 != 0) {
                    sb2.append(ArcCommonLog.TAG_COMMA);
                }
                sb2.append(((c) bVar2.d(i7)).a());
            }
            sb2.append(")");
            bVar.b(0, f() + ' ' + sb2.toString());
            bVar.b(4, "  shorty_idx:      " + L2.a.E(l) + " // " + tVar.f());
            bVar.b(4, "  return_type_idx: " + L2.a.E(n) + " // " + cVar2.a());
            bVar.b(4, "  parameters_off:  ".concat(L2.a.E(i2)));
        }
        bVar.l(l);
        bVar.l(n);
        bVar.l(i2);
    }
}
