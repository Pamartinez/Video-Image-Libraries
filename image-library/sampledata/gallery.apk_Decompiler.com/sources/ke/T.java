package Ke;

import Ae.c;
import Df.w;
import He.C0750f;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import lf.G;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class T extends g implements c {
    public static final T d = new g(2);

    public final String getName() {
        return "loadProperty";
    }

    public final C0750f getOwner() {
        return v.f4727a.b(w.class);
    }

    public final String getSignature() {
        return "loadProperty(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;)Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;";
    }

    public final Object invoke(Object obj, Object obj2) {
        w wVar = (w) obj;
        G g = (G) obj2;
        j.e(wVar, "p0");
        j.e(g, "p1");
        return wVar.f(g);
    }
}
