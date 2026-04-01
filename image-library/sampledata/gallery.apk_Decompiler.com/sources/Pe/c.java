package Pe;

import kotlin.jvm.internal.j;
import qf.C1235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final C1235b f3631a;
    public final C1235b b;

    /* renamed from: c  reason: collision with root package name */
    public final C1235b f3632c;

    public c(C1235b bVar, C1235b bVar2, C1235b bVar3) {
        this.f3631a = bVar;
        this.b = bVar2;
        this.f3632c = bVar3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (j.a(this.f3631a, cVar.f3631a) && j.a(this.b, cVar.b) && j.a(this.f3632c, cVar.f3632c)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.b.hashCode();
        return this.f3632c.hashCode() + ((hashCode + (this.f3631a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "PlatformMutabilityMapping(javaClass=" + this.f3631a + ", kotlinReadOnly=" + this.b + ", kotlinMutable=" + this.f3632c + ')';
    }
}
