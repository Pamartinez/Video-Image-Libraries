package Ff;

import Af.o;
import B1.b;
import Gf.p;
import Hf.B;
import Hf.C0754c;
import Hf.I;
import Hf.M;
import Hf.X;
import Hf.a0;
import Hf.d0;
import Jf.i;
import Jf.k;
import Jf.l;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0822l;
import Qe.C0823m;
import Qe.C0826p;
import Qe.C0833x;
import Re.h;
import Te.C0844e;
import Te.C0845f;
import java.util.List;
import kotlin.jvm.internal.j;
import lf.T;
import nf.C1209f;
import nf.C1211h;
import qf.C1240g;
import rf.C1252b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w extends C0845f implements n {
    public final T m;
    public final C1209f n;

    /* renamed from: o  reason: collision with root package name */
    public final b f3408o;

    /* renamed from: p  reason: collision with root package name */
    public final C1211h f3409p;
    public final m q;
    public B r;
    public B s;
    public List t;
    public B u;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public w(p pVar, C0822l lVar, h hVar, C1240g gVar, C0826p pVar2, T t3, C1209f fVar, b bVar, C1211h hVar2, m mVar) {
        super(pVar, lVar, hVar, gVar, pVar2);
        j.e(pVar, "storageManager");
        j.e(lVar, "containingDeclaration");
        j.e(pVar2, "visibility");
        j.e(t3, "proto");
        j.e(fVar, "nameResolver");
        j.e(bVar, "typeTable");
        j.e(hVar2, "versionRequirementTable");
        this.m = t3;
        this.n = fVar;
        this.f3408o = bVar;
        this.f3409p = hVar2;
        this.q = mVar;
    }

    public final C1209f C() {
        return this.n;
    }

    public final m D() {
        return this.q;
    }

    public final C0816f E0() {
        if (C0754c.k(F0())) {
            return null;
        }
        C0819i g = F0().s0().g();
        if (g instanceof C0816f) {
            return (C0816f) g;
        }
        return null;
    }

    public final B F0() {
        B b = this.s;
        if (b != null) {
            return b;
        }
        j.k("expandedType");
        throw null;
    }

    public final B G0() {
        B b = this.r;
        if (b != null) {
            return b;
        }
        j.k("underlyingType");
        throw null;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [Ae.b, java.lang.Object] */
    public final void H0(List list, B b, B b5) {
        Af.p pVar;
        B b8;
        j.e(b, "underlyingType");
        j.e(b5, "expandedType");
        this.k = list;
        this.r = b;
        this.s = b5;
        this.t = C0833x.c(this);
        C0816f E02 = E0();
        if (E02 == null || (pVar = E02.P()) == null) {
            pVar = o.b;
        }
        Af.p pVar2 = pVar;
        ? obj = new Object();
        i iVar = a0.f3439a;
        if (l.f(this)) {
            b8 = l.c(k.UNABLE_TO_SUBSTITUTE_TYPE, toString());
        } else {
            M q10 = q();
            if (q10 != null) {
                List d = a0.d(((C0844e) q10).getParameters());
                I.e.getClass();
                b8 = C0754c.w(I.f, q10, d, false, pVar2, obj);
            } else {
                a0.a(12);
                throw null;
            }
        }
        this.u = b8;
    }

    public final C1252b Y() {
        return this.m;
    }

    public final C0823m c(X x9) {
        j.e(x9, "substitutor");
        if (x9.f3438a.e()) {
            return this;
        }
        C0822l g = g();
        j.d(g, "getContainingDeclaration(...)");
        h annotations = getAnnotations();
        j.d(annotations, "<get-annotations>(...)");
        C1240g name = getName();
        j.d(name, "getName(...)");
        w wVar = new w(this.f3774i, g, annotations, name, this.f3775j, this.m, this.n, this.f3408o, this.f3409p, this.q);
        List j2 = j();
        B G02 = G0();
        d0 d0Var = d0.INVARIANT;
        wVar.H0(j2, C0754c.b(x9.g(G02, d0Var)), C0754c.b(x9.g(F0(), d0Var)));
        return wVar;
    }

    public final B i() {
        B b = this.u;
        if (b != null) {
            return b;
        }
        j.k("defaultTypeImpl");
        throw null;
    }

    public final b z() {
        return this.f3408o;
    }
}
