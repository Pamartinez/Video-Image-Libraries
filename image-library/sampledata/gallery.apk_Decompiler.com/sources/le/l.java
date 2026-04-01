package Le;

import L1.d;
import java.lang.reflect.Field;
import kotlin.jvm.internal.j;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends m {
    public final /* synthetic */ int e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(Field field, boolean z, int i2) {
        super(field, z);
        this.e = i2;
    }

    public void c(Object[] objArr) {
        switch (this.e) {
            case 1:
                j.e(objArr, "args");
                d.b(this, objArr);
                d(C1192j.n0(objArr));
                return;
            default:
                super.c(objArr);
                return;
        }
    }
}
