package We;

import gf.C1071b;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1202t;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class y extends s implements C1071b {

    /* renamed from: a  reason: collision with root package name */
    public final C1236c f3896a;

    public y(C1236c cVar) {
        j.e(cVar, "fqName");
        this.f3896a = cVar;
    }

    public final C0893e a(C1236c cVar) {
        j.e(cVar, "fqName");
        return null;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof y)) {
            return false;
        }
        if (j.a(this.f3896a, ((y) obj).f3896a)) {
            return true;
        }
        return false;
    }

    public final /* bridge */ /* synthetic */ Collection getAnnotations() {
        return C1202t.d;
    }

    public final int hashCode() {
        return this.f3896a.hashCode();
    }

    public final String toString() {
        return y.class.getName() + ": " + this.f3896a;
    }
}
