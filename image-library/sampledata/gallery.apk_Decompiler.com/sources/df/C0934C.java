package df;

import Ae.b;
import Af.p;
import Qe.C0816f;
import Qf.k;
import java.util.Collection;
import java.util.Set;
import kotlin.jvm.internal.j;
import me.x;

/* renamed from: df.C  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0934C extends k {
    public final /* synthetic */ C0816f b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Set f4235c;
    public final /* synthetic */ b d;

    public C0934C(C0816f fVar, Set set, b bVar) {
        this.b = fVar;
        this.f4235c = set;
        this.d = bVar;
    }

    public final boolean c(Object obj) {
        C0816f fVar = (C0816f) obj;
        j.e(fVar, "current");
        if (fVar == this.b) {
            return true;
        }
        p c02 = fVar.c0();
        j.d(c02, "getStaticScope(...)");
        if (!(c02 instanceof C0936E)) {
            return true;
        }
        this.f4235c.addAll((Collection) this.d.invoke(c02));
        return false;
    }

    public final /* bridge */ /* synthetic */ Object j() {
        return x.f4917a;
    }
}
