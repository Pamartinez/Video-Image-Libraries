package vf;

import kotlin.jvm.internal.j;

/* renamed from: vf.s  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1339s extends C1340t {

    /* renamed from: a  reason: collision with root package name */
    public final C1326f f5163a;

    public C1339s(C1326f fVar) {
        this.f5163a = fVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof C1339s) && j.a(this.f5163a, ((C1339s) obj).f5163a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f5163a.hashCode();
    }

    public final String toString() {
        return "NormalClass(value=" + this.f5163a + ')';
    }
}
