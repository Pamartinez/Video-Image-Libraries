package Ff;

import B1.b;
import Qe.C0813c;
import Qe.C0822l;
import Qe.C0831v;
import Qe.Q;
import Qe.S;
import Re.h;
import Te.K;
import Te.t;
import kotlin.jvm.internal.j;
import lf.C1171y;
import nf.C1209f;
import nf.C1211h;
import qf.C1240g;
import rf.C1252b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class v extends K implements b {

    /* renamed from: H  reason: collision with root package name */
    public final C1171y f3404H;

    /* renamed from: I  reason: collision with root package name */
    public final C1209f f3405I;

    /* renamed from: J  reason: collision with root package name */
    public final b f3406J;

    /* renamed from: K  reason: collision with root package name */
    public final C1211h f3407K;
    public final m L;

    public v(C0822l lVar, K k, h hVar, C1240g gVar, C0813c cVar, C1171y yVar, C1209f fVar, b bVar, C1211h hVar2, m mVar, Q q) {
        S s;
        C1240g gVar2;
        h hVar3;
        K k10;
        C0822l lVar2;
        K k11;
        C1171y yVar2 = yVar;
        C1209f fVar2 = fVar;
        b bVar2 = bVar;
        C1211h hVar4 = hVar2;
        j.e(lVar, "containingDeclaration");
        j.e(hVar, "annotations");
        C0813c cVar2 = cVar;
        j.e(cVar2, "kind");
        j.e(yVar2, "proto");
        j.e(fVar2, "nameResolver");
        j.e(bVar2, "typeTable");
        j.e(hVar4, "versionRequirementTable");
        if (q == null) {
            s = Q.f3662a;
            lVar2 = lVar;
            k10 = k;
            hVar3 = hVar;
            gVar2 = gVar;
            k11 = this;
        } else {
            s = q;
            k11 = this;
            lVar2 = lVar;
            k10 = k;
            hVar3 = hVar;
            gVar2 = gVar;
        }
        new K(lVar2, k10, hVar3, gVar2, cVar2, s);
        this.f3404H = yVar2;
        this.f3405I = fVar2;
        this.f3406J = bVar2;
        this.f3407K = hVar4;
        this.L = mVar;
    }

    public final C1209f C() {
        return this.f3405I;
    }

    public final m D() {
        return this.L;
    }

    public final t G0(C0813c cVar, C0822l lVar, C0831v vVar, Q q, h hVar, C1240g gVar) {
        C1240g gVar2;
        j.e(lVar, "newOwner");
        j.e(cVar, "kind");
        h hVar2 = hVar;
        j.e(hVar2, "annotations");
        K k = (K) vVar;
        if (gVar == null) {
            C1240g name = getName();
            j.d(name, "getName(...)");
            gVar2 = name;
        } else {
            gVar2 = gVar;
        }
        C0813c cVar2 = cVar;
        C0822l lVar2 = lVar;
        v vVar2 = new v(lVar2, k, hVar2, gVar2, cVar2, this.f3404H, this.f3405I, this.f3406J, this.f3407K, this.L, q);
        vVar2.z = this.z;
        return vVar2;
    }

    public final C1252b Y() {
        return this.f3404H;
    }

    public final b z() {
        return this.f3406J;
    }
}
