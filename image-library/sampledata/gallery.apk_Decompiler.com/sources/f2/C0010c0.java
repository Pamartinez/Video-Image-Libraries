package F2;

import He.F;
import c0.C0086a;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;

/* renamed from: F2.c0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0010c0 extends O implements Set {
    public static final /* synthetic */ int f = 0;
    public transient U e;

    public static int w(int i2) {
        int max = Math.max(i2, 2);
        boolean z = true;
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1) << 1;
            while (((double) highestOneBit) * 0.7d < ((double) max)) {
                highestOneBit <<= 1;
            }
            return highestOneBit;
        }
        if (max >= 1073741824) {
            z = false;
        }
        F.i("collection too large", z);
        return 1073741824;
    }

    public static C0010c0 x(int i2, Object... objArr) {
        if (i2 == 0) {
            return E0.m;
        }
        if (i2 != 1) {
            int w = w(i2);
            Object[] objArr2 = new Object[w];
            int i7 = w - 1;
            int i8 = 0;
            int i10 = 0;
            int i11 = 0;
            while (i8 < i2) {
                Object obj = objArr[i8];
                if (obj != null) {
                    int hashCode = obj.hashCode();
                    int p6 = C0040v.p(hashCode);
                    while (true) {
                        int i12 = p6 & i7;
                        Object obj2 = objArr2[i12];
                        if (obj2 == null) {
                            objArr[i11] = obj;
                            objArr2[i12] = obj;
                            i10 += hashCode;
                            i11++;
                            break;
                        } else if (obj2.equals(obj)) {
                            break;
                        } else {
                            p6++;
                        }
                    }
                    i8++;
                } else {
                    throw new NullPointerException(C0086a.i(i8, "at index "));
                }
            }
            Arrays.fill(objArr, i11, i2, (Object) null);
            if (i11 == 1) {
                Object obj3 = objArr[0];
                Objects.requireNonNull(obj3);
                return new L0(obj3);
            } else if (w(i11) < w / 2) {
                return x(i11, objArr);
            } else {
                int length = objArr.length;
                if (i11 < (length >> 1) + (length >> 2)) {
                    objArr = Arrays.copyOf(objArr, i11);
                }
                return new E0(i10, i7, i11, objArr, objArr2);
            }
        } else {
            Object obj4 = objArr[0];
            Objects.requireNonNull(obj4);
            return new L0(obj4);
        }
    }

    public static C0010c0 y(Collection collection) {
        if ((collection instanceof C0010c0) && !(collection instanceof SortedSet)) {
            C0010c0 c0Var = (C0010c0) collection;
            if (!c0Var.u()) {
                return c0Var;
            }
        }
        Object[] array = collection.toArray();
        return x(array.length, array);
    }

    public boolean A() {
        return this instanceof E0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0010c0) || !A() || !((C0010c0) obj).A() || hashCode() == obj.hashCode()) {
            return C0040v.f(this, obj);
        }
        return false;
    }

    public int hashCode() {
        return C0040v.j(this);
    }

    public U p() {
        U u = this.e;
        if (u != null) {
            return u;
        }
        U z = z();
        this.e = z;
        return z;
    }

    public U z() {
        Object[] array = toArray(O.d);
        G g = U.e;
        return U.w(array.length, array);
    }
}
