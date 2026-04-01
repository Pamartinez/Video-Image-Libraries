package ge;

import B1.a;
import B2.o;
import Df.C0741g;
import Df.C0742h;
import E2.g;
import F2.C0042x;
import Fd.C0744a;
import He.F;
import Hf.B;
import Hf.C0754c;
import Hf.C0764m;
import Hf.C0768q;
import Hf.P;
import Hf.c0;
import Hf.d0;
import If.b;
import If.u;
import Jf.k;
import Jf.l;
import Kf.c;
import Kf.d;
import Kf.e;
import Kf.f;
import Kf.i;
import L2.m;
import P1.h;
import Qe.V;
import We.C0892d;
import android.util.SparseIntArray;
import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import ee.C0964A;
import ee.C0966C;
import ee.C0971d;
import ee.C0975h;
import ee.C0976i;
import ee.G;
import ee.H;
import ee.J;
import ee.M;
import ee.a0;
import h2.s;
import h2.t;
import hf.C1084f;
import hf.C1090l;
import hf.C1092n;
import hf.C1094p;
import i.C0212a;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import jf.C1107g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import lf.I;
import lf.K;
import lf.L;
import me.n;
import ne.C1194l;
import ne.C1196n;
import nf.C1209f;
import nf.C1210g;
import o1.C0246a;
import qf.C1235b;
import qf.C1240g;
import rf.C1252b;
import rf.C1260j;
import rf.C1264n;
import rf.C1265o;
import rf.Q;
import t1.C0279d;
import uf.C1316a;
import uf.C1317b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class W0 implements C0966C, C1056t, OnApplyWindowInsetsListener, C0742h, C1209f, b, P1.b {
    public final /* synthetic */ int d;
    public final Object e;
    public Object f;

    public /* synthetic */ W0(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.f = obj;
        this.e = obj2;
    }

    public static Integer x0(M m) {
        Object obj;
        G g = C1057t0.f4548I;
        int i2 = m.b;
        while (true) {
            i2--;
            if (i2 < 0) {
                obj = null;
                break;
            }
            byte[] bArr = g.b;
            H h5 = g.e;
            int i7 = i2 * 2;
            if (Arrays.equals(bArr, (byte[]) m.f4275a[i7])) {
                Object obj2 = m.f4275a[i7 + 1];
                if (obj2 instanceof byte[]) {
                    obj = h5.c(new String((byte[]) obj2, g.f166a));
                } else {
                    J j2 = (J) obj2;
                    j2.getClass();
                    obj = h5.c(new String(j2.a(), g.f166a));
                }
            }
        }
        String str = (String) obj;
        if (str == null) {
            return null;
        }
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public i A(V v) {
        j.e(v, "$receiver");
        d0 t = v.t();
        j.d(t, "getVariance(...)");
        return a.o(t);
    }

    public void A0(int i2, o oVar) {
        Iterator it = (Iterator) this.e;
        while (true) {
            Map.Entry entry = (Map.Entry) this.f;
            if (entry != null && ((C1265o) entry.getKey()).d < i2) {
                C1265o oVar2 = (C1265o) ((Map.Entry) this.f).getKey();
                Object value = ((Map.Entry) this.f).getValue();
                C1260j jVar = C1260j.f5065c;
                Q q = oVar2.e;
                int i7 = oVar2.d;
                if (oVar2.f) {
                    for (Object next : (List) value) {
                        if (q == Q.GROUP) {
                            oVar.x(i7, 3);
                            ((C1252b) next).d(oVar);
                            oVar.x(i7, 4);
                        } else {
                            oVar.x(i7, q.b());
                            C1260j.k(oVar, q, next);
                        }
                    }
                } else if (q == Q.GROUP) {
                    oVar.x(i7, 3);
                    ((C1252b) value).d(oVar);
                    oVar.x(i7, 4);
                } else {
                    oVar.x(i7, q.b());
                    C1260j.k(oVar, q, value);
                }
                if (it.hasNext()) {
                    this.f = (Map.Entry) it.next();
                } else {
                    this.f = null;
                }
            } else {
                return;
            }
        }
    }

    public boolean B(e eVar) {
        j.e(eVar, "<this>");
        return If.g.D(If.g.W(eVar));
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void B0(boolean r4, com.google.android.gms.common.api.Status r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.e
            java.util.Map r0 = (java.util.Map) r0
            monitor-enter(r0)
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x0084 }
            java.lang.Object r2 = r3.e     // Catch:{ all -> 0x0084 }
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ all -> 0x0084 }
            r1.<init>(r2)     // Catch:{ all -> 0x0084 }
            monitor-exit(r0)     // Catch:{ all -> 0x0084 }
            java.lang.Object r0 = r3.f
            r2 = r0
            java.util.Map r2 = (java.util.Map) r2
            monitor-enter(r2)
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x0081 }
            java.lang.Object r3 = r3.f     // Catch:{ all -> 0x0081 }
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ all -> 0x0081 }
            r0.<init>(r3)     // Catch:{ all -> 0x0081 }
            monitor-exit(r2)     // Catch:{ all -> 0x0081 }
            java.util.Set r3 = r1.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x0027:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L_0x004f
            java.lang.Object r1 = r3.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            if (r4 != 0) goto L_0x0042
            java.lang.Object r2 = r1.getValue()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L_0x0042
            goto L_0x0027
        L_0x0042:
            java.lang.Object r3 = r1.getKey()
            r3.getClass()
            java.lang.ClassCastException r3 = new java.lang.ClassCastException
            r3.<init>()
            throw r3
        L_0x004f:
            java.util.Set r3 = r0.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x0057:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0080
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            if (r4 != 0) goto L_0x0071
            java.lang.Object r1 = r0.getValue()
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0057
        L_0x0071:
            java.lang.Object r0 = r0.getKey()
            P1.c r0 = (P1.c) r0
            u1.d r1 = new u1.d
            r1.<init>(r5)
            r0.b(r1)
            goto L_0x0057
        L_0x0080:
            return
        L_0x0081:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0081 }
            throw r3
        L_0x0084:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0084 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.W0.B0(boolean, com.google.android.gms.common.api.Status):void");
    }

    public C0768q C(d dVar) {
        return If.g.g(dVar);
    }

    public If.a D(e eVar) {
        return If.g.U(this, eVar);
    }

    public P E(f fVar, int i2) {
        j.e(fVar, "<this>");
        if (fVar instanceof e) {
            return If.g.p((d) fVar, i2);
        }
        if (fVar instanceof Kf.a) {
            Object obj = ((Kf.a) fVar).get(i2);
            j.d(obj, "get(...)");
            return (P) obj;
        }
        throw new IllegalStateException(("unknown type argument list type: " + fVar + ArcCommonLog.TAG_COMMA + v.f4727a.b(fVar.getClass())).toString());
    }

    public boolean F(int i2) {
        return ((Boolean) y0(i2).f).booleanValue();
    }

    public boolean G(d dVar) {
        C0764m mVar;
        j.e(dVar, "<this>");
        B h5 = If.g.h(dVar);
        if (h5 != null) {
            mVar = If.g.f(h5);
        } else {
            mVar = null;
        }
        if (mVar != null) {
            return true;
        }
        return false;
    }

    public P H(e eVar, int i2) {
        j.e(eVar, "<this>");
        if (i2 < 0 || i2 >= If.g.c(eVar)) {
            return null;
        }
        return If.g.p(eVar, i2);
    }

    public B I(e eVar, boolean z) {
        return If.g.Z(eVar, z);
    }

    public boolean J(c cVar) {
        return If.g.J(cVar);
    }

    public Hf.M K(d dVar) {
        j.e(dVar, "<this>");
        B h5 = If.g.h(dVar);
        if (h5 == null) {
            h5 = o0(dVar);
        }
        return If.g.W(h5);
    }

    public P L(d dVar, int i2) {
        return If.g.p(dVar, i2);
    }

    public boolean M(e eVar) {
        c cVar;
        j.e(eVar, "<this>");
        B h5 = If.g.h(eVar);
        if (h5 != null) {
            cVar = If.g.e(this, h5);
        } else {
            cVar = null;
        }
        if (cVar != null) {
            return true;
        }
        return false;
    }

    public boolean N(c0 c0Var) {
        j.e(c0Var, "<this>");
        if (If.g.F(o0(c0Var)) != If.g.F(f(c0Var))) {
            return true;
        }
        return false;
    }

    public boolean O(V v, Kf.g gVar) {
        return If.g.v(v, gVar);
    }

    public Hf.G P(d dVar) {
        return If.g.i(dVar);
    }

    public boolean Q(Kf.g gVar) {
        return If.g.A(gVar);
    }

    public boolean R(Kf.g gVar) {
        return If.g.x(gVar);
    }

    public int S(f fVar) {
        j.e(fVar, "<this>");
        if (fVar instanceof e) {
            return If.g.c((d) fVar);
        }
        if (fVar instanceof Kf.a) {
            return ((Kf.a) fVar).size();
        }
        throw new IllegalStateException(("unknown type argument list type: " + fVar + ArcCommonLog.TAG_COMMA + v.f4727a.b(fVar.getClass())).toString());
    }

    public B T(C0764m mVar) {
        return If.g.Q(mVar);
    }

    public boolean U(e eVar) {
        j.e(eVar, "<this>");
        if (!If.g.G(K(eVar)) || If.g.H(eVar)) {
            return false;
        }
        return true;
    }

    public c0 V(P p6) {
        return If.g.r(p6);
    }

    public boolean W(e eVar, e eVar2) {
        return If.g.w(eVar, eVar2);
    }

    public String X(int i2) {
        n y0 = y0(i2);
        List list = (List) y0.d;
        String R02 = C1194l.R0((List) y0.e, ".", (String) null, (String) null, (Ae.b) null, 62);
        if (list.isEmpty()) {
            return R02;
        }
        return C1194l.R0(list, "/", (String) null, (String) null, (Ae.b) null, 62) + '/' + R02;
    }

    public d Y(d dVar) {
        return If.g.a0(this, dVar);
    }

    public e Z(e eVar) {
        B Q;
        j.e(eVar, "<this>");
        C0764m f5 = If.g.f(eVar);
        if (f5 == null || (Q = If.g.Q(f5)) == null) {
            return eVar;
        }
        return Q;
    }

    public void a(C0976i iVar) {
        C0971d dVar;
        C0971d t02;
        C1008a1 a1Var = (C1008a1) this.f;
        D0.e eVar = a1Var.d;
        E0 e02 = (E0) this.e;
        C0975h hVar = iVar.f4298a;
        if (hVar != C0975h.SHUTDOWN) {
            C0975h hVar2 = C0975h.TRANSIENT_FAILURE;
            if (hVar == hVar2 || hVar == C0975h.IDLE) {
                eVar.U();
            }
            if (a1Var.f == hVar2) {
                if (hVar != C0975h.CONNECTING) {
                    if (hVar == C0975h.IDLE) {
                        E0 e03 = a1Var.e;
                        if (e03 != null) {
                            e03.a();
                            return;
                        }
                        return;
                    }
                } else {
                    return;
                }
            }
            int i2 = X0.f4487a[hVar.ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    dVar = new Z0(C0964A.d);
                } else if (i2 == 3) {
                    t02 = new Z0(new C0964A(e02, a0.e, false));
                } else if (i2 == 4) {
                    dVar = new Z0(C0964A.a(iVar.b));
                } else {
                    throw new IllegalArgumentException("Unsupported state:" + hVar);
                }
                a1Var.f = hVar;
                eVar.e0(hVar, dVar);
            }
            t02 = new T0(a1Var, e02);
            dVar = t02;
            a1Var.f = hVar;
            eVar.e0(hVar, dVar);
        }
    }

    public B a0(C0768q qVar) {
        return If.g.N(qVar);
    }

    public boolean b(Kf.g gVar, Kf.g gVar2) {
        j.e(gVar, "c1");
        j.e(gVar2, "c2");
        if (!(gVar instanceof Hf.M)) {
            throw new IllegalArgumentException("Failed requirement.");
        } else if (!(gVar2 instanceof Hf.M)) {
            throw new IllegalArgumentException("Failed requirement.");
        } else if (If.g.b(gVar, gVar2)) {
            return true;
        } else {
            Hf.M m = (Hf.M) gVar;
            Hf.M m4 = (Hf.M) gVar2;
            Map map = (Map) this.e;
            if (((If.c) this.f).a(m, m4)) {
                return true;
            }
            if (map == null) {
                return false;
            }
            Hf.M m8 = (Hf.M) map.get(m);
            Hf.M m9 = (Hf.M) map.get(m4);
            if (m8 != null && m8.equals(m4)) {
                return true;
            }
            if (m9 == null || !m9.equals(m)) {
                return false;
            }
            return true;
        }
    }

    public void b0() {
        C1057t0 t0Var = (C1057t0) this.f;
        if (t0Var.isReady()) {
            t0Var.f.execute(new u1(this, 1));
        }
    }

    public boolean c(d dVar) {
        j.e(dVar, "$receiver");
        return dVar instanceof C1084f;
    }

    public int c0(d dVar) {
        return If.g.c(dVar);
    }

    public void d(e eVar) {
        If.g.L(eVar);
    }

    public Kf.b d0(c cVar) {
        return If.g.k(cVar);
    }

    public c e(e eVar) {
        return If.g.e(this, eVar);
    }

    public f e0(e eVar) {
        return If.g.d(eVar);
    }

    public B f(d dVar) {
        B Y;
        j.e(dVar, "<this>");
        C0768q g = If.g.g(dVar);
        if (g != null && (Y = If.g.Y(g)) != null) {
            return Y;
        }
        B h5 = If.g.h(dVar);
        j.b(h5);
        return h5;
    }

    public If.i f0(c cVar) {
        return If.g.X(cVar);
    }

    public boolean g(Kf.g gVar) {
        return If.g.D(gVar);
    }

    public B g0(d dVar) {
        return If.g.h(dVar);
    }

    public String getString(int i2) {
        String str = (String) ((L) this.e).e.get(i2);
        j.d(str, "getString(...)");
        return str;
    }

    public boolean h(e eVar) {
        return If.g.F(eVar);
    }

    public void h0(fe.i iVar) {
        boolean z;
        r1 r1Var = ((C1057t0) this.f).r;
        if (r1Var.f != null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "Headers should be received prior to messages.");
        if (r1Var.f != ((v1) this.e)) {
            Logger logger = Z.f4489a;
            while (true) {
                InputStream g = iVar.g();
                if (g != null) {
                    try {
                        ((fe.e) g).close();
                    } catch (IOException e7) {
                        Z.f4489a.log(Level.WARNING, "exception caught in closeQuietly", e7);
                    }
                } else {
                    return;
                }
            }
        } else {
            ((C1057t0) this.f).f.execute(new s1(1, this, iVar));
        }
    }

    public B i(e eVar, Kf.b bVar) {
        return If.g.j(eVar, bVar);
    }

    public P i0(C1317b bVar) {
        return If.g.T(bVar);
    }

    public i j(P p6) {
        return If.g.t(p6);
    }

    public boolean j0(Kf.g gVar) {
        return If.g.z(gVar);
    }

    public void k(e eVar) {
        If.g.M(eVar);
    }

    public boolean k0(Kf.g gVar) {
        return If.g.E(gVar);
    }

    public boolean l(Kf.g gVar) {
        return If.g.y(gVar);
    }

    public Collection l0(Kf.g gVar) {
        return If.g.V(gVar);
    }

    public C0764m m(e eVar) {
        return If.g.f(eVar);
    }

    public Set m0(e eVar) {
        return If.g.S(this, eVar);
    }

    public c0 n(d dVar) {
        return If.g.P(dVar);
    }

    public boolean n0(P p6) {
        return If.g.K(p6);
    }

    public void o(h hVar) {
        ((Map) ((W0) this.f).f).remove((P1.c) this.e);
    }

    public B o0(d dVar) {
        B N6;
        j.e(dVar, "<this>");
        C0768q g = If.g.g(dVar);
        if (g != null && (N6 = If.g.N(g)) != null) {
            return N6;
        }
        B h5 = If.g.h(dVar);
        j.b(h5);
        return h5;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [h2.t, java.lang.Object] */
    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        t tVar = (t) this.f;
        ? obj = new Object();
        obj.f1778a = tVar.f1778a;
        obj.b = tVar.b;
        obj.f1779c = tVar.f1779c;
        obj.d = tVar.d;
        return ((s) this.e).b(view, windowInsetsCompat, obj);
    }

    public B p(C0768q qVar) {
        return If.g.Y(qVar);
    }

    public boolean p0(c cVar) {
        return cVar instanceof C1316a;
    }

    public C0741g q(C1235b bVar) {
        j.e(bVar, "classId");
        C1107g gVar = (C1107g) this.f;
        j.e(gVar.c().f3350c, "<this>");
        Ve.b E = F.E((B1.b) this.e, bVar, pf.f.g);
        if (E == null) {
            return null;
        }
        C0892d.a(E.f3829a).equals(bVar);
        return gVar.f(E);
    }

    public Hf.M q0(e eVar) {
        return If.g.W(eVar);
    }

    public c0 r(c cVar) {
        return If.g.O(cVar);
    }

    public boolean r0(Kf.g gVar) {
        return If.g.G(gVar);
    }

    public boolean s(d dVar) {
        j.e(dVar, "<this>");
        return !j.a(If.g.W(o0(dVar)), If.g.W(f(dVar)));
    }

    public boolean t(e eVar) {
        j.e(eVar, "<this>");
        return If.g.y(If.g.W(eVar));
    }

    public int t0(Kf.g gVar) {
        return If.g.R(gVar);
    }

    public String toString() {
        switch (this.d) {
            case 13:
                StringBuilder sb2 = new StringBuilder(100);
                sb2.append(this.f.getClass().getSimpleName());
                sb2.append('{');
                ArrayList arrayList = (ArrayList) this.e;
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    sb2.append((String) arrayList.get(i2));
                    if (i2 < size - 1) {
                        sb2.append(ArcCommonLog.TAG_COMMA);
                    }
                }
                sb2.append('}');
                return sb2.toString();
            default:
                return super.toString();
        }
    }

    public V u(Kf.g gVar, int i2) {
        return If.g.q(gVar, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0188, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void u0(ee.a0 r11, ge.C1054s r12, ee.M r13) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.f
            ge.t0 r0 = (ge.C1057t0) r0
            java.lang.Object r0 = r0.l
            monitor-enter(r0)
            java.lang.Object r1 = r10.f     // Catch:{ all -> 0x02bb }
            ge.t0 r1 = (ge.C1057t0) r1     // Catch:{ all -> 0x02bb }
            ge.r1 r2 = r1.r     // Catch:{ all -> 0x02bb }
            java.lang.Object r3 = r10.e     // Catch:{ all -> 0x02bb }
            ge.v1 r3 = (ge.v1) r3     // Catch:{ all -> 0x02bb }
            ge.r1 r2 = r2.d(r3)     // Catch:{ all -> 0x02bb }
            r1.r = r2     // Catch:{ all -> 0x02bb }
            java.lang.Object r1 = r10.f     // Catch:{ all -> 0x02bb }
            ge.t0 r1 = (ge.C1057t0) r1     // Catch:{ all -> 0x02bb }
            G0.c r1 = r1.q     // Catch:{ all -> 0x02bb }
            ee.Y r2 = r11.f4290a     // Catch:{ all -> 0x02bb }
            java.lang.Object r1 = r1.e     // Catch:{ all -> 0x02bb }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ all -> 0x02bb }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x02bb }
            r1.add(r2)     // Catch:{ all -> 0x02bb }
            monitor-exit(r0)     // Catch:{ all -> 0x02bb }
            java.lang.Object r0 = r10.f
            ge.t0 r0 = (ge.C1057t0) r0
            java.util.concurrent.atomic.AtomicInteger r0 = r0.u
            int r0 = r0.decrementAndGet()
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != r1) goto L_0x0049
            java.lang.Object r11 = r10.f
            ge.t0 r11 = (ge.C1057t0) r11
            ee.e0 r11 = r11.f
            ge.u1 r12 = new ge.u1
            r13 = 0
            r12.<init>(r10, r13)
            r11.execute(r12)
            return
        L_0x0049:
            java.lang.Object r0 = r10.e
            ge.v1 r0 = (ge.v1) r0
            boolean r1 = r0.f4568c
            if (r1 == 0) goto L_0x0076
            java.lang.Object r1 = r10.f
            ge.t0 r1 = (ge.C1057t0) r1
            ge.i1 r0 = r1.c(r0)
            if (r0 == 0) goto L_0x0060
            java.util.concurrent.Executor r1 = r1.e
            r1.execute(r0)
        L_0x0060:
            java.lang.Object r0 = r10.f
            ge.t0 r0 = (ge.C1057t0) r0
            ge.r1 r0 = r0.r
            ge.v1 r0 = r0.f
            java.lang.Object r1 = r10.e
            ge.v1 r1 = (ge.v1) r1
            if (r0 != r1) goto L_0x02ba
            java.lang.Object r10 = r10.f
            ge.t0 r10 = (ge.C1057t0) r10
            r10.o(r11, r12, r13)
            return
        L_0x0076:
            ge.s r0 = ge.C1054s.MISCARRIED
            if (r12 != r0) goto L_0x00c1
            java.lang.Object r1 = r10.f
            ge.t0 r1 = (ge.C1057t0) r1
            java.util.concurrent.atomic.AtomicInteger r1 = r1.t
            int r1 = r1.incrementAndGet()
            r2 = 1000(0x3e8, float:1.401E-42)
            if (r1 <= r2) goto L_0x00c1
            java.lang.Object r0 = r10.f
            ge.t0 r0 = (ge.C1057t0) r0
            java.lang.Object r1 = r10.e
            ge.v1 r1 = (ge.v1) r1
            ge.i1 r1 = r0.c(r1)
            if (r1 == 0) goto L_0x009b
            java.util.concurrent.Executor r0 = r0.e
            r0.execute(r1)
        L_0x009b:
            java.lang.Object r0 = r10.f
            ge.t0 r0 = (ge.C1057t0) r0
            ge.r1 r0 = r0.r
            ge.v1 r0 = r0.f
            java.lang.Object r1 = r10.e
            ge.v1 r1 = (ge.v1) r1
            if (r0 != r1) goto L_0x02ba
            ee.a0 r0 = ee.a0.n
            java.lang.String r1 = "Too many transparent retries. Might be a bug in gRPC"
            ee.a0 r0 = r0.g(r1)
            ee.c0 r11 = r11.a()
            ee.a0 r11 = r0.f(r11)
            java.lang.Object r10 = r10.f
            ge.t0 r10 = (ge.C1057t0) r10
            r10.o(r11, r12, r13)
            return
        L_0x00c1:
            java.lang.Object r1 = r10.f
            ge.t0 r1 = (ge.C1057t0) r1
            ge.r1 r1 = r1.r
            ge.v1 r1 = r1.f
            if (r1 != 0) goto L_0x0292
            r1 = 1
            if (r12 == r0) goto L_0x0251
            ge.s r0 = ge.C1054s.REFUSED
            r2 = 0
            if (r12 != r0) goto L_0x00e1
            java.lang.Object r0 = r10.f
            ge.t0 r0 = (ge.C1057t0) r0
            java.util.concurrent.atomic.AtomicBoolean r0 = r0.s
            boolean r0 = r0.compareAndSet(r2, r1)
            if (r0 == 0) goto L_0x00e1
            goto L_0x0251
        L_0x00e1:
            ge.s r0 = ge.C1054s.DROPPED
            if (r12 != r0) goto L_0x00f2
            java.lang.Object r0 = r10.f
            ge.t0 r0 = (ge.C1057t0) r0
            boolean r1 = r0.k
            if (r1 == 0) goto L_0x0292
            r0.m()
            goto L_0x0292
        L_0x00f2:
            java.lang.Object r0 = r10.f
            ge.t0 r0 = (ge.C1057t0) r0
            java.util.concurrent.atomic.AtomicBoolean r0 = r0.s
            r0.set(r1)
            java.lang.Object r0 = r10.f
            ge.t0 r0 = (ge.C1057t0) r0
            boolean r3 = r0.k
            if (r3 == 0) goto L_0x018e
            java.lang.Integer r0 = x0(r13)
            java.lang.Object r3 = r10.f
            ge.t0 r3 = (ge.C1057t0) r3
            ge.a0 r4 = r3.f4557j
            F2.c0 r4 = r4.f4496c
            ee.Y r5 = r11.f4290a
            boolean r4 = r4.contains(r5)
            ge.w1 r5 = r3.f4559p
            if (r5 == 0) goto L_0x012b
            if (r4 != 0) goto L_0x0123
            if (r0 == 0) goto L_0x012b
            int r5 = r0.intValue()
            if (r5 >= 0) goto L_0x012b
        L_0x0123:
            ge.w1 r3 = r3.f4559p
            boolean r3 = r3.a()
            r3 = r3 ^ r1
            goto L_0x012c
        L_0x012b:
            r3 = r2
        L_0x012c:
            if (r4 == 0) goto L_0x0142
            if (r3 != 0) goto L_0x0142
            boolean r5 = r11.e()
            if (r5 != 0) goto L_0x0142
            if (r0 == 0) goto L_0x0142
            int r5 = r0.intValue()
            if (r5 <= 0) goto L_0x0142
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)
        L_0x0142:
            if (r4 == 0) goto L_0x0147
            if (r3 != 0) goto L_0x0147
            goto L_0x0148
        L_0x0147:
            r1 = r2
        L_0x0148:
            if (r1 == 0) goto L_0x0151
            java.lang.Object r2 = r10.f
            ge.t0 r2 = (ge.C1057t0) r2
            ge.C1057t0.a(r2, r0)
        L_0x0151:
            java.lang.Object r0 = r10.f
            ge.t0 r0 = (ge.C1057t0) r0
            java.lang.Object r3 = r0.l
            monitor-enter(r3)
            java.lang.Object r0 = r10.f     // Catch:{ all -> 0x0185 }
            ge.t0 r0 = (ge.C1057t0) r0     // Catch:{ all -> 0x0185 }
            ge.r1 r2 = r0.r     // Catch:{ all -> 0x0185 }
            java.lang.Object r4 = r10.e     // Catch:{ all -> 0x0185 }
            ge.v1 r4 = (ge.v1) r4     // Catch:{ all -> 0x0185 }
            ge.r1 r2 = r2.b(r4)     // Catch:{ all -> 0x0185 }
            r0.r = r2     // Catch:{ all -> 0x0185 }
            if (r1 == 0) goto L_0x0189
            java.lang.Object r0 = r10.f     // Catch:{ all -> 0x0185 }
            ge.t0 r0 = (ge.C1057t0) r0     // Catch:{ all -> 0x0185 }
            ge.r1 r1 = r0.r     // Catch:{ all -> 0x0185 }
            boolean r0 = r0.n(r1)     // Catch:{ all -> 0x0185 }
            if (r0 != 0) goto L_0x0187
            java.lang.Object r0 = r10.f     // Catch:{ all -> 0x0185 }
            ge.t0 r0 = (ge.C1057t0) r0     // Catch:{ all -> 0x0185 }
            ge.r1 r0 = r0.r     // Catch:{ all -> 0x0185 }
            java.util.Collection r0 = r0.d     // Catch:{ all -> 0x0185 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0185 }
            if (r0 != 0) goto L_0x0189
            goto L_0x0187
        L_0x0185:
            r10 = move-exception
            goto L_0x018c
        L_0x0187:
            monitor-exit(r3)     // Catch:{ all -> 0x0185 }
            return
        L_0x0189:
            monitor-exit(r3)     // Catch:{ all -> 0x0185 }
            goto L_0x0292
        L_0x018c:
            monitor-exit(r3)     // Catch:{ all -> 0x0185 }
            throw r10
        L_0x018e:
            ge.x1 r3 = r0.f4556i
            r4 = 0
            if (r3 != 0) goto L_0x019b
            de.c r0 = new de.c
            r0.<init>(r4, r2)
            goto L_0x020e
        L_0x019b:
            F2.c0 r3 = r3.f
            ee.Y r6 = r11.f4290a
            boolean r3 = r3.contains(r6)
            java.lang.Integer r6 = x0(r13)
            ge.w1 r7 = r0.f4559p
            if (r7 == 0) goto L_0x01bd
            if (r3 != 0) goto L_0x01b5
            if (r6 == 0) goto L_0x01bd
            int r7 = r6.intValue()
            if (r7 >= 0) goto L_0x01bd
        L_0x01b5:
            ge.w1 r7 = r0.f4559p
            boolean r7 = r7.a()
            r7 = r7 ^ r1
            goto L_0x01be
        L_0x01bd:
            r7 = r2
        L_0x01be:
            ge.x1 r8 = r0.f4556i
            int r8 = r8.f4572a
            java.lang.Object r9 = r10.e
            ge.v1 r9 = (ge.v1) r9
            int r9 = r9.d
            int r9 = r9 + r1
            if (r8 <= r9) goto L_0x0207
            if (r7 != 0) goto L_0x0207
            if (r6 != 0) goto L_0x01ef
            if (r3 == 0) goto L_0x0207
            long r3 = r0.f4551A
            double r3 = (double) r3
            java.util.Random r5 = ge.C1057t0.f4550K
            double r5 = r5.nextDouble()
            double r5 = r5 * r3
            long r4 = (long) r5
            long r6 = r0.f4551A
            double r6 = (double) r6
            ge.x1 r3 = r0.f4556i
            double r8 = r3.d
            double r6 = r6 * r8
            long r6 = (long) r6
            long r8 = r3.f4573c
            long r6 = java.lang.Math.min(r6, r8)
            r0.f4551A = r6
        L_0x01ed:
            r0 = r1
            goto L_0x0208
        L_0x01ef:
            int r3 = r6.intValue()
            if (r3 < 0) goto L_0x0207
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS
            int r4 = r6.intValue()
            long r4 = (long) r4
            long r4 = r3.toNanos(r4)
            ge.x1 r3 = r0.f4556i
            long r6 = r3.b
            r0.f4551A = r6
            goto L_0x01ed
        L_0x0207:
            r0 = r2
        L_0x0208:
            de.c r3 = new de.c
            r3.<init>(r4, r0)
            r0 = r3
        L_0x020e:
            boolean r3 = r0.f4229a
            if (r3 == 0) goto L_0x0292
            java.lang.Object r11 = r10.f
            ge.t0 r11 = (ge.C1057t0) r11
            java.lang.Object r12 = r10.e
            ge.v1 r12 = (ge.v1) r12
            int r12 = r12.d
            int r12 = r12 + r1
            ge.v1 r11 = r11.d(r12, r2)
            if (r11 != 0) goto L_0x0225
            goto L_0x02ba
        L_0x0225:
            java.lang.Object r12 = r10.f
            ge.t0 r12 = (ge.C1057t0) r12
            java.lang.Object r1 = r12.l
            monitor-enter(r1)
            java.lang.Object r12 = r10.f     // Catch:{ all -> 0x024e }
            ge.t0 r12 = (ge.C1057t0) r12     // Catch:{ all -> 0x024e }
            Kd.a r13 = new Kd.a     // Catch:{ all -> 0x024e }
            java.lang.Object r2 = r12.l     // Catch:{ all -> 0x024e }
            r13.<init>(r2)     // Catch:{ all -> 0x024e }
            r12.y = r13     // Catch:{ all -> 0x024e }
            monitor-exit(r1)     // Catch:{ all -> 0x024e }
            java.util.concurrent.ScheduledExecutorService r12 = r12.g
            ge.t1 r1 = new ge.t1
            r2 = 0
            r1.<init>(r10, r11, r2)
            long r10 = r0.b
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.NANOSECONDS
            java.util.concurrent.ScheduledFuture r10 = r12.schedule(r1, r10, r0)
            r13.f(r10)
            return
        L_0x024e:
            r10 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x024e }
            throw r10
        L_0x0251:
            java.lang.Object r11 = r10.f
            ge.t0 r11 = (ge.C1057t0) r11
            java.lang.Object r12 = r10.e
            ge.v1 r12 = (ge.v1) r12
            int r12 = r12.d
            ge.v1 r11 = r11.d(r12, r1)
            if (r11 != 0) goto L_0x0262
            goto L_0x02ba
        L_0x0262:
            java.lang.Object r12 = r10.f
            ge.t0 r12 = (ge.C1057t0) r12
            boolean r13 = r12.k
            if (r13 == 0) goto L_0x0282
            java.lang.Object r12 = r12.l
            monitor-enter(r12)
            java.lang.Object r13 = r10.f     // Catch:{ all -> 0x027f }
            ge.t0 r13 = (ge.C1057t0) r13     // Catch:{ all -> 0x027f }
            ge.r1 r0 = r13.r     // Catch:{ all -> 0x027f }
            java.lang.Object r1 = r10.e     // Catch:{ all -> 0x027f }
            ge.v1 r1 = (ge.v1) r1     // Catch:{ all -> 0x027f }
            ge.r1 r0 = r0.c(r1, r11)     // Catch:{ all -> 0x027f }
            r13.r = r0     // Catch:{ all -> 0x027f }
            monitor-exit(r12)     // Catch:{ all -> 0x027f }
            goto L_0x0282
        L_0x027f:
            r10 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x027f }
            throw r10
        L_0x0282:
            java.lang.Object r12 = r10.f
            ge.t0 r12 = (ge.C1057t0) r12
            java.util.concurrent.Executor r12 = r12.e
            ge.t1 r13 = new ge.t1
            r0 = 1
            r13.<init>(r10, r11, r0)
            r12.execute(r13)
            return
        L_0x0292:
            java.lang.Object r0 = r10.f
            ge.t0 r0 = (ge.C1057t0) r0
            java.lang.Object r1 = r10.e
            ge.v1 r1 = (ge.v1) r1
            ge.i1 r1 = r0.c(r1)
            if (r1 == 0) goto L_0x02a5
            java.util.concurrent.Executor r0 = r0.e
            r0.execute(r1)
        L_0x02a5:
            java.lang.Object r0 = r10.f
            ge.t0 r0 = (ge.C1057t0) r0
            ge.r1 r0 = r0.r
            ge.v1 r0 = r0.f
            java.lang.Object r1 = r10.e
            ge.v1 r1 = (ge.v1) r1
            if (r0 != r1) goto L_0x02ba
            java.lang.Object r10 = r10.f
            ge.t0 r10 = (ge.C1057t0) r10
            r10.o(r11, r12, r13)
        L_0x02ba:
            return
        L_0x02bb:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x02bb }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.W0.u0(ee.a0, ge.s, ee.M):void");
    }

    public void v(d dVar) {
        j.e(dVar, "<this>");
        If.g.g(dVar);
    }

    public void v0(Object obj, String str) {
        ((ArrayList) this.e).add(C0212a.B(str, "=", String.valueOf(obj)));
    }

    public c0 w(ArrayList arrayList) {
        B b;
        int size = arrayList.size();
        if (size == 0) {
            throw new IllegalStateException("Expected some types");
        } else if (size == 1) {
            return (c0) C1194l.a1(arrayList);
        } else {
            ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
            Iterator it = arrayList.iterator();
            boolean z = false;
            boolean z3 = false;
            while (it.hasNext()) {
                c0 c0Var = (c0) it.next();
                if (z || C0754c.k(c0Var)) {
                    z = true;
                } else {
                    z = false;
                }
                if (c0Var instanceof B) {
                    b = (B) c0Var;
                } else if (c0Var instanceof C0768q) {
                    b = ((C0768q) c0Var).e;
                    z3 = true;
                } else {
                    throw new RuntimeException();
                }
                arrayList2.add(b);
            }
            if (z) {
                return l.c(k.INTERSECTION_OF_ERROR_TYPES, arrayList.toString());
            }
            u uVar = u.f3471a;
            if (!z3) {
                return uVar.b(arrayList2);
            }
            ArrayList arrayList3 = new ArrayList(C1196n.w0(arrayList, 10));
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                arrayList3.add(C0754c.E((c0) it2.next()));
            }
            return C0754c.f(uVar.b(arrayList2), uVar.b(arrayList3));
        }
    }

    public void w0(String str, String str2, Ae.b bVar) {
        LinkedHashMap linkedHashMap = ((m) this.f).f400a;
        C1092n nVar = new C1092n(this, str, str2);
        bVar.invoke(nVar);
        String str3 = (String) this.e;
        ArrayList arrayList = nVar.b;
        ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add((String) ((me.i) it.next()).d);
        }
        String str4 = (String) nVar.f4595c.d;
        j.e(str4, "ret");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append('(');
        sb2.append(C1194l.R0(arrayList2, "", (String) null, (String) null, jf.t.d, 30));
        sb2.append(')');
        if (str4.length() > 1) {
            str4 = C0086a.h(';', "L", str4);
        }
        sb2.append(str4);
        String sb3 = sb2.toString();
        j.e(str3, "internalName");
        j.e(sb3, "jvmDescriptor");
        String str5 = str3 + '.' + sb3;
        C1094p pVar = (C1094p) nVar.f4595c.e;
        ArrayList arrayList3 = new ArrayList(C1196n.w0(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList3.add((C1094p) ((me.i) it2.next()).e);
        }
        linkedHashMap.put(str5, new C1090l(pVar, arrayList3, nVar.f4594a));
    }

    public c0 x(e eVar, e eVar2) {
        return If.g.m(this, eVar, eVar2);
    }

    public boolean y(e eVar) {
        return If.g.B(eVar);
    }

    public n y0(int i2) {
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        boolean z = false;
        while (i2 != -1) {
            lf.J j2 = (lf.J) ((K) this.f).e.get(i2);
            String str = (String) ((L) this.e).e.get(j2.g);
            I i7 = j2.f4754h;
            j.b(i7);
            int i8 = C1210g.f4973a[i7.ordinal()];
            if (i8 == 1) {
                linkedList2.addFirst(str);
            } else if (i8 == 2) {
                linkedList.addFirst(str);
            } else if (i8 == 3) {
                linkedList2.addFirst(str);
                z = true;
            } else {
                throw new RuntimeException();
            }
            i2 = j2.f;
        }
        return new n(linkedList, linkedList2, Boolean.valueOf(z));
    }

    public void z(M m) {
        int i2;
        int i7;
        if (((v1) this.e).d > 0) {
            G g = C1057t0.f4547H;
            m.a(g);
            m.c(g, String.valueOf(((v1) this.e).d));
        }
        C1057t0 t0Var = (C1057t0) this.f;
        G g3 = C1057t0.f4547H;
        C1032i1 c5 = t0Var.c((v1) this.e);
        if (c5 != null) {
            t0Var.e.execute(c5);
        }
        if (((C1057t0) this.f).r.f == ((v1) this.e)) {
            w1 w1Var = ((C1057t0) this.f).f4559p;
            if (w1Var != null) {
                AtomicInteger atomicInteger = w1Var.d;
                do {
                    i2 = atomicInteger.get();
                    i7 = w1Var.f4569a;
                    if (i2 == i7) {
                        break;
                    }
                } while (!atomicInteger.compareAndSet(i2, Math.min(w1Var.f4570c + i2, i7)));
            }
            ((C1057t0) this.f).f.execute(new s1(0, this, m));
        }
    }

    public D0.f z0(C1240g gVar, String str) {
        j.e(gVar, "name");
        String b = gVar.b();
        j.d(b, "asString(...)");
        return new D0.f(this, new jf.s(b.concat(str)));
    }

    public /* synthetic */ W0(Object obj, Object obj2, boolean z, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public W0(int i2) {
        this.d = i2;
        switch (i2) {
            case 14:
                C0279d dVar = C0279d.d;
                this.e = new SparseIntArray();
                this.f = dVar;
                return;
            default:
                this.e = Collections.synchronizedMap(new WeakHashMap());
                this.f = Collections.synchronizedMap(new WeakHashMap());
                return;
        }
    }

    public /* synthetic */ W0(Object obj) {
        this.d = 13;
        this.f = obj;
        this.e = new ArrayList();
    }

    public W0(String str, C0246a aVar, ie.c cVar) {
        this.d = 9;
        this.f = str;
        this.e = aVar;
    }

    public W0(L l, K k) {
        this.d = 6;
        j.e(l, "strings");
        j.e(k, "qualifiedNames");
        this.e = l;
        this.f = k;
    }

    public W0(HashMap hashMap, If.c cVar) {
        this.d = 8;
        j.e(cVar, "equalityAxioms");
        this.e = hashMap;
        this.f = cVar;
    }

    public W0(C0744a aVar, HashMap hashMap, HashMap hashMap2) {
        this.d = 4;
        this.e = aVar;
        this.f = hashMap;
    }

    public W0(m mVar, String str) {
        this.d = 3;
        j.e(str, "className");
        this.f = mVar;
        this.e = str;
    }

    public W0(C1264n nVar) {
        this.d = 7;
        Iterator it = ((C0042x) nVar.d.f5066a.entrySet()).iterator();
        this.e = it;
        if (it.hasNext()) {
            this.f = (Map.Entry) it.next();
        }
    }

    public void s0(e eVar, Kf.g gVar) {
    }
}
