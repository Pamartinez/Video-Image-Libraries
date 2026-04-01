package Te;

import Af.g;
import Df.E;
import Hf.B;
import Hf.C0774x;
import Hf.X;
import Hf.d0;
import Qe.A;
import Qe.C0811a;
import Qe.C0813c;
import Qe.C0822l;
import Qe.C0824n;
import Qe.C0826p;
import Qe.C0827q;
import Qe.C0830u;
import Qe.C0831v;
import Qe.Q;
import Qe.V;
import Re.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.j;
import ne.C1194l;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class t extends C0853n implements C0831v {

    /* renamed from: A  reason: collision with root package name */
    public boolean f3795A;
    public Collection B;

    /* renamed from: C  reason: collision with root package name */
    public volatile E f3796C;
    public final C0831v D;
    public final C0813c E;

    /* renamed from: F  reason: collision with root package name */
    public C0831v f3797F;

    /* renamed from: G  reason: collision with root package name */
    public Map f3798G;

    /* renamed from: i  reason: collision with root package name */
    public List f3799i;

    /* renamed from: j  reason: collision with root package name */
    public List f3800j;
    public C0774x k;
    public List l;
    public u m;
    public u n;

    /* renamed from: o  reason: collision with root package name */
    public A f3801o;

    /* renamed from: p  reason: collision with root package name */
    public C0826p f3802p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;

    /* renamed from: x  reason: collision with root package name */
    public boolean f3803x;
    public boolean y;
    public boolean z;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public t(C0813c cVar, C0822l lVar, C0831v vVar, Q q10, h hVar, C1240g gVar) {
        super(lVar, hVar, gVar, q10);
        if (lVar == null) {
            w0(0);
            throw null;
        } else if (hVar == null) {
            w0(1);
            throw null;
        } else if (gVar == null) {
            w0(2);
            throw null;
        } else if (cVar == null) {
            w0(3);
            throw null;
        } else if (q10 != null) {
            this.f3802p = C0827q.f3678i;
            this.q = false;
            this.r = false;
            this.s = false;
            this.t = false;
            this.u = false;
            this.v = false;
            this.w = false;
            this.f3803x = false;
            this.y = false;
            this.z = true;
            this.f3795A = false;
            this.B = null;
            this.f3796C = null;
            this.f3797F = null;
            this.f3798G = null;
            this.D = vVar == null ? this : vVar;
            this.E = cVar;
        } else {
            w0(4);
            throw null;
        }
    }

    public static ArrayList I0(C0831v vVar, List list, X x9, boolean z3, boolean z7, boolean[] zArr) {
        C0774x xVar;
        g gVar;
        Q q10;
        Q q11;
        Object obj;
        X x10 = x9;
        if (list != null) {
            ArrayList arrayList = new ArrayList(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Q q12 = (Q) it.next();
                S s5 = q12;
                C0774x type = s5.getType();
                d0 d0Var = d0.IN_VARIANCE;
                C0774x i2 = x10.i(type, d0Var);
                C0774x xVar2 = q12.n;
                if (xVar2 == null) {
                    xVar = null;
                } else {
                    xVar = x10.i(xVar2, d0Var);
                }
                if (i2 == null) {
                    return null;
                }
                if (!((i2 == s5.getType() && xVar2 == xVar) || zArr == null)) {
                    zArr[0] = true;
                }
                if (q12 instanceof P) {
                    gVar = new g(23, (List) ((P) q12).f3769p.getValue());
                } else {
                    gVar = null;
                }
                if (z3) {
                    q10 = null;
                } else {
                    q10 = q12;
                }
                int i7 = q12.f3770j;
                h annotations = q12.getAnnotations();
                C1240g name = q12.getName();
                boolean F02 = q12.F0();
                boolean z9 = q12.l;
                boolean z10 = q12.m;
                if (z7) {
                    q11 = q12.getSource();
                } else {
                    q11 = Q.f3662a;
                }
                j.e(annotations, "annotations");
                j.e(name, "name");
                j.e(q11, "source");
                if (gVar == null) {
                    obj = new Q(vVar, q10, i7, annotations, name, i2, F02, z9, z10, xVar, q11);
                } else {
                    obj = new P(vVar, q10, i7, annotations, name, i2, F02, z9, z10, xVar, q11, gVar);
                }
                arrayList.add(obj);
            }
            return arrayList;
        }
        w0(30);
        throw null;
    }

    public static /* synthetic */ void w0(int i2) {
        String str;
        int i7;
        Throwable th;
        switch (i2) {
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i2) {
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                i7 = 2;
                break;
            default:
                i7 = 3;
                break;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
                objArr[0] = "annotations";
                break;
            case 2:
                objArr[0] = "name";
                break;
            case 3:
                objArr[0] = "kind";
                break;
            case 4:
                objArr[0] = "source";
                break;
            case 5:
                objArr[0] = "contextReceiverParameters";
                break;
            case 6:
                objArr[0] = "typeParameters";
                break;
            case 7:
            case 28:
            case 30:
                objArr[0] = "unsubstitutedValueParameters";
                break;
            case 8:
            case 10:
                objArr[0] = "visibility";
                break;
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl";
                break;
            case 11:
                objArr[0] = "unsubstitutedReturnType";
                break;
            case 12:
                objArr[0] = "extensionReceiverParameter";
                break;
            case 17:
                objArr[0] = "overriddenDescriptors";
                break;
            case 22:
                objArr[0] = "originalSubstitutor";
                break;
            case 24:
            case 29:
            case 31:
                objArr[0] = "substitutor";
                break;
            case 25:
                objArr[0] = "configuration";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i2) {
            case 9:
                objArr[1] = "initialize";
                break;
            case 13:
                objArr[1] = "getContextReceiverParameters";
                break;
            case 14:
                objArr[1] = "getOverriddenDescriptors";
                break;
            case 15:
                objArr[1] = "getModality";
                break;
            case 16:
                objArr[1] = "getVisibility";
                break;
            case 18:
                objArr[1] = "getTypeParameters";
                break;
            case 19:
                objArr[1] = "getValueParameters";
                break;
            case 20:
                objArr[1] = "getOriginal";
                break;
            case 21:
                objArr[1] = "getKind";
                break;
            case 23:
                objArr[1] = "newCopyBuilder";
                break;
            case 26:
                objArr[1] = "copy";
                break;
            case 27:
                objArr[1] = "getSourceToUseForCopy";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl";
                break;
        }
        switch (i2) {
            case 5:
            case 6:
            case 7:
            case 8:
                objArr[2] = "initialize";
                break;
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                break;
            case 10:
                objArr[2] = "setVisibility";
                break;
            case 11:
                objArr[2] = "setReturnType";
                break;
            case 12:
                objArr[2] = "setExtensionReceiverParameter";
                break;
            case 17:
                objArr[2] = "setOverriddenDescriptors";
                break;
            case 22:
                objArr[2] = "substitute";
                break;
            case 24:
                objArr[2] = "newCopyBuilder";
                break;
            case 25:
                objArr[2] = "doSubstitute";
                break;
            case 28:
            case 29:
            case 30:
            case 31:
                objArr[2] = "getSubstitutedValueParameters";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        switch (i2) {
            case 9:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            case 19:
            case 20:
            case 21:
            case 23:
            case 26:
            case 27:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    public final List B() {
        List list = this.f3800j;
        if (list != null) {
            return list;
        }
        w0(19);
        throw null;
    }

    public final u E() {
        return this.n;
    }

    public final C0831v E0(C0822l lVar, A a7, C0826p pVar, C0813c cVar) {
        C0831v build = q0().b(lVar).H(a7).t(pVar).u(cVar).k().build();
        if (build != null) {
            return build;
        }
        w0(26);
        throw null;
    }

    /* renamed from: F0 */
    public K S(C0822l lVar, A a7, C0826p pVar, C0813c cVar) {
        return (K) E0(lVar, a7, pVar, cVar);
    }

    public abstract t G0(C0813c cVar, C0822l lVar, C0831v vVar, Q q10, h hVar, C1240g gVar);

    public final u H() {
        return this.m;
    }

    /* JADX WARNING: type inference failed for: r13v0 */
    /* JADX WARNING: type inference failed for: r16v0 */
    /* JADX WARNING: type inference failed for: r16v2 */
    /* JADX WARNING: type inference failed for: r16v3 */
    /* JADX WARNING: type inference failed for: r13v4 */
    /* JADX WARNING: type inference failed for: r13v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Te.t H0(Te.C0857s r21) {
        /*
            r20 = this;
            r7 = r21
            r8 = 1
            boolean[] r9 = new boolean[r8]
            Re.h r0 = r7.v
            if (r0 == 0) goto L_0x0015
            Re.h r0 = r20.getAnnotations()
            Re.h r1 = r7.v
            Re.h r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.k(r0, r1)
        L_0x0013:
            r5 = r0
            goto L_0x001a
        L_0x0015:
            Re.h r0 = r20.getAnnotations()
            goto L_0x0013
        L_0x001a:
            Qe.l r2 = r7.e
            Qe.v r3 = r7.f3789h
            Qe.c r1 = r7.f3790i
            qf.g r6 = r7.f3792o
            boolean r0 = r7.r
            if (r0 == 0) goto L_0x0036
            if (r3 == 0) goto L_0x002a
            r0 = r3
            goto L_0x002e
        L_0x002a:
            Qe.v r0 = r20.a()
        L_0x002e:
            Te.n r0 = (Te.C0853n) r0
            Qe.Q r0 = r0.getSource()
        L_0x0034:
            r4 = r0
            goto L_0x0039
        L_0x0036:
            Qe.S r0 = Qe.Q.f3662a
            goto L_0x0034
        L_0x0039:
            r10 = 0
            if (r4 == 0) goto L_0x0238
            r0 = r20
            Te.t r11 = r0.G0(r1, r2, r3, r4, r5, r6)
            r6 = r0
            ne.t r0 = r7.u
            if (r0 != 0) goto L_0x004b
            java.util.List r0 = r6.getTypeParameters()
        L_0x004b:
            r12 = 0
            boolean r1 = r9[r12]
            boolean r2 = r0.isEmpty()
            r2 = r2 ^ r8
            r1 = r1 | r2
            r9[r12] = r1
            java.util.ArrayList r15 = new java.util.ArrayList
            int r1 = r0.size()
            r15.<init>(r1)
            Hf.T r1 = r7.d
            Hf.X r2 = Hf.C0754c.B(r0, r1, r11, r15, r9)
            if (r2 != 0) goto L_0x0069
            goto L_0x0132
        L_0x0069:
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.util.List r0 = r7.k
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x00bf
            java.util.List r0 = r7.k
            java.util.Iterator r0 = r0.iterator()
            r1 = r12
        L_0x007d:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x00bf
            java.lang.Object r3 = r0.next()
            Te.u r3 = (Te.u) r3
            Hf.x r4 = r3.getType()
            Hf.d0 r5 = Hf.d0.IN_VARIANCE
            Hf.x r4 = r2.i(r4, r5)
            if (r4 != 0) goto L_0x0097
            goto L_0x0132
        L_0x0097:
            Bf.e r5 = r3.E0()
            Bf.b r5 = (Bf.b) r5
            qf.g r5 = r5.C0()
            Re.h r13 = r3.getAnnotations()
            int r16 = r1 + 1
            Te.u r1 = tf.C1312p.e(r11, r4, r5, r13, r1)
            r14.add(r1)
            boolean r1 = r9[r12]
            Hf.x r3 = r3.getType()
            if (r4 == r3) goto L_0x00b8
            r3 = r8
            goto L_0x00b9
        L_0x00b8:
            r3 = r12
        L_0x00b9:
            r1 = r1 | r3
            r9[r12] = r1
            r1 = r16
            goto L_0x007d
        L_0x00bf:
            Te.u r0 = r7.l
            if (r0 == 0) goto L_0x00f9
            Hf.x r0 = r0.getType()
            Hf.d0 r1 = Hf.d0.IN_VARIANCE
            Hf.x r0 = r2.i(r0, r1)
            if (r0 != 0) goto L_0x00d1
            goto L_0x0132
        L_0x00d1:
            Te.u r1 = new Te.u
            Bf.c r3 = new Bf.c
            Te.u r4 = r7.l
            r4.E0()
            r3.<init>(r11, r0)
            Te.u r4 = r7.l
            Re.h r4 = r4.getAnnotations()
            r1.<init>(r11, r3, r4)
            boolean r3 = r9[r12]
            Te.u r4 = r7.l
            Hf.x r4 = r4.getType()
            if (r0 == r4) goto L_0x00f2
            r0 = r8
            goto L_0x00f3
        L_0x00f2:
            r0 = r12
        L_0x00f3:
            r0 = r0 | r3
            r9[r12] = r0
            r13 = r12
            r12 = r1
            goto L_0x00fb
        L_0x00f9:
            r13 = r12
            r12 = r10
        L_0x00fb:
            Te.u r0 = r7.m
            if (r0 == 0) goto L_0x0116
            Te.u r0 = r0.c(r2)
            if (r0 != 0) goto L_0x0106
            goto L_0x0132
        L_0x0106:
            boolean r1 = r9[r13]
            Te.u r3 = r7.m
            if (r0 == r3) goto L_0x010e
            r3 = r8
            goto L_0x010f
        L_0x010e:
            r3 = r13
        L_0x010f:
            r1 = r1 | r3
            r9[r13] = r1
            r16 = r13
            r13 = r0
            goto L_0x0119
        L_0x0116:
            r16 = r13
            r13 = r10
        L_0x0119:
            java.util.List r1 = r7.f3791j
            boolean r3 = r7.s
            boolean r4 = r7.r
            r5 = r9
            r0 = r11
            java.util.ArrayList r1 = I0(r0, r1, r2, r3, r4, r5)
            if (r1 != 0) goto L_0x0128
            goto L_0x0132
        L_0x0128:
            Hf.x r3 = r7.n
            Hf.d0 r4 = Hf.d0.OUT_VARIANCE
            Hf.x r3 = r2.i(r3, r4)
            if (r3 != 0) goto L_0x0133
        L_0x0132:
            return r10
        L_0x0133:
            boolean r4 = r5[r16]
            Hf.x r9 = r7.n
            if (r3 == r9) goto L_0x013b
            r9 = r8
            goto L_0x013d
        L_0x013b:
            r9 = r16
        L_0x013d:
            r4 = r4 | r9
            r5[r16] = r4
            if (r4 != 0) goto L_0x0147
            boolean r4 = r7.z
            if (r4 == 0) goto L_0x0147
            return r6
        L_0x0147:
            Qe.A r4 = r7.f
            Qe.p r5 = r7.g
            r11 = r0
            r16 = r1
            r17 = r3
            r18 = r4
            r19 = r5
            r11.J0(r12, r13, r14, r15, r16, r17, r18, r19)
            boolean r1 = r6.q
            r0.q = r1
            boolean r1 = r6.r
            r0.r = r1
            boolean r1 = r6.s
            r0.s = r1
            boolean r1 = r6.t
            r0.t = r1
            boolean r1 = r6.u
            r0.u = r1
            boolean r1 = r6.y
            r0.y = r1
            boolean r1 = r6.v
            r0.v = r1
            boolean r1 = r6.z
            r0.M0(r1)
            boolean r1 = r7.t
            r0.w = r1
            boolean r1 = r7.w
            r0.f3803x = r1
            java.lang.Boolean r1 = r7.y
            if (r1 == 0) goto L_0x0189
            boolean r1 = r1.booleanValue()
            goto L_0x018b
        L_0x0189:
            boolean r1 = r6.f3795A
        L_0x018b:
            r0.N0(r1)
            java.util.LinkedHashMap r1 = r7.f3794x
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x019a
            java.util.Map r1 = r6.f3798G
            if (r1 == 0) goto L_0x01f1
        L_0x019a:
            java.util.LinkedHashMap r1 = r7.f3794x
            java.util.Map r3 = r6.f3798G
            if (r3 == 0) goto L_0x01ca
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x01a8:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x01ca
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r5 = r4.getKey()
            boolean r5 = r1.containsKey(r5)
            if (r5 != 0) goto L_0x01a8
            java.lang.Object r5 = r4.getKey()
            java.lang.Object r4 = r4.getValue()
            r1.put(r5, r4)
            goto L_0x01a8
        L_0x01ca:
            int r3 = r1.size()
            if (r3 != r8) goto L_0x01ef
            java.util.Set r3 = r1.keySet()
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r3 = r3.next()
            java.util.Collection r1 = r1.values()
            java.util.Iterator r1 = r1.iterator()
            java.lang.Object r1 = r1.next()
            java.util.Map r1 = java.util.Collections.singletonMap(r3, r1)
            r0.f3798G = r1
            goto L_0x01f1
        L_0x01ef:
            r0.f3798G = r1
        L_0x01f1:
            boolean r1 = r7.q
            if (r1 != 0) goto L_0x01f9
            Qe.v r1 = r6.f3797F
            if (r1 == 0) goto L_0x0205
        L_0x01f9:
            Qe.v r1 = r6.f3797F
            if (r1 == 0) goto L_0x01fe
            goto L_0x01ff
        L_0x01fe:
            r1 = r6
        L_0x01ff:
            Qe.v r1 = r1.c(r2)
            r0.f3797F = r1
        L_0x0205:
            boolean r1 = r7.f3793p
            if (r1 == 0) goto L_0x0237
            Qe.v r1 = r6.a()
            java.util.Collection r1 = r1.h()
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0237
            Hf.T r1 = r7.d
            boolean r1 = r1.e()
            if (r1 == 0) goto L_0x022e
            Df.E r1 = r6.f3796C
            if (r1 == 0) goto L_0x0226
            r0.f3796C = r1
            return r0
        L_0x0226:
            java.util.Collection r1 = r6.h()
            r0.m0(r1)
            return r0
        L_0x022e:
            Df.E r1 = new Df.E
            r3 = 12
            r1.<init>((Te.C0853n) r6, (java.lang.Object) r2, (int) r3)
            r0.f3796C = r1
        L_0x0237:
            return r0
        L_0x0238:
            r0 = 27
            w0(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: Te.t.H0(Te.s):Te.t");
    }

    public void J0(u uVar, u uVar2, List list, List list2, List list3, C0774x xVar, A a7, C0826p pVar) {
        if (list == null) {
            w0(5);
            throw null;
        } else if (list2 == null) {
            w0(6);
            throw null;
        } else if (list3 == null) {
            w0(7);
            throw null;
        } else if (pVar != null) {
            this.f3799i = C1194l.k1(list2);
            this.f3800j = C1194l.k1(list3);
            this.k = xVar;
            this.f3801o = a7;
            this.f3802p = pVar;
            this.m = uVar;
            this.n = uVar2;
            this.l = list;
            int i2 = 0;
            int i7 = 0;
            while (i7 < list2.size()) {
                V v6 = (V) list2.get(i7);
                if (v6.getIndex() == i7) {
                    i7++;
                } else {
                    throw new IllegalStateException(v6 + " index is " + v6.getIndex() + " but position is " + i7);
                }
            }
            while (i2 < list3.size()) {
                Q q10 = (Q) list3.get(i2);
                if (q10.f3770j == i2) {
                    i2++;
                } else {
                    throw new IllegalStateException(q10 + "index is " + q10.f3770j + " but position is " + i2);
                }
            }
        } else {
            w0(8);
            throw null;
        }
    }

    public final C0857s K0(X x9) {
        if (x9 != null) {
            return new C0857s(this, x9.f(), g(), k(), getVisibility(), b(), B(), i0(), this.m, getReturnType());
        }
        w0(24);
        throw null;
    }

    public final void L0(C0811a aVar, Object obj) {
        if (this.f3798G == null) {
            this.f3798G = new LinkedHashMap();
        }
        this.f3798G.put(aVar, obj);
    }

    public void M0(boolean z3) {
        this.z = z3;
    }

    public void N0(boolean z3) {
        this.f3795A = z3;
    }

    public final void O0(B b) {
        if (b != null) {
            this.k = b;
        } else {
            w0(11);
            throw null;
        }
    }

    public final boolean Q() {
        return false;
    }

    public boolean Z() {
        return this.f3795A;
    }

    public C0831v a() {
        C0831v vVar = this.D;
        this = this;
        if (vVar != this) {
            this = vVar.a();
        }
        if (this != null) {
            return this;
        }
        w0(20);
        throw null;
    }

    public final C0813c b() {
        C0813c cVar = this.E;
        if (cVar != null) {
            return cVar;
        }
        w0(21);
        throw null;
    }

    public final boolean b0() {
        return this.v;
    }

    public final C0831v g0() {
        return this.f3797F;
    }

    public C0774x getReturnType() {
        return this.k;
    }

    public final List getTypeParameters() {
        List list = this.f3799i;
        if (list != null) {
            return list;
        }
        throw new IllegalStateException("typeParameters == null for " + this);
    }

    public final C0826p getVisibility() {
        C0826p pVar = this.f3802p;
        if (pVar != null) {
            return pVar;
        }
        w0(16);
        throw null;
    }

    public Collection h() {
        E e = this.f3796C;
        if (e != null) {
            this.B = (Collection) e.invoke();
            this.f3796C = null;
        }
        Collection collection = this.B;
        if (collection == null) {
            collection = Collections.EMPTY_LIST;
        }
        if (collection != null) {
            return collection;
        }
        w0(14);
        throw null;
    }

    public final List i0() {
        List list = this.l;
        if (list != null) {
            return list;
        }
        w0(13);
        throw null;
    }

    public boolean isExternal() {
        return this.s;
    }

    public final boolean isInfix() {
        if (this.r) {
            return true;
        }
        for (C0831v isInfix : a().h()) {
            if (isInfix.isInfix()) {
                return true;
            }
        }
        return false;
    }

    public boolean isInline() {
        return this.t;
    }

    public final boolean isOperator() {
        if (this.q) {
            return true;
        }
        for (C0831v isOperator : a().h()) {
            if (isOperator.isOperator()) {
                return true;
            }
        }
        return false;
    }

    public boolean isSuspend() {
        return this.y;
    }

    public final A k() {
        A a7 = this.f3801o;
        if (a7 != null) {
            return a7;
        }
        w0(15);
        throw null;
    }

    public final boolean l0() {
        return this.w;
    }

    public void m0(Collection collection) {
        if (collection != null) {
            this.B = collection;
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (((C0831v) it.next()).o0()) {
                    this.f3803x = true;
                    return;
                }
            }
            return;
        }
        w0(17);
        throw null;
    }

    public final boolean o0() {
        return this.f3803x;
    }

    public C0830u q0() {
        return K0(X.b);
    }

    public Object r0(C0811a aVar) {
        Map map = this.f3798G;
        if (map == null) {
            return null;
        }
        return map.get(aVar);
    }

    public Object v(C0824n nVar, Object obj) {
        return nVar.D(this, obj);
    }

    public boolean w() {
        return this.u;
    }

    public C0831v c(X x9) {
        if (x9 == null) {
            w0(22);
            throw null;
        } else if (x9.f3438a.e()) {
            return this;
        } else {
            C0857s K02 = K0(x9);
            K02.f3789h = a();
            K02.r = true;
            K02.z = true;
            return K02.f3788A.H0(K02);
        }
    }
}
