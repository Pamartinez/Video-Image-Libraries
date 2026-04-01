package Le;

import L1.d;
import java.lang.reflect.Method;
import kotlin.jvm.internal.j;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v extends q {
    public final /* synthetic */ int g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v(Method method, boolean z, int i2, int i7) {
        super(method, z, i2);
        this.g = i7;
    }

    public final Object call(Object[] objArr) {
        Object[] objArr2;
        Object[] objArr3;
        switch (this.g) {
            case 0:
                j.e(objArr, "args");
                d.b(this, objArr);
                Object obj = objArr[0];
                if (objArr.length <= 1) {
                    objArr2 = new Object[0];
                } else {
                    objArr2 = C1192j.i0(objArr, 1, objArr.length);
                }
                return e(objArr2, obj);
            case 1:
                j.e(objArr, "args");
                d.b(this, objArr);
                d(C1192j.n0(objArr));
                if (objArr.length <= 1) {
                    objArr3 = new Object[0];
                } else {
                    objArr3 = C1192j.i0(objArr, 1, objArr.length);
                }
                return e(objArr3, (Object) null);
            default:
                j.e(objArr, "args");
                d.b(this, objArr);
                return e(objArr, (Object) null);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public v(Method method) {
        super(method, false, 6);
        this.g = 0;
        j.e(method, "method");
    }
}
