package Le;

import L1.d;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import kotlin.jvm.internal.j;
import me.x;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class q extends w {
    public final /* synthetic */ int e = 0;
    public final boolean f;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(Method method, boolean z, int i2) {
        this(method, (i2 & 2) != 0 ? !Modifier.isStatic(method.getModifiers()) : z, method.getGenericParameterTypes());
    }

    public void c(Object[] objArr) {
        switch (this.e) {
            case 0:
                j.e(objArr, "args");
                d.b(this, objArr);
                if (this.f && C1192j.t0(objArr) == null) {
                    throw new IllegalArgumentException("null is not allowed as a value for this property.");
                }
                return;
            default:
                super.c(objArr);
                return;
        }
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
        field.set(obj, C1192j.t0(objArr));
        return x.f4917a;
    }

    public Object e(Object[] objArr, Object obj) {
        j.e(objArr, "args");
        Object invoke = ((Method) this.f3530a).invoke(obj, Arrays.copyOf(objArr, objArr.length));
        if (this.f) {
            return x.f4917a;
        }
        return invoke;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public q(java.lang.reflect.Method r3, boolean r4, java.lang.reflect.Type[] r5) {
        /*
            r2 = this;
            r0 = 1
            r2.e = r0
            java.lang.reflect.Type r0 = r3.getGenericReturnType()
            java.lang.String r1 = "getGenericReturnType(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            if (r4 == 0) goto L_0x0013
            java.lang.Class r4 = r3.getDeclaringClass()
            goto L_0x0014
        L_0x0013:
            r4 = 0
        L_0x0014:
            r2.<init>(r3, r0, r4, r5)
            java.lang.Class r3 = java.lang.Void.TYPE
            boolean r3 = r0.equals(r3)
            r2.f = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Le.q.<init>(java.lang.reflect.Method, boolean, java.lang.reflect.Type[]):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public q(java.lang.reflect.Field r5, boolean r6, boolean r7) {
        /*
            r4 = this;
            r0 = 0
            r4.e = r0
            java.lang.String r1 = "TYPE"
            java.lang.Class r2 = java.lang.Void.TYPE
            kotlin.jvm.internal.j.d(r2, r1)
            if (r7 == 0) goto L_0x0011
            java.lang.Class r7 = r5.getDeclaringClass()
            goto L_0x0012
        L_0x0011:
            r7 = 0
        L_0x0012:
            java.lang.reflect.Type r1 = r5.getGenericType()
            r3 = 1
            java.lang.reflect.Type[] r3 = new java.lang.reflect.Type[r3]
            r3[r0] = r1
            r4.<init>(r5, r2, r7, r3)
            r4.f = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Le.q.<init>(java.lang.reflect.Field, boolean, boolean):void");
    }
}
