package J2;

import He.F;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends AbstractList implements RandomAccess, Serializable {
    public final int[] d;
    public final int e;
    public final int f;

    public b(int[] iArr, int i2, int i7) {
        this.d = iArr;
        this.e = i2;
        this.f = i7;
    }

    public final boolean contains(Object obj) {
        if (!(obj instanceof Integer)) {
            return false;
        }
        int intValue = ((Integer) obj).intValue();
        int i2 = this.e;
        while (true) {
            if (i2 >= this.f) {
                i2 = -1;
                break;
            } else if (this.d[i2] == intValue) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b)) {
            return super.equals(obj);
        }
        b bVar = (b) obj;
        int size = size();
        if (bVar.size() != size) {
            return false;
        }
        for (int i2 = 0; i2 < size; i2++) {
            if (this.d[this.e + i2] != bVar.d[bVar.e + i2]) {
                return false;
            }
        }
        return true;
    }

    public final Object get(int i2) {
        F.m(i2, size());
        return Integer.valueOf(this.d[this.e + i2]);
    }

    public final int hashCode() {
        int i2 = 1;
        for (int i7 = this.e; i7 < this.f; i7++) {
            i2 = (i2 * 31) + this.d[i7];
        }
        return i2;
    }

    public final int indexOf(Object obj) {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            int i2 = this.e;
            int i7 = i2;
            while (true) {
                if (i7 >= this.f) {
                    i7 = -1;
                    break;
                } else if (this.d[i7] == intValue) {
                    break;
                } else {
                    i7++;
                }
            }
            if (i7 >= 0) {
                return i7 - i2;
            }
        }
        return -1;
    }

    public final boolean isEmpty() {
        return false;
    }

    public final int lastIndexOf(Object obj) {
        int i2;
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            int i7 = this.f;
            while (true) {
                i7--;
                i2 = this.e;
                if (i7 < i2) {
                    i7 = -1;
                    break;
                } else if (this.d[i7] == intValue) {
                    break;
                }
            }
            if (i7 >= 0) {
                return i7 - i2;
            }
        }
        return -1;
    }

    public final Object set(int i2, Object obj) {
        Integer num = (Integer) obj;
        F.m(i2, size());
        int i7 = this.e + i2;
        int[] iArr = this.d;
        int i8 = iArr[i7];
        num.getClass();
        iArr[i7] = num.intValue();
        return Integer.valueOf(i8);
    }

    public final int size() {
        return this.f - this.e;
    }

    public final List subList(int i2, int i7) {
        F.p(i2, i7, size());
        if (i2 == i7) {
            return Collections.EMPTY_LIST;
        }
        int i8 = this.e;
        return new b(this.d, i2 + i8, i8 + i7);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder(size() * 5);
        sb2.append('[');
        int[] iArr = this.d;
        int i2 = this.e;
        sb2.append(iArr[i2]);
        while (true) {
            i2++;
            if (i2 < this.f) {
                sb2.append(ArcCommonLog.TAG_COMMA);
                sb2.append(iArr[i2]);
            } else {
                sb2.append(']');
                return sb2.toString();
            }
        }
    }
}
