package Ge;

import Be.a;
import L1.d;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class c implements Iterable, a {
    public final int d;
    public final int e;
    public final int f;

    public c(int i2, int i7, int i8) {
        if (i8 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        } else if (i8 != Integer.MIN_VALUE) {
            this.d = i2;
            this.e = d.l(i2, i7, i8);
            this.f = i8;
        } else {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        if (isEmpty() && ((c) obj).isEmpty()) {
            return true;
        }
        c cVar = (c) obj;
        if (this.d == cVar.d && this.e == cVar.e && this.f == cVar.f) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.d * 31) + this.e) * 31) + this.f;
    }

    public boolean isEmpty() {
        int i2 = this.f;
        int i7 = this.e;
        int i8 = this.d;
        if (i2 > 0) {
            if (i8 > i7) {
                return true;
            }
            return false;
        } else if (i8 < i7) {
            return true;
        } else {
            return false;
        }
    }

    public final Iterator iterator() {
        return new d(this.d, this.e, this.f);
    }

    public String toString() {
        StringBuilder sb2;
        int i2 = this.e;
        int i7 = this.d;
        int i8 = this.f;
        if (i8 > 0) {
            sb2.append(i7);
            sb2.append("..");
            sb2.append(i2);
            sb2.append(" step ");
        } else {
            sb2 = new StringBuilder();
            sb2.append(i7);
            sb2.append(" downTo ");
            sb2.append(i2);
            sb2.append(" step ");
            i8 = -i8;
        }
        sb2.append(i8);
        return sb2.toString();
    }
}
