package lg;

import gg.a;
import ig.c;
import ig.f;
import ig.h;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p implements a {

    /* renamed from: a  reason: collision with root package name */
    public static final p f4907a = new Object();
    public static final h b;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, lg.p] */
    static {
        o oVar = o.e;
        b = L2.a.e("kotlinx.serialization.json.JsonElement", c.d, new f[0], oVar);
    }

    public final Object deserialize(jg.c cVar) {
        j.e(cVar, "decoder");
        return D1.f.k(cVar).e();
    }

    public final f getDescriptor() {
        return b;
    }
}
