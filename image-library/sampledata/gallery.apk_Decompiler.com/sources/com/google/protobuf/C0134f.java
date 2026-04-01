package com.google.protobuf;

import c0.C0086a;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* renamed from: com.google.protobuf.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0134f extends C0131c implements Internal$BooleanList, RandomAccess, b0 {
    public static final C0134f g = new C0134f(new boolean[0], 0, false);
    public boolean[] e;
    public int f;

    public C0134f(boolean[] zArr, int i2, boolean z) {
        super(z);
        this.e = zArr;
        this.f = i2;
    }

    public final void add(int i2, Object obj) {
        int i7;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        i();
        if (i2 < 0 || i2 > (i7 = this.f)) {
            StringBuilder o2 = C0086a.o(i2, "Index:", ", Size:");
            o2.append(this.f);
            throw new IndexOutOfBoundsException(o2.toString());
        }
        boolean[] zArr = this.e;
        if (i7 < zArr.length) {
            System.arraycopy(zArr, i2, zArr, i2 + 1, i7 - i2);
        } else {
            boolean[] zArr2 = new boolean[(((i7 * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i2);
            System.arraycopy(this.e, i2, zArr2, i2 + 1, this.f - i2);
            this.e = zArr2;
        }
        this.e[i2] = booleanValue;
        this.f++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        i();
        Charset charset = D.f1578a;
        collection.getClass();
        if (!(collection instanceof C0134f)) {
            return super.addAll(collection);
        }
        C0134f fVar = (C0134f) collection;
        int i2 = fVar.f;
        if (i2 == 0) {
            return false;
        }
        int i7 = this.f;
        if (Integer.MAX_VALUE - i7 >= i2) {
            int i8 = i7 + i2;
            boolean[] zArr = this.e;
            if (i8 > zArr.length) {
                this.e = Arrays.copyOf(zArr, i8);
            }
            System.arraycopy(fVar.e, 0, this.e, this.f, fVar.f);
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
        if (!(obj instanceof C0134f)) {
            return super.equals(obj);
        }
        C0134f fVar = (C0134f) obj;
        if (this.f != fVar.f) {
            return false;
        }
        boolean[] zArr = fVar.e;
        for (int i2 = 0; i2 < this.f; i2++) {
            if (this.e[i2] != zArr[i2]) {
                return false;
            }
        }
        return true;
    }

    public final Object get(int i2) {
        q(i2);
        return Boolean.valueOf(this.e[i2]);
    }

    public final int hashCode() {
        int i2;
        int i7 = 1;
        for (int i8 = 0; i8 < this.f; i8++) {
            int i10 = i7 * 31;
            boolean z = this.e[i8];
            Charset charset = D.f1578a;
            if (z) {
                i2 = 1231;
            } else {
                i2 = 1237;
            }
            i7 = i10 + i2;
        }
        return i7;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Boolean)) {
            return -1;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        int i2 = this.f;
        for (int i7 = 0; i7 < i2; i7++) {
            if (this.e[i7] == booleanValue) {
                return i7;
            }
        }
        return -1;
    }

    public final void p(boolean z) {
        i();
        int i2 = this.f;
        boolean[] zArr = this.e;
        if (i2 == zArr.length) {
            boolean[] zArr2 = new boolean[(((i2 * 3) / 2) + 1)];
            System.arraycopy(zArr, 0, zArr2, 0, i2);
            this.e = zArr2;
        }
        boolean[] zArr3 = this.e;
        int i7 = this.f;
        this.f = i7 + 1;
        zArr3[i7] = z;
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
        boolean[] zArr = this.e;
        boolean z = zArr[i2];
        int i7 = this.f;
        if (i2 < i7 - 1) {
            System.arraycopy(zArr, i2 + 1, zArr, i2, (i7 - i2) - 1);
        }
        this.f--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final void removeRange(int i2, int i7) {
        i();
        if (i7 >= i2) {
            boolean[] zArr = this.e;
            System.arraycopy(zArr, i7, zArr, i2, this.f - i7);
            this.f -= i7 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int i2, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        i();
        q(i2);
        boolean[] zArr = this.e;
        boolean z = zArr[i2];
        zArr[i2] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final int size() {
        return this.f;
    }

    public final C0134f a(int i2) {
        if (i2 >= this.f) {
            return new C0134f(Arrays.copyOf(this.e, i2), this.f, true);
        }
        throw new IllegalArgumentException();
    }

    public final boolean add(Object obj) {
        p(((Boolean) obj).booleanValue());
        return true;
    }
}
