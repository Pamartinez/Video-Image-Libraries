package Le;

import L1.d;
import java.lang.reflect.Field;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j extends m implements f {
    public final Object e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public j(Object obj, Field field) {
        super(field, false);
        kotlin.jvm.internal.j.e(field, "field");
        this.e = obj;
    }

    public final Object call(Object[] objArr) {
        kotlin.jvm.internal.j.e(objArr, "args");
        d.b(this, objArr);
        return ((Field) this.f3530a).get(this.e);
    }
}
