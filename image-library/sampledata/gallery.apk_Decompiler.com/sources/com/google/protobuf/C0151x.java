package com.google.protobuf;

import c0.C0086a;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* renamed from: com.google.protobuf.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0151x extends C0131c implements Internal$FloatList, RandomAccess, b0 {
    public static final C0151x g = new C0151x(new float[0], 0, false);
    public float[] e;
    public int f;

    public C0151x(float[] fArr, int i2, boolean z) {
        super(z);
        this.e = fArr;
        this.f = i2;
    }

    public final void add(int i2, Object obj) {
        int i7;
        float floatValue = ((Float) obj).floatValue();
        i();
        if (i2 < 0 || i2 > (i7 = this.f)) {
            StringBuilder o2 = C0086a.o(i2, "Index:", ", Size:");
            o2.append(this.f);
            throw new IndexOutOfBoundsException(o2.toString());
        }
        float[] fArr = this.e;
        if (i7 < fArr.length) {
            System.arraycopy(fArr, i2, fArr, i2 + 1, i7 - i2);
        } else {
            float[] fArr2 = new float[(((i7 * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i2);
            System.arraycopy(this.e, i2, fArr2, i2 + 1, this.f - i2);
            this.e = fArr2;
        }
        this.e[i2] = floatValue;
        this.f++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        i();
        Charset charset = D.f1578a;
        collection.getClass();
        if (!(collection instanceof C0151x)) {
            return super.addAll(collection);
        }
        C0151x xVar = (C0151x) collection;
        int i2 = xVar.f;
        if (i2 == 0) {
            return false;
        }
        int i7 = this.f;
        if (Integer.MAX_VALUE - i7 >= i2) {
            int i8 = i7 + i2;
            float[] fArr = this.e;
            if (i8 > fArr.length) {
                this.e = Arrays.copyOf(fArr, i8);
            }
            System.arraycopy(xVar.e, 0, this.e, this.f, xVar.f);
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
        if (!(obj instanceof C0151x)) {
            return super.equals(obj);
        }
        C0151x xVar = (C0151x) obj;
        if (this.f != xVar.f) {
            return false;
        }
        float[] fArr = xVar.e;
        for (int i2 = 0; i2 < this.f; i2++) {
            if (Float.floatToIntBits(this.e[i2]) != Float.floatToIntBits(fArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public final Object get(int i2) {
        q(i2);
        return Float.valueOf(this.e[i2]);
    }

    public final int hashCode() {
        int i2 = 1;
        for (int i7 = 0; i7 < this.f; i7++) {
            i2 = (i2 * 31) + Float.floatToIntBits(this.e[i7]);
        }
        return i2;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float floatValue = ((Float) obj).floatValue();
        int i2 = this.f;
        for (int i7 = 0; i7 < i2; i7++) {
            if (this.e[i7] == floatValue) {
                return i7;
            }
        }
        return -1;
    }

    public final void p(float f5) {
        i();
        int i2 = this.f;
        float[] fArr = this.e;
        if (i2 == fArr.length) {
            float[] fArr2 = new float[(((i2 * 3) / 2) + 1)];
            System.arraycopy(fArr, 0, fArr2, 0, i2);
            this.e = fArr2;
        }
        float[] fArr3 = this.e;
        int i7 = this.f;
        this.f = i7 + 1;
        fArr3[i7] = f5;
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
        float[] fArr = this.e;
        float f5 = fArr[i2];
        int i7 = this.f;
        if (i2 < i7 - 1) {
            System.arraycopy(fArr, i2 + 1, fArr, i2, (i7 - i2) - 1);
        }
        this.f--;
        this.modCount++;
        return Float.valueOf(f5);
    }

    public final void removeRange(int i2, int i7) {
        i();
        if (i7 >= i2) {
            float[] fArr = this.e;
            System.arraycopy(fArr, i7, fArr, i2, this.f - i7);
            this.f -= i7 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int i2, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        i();
        q(i2);
        float[] fArr = this.e;
        float f5 = fArr[i2];
        fArr[i2] = floatValue;
        return Float.valueOf(f5);
    }

    public final int size() {
        return this.f;
    }

    public final C0151x a(int i2) {
        if (i2 >= this.f) {
            return new C0151x(Arrays.copyOf(this.e, i2), this.f, true);
        }
        throw new IllegalArgumentException();
    }

    public final boolean add(Object obj) {
        p(((Float) obj).floatValue());
        return true;
    }
}
