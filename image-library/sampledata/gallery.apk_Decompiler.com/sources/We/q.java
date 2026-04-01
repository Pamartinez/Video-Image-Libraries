package We;

import gf.C1073d;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1196n;
import ne.C1202t;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q extends B implements C1073d {

    /* renamed from: a  reason: collision with root package name */
    public final Type f3892a;
    public final s b;

    public q(Type type) {
        s sVar;
        j.e(type, "reflectType");
        this.f3892a = type;
        if (type instanceof Class) {
            sVar = new o((Class) type);
        } else if (type instanceof TypeVariable) {
            sVar = new C((TypeVariable) type);
        } else if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            j.c(rawType, "null cannot be cast to non-null type java.lang.Class<*>");
            sVar = new o((Class) rawType);
        } else {
            throw new IllegalStateException("Not a classifier type (" + type.getClass() + "): " + type);
        }
        this.b = sVar;
    }

    public final C0893e a(C1236c cVar) {
        j.e(cVar, "fqName");
        return null;
    }

    public final Type b() {
        return this.f3892a;
    }

    public final ArrayList c() {
        Object obj;
        Object iVar;
        Iterable<Type> c5 = C0892d.c(this.f3892a);
        ArrayList arrayList = new ArrayList(C1196n.w0(c5, 10));
        for (Type type : c5) {
            j.e(type, "type");
            boolean z = type instanceof Class;
            if (z) {
                Class cls = (Class) type;
                if (cls.isPrimitive()) {
                    obj = new z(cls);
                    arrayList.add(obj);
                }
            }
            if ((type instanceof GenericArrayType) || (z && ((Class) type).isArray())) {
                iVar = new i(type);
            } else if (type instanceof WildcardType) {
                iVar = new E((WildcardType) type);
            } else {
                iVar = new q(type);
            }
            obj = iVar;
            arrayList.add(obj);
        }
        return arrayList;
    }

    public final boolean d() {
        boolean z;
        Type type = this.f3892a;
        if (type instanceof Class) {
            TypeVariable[] typeParameters = ((Class) type).getTypeParameters();
            j.d(typeParameters, "getTypeParameters(...)");
            if (typeParameters.length == 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                return true;
            }
        }
        return false;
    }

    public final Collection getAnnotations() {
        return C1202t.d;
    }
}
