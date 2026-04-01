package We;

import Ae.b;
import He.C0750f;
import java.lang.reflect.Method;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n extends g implements b {
    public static final n d = new g(1);

    public final String getName() {
        return "<init>";
    }

    public final C0750f getOwner() {
        return v.f4727a.b(x.class);
    }

    public final String getSignature() {
        return "<init>(Ljava/lang/reflect/Method;)V";
    }

    public final Object invoke(Object obj) {
        Method method = (Method) obj;
        j.e(method, "p0");
        return new x(method);
    }
}
