package Le;

import We.C0892d;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1196n;

/* renamed from: Le.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0810c implements g {

    /* renamed from: a  reason: collision with root package name */
    public final Class f3526a;
    public final ArrayList b;

    /* renamed from: c  reason: collision with root package name */
    public final C0808a f3527c;
    public final List d;
    public final ArrayList e;
    public final ArrayList f;
    public final ArrayList g;

    public C0810c(Class cls, ArrayList arrayList, C0808a aVar, C0809b bVar, List list) {
        j.e(cls, "jClass");
        j.e(aVar, "callMode");
        j.e(bVar, "origin");
        j.e(list, "methods");
        this.f3526a = cls;
        this.b = arrayList;
        this.f3527c = aVar;
        this.d = list;
        Iterable<Method> iterable = list;
        ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable, 10));
        for (Method genericReturnType : iterable) {
            arrayList2.add(genericReturnType.getGenericReturnType());
        }
        this.e = arrayList2;
        Iterable<Method> iterable2 = this.d;
        ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable2, 10));
        for (Method returnType : iterable2) {
            Class<?> returnType2 = returnType.getReturnType();
            j.b(returnType2);
            List list2 = C0892d.f3885a;
            Class<?> cls2 = (Class) C0892d.f3886c.get(returnType2);
            if (cls2 != null) {
                returnType2 = cls2;
            }
            arrayList3.add(returnType2);
        }
        this.f = arrayList3;
        Iterable<Method> iterable3 = this.d;
        ArrayList arrayList4 = new ArrayList(C1196n.w0(iterable3, 10));
        for (Method defaultValue : iterable3) {
            arrayList4.add(defaultValue.getDefaultValue());
        }
        this.g = arrayList4;
        if (this.f3527c == C0808a.POSITIONAL_CALL && bVar == C0809b.JAVA) {
            ArrayList arrayList5 = this.b;
            j.e(arrayList5, "<this>");
            ArrayList arrayList6 = new ArrayList(C1196n.w0(arrayList5, 10));
            boolean z = false;
            for (Object next : arrayList5) {
                boolean z3 = true;
                if (!z && j.a(next, "value")) {
                    z = true;
                    z3 = false;
                }
                if (z3) {
                    arrayList6.add(next);
                }
            }
            if (!arrayList6.isEmpty()) {
                throw new UnsupportedOperationException("Positional call of a Java annotation constructor is allowed only if there are no parameters or one parameter named \"value\". This restriction exists because Java annotations (in contrast to Kotlin)do not impose any order on their arguments. Use KCallable#callBy instead.");
            }
        }
    }

    public final List a() {
        return this.e;
    }

    public final /* bridge */ /* synthetic */ Member b() {
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007a, code lost:
        if (r11.isInstance(r8) != false) goto L_0x007c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object call(java.lang.Object[] r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            java.lang.String r2 = "args"
            kotlin.jvm.internal.j.e(r1, r2)
            L1.d.b(r17, r18)
            java.util.ArrayList r2 = new java.util.ArrayList
            int r3 = r1.length
            r2.<init>(r3)
            int r3 = r1.length
            r4 = 0
            r5 = r4
            r6 = r5
        L_0x0016:
            java.util.ArrayList r7 = r0.b
            if (r5 >= r3) goto L_0x0131
            r8 = r1[r5]
            int r9 = r6 + 1
            java.util.ArrayList r10 = r0.f
            if (r8 != 0) goto L_0x002f
            Le.a r11 = r0.f3527c
            Le.a r12 = Le.C0808a.CALL_BY_NAME
            if (r11 != r12) goto L_0x002f
            java.util.ArrayList r8 = r0.g
            java.lang.Object r8 = r8.get(r6)
            goto L_0x007c
        L_0x002f:
            java.lang.Object r11 = r10.get(r6)
            java.lang.Class r11 = (java.lang.Class) r11
            boolean r12 = r8 instanceof java.lang.Class
            if (r12 == 0) goto L_0x003b
        L_0x0039:
            r8 = 0
            goto L_0x007c
        L_0x003b:
            boolean r12 = r8 instanceof He.C0748d
            if (r12 == 0) goto L_0x0046
            He.d r8 = (He.C0748d) r8
            java.lang.Class r8 = a.C0068a.A(r8)
            goto L_0x0076
        L_0x0046:
            boolean r12 = r8 instanceof java.lang.Object[]
            if (r12 == 0) goto L_0x0076
            r12 = r8
            java.lang.Object[] r12 = (java.lang.Object[]) r12
            boolean r14 = r12 instanceof java.lang.Class[]
            if (r14 == 0) goto L_0x0052
            goto L_0x0039
        L_0x0052:
            boolean r14 = r12 instanceof He.C0748d[]
            if (r14 == 0) goto L_0x0075
            He.d[] r8 = (He.C0748d[]) r8
            java.util.ArrayList r12 = new java.util.ArrayList
            int r14 = r8.length
            r12.<init>(r14)
            int r14 = r8.length
            r15 = r4
        L_0x0060:
            if (r15 >= r14) goto L_0x006e
            r16 = r8[r15]
            java.lang.Class r13 = a.C0068a.A(r16)
            r12.add(r13)
            int r15 = r15 + 1
            goto L_0x0060
        L_0x006e:
            java.lang.Class[] r8 = new java.lang.Class[r4]
            java.lang.Object[] r8 = r12.toArray(r8)
            goto L_0x0076
        L_0x0075:
            r8 = r12
        L_0x0076:
            boolean r11 = r11.isInstance(r8)
            if (r11 == 0) goto L_0x0039
        L_0x007c:
            if (r8 != 0) goto L_0x0129
            java.lang.Object r0 = r7.get(r6)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r1 = r10.get(r6)
            java.lang.Class r1 = (java.lang.Class) r1
            java.lang.Class<java.lang.Class> r2 = java.lang.Class.class
            boolean r3 = kotlin.jvm.internal.j.a(r1, r2)
            if (r3 == 0) goto L_0x009b
            java.lang.Class<He.d> r1 = He.C0748d.class
            kotlin.jvm.internal.w r2 = kotlin.jvm.internal.v.f4727a
            He.d r1 = r2.b(r1)
            goto L_0x00b8
        L_0x009b:
            boolean r3 = r1.isArray()
            if (r3 == 0) goto L_0x00b4
            java.lang.Class r3 = r1.getComponentType()
            boolean r2 = kotlin.jvm.internal.j.a(r3, r2)
            if (r2 == 0) goto L_0x00b4
            java.lang.Class<He.d[]> r1 = He.C0748d[].class
            kotlin.jvm.internal.w r2 = kotlin.jvm.internal.v.f4727a
            He.d r1 = r2.b(r1)
            goto L_0x00b8
        L_0x00b4:
            He.d r1 = a.C0068a.D(r1)
        L_0x00b8:
            java.lang.String r2 = r1.n()
            java.lang.Class<java.lang.Object[]> r3 = java.lang.Object[].class
            kotlin.jvm.internal.w r4 = kotlin.jvm.internal.v.f4727a
            He.d r3 = r4.b(r3)
            java.lang.String r3 = r3.n()
            boolean r2 = kotlin.jvm.internal.j.a(r2, r3)
            if (r2 == 0) goto L_0x0101
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r1.n()
            r2.append(r3)
            r3 = 60
            r2.append(r3)
            java.lang.Class r1 = a.C0068a.A(r1)
            java.lang.Class r1 = r1.getComponentType()
            java.lang.String r3 = "getComponentType(...)"
            kotlin.jvm.internal.j.d(r1, r3)
            He.d r1 = a.C0068a.D(r1)
            java.lang.String r1 = r1.n()
            r2.append(r1)
            r1 = 62
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            goto L_0x0105
        L_0x0101:
            java.lang.String r1 = r1.n()
        L_0x0105:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Argument #"
            r3.<init>(r4)
            r3.append(r6)
            r4 = 32
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = " is not of the required type "
            r3.append(r0)
            r3.append(r1)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x0129:
            r2.add(r8)
            int r5 = r5 + 1
            r6 = r9
            goto L_0x0016
        L_0x0131:
            java.util.ArrayList r1 = ne.C1194l.r1(r7, r2)
            java.util.Map r1 = ne.z.e0(r1)
            java.util.List r2 = r0.d
            java.lang.Class r0 = r0.f3526a
            java.lang.Object r0 = He.F.y(r0, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Le.C0810c.call(java.lang.Object[]):java.lang.Object");
    }

    public final Type getReturnType() {
        return this.f3526a;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ C0810c(java.lang.Class r8, java.util.ArrayList r9, Le.C0808a r10, Le.C0809b r11) {
        /*
            r7 = this;
            java.util.ArrayList r5 = new java.util.ArrayList
            r0 = 10
            int r0 = ne.C1196n.w0(r9, r0)
            r5.<init>(r0)
            java.util.Iterator r6 = r9.iterator()
        L_0x000f:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x0024
            java.lang.Object r0 = r6.next()
            java.lang.String r0 = (java.lang.String) r0
            r1 = 0
            java.lang.reflect.Method r0 = r8.getDeclaredMethod(r0, r1)
            r5.add(r0)
            goto L_0x000f
        L_0x0024:
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Le.C0810c.<init>(java.lang.Class, java.util.ArrayList, Le.a, Le.b):void");
    }
}
