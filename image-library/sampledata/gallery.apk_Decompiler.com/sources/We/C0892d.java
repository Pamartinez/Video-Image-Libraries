package We;

import Ae.a;
import Ae.b;
import Ae.c;
import Ae.d;
import Ae.e;
import Ae.f;
import He.C0748d;
import Ke.C0785e;
import Sf.h;
import Sf.n;
import Sf.s;
import a.C0068a;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import me.i;
import ne.C1192j;
import ne.C1195m;
import ne.C1196n;
import ne.C1202t;
import ne.z;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;

/* renamed from: We.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0892d {

    /* renamed from: a  reason: collision with root package name */
    public static final List f3885a;
    public static final Map b;

    /* renamed from: c  reason: collision with root package name */
    public static final Map f3886c;

    static {
        w wVar = v.f4727a;
        int i2 = 0;
        List q0 = C1195m.q0(wVar.b(Boolean.TYPE), wVar.b(Byte.TYPE), wVar.b(Character.TYPE), wVar.b(Double.TYPE), wVar.b(Float.TYPE), wVar.b(Integer.TYPE), wVar.b(Long.TYPE), wVar.b(Short.TYPE));
        f3885a = q0;
        Iterable<C0748d> iterable = q0;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (C0748d dVar : iterable) {
            arrayList.add(new i(C0068a.B(dVar), C0068a.C(dVar)));
        }
        b = z.e0(arrayList);
        Iterable<C0748d> iterable2 = f3885a;
        ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable2, 10));
        for (C0748d dVar2 : iterable2) {
            arrayList2.add(new i(C0068a.C(dVar2), C0068a.B(dVar2)));
        }
        f3886c = z.e0(arrayList2);
        Iterable q02 = C1195m.q0(a.class, b.class, c.class, d.class, e.class, C0785e.class, f.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class);
        ArrayList arrayList3 = new ArrayList(C1196n.w0(q02, 10));
        for (Object next : q02) {
            int i7 = i2 + 1;
            if (i2 >= 0) {
                arrayList3.add(new i((Class) next, Integer.valueOf(i2)));
                i2 = i7;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        z.e0(arrayList3);
    }

    public static final C1235b a(Class cls) {
        j.e(cls, "<this>");
        if (cls.isPrimitive()) {
            throw new IllegalArgumentException("Can't compute ClassId for primitive type: " + cls);
        } else if (cls.isArray()) {
            throw new IllegalArgumentException("Can't compute ClassId for array type: " + cls);
        } else if (cls.getEnclosingMethod() == null && cls.getEnclosingConstructor() == null && cls.getSimpleName().length() != 0) {
            Class<?> declaringClass = cls.getDeclaringClass();
            if (declaringClass != null) {
                return a(declaringClass).d(C1240g.e(cls.getSimpleName()));
            }
            C1236c cVar = new C1236c(cls.getName());
            C1236c e = cVar.e();
            C1240g f = cVar.f();
            j.d(f, "shortName(...)");
            return new C1235b(e, f);
        } else {
            C1236c cVar2 = new C1236c(cls.getName());
            return new C1235b(cVar2.e(), C1236c.j(cVar2.f()), true);
        }
    }

    public static final String b(Class cls) {
        j.e(cls, "<this>");
        if (cls.isPrimitive()) {
            String name = cls.getName();
            switch (name.hashCode()) {
                case -1325958191:
                    if (name.equals("double")) {
                        return "D";
                    }
                    break;
                case 104431:
                    if (name.equals("int")) {
                        return "I";
                    }
                    break;
                case 3039496:
                    if (name.equals("byte")) {
                        return "B";
                    }
                    break;
                case 3052374:
                    if (name.equals("char")) {
                        return "C";
                    }
                    break;
                case 3327612:
                    if (name.equals("long")) {
                        return "J";
                    }
                    break;
                case 3625364:
                    if (name.equals("void")) {
                        return "V";
                    }
                    break;
                case 64711720:
                    if (name.equals("boolean")) {
                        return "Z";
                    }
                    break;
                case 97526364:
                    if (name.equals("float")) {
                        return "F";
                    }
                    break;
                case 109413500:
                    if (name.equals("short")) {
                        return "S";
                    }
                    break;
            }
            throw new IllegalArgumentException("Unsupported primitive type: " + cls);
        } else if (cls.isArray()) {
            return Tf.v.r0(cls.getName(), '.', '/');
        } else {
            return "L" + Tf.v.r0(cls.getName(), '.', '/') + ';';
        }
    }

    public static final List c(Type type) {
        j.e(type, "<this>");
        if (!(type instanceof ParameterizedType)) {
            return C1202t.d;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        if (parameterizedType.getOwnerType() != null) {
            return n.v0(new h(n.s0(C0891c.e, type), C0891c.f, s.d));
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        j.d(actualTypeArguments, "getActualTypeArguments(...)");
        return C1192j.x0(actualTypeArguments);
    }

    public static final ClassLoader d(Class cls) {
        j.e(cls, "<this>");
        ClassLoader classLoader = cls.getClassLoader();
        if (classLoader != null) {
            return classLoader;
        }
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        j.d(systemClassLoader, "getSystemClassLoader(...)");
        return systemClassLoader;
    }
}
