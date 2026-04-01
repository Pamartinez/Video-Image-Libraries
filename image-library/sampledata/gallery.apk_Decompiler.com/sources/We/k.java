package We;

import Ae.b;
import He.C0750f;
import java.lang.reflect.Constructor;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k extends g implements b {
    public static final k d = new g(1);

    public final String getName() {
        return "<init>";
    }

    public final C0750f getOwner() {
        return v.f4727a.b(r.class);
    }

    public final String getSignature() {
        return "<init>(Ljava/lang/reflect/Constructor;)V";
    }

    public final Object invoke(Object obj) {
        Constructor constructor = (Constructor) obj;
        j.e(constructor, "p0");
        return new r(constructor);
    }
}
