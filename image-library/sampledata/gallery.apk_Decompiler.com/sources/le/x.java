package Le;

import L1.d;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.jvm.internal.j;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x extends z implements f {
    public final Object d;

    public x(Method method, Object obj) {
        super(method, C1202t.d);
        this.d = obj;
    }

    public final Object call(Object[] objArr) {
        j.e(objArr, "args");
        d.b(this, objArr);
        return this.f3532a.invoke(this.d, Arrays.copyOf(objArr, objArr.length));
    }
}
