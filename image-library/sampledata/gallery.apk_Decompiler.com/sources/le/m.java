package Le;

import java.lang.reflect.Field;
import kotlin.jvm.internal.j;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class m extends w {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public m(java.lang.reflect.Field r3, boolean r4) {
        /*
            r2 = this;
            java.lang.reflect.Type r0 = r3.getGenericType()
            java.lang.String r1 = "getGenericType(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            if (r4 == 0) goto L_0x0010
            java.lang.Class r4 = r3.getDeclaringClass()
            goto L_0x0011
        L_0x0010:
            r4 = 0
        L_0x0011:
            r1 = 0
            java.lang.reflect.Type[] r1 = new java.lang.reflect.Type[r1]
            r2.<init>(r3, r0, r4, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Le.m.<init>(java.lang.reflect.Field, boolean):void");
    }

    public Object call(Object[] objArr) {
        Object obj;
        j.e(objArr, "args");
        c(objArr);
        Field field = (Field) this.f3530a;
        if (this.f3531c != null) {
            obj = C1192j.m0(objArr);
        } else {
            obj = null;
        }
        return field.get(obj);
    }
}
