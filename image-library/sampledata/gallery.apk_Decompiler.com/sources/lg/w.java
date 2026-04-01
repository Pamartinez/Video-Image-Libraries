package lg;

import gg.a;
import ig.f;
import ig.h;
import ig.k;
import jg.c;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w implements a {

    /* renamed from: a  reason: collision with root package name */
    public static final w f4910a = new Object();
    public static final h b = L2.a.f("kotlinx.serialization.json.JsonNull", k.d, new f[0]);

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        D1.f.k(cVar);
        if (!cVar.D()) {
            return v.INSTANCE;
        }
        throw new IllegalArgumentException("Expected 'null' literal");
    }

    public final f getDescriptor() {
        return b;
    }
}
