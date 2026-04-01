package A0;

import K0.a;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements b {
    public final a d;
    public float e = -1.0f;

    public d(List list) {
        this.d = (a) list.get(0);
    }

    public final boolean a(float f) {
        if (this.e == f) {
            return true;
        }
        this.e = f;
        return false;
    }

    public final a b() {
        return this.d;
    }

    public final boolean c(float f) {
        return !this.d.c();
    }

    public final float d() {
        return this.d.b();
    }

    public final float e() {
        return this.d.a();
    }

    public final boolean isEmpty() {
        return false;
    }
}
