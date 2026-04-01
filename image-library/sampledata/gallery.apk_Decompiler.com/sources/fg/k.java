package fg;

import cg.s;
import java.util.concurrent.atomic.AtomicReferenceArray;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends s {
    public final /* synthetic */ AtomicReferenceArray e = new AtomicReferenceArray(j.f);

    public k(long j2, k kVar, int i2) {
        super(j2, kVar, i2);
    }

    public final int g() {
        return j.f;
    }

    public final void h(int i2, C1232h hVar) {
        this.e.set(i2, j.e);
        i();
    }

    public final String toString() {
        return "SemaphoreSegment[id=" + this.f4032c + ", hashCode=" + hashCode() + ']';
    }
}
