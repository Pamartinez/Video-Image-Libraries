package V2;

import L2.r;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {
    public static final Map g = Collections.synchronizedMap(new HashMap());

    /* renamed from: h  reason: collision with root package name */
    public static final HashMap f877h;

    /* renamed from: i  reason: collision with root package name */
    public static final HashMap f878i = new HashMap();

    /* renamed from: j  reason: collision with root package name */
    public static final HashMap f879j;

    /* renamed from: a  reason: collision with root package name */
    public final Class f880a;
    public final ClassLoader b = b.class.getClassLoader();

    /* renamed from: c  reason: collision with root package name */
    public InvocationHandler f881c;
    public Class[] d = new Class[0];
    public Object[] e = new Object[0];
    public final HashSet f = new HashSet();

    static {
        HashMap hashMap = new HashMap();
        f877h = hashMap;
        Class cls = Boolean.TYPE;
        Class<Boolean> cls2 = Boolean.class;
        hashMap.put(cls, cls2);
        Class cls3 = Integer.TYPE;
        Class<Integer> cls4 = Integer.class;
        hashMap.put(cls3, cls4);
        Class cls5 = Byte.TYPE;
        Class<Byte> cls6 = Byte.class;
        hashMap.put(cls5, cls6);
        Class cls7 = Long.TYPE;
        Class<Long> cls8 = Long.class;
        hashMap.put(cls7, cls8);
        Class cls9 = Short.TYPE;
        Class<Short> cls10 = Short.class;
        hashMap.put(cls9, cls10);
        Class cls11 = Float.TYPE;
        Class<Float> cls12 = Float.class;
        hashMap.put(cls11, cls12);
        Class cls13 = Double.TYPE;
        Class<Double> cls14 = Double.class;
        hashMap.put(cls13, cls14);
        Class cls15 = Character.TYPE;
        Class<Boolean> cls16 = cls2;
        Class<Character> cls17 = Character.class;
        hashMap.put(cls15, cls17);
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Iterator it2 = it;
            r a7 = r.a((Class) entry.getKey());
            Class<Character> cls18 = cls17;
            r a10 = r.a((Class) entry.getValue());
            f878i.put(a7, a10.b(a10, "valueOf", a7));
            cls4 = cls4;
            cls17 = cls18;
            it = it2;
            cls6 = cls6;
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put(cls, r.a(cls16).b(r.d, "booleanValue", new r[0]));
        hashMap2.put(cls3, r.a(cls4).b(r.f410i, "intValue", new r[0]));
        hashMap2.put(cls5, r.a(cls6).b(r.e, "byteValue", new r[0]));
        hashMap2.put(cls7, r.a(cls8).b(r.f411j, "longValue", new r[0]));
        hashMap2.put(cls9, r.a(cls10).b(r.k, "shortValue", new r[0]));
        hashMap2.put(cls11, r.a(cls12).b(r.f409h, "floatValue", new r[0]));
        hashMap2.put(cls13, r.a(cls14).b(r.g, "doubleValue", new r[0]));
        hashMap2.put(cls15, r.a(cls17).b(r.f, "charValue", new r[0]));
        f879j = hashMap2;
    }

    public b(Class cls) {
        this.f880a = cls;
    }

    public static void b(HashSet hashSet, HashSet hashSet2, Class cls) {
        for (Method method : cls.getDeclaredMethods()) {
            if ((method.getModifiers() & 16) != 0) {
                a aVar = new a(method);
                hashSet2.add(aVar);
                hashSet.remove(aVar);
            } else if ((method.getModifiers() & 8) == 0 && (!method.getName().equals("finalize") || method.getParameterTypes().length != 0)) {
                a aVar2 = new a(method);
                if (!hashSet2.contains(aVar2)) {
                    hashSet.add(aVar2);
                }
            }
        }
    }

    public static String c(Method method) {
        String name = method.getReturnType().getName();
        return "super$" + method.getName() + "$" + name.replace('.', '_').replace('[', '_').replace(';', '_');
    }

    /* JADX WARNING: type inference failed for: r2v17, types: [java.lang.Object, t1.i] */
    /* JADX WARNING: type inference failed for: r15v22, types: [U2.d, Nf.f, R2.g] */
    /* JADX WARNING: type inference failed for: r3v79, types: [U2.d, R2.l] */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x06c9  */
    /* JADX WARNING: Removed duplicated region for block: B:508:0x0df6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object a() {
        /*
            r77 = this;
            r0 = r77
            java.lang.reflect.InvocationHandler r1 = r0.f881c
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r1 = 1
            goto L_0x000a
        L_0x0009:
            r1 = r2
        L_0x000a:
            if (r1 == 0) goto L_0x0e22
            java.lang.Class[] r1 = r0.d
            int r1 = r1.length
            java.lang.Object[] r4 = r0.e
            int r4 = r4.length
            if (r1 != r4) goto L_0x0016
            r1 = 1
            goto L_0x0017
        L_0x0016:
            r1 = r2
        L_0x0017:
            if (r1 == 0) goto L_0x0e1a
            java.util.Map r1 = g
            java.lang.Class r4 = r0.f880a
            java.lang.Object r5 = r1.get(r4)
            java.lang.Class r5 = (java.lang.Class) r5
            java.lang.String r6 = "$__handler"
            java.lang.ClassLoader r7 = r0.b
            java.util.HashSet r8 = r0.f
            if (r5 == 0) goto L_0x004d
            java.lang.ClassLoader r9 = r5.getClassLoader()
            java.lang.ClassLoader r9 = r9.getParent()
            if (r9 != r7) goto L_0x004d
            java.lang.Class[] r9 = r5.getInterfaces()
            java.util.concurrent.CopyOnWriteArraySet r10 = new java.util.concurrent.CopyOnWriteArraySet
            java.util.List r9 = java.util.Arrays.asList(r9)
            r10.<init>(r9)
            boolean r9 = r8.equals(r10)
            if (r9 == 0) goto L_0x004d
            r1 = r4
            r18 = r6
            goto L_0x0d20
        L_0x004d:
            L2.m r5 = new L2.m
            r5.<init>(r2)
            java.lang.String r9 = r4.getSimpleName()
            java.lang.String r10 = "_Proxy"
            java.lang.String r9 = r9.concat(r10)
            java.lang.String r10 = "L"
            java.lang.String r11 = ";"
            java.lang.String r10 = i.C0212a.m(r10, r9, r11)
            L2.r r11 = new L2.r
            java.lang.String r12 = "V"
            boolean r12 = r10.equals(r12)     // Catch:{ NullPointerException -> 0x0e12 }
            if (r12 == 0) goto L_0x0071
            T2.c r12 = T2.c.r     // Catch:{ NullPointerException -> 0x0e12 }
            goto L_0x0075
        L_0x0071:
            T2.c r12 = T2.c.g(r10)
        L_0x0075:
            r11.<init>(r10, r12)
            L2.r r10 = L2.r.a(r4)
            java.lang.Class<java.lang.reflect.InvocationHandler> r12 = java.lang.reflect.InvocationHandler.class
            L2.r r13 = L2.r.a(r12)
            java.lang.Class<java.lang.reflect.Method[]> r14 = java.lang.reflect.Method[].class
            L2.r r15 = L2.r.a(r14)
            L2.n r2 = new L2.n
            r2.<init>(r11, r13, r6)
            r13 = 2
            r5.b(r2, r13)
            L2.n r2 = new L2.n
            java.lang.String r13 = "$__methodArray"
            r2.<init>(r11, r15, r13)
            r15 = 10
            r5.b(r2, r15)
            java.lang.reflect.Constructor[] r2 = r4.getDeclaredConstructors()
            int r15 = r2.length
            r18 = r2
            r3 = 0
        L_0x00a5:
            java.lang.String r2 = "static methods cannot access 'this'"
            if (r3 >= r15) goto L_0x0156
            r19 = r18[r3]
            r20 = r3
            int r3 = r19.getModifiers()
            r21 = r8
            r8 = 16
            if (r3 != r8) goto L_0x00c0
            r19 = r12
            r22 = r14
            r23 = r15
            goto L_0x0142
        L_0x00c0:
            java.lang.Class[] r3 = r19.getParameterTypes()
            int r8 = r3.length
            r19 = r12
            L2.r[] r12 = new L2.r[r8]
            r22 = r14
            r23 = r15
            r14 = 0
        L_0x00ce:
            int r15 = r3.length
            if (r14 >= r15) goto L_0x00dc
            r15 = r3[r14]
            L2.r r15 = L2.r.a(r15)
            r12[r14] = r15
            int r14 = r14 + 1
            goto L_0x00ce
        L_0x00dc:
            L2.q r3 = new L2.q
            L2.r r14 = L2.r.l
            L2.s r15 = new L2.s
            r15.<init>(r12)
            java.lang.String r0 = "<init>"
            r3.<init>(r11, r14, r0, r15)
            L2.b r3 = r5.a(r3)
            L2.p r14 = r3.e
            if (r14 == 0) goto L_0x0150
            L2.b.d(r14, r11)
            L2.p[] r2 = new L2.p[r8]
            r15 = 0
        L_0x00f8:
            if (r15 >= r8) goto L_0x0109
            r29 = r2
            r2 = r12[r15]
            L2.p r2 = r3.e(r15, r2)
            r29[r15] = r2
            int r15 = r15 + 1
            r2 = r29
            goto L_0x00f8
        L_0x0109:
            r29 = r2
            r10.getClass()
            L2.q r2 = new L2.q
            L2.r r8 = L2.r.l
            L2.s r15 = new L2.s
            r15.<init>(r12)
            r2.<init>(r10, r8, r0, r15)
            r0 = 1
            java.lang.String r8 = r2.a(r0)
            T2.a r0 = T2.a.c(r8)
            R2.n r8 = R2.o.f716a
            R2.n r8 = new R2.n
            T2.b r0 = r0.b()
            T2.b r12 = T2.b.m
            r15 = 52
            r8.<init>(r15, r0, r12)
            r27 = 0
            r26 = r2
            r24 = r3
            r25 = r8
            r28 = r14
            r24.g(r25, r26, r27, r28, r29)
            r24.m()
        L_0x0142:
            int r3 = r20 + 1
            r0 = r77
            r12 = r19
            r8 = r21
            r14 = r22
            r15 = r23
            goto L_0x00a5
        L_0x0150:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r2)
            throw r0
        L_0x0156:
            r21 = r8
            r19 = r12
            r22 = r14
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            r8 = r4
        L_0x0167:
            if (r8 == 0) goto L_0x0171
            b(r0, r3, r8)
            java.lang.Class r8 = r8.getSuperclass()
            goto L_0x0167
        L_0x0171:
            r8 = r4
        L_0x0172:
            if (r8 == 0) goto L_0x018f
            java.lang.Class[] r12 = r8.getInterfaces()
            int r14 = r12.length
            r15 = 0
        L_0x017a:
            if (r15 >= r14) goto L_0x0188
            r18 = r8
            r8 = r12[r15]
            b(r0, r3, r8)
            int r15 = r15 + 1
            r8 = r18
            goto L_0x017a
        L_0x0188:
            r18 = r8
            java.lang.Class r8 = r18.getSuperclass()
            goto L_0x0172
        L_0x018f:
            java.util.Iterator r8 = r21.iterator()
        L_0x0193:
            boolean r12 = r8.hasNext()
            if (r12 == 0) goto L_0x01a3
            java.lang.Object r12 = r8.next()
            java.lang.Class r12 = (java.lang.Class) r12
            b(r0, r3, r12)
            goto L_0x0193
        L_0x01a3:
            int r3 = r0.size()
            java.lang.reflect.Method[] r8 = new java.lang.reflect.Method[r3]
            java.util.Iterator r0 = r0.iterator()
            r12 = 0
        L_0x01ae:
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x01c2
            java.lang.Object r14 = r0.next()
            V2.a r14 = (V2.a) r14
            int r15 = r12 + 1
            java.lang.reflect.Method r14 = r14.d
            r8[r12] = r14
            r12 = r15
            goto L_0x01ae
        L_0x01c2:
            L2.r r0 = L2.r.a(r19)
            L2.r r12 = L2.r.a(r22)
            r0.getClass()
            S2.r r14 = new S2.r
            S2.t r15 = new S2.t
            r15.<init>(r6)
            r18 = r6
            S2.t r6 = new S2.t
            r19 = r1
            java.lang.String r1 = r0.f412a
            r6.<init>(r1)
            r14.<init>(r15, r6)
            S2.h r1 = new S2.h
            S2.u r6 = r11.f413c
            r1.<init>(r6, r14)
            r12.getClass()
            S2.r r14 = new S2.r
            S2.t r15 = new S2.t
            r15.<init>(r13)
            r27 = r1
            S2.t r1 = new S2.t
            r20 = r4
            java.lang.String r4 = r12.f412a
            r1.<init>(r4)
            r14.<init>(r15, r1)
            S2.h r1 = new S2.h
            r1.<init>(r6, r14)
            java.lang.Class<java.lang.reflect.Method> r4 = java.lang.reflect.Method.class
            L2.r r4 = L2.r.a(r4)
            java.lang.Class<java.lang.Object[]> r6 = java.lang.Object[].class
            L2.r r6 = L2.r.a(r6)
            L2.r r14 = L2.r.m
            java.lang.String r15 = "invoke"
            r33 = r1
            L2.r[] r1 = new L2.r[]{r14, r4, r6}
            L2.q r1 = r0.b(r14, r15, r1)
            r14 = 0
        L_0x0221:
            if (r14 >= r3) goto L_0x0648
            r40 = r8[r14]
            r41 = 0
            java.lang.String r15 = r40.getName()
            r42 = r3
            java.lang.Class[] r3 = r40.getParameterTypes()
            r43 = r14
            int r14 = r3.length
            r44 = r8
            L2.r[] r8 = new L2.r[r14]
            r45 = r13
            r13 = 0
        L_0x023b:
            if (r13 >= r14) goto L_0x0248
            r22 = r3[r13]
            L2.r r22 = L2.r.a(r22)
            r8[r13] = r22
            int r13 = r13 + 1
            goto L_0x023b
        L_0x0248:
            java.lang.Class r13 = r40.getReturnType()
            r46 = r7
            L2.r r7 = L2.r.a(r13)
            r47 = r9
            L2.q r9 = r10.b(r7, r15, r8)
            L2.q r15 = r11.b(r7, r15, r8)
            L2.b r15 = r5.a(r15)
            r48 = r10
            L2.p r10 = r15.e
            if (r10 == 0) goto L_0x0641
            L2.b.d(r10, r11)
            r49 = r2
            T2.b r2 = r15.f393j
            r50 = r5
            R2.p r5 = r15.f391h
            r51 = r8
            L2.p r8 = r15.k(r0)
            r52 = r11
            L2.r r11 = r8.b
            r53 = r9
            L2.r r9 = L2.r.m
            L2.p r54 = r15.k(r9)
            r55 = r1
            L2.r r1 = L2.r.f410i
            r56 = r10
            L2.p r10 = r15.k(r1)
            r57 = r10
            L2.p r10 = r15.k(r6)
            r58 = r6
            L2.r r6 = r10.b
            r59 = r14
            L2.p r14 = r15.k(r1)
            L2.p r37 = r15.k(r9)
            L2.p r9 = r15.k(r7)
            r60 = r9
            L2.p r9 = r15.k(r12)
            r61 = r12
            L2.p r12 = r15.k(r4)
            L2.p r1 = r15.k(r1)
            r62 = r4
            java.util.HashMap r4 = f877h
            java.lang.Object r4 = r4.get(r13)
            java.lang.Class r4 = (java.lang.Class) r4
            if (r4 == 0) goto L_0x02cc
            L2.r r4 = L2.r.a(r4)
            L2.p r4 = r15.k(r4)
            r63 = r4
            goto L_0x02ce
        L_0x02cc:
            r63 = r41
        L_0x02ce:
            int r4 = r3.length
            r64 = r3
            L2.p[] r3 = new L2.p[r4]
            r65 = r3
            L2.p r3 = r15.k(r7)
            r66 = r7
            L2.p r7 = r15.k(r0)
            r67 = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r43)
            r15.i(r1, r0)
            R2.q r28 = new R2.q
            L2.r r0 = r9.b
            T2.c r0 = r0.b
            R2.n r22 = R2.o.f716a
            r22 = r1
            int r1 = r0.e
            switch(r1) {
                case 1: goto L_0x0315;
                case 2: goto L_0x0312;
                case 3: goto L_0x030f;
                case 4: goto L_0x030c;
                case 5: goto L_0x0309;
                case 6: goto L_0x0306;
                case 7: goto L_0x0303;
                case 8: goto L_0x0300;
                case 9: goto L_0x02fb;
                default: goto L_0x02f7;
            }
        L_0x02f7:
            R2.o.b(r0)
            throw r41
        L_0x02fb:
            R2.n r0 = R2.o.f718a2
        L_0x02fd:
            r29 = r0
            goto L_0x0318
        L_0x0300:
            R2.n r0 = R2.o.f727e2
            goto L_0x02fd
        L_0x0303:
            R2.n r0 = R2.o.f711X1
            goto L_0x02fd
        L_0x0306:
            R2.n r0 = R2.o.f708W1
            goto L_0x02fd
        L_0x0309:
            R2.n r0 = R2.o.f713Y1
            goto L_0x02fd
        L_0x030c:
            R2.n r0 = R2.o.f715Z1
            goto L_0x02fd
        L_0x030f:
            R2.n r0 = R2.o.d2
            goto L_0x02fd
        L_0x0312:
            R2.n r0 = R2.o.f723c2
            goto L_0x02fd
        L_0x0315:
            R2.n r0 = R2.o.f719b2
            goto L_0x02fd
        L_0x0318:
            R2.p r0 = r15.f391h
            R2.l r31 = R2.l.f
            T2.b r1 = r15.f393j
            r30 = r0
            r32 = r1
            r28.<init>(r29, r30, r31, r32, r33)
            r0 = r28
            r1 = r41
            r15.a(r0, r1)
            r0 = 1
            r15.j(r9, r0)
            R2.r r0 = new R2.r
            L2.r r1 = r12.b
            T2.c r1 = r1.b
            r23 = r9
            int r9 = r1.e
            switch(r9) {
                case 1: goto L_0x0359;
                case 2: goto L_0x0356;
                case 3: goto L_0x0353;
                case 4: goto L_0x0350;
                case 5: goto L_0x034d;
                case 6: goto L_0x034a;
                case 7: goto L_0x0347;
                case 8: goto L_0x0344;
                case 9: goto L_0x0341;
                default: goto L_0x033d;
            }
        L_0x033d:
            R2.o.b(r1)
            throw r41
        L_0x0341:
            R2.n r1 = R2.o.f753o1
            goto L_0x035b
        L_0x0344:
            R2.n r1 = R2.o.f758s1
            goto L_0x035b
        L_0x0347:
            R2.n r1 = R2.o.f745l1
            goto L_0x035b
        L_0x034a:
            R2.n r1 = R2.o.f743k1
            goto L_0x035b
        L_0x034d:
            R2.n r1 = R2.o.f748m1
            goto L_0x035b
        L_0x0350:
            R2.n r1 = R2.o.f751n1
            goto L_0x035b
        L_0x0353:
            R2.n r1 = R2.o.r1
            goto L_0x035b
        L_0x0356:
            R2.n r1 = R2.o.f757q1
            goto L_0x035b
        L_0x0359:
            R2.n r1 = R2.o.f756p1
        L_0x035b:
            R2.k r9 = r23.a()
            r28 = r3
            R2.k r3 = r22.a()
            R2.l r3 = R2.l.i(r9, r3)
            r0.<init>(r1, r5, r3, r2)
            r1 = 0
            r15.a(r0, r1)
            r0 = 1
            r15.j(r12, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r59)
            r15.i(r14, r0)
            R2.q r68 = new R2.q
            T2.c r0 = r6.b
            r0.getClass()
            T2.c r3 = r0.f()
            int r3 = r3.e
            switch(r3) {
                case 1: goto L_0x03b8;
                case 2: goto L_0x03b5;
                case 3: goto L_0x03b2;
                case 4: goto L_0x03af;
                case 5: goto L_0x03ac;
                case 6: goto L_0x03a9;
                case 7: goto L_0x03a6;
                case 8: goto L_0x03a3;
                case 9: goto L_0x038f;
                default: goto L_0x038b;
            }
        L_0x038b:
            R2.o.b(r0)
            throw r1
        L_0x038f:
            R2.n r69 = new R2.n
            T2.b r72 = T2.b.g
            T2.b r73 = R2.d.d
            r74 = 6
            r75 = 0
            r70 = 41
            java.lang.String r76 = "new-array-object"
            r71 = r0
            r69.<init>(r70, r71, r72, r73, r74, r75, r76)
            goto L_0x03ba
        L_0x03a3:
            R2.n r69 = R2.o.f681K1
            goto L_0x03ba
        L_0x03a6:
            R2.n r69 = R2.o.f664E1
            goto L_0x03ba
        L_0x03a9:
            R2.n r69 = R2.o.f662D1
            goto L_0x03ba
        L_0x03ac:
            R2.n r69 = R2.o.f667F1
            goto L_0x03ba
        L_0x03af:
            R2.n r69 = R2.o.f670G1
            goto L_0x03ba
        L_0x03b2:
            R2.n r69 = R2.o.f678J1
            goto L_0x03ba
        L_0x03b5:
            R2.n r69 = R2.o.I1
            goto L_0x03ba
        L_0x03b8:
            R2.n r69 = R2.o.f673H1
        L_0x03ba:
            R2.p r0 = r15.f391h
            R2.k r1 = r14.a()
            R2.l r71 = R2.l.h(r1)
            T2.b r1 = r15.f393j
            S2.u r3 = r6.f413c
            r70 = r0
            r72 = r1
            r73 = r3
            r68.<init>(r69, r70, r71, r72, r73)
            r0 = r68
            r1 = 0
            r15.a(r0, r1)
            r0 = 1
            r15.j(r10, r0)
            R2.q r22 = new R2.q
            T2.c r0 = r11.b
            int r3 = r0.e
            switch(r3) {
                case 1: goto L_0x0402;
                case 2: goto L_0x03ff;
                case 3: goto L_0x03fc;
                case 4: goto L_0x03f9;
                case 5: goto L_0x03f6;
                case 6: goto L_0x03f3;
                case 7: goto L_0x03f0;
                case 8: goto L_0x03ed;
                case 9: goto L_0x03e8;
                default: goto L_0x03e4;
            }
        L_0x03e4:
            R2.o.b(r0)
            throw r1
        L_0x03e8:
            R2.n r0 = R2.o.f699R1
        L_0x03ea:
            r23 = r0
            goto L_0x0405
        L_0x03ed:
            R2.n r0 = R2.o.f705V1
            goto L_0x03ea
        L_0x03f0:
            R2.n r0 = R2.o.f692O1
            goto L_0x03ea
        L_0x03f3:
            R2.n r0 = R2.o.f689N1
            goto L_0x03ea
        L_0x03f6:
            R2.n r0 = R2.o.f695P1
            goto L_0x03ea
        L_0x03f9:
            R2.n r0 = R2.o.f697Q1
            goto L_0x03ea
        L_0x03fc:
            R2.n r0 = R2.o.f703U1
            goto L_0x03ea
        L_0x03ff:
            R2.n r0 = R2.o.f701T1
            goto L_0x03ea
        L_0x0402:
            R2.n r0 = R2.o.f700S1
            goto L_0x03ea
        L_0x0405:
            R2.p r0 = r15.f391h
            R2.k r1 = r56.a()
            R2.l r25 = R2.l.h(r1)
            T2.b r1 = r15.f393j
            r24 = r0
            r26 = r1
            r22.<init>(r23, r24, r25, r26, r27)
            r0 = r22
            r1 = 0
            r15.a(r0, r1)
            r0 = 1
            r15.j(r8, r0)
            r15.i(r7, r1)
            L2.o r0 = new L2.o
            r0.<init>()
            L2.i r1 = L2.i.EQ
            r15.b(r0)
            L2.r r3 = r7.b
            T2.c r3 = r3.b
            T2.c r6 = r11.b
            T2.b r3 = T2.b.h(r3, r6)
            R2.n r1 = r1.a(r3)
            R2.i r3 = new R2.i
            R2.k r6 = r7.a()
            R2.k r7 = r8.a()
            R2.l r6 = R2.l.i(r6, r7)
            r7 = 0
            r3.<init>(r1, r5, r7, r6)
            r15.a(r3, r0)
            r1 = 0
        L_0x0453:
            r3 = r59
            if (r1 >= r3) goto L_0x04ff
            java.lang.Integer r6 = java.lang.Integer.valueOf(r1)
            r7 = r57
            r15.i(r7, r6)
            r6 = r51[r1]
            L2.p r6 = r15.e(r1, r6)
            java.util.HashMap r9 = f878i
            L2.r r11 = r6.b
            java.lang.Object r9 = r9.get(r11)
            L2.q r9 = (L2.q) r9
            if (r9 != 0) goto L_0x0476
            r22 = r1
            r1 = r15
            goto L_0x04a3
        L_0x0476:
            L2.p[] r39 = new L2.p[]{r6}
            r6 = 1
            java.lang.String r11 = r9.a(r6)
            T2.a r6 = T2.a.c(r11)
            R2.n r11 = R2.o.f716a
            R2.n r11 = new R2.n
            T2.b r6 = r6.b()
            T2.b r14 = T2.b.m
            r22 = r1
            r1 = 49
            r11.<init>(r1, r6, r14)
            r38 = 0
            r36 = r9
            r35 = r11
            r34 = r15
            r34.g(r35, r36, r37, r38, r39)
            r1 = r34
            r6 = r37
        L_0x04a3:
            R2.r r9 = new R2.r
            L2.r r11 = r6.b
            T2.c r11 = r11.b
            R2.n r14 = R2.o.f716a
            int r14 = r11.e
            switch(r14) {
                case 1: goto L_0x04ce;
                case 2: goto L_0x04cb;
                case 3: goto L_0x04c8;
                case 4: goto L_0x04c5;
                case 5: goto L_0x04c2;
                case 6: goto L_0x04bf;
                case 7: goto L_0x04bc;
                case 8: goto L_0x04b9;
                case 9: goto L_0x04b6;
                default: goto L_0x04b0;
            }
        L_0x04b0:
            R2.o.b(r11)
            r41 = 0
            throw r41
        L_0x04b6:
            R2.n r11 = R2.o.f769x1
            goto L_0x04d0
        L_0x04b9:
            R2.n r11 = R2.o.f657B1
            goto L_0x04d0
        L_0x04bc:
            R2.n r11 = R2.o.f762u1
            goto L_0x04d0
        L_0x04bf:
            R2.n r11 = R2.o.f760t1
            goto L_0x04d0
        L_0x04c2:
            R2.n r11 = R2.o.f764v1
            goto L_0x04d0
        L_0x04c5:
            R2.n r11 = R2.o.f766w1
            goto L_0x04d0
        L_0x04c8:
            R2.n r11 = R2.o.A1
            goto L_0x04d0
        L_0x04cb:
            R2.n r11 = R2.o.f772z1
            goto L_0x04d0
        L_0x04ce:
            R2.n r11 = R2.o.f770y1
        L_0x04d0:
            R2.k r6 = r6.a()
            R2.k r14 = r10.a()
            R2.k r15 = r7.a()
            r59 = r3
            R2.l r3 = new R2.l
            r57 = r7
            r7 = 3
            r3.<init>(r7)
            r7 = 0
            r3.e(r7, r6)
            r6 = 1
            r3.e(r6, r14)
            r7 = 2
            r3.e(r7, r15)
            r9.<init>(r11, r5, r3, r2)
            r7 = 0
            r1.a(r9, r7)
            int r3 = r22 + 1
            r15 = r1
            r1 = r3
            goto L_0x0453
        L_0x04ff:
            r1 = r15
            r3 = r56
            r6 = 1
            L2.p[] r39 = new L2.p[]{r3, r12, r10}
            r2 = r55
            java.lang.String r7 = r2.a(r6)
            T2.a r6 = T2.a.c(r7)
            R2.n r7 = R2.o.f716a
            R2.n r7 = new R2.n
            T2.b r6 = r6.b()
            T2.b r9 = T2.b.m
            r10 = 53
            r7.<init>(r10, r6, r9)
            r34 = r1
            r36 = r2
            r35 = r7
            r38 = r8
            r37 = r54
            r34.g(r35, r36, r37, r38, r39)
            r6 = r37
            java.util.HashMap r7 = f879j
            boolean r8 = r7.containsKey(r13)
            java.lang.Class r10 = java.lang.Void.TYPE
            if (r8 == 0) goto L_0x0570
            r8 = r63
            r1.c(r8, r6)
            java.lang.Object r6 = r7.get(r13)
            L2.q r6 = (L2.q) r6
            r7 = 0
            L2.p[] r11 = new L2.p[r7]
            r7 = 1
            java.lang.String r12 = r6.a(r7)
            T2.a r7 = T2.a.c(r12)
            R2.n r12 = new R2.n
            r14 = 50
            T2.b r7 = r7.b()
            r12.<init>(r14, r7, r9)
            r34 = r1
            r36 = r6
            r38 = r8
            r39 = r11
            r35 = r12
            r37 = r60
            r34.g(r35, r36, r37, r38, r39)
            r7 = r37
            r1.l(r7)
            goto L_0x0582
        L_0x0570:
            r7 = r60
            boolean r8 = r10.equals(r13)
            if (r8 == 0) goto L_0x057c
            r1.m()
            goto L_0x0582
        L_0x057c:
            r1.c(r7, r6)
            r1.l(r7)
        L_0x0582:
            r1.b(r0)
            boolean r6 = r0.f404c
            if (r6 != 0) goto L_0x0639
            r6 = 1
            r0.f404c = r6
            L2.o r6 = r1.f390c
            if (r6 == 0) goto L_0x05a0
            r1.b(r0)
            R2.i r6 = new R2.i
            R2.n r7 = R2.o.r
            R2.l r8 = R2.l.f
            r9 = 0
            r6.<init>(r7, r5, r9, r8)
            r1.a(r6, r0)
        L_0x05a0:
            r1.f390c = r0
            r0 = 0
        L_0x05a3:
            if (r0 >= r4) goto L_0x05b0
            r5 = r51[r0]
            L2.p r5 = r1.e(r0, r5)
            r65[r0] = r5
            int r0 = r0 + 1
            goto L_0x05a3
        L_0x05b0:
            boolean r0 = r10.equals(r13)
            if (r0 == 0) goto L_0x05c2
            r0 = r53
            r4 = r65
            r7 = 0
            r1.h(r0, r7, r3, r4)
            r1.m()
            goto L_0x05ce
        L_0x05c2:
            r5 = r28
            r0 = r53
            r4 = r65
            r1.h(r0, r5, r3, r4)
            r1.l(r5)
        L_0x05ce:
            java.lang.String r1 = c(r40)
            r4 = r51
            r3 = r52
            r5 = r66
            L2.q r1 = r3.b(r5, r1, r4)
            r6 = r50
            L2.b r1 = r6.a(r1)
            L2.p r7 = r1.e
            if (r7 == 0) goto L_0x0631
            L2.b.d(r7, r3)
            r8 = r64
            int r8 = r8.length
            L2.p[] r9 = new L2.p[r8]
            r11 = 0
        L_0x05ef:
            if (r11 >= r8) goto L_0x05fc
            r12 = r4[r11]
            L2.p r12 = r1.e(r11, r12)
            r9[r11] = r12
            int r11 = r11 + 1
            goto L_0x05ef
        L_0x05fc:
            boolean r4 = r10.equals(r13)
            if (r4 == 0) goto L_0x060a
            r4 = 0
            r1.h(r0, r4, r7, r9)
            r1.m()
            goto L_0x0614
        L_0x060a:
            L2.p r4 = r1.k(r5)
            r1.h(r0, r4, r7, r9)
            r1.l(r4)
        L_0x0614:
            int r14 = r43 + 1
            r1 = r2
            r11 = r3
            r5 = r6
            r3 = r42
            r8 = r44
            r13 = r45
            r7 = r46
            r9 = r47
            r10 = r48
            r2 = r49
            r6 = r58
            r12 = r61
            r4 = r62
            r0 = r67
            goto L_0x0221
        L_0x0631:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r49
            r0.<init>(r1)
            throw r0
        L_0x0639:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "already marked"
            r0.<init>(r1)
            throw r0
        L_0x0641:
            r1 = r2
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0648:
            r6 = r5
            r46 = r7
            r44 = r8
            r47 = r9
            r48 = r10
            r3 = r11
            r45 = r13
            java.lang.String r0 = ".generated"
            r1 = r47
            java.lang.String r0 = i.C0212a.A(r1, r0)
            int r2 = r21.size()
            L2.r[] r2 = new L2.r[r2]
            java.util.Iterator r4 = r21.iterator()
            r5 = 0
        L_0x0667:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x067d
            java.lang.Object r7 = r4.next()
            java.lang.Class r7 = (java.lang.Class) r7
            int r8 = r5 + 1
            L2.r r7 = L2.r.a(r7)
            r2[r5] = r7
            r5 = r8
            goto L_0x0667
        L_0x067d:
            L2.l r4 = r6.c(r3)
            boolean r5 = r4.b
            if (r5 != 0) goto L_0x0dfe
            r7 = 1
            r4.b = r7
            r4.f398c = r7
            r3 = r48
            r4.d = r3
            r4.e = r0
            L2.s r0 = new L2.s
            r0.<init>(r2)
            r4.f = r0
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            java.lang.String r2 = "dexmaker.dexcache"
            java.lang.String r2 = java.lang.System.getProperty(r2)
            if (r2 == 0) goto L_0x06a7
            java.io.File r3 = new java.io.File
            r3.<init>(r2)
            goto L_0x06ca
        L_0x06a7:
            java.lang.Class<L2.a> r2 = L2.a.class
            java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch:{ ClassCastException | ClassNotFoundException -> 0x06c6 }
            java.lang.String r3 = "dalvik.system.PathClassLoader"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ ClassCastException | ClassNotFoundException -> 0x06c6 }
            r3.cast(r2)     // Catch:{ ClassCastException | ClassNotFoundException -> 0x06c6 }
            java.lang.String r2 = L2.a.p(r2, r3)     // Catch:{ ClassCastException | ClassNotFoundException -> 0x06c6 }
            java.io.File[] r2 = L2.a.r(r2)     // Catch:{ ClassCastException | ClassNotFoundException -> 0x06c6 }
            int r3 = r2.length     // Catch:{ ClassCastException | ClassNotFoundException -> 0x06c6 }
            if (r3 <= 0) goto L_0x06c6
            r16 = 0
            r2 = r2[r16]     // Catch:{ ClassCastException | ClassNotFoundException -> 0x06c6 }
            goto L_0x06c7
        L_0x06c6:
            r2 = 0
        L_0x06c7:
            if (r2 == 0) goto L_0x0df6
            r3 = r2
        L_0x06ca:
            t1.i r2 = new t1.i
            r2.<init>()
            P2.f r4 = new P2.f
            r4.<init>(r2)
            java.util.LinkedHashMap r2 = r6.f400a
            java.util.Collection r2 = r2.values()
            java.util.Iterator r2 = r2.iterator()
        L_0x06de:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x0ca5
            java.lang.Object r5 = r2.next()
            L2.l r5 = (L2.l) r5
            java.util.LinkedHashMap r6 = r5.f399h
            java.util.LinkedHashMap r7 = r5.g
            L2.r r8 = r5.f397a
            boolean r9 = r5.b
            if (r9 == 0) goto L_0x0c77
            t1.i r9 = new t1.i
            r9.<init>()
            S2.u r11 = r8.f413c
            P2.c r10 = new P2.c
            int r12 = r5.f398c
            L2.r r8 = r5.d
            S2.u r13 = r8.f413c
            L2.s r8 = r5.f
            T2.b r14 = r8.b
            S2.t r15 = new S2.t
            java.lang.String r5 = r5.e
            r15.<init>(r5)
            r10.<init>(r11, r12, r13, r14, r15)
            java.util.Collection r5 = r6.values()
            java.util.Iterator r5 = r5.iterator()
        L_0x0719:
            boolean r6 = r5.hasNext()
            P2.b r8 = r10.f589j
            if (r6 == 0) goto L_0x0be0
            java.lang.Object r6 = r5.next()
            L2.k r6 = (L2.k) r6
            A0.l r11 = new A0.l
            L2.b r12 = r6.f396c
            int r13 = r6.b
            java.util.ArrayList r14 = r12.b
            boolean r15 = r12.d
            if (r15 != 0) goto L_0x0736
            r12.f()
        L_0x0736:
            java.util.Iterator r15 = r14.iterator()
            r21 = r2
            r2 = 0
        L_0x073d:
            boolean r22 = r15.hasNext()
            if (r22 == 0) goto L_0x07ca
            java.lang.Object r22 = r15.next()
            r23 = r5
            r5 = r22
            L2.o r5 = (L2.o) r5
            r22 = r7
            java.util.ArrayList r7 = r5.f403a
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x075f
            r15.remove()
            r47 = r1
            r24 = r15
            goto L_0x07c0
        L_0x075f:
            r24 = r15
            r7 = 0
        L_0x0762:
            java.util.List r15 = r5.d
            int r15 = r15.size()
            if (r7 >= r15) goto L_0x0793
        L_0x076a:
            java.util.List r15 = r5.d
            java.lang.Object r15 = r15.get(r7)
            L2.o r15 = (L2.o) r15
            java.util.ArrayList r15 = r15.f403a
            boolean r15 = r15.isEmpty()
            if (r15 == 0) goto L_0x078e
            java.util.List r15 = r5.d
            java.lang.Object r25 = r15.get(r7)
            r47 = r1
            r1 = r25
            L2.o r1 = (L2.o) r1
            L2.o r1 = r1.e
            r15.set(r7, r1)
            r1 = r47
            goto L_0x076a
        L_0x078e:
            r47 = r1
            int r7 = r7 + 1
            goto L_0x0762
        L_0x0793:
            r47 = r1
        L_0x0795:
            L2.o r1 = r5.e
            if (r1 == 0) goto L_0x07a8
            java.util.ArrayList r1 = r1.f403a
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x07a8
            L2.o r1 = r5.e
            L2.o r1 = r1.e
            r5.e = r1
            goto L_0x0795
        L_0x07a8:
            L2.o r1 = r5.f
            if (r1 == 0) goto L_0x07bb
            java.util.ArrayList r1 = r1.f403a
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x07bb
            L2.o r1 = r5.f
            L2.o r1 = r1.e
            r5.f = r1
            goto L_0x07a8
        L_0x07bb:
            int r1 = r2 + 1
            r5.g = r2
            r2 = r1
        L_0x07c0:
            r7 = r22
            r5 = r23
            r15 = r24
            r1 = r47
            goto L_0x073d
        L_0x07ca:
            r47 = r1
            r23 = r5
            r22 = r7
            R2.b r1 = new R2.b
            int r2 = r14.size()
            r1.<init>(r2)
            r2 = 0
        L_0x07da:
            int r5 = r14.size()
            if (r2 >= r5) goto L_0x0854
            java.lang.Object r5 = r14.get(r2)
            L2.o r5 = (L2.o) r5
            R2.g r15 = new R2.g
            java.util.ArrayList r7 = r5.f403a
            r26 = r14
            int r14 = r7.size()
            r15.<init>(r14)
            r27 = r0
            r14 = 0
        L_0x07f6:
            int r0 = r7.size()
            if (r14 >= r0) goto L_0x0808
            java.lang.Object r0 = r7.get(r14)
            R2.f r0 = (R2.f) r0
            r15.e(r14, r0)
            int r14 = r14 + 1
            goto L_0x07f6
        L_0x0808:
            r0 = 0
            r15.d = r0
            U2.f r0 = new U2.f
            r7 = 4
            r0.<init>(r7)
            java.util.List r7 = r5.d
            java.util.Iterator r7 = r7.iterator()
        L_0x0817:
            boolean r14 = r7.hasNext()
            if (r14 == 0) goto L_0x0829
            java.lang.Object r14 = r7.next()
            L2.o r14 = (L2.o) r14
            int r14 = r14.g
            r0.d(r14)
            goto L_0x0817
        L_0x0829:
            L2.o r7 = r5.e
            if (r7 == 0) goto L_0x0833
            int r7 = r7.g
            r0.d(r7)
            goto L_0x0834
        L_0x0833:
            r7 = -1
        L_0x0834:
            L2.o r14 = r5.f
            if (r14 == 0) goto L_0x083d
            int r14 = r14.g
            r0.d(r14)
        L_0x083d:
            r14 = 0
            r0.d = r14
            R2.a r14 = new R2.a
            int r5 = r5.g
            r14.<init>(r5, r15, r0, r7)
            r1.i(r2, r14)
            r0 = -1
            r1.g = r0
            int r2 = r2 + 1
            r14 = r26
            r0 = r27
            goto L_0x07da
        L_0x0854:
            r27 = r0
            r11.<init>((R2.b) r1)
            java.util.ArrayList r0 = r12.f
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
        L_0x0860:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0876
            java.lang.Object r2 = r0.next()
            L2.p r2 = (L2.p) r2
            L2.r r2 = r2.b
            T2.c r2 = r2.b
            int r2 = r2.e()
            int r1 = r1 + r2
            goto L_0x0860
        L_0x0876:
            N2.w r0 = new N2.w
            r0.<init>(r11, r1, r9)
            java.lang.Object r1 = r11.e
            R2.b r1 = (R2.b) r1
            java.lang.Object[] r2 = r1.e
            int r5 = r2.length
            int r7 = r1.g()
            int r7 = r7 + 31
            int r7 = r7 >> 5
            int[] r12 = new int[r7]
            int[] r14 = new int[r7]
            r15 = 0
        L_0x088f:
            if (r15 >= r5) goto L_0x08b0
            java.lang.Object r26 = r1.d(r15)
            r28 = r9
            r9 = r26
            R2.a r9 = (R2.a) r9
            int r9 = r9.f645a
            int r26 = r9 >> 5
            r9 = r9 & 31
            r17 = 1
            int r9 = r17 << r9
            r29 = r12[r26]
            r9 = r29 | r9
            r12[r26] = r9
            int r15 = r15 + 1
            r9 = r28
            goto L_0x088f
        L_0x08b0:
            r28 = r9
            int[] r9 = new int[r5]
            r26 = r3
            r29 = r10
            r3 = 0
            r10 = -1
            r15 = 0
        L_0x08bb:
            if (r3 == r10) goto L_0x0a81
        L_0x08bd:
            java.lang.Object r10 = r11.g
            U2.f r10 = (U2.f) r10
            if (r10 != 0) goto L_0x097d
            int r10 = r1.g()
            r30 = r4
            U2.f[] r4 = new U2.f[r10]
            r31 = r8
            U2.f r8 = new U2.f
            r32 = r13
            r13 = 10
            r8.<init>(r13)
            int r13 = r2.length
            r33 = r2
            r2 = 0
        L_0x08da:
            if (r2 >= r13) goto L_0x092d
            java.lang.Object r34 = r1.d(r2)
            r35 = r2
            r2 = r34
            R2.a r2 = (R2.a) r2
            r34 = r13
            int r13 = r2.f645a
            U2.f r2 = r2.f646c
            r36 = r6
            int r6 = r2.f
            if (r6 != 0) goto L_0x08fa
            r8.d(r13)
            r37 = r0
        L_0x08f7:
            r2 = 10
            goto L_0x0923
        L_0x08fa:
            r37 = r0
            r0 = 0
        L_0x08fd:
            if (r0 >= r6) goto L_0x08f7
            int r38 = r2.e(r0)
            r39 = r4[r38]
            r40 = r0
            if (r39 != 0) goto L_0x0915
            U2.f r0 = new U2.f
            r42 = r2
            r2 = 10
            r0.<init>(r2)
            r4[r38] = r0
            goto L_0x091b
        L_0x0915:
            r42 = r2
            r2 = 10
            r0 = r39
        L_0x091b:
            r0.d(r13)
            int r0 = r40 + 1
            r2 = r42
            goto L_0x08fd
        L_0x0923:
            int r0 = r35 + 1
            r2 = r0
            r13 = r34
            r6 = r36
            r0 = r37
            goto L_0x08da
        L_0x092d:
            r37 = r0
            r36 = r6
            r0 = 0
        L_0x0932:
            r2 = 10
            if (r0 >= r10) goto L_0x095c
            r6 = r4[r0]
            if (r6 == 0) goto L_0x0955
            r6.c()
            boolean r13 = r6.g
            if (r13 != 0) goto L_0x094f
            int[] r13 = r6.e
            int r2 = r6.f
            r34 = r0
            r0 = 0
            java.util.Arrays.sort(r13, r0, r2)
            r2 = 1
            r6.g = r2
            goto L_0x0952
        L_0x094f:
            r34 = r0
            r0 = 0
        L_0x0952:
            r6.d = r0
            goto L_0x0958
        L_0x0955:
            r34 = r0
            r0 = 0
        L_0x0958:
            int r2 = r34 + 1
            r0 = r2
            goto L_0x0932
        L_0x095c:
            r0 = 0
            r8.c()
            boolean r2 = r8.g
            if (r2 != 0) goto L_0x096e
            int[] r2 = r8.e
            int r6 = r8.f
            java.util.Arrays.sort(r2, r0, r6)
            r6 = 1
            r8.g = r6
        L_0x096e:
            r8.d = r0
            r2 = r4[r0]
            if (r2 != 0) goto L_0x0978
            U2.f r2 = U2.f.f863h
            r4[r0] = r2
        L_0x0978:
            r11.f = r4
            r11.g = r8
            goto L_0x0989
        L_0x097d:
            r37 = r0
            r33 = r2
            r30 = r4
            r36 = r6
            r31 = r8
            r32 = r13
        L_0x0989:
            java.lang.Object r0 = r11.f
            U2.f[] r0 = (U2.f[]) r0
            r0 = r0[r3]
            if (r0 == 0) goto L_0x0a71
            int r2 = r0.f
            r4 = 0
        L_0x0994:
            if (r4 >= r2) goto L_0x09cf
            int r6 = r0.e(r4)
            boolean r8 = L1.d.j(r6, r14)
            if (r8 == 0) goto L_0x09a1
            goto L_0x09cf
        L_0x09a1:
            boolean r8 = L1.d.j(r6, r12)
            if (r8 != 0) goto L_0x09a8
            goto L_0x09cc
        L_0x09a8:
            R2.a r8 = r1.h(r6)
            int r8 = r8.d
            if (r8 != r3) goto L_0x09cc
            int r0 = r6 >> 5
            r2 = r6 & 31
            r17 = 1
            int r2 = r17 << r2
            r3 = r14[r0]
            r2 = r2 | r3
            r14[r0] = r2
            r3 = r6
            r4 = r30
            r8 = r31
            r13 = r32
            r2 = r33
            r6 = r36
            r0 = r37
            goto L_0x08bd
        L_0x09cc:
            int r4 = r4 + 1
            goto L_0x0994
        L_0x09cf:
            r0 = -1
        L_0x09d0:
            if (r3 == r0) goto L_0x0a47
            int r0 = r3 >> 5
            r2 = r3 & 31
            r17 = 1
            int r2 = r17 << r2
            r4 = r12[r0]
            int r2 = ~r2
            r4 = r4 & r2
            r12[r0] = r4
            r4 = r14[r0]
            r2 = r2 & r4
            r14[r0] = r2
            r9[r15] = r3
            int r15 = r15 + 1
            R2.a r0 = r1.h(r3)
            U2.f r2 = r0.f646c
            int r0 = r0.d
            int r3 = r2.f
            if (r3 == 0) goto L_0x0a16
            r6 = 1
            if (r3 == r6) goto L_0x0a0b
            r10 = -1
            if (r0 == r10) goto L_0x0a00
            R2.a r3 = r1.h(r0)
            goto L_0x0a18
        L_0x0a00:
            r3 = 0
            int r4 = r2.e(r3)
            R2.a r4 = r1.h(r4)
            r3 = r4
            goto L_0x0a18
        L_0x0a0b:
            r3 = 0
            r10 = -1
            int r4 = r2.e(r3)
            R2.a r3 = r1.h(r4)
            goto L_0x0a18
        L_0x0a16:
            r10 = -1
            r3 = 0
        L_0x0a18:
            if (r3 != 0) goto L_0x0a1b
            goto L_0x0a48
        L_0x0a1b:
            int r3 = r3.f645a
            boolean r4 = L1.d.j(r3, r12)
            if (r4 == 0) goto L_0x0a24
            goto L_0x0a45
        L_0x0a24:
            if (r0 == r3) goto L_0x0a30
            if (r0 < 0) goto L_0x0a30
            boolean r3 = L1.d.j(r0, r12)
            if (r3 == 0) goto L_0x0a30
            r3 = r0
            goto L_0x0a45
        L_0x0a30:
            int r0 = r2.f
            r3 = 0
        L_0x0a33:
            if (r3 >= r0) goto L_0x0a44
            int r4 = r2.e(r3)
            boolean r6 = L1.d.j(r4, r12)
            if (r6 == 0) goto L_0x0a41
            r3 = r4
            goto L_0x0a45
        L_0x0a41:
            int r3 = r3 + 1
            goto L_0x0a33
        L_0x0a44:
            r3 = r10
        L_0x0a45:
            r0 = r10
            goto L_0x09d0
        L_0x0a47:
            r10 = r0
        L_0x0a48:
            r0 = 0
        L_0x0a49:
            if (r0 >= r7) goto L_0x0a62
            r2 = r12[r0]
            if (r2 == 0) goto L_0x0a5f
            int r2 = java.lang.Integer.numberOfTrailingZeros(r2)
            r3 = 32
            if (r2 != r3) goto L_0x0a58
            r2 = r10
        L_0x0a58:
            if (r2 < 0) goto L_0x0a5f
            int r0 = r0 << 5
            int r0 = r0 + r2
            r3 = r0
            goto L_0x0a63
        L_0x0a5f:
            int r0 = r0 + 1
            goto L_0x0a49
        L_0x0a62:
            r3 = r10
        L_0x0a63:
            r4 = r30
            r8 = r31
            r13 = r32
            r2 = r33
            r6 = r36
            r0 = r37
            goto L_0x08bb
        L_0x0a71:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = L2.a.D(r3)
            java.lang.String r2 = "no such block: "
            java.lang.String r1 = r2.concat(r1)
            r0.<init>(r1)
            throw r0
        L_0x0a81:
            r37 = r0
            r30 = r4
            r36 = r6
            r31 = r8
            r32 = r13
            if (r15 != r5) goto L_0x0bd8
            r0 = r37
            r0.f = r9
            r7 = 0
        L_0x0a92:
            D0.e r2 = r0.f550c
            A0.l r3 = r0.b
            if (r7 >= r5) goto L_0x0b66
            int r4 = r7 + 1
            if (r4 != r5) goto L_0x0a9e
            r6 = r10
            goto L_0x0aa0
        L_0x0a9e:
            r6 = r9[r4]
        L_0x0aa0:
            r7 = r9[r7]
            R2.a r7 = r1.h(r7)
            int r8 = r7.f645a
            java.lang.Object r12 = r3.e
            N2.e[] r12 = (N2.e[]) r12
            r13 = r12[r8]
            java.lang.Object r14 = r2.e
            N2.t r14 = (N2.t) r14
            java.lang.Object r2 = r2.e
            N2.t r2 = (N2.t) r2
            r14.a(r13)
            java.lang.Object r13 = r3.f
            N2.e[] r13 = (N2.e[]) r13
            r13 = r13[r8]
            D0.f r14 = r0.d
            r14.f = r7
            r14.g = r13
            R2.g r13 = r7.b
            java.lang.Object[] r15 = r13.e
            int r15 = r15.length
            r10 = 0
        L_0x0acb:
            if (r10 >= r15) goto L_0x0adf
            java.lang.Object r33 = r13.d(r10)
            r34 = r1
            r1 = r33
            R2.f r1 = (R2.f) r1
            r1.c(r14)
            int r10 = r10 + 1
            r1 = r34
            goto L_0x0acb
        L_0x0adf:
            r34 = r1
            java.lang.Object r1 = r3.g
            N2.e[] r1 = (N2.e[]) r1
            r1 = r1[r8]
            r2.a(r1)
            int r1 = r7.d
            R2.f r3 = r13.g()
            if (r1 < 0) goto L_0x0b5d
            if (r1 == r6) goto L_0x0b5d
            R2.n r8 = r3.d
            int r8 = r8.e
            r10 = 4
            if (r8 != r10) goto L_0x0b4a
            U2.f r7 = r7.f646c
            int r8 = r7.f
            r13 = 2
            if (r8 != r13) goto L_0x0b42
            r14 = 0
            int r8 = r7.e(r14)
            if (r8 != r1) goto L_0x0b0e
            r15 = 1
            int r8 = r7.e(r15)
        L_0x0b0e:
            if (r8 != r6) goto L_0x0b4c
            r1 = r12[r1]
            java.lang.Object r3 = r2.f
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            int r3 = r3.size()
            int r3 = r3 - r13
            java.lang.Object r6 = r2.f     // Catch:{ IndexOutOfBoundsException -> 0x0b39, ClassCastException -> 0x0b31 }
            java.util.ArrayList r6 = (java.util.ArrayList) r6     // Catch:{ IndexOutOfBoundsException -> 0x0b39, ClassCastException -> 0x0b31 }
            java.lang.Object r6 = r6.get(r3)     // Catch:{ IndexOutOfBoundsException -> 0x0b39, ClassCastException -> 0x0b31 }
            N2.y r6 = (N2.y) r6     // Catch:{ IndexOutOfBoundsException -> 0x0b39, ClassCastException -> 0x0b31 }
            java.lang.Object r2 = r2.f
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            N2.y r1 = r6.n(r1)
            r2.set(r3, r1)
            goto L_0x0b60
        L_0x0b31:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "non-reversible instruction"
            r0.<init>(r1)
            throw r0
        L_0x0b39:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "too few instructions"
            r0.<init>(r1)
            throw r0
        L_0x0b42:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "block doesn't have exactly two successors"
            r0.<init>(r1)
            throw r0
        L_0x0b4a:
            r13 = 2
            r14 = 0
        L_0x0b4c:
            N2.y r6 = new N2.y
            N2.i r7 = N2.k.f450I
            R2.p r3 = r3.e
            R2.l r8 = R2.l.f
            r1 = r12[r1]
            r6.<init>(r7, r3, r8, r1)
            r2.a(r6)
            goto L_0x0b60
        L_0x0b5d:
            r10 = 4
            r13 = 2
            r14 = 0
        L_0x0b60:
            r7 = r4
            r1 = r34
            r10 = -1
            goto L_0x0a92
        L_0x0b66:
            r13 = 2
            r14 = 0
            A0.l r1 = new A0.l
            int[] r0 = r0.f
            r1.<init>((A0.l) r11, (int[]) r0, (A0.l) r3)
            D0.f r0 = new D0.f
            java.lang.Object r3 = r2.e
            N2.t r3 = (N2.t) r3
            java.lang.Object r4 = r2.f
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            if (r4 == 0) goto L_0x0bd0
            int r4 = r4.size()
            r7 = r14
        L_0x0b80:
            if (r7 >= r4) goto L_0x0b92
            java.lang.Object r5 = r2.f
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            java.lang.Object r5 = r5.get(r7)
            N2.g r5 = (N2.g) r5
            r3.a(r5)
            int r7 = r7 + 1
            goto L_0x0b80
        L_0x0b92:
            r7 = 0
            r2.f = r7
            r0.<init>((N2.t) r3, (A0.l) r1)
            P2.j r1 = new P2.j
            r6 = r36
            L2.q r2 = r6.f395a
            S2.q r2 = r2.e
            T2.b r3 = T2.b.f
            r4 = r32
            r1.<init>(r2, r4, r0, r3)
            r0 = 65546(0x1000a, float:9.185E-41)
            r0 = r0 & r4
            if (r0 == 0) goto L_0x0bb5
            r0 = r31
            java.util.ArrayList r0 = r0.l
            r0.add(r1)
            goto L_0x0bbc
        L_0x0bb5:
            r0 = r31
            java.util.ArrayList r0 = r0.m
            r0.add(r1)
        L_0x0bbc:
            r2 = r21
            r7 = r22
            r5 = r23
            r3 = r26
            r0 = r27
            r9 = r28
            r10 = r29
            r4 = r30
            r1 = r47
            goto L_0x0719
        L_0x0bd0:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "already processed"
            r0.<init>(r1)
            throw r0
        L_0x0bd8:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "shouldn't happen"
            r0.<init>(r1)
            throw r0
        L_0x0be0:
            r27 = r0
            r47 = r1
            r21 = r2
            r26 = r3
            r30 = r4
            r22 = r7
            r0 = r8
            r29 = r10
            r13 = 2
            r14 = 0
            java.util.Collection r1 = r22.values()
            java.util.Iterator r1 = r1.iterator()
        L_0x0bf9:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0c38
            java.lang.Object r2 = r1.next()
            L2.j r2 = (L2.j) r2
            P2.h r3 = new P2.h
            L2.n r4 = r2.f394a
            int r2 = r2.b
            S2.h r4 = r4.f402c
            r3.<init>(r4, r2)
            r2 = r2 & 8
            if (r2 == 0) goto L_0x0c32
            r41 = 0
            S2.v r2 = Gd.a.u(r41)
            S2.c r4 = r0.n
            if (r4 != 0) goto L_0x0c29
            java.util.ArrayList r4 = r0.f584i
            r4.add(r3)
            java.util.HashMap r4 = r0.f585j
            r4.put(r3, r2)
            goto L_0x0bf9
        L_0x0c29:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "static fields already sorted"
            r0.<init>(r1)
            throw r0
        L_0x0c32:
            java.util.ArrayList r2 = r0.k
            r2.add(r3)
            goto L_0x0bf9
        L_0x0c38:
            r2 = r30
            P2.d r0 = r2.f597j
            java.util.TreeMap r1 = r0.f
            r10 = r29
            S2.u r3 = r10.e     // Catch:{ NullPointerException -> 0x0c6f }
            T2.c r3 = r3.d     // Catch:{ NullPointerException -> 0x0c6f }
            r0.g()
            java.lang.Object r0 = r1.get(r3)
            if (r0 != 0) goto L_0x0c5b
            r1.put(r3, r10)
            r4 = r2
            r2 = r21
            r3 = r26
            r0 = r27
            r1 = r47
            goto L_0x06de
        L_0x0c5b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "already added: "
            r1.<init>(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0c6f:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "clazz == null"
            r0.<init>(r1)
            throw r0
        L_0x0c77:
            r22 = r7
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Undeclared type "
            r1.<init>(r2)
            r1.append(r8)
            java.lang.String r2 = " declares members: "
            r1.append(r2)
            java.util.Set r2 = r22.keySet()
            r1.append(r2)
            java.lang.String r2 = " "
            r1.append(r2)
            java.util.Set r2 = r6.keySet()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0ca5:
            r27 = r0
            r47 = r1
            r26 = r3
            r2 = r4
            byte[] r0 = r2.b()     // Catch:{ IOException -> 0x0def }
            java.lang.String r1 = "Generated"
            java.lang.String r2 = ".jar"
            r3 = r26
            java.io.File r1 = java.io.File.createTempFile(r1, r2, r3)
            r1.deleteOnExit()
            java.util.jar.JarOutputStream r2 = new java.util.jar.JarOutputStream
            java.io.FileOutputStream r4 = new java.io.FileOutputStream
            r4.<init>(r1)
            r2.<init>(r4)
            java.util.jar.JarEntry r4 = new java.util.jar.JarEntry
            java.lang.String r5 = "classes.dex"
            r4.<init>(r5)
            r2.putNextEntry(r4)
            r2.write(r0)
            r2.closeEntry()
            r2.close()
            java.lang.String r0 = "dalvik.system.DexClassLoader"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x0de6, InvocationTargetException -> 0x0ddb, InstantiationException -> 0x0dd5, NoSuchMethodException -> 0x0dcf, IllegalAccessException -> 0x0dc9 }
            java.lang.Class<java.lang.ClassLoader> r2 = java.lang.ClassLoader.class
            r4 = r27
            java.lang.Class[] r2 = new java.lang.Class[]{r4, r4, r4, r2}     // Catch:{ ClassNotFoundException -> 0x0de6, InvocationTargetException -> 0x0ddb, InstantiationException -> 0x0dd5, NoSuchMethodException -> 0x0dcf, IllegalAccessException -> 0x0dc9 }
            java.lang.reflect.Constructor r0 = r0.getConstructor(r2)     // Catch:{ ClassNotFoundException -> 0x0de6, InvocationTargetException -> 0x0ddb, InstantiationException -> 0x0dd5, NoSuchMethodException -> 0x0dcf, IllegalAccessException -> 0x0dc9 }
            java.lang.String r1 = r1.getPath()     // Catch:{ ClassNotFoundException -> 0x0de6, InvocationTargetException -> 0x0ddb, InstantiationException -> 0x0dd5, NoSuchMethodException -> 0x0dcf, IllegalAccessException -> 0x0dc9 }
            java.lang.String r2 = r3.getAbsolutePath()     // Catch:{ ClassNotFoundException -> 0x0de6, InvocationTargetException -> 0x0ddb, InstantiationException -> 0x0dd5, NoSuchMethodException -> 0x0dcf, IllegalAccessException -> 0x0dc9 }
            r3 = r46
            r7 = 0
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r2, r7, r3}     // Catch:{ ClassNotFoundException -> 0x0de6, InvocationTargetException -> 0x0ddb, InstantiationException -> 0x0dd5, NoSuchMethodException -> 0x0dcf, IllegalAccessException -> 0x0dc9 }
            java.lang.Object r0 = r0.newInstance(r1)     // Catch:{ ClassNotFoundException -> 0x0de6, InvocationTargetException -> 0x0ddb, InstantiationException -> 0x0dd5, NoSuchMethodException -> 0x0dcf, IllegalAccessException -> 0x0dc9 }
            java.lang.ClassLoader r0 = (java.lang.ClassLoader) r0     // Catch:{ ClassNotFoundException -> 0x0de6, InvocationTargetException -> 0x0ddb, InstantiationException -> 0x0dd5, NoSuchMethodException -> 0x0dcf, IllegalAccessException -> 0x0dc9 }
            r1 = r47
            java.lang.Class r5 = r0.loadClass(r1)     // Catch:{ IllegalAccessError -> 0x0db2, ClassNotFoundException -> 0x0dab }
            r0 = r45
            java.lang.reflect.Field r0 = r5.getDeclaredField(r0)     // Catch:{ NoSuchFieldException -> 0x0da4, IllegalAccessException -> 0x0d9d }
            r6 = 1
            r0.setAccessible(r6)     // Catch:{ NoSuchFieldException -> 0x0da4, IllegalAccessException -> 0x0d9d }
            r1 = r44
            r7 = 0
            r0.set(r7, r1)     // Catch:{ NoSuchFieldException -> 0x0da4, IllegalAccessException -> 0x0d9d }
            r0 = r19
            r1 = r20
            r0.put(r1, r5)
            r0 = r77
        L_0x0d20:
            java.lang.Class[] r2 = r0.d     // Catch:{ NoSuchMethodException -> 0x0d77 }
            java.lang.reflect.Constructor r1 = r5.getConstructor(r2)     // Catch:{ NoSuchMethodException -> 0x0d77 }
            java.lang.Object[] r2 = r0.e     // Catch:{ InstantiationException -> 0x0d70, IllegalAccessException -> 0x0d69, InvocationTargetException -> 0x0d50 }
            java.lang.Object r1 = r1.newInstance(r2)     // Catch:{ InstantiationException -> 0x0d70, IllegalAccessException -> 0x0d69, InvocationTargetException -> 0x0d50 }
            java.lang.reflect.InvocationHandler r0 = r0.f881c
            java.lang.Class r2 = r1.getClass()     // Catch:{ NoSuchFieldException -> 0x0d47, IllegalAccessException -> 0x0d40 }
            r3 = r18
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch:{ NoSuchFieldException -> 0x0d47, IllegalAccessException -> 0x0d40 }
            r6 = 1
            r2.setAccessible(r6)     // Catch:{ NoSuchFieldException -> 0x0d47, IllegalAccessException -> 0x0d40 }
            r2.set(r1, r0)     // Catch:{ NoSuchFieldException -> 0x0d47, IllegalAccessException -> 0x0d40 }
            return r1
        L_0x0d40:
            r0 = move-exception
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>(r0)
            throw r1
        L_0x0d47:
            r0 = move-exception
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "Not a valid proxy instance"
            r1.<init>(r2, r0)
            throw r1
        L_0x0d50:
            r0 = move-exception
            java.lang.Throwable r0 = r0.getCause()
            boolean r1 = r0 instanceof java.lang.Error
            if (r1 != 0) goto L_0x0d66
            boolean r1 = r0 instanceof java.lang.RuntimeException
            if (r1 == 0) goto L_0x0d60
            java.lang.RuntimeException r0 = (java.lang.RuntimeException) r0
            throw r0
        L_0x0d60:
            java.lang.reflect.UndeclaredThrowableException r1 = new java.lang.reflect.UndeclaredThrowableException
            r1.<init>(r0)
            throw r1
        L_0x0d66:
            java.lang.Error r0 = (java.lang.Error) r0
            throw r0
        L_0x0d69:
            r0 = move-exception
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>(r0)
            throw r1
        L_0x0d70:
            r0 = move-exception
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>(r0)
            throw r1
        L_0x0d77:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "No constructor for "
            r3.<init>(r4)
            java.lang.String r1 = r1.getName()
            r3.append(r1)
            java.lang.String r1 = " with parameter types "
            r3.append(r1)
            java.lang.Class[] r0 = r0.d
            java.lang.String r0 = java.util.Arrays.toString(r0)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x0d9d:
            r0 = move-exception
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>(r0)
            throw r1
        L_0x0da4:
            r0 = move-exception
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>(r0)
            throw r1
        L_0x0dab:
            r0 = move-exception
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>(r0)
            throw r1
        L_0x0db2:
            r0 = move-exception
            r1 = r20
            java.lang.UnsupportedOperationException r2 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "cannot proxy inaccessible class "
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1, r0)
            throw r2
        L_0x0dc9:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x0dcf:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x0dd5:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x0ddb:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.Throwable r0 = r0.getCause()
            r1.<init>(r0)
            throw r1
        L_0x0de6:
            r0 = move-exception
            java.lang.UnsupportedOperationException r1 = new java.lang.UnsupportedOperationException
            java.lang.String r2 = "load() requires a Dalvik VM"
            r1.<init>(r2, r0)
            throw r1
        L_0x0def:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            throw r1
        L_0x0df6:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "dexcache == null (and no default could be found; consider setting the 'dexmaker.dexcache' system property)"
            r0.<init>(r1)
            throw r0
        L_0x0dfe:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "already declared: "
            r1.<init>(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0e12:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "descriptor == null"
            r0.<init>(r1)
            throw r0
        L_0x0e1a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "constructorArgValues.length != constructorArgTypes.length"
            r0.<init>(r1)
            throw r0
        L_0x0e22:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "handler == null"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: V2.b.a():java.lang.Object");
    }
}
