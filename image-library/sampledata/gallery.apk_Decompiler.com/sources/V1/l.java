package v1;

import ge.W0;
import java.util.Arrays;
import t1.C0278c;
import w1.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    public final C0296a f1969a;
    public final C0278c b;

    public /* synthetic */ l(C0296a aVar, C0278c cVar) {
        this.f1969a = aVar;
        this.b = cVar;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof l)) {
            l lVar = (l) obj;
            if (!r.d(this.f1969a, lVar.f1969a) || !r.d(this.b, lVar.b)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f1969a, this.b});
    }

    public final String toString() {
        W0 w02 = new W0((Object) this);
        w02.v0(this.f1969a, "key");
        w02.v0(this.b, "feature");
        return w02.toString();
    }
}
