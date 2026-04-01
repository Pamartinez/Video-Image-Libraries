package Jf;

import Ae.b;
import Af.f;
import Af.p;
import Qe.A;
import Qe.C0813c;
import Qe.C0819i;
import Qe.C0826p;
import Qe.C0827q;
import Qe.Q;
import Te.K;
import Te.u;
import Ye.a;
import c0.C0086a;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1202t;
import ne.v;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class g implements p {
    public final String b;

    public g(h hVar, String... strArr) {
        j.e(hVar, "kind");
        j.e(strArr, "formatParams");
        String a7 = hVar.a();
        Object[] copyOf = Arrays.copyOf(strArr, strArr.length);
        this.b = String.format(a7, Arrays.copyOf(copyOf, copyOf.length));
    }

    public Set b() {
        return v.d;
    }

    public C0819i c(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        return new a(C1240g.g(String.format(b.ERROR_CLASS.a(), Arrays.copyOf(new Object[]{gVar}, 1))));
    }

    public Collection d(f fVar, b bVar) {
        j.e(fVar, "kindFilter");
        return C1202t.d;
    }

    public Set e() {
        return v.d;
    }

    public Set g() {
        return v.d;
    }

    /* renamed from: h */
    public Set a(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        a aVar2 = l.f3483c;
        j.e(aVar2, "containingDeclaration");
        K k = new K(aVar2, (K) null, Re.g.f3692a, C1240g.g(b.ERROR_FUNCTION.a()), C0813c.DECLARATION, Q.f3662a);
        i c5 = l.c(k.RETURN_TYPE_FOR_FUNCTION, new String[0]);
        A a7 = A.OPEN;
        C0826p pVar = C0827q.e;
        C1202t tVar = C1202t.d;
        k.J0((u) null, (u) null, tVar, tVar, tVar, c5, a7, pVar);
        return B1.a.S(k);
    }

    /* renamed from: i */
    public Set f(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        l lVar = l.f3482a;
        return l.f;
    }

    public String toString() {
        return C0086a.m(new StringBuilder("ErrorScope{"), this.b, '}');
    }
}
