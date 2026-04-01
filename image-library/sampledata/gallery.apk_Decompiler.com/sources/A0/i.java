package A0;

import D0.e;
import J0.f;
import K0.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends k {
    public final Object g(a aVar, float f) {
        return Float.valueOf(m(aVar, f));
    }

    public final float l() {
        return m(b(), d());
    }

    public final float m(a aVar, float f) {
        float f5;
        Object obj = aVar.b;
        Object obj2 = aVar.b;
        if (obj == null || aVar.f370c == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        e eVar = this.e;
        if (eVar != null) {
            f5 = f;
            Float f8 = (Float) eVar.L(aVar.g, aVar.f371h.floatValue(), (Float) obj2, (Float) aVar.f370c, f5, e(), this.d);
            if (f8 != null) {
                return f8.floatValue();
            }
        } else {
            f5 = f;
        }
        if (aVar.f372i == -3987645.8f) {
            aVar.f372i = ((Float) obj2).floatValue();
        }
        float f10 = aVar.f372i;
        if (aVar.f373j == -3987645.8f) {
            aVar.f373j = ((Float) aVar.f370c).floatValue();
        }
        return f.e(f10, aVar.f373j, f5);
    }
}
