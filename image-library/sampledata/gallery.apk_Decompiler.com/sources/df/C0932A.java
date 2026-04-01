package df;

import A0.l;
import Af.q;
import B0.a;
import D1.f;
import Gf.c;
import Gf.e;
import Gf.h;
import Gf.i;
import Gf.j;
import Gf.m;
import He.F;
import He.t;
import Hf.C0774x;
import Hf.Y;
import Hf.c0;
import Qe.A;
import Qe.C;
import Qe.C0822l;
import Qe.C0835z;
import Qe.V;
import Sf.b;
import Sf.r;
import Te.Q;
import Te.u;
import We.B;
import We.D;
import We.x;
import a.C0068a;
import bf.g;
import cf.C0922a;
import ef.C0993a;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import ne.C1194l;
import ne.C1196n;
import ne.C1202t;
import ne.C1203u;
import qf.C1240g;

/* renamed from: df.A  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0932A extends q {
    public static final /* synthetic */ t[] m;
    public final a b;

    /* renamed from: c  reason: collision with root package name */
    public final C0932A f4231c;
    public final c d;
    public final i e;
    public final e f;
    public final j g;

    /* renamed from: h  reason: collision with root package name */
    public final e f4232h;

    /* renamed from: i  reason: collision with root package name */
    public final i f4233i;

    /* renamed from: j  reason: collision with root package name */
    public final i f4234j;
    public final i k;
    public final e l;

    static {
        w wVar = v.f4727a;
        Class<C0932A> cls = C0932A.class;
        m = new t[]{wVar.f(new p(wVar.b(cls), "functionNamesLazy", "getFunctionNamesLazy()Ljava/util/Set;")), wVar.f(new p(wVar.b(cls), "propertyNamesLazy", "getPropertyNamesLazy()Ljava/util/Set;")), wVar.f(new p(wVar.b(cls), "classNamesLazy", "getClassNamesLazy()Ljava/util/Set;"))};
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [Gf.c, Gf.h] */
    /* JADX WARNING: type inference failed for: r1v1, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r1v2, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r1v3, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r1v4, types: [Gf.h, Gf.i] */
    public C0932A(a aVar, C0951n nVar) {
        kotlin.jvm.internal.j.e(aVar, "c");
        this.b = aVar;
        this.f4231c = nVar;
        Gf.p pVar = ((C0922a) aVar.d).f4006a;
        C0960w wVar = new C0960w(this, 0);
        m mVar = (m) pVar;
        mVar.getClass();
        this.d = new h(mVar, wVar);
        C0960w wVar2 = new C0960w(this, 1);
        m mVar2 = (m) pVar;
        mVar2.getClass();
        this.e = new h(mVar2, wVar2);
        this.f = ((m) pVar).b(new C0962y(this, 0));
        this.g = ((m) pVar).c(new C0962y(this, 1));
        this.f4232h = ((m) pVar).b(new C0962y(this, 2));
        C0960w wVar3 = new C0960w(this, 2);
        m mVar3 = (m) pVar;
        mVar3.getClass();
        this.f4233i = new h(mVar3, wVar3);
        C0960w wVar4 = new C0960w(this, 3);
        m mVar4 = (m) pVar;
        mVar4.getClass();
        this.f4234j = new h(mVar4, wVar4);
        C0960w wVar5 = new C0960w(this, 4);
        m mVar5 = (m) pVar;
        mVar5.getClass();
        this.k = new h(mVar5, wVar5);
        this.l = ((m) pVar).b(new C0962y(this, 3));
    }

    public static C0774x l(x xVar, a aVar) {
        kotlin.jvm.internal.j.e(xVar, "method");
        Class<?> declaringClass = ((Method) xVar.b()).getDeclaringClass();
        kotlin.jvm.internal.j.d(declaringClass, "getDeclaringClass(...)");
        return ((l) aVar.f34h).p(xVar.f(), C0068a.Y(Y.COMMON, declaringClass.isAnnotation(), (C0937F) null, 6));
    }

    public static Gf.l u(a aVar, Te.t tVar, List list) {
        me.i iVar;
        C0774x xVar;
        C1240g gVar;
        C1240g e7;
        We.i iVar2;
        a aVar2 = aVar;
        l lVar = (l) aVar2.f34h;
        C0922a aVar3 = (C0922a) aVar2.d;
        C c5 = aVar3.f4011o;
        r q12 = C1194l.q1(list);
        ArrayList arrayList = new ArrayList(C1196n.w0(q12, 10));
        Iterator it = q12.iterator();
        boolean z = false;
        while (true) {
            b bVar = (b) it;
            if (!bVar.e.hasNext()) {
                return new Gf.l(C1194l.k1(arrayList), z, 1);
            }
            ne.x xVar2 = (ne.x) bVar.next();
            int i2 = xVar2.f4950a;
            D d2 = (D) xVar2.b;
            cf.c O4 = F.O(aVar2, d2);
            C1240g gVar2 = null;
            C0993a Y = C0068a.Y(Y.COMMON, false, (C0937F) null, 7);
            boolean z3 = d2.d;
            B b5 = d2.f3879a;
            if (z3) {
                if (b5 instanceof We.i) {
                    iVar2 = (We.i) b5;
                } else {
                    iVar2 = null;
                }
                if (iVar2 != null) {
                    c0 o2 = lVar.o(iVar2, Y, true);
                    iVar = new me.i(o2, c5.f().f(o2));
                } else {
                    throw new AssertionError("Vararg parameter should be an array: " + d2);
                }
            } else {
                iVar = new me.i(lVar.p(b5, Y), (Object) null);
            }
            C0774x xVar3 = (C0774x) iVar.d;
            C0774x xVar4 = (C0774x) iVar.e;
            if (!kotlin.jvm.internal.j.a(tVar.getName().b(), "equals") || list.size() != 1 || !c5.f().o().equals(xVar3)) {
                String str = d2.f3880c;
                if (str != null) {
                    gVar2 = C1240g.d(str);
                }
                if (gVar2 == null) {
                    z = true;
                }
                if (gVar2 == null) {
                    e7 = C1240g.e("p" + i2);
                } else {
                    xVar = xVar3;
                    gVar = gVar2;
                    arrayList.add(new Q(tVar, (Q) null, i2, O4, gVar, xVar, false, false, false, xVar4, aVar3.f4010j.b(d2)));
                }
            } else {
                e7 = C1240g.e("other");
            }
            xVar = xVar3;
            gVar = e7;
            arrayList.add(new Q(tVar, (Q) null, i2, O4, gVar, xVar, false, false, false, xVar4, aVar3.f4010j.b(d2)));
        }
    }

    public Collection a(C1240g gVar, Ye.a aVar) {
        kotlin.jvm.internal.j.e(gVar, "name");
        kotlin.jvm.internal.j.e(aVar, "location");
        if (!b().contains(gVar)) {
            return C1202t.d;
        }
        return (Collection) this.f4232h.invoke(gVar);
    }

    public final Set b() {
        return (Set) f.y(this.f4233i, m[0]);
    }

    public Collection d(Af.f fVar, Ae.b bVar) {
        kotlin.jvm.internal.j.e(fVar, "kindFilter");
        return (Collection) this.d.invoke();
    }

    public final Set e() {
        return (Set) f.y(this.k, m[2]);
    }

    public Collection f(C1240g gVar, Ye.a aVar) {
        kotlin.jvm.internal.j.e(gVar, "name");
        kotlin.jvm.internal.j.e(aVar, "location");
        if (!g().contains(gVar)) {
            return C1202t.d;
        }
        return (Collection) this.l.invoke(gVar);
    }

    public final Set g() {
        return (Set) f.y(this.f4234j, m[1]);
    }

    public abstract Set h(Af.f fVar, Ae.b bVar);

    public abstract Set i(Af.f fVar, Af.m mVar);

    public void j(ArrayList arrayList, C1240g gVar) {
        kotlin.jvm.internal.j.e(gVar, "name");
    }

    public abstract C0940c k();

    public abstract void m(LinkedHashSet linkedHashSet, C1240g gVar);

    public abstract void n(ArrayList arrayList, C1240g gVar);

    public abstract Set o(Af.f fVar);

    public abstract u p();

    public abstract C0822l q();

    public boolean r(g gVar) {
        return true;
    }

    public abstract C0963z s(x xVar, ArrayList arrayList, C0774x xVar2, List list);

    /* JADX WARNING: type inference failed for: r3v2, types: [me.f, java.lang.Object] */
    public final g t(x xVar) {
        boolean z;
        A a7;
        x xVar2 = xVar;
        kotlin.jvm.internal.j.e(xVar2, "method");
        a aVar = this.b;
        cf.c O4 = F.O(aVar, xVar2);
        C0822l q = q();
        C1240g c5 = xVar2.c();
        Ve.f b5 = ((C0922a) aVar.d).f4010j.b(xVar2);
        if (((C0940c) this.e.invoke()).f(xVar2.c()) == null || !((ArrayList) xVar2.g()).isEmpty()) {
            z = false;
        } else {
            z = true;
        }
        g T0 = g.T0(q, O4, c5, b5, z);
        kotlin.jvm.internal.j.e(aVar, "<this>");
        a aVar2 = new a((C0922a) aVar.d, new Ed.b(aVar, T0, xVar2, 0), aVar.f);
        ArrayList typeParameters = xVar2.getTypeParameters();
        ArrayList arrayList = new ArrayList(C1196n.w0(typeParameters, 10));
        Iterator it = typeParameters.iterator();
        while (it.hasNext()) {
            V a10 = ((cf.e) aVar2.e).a((We.C) it.next());
            kotlin.jvm.internal.j.b(a10);
            arrayList.add(a10);
        }
        Gf.l u = u(aVar2, T0, xVar2.g());
        C0963z s = s(xVar2, arrayList, l(xVar2, aVar2), (List) u.f3415c);
        Collection collection = s.d;
        u p6 = p();
        ArrayList arrayList2 = s.f4265c;
        List list = s.b;
        C0774x xVar3 = s.f4264a;
        C0835z zVar = A.Companion;
        boolean isAbstract = Modifier.isAbstract(((Method) xVar2.b()).getModifiers());
        boolean isFinal = Modifier.isFinal(((Method) xVar2.b()).getModifiers());
        zVar.getClass();
        if (isAbstract) {
            a7 = A.ABSTRACT;
        } else if (!isFinal) {
            a7 = A.OPEN;
        } else {
            a7 = A.FINAL;
        }
        T0.S0((u) null, p6, C1202t.d, arrayList2, list, xVar3, a7, Gd.a.i0(xVar2.e()), C1203u.d);
        T0.U0(false, u.b);
        if (collection.isEmpty()) {
            return T0;
        }
        ((C0922a) aVar2.d).e.getClass();
        throw new UnsupportedOperationException("Should not be called");
    }

    public String toString() {
        return "Lazy scope for " + q();
    }
}
