package com.google.protobuf;

import c0.C0086a;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* renamed from: com.google.protobuf.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0144p extends C0131c implements Internal$DoubleList, RandomAccess, b0 {
    public static final C0144p g = new C0144p(new double[0], 0, false);
    public double[] e;
    public int f;

    public C0144p(double[] dArr, int i2, boolean z) {
        super(z);
        this.e = dArr;
        this.f = i2;
    }

    public final void add(int i2, Object obj) {
        int i7;
        double doubleValue = ((Double) obj).doubleValue();
        i();
        if (i2 < 0 || i2 > (i7 = this.f)) {
            StringBuilder o2 = C0086a.o(i2, "Index:", ", Size:");
            o2.append(this.f);
            throw new IndexOutOfBoundsException(o2.toString());
        }
        double[] dArr = this.e;
        if (i7 < dArr.length) {
            System.arraycopy(dArr, i2, dArr, i2 + 1, i7 - i2);
        } else {
            double[] dArr2 = new double[(((i7 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i2);
            System.arraycopy(this.e, i2, dArr2, i2 + 1, this.f - i2);
            this.e = dArr2;
        }
        this.e[i2] = doubleValue;
        this.f++;
        this.modCount++;
    }

    public final boolean addAll(Collection collection) {
        i();
        Charset charset = D.f1578a;
        collection.getClass();
        if (!(collection instanceof C0144p)) {
            return super.addAll(collection);
        }
        C0144p pVar = (C0144p) collection;
        int i2 = pVar.f;
        if (i2 == 0) {
            return false;
        }
        int i7 = this.f;
        if (Integer.MAX_VALUE - i7 >= i2) {
            int i8 = i7 + i2;
            double[] dArr = this.e;
            if (i8 > dArr.length) {
                this.e = Arrays.copyOf(dArr, i8);
            }
            System.arraycopy(pVar.e, 0, this.e, this.f, pVar.f);
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
        if (!(obj instanceof C0144p)) {
            return super.equals(obj);
        }
        C0144p pVar = (C0144p) obj;
        if (this.f != pVar.f) {
            return false;
        }
        double[] dArr = pVar.e;
        for (int i2 = 0; i2 < this.f; i2++) {
            if (Double.doubleToLongBits(this.e[i2]) != Double.doubleToLongBits(dArr[i2])) {
                return false;
            }
        }
        return true;
    }

    public final Object get(int i2) {
        q(i2);
        return Double.valueOf(this.e[i2]);
    }

    public final int hashCode() {
        int i2 = 1;
        for (int i7 = 0; i7 < this.f; i7++) {
            i2 = (i2 * 31) + D.b(Double.doubleToLongBits(this.e[i7]));
        }
        return i2;
    }

    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double doubleValue = ((Double) obj).doubleValue();
        int i2 = this.f;
        for (int i7 = 0; i7 < i2; i7++) {
            if (this.e[i7] == doubleValue) {
                return i7;
            }
        }
        return -1;
    }

    public final void p(double d) {
        i();
        int i2 = this.f;
        double[] dArr = this.e;
        if (i2 == dArr.length) {
            double[] dArr2 = new double[(((i2 * 3) / 2) + 1)];
            System.arraycopy(dArr, 0, dArr2, 0, i2);
            this.e = dArr2;
        }
        double[] dArr3 = this.e;
        int i7 = this.f;
        this.f = i7 + 1;
        dArr3[i7] = d;
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
        double[] dArr = this.e;
        double d = dArr[i2];
        int i7 = this.f;
        if (i2 < i7 - 1) {
            System.arraycopy(dArr, i2 + 1, dArr, i2, (i7 - i2) - 1);
        }
        this.f--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final void removeRange(int i2, int i7) {
        i();
        if (i7 >= i2) {
            double[] dArr = this.e;
            System.arraycopy(dArr, i7, dArr, i2, this.f - i7);
            this.f -= i7 - i2;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    public final Object set(int i2, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        i();
        q(i2);
        double[] dArr = this.e;
        double d = dArr[i2];
        dArr[i2] = doubleValue;
        return Double.valueOf(d);
    }

    public final int size() {
        return this.f;
    }

    public final C0144p a(int i2) {
        if (i2 >= this.f) {
            return new C0144p(Arrays.copyOf(this.e, i2), this.f, true);
        }
        throw new IllegalArgumentException();
    }

    public final boolean add(Object obj) {
        p(((Double) obj).doubleValue());
        return true;
    }
}
