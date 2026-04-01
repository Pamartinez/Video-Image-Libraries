package lg;

import D1.f;
import gg.a;
import jg.c;
import kg.X;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import mg.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t implements a {

    /* renamed from: a  reason: collision with root package name */
    public static final X f4909a = L2.a.a("kotlinx.serialization.json.JsonLiteral");

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        l e = f.k(cVar).e();
        if (e instanceof s) {
            return (s) e;
        }
        StringBuilder sb2 = new StringBuilder("Unexpected JSON element, expected JsonLiteral, had ");
        throw h.d(-1, A.a.g(v.f4727a, e.getClass(), sb2), e.toString());
    }

    public final ig.f getDescriptor() {
        return f4909a;
    }
}
