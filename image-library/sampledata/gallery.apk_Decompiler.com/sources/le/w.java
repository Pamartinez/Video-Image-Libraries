package Le;

import D0.c;
import L1.d;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import ne.C1192j;
import ne.C1195m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class w implements g {

    /* renamed from: a  reason: collision with root package name */
    public final Member f3530a;
    public final Type b;

    /* renamed from: c  reason: collision with root package name */
    public final Class f3531c;
    public final List d;

    public w(Member member, Type type, Class cls, Type[] typeArr) {
        List list;
        this.f3530a = member;
        this.b = type;
        this.f3531c = cls;
        if (cls != null) {
            c cVar = new c(2);
            cVar.a(cls);
            cVar.b(typeArr);
            ArrayList arrayList = cVar.d;
            list = C1195m.q0(arrayList.toArray(new Type[arrayList.size()]));
        } else {
            list = C1192j.x0(typeArr);
        }
        this.d = list;
    }

    public final List a() {
        return this.d;
    }

    public final Member b() {
        return this.f3530a;
    }

    public void c(Object[] objArr) {
        d.b(this, objArr);
    }

    public final void d(Object obj) {
        if (obj == null || !this.f3530a.getDeclaringClass().isInstance(obj)) {
            throw new IllegalArgumentException("An object member requires the object instance passed as the first argument.");
        }
    }

    public final Type getReturnType() {
        return this.b;
    }
}
