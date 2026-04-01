package tf;

import Af.f;
import Af.p;
import Bf.b;
import Df.C0736b;
import Ff.w;
import He.F;
import Hf.C0754c;
import Hf.C0774x;
import Hf.G;
import Hf.M;
import Hf.d0;
import Ne.q;
import Qe.A;
import Qe.B;
import Qe.C;
import Qe.C0812b;
import Qe.C0813c;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0817g;
import Qe.C0819i;
import Qe.C0822l;
import Qe.C0826p;
import Qe.C0827q;
import Qe.C0831v;
import Qe.C0833x;
import Qe.O;
import Qe.Q;
import Qe.U;
import Qf.k;
import Re.g;
import Re.h;
import Te.C0841b;
import Te.H;
import Te.I;
import Te.J;
import Te.K;
import Te.r;
import Te.u;
import Tf.m;
import Ye.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import qf.C1240g;
import qf.C1241h;
import qf.C1243j;
import xf.C1353d;

/* renamed from: tf.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1312p {

    /* renamed from: a  reason: collision with root package name */
    public static final B f5144a = new B("ResolutionAnchorProvider", 0);
    public static final B b = new B("StdlibClassFinder", 0);

    public static /* synthetic */ void a(int i2) {
        String str;
        int i7;
        Throwable th;
        if (i2 == 12 || i2 == 23 || i2 == 25) {
            str = "@NotNull method %s.%s must not return null";
        } else {
            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        }
        if (i2 == 12 || i2 == 23 || i2 == 25) {
            i7 = 2;
        } else {
            i7 = 3;
        }
        Object[] objArr = new Object[i7];
        switch (i2) {
            case 1:
            case 4:
            case 8:
            case 14:
            case 16:
            case 18:
            case 31:
            case 33:
            case 35:
                objArr[0] = "annotations";
                break;
            case 2:
            case 5:
            case 9:
                objArr[0] = "parameterAnnotations";
                break;
            case 6:
            case 11:
            case 19:
                objArr[0] = "sourceElement";
                break;
            case 10:
                objArr[0] = "visibility";
                break;
            case 12:
            case 23:
            case 25:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory";
                break;
            case 20:
                objArr[0] = "containingClass";
                break;
            case 21:
                objArr[0] = "source";
                break;
            case 22:
            case 24:
            case 26:
                objArr[0] = "enumClass";
                break;
            case 27:
            case 28:
            case 29:
                objArr[0] = "descriptor";
                break;
            case 30:
            case 32:
            case 34:
                objArr[0] = "owner";
                break;
            default:
                objArr[0] = "propertyDescriptor";
                break;
        }
        if (i2 == 12) {
            objArr[1] = "createSetter";
        } else if (i2 == 23) {
            objArr[1] = "createEnumValuesMethod";
        } else if (i2 != 25) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorFactory";
        } else {
            objArr[1] = "createEnumValueOfMethod";
        }
        switch (i2) {
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[2] = "createSetter";
                break;
            case 12:
            case 23:
            case 25:
                break;
            case 13:
            case 14:
                objArr[2] = "createDefaultGetter";
                break;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                objArr[2] = "createGetter";
                break;
            case 20:
            case 21:
                objArr[2] = "createPrimaryConstructorForObject";
                break;
            case 22:
                objArr[2] = "createEnumValuesMethod";
                break;
            case 24:
                objArr[2] = "createEnumValueOfMethod";
                break;
            case 26:
                objArr[2] = "createEnumEntriesProperty";
                break;
            case 27:
                objArr[2] = "isEnumValuesMethod";
                break;
            case 28:
                objArr[2] = "isEnumValueOfMethod";
                break;
            case 29:
                objArr[2] = "isEnumSpecialMethod";
                break;
            case 30:
            case 31:
                objArr[2] = "createExtensionReceiverParameterForCallable";
                break;
            case 32:
            case 33:
                objArr[2] = "createContextReceiverParameterForCallable";
                break;
            case 34:
            case 35:
                objArr[2] = "createContextReceiverParameterForClass";
                break;
            default:
                objArr[2] = "createDefaultSetter";
                break;
        }
        String format = String.format(str, objArr);
        if (i2 == 12 || i2 == 23 || i2 == 25) {
            th = new IllegalStateException(format);
        } else {
            th = new IllegalArgumentException(format);
        }
        throw th;
    }

    public static final void c(C0816f fVar, LinkedHashSet linkedHashSet, p pVar, boolean z) {
        for (C0822l lVar : F.F(pVar, f.f3311o, 2)) {
            if (lVar instanceof C0816f) {
                C0816f fVar2 = (C0816f) lVar;
                if (fVar2.b0()) {
                    C1240g name = fVar2.getName();
                    j.d(name, "getName(...)");
                    C0819i c5 = pVar.c(name, c.WHEN_GET_ALL_DESCRIPTORS);
                    if (c5 instanceof C0816f) {
                        fVar2 = (C0816f) c5;
                    } else if (c5 instanceof U) {
                        fVar2 = ((w) ((U) c5)).E0();
                    } else {
                        fVar2 = null;
                    }
                }
                if (fVar2 != null) {
                    int i2 = C1301e.f5137a;
                    Iterator it = fVar2.q().h().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (C1301e.p((C0774x) it.next(), fVar.a())) {
                                linkedHashSet.add(fVar2);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (z) {
                        p M2 = fVar2.M();
                        j.d(M2, "getUnsubstitutedInnerClassesScope(...)");
                        c(fVar, linkedHashSet, M2, z);
                    }
                }
            }
        }
    }

    public static u e(C0812b bVar, C0774x xVar, C1240g gVar, h hVar, int i2) {
        if (hVar == null) {
            a(33);
            throw null;
        } else if (xVar == null) {
            return null;
        } else {
            b bVar2 = new b(bVar, xVar, gVar);
            m mVar = C1241h.f5040a;
            return new u(bVar, bVar2, hVar, C1240g.e(C1241h.b + '_' + i2));
        }
    }

    public static I f(O o2, h hVar) {
        return l(o2, hVar, true, o2.getSource());
    }

    public static J g(O o2, h hVar) {
        Q source = o2.getSource();
        if (source != null) {
            return m(o2, hVar, g.f3692a, true, o2.getVisibility(), source);
        }
        a(6);
        throw null;
    }

    public static H h(C0841b bVar) {
        if (bVar != null) {
            C d = C1301e.d(bVar);
            j.e(d, "<this>");
            C1313q qVar = (C1313q) d.x(b);
            C0816f d2 = C0833x.d(d, C1243j.w);
            if (d2 == null) {
                return null;
            }
            A a7 = A.FINAL;
            C0826p pVar = C0827q.e;
            C1240g gVar = q.b;
            C0813c cVar = C0813c.SYNTHESIZED;
            H F02 = H.F0(bVar, a7, pVar, false, gVar, cVar, bVar.getSource());
            C0826p pVar2 = pVar;
            A a10 = a7;
            H h5 = F02;
            I i2 = new I(h5, g.f3692a, a10, pVar2, false, false, false, cVar, (I) null, bVar.getSource());
            h5.I0(i2, (J) null, (r) null, (r) null);
            Hf.I.e.getClass();
            Hf.I i7 = Hf.I.f;
            M q = d2.q();
            List singletonList = Collections.singletonList(new G((C0774x) bVar.i()));
            j.e(i7, "attributes");
            j.e(q, "constructor");
            j.e(singletonList, "arguments");
            Hf.B u = C0754c.u(i7, q, singletonList, false);
            List list = Collections.EMPTY_LIST;
            h5.L0(u, list, (u) null, (u) null, list);
            i2.H0(h5.getReturnType());
            return h5;
        }
        a(26);
        throw null;
    }

    public static K i(C0841b bVar) {
        if (bVar != null) {
            K P02 = K.P0(bVar, q.f3575c, C0813c.SYNTHESIZED, bVar.getSource());
            Te.Q q = new Te.Q(P02, (Te.Q) null, 0, g.f3692a, C1240g.e("value"), C1353d.e(bVar).u(), false, false, false, (C0774x) null, bVar.getSource());
            List list = Collections.EMPTY_LIST;
            return P02.J0((u) null, (u) null, list, list, Collections.singletonList(q), bVar.i(), A.FINAL, C0827q.e);
        }
        a(24);
        throw null;
    }

    public static K j(C0841b bVar) {
        if (bVar != null) {
            K P02 = K.P0(bVar, q.f3574a, C0813c.SYNTHESIZED, bVar.getSource());
            List list = Collections.EMPTY_LIST;
            return P02.J0((u) null, (u) null, list, list, list, C1353d.e(bVar).h(d0.INVARIANT, bVar.i()), A.FINAL, C0827q.e);
        }
        a(22);
        throw null;
    }

    public static u k(C0812b bVar, C0774x xVar, h hVar) {
        if (xVar == null) {
            return null;
        }
        return new u(bVar, new Bf.c(bVar, xVar), hVar);
    }

    public static I l(O o2, h hVar, boolean z, Q q) {
        if (hVar == null) {
            a(18);
            throw null;
        } else if (q != null) {
            return new I(o2, hVar, o2.k(), o2.getVisibility(), z, false, false, C0813c.DECLARATION, (I) null, q);
        } else {
            a(19);
            throw null;
        }
    }

    public static J m(O o2, h hVar, h hVar2, boolean z, C0826p pVar, Q q) {
        if (hVar == null) {
            a(8);
            throw null;
        } else if (hVar2 == null) {
            a(9);
            throw null;
        } else if (pVar == null) {
            a(10);
            throw null;
        } else if (q != null) {
            C0826p pVar2 = pVar;
            J j2 = new J(o2, hVar, o2.k(), pVar2, z, false, false, C0813c.DECLARATION, (J) null, q);
            j2.q = J.G0(j2, o2.getType(), hVar2);
            return j2;
        } else {
            a(11);
            throw null;
        }
    }

    public static boolean n(C0831v vVar) {
        if (vVar.b() != C0813c.SYNTHESIZED) {
            return false;
        }
        C0822l g = vVar.g();
        int i2 = C1301e.f5137a;
        if (C1301e.n(g, C0817g.ENUM_CLASS)) {
            return true;
        }
        return false;
    }

    public static final Collection o(Collection collection, Ae.b bVar) {
        j.e(collection, "<this>");
        if (collection.size() <= 1) {
            return collection;
        }
        LinkedList linkedList = new LinkedList(collection);
        int i2 = Qf.h.f;
        Qf.h e = k.e();
        while (!linkedList.isEmpty()) {
            Object L02 = C1194l.L0(linkedList);
            int i7 = Qf.h.f;
            Qf.h e7 = k.e();
            ArrayList g = C1311o.g(L02, linkedList, bVar, new C0736b(25, e7));
            if (g.size() != 1 || !e7.isEmpty()) {
                Object s = C1311o.s(g, bVar);
                C0812b bVar2 = (C0812b) bVar.invoke(s);
                Iterator it = g.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    j.b(next);
                    if (!C1311o.k(bVar2, (C0812b) bVar.invoke(next))) {
                        e7.add(next);
                    }
                }
                if (!e7.isEmpty()) {
                    e.addAll(e7);
                }
                e.add(s);
            } else {
                Object a12 = C1194l.a1(g);
                j.d(a12, "single(...)");
                e.add(a12);
            }
        }
        return e;
    }

    public abstract void b(C0814d dVar);

    public abstract void d(C0814d dVar, C0814d dVar2);

    public void p(C0814d dVar, Collection collection) {
        j.e(dVar, "member");
        dVar.m0(collection);
    }
}
