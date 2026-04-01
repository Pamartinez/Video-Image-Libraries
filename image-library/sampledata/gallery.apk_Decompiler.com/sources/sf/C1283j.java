package sf;

import Ae.b;
import G0.e;
import Gf.k;
import He.t;
import Hf.B;
import Hf.C0752a;
import Hf.C0754c;
import Hf.C0764m;
import Hf.C0768q;
import Hf.C0773w;
import Hf.C0774x;
import Hf.C0776z;
import Hf.M;
import Hf.P;
import Hf.a0;
import Hf.c0;
import Jf.i;
import Jf.l;
import L1.d;
import L2.a;
import Ne.p;
import Qe.A;
import Qe.C;
import Qe.C0813c;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0817g;
import Qe.C0819i;
import Qe.C0820j;
import Qe.C0822l;
import Qe.C0823m;
import Qe.C0826p;
import Qe.C0827q;
import Qe.C0833x;
import Qe.C0834y;
import Qe.H;
import Qe.L;
import Qe.O;
import Qe.U;
import Qe.V;
import Qe.Y;
import Te.I;
import Te.J;
import Te.Q;
import Te.r;
import Te.u;
import Tf.n;
import Tf.v;
import a.C0068a;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.scs.base.utils.Log;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.j;
import me.m;
import ne.C1194l;
import ne.C1195m;
import o1.C0246a;
import qf.C1236c;
import qf.C1238e;
import qf.C1240g;
import tf.C1301e;
import vf.C1321a;
import vf.C1322b;
import vf.C1326f;
import vf.C1327g;
import vf.C1338r;
import vf.C1339s;
import vf.C1340t;
import vf.C1341u;

/* renamed from: sf.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1283j implements C1286m {

    /* renamed from: c  reason: collision with root package name */
    public static final C1283j f5083c = a.H(C1277d.l);
    public static final C1283j d = a.H(C1277d.n);
    public static final C1283j e = a.H(C1277d.f5077o);

    /* renamed from: a  reason: collision with root package name */
    public final C1288o f5084a;
    public final m b = d.q(new uf.d(1, this));

    static {
        a.H(C1277d.e);
        a.H(C1277d.g);
        a.H(C1277d.f5074h);
        a.H(C1277d.f5075i);
        a.H(C1277d.f5076j);
        a.H(C1277d.k);
        a.H(C1277d.m);
        a.H(C1277d.f);
    }

    public C1283j(C1288o oVar) {
        this.f5084a = oVar;
    }

    public static void X(StringBuilder sb2) {
        int length = sb2.length();
        if (length == 0 || sb2.charAt(length - 1) != ' ') {
            sb2.append(' ');
        }
    }

    public static boolean j0(C0774x xVar) {
        if (!Gd.a.K(xVar)) {
            return false;
        }
        Iterable<P> e02 = xVar.e0();
        if ((e02 instanceof Collection) && ((Collection) e02).isEmpty()) {
            return true;
        }
        for (P c5 : e02) {
            if (c5.c()) {
                return false;
            }
        }
        return true;
    }

    public static final void n(C1283j jVar, O o2, StringBuilder sb2) {
        boolean z;
        boolean z3;
        boolean r = jVar.r();
        C1288o oVar = jVar.f5084a;
        if (!r) {
            C1287n nVar = oVar.g;
            t[] tVarArr = C1288o.Y;
            if (!((Boolean) nVar.f(oVar, tVarArr[5])).booleanValue()) {
                List i02 = o2.i0();
                j.d(i02, "getContextReceiverParameters(...)");
                jVar.C(sb2, i02);
                if (jVar.q().contains(C1285l.ANNOTATIONS)) {
                    jVar.y(sb2, o2, (Re.d) null);
                    r h0 = o2.h0();
                    if (h0 != null) {
                        jVar.y(sb2, h0, Re.d.FIELD);
                    }
                    r I6 = o2.I();
                    if (I6 != null) {
                        jVar.y(sb2, I6, Re.d.PROPERTY_DELEGATE_FIELD);
                    }
                    if (((C1293t) oVar.f5089H.f(oVar, tVarArr[32])) == C1293t.NONE) {
                        I getter = o2.getGetter();
                        if (getter != null) {
                            jVar.y(sb2, getter, Re.d.PROPERTY_GETTER);
                        }
                        J setter = o2.getSetter();
                        if (setter != null) {
                            jVar.y(sb2, setter, Re.d.PROPERTY_SETTER);
                            List B = setter.B();
                            j.d(B, "getValueParameters(...)");
                            Q q = (Q) C1194l.b1(B);
                            j.b(q);
                            jVar.y(sb2, q, Re.d.SETTER_PARAMETER);
                        }
                    }
                }
                C0826p visibility = o2.getVisibility();
                j.d(visibility, "getVisibility(...)");
                jVar.h0(visibility, sb2);
                if (!jVar.q().contains(C1285l.CONST) || !o2.U()) {
                    z = false;
                } else {
                    z = true;
                }
                jVar.N(sb2, z, "const");
                jVar.K(o2, sb2);
                jVar.M(o2, sb2);
                jVar.S(o2, sb2);
                if (!jVar.q().contains(C1285l.LATEINIT) || !o2.j0()) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                jVar.N(sb2, z3, "lateinit");
                jVar.J(o2, sb2);
            }
            jVar.e0(o2, sb2, false);
            List typeParameters = o2.getTypeParameters();
            j.d(typeParameters, "getTypeParameters(...)");
            jVar.d0(typeParameters, sb2, true);
            jVar.V(o2, sb2);
        }
        jVar.P(o2, sb2, true);
        sb2.append(": ");
        C0774x type = o2.getType();
        j.d(type, "getType(...)");
        sb2.append(jVar.Y(type));
        jVar.W(o2, sb2);
        jVar.H(o2, sb2);
        List typeParameters2 = o2.getTypeParameters();
        j.d(typeParameters2, "getTypeParameters(...)");
        jVar.i0(sb2, typeParameters2);
    }

    public static A v(C0834y yVar) {
        C0816f fVar;
        if (!(yVar instanceof C0816f)) {
            C0822l g = yVar.g();
            if (g instanceof C0816f) {
                fVar = (C0816f) g;
            } else {
                fVar = null;
            }
            if (fVar == null) {
                return A.FINAL;
            }
            if (!(yVar instanceof C0814d)) {
                return A.FINAL;
            }
            C0814d dVar = (C0814d) yVar;
            Collection h5 = dVar.h();
            j.d(h5, "getOverriddenDescriptors(...)");
            if (!h5.isEmpty() && fVar.k() != A.FINAL) {
                return A.OPEN;
            }
            if (fVar.b() != C0817g.INTERFACE || j.a(dVar.getVisibility(), C0827q.f3675a)) {
                return A.FINAL;
            }
            A k = dVar.k();
            A a7 = A.ABSTRACT;
            if (k == a7) {
                return a7;
            }
            return A.OPEN;
        } else if (((C0816f) yVar).b() == C0817g.INTERFACE) {
            return A.ABSTRACT;
        } else {
            return A.FINAL;
        }
    }

    public final void A(C0820j jVar, StringBuilder sb2) {
        List j2 = jVar.j();
        j.d(j2, "getDeclaredTypeParameters(...)");
        List parameters = jVar.q().getParameters();
        j.d(parameters, "getParameters(...)");
        if (u() && jVar.s() && parameters.size() > j2.size()) {
            sb2.append(" /*captured type parameters: ");
            c0(sb2, parameters.subList(j2.size(), parameters.size()));
            sb2.append("*/");
        }
    }

    public final String B(C1327g gVar) {
        C1288o oVar = this.f5084a;
        b bVar = (b) oVar.v.f(oVar, C1288o.Y[20]);
        if (bVar != null) {
            return (String) bVar.invoke(gVar);
        }
        if (gVar instanceof C1322b) {
            ArrayList arrayList = new ArrayList();
            for (C1327g B : (Iterable) ((C1322b) gVar).f5158a) {
                String B9 = B(B);
                if (B9 != null) {
                    arrayList.add(B9);
                }
            }
            return C1194l.R0(arrayList, ArcCommonLog.TAG_COMMA, "{", "}", (b) null, 56);
        } else if (gVar instanceof C1321a) {
            return n.H0(x((Re.b) ((C1321a) gVar).f5158a, (Re.d) null), Log.TAG_SEPARATOR);
        } else {
            if (!(gVar instanceof C1341u)) {
                return gVar.toString();
            }
            C1340t tVar = (C1340t) ((C1341u) gVar).f5158a;
            if (tVar instanceof C1338r) {
                return ((C1338r) tVar).f5162a + "::class";
            } else if (tVar instanceof C1339s) {
                C1326f fVar = ((C1339s) tVar).f5163a;
                String b5 = fVar.f5157a.a().b();
                int i2 = fVar.b;
                for (int i7 = 0; i7 < i2; i7++) {
                    b5 = C0086a.h('>', "kotlin.Array<", b5);
                }
                return C0212a.A(b5, "::class");
            } else {
                throw new RuntimeException();
            }
        }
    }

    public final void C(StringBuilder sb2, List list) {
        if (!list.isEmpty()) {
            sb2.append("context(");
            Iterator it = list.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                int i7 = i2 + 1;
                u uVar = (u) it.next();
                y(sb2, uVar, Re.d.RECEIVER);
                C0774x type = uVar.getType();
                j.d(type, "getType(...)");
                sb2.append(G(type));
                if (i2 == C1195m.p0(list)) {
                    sb2.append(") ");
                } else {
                    sb2.append(ArcCommonLog.TAG_COMMA);
                }
                i2 = i7;
            }
        }
    }

    public final void D(StringBuilder sb2, B b5) {
        C0820j jVar;
        y(sb2, b5, (Re.d) null);
        if (C0754c.k(b5)) {
            boolean z = b5 instanceof i;
            C1288o oVar = this.f5084a;
            if (!z || !((i) b5).g.b() || !((Boolean) oVar.V.f(oVar, C1288o.Y[47])).booleanValue()) {
                if (!z || ((Boolean) oVar.f5098X.f(oVar, C1288o.Y[49])).booleanValue()) {
                    sb2.append(b5.s0().toString());
                } else {
                    sb2.append(((i) b5).k);
                }
                sb2.append(Z(b5.e0()));
            } else {
                l lVar = l.f3482a;
                if (z) {
                    boolean b8 = ((i) b5).g.b();
                }
                M s0 = b5.s0();
                j.c(s0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.error.ErrorTypeConstructor");
                sb2.append(E(((Jf.j) s0).b[0]));
            }
        } else {
            M s02 = b5.s0();
            C0819i g = b5.s0().g();
            if (g instanceof C0820j) {
                jVar = (C0820j) g;
            } else {
                jVar = null;
            }
            A0.l a7 = C0833x.a(b5, jVar, 0);
            if (a7 == null) {
                sb2.append(a0(s02));
                sb2.append(Z(b5.e0()));
            } else {
                U(sb2, a7);
            }
        }
        if (b5.u0()) {
            sb2.append("?");
        }
        if (b5 instanceof C0764m) {
            sb2.append(" & Any");
        }
    }

    public final String E(String str) {
        int i2 = C1282i.f5082a[s().ordinal()];
        if (i2 == 1) {
            return str;
        }
        if (i2 == 2) {
            return C0212a.m("<font color=red><b>", str, "</b></font>");
        }
        throw new RuntimeException();
    }

    public final String F(String str, String str2, Ne.i iVar) {
        j.e(str, "lowerRendered");
        j.e(str2, "upperRendered");
        if (!C0068a.b0(str, str2)) {
            C1276c p6 = p();
            C0816f i2 = iVar.i(p.f3552C);
            if (i2 != null) {
                String Q02 = n.Q0(p6.a(i2, this), "Collection");
                String U = C0068a.U(str, Q02.concat("Mutable"), str2, Q02, Q02.concat("(Mutable)"));
                if (U != null) {
                    return U;
                }
                String U8 = C0068a.U(str, Q02.concat("MutableMap.MutableEntry"), str2, Q02.concat("Map.Entry"), Q02.concat("(Mutable)Map.(Mutable)Entry"));
                if (U8 != null) {
                    return U8;
                }
                String Q03 = n.Q0(p().a(iVar.j("Array"), this), "Array");
                String U10 = C0068a.U(str, Q03.concat(o("Array<")), str2, Q03.concat(o("Array<out ")), Q03.concat(o("Array<(out) ")));
                if (U10 != null) {
                    return U10;
                }
                return "(" + str + ".." + str2 + ')';
            }
            Ne.i.a(35);
            throw null;
        } else if (v.t0(str2, "(")) {
            return C0212a.m("(", str, ")!");
        } else {
            return str.concat("!");
        }
    }

    public final String G(C0774x xVar) {
        String Y = Y(xVar);
        if ((!j0(xVar) || a0.e(xVar)) && !(xVar instanceof C0764m)) {
            return Y;
        }
        return C0086a.h(')', "(", Y);
    }

    public final void H(Y y, StringBuilder sb2) {
        C1327g d0;
        String B;
        C1288o oVar = this.f5084a;
        if (((Boolean) oVar.u.f(oVar, C1288o.Y[19])).booleanValue() && (d0 = y.d0()) != null && (B = B(d0)) != null) {
            sb2.append(" = ");
            sb2.append(o(B));
        }
    }

    public final String I(String str) {
        int i2 = C1282i.f5082a[s().ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                C1288o oVar = this.f5084a;
                if (!((Boolean) oVar.f5097W.f(oVar, C1288o.Y[48])).booleanValue()) {
                    return C0212a.m("<b>", str, "</b>");
                }
            } else {
                throw new RuntimeException();
            }
        }
        return str;
    }

    public final void J(C0814d dVar, StringBuilder sb2) {
        if (q().contains(C1285l.MEMBER_KIND) && u() && dVar.b() != C0813c.DECLARATION) {
            sb2.append("/*");
            sb2.append(B1.a.U(dVar.b().name()));
            sb2.append("*/ ");
        }
    }

    public final void K(C0834y yVar, StringBuilder sb2) {
        boolean z;
        N(sb2, yVar.isExternal(), "external");
        boolean z3 = false;
        if (!q().contains(C1285l.EXPECT) || !yVar.b0()) {
            z = false;
        } else {
            z = true;
        }
        N(sb2, z, "expect");
        if (q().contains(C1285l.ACTUAL) && yVar.Q()) {
            z3 = true;
        }
        N(sb2, z3, "actual");
    }

    public final void L(A a7, StringBuilder sb2, A a10) {
        C1288o oVar = this.f5084a;
        if (((Boolean) oVar.f5105p.f(oVar, C1288o.Y[14])).booleanValue() || a7 != a10) {
            N(sb2, q().contains(C1285l.MODALITY), B1.a.U(a7.name()));
        }
    }

    public final void M(C0814d dVar, StringBuilder sb2) {
        if (!C1301e.s(dVar) || dVar.k() != A.FINAL) {
            C1288o oVar = this.f5084a;
            if (((C1291r) oVar.B.f(oVar, C1288o.Y[26])) != C1291r.RENDER_OVERRIDE || dVar.k() != A.OPEN || dVar.h().isEmpty()) {
                A k = dVar.k();
                j.d(k, "getModality(...)");
                L(k, sb2, v(dVar));
            }
        }
    }

    public final void N(StringBuilder sb2, boolean z, String str) {
        if (z) {
            sb2.append(I(str));
            sb2.append(" ");
        }
    }

    public final String O(C1240g gVar, boolean z) {
        String o2 = o(C0068a.S(gVar));
        C1288o oVar = this.f5084a;
        if (!((Boolean) oVar.f5097W.f(oVar, C1288o.Y[48])).booleanValue() || s() != w.HTML || !z) {
            return o2;
        }
        return C0212a.m("<b>", o2, "</b>");
    }

    public final void P(C0822l lVar, StringBuilder sb2, boolean z) {
        C1240g name = lVar.getName();
        j.d(name, "getName(...)");
        sb2.append(O(name, z));
    }

    public final void Q(StringBuilder sb2, C0774x xVar) {
        C0752a aVar;
        c0 x02 = xVar.x0();
        if (x02 instanceof C0752a) {
            aVar = (C0752a) x02;
        } else {
            aVar = null;
        }
        if (aVar != null) {
            B b5 = aVar.f;
            B b8 = aVar.e;
            C1288o oVar = this.f5084a;
            C1287n nVar = oVar.R;
            t[] tVarArr = C1288o.Y;
            if (((Boolean) nVar.f(oVar, tVarArr[42])).booleanValue()) {
                R(sb2, b8);
                if (((Boolean) oVar.S.f(oVar, tVarArr[43])).booleanValue()) {
                    w s = s();
                    w wVar = w.HTML;
                    if (s == wVar) {
                        sb2.append("<font color=\"808080\"><i>");
                    }
                    sb2.append(" /* ");
                    sb2.append("from: ");
                    R(sb2, b5);
                    sb2.append(" */");
                    if (s() == wVar) {
                        sb2.append("</i></font>");
                        return;
                    }
                    return;
                }
                return;
            }
            R(sb2, b5);
            if (((Boolean) oVar.Q.f(oVar, tVarArr[41])).booleanValue()) {
                w s5 = s();
                w wVar2 = w.HTML;
                if (s5 == wVar2) {
                    sb2.append("<font color=\"808080\"><i>");
                }
                sb2.append(" /* ");
                sb2.append("= ");
                R(sb2, b8);
                sb2.append(" */");
                if (s() == wVar2) {
                    sb2.append("</i></font>");
                    return;
                }
                return;
            }
            return;
        }
        R(sb2, xVar);
    }

    public final void R(StringBuilder sb2, C0774x xVar) {
        boolean z;
        boolean z3;
        String str;
        C1240g gVar;
        boolean z7;
        StringBuilder sb3 = sb2;
        C0774x xVar2 = xVar;
        C1288o oVar = this.f5084a;
        if ((xVar2 instanceof C0776z) && oVar.n()) {
            Gf.i iVar = ((C0776z) xVar2).g;
            if (iVar.f == k.NOT_COMPUTED || iVar.f == k.COMPUTING) {
                sb3.append("<Not computed yet>");
                return;
            }
        }
        c0 x02 = xVar2.x0();
        if (x02 instanceof C0768q) {
            sb3.append(((C0768q) x02).C0(this, this));
        } else if (x02 instanceof B) {
            B b5 = (B) x02;
            if (b5.equals(a0.b) || b5.s0() == a0.f3439a.e) {
                sb3.append("???");
                return;
            }
            M s0 = b5.s0();
            if (!(s0 instanceof Jf.j) || ((Jf.j) s0).f3480a != Jf.k.UNINFERRED_TYPE_VARIABLE) {
                if (C0754c.k(b5)) {
                    D(sb3, b5);
                } else if (j0(b5)) {
                    int length = sb3.length();
                    ((C1283j) this.b.getValue()).y(sb3, b5, (Re.d) null);
                    if (sb3.length() != length) {
                        z = true;
                    } else {
                        z = false;
                    }
                    C0774x G5 = Gd.a.G(b5);
                    List v = Gd.a.v(b5);
                    boolean O4 = Gd.a.O(b5);
                    boolean u02 = b5.u0();
                    if (u02 || (z && G5 != null)) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z3) {
                        if (O4) {
                            sb3.insert(length, '(');
                        } else {
                            if (z) {
                                B1.a.I(n.D0(sb3));
                                if (sb3.charAt(n.x0(sb3) - 1) != ')') {
                                    sb3.insert(n.x0(sb3), "()");
                                }
                            }
                            sb3.append("(");
                        }
                    }
                    if (!v.isEmpty()) {
                        sb3.append("context(");
                        for (C0774x Q : v.subList(0, C1195m.p0(v))) {
                            Q(sb3, Q);
                            sb3.append(ArcCommonLog.TAG_COMMA);
                        }
                        Q(sb3, (C0774x) C1194l.T0(v));
                        sb3.append(") ");
                    }
                    N(sb3, O4, "suspend");
                    if (G5 != null) {
                        if ((!j0(G5) || G5.u0()) && !Gd.a.O(G5) && G5.getAnnotations().isEmpty() && !(G5 instanceof C0764m)) {
                            z7 = false;
                        } else {
                            z7 = true;
                        }
                        if (z7) {
                            sb3.append("(");
                        }
                        Q(sb3, G5);
                        if (z7) {
                            sb3.append(")");
                        }
                        sb3.append(".");
                    }
                    sb3.append("(");
                    if (!Gd.a.K(b5) || b5.getAnnotations().m(p.f3572p) == null || b5.e0().size() > 1) {
                        int i2 = 0;
                        for (P p6 : Gd.a.I(b5)) {
                            int i7 = i2 + 1;
                            if (i2 > 0) {
                                sb3.append(ArcCommonLog.TAG_COMMA);
                            }
                            if (((Boolean) oVar.U.f(oVar, C1288o.Y[45])).booleanValue()) {
                                C0774x b8 = p6.b();
                                j.d(b8, "getType(...)");
                                gVar = Gd.a.s(b8);
                            } else {
                                gVar = null;
                            }
                            if (gVar != null) {
                                sb3.append(O(gVar, false));
                                sb3.append(": ");
                            }
                            j.e(p6, "typeProjection");
                            StringBuilder sb4 = new StringBuilder();
                            C1194l.Q0(C0246a.e0(p6), sb4, ArcCommonLog.TAG_COMMA, (String) null, (String) null, new C1280g(this, 0), 60);
                            String sb5 = sb4.toString();
                            j.d(sb5, "toString(...)");
                            sb3.append(sb5);
                            i2 = i7;
                        }
                    } else {
                        sb3.append("???");
                    }
                    sb3.append(") ");
                    int i8 = C1282i.f5082a[s().ordinal()];
                    if (i8 == 1) {
                        str = o("->");
                    } else if (i8 == 2) {
                        str = "&rarr;";
                    } else {
                        throw new RuntimeException();
                    }
                    sb3.append(str);
                    sb3.append(" ");
                    Gd.a.K(b5);
                    C0774x b10 = ((P) C1194l.T0(b5.e0())).b();
                    j.d(b10, "getType(...)");
                    Q(sb3, b10);
                    if (z3) {
                        sb3.append(")");
                    }
                    if (u02) {
                        sb3.append("?");
                    }
                } else {
                    D(sb3, b5);
                }
            } else if (((Boolean) oVar.t.f(oVar, C1288o.Y[18])).booleanValue()) {
                M s02 = b5.s0();
                j.c(s02, "null cannot be cast to non-null type org.jetbrains.kotlin.types.error.ErrorTypeConstructor");
                sb3.append(E(((Jf.j) s02).b[0]));
            } else {
                sb3.append("???");
            }
        } else {
            throw new RuntimeException();
        }
    }

    public final void S(C0814d dVar, StringBuilder sb2) {
        if (q().contains(C1285l.OVERRIDE) && !dVar.h().isEmpty()) {
            C1288o oVar = this.f5084a;
            if (((C1291r) oVar.B.f(oVar, C1288o.Y[26])) != C1291r.RENDER_OPEN) {
                N(sb2, true, "override");
                if (u()) {
                    sb2.append("/*");
                    sb2.append(dVar.h().size());
                    sb2.append("*/ ");
                }
            }
        }
    }

    public final void T(C1236c cVar, String str, StringBuilder sb2) {
        sb2.append(I(str));
        C1238e i2 = cVar.i();
        j.d(i2, "toUnsafe(...)");
        String o2 = o(C0068a.T(i2.e()));
        if (o2.length() > 0) {
            sb2.append(" ");
            sb2.append(o2);
        }
    }

    public final void U(StringBuilder sb2, A0.l lVar) {
        A0.l lVar2 = (A0.l) lVar.f;
        C0820j jVar = (C0820j) lVar.e;
        if (lVar2 != null) {
            U(sb2, lVar2);
            sb2.append('.');
            C1240g name = jVar.getName();
            j.d(name, "getName(...)");
            sb2.append(O(name, false));
        } else {
            M q = jVar.q();
            j.d(q, "getTypeConstructor(...)");
            sb2.append(a0(q));
        }
        sb2.append(Z((List) lVar.g));
    }

    public final void V(C0814d dVar, StringBuilder sb2) {
        u H5 = dVar.H();
        if (H5 != null) {
            y(sb2, H5, Re.d.RECEIVER);
            C0774x type = H5.getType();
            j.d(type, "getType(...)");
            sb2.append(G(type));
            sb2.append(".");
        }
    }

    public final void W(C0814d dVar, StringBuilder sb2) {
        u H5;
        C1288o oVar = this.f5084a;
        if (((Boolean) oVar.f5087F.f(oVar, C1288o.Y[30])).booleanValue() && (H5 = dVar.H()) != null) {
            sb2.append(" on ");
            C0774x type = H5.getType();
            j.d(type, "getType(...)");
            sb2.append(Y(type));
        }
    }

    public final String Y(C0774x xVar) {
        j.e(xVar, "type");
        StringBuilder sb2 = new StringBuilder();
        C1288o oVar = this.f5084a;
        Q(sb2, (C0774x) ((b) oVar.y.f(oVar, C1288o.Y[23])).invoke(xVar));
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public final String Z(List list) {
        j.e(list, "typeArguments");
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(o("<"));
        C1194l.Q0(list, sb2, ArcCommonLog.TAG_COMMA, (String) null, (String) null, new C1280g(this, 0), 60);
        sb2.append(o(">"));
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public final void a() {
        this.f5084a.a();
    }

    public final String a0(M m) {
        j.e(m, "typeConstructor");
        C0819i g = m.g();
        if ((g instanceof V) || (g instanceof C0816f) || (g instanceof U)) {
            j.e(g, "klass");
            if (l.f(g)) {
                return g.q().toString();
            }
            return p().a(g, this);
        } else if (g != null) {
            throw new IllegalStateException(("Unexpected classifier: " + g.getClass()).toString());
        } else if (m instanceof C0773w) {
            return ((C0773w) m).c(C1277d.f5078p);
        } else {
            return m.toString();
        }
    }

    public final void b() {
        this.f5084a.b();
    }

    public final void b0(V v, StringBuilder sb2, boolean z) {
        boolean z3;
        if (z) {
            sb2.append(o("<"));
        }
        if (u()) {
            sb2.append("/*");
            sb2.append(v.getIndex());
            sb2.append("*/ ");
        }
        N(sb2, v.r(), "reified");
        String b5 = v.t().b();
        boolean z7 = true;
        if (b5.length() > 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        N(sb2, z3, b5);
        y(sb2, v, (Re.d) null);
        P(v, sb2, z);
        int size = v.getUpperBounds().size();
        if ((size > 1 && !z) || size == 1) {
            C0774x xVar = (C0774x) v.getUpperBounds().iterator().next();
            if (xVar == null) {
                Ne.i.a(142);
                throw null;
            } else if (!Ne.i.x(xVar) || !xVar.u0()) {
                sb2.append(" : ");
                sb2.append(Y(xVar));
            }
        } else if (z) {
            for (C0774x xVar2 : v.getUpperBounds()) {
                if (xVar2 == null) {
                    Ne.i.a(142);
                    throw null;
                } else if (!Ne.i.x(xVar2) || !xVar2.u0()) {
                    if (z7) {
                        sb2.append(" : ");
                    } else {
                        sb2.append(" & ");
                    }
                    sb2.append(Y(xVar2));
                    z7 = false;
                }
            }
        }
        if (z) {
            sb2.append(o(">"));
        }
    }

    public final void c(C1276c cVar) {
        this.f5084a.c(cVar);
    }

    public final void c0(StringBuilder sb2, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            b0((V) it.next(), sb2, false);
            if (it.hasNext()) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
        }
    }

    public final void d() {
        this.f5084a.d();
    }

    public final void d0(List list, StringBuilder sb2, boolean z) {
        C1288o oVar = this.f5084a;
        if (!((Boolean) oVar.w.f(oVar, C1288o.Y[21])).booleanValue() && !list.isEmpty()) {
            sb2.append(o("<"));
            c0(sb2, list);
            sb2.append(o(">"));
            if (z) {
                sb2.append(" ");
            }
        }
    }

    public final void e(C1292s sVar) {
        j.e(sVar, "<set-?>");
        this.f5084a.e(sVar);
    }

    public final void e0(Y y, StringBuilder sb2, boolean z) {
        String str;
        if (z || !(y instanceof Q)) {
            if (y.G()) {
                str = "var";
            } else {
                str = "val";
            }
            sb2.append(I(str));
            sb2.append(" ");
        }
    }

    public final void f(w wVar) {
        j.e(wVar, "<set-?>");
        this.f5084a.f(wVar);
    }

    /* JADX WARNING: type inference failed for: r2v13, types: [Qe.b] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void f0(Te.Q r11, boolean r12, java.lang.StringBuilder r13, boolean r14) {
        /*
            r10 = this;
            if (r14 == 0) goto L_0x0010
            java.lang.String r0 = "value-parameter"
            java.lang.String r0 = r10.I(r0)
            r13.append(r0)
            java.lang.String r0 = " "
            r13.append(r0)
        L_0x0010:
            boolean r0 = r10.u()
            if (r0 == 0) goto L_0x0025
            java.lang.String r0 = "/*"
            r13.append(r0)
            int r0 = r11.f3770j
            r13.append(r0)
            java.lang.String r0 = "*/ "
            r13.append(r0)
        L_0x0025:
            r0 = 0
            r10.y(r13, r11, r0)
            boolean r1 = r11.l
            java.lang.String r2 = "crossinline"
            r10.N(r13, r1, r2)
            boolean r1 = r11.m
            java.lang.String r2 = "noinline"
            r10.N(r13, r1, r2)
            sf.o r1 = r10.f5084a
            sf.n r2 = r1.r
            He.t[] r3 = sf.C1288o.Y
            r4 = 16
            r4 = r3[r4]
            java.lang.Object r2 = r2.f(r1, r4)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L_0x0062
            Qe.b r2 = r11.g()
            boolean r6 = r2 instanceof Te.C0848i
            if (r6 == 0) goto L_0x005a
            r0 = r2
            Te.i r0 = (Te.C0848i) r0
        L_0x005a:
            if (r0 == 0) goto L_0x0062
            boolean r0 = r0.f3779H
            if (r0 != r5) goto L_0x0062
            r0 = r5
            goto L_0x0063
        L_0x0062:
            r0 = r4
        L_0x0063:
            if (r0 == 0) goto L_0x007a
            sf.n r2 = r1.s
            r6 = 17
            r6 = r3[r6]
            java.lang.Object r2 = r2.f(r1, r6)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            java.lang.String r6 = "actual"
            r10.N(r13, r2, r6)
        L_0x007a:
            r2 = r11
            Te.S r2 = (Te.S) r2
            Hf.x r2 = r2.getType()
            java.lang.String r6 = "getType(...)"
            kotlin.jvm.internal.j.d(r2, r6)
            Hf.x r6 = r11.n
            if (r6 != 0) goto L_0x008c
            r7 = r2
            goto L_0x008d
        L_0x008c:
            r7 = r6
        L_0x008d:
            if (r6 == 0) goto L_0x0091
            r8 = r5
            goto L_0x0092
        L_0x0091:
            r8 = r4
        L_0x0092:
            java.lang.String r9 = "vararg"
            r10.N(r13, r8, r9)
            if (r0 != 0) goto L_0x00a1
            if (r14 == 0) goto L_0x00a4
            boolean r8 = r10.r()
            if (r8 != 0) goto L_0x00a4
        L_0x00a1:
            r10.e0(r11, r13, r0)
        L_0x00a4:
            if (r12 == 0) goto L_0x00ae
            r10.P(r11, r13, r14)
            java.lang.String r12 = ": "
            r13.append(r12)
        L_0x00ae:
            java.lang.String r12 = r10.Y(r7)
            r13.append(r12)
            r10.H(r11, r13)
            boolean r12 = r10.u()
            if (r12 == 0) goto L_0x00d1
            if (r6 == 0) goto L_0x00d1
            java.lang.String r12 = " /*"
            r13.append(r12)
            java.lang.String r10 = r10.Y(r2)
            r13.append(r10)
            java.lang.String r10 = "*/"
            r13.append(r10)
        L_0x00d1:
            sf.n r10 = r1.z
            r12 = 24
            r14 = r3[r12]
            java.lang.Object r10 = r10.f(r1, r14)
            Ae.b r10 = (Ae.b) r10
            if (r10 == 0) goto L_0x00f1
            boolean r10 = r1.n()
            if (r10 == 0) goto L_0x00ea
            boolean r10 = r11.F0()
            goto L_0x00ee
        L_0x00ea:
            boolean r10 = xf.C1353d.a(r11)
        L_0x00ee:
            if (r10 == 0) goto L_0x00f1
            r4 = r5
        L_0x00f1:
            if (r4 == 0) goto L_0x0117
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r14 = " = "
            r10.<init>(r14)
            sf.n r14 = r1.z
            r12 = r3[r12]
            java.lang.Object r12 = r14.f(r1, r12)
            Ae.b r12 = (Ae.b) r12
            kotlin.jvm.internal.j.b(r12)
            java.lang.Object r11 = r12.invoke(r11)
            java.lang.String r11 = (java.lang.String) r11
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r13.append(r10)
        L_0x0117:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: sf.C1283j.f0(Te.Q, boolean, java.lang.StringBuilder, boolean):void");
    }

    public final void g() {
        this.f5084a.g();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        if (r9 == false) goto L_0x002c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void g0(java.util.Collection r8, boolean r9, java.lang.StringBuilder r10) {
        /*
            r7 = this;
            sf.o r0 = r7.f5084a
            sf.n r1 = r0.E
            He.t[] r2 = sf.C1288o.Y
            r3 = 29
            r2 = r2[r3]
            java.lang.Object r0 = r1.f(r0, r2)
            sf.s r0 = (sf.C1292s) r0
            int[] r1 = sf.C1282i.b
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L_0x002c
            r3 = 2
            if (r0 == r3) goto L_0x002a
            r9 = 3
            if (r0 != r9) goto L_0x0024
        L_0x0022:
            r9 = r1
            goto L_0x002d
        L_0x0024:
            Dd.a r7 = new Dd.a
            r7.<init>()
            throw r7
        L_0x002a:
            if (r9 != 0) goto L_0x0022
        L_0x002c:
            r9 = r2
        L_0x002d:
            int r0 = r8.size()
            sf.f r3 = r7.t()
            r3.getClass()
            java.lang.String r3 = "builder"
            kotlin.jvm.internal.j.e(r10, r3)
            java.lang.String r3 = "("
            r10.append(r3)
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r8 = r8.iterator()
            r3 = r1
        L_0x0049:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L_0x0078
            int r4 = r3 + 1
            java.lang.Object r5 = r8.next()
            Te.Q r5 = (Te.Q) r5
            sf.f r6 = r7.t()
            r6.getClass()
            java.lang.String r6 = "parameter"
            kotlin.jvm.internal.j.e(r5, r6)
            r7.f0(r5, r9, r10, r1)
            sf.f r5 = r7.t()
            r5.getClass()
            int r5 = r0 + -1
            if (r3 == r5) goto L_0x0076
            java.lang.String r3 = ", "
            r10.append(r3)
        L_0x0076:
            r3 = r4
            goto L_0x0049
        L_0x0078:
            sf.f r7 = r7.t()
            r7.getClass()
            java.lang.String r7 = ")"
            r10.append(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: sf.C1283j.g0(java.util.Collection, boolean, java.lang.StringBuilder):void");
    }

    public final Set h() {
        return this.f5084a.h();
    }

    public final boolean h0(C0826p pVar, StringBuilder sb2) {
        if (!q().contains(C1285l.VISIBILITY)) {
            return false;
        }
        C1288o oVar = this.f5084a;
        C1287n nVar = oVar.n;
        t[] tVarArr = C1288o.Y;
        if (((Boolean) nVar.f(oVar, tVarArr[12])).booleanValue()) {
            pVar = C0827q.f(pVar.f3674a.c());
        }
        if (!((Boolean) oVar.f5104o.f(oVar, tVarArr[13])).booleanValue() && j.a(pVar, C0827q.f3679j)) {
            return false;
        }
        sb2.append(I(pVar.f3674a.b()));
        sb2.append(" ");
        return true;
    }

    public final void i() {
        this.f5084a.i();
    }

    public final void i0(StringBuilder sb2, List list) {
        C1288o oVar = this.f5084a;
        if (!((Boolean) oVar.w.f(oVar, C1288o.Y[21])).booleanValue()) {
            ArrayList arrayList = new ArrayList(0);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                V v = (V) it.next();
                List upperBounds = v.getUpperBounds();
                j.d(upperBounds, "getUpperBounds(...)");
                for (C0774x xVar : C1194l.I0(upperBounds)) {
                    StringBuilder sb3 = new StringBuilder();
                    C1240g name = v.getName();
                    j.d(name, "getName(...)");
                    sb3.append(O(name, false));
                    sb3.append(" : ");
                    j.b(xVar);
                    sb3.append(Y(xVar));
                    arrayList.add(sb3.toString());
                }
            }
            if (!arrayList.isEmpty()) {
                sb2.append(" ");
                sb2.append(I("where"));
                sb2.append(" ");
                C1194l.Q0(arrayList, sb2, ArcCommonLog.TAG_COMMA, (String) null, (String) null, (b) null, 124);
            }
        }
    }

    public final void j() {
        this.f5084a.j();
    }

    public final void k(Set set) {
        j.e(set, "<set-?>");
        this.f5084a.k(set);
    }

    public final void l(LinkedHashSet linkedHashSet) {
        this.f5084a.l(linkedHashSet);
    }

    public final void m() {
        this.f5084a.m();
    }

    public final String o(String str) {
        return s().a(str);
    }

    public final C1276c p() {
        C1288o oVar = this.f5084a;
        return (C1276c) oVar.b.f(oVar, C1288o.Y[0]);
    }

    public final Set q() {
        C1288o oVar = this.f5084a;
        return (Set) oVar.e.f(oVar, C1288o.Y[3]);
    }

    public final boolean r() {
        C1288o oVar = this.f5084a;
        return ((Boolean) oVar.f.f(oVar, C1288o.Y[4])).booleanValue();
    }

    public final w s() {
        C1288o oVar = this.f5084a;
        return (w) oVar.D.f(oVar, C1288o.Y[28]);
    }

    public final C1279f t() {
        C1288o oVar = this.f5084a;
        return (C1279f) oVar.f5086C.f(oVar, C1288o.Y[27]);
    }

    public final boolean u() {
        C1288o oVar = this.f5084a;
        return ((Boolean) oVar.f5103j.f(oVar, C1288o.Y[8])).booleanValue();
    }

    public final String w(C0822l lVar) {
        C0822l g;
        String str;
        String str2;
        j.e(lVar, "declarationDescriptor");
        StringBuilder sb2 = new StringBuilder();
        lVar.v(new e((Object) this), sb2);
        C1288o oVar = this.f5084a;
        C1287n nVar = oVar.f5100c;
        t[] tVarArr = C1288o.Y;
        if (((Boolean) nVar.f(oVar, tVarArr[1])).booleanValue() && !(lVar instanceof H) && !(lVar instanceof L) && (g = lVar.g()) != null && !(g instanceof C)) {
            sb2.append(" ");
            int i2 = C1282i.f5082a[s().ordinal()];
            if (i2 == 1) {
                str = "defined in";
            } else if (i2 == 2) {
                str = "<i>defined in</i>";
            } else {
                throw new RuntimeException();
            }
            sb2.append(str);
            sb2.append(" ");
            C1238e g3 = C1301e.g(g);
            j.d(g3, "getFqName(...)");
            if (g3.f5037a.isEmpty()) {
                str2 = "root package";
            } else {
                str2 = o(C0068a.T(g3.e()));
            }
            sb2.append(str2);
            if (((Boolean) oVar.d.f(oVar, tVarArr[2])).booleanValue() && (g instanceof H) && (lVar instanceof C0823m)) {
                ((C0823m) lVar).getSource().getClass();
            }
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: ne.t} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: ne.t} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String x(Re.b r12, Re.d r13) {
        /*
            r11 = this;
            sf.o r0 = r11.f5084a
            sf.n r1 = r0.f5094N
            java.lang.String r2 = "annotation"
            kotlin.jvm.internal.j.e(r12, r2)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r2 = 64
            r4.append(r2)
            if (r13 == 0) goto L_0x002d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r13 = r13.a()
            r2.append(r13)
            r13 = 58
            r2.append(r13)
            java.lang.String r13 = r2.toString()
            r4.append(r13)
        L_0x002d:
            Hf.x r13 = r12.getType()
            java.lang.String r2 = r11.Y(r13)
            r4.append(r2)
            He.t[] r2 = sf.C1288o.Y
            r3 = 38
            r5 = r2[r3]
            java.lang.Object r5 = r1.f(r0, r5)
            sf.a r5 = (sf.C1274a) r5
            boolean r5 = r5.a()
            if (r5 == 0) goto L_0x019a
            java.util.Map r5 = r12.a()
            sf.n r6 = r0.f5090I
            r7 = 33
            r2 = r2[r7]
            java.lang.Object r2 = r6.f(r0, r2)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            r6 = 0
            if (r2 == 0) goto L_0x0066
            Qe.f r12 = xf.C1353d.d(r12)
            goto L_0x0067
        L_0x0066:
            r12 = r6
        L_0x0067:
            r2 = 10
            if (r12 == 0) goto L_0x00bf
            Te.i r12 = r12.y()
            if (r12 == 0) goto L_0x00bf
            Te.t r12 = (Te.t) r12
            java.util.List r12 = r12.B()
            if (r12 == 0) goto L_0x00bf
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r12 = r12.iterator()
        L_0x0084:
            boolean r7 = r12.hasNext()
            if (r7 == 0) goto L_0x009b
            java.lang.Object r7 = r12.next()
            r8 = r7
            Te.Q r8 = (Te.Q) r8
            boolean r8 = r8.F0()
            if (r8 == 0) goto L_0x0084
            r6.add(r7)
            goto L_0x0084
        L_0x009b:
            java.util.ArrayList r12 = new java.util.ArrayList
            int r7 = ne.C1196n.w0(r6, r2)
            r12.<init>(r7)
            java.util.Iterator r6 = r6.iterator()
        L_0x00a8:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00be
            java.lang.Object r7 = r6.next()
            Te.Q r7 = (Te.Q) r7
            Te.m r7 = (Te.C0852m) r7
            qf.g r7 = r7.getName()
            r12.add(r7)
            goto L_0x00a8
        L_0x00be:
            r6 = r12
        L_0x00bf:
            if (r6 != 0) goto L_0x00c3
            ne.t r6 = ne.C1202t.d
        L_0x00c3:
            r12 = r6
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r12 = r12.iterator()
        L_0x00cf:
            boolean r8 = r12.hasNext()
            if (r8 == 0) goto L_0x00e6
            java.lang.Object r8 = r12.next()
            r9 = r8
            qf.g r9 = (qf.C1240g) r9
            boolean r9 = r5.containsKey(r9)
            if (r9 != 0) goto L_0x00cf
            r7.add(r8)
            goto L_0x00cf
        L_0x00e6:
            java.util.ArrayList r12 = new java.util.ArrayList
            int r8 = ne.C1196n.w0(r7, r2)
            r12.<init>(r8)
            java.util.Iterator r7 = r7.iterator()
        L_0x00f3:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0118
            java.lang.Object r8 = r7.next()
            qf.g r8 = (qf.C1240g) r8
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r8 = r8.b()
            r9.append(r8)
            java.lang.String r8 = " = ..."
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            r12.add(r8)
            goto L_0x00f3
        L_0x0118:
            java.util.Set r5 = r5.entrySet()
            java.util.ArrayList r7 = new java.util.ArrayList
            int r2 = ne.C1196n.w0(r5, r2)
            r7.<init>(r2)
            java.util.Iterator r2 = r5.iterator()
        L_0x0129:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x016a
            java.lang.Object r5 = r2.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r8 = r5.getKey()
            qf.g r8 = (qf.C1240g) r8
            java.lang.Object r5 = r5.getValue()
            vf.g r5 = (vf.C1327g) r5
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = r8.b()
            r9.append(r10)
            java.lang.String r10 = " = "
            r9.append(r10)
            boolean r8 = r6.contains(r8)
            if (r8 != 0) goto L_0x015d
            java.lang.String r5 = r11.B(r5)
            goto L_0x015f
        L_0x015d:
            java.lang.String r5 = "..."
        L_0x015f:
            r9.append(r5)
            java.lang.String r5 = r9.toString()
            r7.add(r5)
            goto L_0x0129
        L_0x016a:
            java.util.ArrayList r12 = ne.C1194l.X0(r7, r12)
            java.util.List r12 = ne.C1194l.f1(r12)
            He.t[] r2 = sf.C1288o.Y
            r2 = r2[r3]
            java.lang.Object r0 = r1.f(r0, r2)
            sf.a r0 = (sf.C1274a) r0
            boolean r0 = r0.b()
            if (r0 != 0) goto L_0x018b
            r0 = r12
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x019a
        L_0x018b:
            r3 = r12
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            r8 = 0
            r9 = 112(0x70, float:1.57E-43)
            java.lang.String r5 = ", "
            java.lang.String r6 = "("
            java.lang.String r7 = ")"
            ne.C1194l.Q0(r3, r4, r5, r6, r7, r8, r9)
        L_0x019a:
            boolean r11 = r11.u()
            if (r11 == 0) goto L_0x01b7
            boolean r11 = Hf.C0754c.k(r13)
            if (r11 != 0) goto L_0x01b2
            Hf.M r11 = r13.s0()
            Qe.i r11 = r11.g()
            boolean r11 = r11 instanceof Qe.G
            if (r11 == 0) goto L_0x01b7
        L_0x01b2:
            java.lang.String r11 = " /* annotation class not found */"
            r4.append(r11)
        L_0x01b7:
            java.lang.String r11 = r4.toString()
            java.lang.String r12 = "toString(...)"
            kotlin.jvm.internal.j.d(r11, r12)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: sf.C1283j.x(Re.b, Re.d):java.lang.String");
    }

    public final void y(StringBuilder sb2, Re.a aVar, Re.d dVar) {
        Set set;
        if (q().contains(C1285l.ANNOTATIONS)) {
            boolean z = aVar instanceof C0774x;
            C1288o oVar = this.f5084a;
            if (z) {
                set = oVar.h();
            } else {
                set = (Set) oVar.f5092K.f(oVar, C1288o.Y[35]);
            }
            b bVar = (b) oVar.f5093M.f(oVar, C1288o.Y[37]);
            for (Re.b bVar2 : aVar.getAnnotations()) {
                if (!C1194l.G0(set, bVar2.b()) && !j.a(bVar2.b(), p.r)) {
                    if (bVar == null || ((Boolean) bVar.invoke(bVar2)).booleanValue()) {
                        sb2.append(x(bVar2, dVar));
                        if (((Boolean) oVar.f5091J.f(oVar, C1288o.Y[34])).booleanValue()) {
                            sb2.append(10);
                        } else {
                            sb2.append(" ");
                        }
                    }
                }
            }
        }
    }
}
