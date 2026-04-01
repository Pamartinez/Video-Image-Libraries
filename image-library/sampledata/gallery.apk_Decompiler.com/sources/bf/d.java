package Bf;

import Hf.B;
import Hf.C0774x;
import Qe.C0816f;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements e {
    public final C0816f d;

    public d(C0816f fVar) {
        this.d = fVar;
    }

    public final boolean equals(Object obj) {
        d dVar;
        C0816f fVar = null;
        if (obj instanceof d) {
            dVar = (d) obj;
        } else {
            dVar = null;
        }
        if (dVar != null) {
            fVar = dVar.d;
        }
        return j.a(this.d, fVar);
    }

    public final C0774x getType() {
        B i2 = this.d.i();
        j.d(i2, "getDefaultType(...)");
        return i2;
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Class{");
        B i2 = this.d.i();
        j.d(i2, "getDefaultType(...)");
        sb2.append(i2);
        sb2.append('}');
        return sb2.toString();
    }
}
