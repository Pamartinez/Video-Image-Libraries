package F2;

import E2.h;
import java.io.Serializable;
import java.util.Arrays;

/* renamed from: F2.u  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0039u extends w0 implements Serializable {
    public final h d;
    public final w0 e;

    public C0039u(h hVar, w0 w0Var) {
        hVar.getClass();
        this.d = hVar;
        this.e = w0Var;
    }

    public final int compare(Object obj, Object obj2) {
        h hVar = this.d;
        return this.e.compare(hVar.apply(obj), hVar.apply(obj2));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0039u) {
            C0039u uVar = (C0039u) obj;
            if (!this.d.equals(uVar.d) || !this.e.equals(uVar.e)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.d, this.e});
    }

    public final String toString() {
        return this.e + ".onResultOf(" + this.d + ")";
    }
}
