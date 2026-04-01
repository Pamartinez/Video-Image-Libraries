package We;

import Ae.b;
import He.C0750f;
import java.lang.reflect.Field;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m extends g implements b {
    public static final m d = new g(1);

    public final String getName() {
        return "<init>";
    }

    public final C0750f getOwner() {
        return v.f4727a.b(u.class);
    }

    public final String getSignature() {
        return "<init>(Ljava/lang/reflect/Field;)V";
    }

    public final Object invoke(Object obj) {
        Field field = (Field) obj;
        j.e(field, "p0");
        return new u(field);
    }
}
