package Le;

import java.lang.reflect.Field;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n extends q implements f {
    public final Object g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public n(Field field, boolean z, Object obj) {
        super(field, z, false);
        j.e(field, "field");
        this.g = obj;
    }

    public final Object call(Object[] objArr) {
        j.e(objArr, "args");
        c(objArr);
        ((Field) this.f3530a).set(this.g, C1192j.m0(objArr));
        return x.f4917a;
    }
}
