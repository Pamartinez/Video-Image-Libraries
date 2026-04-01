package P2;

import D0.f;
import L2.a;
import P0.b;
import S2.q;
import T2.e;
import a.C0068a;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;

/* renamed from: P2.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0060j extends C0059i implements Comparable {
    public final q e;
    public final C0055e f;

    public C0060j(q qVar, int i2, f fVar, e eVar) {
        super(i2);
        boolean z;
        if (qVar != null) {
            this.e = qVar;
            if ((i2 & 8) != 0) {
                z = true;
            } else {
                z = false;
            }
            this.f = new C0055e(qVar, fVar, z, eVar);
            return;
        }
        throw new NullPointerException("method == null");
    }

    public final String a() {
        return this.e.a();
    }

    public final int c(C0056f fVar, b bVar, int i2, int i7) {
        int i8;
        boolean z;
        v vVar = fVar.f596i;
        q qVar = this.e;
        int l = vVar.l(qVar);
        int i10 = l - i2;
        C0055e eVar = this.f;
        if (eVar == null) {
            i8 = 0;
        } else {
            i8 = eVar.f();
        }
        boolean z3 = true;
        if (i8 != 0) {
            z = true;
        } else {
            z = false;
        }
        int i11 = this.d;
        if ((i11 & MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE) != 0) {
            z3 = false;
        }
        if (z == z3) {
            if (bVar.d()) {
                bVar.b(0, String.format("  [%x] %s", new Object[]{Integer.valueOf(i7), qVar.a()}));
                bVar.b(C0068a.c0(i10), "    method_idx:   ".concat(a.E(l)));
                bVar.b(C0068a.c0(i11), "    access_flags: " + a.s(i11, 204287, 3));
                bVar.b(C0068a.c0(i8), "    code_off:     ".concat(a.E(i8)));
            }
            bVar.o(i10);
            bVar.o(i11);
            bVar.o(i8);
            return l;
        }
        throw new UnsupportedOperationException("code vs. access_flags mismatch");
    }

    public final int compareTo(Object obj) {
        return this.e.compareTo(((C0060j) obj).e);
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof C0060j) && this.e.compareTo(((C0060j) obj).e) == 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(C0060j.class.getName());
        stringBuffer.append('{');
        stringBuffer.append(a.D(this.d));
        stringBuffer.append(' ');
        stringBuffer.append(this.e);
        C0055e eVar = this.f;
        if (eVar != null) {
            stringBuffer.append(' ');
            stringBuffer.append(eVar);
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
