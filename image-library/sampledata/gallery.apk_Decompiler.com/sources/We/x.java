package We;

import gf.C1074e;
import java.lang.annotation.Annotation;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x extends w implements C1074e {

    /* renamed from: a  reason: collision with root package name */
    public final Method f3895a;

    public x(Method method) {
        j.e(method, "member");
        this.f3895a = method;
    }

    public final Member b() {
        return this.f3895a;
    }

    public final B f() {
        Type genericReturnType = this.f3895a.getGenericReturnType();
        j.d(genericReturnType, "getGenericReturnType(...)");
        boolean z = genericReturnType instanceof Class;
        if (z) {
            Class cls = (Class) genericReturnType;
            if (cls.isPrimitive()) {
                return new z(cls);
            }
        }
        if ((genericReturnType instanceof GenericArrayType) || (z && ((Class) genericReturnType).isArray())) {
            return new i(genericReturnType);
        }
        if (genericReturnType instanceof WildcardType) {
            return new E((WildcardType) genericReturnType);
        }
        return new q(genericReturnType);
    }

    public final List g() {
        Method method = this.f3895a;
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        j.d(genericParameterTypes, "getGenericParameterTypes(...)");
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        j.d(parameterAnnotations, "getParameterAnnotations(...)");
        return d(genericParameterTypes, parameterAnnotations, method.isVarArgs());
    }

    public final ArrayList getTypeParameters() {
        TypeVariable[] typeParameters = this.f3895a.getTypeParameters();
        j.d(typeParameters, "getTypeParameters(...)");
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable c5 : typeParameters) {
            arrayList.add(new C(c5));
        }
        return arrayList;
    }
}
