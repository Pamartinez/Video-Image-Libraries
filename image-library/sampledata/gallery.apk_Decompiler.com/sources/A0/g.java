package A0;

import D0.e;
import K0.b;
import x0.I;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends e {
    public final /* synthetic */ e g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public g(e eVar) {
        super(9);
        this.g = eVar;
    }

    public final Object K(b bVar) {
        Float f = (Float) ((I) this.g.f);
        if (f == null) {
            return null;
        }
        return Float.valueOf(f.floatValue() * 2.55f);
    }
}
