package We;

import Qe.d0;
import Qe.g0;
import Qe.j0;
import Ue.a;
import Ue.b;
import Ue.c;
import gf.C1071b;
import gf.C1072c;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1202t;
import og.k;
import qf.C1236c;
import qf.C1240g;
import qf.C1242i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class w extends s implements C1071b, C1072c {
    public final C0893e a(C1236c cVar) {
        j.e(cVar, "fqName");
        Member b = b();
        j.c(b, "null cannot be cast to non-null type java.lang.reflect.AnnotatedElement");
        Annotation[] declaredAnnotations = ((AnnotatedElement) b).getDeclaredAnnotations();
        if (declaredAnnotations != null) {
            return k.w(declaredAnnotations, cVar);
        }
        return null;
    }

    public abstract Member b();

    public final C1240g c() {
        String name = b().getName();
        if (name != null) {
            return C1240g.e(name);
        }
        return C1242i.f5041a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x011b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.ArrayList d(java.lang.reflect.Type[] r13, java.lang.annotation.Annotation[][] r14, boolean r15) {
        /*
            r12 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r13.length
            r0.<init>(r1)
            We.b r1 = We.C0890b.f3883a
            java.lang.reflect.Member r2 = r12.b()
            java.lang.String r3 = "member"
            kotlin.jvm.internal.j.e(r2, r3)
            We.a r3 = We.C0890b.b
            r4 = 0
            if (r3 != 0) goto L_0x004a
            monitor-enter(r1)
            We.a r3 = We.C0890b.b     // Catch:{ all -> 0x0044 }
            if (r3 != 0) goto L_0x0046
            java.lang.Class r3 = r2.getClass()     // Catch:{ all -> 0x0044 }
            java.lang.String r5 = "getParameters"
            java.lang.reflect.Method r5 = r3.getMethod(r5, r4)     // Catch:{ NoSuchMethodException -> 0x003b }
            java.lang.ClassLoader r3 = We.C0892d.d(r3)     // Catch:{ all -> 0x0044 }
            java.lang.String r6 = "java.lang.reflect.Parameter"
            java.lang.Class r3 = r3.loadClass(r6)     // Catch:{ all -> 0x0044 }
            We.a r6 = new We.a     // Catch:{ all -> 0x0044 }
            java.lang.String r7 = "getName"
            java.lang.reflect.Method r3 = r3.getMethod(r7, r4)     // Catch:{ all -> 0x0044 }
            r6.<init>(r5, r3)     // Catch:{ all -> 0x0044 }
            goto L_0x0040
        L_0x003b:
            We.a r6 = new We.a     // Catch:{ all -> 0x0044 }
            r6.<init>(r4, r4)     // Catch:{ all -> 0x0044 }
        L_0x0040:
            We.C0890b.b = r6     // Catch:{ all -> 0x0044 }
            r3 = r6
            goto L_0x0046
        L_0x0044:
            r12 = move-exception
            goto L_0x0048
        L_0x0046:
            monitor-exit(r1)
            goto L_0x004a
        L_0x0048:
            monitor-exit(r1)
            throw r12
        L_0x004a:
            java.lang.reflect.Method r1 = r3.f3882a
            r5 = 0
            if (r1 != 0) goto L_0x0051
        L_0x004f:
            r2 = r4
            goto L_0x007e
        L_0x0051:
            java.lang.reflect.Method r3 = r3.b
            if (r3 != 0) goto L_0x0056
            goto L_0x004f
        L_0x0056:
            java.lang.Object r1 = r1.invoke(r2, r4)
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.Array<*>"
            kotlin.jvm.internal.j.c(r1, r2)
            java.lang.Object[] r1 = (java.lang.Object[]) r1
            java.util.ArrayList r2 = new java.util.ArrayList
            int r6 = r1.length
            r2.<init>(r6)
            int r6 = r1.length
            r7 = r5
        L_0x0069:
            if (r7 >= r6) goto L_0x007e
            r8 = r1[r7]
            java.lang.Object r8 = r3.invoke(r8, r4)
            java.lang.String r9 = "null cannot be cast to non-null type kotlin.String"
            kotlin.jvm.internal.j.c(r8, r9)
            java.lang.String r8 = (java.lang.String) r8
            r2.add(r8)
            int r7 = r7 + 1
            goto L_0x0069
        L_0x007e:
            if (r2 == 0) goto L_0x0087
            int r1 = r2.size()
            int r3 = r13.length
            int r1 = r1 - r3
            goto L_0x0088
        L_0x0087:
            r1 = r5
        L_0x0088:
            int r3 = r13.length
            r6 = r5
        L_0x008a:
            if (r6 >= r3) goto L_0x0130
            r7 = r13[r6]
            java.lang.String r8 = "type"
            kotlin.jvm.internal.j.e(r7, r8)
            boolean r8 = r7 instanceof java.lang.Class
            if (r8 == 0) goto L_0x00a6
            r9 = r7
            java.lang.Class r9 = (java.lang.Class) r9
            boolean r10 = r9.isPrimitive()
            if (r10 == 0) goto L_0x00a6
            We.z r7 = new We.z
            r7.<init>(r9)
            goto L_0x00cf
        L_0x00a6:
            boolean r9 = r7 instanceof java.lang.reflect.GenericArrayType
            if (r9 != 0) goto L_0x00c9
            if (r8 == 0) goto L_0x00b6
            r8 = r7
            java.lang.Class r8 = (java.lang.Class) r8
            boolean r8 = r8.isArray()
            if (r8 == 0) goto L_0x00b6
            goto L_0x00c9
        L_0x00b6:
            boolean r8 = r7 instanceof java.lang.reflect.WildcardType
            if (r8 == 0) goto L_0x00c3
            We.E r8 = new We.E
            java.lang.reflect.WildcardType r7 = (java.lang.reflect.WildcardType) r7
            r8.<init>(r7)
        L_0x00c1:
            r7 = r8
            goto L_0x00cf
        L_0x00c3:
            We.q r8 = new We.q
            r8.<init>(r7)
            goto L_0x00c1
        L_0x00c9:
            We.i r8 = new We.i
            r8.<init>(r7)
            goto L_0x00c1
        L_0x00cf:
            if (r2 == 0) goto L_0x0118
            int r8 = r6 + r1
            java.lang.Object r8 = ne.C1194l.O0(r8, r2)
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L_0x00dc
            goto L_0x0119
        L_0x00dc:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r15 = "No parameter with index "
            r14.<init>(r15)
            r14.append(r6)
            r15 = 43
            r14.append(r15)
            r14.append(r1)
            java.lang.String r15 = " (name="
            r14.append(r15)
            qf.g r15 = r12.c()
            r14.append(r15)
            java.lang.String r15 = " type="
            r14.append(r15)
            r14.append(r7)
            java.lang.String r15 = ") in "
            r14.append(r15)
            r14.append(r12)
            java.lang.String r12 = r14.toString()
            java.lang.String r12 = r12.toString()
            r13.<init>(r12)
            throw r13
        L_0x0118:
            r8 = r4
        L_0x0119:
            if (r15 == 0) goto L_0x0121
            int r9 = r13.length
            r10 = 1
            int r9 = r9 - r10
            if (r6 != r9) goto L_0x0121
            goto L_0x0122
        L_0x0121:
            r10 = r5
        L_0x0122:
            We.D r9 = new We.D
            r11 = r14[r6]
            r9.<init>(r7, r11, r8, r10)
            r0.add(r9)
            int r6 = r6 + 1
            goto L_0x008a
        L_0x0130:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: We.w.d(java.lang.reflect.Type[], java.lang.annotation.Annotation[][], boolean):java.util.ArrayList");
    }

    public final j0 e() {
        int modifiers = b().getModifiers();
        if (Modifier.isPublic(modifiers)) {
            return g0.f3670c;
        }
        if (Modifier.isPrivate(modifiers)) {
            return d0.f3667c;
        }
        if (!Modifier.isProtected(modifiers)) {
            return a.f3825c;
        }
        if (Modifier.isStatic(modifiers)) {
            return c.f3827c;
        }
        return b.f3826c;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof w) || !j.a(b(), ((w) obj).b())) {
            return false;
        }
        return true;
    }

    public final Collection getAnnotations() {
        Collection collection;
        Member b = b();
        j.c(b, "null cannot be cast to non-null type java.lang.reflect.AnnotatedElement");
        Annotation[] declaredAnnotations = ((AnnotatedElement) b).getDeclaredAnnotations();
        if (declaredAnnotations != null) {
            collection = k.z(declaredAnnotations);
        } else {
            collection = C1202t.d;
        }
        return collection;
    }

    public final int hashCode() {
        return b().hashCode();
    }

    public final String toString() {
        return getClass().getName() + ": " + b();
    }
}
