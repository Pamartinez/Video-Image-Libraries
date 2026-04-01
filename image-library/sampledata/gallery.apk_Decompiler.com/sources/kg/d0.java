package kg;

import gg.a;
import ig.d;
import ig.f;
import jg.c;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d0 implements a {

    /* renamed from: a  reason: collision with root package name */
    public static final d0 f4691a = new Object();
    public static final X b = new X("kotlin.Short", d.k);

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        return Short.valueOf(cVar.o());
    }

    public final f getDescriptor() {
        return b;
    }
}
