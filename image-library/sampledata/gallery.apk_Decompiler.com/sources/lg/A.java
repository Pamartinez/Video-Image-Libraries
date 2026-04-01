package lg;

import D1.f;
import gg.a;
import java.util.Map;
import jg.c;
import kg.C;
import kg.e0;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A implements a {

    /* renamed from: a  reason: collision with root package name */
    public static final A f4892a = new Object();
    public static final z b = z.b;

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        f.k(cVar);
        e0 e0Var = e0.f4693a;
        p pVar = p.f4907a;
        return new y((Map) new C((a) e0.f4693a, (a) p.f4907a, 1).deserialize(cVar));
    }

    public final ig.f getDescriptor() {
        return b;
    }
}
