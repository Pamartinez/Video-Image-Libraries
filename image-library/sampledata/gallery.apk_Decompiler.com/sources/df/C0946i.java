package df;

import A0.l;
import Af.j;
import Af.p;
import B0.a;
import Ff.i;
import Hf.M;
import Hf.Y;
import If.f;
import L1.d;
import Qe.A;
import Qe.C0816f;
import Qe.C0817g;
import Qe.C0819i;
import Qe.C0826p;
import Qe.C0827q;
import Qe.P;
import Qe.W;
import Qe.j0;
import Re.h;
import Sf.e;
import Sf.k;
import Te.C0848i;
import Te.C0849j;
import We.o;
import We.q;
import a.C0068a;
import bf.C0918c;
import cf.c;
import ef.C0993a;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import me.m;
import ne.C1192j;
import ne.C1194l;
import ne.C1202t;
import o1.C0246a;
import xf.C1353d;

/* renamed from: df.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0946i extends C0849j implements C0918c {

    /* renamed from: j  reason: collision with root package name */
    public final a f4247j;
    public final o k;
    public final C0816f l;
    public final a m;
    public final m n = d.q(new C0944g(this, 0));

    /* renamed from: o  reason: collision with root package name */
    public final C0817g f4248o;

    /* renamed from: p  reason: collision with root package name */
    public final A f4249p;
    public final j0 q;
    public final boolean r;
    public final i s;
    public final C0951n t;
    public final P u;
    public final j v;
    public final C0935D w;

    /* renamed from: x  reason: collision with root package name */
    public final c f4250x;
    public final Gf.i y;

    static {
        C1192j.z0(new String[]{"equals", "hashCode", "getClass", "wait", "notify", "notifyAll", "toString"});
    }

    /* JADX WARNING: type inference failed for: r9v7, types: [Gf.h, Gf.i] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0946i(B0.a r9, Qe.C0822l r10, We.o r11, Qe.C0816f r12) {
        /*
            r8 = this;
            java.lang.String r0 = "outerContext"
            kotlin.jvm.internal.j.e(r9, r0)
            java.lang.String r0 = "containingDeclaration"
            kotlin.jvm.internal.j.e(r10, r0)
            java.lang.String r0 = "jClass"
            kotlin.jvm.internal.j.e(r11, r0)
            java.lang.Object r0 = r9.d
            cf.a r0 = (cf.C0922a) r0
            Gf.p r1 = r0.f4006a
            qf.g r2 = r11.e()
            Ve.d r0 = r0.f4010j
            Ve.f r0 = r0.b(r11)
            r8.<init>(r1, r10, r2, r0)
            r8.f4247j = r9
            r8.k = r11
            r8.l = r12
            r10 = 4
            B0.a r1 = Gd.a.g(r9, r8, r11, r10)
            r8.m = r1
            java.lang.Object r9 = r1.d
            cf.a r9 = (cf.C0922a) r9
            Gf.p r10 = r9.f4006a
            af.h r0 = r9.g
            r0.getClass()
            df.g r0 = new df.g
            r2 = 0
            r0.<init>(r8, r2)
            me.m r0 = L1.d.q(r0)
            r8.n = r0
            java.lang.Class r0 = r11.f3891a
            boolean r2 = r0.isAnnotation()
            if (r2 == 0) goto L_0x0051
            Qe.g r2 = Qe.C0817g.ANNOTATION_CLASS
            goto L_0x0065
        L_0x0051:
            boolean r2 = r0.isInterface()
            if (r2 == 0) goto L_0x005a
            Qe.g r2 = Qe.C0817g.INTERFACE
            goto L_0x0065
        L_0x005a:
            boolean r2 = r0.isEnum()
            if (r2 == 0) goto L_0x0063
            Qe.g r2 = Qe.C0817g.ENUM_CLASS
            goto L_0x0065
        L_0x0063:
            Qe.g r2 = Qe.C0817g.CLASS
        L_0x0065:
            r8.f4248o = r2
            boolean r2 = r0.isAnnotation()
            r3 = 0
            r4 = 1
            if (r2 != 0) goto L_0x00b3
            boolean r2 = r0.isEnum()
            if (r2 == 0) goto L_0x0076
            goto L_0x00b3
        L_0x0076:
            Qe.z r2 = Qe.A.Companion
            boolean r5 = r11.h()
            boolean r6 = r11.h()
            if (r6 != 0) goto L_0x0095
            int r6 = r0.getModifiers()
            boolean r6 = java.lang.reflect.Modifier.isAbstract(r6)
            if (r6 != 0) goto L_0x0095
            boolean r6 = r0.isInterface()
            if (r6 == 0) goto L_0x0093
            goto L_0x0095
        L_0x0093:
            r6 = r3
            goto L_0x0096
        L_0x0095:
            r6 = r4
        L_0x0096:
            int r7 = r0.getModifiers()
            boolean r7 = java.lang.reflect.Modifier.isFinal(r7)
            r2.getClass()
            if (r5 == 0) goto L_0x00a6
            Qe.A r2 = Qe.A.SEALED
            goto L_0x00b5
        L_0x00a6:
            if (r6 == 0) goto L_0x00ab
            Qe.A r2 = Qe.A.ABSTRACT
            goto L_0x00b5
        L_0x00ab:
            if (r7 != 0) goto L_0x00b0
            Qe.A r2 = Qe.A.OPEN
            goto L_0x00b5
        L_0x00b0:
            Qe.A r2 = Qe.A.FINAL
            goto L_0x00b5
        L_0x00b3:
            Qe.A r2 = Qe.A.FINAL
        L_0x00b5:
            r8.f4249p = r2
            int r2 = r0.getModifiers()
            boolean r5 = java.lang.reflect.Modifier.isPublic(r2)
            if (r5 == 0) goto L_0x00c4
            Qe.g0 r2 = Qe.g0.f3670c
            goto L_0x00e1
        L_0x00c4:
            boolean r5 = java.lang.reflect.Modifier.isPrivate(r2)
            if (r5 == 0) goto L_0x00cd
            Qe.d0 r2 = Qe.d0.f3667c
            goto L_0x00e1
        L_0x00cd:
            boolean r5 = java.lang.reflect.Modifier.isProtected(r2)
            if (r5 == 0) goto L_0x00df
            boolean r2 = java.lang.reflect.Modifier.isStatic(r2)
            if (r2 == 0) goto L_0x00dc
            Ue.c r2 = Ue.c.f3827c
            goto L_0x00e1
        L_0x00dc:
            Ue.b r2 = Ue.b.f3826c
            goto L_0x00e1
        L_0x00df:
            Ue.a r2 = Ue.a.f3825c
        L_0x00e1:
            r8.q = r2
            java.lang.Class r2 = r0.getDeclaringClass()
            if (r2 == 0) goto L_0x00ef
            We.o r5 = new We.o
            r5.<init>(r2)
            goto L_0x00f0
        L_0x00ef:
            r5 = 0
        L_0x00f0:
            if (r5 == 0) goto L_0x00fe
            int r0 = r0.getModifiers()
            boolean r0 = java.lang.reflect.Modifier.isStatic(r0)
            if (r0 != 0) goto L_0x00fe
            r0 = r4
            goto L_0x00ff
        L_0x00fe:
            r0 = r3
        L_0x00ff:
            r8.r = r0
            Ff.i r0 = new Ff.i
            r0.<init>((df.C0946i) r8)
            r8.s = r0
            df.n r0 = new df.n
            if (r12 == 0) goto L_0x010d
            goto L_0x010e
        L_0x010d:
            r4 = r3
        L_0x010e:
            r5 = 0
            r2 = r8
            r3 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            r2.t = r0
            Qe.S r8 = Qe.P.d
            If.k r9 = r9.u
            If.l r9 = (If.l) r9
            r9.getClass()
            Df.b r9 = new Df.b
            r11 = 19
            r9.<init>(r11, r2)
            r8.getClass()
            java.lang.String r8 = "storageManager"
            kotlin.jvm.internal.j.e(r10, r8)
            Qe.P r8 = new Qe.P
            r8.<init>(r2, r10, r9)
            r2.u = r8
            Af.j r8 = new Af.j
            r8.<init>(r0)
            r2.v = r8
            df.D r8 = new df.D
            r8.<init>(r1, r3, r2)
            r2.w = r8
            cf.c r8 = He.F.O(r1, r3)
            r2.f4250x = r8
            df.g r8 = new df.g
            r9 = 1
            r8.<init>(r2, r9)
            Gf.m r10 = (Gf.m) r10
            r10.getClass()
            Gf.i r9 = new Gf.i
            r9.<init>(r10, r8)
            r2.y = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: df.C0946i.<init>(B0.a, Qe.l, We.o, Qe.f):void");
    }

    public final p K(f fVar) {
        P p6 = this.u;
        C1353d.j(p6.f3660a);
        return (C0951n) ((p) D1.f.y(p6.f3661c, P.e[0]));
    }

    public final p M() {
        return this.v;
    }

    public final W N() {
        return null;
    }

    public final p P() {
        return (C0951n) super.P();
    }

    public final boolean Q() {
        return false;
    }

    public final boolean T() {
        return false;
    }

    public final boolean X() {
        return false;
    }

    public final C0817g b() {
        return this.f4248o;
    }

    public final boolean b0() {
        return false;
    }

    public final p c0() {
        return this.w;
    }

    public final Collection d() {
        return (List) this.t.q.invoke();
    }

    /* JADX WARNING: type inference failed for: r11v2, types: [java.lang.Object, java.util.Comparator] */
    public final Collection e() {
        Class[] clsArr;
        k<q> kVar;
        C0816f fVar;
        if (this.f4249p != A.SEALED) {
            return C1202t.d;
        }
        C0993a Y = C0068a.Y(Y.COMMON, false, (C0937F) null, 7);
        Class cls = this.k.f3891a;
        kotlin.jvm.internal.j.e(cls, "clazz");
        D0.f fVar2 = C0246a.g;
        if (fVar2 == null) {
            Class<Class> cls2 = Class.class;
            try {
                fVar2 = new D0.f(cls2.getMethod("isSealed", (Class[]) null), cls2.getMethod("getPermittedSubclasses", (Class[]) null), cls2.getMethod("isRecord", (Class[]) null), cls2.getMethod("getRecordComponents", (Class[]) null), 8);
            } catch (NoSuchMethodException unused) {
                fVar2 = new D0.f((Object) null, (Object) null, (Object) null, (Object) null, 8);
            }
            C0246a.g = fVar2;
        }
        Method method = (Method) fVar2.f;
        if (method == null) {
            clsArr = null;
        } else {
            Object invoke = method.invoke(cls, (Object[]) null);
            kotlin.jvm.internal.j.c(invoke, "null cannot be cast to non-null type kotlin.Array<java.lang.Class<*>>");
            clsArr = (Class[]) invoke;
        }
        if (clsArr != null) {
            ArrayList arrayList = new ArrayList(clsArr.length);
            for (Class qVar : clsArr) {
                arrayList.add(new q(qVar));
            }
            kVar = C1194l.F0(arrayList);
        } else {
            kVar = e.f3730a;
        }
        ArrayList arrayList2 = new ArrayList();
        for (q p6 : kVar) {
            C0819i g = ((l) this.m.f34h).p(p6, Y).s0().g();
            if (g instanceof C0816f) {
                fVar = (C0816f) g;
            } else {
                fVar = null;
            }
            if (fVar != null) {
                arrayList2.add(fVar);
            }
        }
        return C1194l.g1(arrayList2, new Object());
    }

    public final C0951n e0() {
        return (C0951n) super.P();
    }

    public final h getAnnotations() {
        return this.f4250x;
    }

    public final C0826p getVisibility() {
        o oVar;
        C0826p pVar = C0827q.f3675a;
        j0 j0Var = this.q;
        if (kotlin.jvm.internal.j.a(j0Var, pVar)) {
            Class<?> declaringClass = this.k.f3891a.getDeclaringClass();
            if (declaringClass != null) {
                oVar = new o(declaringClass);
            } else {
                oVar = null;
            }
            if (oVar == null) {
                C0826p pVar2 = Ze.o.f3951a;
                kotlin.jvm.internal.j.b(pVar2);
                return pVar2;
            }
        }
        return Gd.a.i0(j0Var);
    }

    public final boolean isInline() {
        return false;
    }

    public final List j() {
        return (List) this.y.invoke();
    }

    public final A k() {
        return this.f4249p;
    }

    public final boolean l() {
        return false;
    }

    public final M q() {
        return this.s;
    }

    public final boolean s() {
        return this.r;
    }

    public final boolean t0() {
        return false;
    }

    public final String toString() {
        return "Lazy Java class " + C1353d.h(this);
    }

    public final C0848i y() {
        return null;
    }
}
