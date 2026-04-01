package Ff;

import B1.b;
import Qe.C0813c;
import Qe.C0816f;
import Qe.C0821k;
import Qe.C0822l;
import Qe.C0831v;
import Qe.Q;
import Qe.S;
import Re.h;
import Te.C0848i;
import Te.t;
import kotlin.jvm.internal.j;
import lf.C1159l;
import nf.C1209f;
import nf.C1211h;
import qf.C1240g;
import rf.C1252b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends C0848i implements b {

    /* renamed from: I  reason: collision with root package name */
    public final C1159l f3377I;

    /* renamed from: J  reason: collision with root package name */
    public final C1209f f3378J;

    /* renamed from: K  reason: collision with root package name */
    public final b f3379K;
    public final C1211h L;

    /* renamed from: M  reason: collision with root package name */
    public final m f3380M;

    public c(C0816f fVar, C0821k kVar, h hVar, boolean z, C0813c cVar, C1159l lVar, C1209f fVar2, b bVar, C1211h hVar2, m mVar, Q q) {
        S s;
        boolean z3;
        h hVar3;
        C0821k kVar2;
        C0816f fVar3;
        C0848i iVar;
        C1159l lVar2 = lVar;
        C1209f fVar4 = fVar2;
        b bVar2 = bVar;
        C1211h hVar4 = hVar2;
        j.e(fVar, "containingDeclaration");
        j.e(hVar, "annotations");
        C0813c cVar2 = cVar;
        j.e(cVar2, "kind");
        j.e(lVar2, "proto");
        j.e(fVar4, "nameResolver");
        j.e(bVar2, "typeTable");
        j.e(hVar4, "versionRequirementTable");
        if (q == null) {
            s = Q.f3662a;
            fVar3 = fVar;
            kVar2 = kVar;
            hVar3 = hVar;
            z3 = z;
            iVar = this;
        } else {
            s = q;
            iVar = this;
            fVar3 = fVar;
            kVar2 = kVar;
            hVar3 = hVar;
            z3 = z;
        }
        new C0848i(fVar3, kVar2, hVar3, z3, cVar2, s);
        this.f3377I = lVar2;
        this.f3378J = fVar4;
        this.f3379K = bVar2;
        this.L = hVar4;
        this.f3380M = mVar;
    }

    public final C1209f C() {
        return this.f3378J;
    }

    public final m D() {
        return this.f3380M;
    }

    public final /* bridge */ /* synthetic */ t G0(C0813c cVar, C0822l lVar, C0831v vVar, Q q, h hVar, C1240g gVar) {
        C0831v vVar2 = vVar;
        C0813c cVar2 = cVar;
        return V0(lVar, vVar2, cVar2, hVar, q);
    }

    public final /* bridge */ /* synthetic */ C0848i P0(C0813c cVar, C0822l lVar, C0831v vVar, Q q, h hVar, C1240g gVar) {
        C0831v vVar2 = vVar;
        C0813c cVar2 = cVar;
        return V0(lVar, vVar2, cVar2, hVar, q);
    }

    public final c V0(C0822l lVar, C0831v vVar, C0813c cVar, h hVar, Q q) {
        j.e(lVar, "newOwner");
        C0813c cVar2 = cVar;
        j.e(cVar2, "kind");
        h hVar2 = hVar;
        j.e(hVar2, "annotations");
        c cVar3 = new c((C0816f) lVar, (C0821k) vVar, hVar2, this.f3779H, cVar2, this.f3377I, this.f3378J, this.f3379K, this.L, this.f3380M, q);
        cVar3.z = this.z;
        return cVar3;
    }

    public final C1252b Y() {
        return this.f3377I;
    }

    public final boolean isExternal() {
        return false;
    }

    public final boolean isInline() {
        return false;
    }

    public final boolean isSuspend() {
        return false;
    }

    public final boolean w() {
        return false;
    }

    public final b z() {
        return this.f3379K;
    }
}
