package df;

import We.o;
import kotlin.jvm.internal.j;
import qf.C1240g;

/* renamed from: df.r  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0955r {

    /* renamed from: a  reason: collision with root package name */
    public final C1240g f4258a;
    public final o b;

    public C0955r(C1240g gVar, o oVar) {
        j.e(gVar, "name");
        this.f4258a = gVar;
        this.b = oVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0955r)) {
            return false;
        }
        if (j.a(this.f4258a, ((C0955r) obj).f4258a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f4258a.hashCode();
    }
}
