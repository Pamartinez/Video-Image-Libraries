package Te;

import Af.g;
import Df.C0736b;
import Gf.m;
import Gf.p;
import Hf.M;
import Hf.a0;
import Qe.C0819i;
import Qe.C0822l;
import Qe.C0823m;
import Qe.C0824n;
import Qe.C0826p;
import Qe.Q;
import Qe.U;
import Re.h;
import java.util.List;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import qf.C1240g;

/* renamed from: Te.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0845f extends C0853n implements U {

    /* renamed from: i  reason: collision with root package name */
    public final p f3774i;

    /* renamed from: j  reason: collision with root package name */
    public final C0826p f3775j;
    public List k;
    public final C0844e l = new C0844e(this);

    static {
        w wVar = v.f4727a;
        wVar.f(new kotlin.jvm.internal.p(wVar.b(C0845f.class), "constructors", "getConstructors()Ljava/util/Collection;"));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0845f(p pVar, C0822l lVar, h hVar, C1240g gVar, C0826p pVar2) {
        super(lVar, hVar, gVar, Q.f3662a);
        j.e(pVar, "storageManager");
        j.e(lVar, "containingDeclaration");
        j.e(pVar2, "visibilityImpl");
        this.f3774i = pVar;
        this.f3775j = pVar2;
        ((m) pVar).a(new g(20, this));
    }

    public final boolean Q() {
        return false;
    }

    public final C0819i a() {
        return this;
    }

    public final boolean b0() {
        return false;
    }

    public final C0826p getVisibility() {
        return this.f3775j;
    }

    public final boolean isExternal() {
        return false;
    }

    public final List j() {
        List list = this.k;
        if (list != null) {
            return list;
        }
        j.k("declaredTypeParametersImpl");
        throw null;
    }

    public final M q() {
        return this.l;
    }

    public final boolean s() {
        return a0.c(((Ff.w) this).G0(), new C0736b(8, this), (Qf.h) null);
    }

    public final String toString() {
        return "typealias " + getName().b();
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.x(this, obj);
    }

    /* renamed from: a  reason: collision with other method in class */
    public final C0822l m55a() {
        return this;
    }

    public final C0823m D0() {
        return this;
    }
}
