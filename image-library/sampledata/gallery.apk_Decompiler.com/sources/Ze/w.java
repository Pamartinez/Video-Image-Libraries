package Ze;

import B1.a;
import Tf.v;
import kotlin.jvm.internal.j;
import og.k;
import qf.C1235b;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class w {

    /* renamed from: a  reason: collision with root package name */
    public static final C1236c f3962a;
    public static final C1235b b = k.y("kotlin/jvm/internal/RepeatableContainer", false);

    static {
        C1236c cVar = new C1236c("kotlin.jvm.JvmField");
        f3962a = cVar;
        k.U(cVar);
        k.U(new C1236c("kotlin.reflect.jvm.internal.ReflectionFactoryImpl"));
    }

    public static final String a(String str) {
        j.e(str, "propertyName");
        if (c(str)) {
            return str;
        }
        return "get".concat(a.e(str));
    }

    public static final String b(String str) {
        String str2;
        if (c(str)) {
            str2 = str.substring(2);
            j.d(str2, "substring(...)");
        } else {
            str2 = a.e(str);
        }
        return "set".concat(str2);
    }

    public static final boolean c(String str) {
        j.e(str, "name");
        if (!v.t0(str, "is") || str.length() == 2) {
            return false;
        }
        char charAt = str.charAt(2);
        if (j.f(97, charAt) > 0 || j.f(charAt, 122) > 0) {
            return true;
        }
        return false;
    }
}
