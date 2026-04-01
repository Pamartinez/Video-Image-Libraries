package Ke;

import D0.e;
import Qe.O;
import Tf.m;
import Tf.n;
import Tf.v;
import We.C0892d;
import ig.i;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.c;
import kotlin.jvm.internal.j;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class F implements c {
    public static final m d = new m("<v#(\\d+)>");

    /* JADX WARNING: type inference failed for: r5v5, types: [java.util.List] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void f(java.util.ArrayList r4, java.util.ArrayList r5, boolean r6) {
        /*
            java.lang.Object r0 = ne.C1194l.U0(r5)
            java.lang.Class<kotlin.jvm.internal.e> r1 = kotlin.jvm.internal.e.class
            boolean r0 = kotlin.jvm.internal.j.a(r0, r1)
            r2 = 0
            if (r0 == 0) goto L_0x0017
            int r0 = r5.size()
            int r0 = r0 + -1
            java.util.List r5 = r5.subList(r2, r0)
        L_0x0017:
            r0 = r5
            java.util.Collection r0 = (java.util.Collection) r0
            r4.addAll(r0)
            int r5 = r5.size()
            int r5 = r5 + 31
            int r5 = r5 / 32
        L_0x0025:
            if (r2 >= r5) goto L_0x0034
            java.lang.String r0 = "TYPE"
            java.lang.Class r3 = java.lang.Integer.TYPE
            kotlin.jvm.internal.j.d(r3, r0)
            r4.add(r3)
            int r2 = r2 + 1
            goto L_0x0025
        L_0x0034:
            if (r6 == 0) goto L_0x0037
            goto L_0x0039
        L_0x0037:
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
        L_0x0039:
            r4.add(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.F.f(java.util.ArrayList, java.util.ArrayList, boolean):void");
    }

    public static Method s(Class cls, String str, Class[] clsArr, Class cls2, boolean z) {
        Class R;
        Method s;
        if (z) {
            clsArr[0] = cls;
        }
        Method w = w(cls, str, clsArr, cls2);
        if (w != null) {
            return w;
        }
        Class superclass = cls.getSuperclass();
        if (superclass != null && (s = s(superclass, str, clsArr, cls2, z)) != null) {
            return s;
        }
        i g = j.g(cls.getInterfaces());
        while (g.hasNext()) {
            Class cls3 = (Class) g.next();
            j.b(cls3);
            Method s5 = s(cls3, str, clsArr, cls2, z);
            if (s5 != null) {
                return s5;
            }
            if (z && (R = He.F.R(C0892d.d(cls3), cls3.getName().concat("$DefaultImpls"))) != null) {
                clsArr[0] = cls3;
                Method w6 = w(R, str, clsArr, cls2);
                if (w6 != null) {
                    return w6;
                }
            }
        }
        return null;
    }

    public static Constructor v(Class cls, ArrayList arrayList) {
        try {
            Class[] clsArr = (Class[]) arrayList.toArray(new Class[0]);
            return cls.getDeclaredConstructor((Class[]) Arrays.copyOf(clsArr, clsArr.length));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static Method w(Class cls, String str, Class[] clsArr, Class cls2) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, (Class[]) Arrays.copyOf(clsArr, clsArr.length));
            if (j.a(declaredMethod.getReturnType(), cls2)) {
                return declaredMethod;
            }
            Method[] declaredMethods = cls.getDeclaredMethods();
            j.d(declaredMethods, "getDeclaredMethods(...)");
            for (Method method : declaredMethods) {
                if (j.a(method.getName(), str) && j.a(method.getReturnType(), cls2) && Arrays.equals(method.getParameterTypes(), clsArr)) {
                    return method;
                }
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public final Method g(String str, String str2, boolean z) {
        j.e(str, "name");
        j.e(str2, "desc");
        if (str.equals("<init>")) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(b());
        }
        e t = t(str2, true);
        f(arrayList, (ArrayList) t.e, false);
        Class cls = (Class) t.f;
        j.b(cls);
        return s(q(), str.concat("$default"), (Class[]) arrayList.toArray(new Class[0]), cls, z);
    }

    public final Method h(String str, String str2) {
        Method s;
        j.e(str, "name");
        j.e(str2, "desc");
        if (str.equals("<init>")) {
            return null;
        }
        e t = t(str2, true);
        Class[] clsArr = (Class[]) ((ArrayList) t.e).toArray(new Class[0]);
        Class cls = (Class) t.f;
        j.b(cls);
        Method s5 = s(q(), str, clsArr, cls, false);
        if (s5 != null) {
            return s5;
        }
        if (!q().isInterface() || (s = s(Object.class, str, clsArr, cls, false)) == null) {
            return null;
        }
        return s;
    }

    public abstract Collection i();

    public abstract Collection j(C1240g gVar);

    public abstract O k(int i2);

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0021 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Collection p(Af.p r8, Ke.D r9) {
        /*
            r7 = this;
            java.lang.String r0 = "scope"
            kotlin.jvm.internal.j.e(r8, r0)
            java.lang.String r0 = "belonginess"
            kotlin.jvm.internal.j.e(r9, r0)
            Ke.E r0 = new Ke.E
            r1 = 6
            r0.<init>((int) r1, (java.lang.Object) r7)
            r7 = 3
            r1 = 0
            java.util.Collection r7 = He.F.F(r8, r1, r7)
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Iterator r7 = r7.iterator()
        L_0x0021:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L_0x0068
            java.lang.Object r2 = r7.next()
            Qe.l r2 = (Qe.C0822l) r2
            boolean r3 = r2 instanceof Qe.C0814d
            if (r3 == 0) goto L_0x0061
            r3 = r2
            Qe.d r3 = (Qe.C0814d) r3
            Qe.p r4 = r3.getVisibility()
            Qe.p r5 = Qe.C0827q.f3677h
            boolean r4 = kotlin.jvm.internal.j.a(r4, r5)
            if (r4 != 0) goto L_0x0061
            Qe.c r3 = r3.b()
            r3.getClass()
            Qe.c r4 = Qe.C0813c.FAKE_OVERRIDE
            r5 = 1
            r6 = 0
            if (r3 == r4) goto L_0x004f
            r3 = r5
            goto L_0x0050
        L_0x004f:
            r3 = r6
        L_0x0050:
            Ke.D r4 = Ke.D.DECLARED
            if (r9 != r4) goto L_0x0055
            goto L_0x0056
        L_0x0055:
            r5 = r6
        L_0x0056:
            if (r3 != r5) goto L_0x0061
            me.x r3 = me.x.f4917a
            java.lang.Object r2 = r2.v(r0, r3)
            Ke.s r2 = (Ke.C0800s) r2
            goto L_0x0062
        L_0x0061:
            r2 = r1
        L_0x0062:
            if (r2 == 0) goto L_0x0021
            r8.add(r2)
            goto L_0x0021
        L_0x0068:
            java.util.List r7 = ne.C1194l.k1(r8)
            java.util.Collection r7 = (java.util.Collection) r7
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.F.p(Af.p, Ke.D):java.util.Collection");
    }

    public Class q() {
        Class b = b();
        List list = C0892d.f3885a;
        j.e(b, "<this>");
        Class cls = (Class) C0892d.f3886c.get(b);
        if (cls == null) {
            return b();
        }
        return cls;
    }

    public abstract Collection r(C1240g gVar);

    public final e t(String str, boolean z) {
        Class cls;
        int i2;
        ArrayList arrayList = new ArrayList();
        int i7 = 1;
        while (str.charAt(i7) != ')') {
            int i8 = i7;
            while (str.charAt(i8) == '[') {
                i8++;
            }
            char charAt = str.charAt(i8);
            if (n.v0("VZCBSIFJD", charAt)) {
                i2 = i8 + 1;
            } else if (charAt == 'L') {
                i2 = n.A0(str, ';', i7, 4) + 1;
            } else {
                throw new v0("Unknown type prefix in the method signature: ".concat(str), 0);
            }
            arrayList.add(u(i7, i2, str));
            i7 = i2;
        }
        if (z) {
            cls = u(i7 + 1, str.length(), str);
        } else {
            cls = null;
        }
        return new e(11, (Object) arrayList, (Object) cls);
    }

    public final Class u(int i2, int i7, String str) {
        char charAt = str.charAt(i2);
        if (charAt == 'F') {
            return Float.TYPE;
        }
        if (charAt == 'L') {
            ClassLoader d2 = C0892d.d(b());
            String substring = str.substring(i2 + 1, i7 - 1);
            j.d(substring, "substring(...)");
            Class<?> loadClass = d2.loadClass(v.r0(substring, '/', '.'));
            j.d(loadClass, "loadClass(...)");
            return loadClass;
        } else if (charAt == 'S') {
            return Short.TYPE;
        } else {
            if (charAt == 'V') {
                Class cls = Void.TYPE;
                j.d(cls, "TYPE");
                return cls;
            } else if (charAt == 'I') {
                return Integer.TYPE;
            } else {
                if (charAt == 'J') {
                    return Long.TYPE;
                }
                if (charAt == 'Z') {
                    return Boolean.TYPE;
                }
                if (charAt != '[') {
                    switch (charAt) {
                        case 'B':
                            return Byte.TYPE;
                        case 'C':
                            return Character.TYPE;
                        case 'D':
                            return Double.TYPE;
                        default:
                            throw new v0("Unknown type prefix in the method signature: ".concat(str), 0);
                    }
                } else {
                    Class u = u(i2 + 1, i7, str);
                    C1236c cVar = E0.f3489a;
                    j.e(u, "<this>");
                    return Array.newInstance(u, 0).getClass();
                }
            }
        }
    }
}
