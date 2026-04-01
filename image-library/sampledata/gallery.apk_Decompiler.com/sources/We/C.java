package We;

import gf.C1071b;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1202t;
import og.k;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C extends s implements C1071b {

    /* renamed from: a  reason: collision with root package name */
    public final TypeVariable f3878a;

    public C(TypeVariable typeVariable) {
        j.e(typeVariable, "typeVariable");
        this.f3878a = typeVariable;
    }

    public final C0893e a(C1236c cVar) {
        AnnotatedElement annotatedElement;
        Annotation[] declaredAnnotations;
        j.e(cVar, "fqName");
        TypeVariable typeVariable = this.f3878a;
        if (typeVariable instanceof AnnotatedElement) {
            annotatedElement = (AnnotatedElement) typeVariable;
        } else {
            annotatedElement = null;
        }
        if (annotatedElement == null || (declaredAnnotations = annotatedElement.getDeclaredAnnotations()) == null) {
            return null;
        }
        return k.w(declaredAnnotations, cVar);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C)) {
            return false;
        }
        if (j.a(this.f3878a, ((C) obj).f3878a)) {
            return true;
        }
        return false;
    }

    public final Collection getAnnotations() {
        AnnotatedElement annotatedElement;
        Collection collection;
        Annotation[] declaredAnnotations;
        TypeVariable typeVariable = this.f3878a;
        if (typeVariable instanceof AnnotatedElement) {
            annotatedElement = (AnnotatedElement) typeVariable;
        } else {
            annotatedElement = null;
        }
        if (annotatedElement == null || (declaredAnnotations = annotatedElement.getDeclaredAnnotations()) == null) {
            collection = C1202t.d;
        } else {
            collection = k.z(declaredAnnotations);
        }
        return collection;
    }

    public final int hashCode() {
        return this.f3878a.hashCode();
    }

    public final String toString() {
        return C.class.getName() + ": " + this.f3878a;
    }
}
