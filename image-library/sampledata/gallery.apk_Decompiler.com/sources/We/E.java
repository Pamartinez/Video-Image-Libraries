package We;

import gf.C1073d;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class E extends B implements C1073d {

    /* renamed from: a  reason: collision with root package name */
    public final WildcardType f3881a;

    public E(WildcardType wildcardType) {
        this.f3881a = wildcardType;
    }

    public final Type b() {
        return this.f3881a;
    }

    public final B c() {
        WildcardType wildcardType = this.f3881a;
        Type[] upperBounds = wildcardType.getUpperBounds();
        Type[] lowerBounds = wildcardType.getLowerBounds();
        if (upperBounds.length > 1 || lowerBounds.length > 1) {
            throw new UnsupportedOperationException("Wildcard types with many bounds are not yet supported: " + wildcardType);
        } else if (lowerBounds.length == 1) {
            Object v02 = C1192j.v0(lowerBounds);
            j.d(v02, "single(...)");
            Type type = (Type) v02;
            boolean z = type instanceof Class;
            if (z) {
                Class cls = (Class) type;
                if (cls.isPrimitive()) {
                    return new z(cls);
                }
            }
            if ((type instanceof GenericArrayType) || (z && ((Class) type).isArray())) {
                return new i(type);
            }
            if (type instanceof WildcardType) {
                return new E((WildcardType) type);
            }
            return new q(type);
        } else if (upperBounds.length != 1) {
            return null;
        } else {
            Type type2 = (Type) C1192j.v0(upperBounds);
            if (j.a(type2, Object.class)) {
                return null;
            }
            j.b(type2);
            boolean z3 = type2 instanceof Class;
            if (z3) {
                Class cls2 = (Class) type2;
                if (cls2.isPrimitive()) {
                    return new z(cls2);
                }
            }
            if ((type2 instanceof GenericArrayType) || (z3 && ((Class) type2).isArray())) {
                return new i(type2);
            }
            if (type2 instanceof WildcardType) {
                return new E((WildcardType) type2);
            }
            return new q(type2);
        }
    }

    public final Collection getAnnotations() {
        return C1202t.d;
    }
}
