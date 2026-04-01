package If;

import A.a;
import Hf.C0754c;
import Hf.C0764m;
import Hf.C0768q;
import Hf.C0773w;
import Hf.C0774x;
import Hf.E;
import Hf.G;
import Hf.I;
import Hf.L;
import Hf.M;
import Hf.N;
import Hf.P;
import Hf.X;
import Hf.a0;
import Hf.c0;
import Hf.d0;
import Kf.b;
import Kf.c;
import Kf.d;
import Kf.e;
import Kf.f;
import Ne.i;
import Ne.p;
import Qe.A;
import Qe.B;
import Qe.C0816f;
import Qe.C0817g;
import Qe.C0819i;
import Qe.C0822l;
import Qe.C0832w;
import Qe.V;
import Qe.W;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import ne.C1194l;
import ne.C1196n;
import qf.C1236c;
import sf.C1283j;
import uf.C1317b;
import vf.C1336p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class g {

    /* renamed from: a  reason: collision with root package name */
    public static final B f3462a = new B("KotlinTypeRefiner", 0);

    public static boolean A(Kf.g gVar) {
        j.e(gVar, "$receiver");
        if (gVar instanceof M) {
            return ((M) gVar).i();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(gVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
    }

    public static boolean B(d dVar) {
        j.e(dVar, "$receiver");
        if (dVar instanceof C0774x) {
            return C0754c.k((C0774x) dVar);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(dVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, dVar.getClass(), sb2).toString());
    }

    public static boolean C(Kf.g gVar) {
        C0816f fVar;
        j.e(gVar, "$receiver");
        if (gVar instanceof M) {
            C0819i g = ((M) gVar).g();
            W w = null;
            if (g instanceof C0816f) {
                fVar = (C0816f) g;
            } else {
                fVar = null;
            }
            if (fVar != null) {
                w = fVar.N();
            }
            return w instanceof C0832w;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(gVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
    }

    public static boolean D(Kf.g gVar) {
        j.e(gVar, "$receiver");
        if (gVar instanceof M) {
            return gVar instanceof C1336p;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(gVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
    }

    public static boolean E(Kf.g gVar) {
        j.e(gVar, "$receiver");
        if (gVar instanceof M) {
            return gVar instanceof C0773w;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(gVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
    }

    public static boolean F(e eVar) {
        j.e(eVar, "$receiver");
        if (eVar instanceof Hf.B) {
            return ((Hf.B) eVar).u0();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(eVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
    }

    public static boolean G(Kf.g gVar) {
        j.e(gVar, "$receiver");
        if (gVar instanceof M) {
            return i.H((M) gVar, p.b);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(gVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
    }

    public static boolean H(d dVar) {
        j.e(dVar, "$receiver");
        if (dVar instanceof C0774x) {
            return a0.e((C0774x) dVar);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(dVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, dVar.getClass(), sb2).toString());
    }

    public static boolean I(e eVar) {
        if (eVar instanceof C0774x) {
            return i.F((C0774x) eVar);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(eVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
    }

    public static boolean J(c cVar) {
        if (cVar instanceof h) {
            return ((h) cVar).f3465j;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(cVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, cVar.getClass(), sb2).toString());
    }

    public static boolean K(P p6) {
        j.e(p6, "$receiver");
        if (p6 instanceof P) {
            return p6.c();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(p6);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, p6.getClass(), sb2).toString());
    }

    public static void L(e eVar) {
        j.e(eVar, "$receiver");
        if (eVar instanceof Hf.B) {
            C0774x xVar = (C0774x) eVar;
            return;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(eVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
    }

    public static void M(e eVar) {
        j.e(eVar, "$receiver");
        if (eVar instanceof Hf.B) {
            C0774x xVar = (C0774x) eVar;
            return;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(eVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
    }

    public static Hf.B N(C0768q qVar) {
        if (qVar instanceof C0768q) {
            return qVar.e;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(qVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, qVar.getClass(), sb2).toString());
    }

    public static c0 O(c cVar) {
        if (cVar instanceof h) {
            return ((h) cVar).g;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(cVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, cVar.getClass(), sb2).toString());
    }

    public static c0 P(d dVar) {
        if (dVar instanceof c0) {
            return C0754c.n((c0) dVar, false);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(dVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, dVar.getClass(), sb2).toString());
    }

    public static Hf.B Q(C0764m mVar) {
        if (mVar instanceof C0764m) {
            return mVar.e;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(mVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, mVar.getClass(), sb2).toString());
    }

    public static int R(Kf.g gVar) {
        j.e(gVar, "$receiver");
        if (gVar instanceof M) {
            return ((M) gVar).getParameters().size();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(gVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
    }

    public static Set S(b bVar, e eVar) {
        j.e(eVar, "$receiver");
        M q0 = bVar.q0(eVar);
        if (q0 instanceof C1336p) {
            return ((C1336p) q0).f5161a;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(eVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
    }

    public static P T(C1317b bVar) {
        j.e(bVar, "$receiver");
        if (bVar instanceof i) {
            return ((i) bVar).f3466a;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(bVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, bVar.getClass(), sb2).toString());
    }

    public static a U(b bVar, e eVar) {
        if (eVar instanceof Hf.B) {
            C0774x xVar = (C0774x) eVar;
            return new a(bVar, new X(N.b.f(xVar.s0(), xVar.e0())));
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(eVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
    }

    public static Collection V(Kf.g gVar) {
        j.e(gVar, "$receiver");
        if (gVar instanceof M) {
            Collection h5 = ((M) gVar).h();
            j.d(h5, "getSupertypes(...)");
            return h5;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(gVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
    }

    public static M W(e eVar) {
        j.e(eVar, "$receiver");
        if (eVar instanceof Hf.B) {
            return ((Hf.B) eVar).s0();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(eVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
    }

    public static i X(c cVar) {
        j.e(cVar, "$receiver");
        if (cVar instanceof h) {
            return ((h) cVar).f;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(cVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, cVar.getClass(), sb2).toString());
    }

    public static Hf.B Y(C0768q qVar) {
        if (qVar instanceof C0768q) {
            return qVar.f;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(qVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, qVar.getClass(), sb2).toString());
    }

    public static Hf.B Z(e eVar, boolean z) {
        j.e(eVar, "$receiver");
        if (eVar instanceof Hf.B) {
            return ((Hf.B) eVar).y0(z);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(eVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
    }

    public static /* synthetic */ void a(int i2) {
        Object[] objArr = new Object[3];
        switch (i2) {
            case 1:
            case 4:
                objArr[0] = "b";
                break;
            case 2:
            case 7:
                objArr[0] = "typeCheckingProcedure";
                break;
            case 5:
            case 10:
                objArr[0] = "subtype";
                break;
            case 6:
            case 11:
                objArr[0] = "supertype";
                break;
            case 8:
                objArr[0] = "type";
                break;
            case 9:
                objArr[0] = "typeProjection";
                break;
            default:
                objArr[0] = "a";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/types/checker/TypeCheckerProcedureCallbacksImpl";
        switch (i2) {
            case 3:
            case 4:
                objArr[2] = "assertEqualTypeConstructors";
                break;
            case 5:
            case 6:
            case 7:
                objArr[2] = "assertSubtype";
                break;
            case 8:
            case 9:
                objArr[2] = ScreenShotFilterType.CAPTURE;
                break;
            case 10:
            case 11:
                objArr[2] = "noCorrespondingSupertype";
                break;
            default:
                objArr[2] = "assertEqualTypes";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static d a0(b bVar, d dVar) {
        if (dVar instanceof e) {
            return bVar.I((e) dVar, true);
        }
        if (dVar instanceof C0768q) {
            C0768q qVar = (C0768q) dVar;
            return bVar.x(bVar.I(bVar.a0(qVar), true), bVar.I(bVar.p(qVar), true));
        }
        throw new IllegalStateException("sealed");
    }

    public static boolean b(Kf.g gVar, Kf.g gVar2) {
        j.e(gVar, "c1");
        j.e(gVar2, "c2");
        if (!(gVar instanceof M)) {
            StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb2.append(gVar);
            sb2.append(ArcCommonLog.TAG_COMMA);
            throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
        } else if (gVar2 instanceof M) {
            return gVar.equals(gVar2);
        } else {
            StringBuilder sb3 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb3.append(gVar2);
            sb3.append(ArcCommonLog.TAG_COMMA);
            throw new IllegalArgumentException(a.g(v.f4727a, gVar2.getClass(), sb3).toString());
        }
    }

    public static int c(d dVar) {
        j.e(dVar, "$receiver");
        if (dVar instanceof C0774x) {
            return ((C0774x) dVar).e0().size();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(dVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, dVar.getClass(), sb2).toString());
    }

    public static f d(e eVar) {
        j.e(eVar, "$receiver");
        if (eVar instanceof Hf.B) {
            return (f) eVar;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(eVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
    }

    public static c e(b bVar, e eVar) {
        j.e(eVar, "$receiver");
        if (!(eVar instanceof Hf.B)) {
            StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb2.append(eVar);
            sb2.append(ArcCommonLog.TAG_COMMA);
            throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
        } else if (eVar instanceof E) {
            return bVar.e(((E) eVar).e);
        } else {
            if (eVar instanceof h) {
                return (h) eVar;
            }
            return null;
        }
    }

    public static C0764m f(e eVar) {
        j.e(eVar, "$receiver");
        if (!(eVar instanceof Hf.B)) {
            StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb2.append(eVar);
            sb2.append(ArcCommonLog.TAG_COMMA);
            throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
        } else if (eVar instanceof C0764m) {
            return (C0764m) eVar;
        } else {
            return null;
        }
    }

    public static C0768q g(d dVar) {
        j.e(dVar, "$receiver");
        if (dVar instanceof C0774x) {
            c0 x02 = ((C0774x) dVar).x0();
            if (x02 instanceof C0768q) {
                return (C0768q) x02;
            }
            return null;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(dVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, dVar.getClass(), sb2).toString());
    }

    public static Hf.B h(d dVar) {
        j.e(dVar, "$receiver");
        if (dVar instanceof C0774x) {
            c0 x02 = ((C0774x) dVar).x0();
            if (x02 instanceof Hf.B) {
                return (Hf.B) x02;
            }
            return null;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(dVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, dVar.getClass(), sb2).toString());
    }

    public static G i(d dVar) {
        j.e(dVar, "$receiver");
        if (dVar instanceof C0774x) {
            return com.samsung.context.sdk.samsunganalytics.internal.sender.c.h((C0774x) dVar);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(dVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, dVar.getClass(), sb2).toString());
    }

    public static Hf.B j(e eVar, b bVar) {
        ArrayList arrayList;
        e eVar2;
        b bVar2;
        c0 c0Var;
        j.e(bVar, "status");
        if (eVar instanceof Hf.B) {
            Hf.B b = (Hf.B) eVar;
            if (b.e0().size() == b.s0().getParameters().size()) {
                List e02 = b.e0();
                Iterable iterable = e02;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    Iterator it = iterable.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        } else if (((P) it.next()).a() != d0.INVARIANT) {
                            List parameters = b.s0().getParameters();
                            j.d(parameters, "getParameters(...)");
                            ArrayList r1 = C1194l.r1(iterable, parameters);
                            arrayList = new ArrayList(C1196n.w0(r1, 10));
                            Iterator it2 = r1.iterator();
                            while (it2.hasNext()) {
                                me.i iVar = (me.i) it2.next();
                                P p6 = (P) iVar.d;
                                V v = (V) iVar.e;
                                if (p6.a() == d0.INVARIANT) {
                                    bVar2 = bVar;
                                } else {
                                    if (p6.c() || p6.a() != d0.IN_VARIANCE) {
                                        c0Var = null;
                                    } else {
                                        c0Var = p6.b().x0();
                                    }
                                    j.b(v);
                                    bVar2 = bVar;
                                    p6 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.h(new h(bVar2, new i(p6, (Ff.e) null, v, 6), c0Var, (I) null, false, 56));
                                }
                                arrayList.add(p6);
                                bVar = bVar2;
                            }
                            X x9 = new X(N.b.f(b.s0(), arrayList));
                            int size = e02.size();
                            for (int i2 = 0; i2 < size; i2++) {
                                P p8 = (P) e02.get(i2);
                                P p10 = (P) arrayList.get(i2);
                                if (p8.a() != d0.INVARIANT) {
                                    List upperBounds = ((V) b.s0().getParameters().get(i2)).getUpperBounds();
                                    j.d(upperBounds, "getUpperBounds(...)");
                                    ArrayList arrayList2 = new ArrayList();
                                    Iterator it3 = upperBounds.iterator();
                                    while (true) {
                                        boolean hasNext = it3.hasNext();
                                        eVar2 = e.f3460a;
                                        if (!hasNext) {
                                            break;
                                        }
                                        arrayList2.add(eVar2.a(x9.g((C0774x) it3.next(), d0.INVARIANT).x0()));
                                    }
                                    if (!p8.c() && p8.a() == d0.OUT_VARIANCE) {
                                        arrayList2.add(eVar2.a(p8.b().x0()));
                                    }
                                    C0774x b5 = p10.b();
                                    j.c(b5, "null cannot be cast to non-null type org.jetbrains.kotlin.types.checker.NewCapturedType");
                                    i iVar2 = ((h) b5).f;
                                    iVar2.getClass();
                                    iVar2.b = new Ff.e(arrayList2, 2);
                                }
                            }
                        }
                    }
                }
            }
            arrayList = null;
            if (arrayList != null) {
                return C0754c.u(b.p0(), b.s0(), arrayList, b.u0());
            }
            return null;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(eVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
    }

    public static b k(c cVar) {
        if (cVar instanceof h) {
            return ((h) cVar).e;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(cVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, cVar.getClass(), sb2).toString());
    }

    public static L l(boolean z, e eVar, int i2) {
        if ((i2 & 8) != 0) {
            eVar = e.f3460a;
        }
        return new L(z, true, m.d, eVar, f.f3461a);
    }

    public static c0 m(b bVar, e eVar, e eVar2) {
        j.e(eVar, "lowerBound");
        j.e(eVar2, "upperBound");
        if (!(eVar instanceof Hf.B)) {
            StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb2.append(bVar);
            sb2.append(ArcCommonLog.TAG_COMMA);
            throw new IllegalArgumentException(a.g(v.f4727a, bVar.getClass(), sb2).toString());
        } else if (eVar2 instanceof Hf.B) {
            return C0754c.f((Hf.B) eVar, (Hf.B) eVar2);
        } else {
            StringBuilder sb3 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb3.append(bVar);
            sb3.append(ArcCommonLog.TAG_COMMA);
            throw new IllegalArgumentException(a.g(v.f4727a, bVar.getClass(), sb3).toString());
        }
    }

    public static final String n(M m) {
        StringBuilder sb2 = new StringBuilder();
        o(sb2, "type: " + m);
        o(sb2, "hashCode: " + m.hashCode());
        o(sb2, "javaClass: " + m.getClass().getCanonicalName());
        for (C0822l g = m.g(); g != null; g = g.g()) {
            o(sb2, "fqName: ".concat(C1283j.f5083c.w(g)));
            o(sb2, "javaClass: " + g.getClass().getCanonicalName());
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public static final void o(StringBuilder sb2, String str) {
        j.e(str, "<this>");
        sb2.append(str);
        sb2.append(10);
    }

    public static P p(d dVar, int i2) {
        j.e(dVar, "$receiver");
        if (dVar instanceof C0774x) {
            return (P) ((C0774x) dVar).e0().get(i2);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(dVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, dVar.getClass(), sb2).toString());
    }

    public static V q(Kf.g gVar, int i2) {
        j.e(gVar, "$receiver");
        if (gVar instanceof M) {
            Object obj = ((M) gVar).getParameters().get(i2);
            j.d(obj, "get(...)");
            return (V) obj;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(gVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
    }

    public static c0 r(P p6) {
        j.e(p6, "$receiver");
        if (p6 instanceof P) {
            return p6.b().x0();
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(p6);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, p6.getClass(), sb2).toString());
    }

    public static V s(Kf.g gVar) {
        j.e(gVar, "$receiver");
        if (gVar instanceof M) {
            C0819i g = ((M) gVar).g();
            if (g instanceof V) {
                return (V) g;
            }
            return null;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(gVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
    }

    public static Kf.i t(P p6) {
        j.e(p6, "$receiver");
        if (p6 instanceof P) {
            d0 a7 = p6.a();
            j.d(a7, "getProjectionKind(...)");
            return B1.a.o(a7);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(p6);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, p6.getClass(), sb2).toString());
    }

    public static boolean u(d dVar, C1236c cVar) {
        j.e(dVar, "$receiver");
        j.e(cVar, "fqName");
        if (dVar instanceof C0774x) {
            return ((C0774x) dVar).getAnnotations().g(cVar);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(dVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, dVar.getClass(), sb2).toString());
    }

    public static boolean v(V v, Kf.g gVar) {
        boolean z;
        if (gVar == null) {
            z = true;
        } else {
            z = gVar instanceof M;
        }
        if (z) {
            return com.samsung.context.sdk.samsunganalytics.internal.sender.c.I(v, (M) gVar, 4);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(v);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, v.getClass(), sb2).toString());
    }

    public static boolean w(e eVar, e eVar2) {
        j.e(eVar, "a");
        j.e(eVar2, "b");
        if (!(eVar instanceof Hf.B)) {
            StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb2.append(eVar);
            sb2.append(ArcCommonLog.TAG_COMMA);
            throw new IllegalArgumentException(a.g(v.f4727a, eVar.getClass(), sb2).toString());
        } else if (!(eVar2 instanceof Hf.B)) {
            StringBuilder sb3 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
            sb3.append(eVar2);
            sb3.append(ArcCommonLog.TAG_COMMA);
            throw new IllegalArgumentException(a.g(v.f4727a, eVar2.getClass(), sb3).toString());
        } else if (((Hf.B) eVar).e0() == ((Hf.B) eVar2).e0()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean x(Kf.g gVar) {
        j.e(gVar, "$receiver");
        if (gVar instanceof M) {
            return i.H((M) gVar, p.f3565a);
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(gVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
    }

    public static boolean y(Kf.g gVar) {
        j.e(gVar, "$receiver");
        if (gVar instanceof M) {
            return ((M) gVar).g() instanceof C0816f;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(gVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
    }

    public static boolean z(Kf.g gVar) {
        C0816f fVar;
        if (gVar instanceof M) {
            C0819i g = ((M) gVar).g();
            if (g instanceof C0816f) {
                fVar = (C0816f) g;
            } else {
                fVar = null;
            }
            if (fVar == null || fVar.k() != A.FINAL || fVar.b() == C0817g.ENUM_CLASS || fVar.b() == C0817g.ENUM_ENTRY || fVar.b() == C0817g.ANNOTATION_CLASS) {
                return false;
            }
            return true;
        }
        StringBuilder sb2 = new StringBuilder("ClassicTypeSystemContext couldn't handle: ");
        sb2.append(gVar);
        sb2.append(ArcCommonLog.TAG_COMMA);
        throw new IllegalArgumentException(a.g(v.f4727a, gVar.getClass(), sb2).toString());
    }
}
