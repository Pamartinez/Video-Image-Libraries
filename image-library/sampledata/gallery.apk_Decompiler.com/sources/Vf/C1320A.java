package vf;

import Hf.B;
import Hf.C0774x;
import Jf.k;
import Jf.l;
import Ne.p;
import Qe.C;
import Qe.C0816f;
import Qe.C0833x;
import kotlin.jvm.internal.j;

/* renamed from: vf.A  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1320A extends C1337q {
    public final /* synthetic */ int b = 0;

    public C1320A(byte b5) {
        super(Byte.valueOf(b5));
    }

    public final C0774x a(C c5) {
        B i2;
        B i7;
        B i8;
        B i10;
        switch (this.b) {
            case 0:
                j.e(c5, "module");
                C0816f d = C0833x.d(c5, p.S);
                if (d == null || (i2 = d.i()) == null) {
                    return l.c(k.NOT_FOUND_UNSIGNED_TYPE, "UByte");
                }
                return i2;
            case 1:
                j.e(c5, "module");
                C0816f d2 = C0833x.d(c5, p.U);
                if (d2 == null || (i7 = d2.i()) == null) {
                    return l.c(k.NOT_FOUND_UNSIGNED_TYPE, "UInt");
                }
                return i7;
            case 2:
                j.e(c5, "module");
                C0816f d3 = C0833x.d(c5, p.V);
                if (d3 == null || (i8 = d3.i()) == null) {
                    return l.c(k.NOT_FOUND_UNSIGNED_TYPE, "ULong");
                }
                return i8;
            default:
                j.e(c5, "module");
                C0816f d5 = C0833x.d(c5, p.T);
                if (d5 == null || (i10 = d5.i()) == null) {
                    return l.c(k.NOT_FOUND_UNSIGNED_TYPE, "UShort");
                }
                return i10;
        }
    }

    public final String toString() {
        switch (this.b) {
            case 0:
                return ((Number) this.f5158a).intValue() + ".toUByte()";
            case 1:
                return ((Number) this.f5158a).intValue() + ".toUInt()";
            case 2:
                return ((Number) this.f5158a).longValue() + ".toULong()";
            default:
                return ((Number) this.f5158a).intValue() + ".toUShort()";
        }
    }

    public C1320A(short s) {
        super(Short.valueOf(s));
    }

    public C1320A(int i2) {
        super(Integer.valueOf(i2));
    }

    public C1320A(long j2) {
        super(Long.valueOf(j2));
    }
}
