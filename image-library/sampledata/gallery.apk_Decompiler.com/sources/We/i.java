package We;

import gf.C1073d;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends B implements C1073d {

    /* renamed from: a  reason: collision with root package name */
    public final Type f3889a;
    public final B b;

    /* renamed from: c  reason: collision with root package name */
    public final C1202t f3890c;

    public i(Type type) {
        B b5;
        B iVar;
        this.f3889a = type;
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            j.d(genericComponentType, "getGenericComponentType(...)");
            boolean z = genericComponentType instanceof Class;
            if (z) {
                Class cls = (Class) genericComponentType;
                if (cls.isPrimitive()) {
                    b5 = new z(cls);
                    this.b = b5;
                    this.f3890c = C1202t.d;
                }
            }
            if ((genericComponentType instanceof GenericArrayType) || (z && ((Class) genericComponentType).isArray())) {
                iVar = new i(genericComponentType);
            } else if (genericComponentType instanceof WildcardType) {
                iVar = new E((WildcardType) genericComponentType);
            } else {
                iVar = new q(genericComponentType);
            }
        } else {
            if (type instanceof Class) {
                Class cls2 = (Class) type;
                if (cls2.isArray()) {
                    Class<?> componentType = cls2.getComponentType();
                    j.d(componentType, "getComponentType(...)");
                    if (componentType.isPrimitive()) {
                        iVar = new z(componentType);
                    } else if ((componentType instanceof GenericArrayType) || componentType.isArray()) {
                        iVar = new i(componentType);
                    } else if (componentType instanceof WildcardType) {
                        iVar = new E((WildcardType) componentType);
                    } else {
                        iVar = new q(componentType);
                    }
                }
            }
            throw new IllegalArgumentException("Not an array type (" + type.getClass() + "): " + type);
        }
        b5 = iVar;
        this.b = b5;
        this.f3890c = C1202t.d;
    }

    public final Type b() {
        return this.f3889a;
    }

    public final Collection getAnnotations() {
        return this.f3890c;
    }
}
