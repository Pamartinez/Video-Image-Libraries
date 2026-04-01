package Re;

import Hf.B;
import Hf.C0774x;
import Jf.l;
import Qe.C0816f;
import Qe.Q;
import java.util.Map;
import qf.C1236c;
import sf.C1283j;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements b {

    /* renamed from: a  reason: collision with root package name */
    public final C0774x f3688a;
    public final Map b;

    /* renamed from: c  reason: collision with root package name */
    public final Q f3689c;

    public c(B b5, Map map, Q q) {
        if (b5 == null) {
            c(0);
            throw null;
        } else if (map != null) {
            this.f3688a = b5;
            this.b = map;
            this.f3689c = q;
        } else {
            c(1);
            throw null;
        }
    }

    public static /* synthetic */ void c(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 3 || i2 == 4 || i2 == 5) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 3 || i2 == 4 || i2 == 5) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        if (i2 == 1) {
            objArr[0] = "valueArguments";
        } else if (i2 == 2) {
            objArr[0] = "source";
        } else if (i2 == 3 || i2 == 4 || i2 == 5) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptorImpl";
        } else {
            objArr[0] = "annotationType";
        }
        if (i2 == 3) {
            objArr[1] = "getType";
        } else if (i2 == 4) {
            objArr[1] = "getAllValueArguments";
        } else if (i2 != 5) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotationDescriptorImpl";
        } else {
            objArr[1] = "getSource";
        }
        if (!(i2 == 3 || i2 == 4 || i2 == 5)) {
            objArr[2] = "<init>";
        }
        String format = String.format(str, objArr);
        if (i2 == 3 || i2 == 4 || i2 == 5) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public final Map a() {
        Map map = this.b;
        if (map != null) {
            return map;
        }
        c(4);
        throw null;
    }

    public final C1236c b() {
        C0816f d = C1353d.d(this);
        if (d != null) {
            if (l.f(d)) {
                d = null;
            }
            if (d != null) {
                return C1353d.c(d);
            }
        }
        return null;
    }

    public final Q getSource() {
        Q q = this.f3689c;
        if (q != null) {
            return q;
        }
        c(5);
        throw null;
    }

    public final C0774x getType() {
        C0774x xVar = this.f3688a;
        if (xVar != null) {
            return xVar;
        }
        c(3);
        throw null;
    }

    public final String toString() {
        return C1283j.f5083c.x(this, (d) null);
    }
}
