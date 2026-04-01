package D0;

import A0.e;
import A0.p;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements h {
    public final b d;
    public final b e;

    public d(b bVar, b bVar2) {
        this.d = bVar;
        this.e = bVar2;
    }

    public final e p0() {
        return new p(this.d.p0(), this.e.p0());
    }

    public final List s0() {
        throw new UnsupportedOperationException("Cannot call getKeyframes on AnimatableSplitDimensionPathValue.");
    }

    public final boolean u0() {
        if (!this.d.u0() || !this.e.u0()) {
            return false;
        }
        return true;
    }
}
