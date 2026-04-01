package Le;

import java.lang.reflect.Field;
import kotlin.jvm.internal.j;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p extends q {
    public final /* synthetic */ int g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(Field field, boolean z, boolean z3, int i2) {
        super(field, z, z3);
        this.g = i2;
    }

    public void c(Object[] objArr) {
        switch (this.g) {
            case 1:
                j.e(objArr, "args");
                super.c(objArr);
                d(C1192j.n0(objArr));
                return;
            default:
                super.c(objArr);
                return;
        }
    }
}
