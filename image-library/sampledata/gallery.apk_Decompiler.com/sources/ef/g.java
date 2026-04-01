package ef;

import Af.p;
import D0.e;
import Df.C0736b;
import Hf.B;
import Hf.C0754c;
import Hf.C0774x;
import Hf.G;
import Hf.I;
import Hf.M;
import Hf.P;
import Hf.T;
import Hf.Y;
import Hf.d0;
import Jf.k;
import Jf.l;
import Qe.C0816f;
import Qe.C0819i;
import Qe.V;
import a.C0068a;
import df.C0937F;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1196n;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends T {

    /* renamed from: c  reason: collision with root package name */
    public static final C0993a f4322c;
    public static final C0993a d;
    public final e b = new e((f) new Object());

    static {
        Y y = Y.COMMON;
        f4322c = C0068a.Y(y, false, (C0937F) null, 5).b(C0994b.FLEXIBLE_LOWER_BOUND);
        d = C0068a.Y(y, false, (C0937F) null, 5).b(C0994b.FLEXIBLE_UPPER_BOUND);
    }

    public final P d(C0774x xVar) {
        return new G(h(xVar, new C0993a(Y.COMMON, false, false, (Set) null, 62)));
    }

    public final i g(B b5, C0816f fVar, C0993a aVar) {
        if (b5.s0().getParameters().isEmpty()) {
            return new i(b5, Boolean.FALSE);
        }
        if (Ne.i.y(b5)) {
            P p6 = (P) b5.e0().get(0);
            d0 a7 = p6.a();
            C0774x b8 = p6.b();
            j.d(b8, "getType(...)");
            return new i(C0754c.u(b5.p0(), b5.s0(), C0246a.e0(new G(h(b8, aVar), a7)), b5.u0()), Boolean.FALSE);
        } else if (C0754c.k(b5)) {
            return new i(l.c(k.ERROR_RAW_TYPE, b5.s0().toString()), Boolean.FALSE);
        } else {
            p a0 = fVar.a0(this);
            j.d(a0, "getMemberScope(...)");
            I p02 = b5.p0();
            M q = fVar.q();
            j.d(q, "getTypeConstructor(...)");
            List parameters = fVar.q().getParameters();
            j.d(parameters, "getParameters(...)");
            Iterable<V> iterable = parameters;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (V v : iterable) {
                j.b(v);
                e eVar = this.b;
                arrayList.add(f.a(v, aVar, eVar, eVar.I(v, aVar)));
            }
            return new i(C0754c.w(p02, q, arrayList, b5.u0(), a0, new C0736b(fVar, this, b5, aVar)), Boolean.TRUE);
        }
    }

    public final C0774x h(C0774x xVar, C0993a aVar) {
        C0819i g = xVar.s0().g();
        if (g instanceof V) {
            aVar.getClass();
            C0993a aVar2 = aVar;
            C0993a a7 = C0993a.a(aVar2, (C0994b) null, true, (Set) null, (B) null, 59);
            return h(this.b.I((V) g, a7), aVar2);
        } else if (g instanceof C0816f) {
            C0819i g3 = C0754c.E(xVar).s0().g();
            if (g3 instanceof C0816f) {
                i g10 = g(C0754c.m(xVar), (C0816f) g, f4322c);
                B b5 = (B) g10.d;
                boolean booleanValue = ((Boolean) g10.e).booleanValue();
                i g11 = g(C0754c.E(xVar), (C0816f) g3, d);
                B b8 = (B) g11.d;
                boolean booleanValue2 = ((Boolean) g11.e).booleanValue();
                if (booleanValue || booleanValue2) {
                    return new i(b5, b8);
                }
                return C0754c.f(b5, b8);
            }
            throw new IllegalStateException(("For some reason declaration for upper bound is not a class but \"" + g3 + "\" while for lower it's \"" + g + '\"').toString());
        } else {
            throw new IllegalStateException(("Unexpected declaration kind: " + g).toString());
        }
    }
}
