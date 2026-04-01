package Ef;

import Af.g;
import Af.p;
import D0.f;
import Df.F;
import Df.l;
import Ff.t;
import Gf.m;
import Qe.C;
import Qe.H;
import Te.B;
import ge.W0;
import jf.C1109i;
import kotlin.jvm.internal.j;
import lf.E;
import lf.K;
import lf.L;
import mf.C1178a;
import qf.C1236c;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends B implements H {
    public final C1178a k;
    public final W0 l;
    public final f m;
    public E n;

    /* renamed from: o  reason: collision with root package name */
    public t f3373o;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(C1236c cVar, m mVar, C c5, E e, C1178a aVar) {
        super(c5, cVar);
        j.e(cVar, "fqName");
        j.e(c5, "module");
        this.k = aVar;
        L l8 = e.g;
        j.d(l8, "getStrings(...)");
        K k10 = e.f4739h;
        j.d(k10, "getQualifiedNames(...)");
        W0 w02 = new W0(l8, k10);
        this.l = w02;
        this.m = new f(e, w02, aVar, new F(this));
        this.n = e;
    }

    public final p A() {
        t tVar = this.f3373o;
        if (tVar != null) {
            return tVar;
        }
        j.k("_memberScope");
        throw null;
    }

    public final void F0(l lVar) {
        j.e(lVar, "components");
        E e = this.n;
        if (e != null) {
            this.n = null;
            lf.C c5 = e.f4740i;
            j.d(c5, "getPackage(...)");
            this.f3373o = new t(this, c5, this.l, this.k, (C1109i) null, lVar, "scope of " + this, new g(3, this));
            return;
        }
        throw new IllegalStateException("Repeated call to DeserializedPackageFragmentImpl::initialize");
    }

    public final String toString() {
        return "builtins package fragment for " + this.f3743i + " from " + C1353d.j(this);
    }
}
