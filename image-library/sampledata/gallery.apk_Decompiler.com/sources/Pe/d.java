package Pe;

import B1.a;
import Ne.l;
import Ne.p;
import Ne.q;
import Oe.h;
import Oe.i;
import Oe.j;
import Oe.k;
import Tf.n;
import Tf.u;
import Tf.v;
import c0.C0086a;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import ne.C1195m;
import qf.C1235b;
import qf.C1236c;
import qf.C1238e;
import qf.C1240g;
import qf.C1242i;
import qf.C1243j;
import yf.C1359c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3633a;
    public static final String b;

    /* renamed from: c  reason: collision with root package name */
    public static final String f3634c;
    public static final String d;
    public static final C1235b e;
    public static final C1236c f;
    public static final C1235b g = C1243j.q;

    /* renamed from: h  reason: collision with root package name */
    public static final HashMap f3635h = new HashMap();

    /* renamed from: i  reason: collision with root package name */
    public static final HashMap f3636i = new HashMap();

    /* renamed from: j  reason: collision with root package name */
    public static final HashMap f3637j = new HashMap();
    public static final HashMap k = new HashMap();
    public static final HashMap l = new HashMap();
    public static final HashMap m = new HashMap();
    public static final List n;

    static {
        StringBuilder sb2 = new StringBuilder();
        h hVar = h.f3618c;
        sb2.append(hVar.f3622a);
        sb2.append('.');
        sb2.append(hVar.b);
        f3633a = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        i iVar = i.f3619c;
        sb3.append(iVar.f3622a);
        sb3.append('.');
        sb3.append(iVar.b);
        b = sb3.toString();
        StringBuilder sb4 = new StringBuilder();
        k kVar = k.f3621c;
        sb4.append(kVar.f3622a);
        sb4.append('.');
        sb4.append(kVar.b);
        f3634c = sb4.toString();
        StringBuilder sb5 = new StringBuilder();
        j jVar = j.f3620c;
        sb5.append(jVar.f3622a);
        sb5.append('.');
        sb5.append(jVar.b);
        d = sb5.toString();
        C1235b U = og.k.U(new C1236c("kotlin.jvm.functions.FunctionN"));
        e = U;
        f = U.a();
        d(Class.class);
        C1235b U8 = og.k.U(p.B);
        C1236c cVar = p.f3557J;
        C1236c cVar2 = U8.f5033a;
        c cVar3 = new c(d(Iterable.class), U8, new C1235b(cVar2, a.T(cVar, cVar2), false));
        C1235b U10 = og.k.U(p.f3551A);
        C1236c cVar4 = p.f3556I;
        C1236c cVar5 = U10.f5033a;
        c cVar6 = new c(d(Iterator.class), U10, new C1235b(cVar5, a.T(cVar4, cVar5), false));
        C1235b U11 = og.k.U(p.f3552C);
        C1236c cVar7 = p.f3558K;
        C1236c cVar8 = U11.f5033a;
        c cVar9 = new c(d(Collection.class), U11, new C1235b(cVar8, a.T(cVar7, cVar8), false));
        C1235b U12 = og.k.U(p.D);
        C1236c cVar10 = p.L;
        C1236c cVar11 = U12.f5033a;
        c cVar12 = new c(d(List.class), U12, new C1235b(cVar11, a.T(cVar10, cVar11), false));
        C1235b U13 = og.k.U(p.f3553F);
        C1236c cVar13 = p.f3560N;
        C1236c cVar14 = U13.f5033a;
        c cVar15 = new c(d(Set.class), U13, new C1235b(cVar14, a.T(cVar13, cVar14), false));
        C1235b U14 = og.k.U(p.E);
        C1236c cVar16 = p.f3559M;
        C1236c cVar17 = U14.f5033a;
        c cVar18 = new c(d(ListIterator.class), U14, new C1235b(cVar17, a.T(cVar16, cVar17), false));
        C1236c cVar19 = p.f3554G;
        C1235b U15 = og.k.U(cVar19);
        C1236c cVar20 = p.f3561O;
        C1236c cVar21 = U15.f5033a;
        c cVar22 = new c(d(Map.class), U15, new C1235b(cVar21, a.T(cVar20, cVar21), false));
        C1235b U16 = og.k.U(cVar19);
        C1240g f5 = p.f3555H.f();
        kotlin.jvm.internal.j.d(f5, "shortName(...)");
        C1235b d2 = U16.d(f5);
        C1236c cVar23 = p.f3562P;
        C1236c cVar24 = d2.f5033a;
        List<c> q0 = C1195m.q0(cVar3, cVar6, cVar9, cVar12, cVar15, cVar18, cVar22, new c(d(Map.Entry.class), d2, new C1235b(cVar24, a.T(cVar23, cVar24), false)));
        n = q0;
        c(Object.class, p.f3565a);
        c(String.class, p.f);
        c(CharSequence.class, p.e);
        b(Throwable.class, p.k);
        c(Cloneable.class, p.f3566c);
        c(Number.class, p.f3569i);
        b(Comparable.class, p.l);
        c(Enum.class, p.f3570j);
        b(Annotation.class, p.s);
        for (c cVar25 : q0) {
            C1235b bVar = cVar25.f3631a;
            C1235b bVar2 = cVar25.b;
            C1235b bVar3 = cVar25.f3632c;
            a(bVar, bVar2);
            f3636i.put(bVar3.a().i(), bVar);
            l.put(bVar3, bVar2);
            m.put(bVar2, bVar3);
            C1236c a7 = bVar2.a();
            C1236c a10 = bVar3.a();
            f3637j.put(bVar3.a().i(), a7);
            k.put(a7.i(), a10);
        }
        for (C1359c cVar26 : C1359c.values()) {
            C1236c g3 = cVar26.g();
            kotlin.jvm.internal.j.d(g3, "getWrapperFqName(...)");
            C1236c e7 = g3.e();
            C1240g f8 = g3.f();
            kotlin.jvm.internal.j.d(f8, "shortName(...)");
            C1235b bVar4 = new C1235b(e7, f8);
            l f10 = cVar26.f();
            kotlin.jvm.internal.j.d(f10, "getPrimitiveType(...)");
            C1236c c5 = q.l.c(f10.f());
            C1236c e8 = c5.e();
            C1240g f11 = c5.f();
            kotlin.jvm.internal.j.d(f11, "shortName(...)");
            a(bVar4, new C1235b(e8, f11));
        }
        for (C1235b bVar5 : Ne.d.f3545a) {
            C1236c cVar27 = new C1236c("kotlin.jvm.internal." + bVar5.f().b() + "CompanionObject");
            C1236c e9 = cVar27.e();
            C1240g f12 = cVar27.f();
            kotlin.jvm.internal.j.d(f12, "shortName(...)");
            a(new C1235b(e9, f12), bVar5.d(C1242i.b));
        }
        for (int i2 = 0; i2 < 23; i2++) {
            C1236c cVar28 = new C1236c(C0086a.i(i2, "kotlin.jvm.functions.Function"));
            C1236c e10 = cVar28.e();
            C1240g f13 = cVar28.f();
            kotlin.jvm.internal.j.d(f13, "shortName(...)");
            a(new C1235b(e10, f13), new C1235b(q.l, C1240g.e("Function" + i2)));
            f3636i.put(new C1236c(b + i2).i(), g);
        }
        for (int i7 = 0; i7 < 22; i7++) {
            j jVar2 = j.f3620c;
            f3636i.put(new C1236c((jVar2.f3622a + '.' + jVar2.b) + i7).i(), g);
        }
        f3636i.put(p.b.g().i(), d(Void.class));
    }

    public static void a(C1235b bVar, C1235b bVar2) {
        f3635h.put(bVar.a().i(), bVar2);
        f3636i.put(bVar2.a().i(), bVar);
    }

    public static void b(Class cls, C1236c cVar) {
        C1235b d2 = d(cls);
        kotlin.jvm.internal.j.e(cVar, "topLevelFqName");
        C1236c e7 = cVar.e();
        C1240g f5 = cVar.f();
        kotlin.jvm.internal.j.d(f5, "shortName(...)");
        a(d2, new C1235b(e7, f5));
    }

    public static void c(Class cls, C1238e eVar) {
        b(cls, eVar.g());
    }

    public static C1235b d(Class cls) {
        if (!cls.isPrimitive()) {
            boolean isArray = cls.isArray();
        }
        Class<?> declaringClass = cls.getDeclaringClass();
        if (declaringClass != null) {
            return d(declaringClass).d(C1240g.e(cls.getSimpleName()));
        }
        C1236c cVar = new C1236c(cls.getCanonicalName());
        C1236c e7 = cVar.e();
        C1240g f5 = cVar.f();
        kotlin.jvm.internal.j.d(f5, "shortName(...)");
        return new C1235b(e7, f5);
    }

    public static boolean e(C1238e eVar, String str) {
        Integer n02;
        String str2 = eVar.f5037a;
        if (str2 == null) {
            C1238e.a(4);
            throw null;
        } else if (!v.t0(str2, str)) {
            return false;
        } else {
            String substring = str2.substring(str.length());
            kotlin.jvm.internal.j.d(substring, "substring(...)");
            if (n.L0(substring, '0') || (n02 = u.n0(substring)) == null || n02.intValue() < 23) {
                return false;
            }
            return true;
        }
    }

    public static C1235b f(C1238e eVar) {
        if (e(eVar, f3633a) || e(eVar, f3634c)) {
            return e;
        }
        if (!e(eVar, b) && !e(eVar, d)) {
            return (C1235b) f3636i.get(eVar);
        }
        return g;
    }
}
