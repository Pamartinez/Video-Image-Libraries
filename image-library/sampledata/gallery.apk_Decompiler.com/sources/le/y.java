package Le;

import L1.d;
import java.util.Arrays;
import kotlin.jvm.internal.j;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class y extends z {
    public final Object call(Object[] objArr) {
        Object[] objArr2;
        j.e(objArr, "args");
        d.b(this, objArr);
        Object obj = objArr[0];
        if (objArr.length <= 1) {
            objArr2 = new Object[0];
        } else {
            objArr2 = C1192j.i0(objArr, 1, objArr.length);
        }
        return this.f3532a.invoke(obj, Arrays.copyOf(objArr2, objArr2.length));
    }
}
