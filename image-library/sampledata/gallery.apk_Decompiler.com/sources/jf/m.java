package Jf;

import Ae.b;
import Af.f;
import Qe.C0819i;
import Ye.a;
import c0.C0086a;
import java.util.Collection;
import java.util.Set;
import kotlin.jvm.internal.j;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m extends g {
    public final /* bridge */ /* synthetic */ Collection a(C1240g gVar, a aVar) {
        h(gVar, aVar);
        throw null;
    }

    public final Set b() {
        throw new IllegalStateException();
    }

    public final C0819i c(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        throw new IllegalStateException(this.b + ", required name: " + gVar);
    }

    public final Collection d(f fVar, b bVar) {
        j.e(fVar, "kindFilter");
        throw new IllegalStateException(this.b);
    }

    public final Set e() {
        throw new IllegalStateException();
    }

    public final /* bridge */ /* synthetic */ Collection f(C1240g gVar, a aVar) {
        i(gVar, aVar);
        throw null;
    }

    public final Set g() {
        throw new IllegalStateException();
    }

    public final Set h(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        throw new IllegalStateException(this.b + ", required name: " + gVar);
    }

    public final Set i(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        throw new IllegalStateException(this.b + ", required name: " + gVar);
    }

    public final String toString() {
        return C0086a.m(new StringBuilder("ThrowingScope{"), this.b, '}');
    }
}
