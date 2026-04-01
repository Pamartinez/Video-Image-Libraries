package ee;

import B1.a;
import E2.k;

/* renamed from: ee.D  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0967D {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return false;
    }

    public final String toString() {
        k V = a.V(this);
        V.a("pick_first", "policy");
        V.d("priority", String.valueOf(5));
        V.c("available", true);
        return V.toString();
    }
}
