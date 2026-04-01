package V0;

import i1.a;
import java.io.Serializable;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k implements Serializable {
    public final String d;

    static {
        new k("");
        new k(new String(""));
    }

    public k(String str) {
        Iterator it = a.f1782a;
        this.d = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != k.class) {
            return false;
        }
        String str = ((k) obj).d;
        String str2 = this.d;
        if (str2 == null) {
            if (str != null) {
                return false;
            }
        } else if (!str2.equals(str)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.d.hashCode();
    }

    public final String toString() {
        return this.d;
    }
}
