package Qe;

import i.C0212a;
import java.util.List;
import kotlin.jvm.internal.j;
import qf.C1235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class F {

    /* renamed from: a  reason: collision with root package name */
    public final C1235b f3657a;
    public final List b;

    public F(C1235b bVar, List list) {
        j.e(bVar, "classId");
        this.f3657a = bVar;
        this.b = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof F)) {
            return false;
        }
        F f = (F) obj;
        if (j.a(this.f3657a, f.f3657a) && j.a(this.b, f.b)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.f3657a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("ClassRequest(classId=");
        sb2.append(this.f3657a);
        sb2.append(", typeParametersCount=");
        return C0212a.r(sb2, this.b, ')');
    }
}
