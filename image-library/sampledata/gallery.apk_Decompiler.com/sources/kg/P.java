package kg;

import L1.d;
import c0.C0086a;
import gg.a;
import ig.f;
import jg.c;
import kotlin.jvm.internal.j;
import me.h;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class P implements a {

    /* renamed from: a  reason: collision with root package name */
    public final Object f4677a = d.p(h.PUBLICATION, new gg.d(2, this));

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        f descriptor = getDescriptor();
        jg.a a7 = cVar.a(descriptor);
        int d = a7.d(getDescriptor());
        if (d == -1) {
            a7.b(descriptor);
            return x.f4917a;
        }
        throw new IllegalArgumentException(C0086a.i(d, "Unexpected index "));
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final f getDescriptor() {
        return (f) this.f4677a.getValue();
    }
}
