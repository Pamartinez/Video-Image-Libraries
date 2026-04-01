package Pe;

import Af.g;
import Af.o;
import D1.f;
import Df.C0736b;
import Df.E;
import Gd.a;
import Gf.e;
import Gf.h;
import Gf.i;
import Gf.m;
import He.t;
import Hf.B;
import Hf.C0776z;
import Hf.T;
import Hf.X;
import Qe.A;
import Qe.C0816f;
import Qe.C0817g;
import Qe.C0819i;
import Qe.C0833x;
import Se.b;
import Se.d;
import Te.C0848i;
import Te.C0850k;
import Te.C0857s;
import Te.K;
import Te.Q;
import Te.z;
import Ye.c;
import a.C0068a;
import df.C0946i;
import df.C0951n;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.C1202t;
import o1.C0246a;
import qf.C1235b;
import qf.C1236c;
import qf.C1238e;
import qf.C1240g;
import tf.C1309m;
import tf.C1311o;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q implements b, d {

    /* renamed from: h  reason: collision with root package name */
    public static final /* synthetic */ t[] f3646h;

    /* renamed from: a  reason: collision with root package name */
    public final z f3647a;
    public final i b;

    /* renamed from: c  reason: collision with root package name */
    public final B f3648c;
    public final i d;
    public final e e;
    public final i f;
    public final e g;

    static {
        w wVar = v.f4727a;
        Class<q> cls = q.class;
        f3646h = new t[]{wVar.f(new p(wVar.b(cls), "settings", "getSettings()Lorg/jetbrains/kotlin/builtins/jvm/JvmBuiltIns$Settings;")), wVar.f(new p(wVar.b(cls), "cloneableType", "getCloneableType()Lorg/jetbrains/kotlin/types/SimpleType;")), wVar.f(new p(wVar.b(cls), "notConsideredDeprecation", "getNotConsideredDeprecation()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;"))};
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r10v3, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r11v6, types: [Ae.b, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r10v6, types: [Gf.h, Gf.i] */
    public q(z zVar, m mVar, g gVar) {
        this.f3647a = zVar;
        this.b = new h(mVar, gVar);
        m mVar2 = mVar;
        C0850k kVar = new C0850k(new o(zVar, new C1236c("java.io"), 0), C1240g.e("Serializable"), A.ABSTRACT, C0817g.INTERFACE, C0246a.e0(new C0776z(mVar, new l(this, 1))), mVar2);
        kVar.e0(o.b, ne.v.d, (C0848i) null);
        this.f3648c = kVar.i();
        this.d = new h(mVar2, new E(9, (Object) this, (Object) mVar2));
        this.e = new e(mVar2, new ConcurrentHashMap(3, 1.0f, 2), new Object(), 0);
        this.f = new h(mVar2, new l(this, 0));
        this.g = mVar2.b(new C0736b(6, this));
    }

    public final Collection a(C0816f fVar) {
        C0816f c5;
        if (fVar.b() == C0817g.CLASS) {
            g().getClass();
            C0946i f5 = f(fVar);
            if (!(f5 == null || (c5 = e.c(C1353d.g(f5), b.f)) == null)) {
                X x9 = new X(a.n(c5, f5));
                ArrayList arrayList = new ArrayList();
                Iterator it = ((List) f5.t.q.invoke()).iterator();
                while (true) {
                    C1238e eVar = null;
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    C0848i iVar = (C0848i) next;
                    Te.t tVar = iVar;
                    if (tVar.getVisibility().f3674a.b) {
                        Collection d2 = c5.d();
                        j.d(d2, "getConstructors(...)");
                        Iterable iterable = d2;
                        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                            Iterator it2 = iterable.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                C0848i iVar2 = (C0848i) it2.next();
                                j.b(iVar2);
                                if (C1311o.j(iVar2, iVar.c(x9)) == C1309m.OVERRIDABLE) {
                                    break;
                                }
                            }
                        }
                        if (tVar.B().size() == 1) {
                            List B = tVar.B();
                            j.d(B, "getValueParameters(...)");
                            C0819i g3 = ((Q) C1194l.b1(B)).getType().s0().g();
                            if (g3 != null) {
                                eVar = C1353d.h(g3);
                            }
                            if (j.a(eVar, C1353d.h(fVar))) {
                            }
                        }
                        if (!Ne.i.C(iVar) && !t.f.contains(L2.a.z(f5, C0068a.m(iVar, 3)))) {
                            arrayList.add(next);
                        }
                    }
                }
                ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    C0848i iVar3 = (C0848i) it3.next();
                    Te.t tVar2 = iVar3;
                    tVar2.getClass();
                    C0857s K02 = tVar2.K0(X.b);
                    K02.e = fVar;
                    K02.F(fVar.i());
                    K02.r = true;
                    T f8 = x9.f();
                    if (f8 != null) {
                        K02.d = f8;
                        if (!t.g.contains(L2.a.z(f5, C0068a.m(iVar3, 3)))) {
                            K02.j((Re.h) f.y(this.f, f3646h[2]));
                        }
                        Te.t H02 = K02.f3788A.H0(K02);
                        j.c(H02, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor");
                        arrayList2.add((C0848i) H02);
                    } else {
                        C0857s.c(37);
                        throw null;
                    }
                }
                return arrayList2;
            }
        }
        return C1202t.d;
    }

    public final boolean b(C0816f fVar, Ff.v vVar) {
        j.e(fVar, "classDescriptor");
        C0946i f5 = f(fVar);
        if (f5 == null || !vVar.getAnnotations().g(Se.e.f3727a)) {
            return true;
        }
        g().getClass();
        String m = C0068a.m(vVar, 3);
        C0951n e02 = f5.e0();
        C1240g name = vVar.getName();
        j.d(name, "getName(...)");
        Iterable<K> a7 = e02.a(name, c.FROM_BUILTINS);
        if ((a7 instanceof Collection) && ((Collection) a7).isEmpty()) {
            return false;
        }
        for (K m4 : a7) {
            if (C0068a.m(m4, 3).equals(m)) {
                return true;
            }
        }
        return false;
    }

    public final Collection c(C0816f fVar) {
        C1238e h5 = C1353d.h(fVar);
        LinkedHashSet linkedHashSet = t.f3652a;
        C1238e eVar = Ne.p.g;
        boolean equals = h5.equals(eVar);
        boolean z = false;
        B b5 = this.f3648c;
        if (!equals) {
            HashMap hashMap = Ne.p.d0;
            if (hashMap.get(h5) == null) {
                if (h5.equals(eVar) || hashMap.get(h5) != null) {
                    z = true;
                } else {
                    String str = d.f3633a;
                    C1235b f5 = d.f(h5);
                    if (f5 != null) {
                        try {
                            z = Serializable.class.isAssignableFrom(Class.forName(f5.a().b()));
                        } catch (ClassNotFoundException unused) {
                        }
                    }
                }
                if (z) {
                    return C0246a.e0(b5);
                }
                return C1202t.d;
            }
        }
        return C1195m.q0((B) f.y(this.d, f3646h[1]), b5);
    }

    public final Collection d(C0816f fVar) {
        Set b5;
        j.e(fVar, "classDescriptor");
        g().getClass();
        C0946i f5 = f(fVar);
        if (f5 == null || (b5 = f5.e0().b()) == null) {
            return ne.v.d;
        }
        return b5;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v34, resolved type: ne.t} */
    /* JADX WARNING: type inference failed for: r13v0, types: [java.lang.Object, java.io.Serializable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x037a  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x024c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x025b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0249  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0261  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Collection e(qf.C1240g r18, Qe.C0816f r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            java.lang.String r3 = "name"
            kotlin.jvm.internal.j.e(r1, r3)
            java.lang.String r3 = "classDescriptor"
            kotlin.jvm.internal.j.e(r2, r3)
            qf.g r3 = Pe.a.e
            boolean r3 = r1.equals(r3)
            He.t[] r4 = f3646h
            r5 = 1
            ne.t r6 = ne.C1202t.d
            if (r3 == 0) goto L_0x00b5
            boolean r3 = r2 instanceof Ff.k
            if (r3 == 0) goto L_0x00b5
            qf.e r3 = Ne.p.g
            boolean r3 = Ne.i.b(r2, r3)
            if (r3 != 0) goto L_0x002f
            Ne.l r3 = Ne.i.r(r2)
            if (r3 == 0) goto L_0x00b5
        L_0x002f:
            Ff.k r2 = (Ff.k) r2
            lf.j r3 = r2.f3386h
            java.util.List r3 = r3.t
            java.lang.String r7 = "getFunctionList(...)"
            kotlin.jvm.internal.j.d(r3, r7)
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            boolean r7 = r3 instanceof java.util.Collection
            if (r7 == 0) goto L_0x004a
            r7 = r3
            java.util.Collection r7 = (java.util.Collection) r7
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x004a
            goto L_0x006f
        L_0x004a:
            java.util.Iterator r3 = r3.iterator()
        L_0x004e:
            boolean r7 = r3.hasNext()
            if (r7 == 0) goto L_0x006f
            java.lang.Object r7 = r3.next()
            lf.y r7 = (lf.C1171y) r7
            Df.n r8 = r2.f3389o
            java.lang.Object r8 = r8.b
            nf.f r8 = (nf.C1209f) r8
            int r7 = r7.f4888i
            qf.g r7 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(r8, r7)
            qf.g r8 = Pe.a.e
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x004e
            return r6
        L_0x006f:
            Gf.i r0 = r0.d
            r3 = r4[r5]
            java.lang.Object r0 = D1.f.y(r0, r3)
            Hf.B r0 = (Hf.B) r0
            Af.p r0 = r0.A()
            Ye.c r3 = Ye.c.FROM_BUILTINS
            java.util.Collection r0 = r0.a(r1, r3)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.lang.Object r0 = ne.C1194l.a1(r0)
            Te.K r0 = (Te.K) r0
            Qe.u r0 = r0.q0()
            r0.b(r2)
            Qe.p r1 = Qe.C0827q.e
            r0.t(r1)
            Hf.B r1 = r2.i()
            r0.F(r1)
            Te.u r1 = r2.v0()
            r0.C(r1)
            Qe.v r0 = r0.build()
            kotlin.jvm.internal.j.b(r0)
            Te.K r0 = (Te.K) r0
            java.util.List r0 = o1.C0246a.e0(r0)
            java.util.Collection r0 = (java.util.Collection) r0
            return r0
        L_0x00b5:
            Pe.i r3 = r0.g()
            r3.getClass()
            df.i r3 = r0.f(r2)
            r7 = 2
            r8 = 0
            r9 = 3
            java.lang.String r10 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            if (r3 != 0) goto L_0x00cb
        L_0x00c7:
            r16 = 0
            goto L_0x0250
        L_0x00cb:
            qf.c r12 = xf.C1353d.g(r3)
            Pe.b r13 = Pe.b.f
            java.lang.String r14 = "builtIns"
            kotlin.jvm.internal.j.e(r13, r14)
            Qe.f r12 = Pe.e.c(r12, r13)
            if (r12 != 0) goto L_0x00df
            ne.v r12 = ne.v.d
            goto L_0x0104
        L_0x00df:
            java.lang.String r14 = Pe.d.f3633a
            qf.e r14 = xf.C1353d.h(r12)
            java.util.HashMap r15 = Pe.d.k
            java.lang.Object r14 = r15.get(r14)
            qf.c r14 = (qf.C1236c) r14
            if (r14 != 0) goto L_0x00f4
            java.util.Set r12 = B1.a.S(r12)
            goto L_0x0104
        L_0x00f4:
            Qe.f r13 = r13.i(r14)
            Qe.f[] r14 = new Qe.C0816f[r7]
            r14[r8] = r12
            r14[r5] = r13
            java.util.List r12 = ne.C1195m.q0(r14)
            java.util.Collection r12 = (java.util.Collection) r12
        L_0x0104:
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            boolean r13 = r12 instanceof java.util.List
            if (r13 == 0) goto L_0x011a
            r13 = r12
            java.util.List r13 = (java.util.List) r13
            boolean r14 = r13.isEmpty()
            if (r14 == 0) goto L_0x0115
        L_0x0113:
            r13 = 0
            goto L_0x0135
        L_0x0115:
            java.lang.Object r13 = c0.C0086a.f(r5, r13)
            goto L_0x0135
        L_0x011a:
            java.util.Iterator r13 = r12.iterator()
            boolean r14 = r13.hasNext()
            if (r14 != 0) goto L_0x0125
            goto L_0x0113
        L_0x0125:
            java.lang.Object r14 = r13.next()
        L_0x0129:
            boolean r15 = r13.hasNext()
            if (r15 == 0) goto L_0x0134
            java.lang.Object r14 = r13.next()
            goto L_0x0129
        L_0x0134:
            r13 = r14
        L_0x0135:
            Qe.f r13 = (Qe.C0816f) r13
            if (r13 != 0) goto L_0x013a
            goto L_0x00c7
        L_0x013a:
            int r6 = Qf.h.f
            java.util.ArrayList r6 = new java.util.ArrayList
            r14 = 10
            int r15 = ne.C1196n.w0(r12, r14)
            r6.<init>(r15)
            java.util.Iterator r12 = r12.iterator()
        L_0x014b:
            boolean r15 = r12.hasNext()
            if (r15 == 0) goto L_0x015f
            java.lang.Object r15 = r12.next()
            Qe.f r15 = (Qe.C0816f) r15
            qf.c r15 = xf.C1353d.g(r15)
            r6.add(r15)
            goto L_0x014b
        L_0x015f:
            Qf.h r12 = new Qf.h
            r12.<init>(r8)
            r12.addAll(r6)
            java.lang.String r6 = Pe.d.f3633a
            qf.e r6 = tf.C1301e.g(r2)
            java.util.HashMap r15 = Pe.d.f3637j
            boolean r6 = r15.containsKey(r6)
            qf.c r15 = xf.C1353d.g(r3)
            r16 = 0
            Df.E r11 = new Df.E
            r11.<init>((int) r14, (java.lang.Object) r3, (java.lang.Object) r13)
            Gf.e r3 = r0.e
            r3.getClass()
            Gf.g r13 = new Gf.g
            r13.<init>(r15, r11)
            java.lang.Object r3 = r3.invoke(r13)
            if (r3 == 0) goto L_0x0380
            Qe.f r3 = (Qe.C0816f) r3
            Af.p r3 = r3.P()
            java.lang.String r11 = "getUnsubstitutedMemberScope(...)"
            kotlin.jvm.internal.j.d(r3, r11)
            Ye.c r11 = Ye.c.FROM_BUILTINS
            java.util.Collection r1 = r3.a(r1, r11)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r1 = r1.iterator()
        L_0x01aa:
            boolean r11 = r1.hasNext()
            if (r11 == 0) goto L_0x024f
            java.lang.Object r11 = r1.next()
            r13 = r11
            Te.K r13 = (Te.K) r13
            Qe.c r14 = r13.b()
            Qe.c r15 = Qe.C0813c.DECLARATION
            if (r14 == r15) goto L_0x01c2
        L_0x01bf:
            r7 = r8
            goto L_0x0247
        L_0x01c2:
            Qe.p r14 = r13.getVisibility()
            Qe.j0 r14 = r14.f3674a
            boolean r14 = r14.b
            if (r14 != 0) goto L_0x01cd
            goto L_0x01bf
        L_0x01cd:
            boolean r14 = Ne.i.C(r13)
            if (r14 == 0) goto L_0x01d4
            goto L_0x01bf
        L_0x01d4:
            java.util.Collection r14 = r13.h()
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            boolean r15 = r14 instanceof java.util.Collection
            if (r15 == 0) goto L_0x01e8
            r15 = r14
            java.util.Collection r15 = (java.util.Collection) r15
            boolean r15 = r15.isEmpty()
            if (r15 == 0) goto L_0x01e8
            goto L_0x020e
        L_0x01e8:
            java.util.Iterator r14 = r14.iterator()
        L_0x01ec:
            boolean r15 = r14.hasNext()
            if (r15 == 0) goto L_0x020e
            java.lang.Object r15 = r14.next()
            Qe.v r15 = (Qe.C0831v) r15
            Qe.l r15 = r15.g()
            java.lang.String r7 = "getContainingDeclaration(...)"
            kotlin.jvm.internal.j.d(r15, r7)
            qf.c r7 = xf.C1353d.g(r15)
            boolean r7 = r12.contains(r7)
            if (r7 == 0) goto L_0x020c
            goto L_0x01bf
        L_0x020c:
            r7 = 2
            goto L_0x01ec
        L_0x020e:
            Qe.l r7 = r13.g()
            kotlin.jvm.internal.j.c(r7, r10)
            Qe.f r7 = (Qe.C0816f) r7
            java.lang.String r14 = a.C0068a.m(r13, r9)
            java.util.LinkedHashSet r15 = Pe.t.e
            java.lang.String r7 = L2.a.z(r7, r14)
            boolean r7 = r15.contains(r7)
            r7 = r7 ^ r6
            if (r7 == 0) goto L_0x022a
            r7 = r5
            goto L_0x0244
        L_0x022a:
            java.util.List r7 = o1.C0246a.e0(r13)
            java.util.Collection r7 = (java.util.Collection) r7
            Pe.e r13 = Pe.e.d
            Pe.f r14 = new Pe.f
            r14.<init>(r0)
            java.lang.Boolean r7 = Qf.k.h(r7, r13, r14)
            java.lang.String r13 = "ifAny(...)"
            kotlin.jvm.internal.j.d(r7, r13)
            boolean r7 = r7.booleanValue()
        L_0x0244:
            if (r7 != 0) goto L_0x01bf
            r7 = r5
        L_0x0247:
            if (r7 == 0) goto L_0x024c
            r3.add(r11)
        L_0x024c:
            r7 = 2
            goto L_0x01aa
        L_0x024f:
            r6 = r3
        L_0x0250:
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r3 = r6.iterator()
        L_0x025b:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto L_0x037f
            java.lang.Object r6 = r3.next()
            Te.K r6 = (Te.K) r6
            Qe.l r7 = r6.g()
            kotlin.jvm.internal.j.c(r7, r10)
            Qe.f r7 = (Qe.C0816f) r7
            Hf.H r7 = Gd.a.n(r7, r2)
            Hf.X r11 = new Hf.X
            r11.<init>(r7)
            Qe.v r7 = r6.c((Hf.X) r11)
            java.lang.String r11 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor"
            kotlin.jvm.internal.j.c(r7, r11)
            Te.K r7 = (Te.K) r7
            Qe.u r7 = r7.q0()
            r7.b(r2)
            Te.u r11 = r2.v0()
            r7.C(r11)
            r7.z()
            Qe.l r11 = r6.g()
            kotlin.jvm.internal.j.c(r11, r10)
            Qe.f r11 = (Qe.C0816f) r11
            java.lang.String r12 = a.C0068a.m(r6, r9)
            kotlin.jvm.internal.u r13 = new kotlin.jvm.internal.u
            r13.<init>()
            java.util.List r11 = o1.C0246a.e0(r11)
            java.util.Collection r11 = (java.util.Collection) r11
            B1.b r14 = new B1.b
            r15 = 5
            r14.<init>(r15, r0)
            Pe.p r15 = new Pe.p
            r15.<init>(r12, r13, r8)
            java.lang.Object r11 = Qf.k.f(r11, r14, r15)
            java.lang.String r12 = "dfs(...)"
            kotlin.jvm.internal.j.d(r11, r12)
            Pe.m r11 = (Pe.m) r11
            int[] r12 = Pe.n.f3644a
            int r11 = r11.ordinal()
            r11 = r12[r11]
            if (r11 == r5) goto L_0x0354
            r12 = 2
            if (r11 == r12) goto L_0x02f3
            if (r11 == r9) goto L_0x02e4
            r6 = 4
            if (r11 == r6) goto L_0x02e0
            r6 = 5
            if (r11 != r6) goto L_0x02da
            goto L_0x036f
        L_0x02da:
            Dd.a r0 = new Dd.a
            r0.<init>()
            throw r0
        L_0x02e0:
            r6 = r16
            goto L_0x0378
        L_0x02e4:
            Gf.i r6 = r0.f
            r11 = r4[r12]
            java.lang.Object r6 = D1.f.y(r6, r11)
            Re.h r6 = (Re.h) r6
            r7.j(r6)
            goto L_0x036f
        L_0x02f3:
            qf.g r11 = r6.getName()
            qf.g r13 = Pe.r.f3649a
            boolean r13 = kotlin.jvm.internal.j.a(r11, r13)
            Gf.e r14 = r0.g
            if (r13 == 0) goto L_0x0317
            qf.g r6 = r6.getName()
            java.lang.String r6 = r6.b()
            me.i r11 = new me.i
            java.lang.String r13 = "first"
            r11.<init>(r6, r13)
            java.lang.Object r6 = r14.invoke(r11)
            Re.h r6 = (Re.h) r6
            goto L_0x0334
        L_0x0317:
            qf.g r13 = Pe.r.b
            boolean r11 = kotlin.jvm.internal.j.a(r11, r13)
            if (r11 == 0) goto L_0x0338
            qf.g r6 = r6.getName()
            java.lang.String r6 = r6.b()
            me.i r11 = new me.i
            java.lang.String r13 = "last"
            r11.<init>(r6, r13)
            java.lang.Object r6 = r14.invoke(r11)
            Re.h r6 = (Re.h) r6
        L_0x0334:
            r7.j(r6)
            goto L_0x036f
        L_0x0338:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unexpected name: "
            r1.<init>(r2)
            qf.g r2 = r6.getName()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0354:
            r12 = 2
            Qe.A r6 = r2.k()
            Qe.A r11 = Qe.A.FINAL
            if (r6 != r11) goto L_0x0367
            Qe.g r6 = r2.b()
            Qe.g r11 = Qe.C0817g.ENUM_CLASS
            if (r6 == r11) goto L_0x0367
            r6 = r5
            goto L_0x0368
        L_0x0367:
            r6 = r8
        L_0x0368:
            if (r6 == 0) goto L_0x036c
            goto L_0x02e0
        L_0x036c:
            r7.i()
        L_0x036f:
            Qe.v r6 = r7.build()
            kotlin.jvm.internal.j.b(r6)
            Te.K r6 = (Te.K) r6
        L_0x0378:
            if (r6 == 0) goto L_0x025b
            r1.add(r6)
            goto L_0x025b
        L_0x037f:
            return r1
        L_0x0380:
            Gf.e.a(r9)
            throw r16
        */
        throw new UnsupportedOperationException("Method not decompiled: Pe.q.e(qf.g, Qe.f):java.util.Collection");
    }

    public final C0946i f(C0816f fVar) {
        C1236c a7;
        if (fVar != null) {
            if (!Ne.i.b(fVar, Ne.p.f3565a) && Ne.i.I(fVar)) {
                C1238e h5 = C1353d.h(fVar);
                if (h5.d()) {
                    String str = d.f3633a;
                    C1235b f5 = d.f(h5);
                    if (!(f5 == null || (a7 = f5.a()) == null)) {
                        C0816f j2 = C0833x.j(g().f3641a, a7, c.FROM_BUILTINS);
                        if (j2 instanceof C0946i) {
                            return (C0946i) j2;
                        }
                    }
                }
            }
            return null;
        }
        Ne.i.a(109);
        throw null;
    }

    public final i g() {
        return (i) f.y(this.b, f3646h[0]);
    }
}
