package V2;

import java.lang.reflect.Method;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final String f875a;
    public final Class[] b;

    /* renamed from: c  reason: collision with root package name */
    public final Class f876c;
    public final Method d;

    public a(Method method) {
        this.d = method;
        this.f875a = method.getName();
        this.b = method.getParameterTypes();
        this.f876c = method.getReturnType();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (!this.f875a.equals(aVar.f875a) || !this.f876c.equals(aVar.f876c) || !Arrays.equals(this.b, aVar.b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.f875a.hashCode() + 544;
        int hashCode2 = this.f876c.hashCode() + (hashCode * 31) + hashCode;
        return (hashCode2 * 31) + Arrays.hashCode(this.b) + hashCode2;
    }
}
