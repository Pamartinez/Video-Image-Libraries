package h1;

import java.io.Serializable;

/* renamed from: h1.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0201a implements Comparable, Serializable {
    public String d;
    public Class e;
    public int f;

    public final int compareTo(Object obj) {
        return this.d.compareTo(((C0201a) obj).d);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == C0201a.class && ((C0201a) obj).e == this.e) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.f;
    }

    public final String toString() {
        return this.d;
    }
}
