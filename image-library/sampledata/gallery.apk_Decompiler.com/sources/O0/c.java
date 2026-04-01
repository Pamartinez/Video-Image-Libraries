package O0;

import L0.a;
import L0.d;
import java.lang.ref.SoftReference;
import pg.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends b {
    public c() {
        new a();
        SoftReference softReference = (SoftReference) d.f388a.get();
        if (softReference != null && softReference.get() != null) {
            throw new ClassCastException();
        }
    }
}
