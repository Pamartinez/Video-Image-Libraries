package Le;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class z implements g {

    /* renamed from: a  reason: collision with root package name */
    public final Method f3532a;
    public final List b;

    /* renamed from: c  reason: collision with root package name */
    public final Class f3533c;

    public z(Method method, List list) {
        this.f3532a = method;
        this.b = list;
        Class<?> returnType = method.getReturnType();
        j.d(returnType, "getReturnType(...)");
        this.f3533c = returnType;
    }

    public final List a() {
        return this.b;
    }

    public final /* bridge */ /* synthetic */ Member b() {
        return null;
    }

    public final Type getReturnType() {
        return this.f3533c;
    }
}
