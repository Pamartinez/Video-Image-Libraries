package com.google.protobuf;

import c0.C0086a;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class M extends C0131c implements Internal$LongList, RandomAccess, b0 {
    public static final M g = new M(new long[0], 0, false);
    public long[] e;
    public int f;

    public M(long[] jArr, int i2, boolean z) {
        super(z);
        this.e = jArr;
        this.f = i2;
    }

    public final void add(int i2, Object obj) {
        int i7;
        long longValue = ((Long) obj).longValue();
        i();
        if (i2 < 0 || i2 > (i7 = this.f)) {
            StringBuilder o2 = C0086a.o(i2, "Index:", ", Size:");
            o2.append(this.f);
            throw new IndexOutOfBoundsException(o2.toString());
        }
        long[] jArr = this.e;
        if (i7 < jArr.length) {
            System.arraycopy(jArr, i2, jArr, i2 + 1, i7 - i2);
        } else {
            long[] jArr2 = new long[(((i7 * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i2);
            System.arraycopy(this.e, i2, jArr2, i2 + 1, this.f - i2);
            this.e = jArr2;
        }
        this.e[i2] = longValue;
        this.f++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        i();
        Charset charset = D.f1578a;
        collection.getClass();
        if (!(collection instanceof M)) {
            return super.addAll(collection);
        }
        M m = (M) collection;
        int i2 = m.f;
        if (i2 == 0) {
            return false;
        }
        int i7 = this.f;
        if (Integer.MAX_VALUE - i7 >= i2) {
            int i8 = i7 + i2;
            long[] jArr = this.e;
            if (i8 > jArr.length) {
                this.e = Arrays.copyOf(jArr, i8);
            }
            System.arraycopy(m.e, 0, this.e, this.f, m.f);
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

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof M)) {
            return super.equals(obj);
        }
        M m = (M) obj;
        if (this.f != m.f) {
            return false;
        }
        long[] jArr = m.e;
        for (int i2 = 0; i2 < this.f; i2++) {
            if (this.e[i2] != jArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public final Object get(int i2) {
        q(i2);
        return Long.valueOf(this.e[i2]);
    }

    public final int hashCode() {
        int i2 = 1;
        for (int i7 = 0; i7 < this.f; i7++) {
            i2 = (i2 * 31) + D.b(this.e[i7]);
        }
        return i2;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Long)) {
            return -1;
        }
        long longValue = ((Long) obj).longValue();
        int i2 = this.f;
        for (int i7 = 0; i7 < i2; i7++) {
            if (this.e[i7] == longValue) {
                return i7;
            }
        }
        return -1;
    }

    public final void p(long j2) {
        i();
        int i2 = this.f;
        long[] jArr = this.e;
        if (i2 == jArr.length) {
            long[] jArr2 = new long[(((i2 * 3) / 2) + 1)];
            System.arraycopy(jArr, 0, jArr2, 0, i2);
            this.e = jArr2;
        }
        long[] jArr3 = this.e;
        int i7 = this.f;
        this.f = i7 + 1;
        jArr3[i7] = j2;
    }

    public final void q(int i2) {
        if (i2 < 0 || i2 >= this.f) {
            StringBuilder o2 = C0086a.o(i2, "Index:", ", Size:");
            o2.append(this.f);
            throw new IndexOutOfBoundsException(o2.toString());
        }
    }

    public final Object remove(int i2) {
        i();
        q(i2);
        long[] jArr = this.e;
        long j2 = jArr[i2];
        int i7 = this.f;
        if (i2 < i7 - 1) {
            System.arraycopy(jArr, i2 + 1, jArr, i2, (i7 - i2) - 1);
        }
        this.f--;
        this.modCount++;
        return Long.valueOf(j2);
    }

    public final void removeRange(int i2, int i7) {
        i();
        if (i7 >= i2) {
            long[] jArr = this.e;
            System.arraycopy(jArr, i7, jArr, i2, this.f - i7);
            this.f -= i7 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int i2, Object obj) {
        long longValue = ((Long) obj).longValue();
        i();
        q(i2);
        long[] jArr = this.e;
        long j2 = jArr[i2];
        jArr[i2] = longValue;
        return Long.valueOf(j2);
    }

    public final int size() {
        return this.f;
    }

    public final M a(int i2) {
        if (i2 >= this.f) {
            return new M(Arrays.copyOf(this.e, i2), this.f, true);
        }
        throw new IllegalArgumentException();
    }

    public final boolean add(Object obj) {
        p(((Long) obj).longValue());
        return true;
    }
}
