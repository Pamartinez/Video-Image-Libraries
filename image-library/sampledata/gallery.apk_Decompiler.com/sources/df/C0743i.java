package Df;

import kotlin.jvm.internal.j;
import qf.C1235b;

/* renamed from: Df.i  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0743i {

    /* renamed from: a  reason: collision with root package name */
    public final C1235b f3345a;
    public final C0741g b;

    public C0743i(C1235b bVar, C0741g gVar) {
        j.e(bVar, "classId");
        this.f3345a = bVar;
        this.b = gVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0743i)) {
            return false;
        }
        if (j.a(this.f3345a, ((C0743i) obj).f3345a)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f3345a.hashCode();
    }
}
