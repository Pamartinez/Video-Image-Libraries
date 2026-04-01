package kg;

import gg.a;
import ig.f;
import jg.c;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class W extends C1133n {
    public final V b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public W(a aVar) {
        super(aVar);
        j.e(aVar, "primitiveSerializer");
        this.b = new V(aVar.getDescriptor());
    }

    public final Object a() {
        return (U) e(h());
    }

    public final int b(Object obj) {
        U u = (U) obj;
        j.e(u, "<this>");
        return u.d();
    }

    public final Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        return c(cVar);
    }

    public final Object f(Object obj) {
        U u = (U) obj;
        j.e(u, "<this>");
        return u.a();
    }

    public final void g(int i2, Object obj, Object obj2) {
        j.e((U) obj, "<this>");
        throw new IllegalStateException("This method lead to boxing and must not be used, use Builder.append instead");
    }

    public final f getDescriptor() {
        return this.b;
    }

    public abstract Object h();
}
