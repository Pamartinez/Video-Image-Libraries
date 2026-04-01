package vf;

import Hf.C0774x;
import Ne.i;
import Ne.l;
import Qe.C;
import kotlin.jvm.internal.j;

/* renamed from: vf.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1323c extends C1327g {
    public final /* synthetic */ int b = 1;

    public /* synthetic */ C1323c(Object obj) {
        super(obj);
    }

    public final C0774x a(C c5) {
        switch (this.b) {
            case 0:
                j.e(c5, "module");
                i f = c5.f();
                f.getClass();
                return f.s(l.BOOLEAN);
            case 1:
                j.e(c5, "module");
                i f5 = c5.f();
                f5.getClass();
                return f5.s(l.DOUBLE);
            default:
                j.e(c5, "module");
                i f8 = c5.f();
                f8.getClass();
                return f8.s(l.FLOAT);
        }
    }

    public String toString() {
        switch (this.b) {
            case 1:
                return ((Number) this.f5158a).doubleValue() + ".toDouble()";
            case 2:
                return ((Number) this.f5158a).floatValue() + ".toFloat()";
            default:
                return super.toString();
        }
    }

    public C1323c(double d) {
        super(Double.valueOf(d));
    }

    public C1323c(float f) {
        super(Float.valueOf(f));
    }
}
