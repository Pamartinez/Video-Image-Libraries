package lg;

import gg.a;
import ig.d;
import ig.f;
import ig.h;
import jg.c;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D implements a {

    /* renamed from: a  reason: collision with root package name */
    public static final D f4893a = new Object();
    public static final h b = L2.a.f("kotlinx.serialization.json.JsonPrimitive", d.l, new f[0]);

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        l e = D1.f.k(cVar).e();
        if (e instanceof C) {
            return (C) e;
        }
        StringBuilder sb2 = new StringBuilder("Unexpected JSON element, expected JsonPrimitive, had ");
        throw mg.h.d(-1, A.a.g(v.f4727a, e.getClass(), sb2), e.toString());
    }

    public final f getDescriptor() {
        return b;
    }
}
