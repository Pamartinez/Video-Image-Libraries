package Je;

import Ae.c;
import Df.w;
import He.C0750f;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import lf.C1171y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a extends g implements c {
    public static final a d = new g(2);

    public final String getName() {
        return "loadFunction";
    }

    public final C0750f getOwner() {
        return v.f4727a.b(w.class);
    }

    public final String getSignature() {
        return "loadFunction(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;)Lorg/jetbrains/kotlin/descriptors/SimpleFunctionDescriptor;";
    }

    public final Object invoke(Object obj, Object obj2) {
        w wVar = (w) obj;
        C1171y yVar = (C1171y) obj2;
        j.e(wVar, "p0");
        j.e(yVar, "p1");
        return wVar.e(yVar);
    }
}
