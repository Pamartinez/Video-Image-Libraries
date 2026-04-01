package Ke;

import Ae.c;
import He.o;
import L1.d;
import Qe.O;
import kotlin.jvm.internal.j;
import me.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class g0 extends n0 implements c {
    public final Object q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g0(F f, O o2) {
        super(f, o2);
        j.e(o2, "descriptor");
        h hVar = h.PUBLICATION;
        this.q = d.p(hVar, new C0786e0(this, 0));
        d.p(hVar, new C0786e0(this, 1));
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final o getGetter() {
        return (C0788f0) this.q.getValue();
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final Object invoke(Object obj, Object obj2) {
        return ((C0788f0) this.q.getValue()).call(obj, obj2);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final k0 t() {
        return (C0788f0) this.q.getValue();
    }
}
