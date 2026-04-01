package F2;

import java.io.Serializable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G0 extends w0 implements Serializable {
    public final w0 d;

    public G0(w0 w0Var) {
        this.d = w0Var;
    }

    public final w0 a() {
        return this.d;
    }

    public final int compare(Object obj, Object obj2) {
        return this.d.compare(obj2, obj);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof G0) {
            return this.d.equals(((G0) obj).d);
        }
        return false;
    }

    public final int hashCode() {
        return -this.d.hashCode();
    }

    public final String toString() {
        return this.d + ".reverse()";
    }
}
