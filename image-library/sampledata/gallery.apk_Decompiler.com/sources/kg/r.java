package kg;

import gg.a;
import i.C0212a;
import ig.d;
import ig.f;
import jg.c;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r implements a {

    /* renamed from: a  reason: collision with root package name */
    public static final r f4716a = new Object();
    public static final X b = new X("kotlin.time.Duration", d.l);

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        int i2 = Uf.a.g;
        String B = cVar.B();
        j.e(B, "value");
        try {
            return new Uf.a(com.samsung.context.sdk.samsunganalytics.internal.sender.c.f(B));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(C0212a.m("Invalid ISO duration string format: '", B, "'."), e);
        }
    }

    public final f getDescriptor() {
        return b;
    }
}
