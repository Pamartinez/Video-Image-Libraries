package Ke;

import L2.a;
import java.lang.reflect.Constructor;
import kotlin.jvm.internal.j;
import ne.C1192j;

/* renamed from: Ke.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0790h extends a {
    public final Constructor d;

    public C0790h(Constructor constructor) {
        j.e(constructor, "constructor");
        this.d = constructor;
    }

    public final String d() {
        Class[] parameterTypes = this.d.getParameterTypes();
        j.d(parameterTypes, "getParameterTypes(...)");
        return C1192j.s0(parameterTypes, "", "<init>(", ")V", C0779b.f3497h, 24);
    }
}
