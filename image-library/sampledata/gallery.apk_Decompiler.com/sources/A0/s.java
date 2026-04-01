package A0;

import D0.e;
import K0.a;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class s extends e {

    /* renamed from: i  reason: collision with root package name */
    public final Object f27i;

    public s(e eVar, Object obj) {
        super(Collections.EMPTY_LIST);
        k(eVar);
        this.f27i = obj;
    }

    public final float c() {
        return 1.0f;
    }

    public final Object f() {
        e eVar = this.e;
        Object obj = this.f27i;
        float f = this.d;
        return eVar.L(0.0f, 0.0f, obj, obj, f, f, f);
    }

    public final Object g(a aVar, float f) {
        return f();
    }

    public final void i() {
        if (this.e != null) {
            super.i();
        }
    }

    public final void j(float f) {
        this.d = f;
    }
}
