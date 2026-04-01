package We;

import Df.C0736b;
import Sf.g;
import Sf.n;
import gf.C1071b;
import gf.C1074e;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1202t;
import og.k;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends s implements C1071b, C1074e {

    /* renamed from: a  reason: collision with root package name */
    public final Class f3891a;

    public o(Class cls) {
        j.e(cls, "klass");
        this.f3891a = cls;
    }

    public final C0893e a(C1236c cVar) {
        Annotation[] declaredAnnotations;
        j.e(cVar, "fqName");
        Class cls = this.f3891a;
        if (cls == null || (declaredAnnotations = cls.getDeclaredAnnotations()) == null) {
            return null;
        }
        return k.w(declaredAnnotations, cVar);
    }

    public final Collection b() {
        Field[] declaredFields = this.f3891a.getDeclaredFields();
        j.d(declaredFields, "getDeclaredFields(...)");
        return n.v0(n.t0(new g(C1192j.b0(declaredFields), false, l.d), m.d));
    }

    public final C1236c c() {
        return C0892d.a(this.f3891a).a();
    }

    public final Collection d() {
        Method[] declaredMethods = this.f3891a.getDeclaredMethods();
        j.d(declaredMethods, "getDeclaredMethods(...)");
        return n.v0(n.t0(n.p0(C1192j.b0(declaredMethods), new C0736b(10, this)), n.d));
    }

    public final C1240g e() {
        Class cls = this.f3891a;
        if (!cls.isAnonymousClass()) {
            return C1240g.e(cls.getSimpleName());
        }
        String name = cls.getName();
        int E02 = Tf.n.E0(0, 6, name, ".");
        if (E02 != -1) {
            name = name.substring(1 + E02, name.length());
            j.d(name, "substring(...)");
        }
        return C1240g.e(name);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof o)) {
            return false;
        }
        if (j.a(this.f3891a, ((o) obj).f3891a)) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.ArrayList f() {
        /*
            r9 = this;
            java.lang.String r0 = "clazz"
            java.lang.Class r9 = r9.f3891a
            kotlin.jvm.internal.j.e(r9, r0)
            D0.f r0 = o1.C0246a.g
            r2 = 0
            if (r0 != 0) goto L_0x003c
            java.lang.Class<java.lang.Class> r0 = java.lang.Class.class
            D0.f r3 = new D0.f     // Catch:{ NoSuchMethodException -> 0x002f }
            java.lang.String r1 = "isSealed"
            java.lang.reflect.Method r4 = r0.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x002f }
            java.lang.String r1 = "getPermittedSubclasses"
            java.lang.reflect.Method r5 = r0.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x002f }
            java.lang.String r1 = "isRecord"
            java.lang.reflect.Method r6 = r0.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x002f }
            java.lang.String r1 = "getRecordComponents"
            java.lang.reflect.Method r7 = r0.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x002f }
            r8 = 8
            r3.<init>(r4, r5, r6, r7, r8)     // Catch:{ NoSuchMethodException -> 0x002f }
            r0 = r3
            goto L_0x003a
        L_0x002f:
            D0.f r1 = new D0.f
            r6 = 8
            r3 = r2
            r4 = r2
            r5 = r2
            r1.<init>(r2, r3, r4, r5, r6)
            r0 = r1
        L_0x003a:
            o1.C0246a.g = r0
        L_0x003c:
            java.lang.Object r0 = r0.f106h
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            if (r0 != 0) goto L_0x0043
            goto L_0x004a
        L_0x0043:
            java.lang.Object r9 = r0.invoke(r9, r2)
            r2 = r9
            java.lang.Object[] r2 = (java.lang.Object[]) r2
        L_0x004a:
            r9 = 0
            if (r2 != 0) goto L_0x004f
            java.lang.Object[] r2 = new java.lang.Object[r9]
        L_0x004f:
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r2.length
            r0.<init>(r1)
            int r1 = r2.length
        L_0x0056:
            if (r9 >= r1) goto L_0x0065
            r3 = r2[r9]
            We.A r4 = new We.A
            r4.<init>(r3)
            r0.add(r4)
            int r9 = r9 + 1
            goto L_0x0056
        L_0x0065:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: We.o.f():java.util.ArrayList");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.Boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean g() {
        /*
            r9 = this;
            java.lang.String r0 = "clazz"
            java.lang.Class r9 = r9.f3891a
            kotlin.jvm.internal.j.e(r9, r0)
            D0.f r0 = o1.C0246a.g
            r2 = 0
            if (r0 != 0) goto L_0x003c
            java.lang.Class<java.lang.Class> r0 = java.lang.Class.class
            D0.f r3 = new D0.f     // Catch:{ NoSuchMethodException -> 0x002f }
            java.lang.String r1 = "isSealed"
            java.lang.reflect.Method r4 = r0.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x002f }
            java.lang.String r1 = "getPermittedSubclasses"
            java.lang.reflect.Method r5 = r0.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x002f }
            java.lang.String r1 = "isRecord"
            java.lang.reflect.Method r6 = r0.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x002f }
            java.lang.String r1 = "getRecordComponents"
            java.lang.reflect.Method r7 = r0.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x002f }
            r8 = 8
            r3.<init>(r4, r5, r6, r7, r8)     // Catch:{ NoSuchMethodException -> 0x002f }
            r0 = r3
            goto L_0x003a
        L_0x002f:
            D0.f r1 = new D0.f
            r6 = 8
            r3 = r2
            r4 = r2
            r5 = r2
            r1.<init>(r2, r3, r4, r5, r6)
            r0 = r1
        L_0x003a:
            o1.C0246a.g = r0
        L_0x003c:
            java.lang.Object r0 = r0.g
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            if (r0 != 0) goto L_0x0043
            goto L_0x004f
        L_0x0043:
            java.lang.Object r9 = r0.invoke(r9, r2)
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.Boolean"
            kotlin.jvm.internal.j.c(r9, r0)
            r2 = r9
            java.lang.Boolean r2 = (java.lang.Boolean) r2
        L_0x004f:
            if (r2 == 0) goto L_0x0056
            boolean r9 = r2.booleanValue()
            return r9
        L_0x0056:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: We.o.g():boolean");
    }

    public final Collection getAnnotations() {
        Collection collection;
        Annotation[] declaredAnnotations;
        Class cls = this.f3891a;
        if (cls == null || (declaredAnnotations = cls.getDeclaredAnnotations()) == null) {
            collection = C1202t.d;
        } else {
            collection = k.z(declaredAnnotations);
        }
        return collection;
    }

    public final ArrayList getTypeParameters() {
        TypeVariable[] typeParameters = this.f3891a.getTypeParameters();
        j.d(typeParameters, "getTypeParameters(...)");
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable c5 : typeParameters) {
            arrayList.add(new C(c5));
        }
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.Boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean h() {
        /*
            r9 = this;
            java.lang.String r0 = "clazz"
            java.lang.Class r9 = r9.f3891a
            kotlin.jvm.internal.j.e(r9, r0)
            D0.f r0 = o1.C0246a.g
            r2 = 0
            if (r0 != 0) goto L_0x003c
            java.lang.Class<java.lang.Class> r0 = java.lang.Class.class
            D0.f r3 = new D0.f     // Catch:{ NoSuchMethodException -> 0x002f }
            java.lang.String r1 = "isSealed"
            java.lang.reflect.Method r4 = r0.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x002f }
            java.lang.String r1 = "getPermittedSubclasses"
            java.lang.reflect.Method r5 = r0.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x002f }
            java.lang.String r1 = "isRecord"
            java.lang.reflect.Method r6 = r0.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x002f }
            java.lang.String r1 = "getRecordComponents"
            java.lang.reflect.Method r7 = r0.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x002f }
            r8 = 8
            r3.<init>(r4, r5, r6, r7, r8)     // Catch:{ NoSuchMethodException -> 0x002f }
            r0 = r3
            goto L_0x003a
        L_0x002f:
            D0.f r1 = new D0.f
            r6 = 8
            r3 = r2
            r4 = r2
            r5 = r2
            r1.<init>(r2, r3, r4, r5, r6)
            r0 = r1
        L_0x003a:
            o1.C0246a.g = r0
        L_0x003c:
            java.lang.Object r0 = r0.e
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            if (r0 != 0) goto L_0x0043
            goto L_0x004f
        L_0x0043:
            java.lang.Object r9 = r0.invoke(r9, r2)
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.Boolean"
            kotlin.jvm.internal.j.c(r9, r0)
            r2 = r9
            java.lang.Boolean r2 = (java.lang.Boolean) r2
        L_0x004f:
            if (r2 == 0) goto L_0x0056
            boolean r9 = r2.booleanValue()
            return r9
        L_0x0056:
            r9 = 0
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: We.o.h():boolean");
    }

    public final int hashCode() {
        return this.f3891a.hashCode();
    }

    public final String toString() {
        return o.class.getName() + ": " + this.f3891a;
    }
}
