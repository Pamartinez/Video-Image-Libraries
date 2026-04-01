package We;

import a.C0068a;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.j;
import qf.C1240g;

/* renamed from: We.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0893e extends s {

    /* renamed from: a  reason: collision with root package name */
    public final Annotation f3887a;

    public C0893e(Annotation annotation) {
        j.e(annotation, "annotation");
        this.f3887a = annotation;
    }

    public final ArrayList b() {
        Object obj;
        Annotation annotation = this.f3887a;
        Method[] declaredMethods = C0068a.A(C0068a.w(annotation)).getDeclaredMethods();
        j.d(declaredMethods, "getDeclaredMethods(...)");
        ArrayList arrayList = new ArrayList(declaredMethods.length);
        for (Method method : declaredMethods) {
            Object invoke = method.invoke(annotation, (Object[]) null);
            j.d(invoke, "invoke(...)");
            C1240g e = C1240g.e(method.getName());
            Class<?> cls = invoke.getClass();
            List list = C0892d.f3885a;
            if (Enum.class.isAssignableFrom(cls)) {
                obj = new t(e, (Enum) invoke);
            } else if (invoke instanceof Annotation) {
                obj = new g(e, (Annotation) invoke);
            } else if (invoke instanceof Object[]) {
                obj = new h(e, (Object[]) invoke);
            } else if (invoke instanceof Class) {
                obj = new p(e, (Class) invoke);
            } else {
                obj = new v(e, invoke);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0893e) || this.f3887a != ((C0893e) obj).f3887a) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f3887a);
    }

    public final String toString() {
        return C0893e.class.getName() + ": " + this.f3887a;
    }
}
