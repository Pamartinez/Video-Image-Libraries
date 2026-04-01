package Le;

import A0.l;
import Ge.c;
import Ge.e;
import Ke.E0;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;
import o1.C0246a;
import oe.C1214c;
import re.C1245a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C implements g {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f3524a;
    public final g b;

    /* renamed from: c  reason: collision with root package name */
    public final Member f3525c;
    public final l d;
    public final e[] e;
    public final boolean f;

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00d5, code lost:
        if (Ne.i.F(r5) == true) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0137, code lost:
        if ((r11 instanceof Le.f) != false) goto L_0x0155;
     */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x02d3  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x02d9  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x02e2  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02f1  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0334  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x0351 A[EDGE_INSN: B:196:0x0351->B:175:0x0351 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01d4  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01e5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C(Le.g r11, Qe.C0814d r12, boolean r13) {
        /*
            r10 = this;
            java.lang.String r0 = "descriptor"
            kotlin.jvm.internal.j.e(r12, r0)
            r10.<init>()
            r10.f3524a = r13
            boolean r0 = r11 instanceof Le.t
            java.lang.String r1 = "getValueParameters(...)"
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L_0x00a0
            Te.u r0 = r12.H()
            if (r0 != 0) goto L_0x001c
            Te.u r0 = r12.E()
        L_0x001c:
            if (r0 == 0) goto L_0x0023
            Hf.x r0 = r0.getType()
            goto L_0x0024
        L_0x0023:
            r0 = r2
        L_0x0024:
            if (r0 == 0) goto L_0x00a0
            boolean r4 = tf.C1305i.h(r0)
            if (r4 == 0) goto L_0x00a0
            if (r13 == 0) goto L_0x005b
            java.util.List r13 = r12.B()
            kotlin.jvm.internal.j.d(r13, r1)
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            boolean r4 = r13 instanceof java.util.Collection
            if (r4 == 0) goto L_0x0045
            r4 = r13
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x0045
            goto L_0x00a0
        L_0x0045:
            java.util.Iterator r13 = r13.iterator()
        L_0x0049:
            boolean r4 = r13.hasNext()
            if (r4 == 0) goto L_0x00a0
            java.lang.Object r4 = r13.next()
            Te.Q r4 = (Te.Q) r4
            boolean r4 = r4.F0()
            if (r4 == 0) goto L_0x0049
        L_0x005b:
            Hf.B r13 = Hf.C0754c.b(r0)
            java.util.ArrayList r13 = a.C0068a.E(r13)
            kotlin.jvm.internal.j.b(r13)
            java.util.ArrayList r0 = new java.util.ArrayList
            r4 = 10
            int r4 = ne.C1196n.w0(r13, r4)
            r0.<init>(r4)
            java.util.Iterator r13 = r13.iterator()
        L_0x0075:
            boolean r4 = r13.hasNext()
            if (r4 == 0) goto L_0x008e
            java.lang.Object r4 = r13.next()
            java.lang.reflect.Method r4 = (java.lang.reflect.Method) r4
            r5 = r11
            Le.t r5 = (Le.t) r5
            java.lang.Object r5 = r5.g
            java.lang.Object r4 = r4.invoke(r5, r2)
            r0.add(r4)
            goto L_0x0075
        L_0x008e:
            java.lang.Object[] r13 = new java.lang.Object[r3]
            java.lang.Object[] r13 = r0.toArray(r13)
            Le.u r0 = new Le.u
            Le.q r11 = (Le.q) r11
            java.lang.reflect.Member r11 = r11.f3530a
            java.lang.reflect.Method r11 = (java.lang.reflect.Method) r11
            r0.<init>(r11, r13)
            r11 = r0
        L_0x00a0:
            r10.b = r11
            java.lang.reflect.Member r13 = r11.b()
            r10.f3525c = r13
            Hf.x r13 = r12.getReturnType()
            kotlin.jvm.internal.j.b(r13)
            boolean r0 = r12 instanceof Qe.C0831v
            r4 = 1
            if (r0 == 0) goto L_0x00d9
            r5 = r12
            Qe.v r5 = (Qe.C0831v) r5
            boolean r5 = r5.isSuspend()
            if (r5 == 0) goto L_0x00d9
            Hf.B r5 = tf.C1305i.i(r13)
            if (r5 == 0) goto L_0x00ce
            Hf.X r6 = Hf.X.d(r13)
            Hf.d0 r7 = Hf.d0.INVARIANT
            Hf.x r5 = r6.i(r5, r7)
            goto L_0x00cf
        L_0x00ce:
            r5 = r2
        L_0x00cf:
            if (r5 == 0) goto L_0x00d9
            boolean r5 = Ne.i.F(r5)
            if (r5 != r4) goto L_0x00d9
        L_0x00d7:
            r13 = r2
            goto L_0x0114
        L_0x00d9:
            java.lang.Class r13 = a.C0068a.Z(r13)
            if (r13 == 0) goto L_0x00d7
            java.lang.String r5 = "box-impl"
            java.lang.reflect.Method r6 = a.C0068a.z(r13, r12)     // Catch:{ NoSuchMethodException -> 0x00f2 }
            java.lang.Class r6 = r6.getReturnType()     // Catch:{ NoSuchMethodException -> 0x00f2 }
            java.lang.Class[] r6 = new java.lang.Class[]{r6}     // Catch:{ NoSuchMethodException -> 0x00f2 }
            java.lang.reflect.Method r13 = r13.getDeclaredMethod(r5, r6)     // Catch:{ NoSuchMethodException -> 0x00f2 }
            goto L_0x0114
        L_0x00f2:
            Ke.v0 r10 = new Ke.v0
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "No box method found in inline class: "
            r11.<init>(r0)
            r11.append(r13)
            java.lang.String r13 = " (calling "
            r11.append(r13)
            r11.append(r12)
            r12 = 41
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            r12 = 0
            r10.<init>(r11, r12)
            throw r10
        L_0x0114:
            boolean r5 = tf.C1305i.a(r12)
            if (r5 == 0) goto L_0x0125
            A0.l r11 = new A0.l
            Ge.e r12 = Ge.e.g
            java.util.List[] r0 = new java.util.List[r3]
            r11.<init>((Ge.e) r12, (java.util.List[]) r0, (java.lang.reflect.Method) r13)
            goto L_0x02c7
        L_0x0125:
            boolean r5 = r11 instanceof Le.t
            java.lang.String r6 = "getContainingDeclaration(...)"
            r7 = -1
            if (r5 != 0) goto L_0x0155
            boolean r5 = r11 instanceof Le.u
            if (r5 == 0) goto L_0x0131
            goto L_0x0155
        L_0x0131:
            boolean r5 = r12 instanceof Qe.C0821k
            if (r5 == 0) goto L_0x013c
            boolean r5 = r11 instanceof Le.f
            if (r5 == 0) goto L_0x013a
            goto L_0x0155
        L_0x013a:
            r7 = r3
            goto L_0x0155
        L_0x013c:
            Te.u r5 = r12.E()
            if (r5 == 0) goto L_0x013a
            boolean r5 = r11 instanceof Le.f
            if (r5 != 0) goto L_0x013a
            Qe.l r5 = r12.g()
            kotlin.jvm.internal.j.d(r5, r6)
            boolean r5 = tf.C1305i.f(r5)
            if (r5 == 0) goto L_0x0154
            goto L_0x013a
        L_0x0154:
            r7 = r4
        L_0x0155:
            boolean r5 = r11 instanceof Le.u
            if (r5 == 0) goto L_0x0161
            r5 = r11
            Le.u r5 = (Le.u) r5
            java.lang.Object[] r5 = r5.g
            int r5 = r5.length
            int r5 = -r5
            goto L_0x0162
        L_0x0161:
            r5 = r7
        L_0x0162:
            java.lang.reflect.Member r11 = r11.b()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            Te.u r9 = r12.H()
            if (r9 == 0) goto L_0x0176
            Hf.x r9 = r9.getType()
            goto L_0x0177
        L_0x0176:
            r9 = r2
        L_0x0177:
            if (r9 == 0) goto L_0x017e
            r8.add(r9)
            goto L_0x01ec
        L_0x017e:
            boolean r9 = r12 instanceof Qe.C0821k
            if (r9 == 0) goto L_0x01a7
            r11 = r12
            Qe.k r11 = (Qe.C0821k) r11
            Qe.f r11 = r11.W()
            java.lang.String r6 = "getConstructedClass(...)"
            kotlin.jvm.internal.j.d(r11, r6)
            boolean r6 = r11.s()
            if (r6 == 0) goto L_0x01ec
            Qe.l r11 = r11.g()
            java.lang.String r6 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            kotlin.jvm.internal.j.c(r11, r6)
            Qe.f r11 = (Qe.C0816f) r11
            Hf.B r11 = r11.i()
            r8.add(r11)
            goto L_0x01ec
        L_0x01a7:
            Qe.l r9 = r12.g()
            kotlin.jvm.internal.j.d(r9, r6)
            boolean r6 = r9 instanceof Qe.C0816f
            if (r6 == 0) goto L_0x01ec
            Qe.f r9 = (Qe.C0816f) r9
            boolean r6 = tf.C1305i.f(r9)
            if (r6 == 0) goto L_0x01ec
            if (r11 == 0) goto L_0x01d1
            java.lang.Class r11 = r11.getDeclaringClass()
            if (r11 != 0) goto L_0x01c4
            r11 = r3
            goto L_0x01cd
        L_0x01c4:
            He.d r11 = a.C0068a.D(r11)
            boolean r11 = r11.l()
            r11 = r11 ^ r4
        L_0x01cd:
            if (r11 != r4) goto L_0x01d1
            r11 = r4
            goto L_0x01d2
        L_0x01d1:
            r11 = r3
        L_0x01d2:
            if (r11 == 0) goto L_0x01e5
            Hf.B r11 = r9.i()
            java.lang.String r6 = "getDefaultType(...)"
            kotlin.jvm.internal.j.d(r11, r6)
            Hf.c0 r11 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.J(r11)
            r8.add(r11)
            goto L_0x01ec
        L_0x01e5:
            Hf.B r11 = r9.i()
            r8.add(r11)
        L_0x01ec:
            java.util.List r11 = r12.B()
            kotlin.jvm.internal.j.d(r11, r1)
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.Iterator r11 = r11.iterator()
        L_0x01f9:
            boolean r1 = r11.hasNext()
            if (r1 == 0) goto L_0x020f
            java.lang.Object r1 = r11.next()
            Te.Q r1 = (Te.Q) r1
            Te.S r1 = (Te.S) r1
            Hf.x r1 = r1.getType()
            r8.add(r1)
            goto L_0x01f9
        L_0x020f:
            boolean r11 = r10.f3524a
            if (r11 == 0) goto L_0x023c
            java.util.Iterator r11 = r8.iterator()
            r1 = r3
        L_0x0218:
            boolean r6 = r11.hasNext()
            if (r6 == 0) goto L_0x0236
            java.lang.Object r6 = r11.next()
            Hf.x r6 = (Hf.C0774x) r6
            Hf.B r6 = Hf.C0754c.b(r6)
            java.util.ArrayList r6 = a.C0068a.E(r6)
            if (r6 == 0) goto L_0x0233
            int r6 = r6.size()
            goto L_0x0234
        L_0x0233:
            r6 = r4
        L_0x0234:
            int r1 = r1 + r6
            goto L_0x0218
        L_0x0236:
            int r1 = r1 + 31
            int r1 = r1 / 32
            int r1 = r1 + r4
            goto L_0x023d
        L_0x023c:
            r1 = r3
        L_0x023d:
            if (r0 == 0) goto L_0x024a
            r11 = r12
            Qe.v r11 = (Qe.C0831v) r11
            boolean r11 = r11.isSuspend()
            if (r11 == 0) goto L_0x024a
            r11 = r4
            goto L_0x024b
        L_0x024a:
            r11 = r3
        L_0x024b:
            int r1 = r1 + r11
            java.util.Iterator r11 = r8.iterator()
            r0 = r3
        L_0x0251:
            boolean r6 = r11.hasNext()
            if (r6 == 0) goto L_0x026f
            java.lang.Object r6 = r11.next()
            Hf.x r6 = (Hf.C0774x) r6
            Hf.B r6 = Hf.C0754c.b(r6)
            java.util.ArrayList r6 = a.C0068a.E(r6)
            if (r6 == 0) goto L_0x026c
            int r6 = r6.size()
            goto L_0x026d
        L_0x026c:
            r6 = r4
        L_0x026d:
            int r0 = r0 + r6
            goto L_0x0251
        L_0x026f:
            int r0 = r0 + r5
            int r0 = r0 + r1
            boolean r11 = r10.f3524a
            int r1 = L2.a.o(r10)
            if (r1 != r0) goto L_0x0354
            int r11 = java.lang.Math.max(r7, r3)
            int r1 = r8.size()
            int r1 = r1 + r7
            Ge.e r11 = B1.a.Z(r11, r1)
            java.util.List[] r1 = new java.util.List[r0]
            r5 = r3
        L_0x0289:
            if (r5 >= r0) goto L_0x02c1
            int r6 = r11.d
            int r9 = r11.e
            if (r5 > r9) goto L_0x0295
            if (r6 > r5) goto L_0x0295
            r6 = r4
            goto L_0x0296
        L_0x0295:
            r6 = r3
        L_0x0296:
            if (r6 == 0) goto L_0x02bb
            int r6 = r5 - r7
            java.lang.Object r6 = r8.get(r6)
            Hf.x r6 = (Hf.C0774x) r6
            Hf.B r6 = Hf.C0754c.b(r6)
            java.util.ArrayList r9 = a.C0068a.E(r6)
            if (r9 != 0) goto L_0x02bc
            java.lang.Class r6 = a.C0068a.Z(r6)
            if (r6 == 0) goto L_0x02bb
            java.lang.reflect.Method r6 = a.C0068a.z(r6, r12)
            if (r6 == 0) goto L_0x02bb
            java.util.List r9 = o1.C0246a.e0(r6)
            goto L_0x02bc
        L_0x02bb:
            r9 = r2
        L_0x02bc:
            r1[r5] = r9
            int r5 = r5 + 1
            goto L_0x0289
        L_0x02c1:
            A0.l r12 = new A0.l
            r12.<init>((Ge.e) r11, (java.util.List[]) r1, (java.lang.reflect.Method) r13)
            r11 = r12
        L_0x02c7:
            r10.d = r11
            oe.c r12 = o1.C0246a.R()
            Le.g r13 = r10.b
            boolean r0 = r13 instanceof Le.u
            if (r0 == 0) goto L_0x02d9
            Le.u r13 = (Le.u) r13
            java.lang.Object[] r13 = r13.g
            int r13 = r13.length
            goto L_0x02e0
        L_0x02d9:
            boolean r13 = r13 instanceof Le.t
            if (r13 == 0) goto L_0x02df
            r13 = r4
            goto L_0x02e0
        L_0x02df:
            r13 = r3
        L_0x02e0:
            if (r13 <= 0) goto L_0x02e9
            Ge.e r0 = B1.a.Z(r3, r13)
            r12.add(r0)
        L_0x02e9:
            java.lang.Object r11 = r11.f
            java.util.List[] r11 = (java.util.List[]) r11
            int r0 = r11.length
            r1 = r3
        L_0x02ef:
            if (r1 >= r0) goto L_0x0307
            r2 = r11[r1]
            if (r2 == 0) goto L_0x02fa
            int r2 = r2.size()
            goto L_0x02fb
        L_0x02fa:
            r2 = r4
        L_0x02fb:
            int r2 = r2 + r13
            Ge.e r13 = B1.a.Z(r13, r2)
            r12.add(r13)
            int r1 = r1 + 1
            r13 = r2
            goto L_0x02ef
        L_0x0307:
            oe.c r11 = o1.C0246a.K(r12)
            Ge.e[] r12 = new Ge.e[r3]
            java.lang.Object[] r11 = r11.toArray(r12)
            Ge.e[] r11 = (Ge.e[]) r11
            r10.e = r11
            A0.l r11 = r10.d
            java.lang.Object r11 = r11.e
            Ge.e r11 = (Ge.e) r11
            boolean r12 = r11 instanceof java.util.Collection
            if (r12 == 0) goto L_0x0329
            r12 = r11
            java.util.Collection r12 = (java.util.Collection) r12
            boolean r12 = r12.isEmpty()
            if (r12 == 0) goto L_0x0329
            goto L_0x0351
        L_0x0329:
            java.util.Iterator r11 = r11.iterator()
        L_0x032d:
            r12 = r11
            Ge.d r12 = (Ge.d) r12
            boolean r12 = r12.f
            if (r12 == 0) goto L_0x0351
            r12 = r11
            ne.y r12 = (ne.y) r12
            int r12 = r12.nextInt()
            A0.l r13 = r10.d
            java.lang.Object r13 = r13.f
            java.util.List[] r13 = (java.util.List[]) r13
            r12 = r13[r12]
            if (r12 != 0) goto L_0x0347
        L_0x0345:
            r12 = r3
            goto L_0x034e
        L_0x0347:
            int r12 = r12.size()
            if (r12 <= r4) goto L_0x0345
            r12 = r4
        L_0x034e:
            if (r12 == 0) goto L_0x032d
            r3 = r4
        L_0x0351:
            r10.f = r3
            return
        L_0x0354:
            Ke.v0 r13 = new Ke.v0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Inconsistent number of parameters in the descriptor and Java reflection object: "
            r1.<init>(r2)
            int r2 = L2.a.o(r10)
            r1.append(r2)
            java.lang.String r2 = " != "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = "\nCalling: "
            r1.append(r0)
            r1.append(r12)
            java.lang.String r12 = "\nParameter types: "
            r1.append(r12)
            Le.g r10 = r10.b
            java.util.List r10 = r10.a()
            r1.append(r10)
            java.lang.String r10 = ")\nDefault: "
            r1.append(r10)
            r1.append(r11)
            java.lang.String r10 = r1.toString()
            r11 = 0
            r13.<init>(r10, r11)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: Le.C.<init>(Le.g, Qe.d, boolean):void");
    }

    public final List a() {
        return this.b.a();
    }

    public final Member b() {
        return this.f3525c;
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [Ge.c, Ge.e] */
    /* JADX WARNING: type inference failed for: r2v7, types: [Ge.c, Ge.e] */
    public final e c(int i2) {
        e[] eVarArr = this.e;
        if (i2 >= 0 && i2 < eVarArr.length) {
            return eVarArr[i2];
        }
        if (eVarArr.length == 0) {
            return new c(i2, i2, 1);
        }
        int length = ((e) C1192j.t0(eVarArr)).e + 1 + (i2 - eVarArr.length);
        return new c(length, length, 1);
    }

    public final Object call(Object[] objArr) {
        Object invoke;
        Object obj;
        Method method;
        Object obj2;
        j.e(objArr, "args");
        l lVar = this.d;
        e eVar = (e) lVar.e;
        List[] listArr = (List[]) lVar.f;
        Method method2 = (Method) lVar.g;
        boolean isEmpty = eVar.isEmpty();
        int i2 = eVar.e;
        int i7 = eVar.d;
        if (!isEmpty) {
            if (this.f) {
                C1214c cVar = new C1214c(objArr.length);
                for (int i8 = 0; i8 < i7; i8++) {
                    cVar.add(objArr[i8]);
                }
                if (i7 <= i2) {
                    while (true) {
                        List<Method> list = listArr[i7];
                        Object obj3 = objArr[i7];
                        if (list != null) {
                            for (Method method3 : list) {
                                if (obj3 != null) {
                                    obj2 = method3.invoke(obj3, (Object[]) null);
                                } else {
                                    Class<?> returnType = method3.getReturnType();
                                    j.d(returnType, "getReturnType(...)");
                                    obj2 = E0.e(returnType);
                                }
                                cVar.add(obj2);
                            }
                        } else {
                            cVar.add(obj3);
                        }
                        if (i7 == i2) {
                            break;
                        }
                        i7++;
                    }
                }
                int i10 = i2 + 1;
                int length = objArr.length - 1;
                if (i10 <= length) {
                    while (true) {
                        cVar.add(objArr[i10]);
                        if (i10 == length) {
                            break;
                        }
                        i10++;
                    }
                }
                objArr = C0246a.K(cVar).toArray(new Object[0]);
            } else {
                int length2 = objArr.length;
                Object[] objArr2 = new Object[length2];
                for (int i11 = 0; i11 < length2; i11++) {
                    if (i11 > i2 || i7 > i11) {
                        obj = objArr[i11];
                    } else {
                        List list2 = listArr[i11];
                        if (list2 != null) {
                            method = (Method) C1194l.b1(list2);
                        } else {
                            method = null;
                        }
                        obj = objArr[i11];
                        if (method != null) {
                            if (obj != null) {
                                obj = method.invoke(obj, (Object[]) null);
                            } else {
                                Class<?> returnType2 = method.getReturnType();
                                j.d(returnType2, "getReturnType(...)");
                                obj = E0.e(returnType2);
                            }
                        }
                    }
                    objArr2[i11] = obj;
                }
                objArr = objArr2;
            }
        }
        Object call = this.b.call(objArr);
        if (call == C1245a.COROUTINE_SUSPENDED || method2 == null || (invoke = method2.invoke((Object) null, new Object[]{call})) == null) {
            return call;
        }
        return invoke;
    }

    public final Type getReturnType() {
        return this.b.getReturnType();
    }
}
