package Le;

import L1.d;
import java.lang.reflect.Method;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends q implements f {
    public final Object g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public r(Method method, Object obj) {
        super(method, false, 4);
        j.e(method, "method");
        this.g = obj;
    }

    public final Object call(Object[] objArr) {
        j.e(objArr, "args");
        d.b(this, objArr);
        return e(objArr, this.g);
    }
}
