package He;

import Ae.b;
import java.lang.reflect.Type;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A extends h implements b {
    public static final A d = new h(1, F.class, "typeToString", "typeToString(Ljava/lang/reflect/Type;)Ljava/lang/String;", 1);

    public final Object invoke(Object obj) {
        Type type = (Type) obj;
        j.e(type, "p0");
        return F.d(type);
    }
}
