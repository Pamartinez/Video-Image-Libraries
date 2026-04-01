package com.google.protobuf;

import c0.C0086a;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class B extends C0131c implements Internal$IntList, RandomAccess, b0 {
    public static final B g = new B(new int[0], 0, false);
    public int[] e;
    public int f;

    public B(int[] iArr, int i2, boolean z) {
        super(z);
        this.e = iArr;
        this.f = i2;
    }

    public final void add(int i2, Object obj) {
        int i7;
        int intValue = ((Integer) obj).intValue();
        i();
        if (i2 < 0 || i2 > (i7 = this.f)) {
            StringBuilder o2 = C0086a.o(i2, "Index:", ", Size:");
            o2.append(this.f);
            throw new IndexOutOfBoundsException(o2.toString());
        }
        int[] iArr = this.e;
        if (i7 < iArr.length) {
            System.arraycopy(iArr, i2, iArr, i2 + 1, i7 - i2);
        } else {
            int[] iArr2 = new int[(((i7 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.e, i2, iArr2, i2 + 1, this.f - i2);
            this.e = iArr2;
        }
        this.e[i2] = intValue;
        this.f++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        i();
        Charset charset = D.f1578a;
        collection.getClass();
        if (!(collection instanceof B)) {
            return super.addAll(collection);
        }
        B b = (B) collection;
        int i2 = b.f;
        if (i2 == 0) {
            return false;
        }
        int i7 = this.f;
        if (Integer.MAX_VALUE - i7 >= i2) {
            int i8 = i7 + i2;
            int[] iArr = this.e;
            if (i8 > iArr.length) {
                this.e = Arrays.copyOf(iArr, i8);
            }
            System.arraycopy(b.e, 0, this.e, this.f, b.f);
            this.f = i8;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    public final boolean contains(Object obj) {
        if (indexOf(obj) != -1) {
            return true;
        }
        return false;
    }

    public final int e(int i2, int i7) {
        i();
        p(i2);
        int[] iArr = this.e;
        int i8 = iArr[i2];
        iArr[i2] = i7;
        return i8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof B)) {
            return super.equals(obj);
        }
        B b = (B) obj;
        if (this.f != b.f) {
            return false;
        }
        int[] iArr = b.e;
        for (int i2 = 0; i2 < this.f; i2++) {
            if (this.e[i2] != iArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public final Object get(int i2) {
        return Integer.valueOf(getInt(i2));
    }

    public final int getInt(int i2) {
        p(i2);
        return this.e[i2];
    }

    public final int hashCode() {
        int i2 = 1;
        for (int i7 = 0; i7 < this.f; i7++) {
            i2 = (i2 * 31) + this.e[i7];
        }
        return i2;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int intValue = ((Integer) obj).intValue();
        int i2 = this.f;
        for (int i7 = 0; i7 < i2; i7++) {
            if (this.e[i7] == intValue) {
                return i7;
            }
        }
        return -1;
    }

    public final void k(int i2) {
        i();
        int i7 = this.f;
        int[] iArr = this.e;
        if (i7 == iArr.length) {
            int[] iArr2 = new int[(((i7 * 3) / 2) + 1)];
            System.arraycopy(iArr, 0, iArr2, 0, i7);
            this.e = iArr2;
        }
        int[] iArr3 = this.e;
        int i8 = this.f;
        this.f = i8 + 1;
        iArr3[i8] = i2;
    }

    public final void p(int i2) {
        if (i2 < 0 || i2 >= this.f) {
            StringBuilder o2 = C0086a.o(i2, "Index:", ", Size:");
            o2.append(this.f);
            throw new IndexOutOfBoundsException(o2.toString());
        }
    }

    public final Object remove(int i2) {
        i();
        p(i2);
        int[] iArr = this.e;
        int i7 = iArr[i2];
        int i8 = this.f;
        if (i2 < i8 - 1) {
            System.arraycopy(iArr, i2 + 1, iArr, i2, (i8 - i2) - 1);
        }
        this.f--;
        this.modCount++;
        return Integer.valueOf(i7);
    }

    public final void removeRange(int i2, int i7) {
        i();
        if (i7 >= i2) {
            int[] iArr = this.e;
            System.arraycopy(iArr, i7, iArr, i2, this.f - i7);
            this.f -= i7 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int i2, Object obj) {
        return Integer.valueOf(e(i2, ((Integer) obj).intValue()));
    }

    public final int size() {
        return this.f;
    }

    public final B a(int i2) {
        if (i2 >= this.f) {
            return new B(Arrays.copyOf(this.e, i2), this.f, true);
        }
        throw new IllegalArgumentException();
    }

    public final boolean add(Object obj) {
        k(((Integer) obj).intValue());
        return true;
    }
}
