package kg;

import gg.a;
import jg.c;
import kotlin.jvm.internal.j;

/* renamed from: kg.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1120a implements a {
    public abstract Object a();

    public abstract int b(Object obj);

    public final Object c(c cVar) {
        j.e(cVar, "decoder");
        Object a7 = a();
        int b = b(a7);
        jg.a a10 = cVar.a(getDescriptor());
        while (true) {
            int d = a10.d(getDescriptor());
            if (d != -1) {
                d(a10, d + b, a7);
            } else {
                a10.b(getDescriptor());
                return f(a7);
            }
        }
    }

    public abstract void d(jg.a aVar, int i2, Object obj);

    public Object deserialize(c cVar) {
        j.e(cVar, "decoder");
        return c(cVar);
    }

    public abstract Object e(Object obj);

    public abstract Object f(Object obj);
}
