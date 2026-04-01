package kotlin.jvm.internal;

import Ae.a;
import Ae.b;
import Ae.c;
import Ae.e;
import Ae.f;
import He.C0748d;
import Ke.C0785e;
import Ke.v0;
import Tf.n;
import a.C0068a;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import me.i;
import ne.C1195m;
import ne.C1196n;
import ne.z;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements C0748d, c {
    public static final Map e;
    public static final HashMap f;
    public static final LinkedHashMap g;
    public final Class d;

    static {
        Iterable q0 = C1195m.q0(a.class, b.class, c.class, Ae.d.class, e.class, C0785e.class, f.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class, C0785e.class);
        ArrayList arrayList = new ArrayList(C1196n.w0(q0, 10));
        int i2 = 0;
        for (Object next : q0) {
            int i7 = i2 + 1;
            if (i2 >= 0) {
                arrayList.add(new i((Class) next, Integer.valueOf(i2)));
                i2 = i7;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        e = z.e0(arrayList);
        HashMap hashMap = new HashMap();
        hashMap.put("boolean", "kotlin.Boolean");
        hashMap.put("char", "kotlin.Char");
        hashMap.put("byte", "kotlin.Byte");
        hashMap.put("short", "kotlin.Short");
        hashMap.put("int", "kotlin.Int");
        hashMap.put("float", "kotlin.Float");
        hashMap.put("long", "kotlin.Long");
        hashMap.put("double", "kotlin.Double");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("java.lang.Boolean", "kotlin.Boolean");
        hashMap2.put("java.lang.Character", "kotlin.Char");
        hashMap2.put("java.lang.Byte", "kotlin.Byte");
        hashMap2.put("java.lang.Short", "kotlin.Short");
        hashMap2.put("java.lang.Integer", "kotlin.Int");
        hashMap2.put("java.lang.Float", "kotlin.Float");
        hashMap2.put("java.lang.Long", "kotlin.Long");
        hashMap2.put("java.lang.Double", "kotlin.Double");
        HashMap hashMap3 = new HashMap();
        hashMap3.put("java.lang.Object", "kotlin.Any");
        hashMap3.put("java.lang.String", "kotlin.String");
        hashMap3.put("java.lang.CharSequence", "kotlin.CharSequence");
        hashMap3.put("java.lang.Throwable", "kotlin.Throwable");
        hashMap3.put("java.lang.Cloneable", "kotlin.Cloneable");
        hashMap3.put("java.lang.Number", "kotlin.Number");
        hashMap3.put("java.lang.Comparable", "kotlin.Comparable");
        hashMap3.put("java.lang.Enum", "kotlin.Enum");
        hashMap3.put("java.lang.annotation.Annotation", "kotlin.Annotation");
        hashMap3.put("java.lang.Iterable", "kotlin.collections.Iterable");
        hashMap3.put("java.util.Iterator", "kotlin.collections.Iterator");
        hashMap3.put("java.util.Collection", "kotlin.collections.Collection");
        hashMap3.put("java.util.List", "kotlin.collections.List");
        hashMap3.put("java.util.Set", "kotlin.collections.Set");
        hashMap3.put("java.util.ListIterator", "kotlin.collections.ListIterator");
        hashMap3.put("java.util.Map", "kotlin.collections.Map");
        hashMap3.put("java.util.Map$Entry", "kotlin.collections.Map.Entry");
        hashMap3.put("kotlin.jvm.internal.StringCompanionObject", "kotlin.String.Companion");
        hashMap3.put("kotlin.jvm.internal.EnumCompanionObject", "kotlin.Enum.Companion");
        hashMap3.putAll(hashMap);
        hashMap3.putAll(hashMap2);
        Collection<String> values = hashMap.values();
        j.d(values, "<get-values>(...)");
        for (String str : values) {
            StringBuilder sb2 = new StringBuilder("kotlin.jvm.internal.");
            j.b(str);
            sb2.append(n.O0(str, '.'));
            sb2.append("CompanionObject");
            hashMap3.put(sb2.toString(), str.concat(".Companion"));
        }
        for (Map.Entry entry : e.entrySet()) {
            int intValue = ((Number) entry.getValue()).intValue();
            String name = ((Class) entry.getKey()).getName();
            hashMap3.put(name, "kotlin.Function" + intValue);
        }
        f = hashMap3;
        LinkedHashMap linkedHashMap = new LinkedHashMap(z.Z(hashMap3.size()));
        for (Map.Entry entry2 : hashMap3.entrySet()) {
            Object key = entry2.getKey();
            String str2 = (String) entry2.getValue();
            j.b(str2);
            linkedHashMap.put(key, n.O0(str2, '.'));
        }
        g = linkedHashMap;
    }

    public d(Class cls) {
        j.e(cls, "jClass");
        this.d = cls;
    }

    public final Class b() {
        return this.d;
    }

    public final Collection d() {
        throw new v0(3);
    }

    public final List e() {
        throw new v0(3);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof d) || !C0068a.B(this).equals(C0068a.B((C0748d) obj))) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return C0068a.B(this).hashCode();
    }

    public final boolean l() {
        throw new v0(3);
    }

    public final Object m() {
        throw new v0(3);
    }

    public final String n() {
        String str;
        Class cls = this.d;
        j.e(cls, "jClass");
        String str2 = null;
        if (cls.isAnonymousClass() || cls.isLocalClass()) {
            return null;
        }
        boolean isArray = cls.isArray();
        HashMap hashMap = f;
        if (isArray) {
            Class<?> componentType = cls.getComponentType();
            if (componentType.isPrimitive() && (str = (String) hashMap.get(componentType.getName())) != null) {
                str2 = str.concat("Array");
            }
            if (str2 == null) {
                return "kotlin.Array";
            }
            return str2;
        }
        String str3 = (String) hashMap.get(cls.getName());
        if (str3 == null) {
            return cls.getCanonicalName();
        }
        return str3;
    }

    public final String o() {
        String str;
        Class cls = this.d;
        j.e(cls, "jClass");
        String str2 = null;
        if (cls.isAnonymousClass()) {
            return null;
        }
        if (cls.isLocalClass()) {
            String simpleName = cls.getSimpleName();
            Method enclosingMethod = cls.getEnclosingMethod();
            if (enclosingMethod != null) {
                return n.N0(simpleName, enclosingMethod.getName() + '$');
            }
            Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
            if (enclosingConstructor == null) {
                return n.M0('$', simpleName, simpleName);
            }
            return n.N0(simpleName, enclosingConstructor.getName() + '$');
        }
        boolean isArray = cls.isArray();
        LinkedHashMap linkedHashMap = g;
        if (isArray) {
            Class<?> componentType = cls.getComponentType();
            if (componentType.isPrimitive() && (str = (String) linkedHashMap.get(componentType.getName())) != null) {
                str2 = str.concat("Array");
            }
            if (str2 == null) {
                return "Array";
            }
            return str2;
        }
        String str3 = (String) linkedHashMap.get(cls.getName());
        if (str3 == null) {
            return cls.getSimpleName();
        }
        return str3;
    }

    public final String toString() {
        return this.d + " (Kotlin reflection is not available)";
    }
}
